package com.pravin.syncednotes.viewmodel.repository

import android.content.Context
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.GetTodoQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.cache.http.ApolloHttpCache
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore
import com.apollographql.apollo.exception.ApolloException
import com.pravin.syncednotes.ui.listeners.IOnDataTodoListener
import com.pravin.syncednotes.util.Constants
import okhttp3.OkHttpClient
import java.io.File

class TODORepository(private val context:Context,private val onDataTodoListener: IOnDataTodoListener) {
    private val apolloCacheFile = File(context.filesDir, Constants.CACHE_FILE_NAME)
    private val cacheStore = DiskLruHttpCacheStore(apolloCacheFile, Constants.CACHE_FILE_SIZE)
    private val okHttp = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder().method(
                original.method,
                original.body
            )
            builder.addHeader(Constants.HEADER_KEY, Constants.HEADER_VAL)
            chain.proceed(builder.build())
        }
        .build()

    private val apolloClient = ApolloClient.builder().httpCache(ApolloHttpCache(cacheStore))
        .serverUrl(Constants.BASE_URL)
        .okHttpClient(okHttp).build()

    fun getTodosForUser(userId:String){
        val apolloCall = apolloClient.query(GetTodoQuery(userId) ).toBuilder()
            .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
            .build()
            onDataTodoListener.onLoading()
        apolloCall.enqueue(object : ApolloCall.Callback<GetTodoQuery.Data>() {
            val TAG = "**apolloCallBack"
            override fun onResponse(response: Response<GetTodoQuery.Data>) {
                val todos = response.data?.todos()
                onDataTodoListener.onDataReceivedSuccessfully(todos)
                Log.e(TAG, "onResponse: ${Thread.currentThread()} ")
            }

            override fun onFailure(e: ApolloException) {
                Log.e(TAG, "onFailure: ${e.localizedMessage}" )
                onDataTodoListener.onRequestFailed(e)
            }

        })
    }

}