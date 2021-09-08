package com.learning.todolist.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.learning.todolist.data.repository.MainRepository
import com.learning.todolist.utils.common.Resource
import kotlinx.coroutines.Dispatchers

class TodoListViewModel(private val mainRepository: MainRepository) : ViewModel(){

    fun makeNetworkCall() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.doNetworkCall()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}