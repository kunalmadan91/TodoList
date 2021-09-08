package com.learning.todolist.ui.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learning.todolist.R
import com.learning.todolist.TodoListApplication
import com.learning.todolist.di.component.DaggerActivityComponent
import com.learning.todolist.di.module.TodoModule
import javax.inject.Inject

class TodoListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies();

        viewModel.makeNetworkCall()


        viewModel.makeNetworkCall().observe(this,
            {
                it?.let {

                }
            }
        )
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as TodoListApplication).applicationComponent)
            .todoModule(TodoModule(this))
            .build()
    }
}