package com.example.fiveletters.data.api

import com.example.fiveletters.data.model.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApi {
    @GET("/word?")
    suspend fun getRandomWord(
        @Query("length") length: Int,
    ): Response<Word>
}