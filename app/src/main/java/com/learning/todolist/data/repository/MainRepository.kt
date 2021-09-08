package com.learning.todolist.data.repository

import com.learning.todolist.data.remote.NetworkService
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val networkService: NetworkService
) {

    suspend fun doNetworkCall() =
        networkService.doNetworkCall();


}