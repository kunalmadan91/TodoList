package com.learning.todolist.di.component

import android.app.Application
import android.content.Context
import com.learning.todolist.TodoListApplication
import com.learning.todolist.data.remote.NetworkService
import com.learning.todolist.data.repository.MainRepository
import com.learning.todolist.di.ApplicationContext
import com.learning.todolist.di.module.ApplicationModule
import com.learning.todolist.utils.network.NetworkHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: TodoListApplication)

    fun getNetworkService(): NetworkService

    fun getNetworkHelper(): NetworkHelper

    fun getMainRepository(): MainRepository


    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context
}