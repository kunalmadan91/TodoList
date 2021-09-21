package com.learning.todolist.ui.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.todolist.TodoListApplication
import com.learning.todolist.data.model.TodoModel
import com.learning.todolist.databinding.ActivityMainBinding
import com.learning.todolist.di.component.DaggerTodoComponent
import com.learning.todolist.di.module.TodoModule
import com.learning.todolist.utils.common.Status
import com.learning.todolist.utils.network.NetworkHelper
import timber.log.Timber
import javax.inject.Inject

class TodoListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TodoListViewModel

    @Inject
    lateinit var  networkHelper: NetworkHelper

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        injectDependencies();
        if(!networkHelper.isNetworkConnected())  {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
            return
        }
        setupUI();
        setupObservers();
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                ( binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter

        binding.textViewReload.setOnClickListener {
            setupObservers()
        }
    }

    private fun setupObservers() {
        viewModel.makeNetworkCall().observe(this,
            {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Timber.d("response" + resource.data)
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            binding.rlerror.visibility = View.GONE
                            resource.data?.let { items -> retrieveList(items) }
                        }
                        Status.ERROR -> {
                            Timber.d("error" + resource.data)
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            binding.rlerror.visibility = View.VISIBLE
                        }
                        Status.LOADING -> {
                            Timber.d("loading" + resource.data)
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                            binding.rlerror.visibility = View.GONE
                        }
                        Status.UNKNOWN -> {
                            Timber.d("unknown error" + resource.data)
                            binding.recyclerView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            binding.rlerror.visibility = View.VISIBLE
                        }
                    }
                }
            }
        )
    }

    private fun retrieveList(items: List<TodoModel>) {
        adapter.apply {
            addList(items)
            notifyDataSetChanged()
        }
    }

    private fun injectDependencies() {
        DaggerTodoComponent
            .builder()
            .applicationComponent((application as TodoListApplication).applicationComponent)
            .todoModule(TodoModule(this))
            .build()
            .inject(this)
    }
}