package org.hackathon.ody.data.remote.location

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object KakaoRetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://dapi.kakao.com/"

    fun getRetrofit(): Retrofit {
        val moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        return retrofit ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
