package org.hackathon.ody.data.remote.location

import org.hackathon.ody.BuildConfig
import org.hackathon.ody.data.remote.location.service.KakaoLocationService
import org.hackathon.ody.domain.GeoCoordinate
import org.hackathon.ody.domain.LocationRepository
import kotlin.concurrent.thread

object KakaoLocationRepository : LocationRepository {
    override fun getGeoCoordinate(address: String): GeoCoordinate {
        var geoCoordinate: GeoCoordinate? = null
        thread {
            val result =
                KakaoRetrofitClient.getRetrofit().create(KakaoLocationService::class.java)
                    .searchLocation(
                        key = "KakaoAK ${BuildConfig.KAKAO_API_KEY}",
                        query = address,
                    ).execute().body()!!
            val latitude: String = result.documents[0].x
            val longitude: String = result.documents[0].y
            geoCoordinate = GeoCoordinate(address, latitude, longitude)
        }.join()
        return geoCoordinate!!
    }
}
