package com.elshafee.androiden.todolistapi.services

import com.elshafee.androiden.todolistapi.db.TodoApiData
import retrofit2.Response
import retrofit2.http.GET

interface TodaoApi {
    @GET("/todos")
    suspend fun getTodos():Response<List<TodoApiData>>
}