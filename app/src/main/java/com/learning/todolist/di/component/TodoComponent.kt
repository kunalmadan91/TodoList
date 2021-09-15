package com.learning.todolist.di.component

import com.learning.todolist.di.ActivityScope
import com.learning.todolist.di.module.TodoModule
import com.learning.todolist.ui.todo.TodoListActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [TodoModule::class]
)
interface TodoComponent {

    fun inject(activity: TodoListActivity)
}