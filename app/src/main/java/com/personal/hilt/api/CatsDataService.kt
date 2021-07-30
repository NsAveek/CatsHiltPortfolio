package com.personal.hilt.api

import com.personal.hilt.data.CatsDataRepository.Companion.NETWORK_PAGE_SIZE
import com.personal.hilt.model.CatsDataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsDataService {
    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/images/"

        fun create(): CatsDataService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CatsDataService::class.java)
        }
    }

    @GET("search")
    //@Query("has_breeds") hasBreeds : Boolean = true,
    suspend fun getCats(@Query("limit") pageSize: Int = NETWORK_PAGE_SIZE,
                        @Query("page") pageNumber: Int,
                        @Query("include_categories") includeCategories : Boolean = true,
                        @Query("mime_types") mimeType: String = "png",) : CatsDataResponse


}