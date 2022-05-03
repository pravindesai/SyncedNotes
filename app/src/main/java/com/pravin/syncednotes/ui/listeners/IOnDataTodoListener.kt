package com.pravin.syncednotes.ui.listeners

import com.apollographql.apollo.GetTodoQuery

interface IOnDataTodoListener {
    fun onLoading()
    fun onDataReceivedSuccessfully(todos:List<GetTodoQuery.Todo>?)
    fun onRequestFailed(t:Throwable)
}