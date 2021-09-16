package com.learning.todolist

import android.app.AppComponentFactory
import android.app.Application
import com.learning.todolist.data.remote.NetworkService
import com.learning.todolist.di.component.ApplicationComponent
import com.learning.todolist.di.component.DaggerApplicationComponent
import com.learning.todolist.di.module.ApplicationModule
import timber.log.Timber
import javax.inject.Inject

class TodoListApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService


    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}