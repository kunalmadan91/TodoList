package com.learning.todolist.di.module

import android.app.Application
import android.content.Context
import com.learning.todolist.BuildConfig
import com.learning.todolist.TodoListApplication
import com.learning.todolist.data.remote.NetworkService
import com.learning.todolist.data.remote.Networking
import com.learning.todolist.di.ApplicationContext
import com.learning.todolist.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: TodoListApplication) {



    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "https://jsonplaceholder.typicode.com"
        )


    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}