package com.elshafee.androiden.breakingbadapp.services

import com.elshafee.androiden.breakingbadapp.model.BreakingBadCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BBASE_URL ="https://63c4417ba908563575346178.mockapi.io/api/v1/"
interface BreakingBadServices {

    @GET("characters")
    suspend fun getCharacters():List<BreakingBadCharacter>
}

object BreakingBadNetwork{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BBASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val serviceApi = retrofit.create(BreakingBadServices::class.java)
}