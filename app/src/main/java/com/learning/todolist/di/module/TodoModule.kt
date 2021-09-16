package com.learning.todolist.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.learning.todolist.data.repository.MainRepository
import com.learning.todolist.ui.todo.TodoListActivity
import com.learning.todolist.ui.todo.TodoListViewModel
import com.learning.todolist.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class TodoModule(private val activity: TodoListActivity) {

    @Provides
    fun provideTodoListViewModel(mainRepository: MainRepository): TodoListViewModel =
         ViewModelProvider(
            activity, ViewModelProviderFactory(TodoListViewModel::class) {
                TodoListViewModel(mainRepository)
            }).get(TodoListViewModel::class.java)

}