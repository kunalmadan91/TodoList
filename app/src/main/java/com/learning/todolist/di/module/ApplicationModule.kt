package com.learning.todolist.di.module

import com.learning.todolist.TodoListApplication
import dagger.Module

@Module
class ApplicationModule(private val application: TodoListApplication) {
}