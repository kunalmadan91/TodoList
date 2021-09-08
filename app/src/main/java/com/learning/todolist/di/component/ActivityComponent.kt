package com.learning.todolist.di.component

import com.learning.todolist.di.ActivityScope
import com.learning.todolist.di.module.TodoModule
import com.learning.todolist.ui.todo.TodoListActivity
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [TodoModule::class]
)
interface ActivityComponent {

    fun inject(activity: TodoListActivity)
}