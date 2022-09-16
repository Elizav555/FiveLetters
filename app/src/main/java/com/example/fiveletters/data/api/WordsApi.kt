package com.example.fiveletters.data.api

import com.example.fiveletters.data.model.WordApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsApi {
    @GET("/v4/words.json/randomWord?")
    suspend fun getRandomWord(
        @Query("minLength") minLength: Int,
        @Query("maxLength") maxLength: Int,
        @Query("includePartOfSpeech") includePartOfSpeech: String = "noun"
    ): Response<WordApi>
}