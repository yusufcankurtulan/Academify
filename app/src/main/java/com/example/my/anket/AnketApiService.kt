package com.example.my.anket

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


interface AnketApiService {
    // Yeni anket verilerini yükleme endpoint'i
    @POST("api/anket-yukle")
    suspend fun yukleAnketVerileri(@Body anketVerileri: AnketVerileri): Map<String, Any>

    // Mevcut anket verilerini çekme endpoint'i
    @GET("api/anketler")
    suspend fun getAnketVerileri(): List<AnketVerileri>
}