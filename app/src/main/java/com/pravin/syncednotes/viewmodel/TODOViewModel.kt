package com.pravin.syncednotes.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.apollographql.apollo.GetTodoQuery
import com.pravin.syncednotes.ui.listeners.IOnDataTodoListener
import com.pravin.syncednotes.viewmodel.repository.TODORepository

class TODOViewModel(application: Application) : AndroidViewModel(application), IOnDataTodoListener {
    private val todoRepository:TODORepository = TODORepository(application, this)
    val totalTodos:ObservableField<String> = ObservableField("0")
    val loadingVisibility:ObservableField<Int> = ObservableField(View.GONE)
    fun fetchTodos(userId:String){
        todoRepository.getTodosForUser(userId)
    }
    override fun onLoading() {
        loadingVisibility.set(View.VISIBLE)
    }

    override fun onDataReceivedSuccessfully(todos: List<GetTodoQuery.Todo>?) {
        loadingVisibility.set(View.GONE)
        totalTodos.set(todos?.size.toString())
    }

    override fun onRequestFailed(t: Throwable) {
        loadingVisibility.set(View.GONE)
    }

}