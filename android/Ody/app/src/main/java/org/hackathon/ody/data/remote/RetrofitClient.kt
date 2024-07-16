package org.hackathon.ody.data.remote

import android.accounts.NetworkErrorException
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://localhost:80"
    /*
    val token = CoroutineScope(Dispatchers.IO).async {
        getCurrentNotificationToken()
    }
     */
    val token = "fRLyMpdlSQSqlYUugU9pD6:APA91bG0nRfaMP8bAiBXIcOhCVhhoI4GXLJ1lJpJYV9o_QIpItFGzFvHqfOiG7r7rWiTyRnuma-Qp3nHT6hW4zywLvCdH5Hmdz4akbFR08-8YLX5F-cy8EZzz60khG4AU71Q6sXDquX2"
    private val interceptor = Interceptor {
        /*
        val token = runBlocking {
            getCurrentNotificationToken()
        }
         */
        val newRequest = it.request().newBuilder().addHeader("Authorization", token).build()
        it.proceed(newRequest)
    }

    fun getRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(interceptor)
        val okHttpClient = builder.build()
        return retrofit ?: Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private suspend fun getCurrentNotificationToken() =
        suspendCoroutine<String> { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    Result.success(task.result)
                } else {
                    Result.failure(NetworkErrorException("notitokenerror"))
                }
                continuation.resumeWith(result)
            }
        }
}
