package org.hackathon.ody.presentation.address

import android.content.Context
import android.location.Geocoder
import android.os.Build
import java.util.Locale

class AddressInformation(
    val address: String,
    val latitude: String,
    val longitude: String,
) {
    companion object {
        fun of(address: String, context: Context): AddressInformation {
            return AddressInformation(
                address,
                address.convertToLatitude(context),
                address.convertToLongitude(context),
            )
        }

        private fun String.convertToLatitude(context: Context): String {
            val geoCoder = Geocoder(context, Locale.KOREA)

            var latitude: Double = 0.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geoCoder.getFromLocationName(this, 1) {
                    latitude = it[0].latitude
                }
            } else {
                geoCoder.getFromLocationName(this, 1)?.let {
                    latitude = it[0].latitude
                }
            }
            return latitude.toString()
        }

        private fun String.convertToLongitude(context: Context): String {
            val geoCoder = Geocoder(context, Locale.KOREA)

            var longitude: Double = 0.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geoCoder.getFromLocationName(this, 1) {
                    longitude = it[0].longitude
                }
            } else {
                geoCoder.getFromLocationName(this, 1)?.let {
                    longitude = it[0].longitude
                }
            }
            return longitude.toString()
        }
    }
}
