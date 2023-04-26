package dev.gico.wcorreios.data.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.gico.wcorreios.data.domain.CorrestData
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface CorrestService {

    @GET("/api/track")
    fun getTrack(@Query("code") codde: String): Call<CorrestData>

    companion object {
        private var retrofitService: CorrestService? = null

        fun getInstance(): CorrestService {
            val contentType = MediaType.get("application/json")

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://correst-serverless.vercel.app/")
                    .addConverterFactory(Json.asConverterFactory(contentType))
                    .build()
                retrofitService = retrofit.create(CorrestService::class.java)
            }

            return retrofitService!!
        }
    }
}
