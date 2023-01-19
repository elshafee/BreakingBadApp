package com.elshafee.androiden.todolistapi.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofiinstanceTodoApi {

    val api:TodaoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodaoApi::class.java)
    }
}