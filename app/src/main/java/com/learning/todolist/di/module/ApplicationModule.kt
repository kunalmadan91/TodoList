package com.learning.todolist.di.module

import com.learning.todolist.BuildConfig
import com.learning.todolist.TodoListApplication
import com.learning.todolist.data.remote.NetworkService
import com.learning.todolist.data.remote.Networking
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: TodoListApplication) {

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "https://jsonplaceholder.typicode.com"
        )
}