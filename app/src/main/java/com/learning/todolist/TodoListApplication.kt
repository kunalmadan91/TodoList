package com.learning.todolist

import android.app.AppComponentFactory
import android.app.Application
import com.learning.todolist.di.component.ApplicationComponent
import com.learning.todolist.di.component.DaggerApplicationComponent
import com.learning.todolist.di.module.ApplicationModule

class TodoListApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent


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