package com.learning.todolist.di.component

import com.learning.todolist.TodoListApplication
import com.learning.todolist.data.remote.NetworkService
import com.learning.todolist.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: TodoListApplication)

    fun getNetworkService(): NetworkService
}