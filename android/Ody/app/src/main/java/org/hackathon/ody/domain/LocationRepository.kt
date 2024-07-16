package org.hackathon.ody.domain

interface LocationRepository {
    fun getGeoCoordinate(address: String): GeoCoordinate
}
