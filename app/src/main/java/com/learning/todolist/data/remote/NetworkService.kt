package com.learning.todolist.data.remote

import com.learning.todolist.data.model.TodoModel
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET(Endpoint.TODOS)
    fun doNetworkCall() : List<TodoModel>

}