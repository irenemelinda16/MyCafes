package com.irene.mycafe.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("NatureLitePrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_NAME = "restaurant_name"
        private const val KEY_ADDRESS = "restaurant_address"
        private const val KEY_DESC = "restaurant_description"
        private const val KEY_HOURS = "restaurant_hours"
        private const val KEY_DARK_MODE = "dark_mode"
    }

    fun saveProfile(profile: RestaurantProfile) {
        prefs.edit().apply {
            putString(KEY_NAME, profile.name)
            putString(KEY_ADDRESS, profile.address)
            putString(KEY_DESC, profile.description)
            putString(KEY_HOURS, profile.openingHours)
            apply()
        }
    }

    fun getProfile(): RestaurantProfile {
        return RestaurantProfile(
            name = prefs.getString(KEY_NAME, "NatureLite") ?: "NatureLite",
            address = prefs.getString(KEY_ADDRESS, "Jl. Sehat No. 123, Jakarta") ?: "Jl. Sehat No. 123, Jakarta",
            description = prefs.getString(KEY_DESC, "Makanan sehat untuk hidup yang lebih baik.") ?: "Makanan sehat untuk hidup yang lebih baik.",
            openingHours = prefs.getString(KEY_HOURS, "08:00 - 20:00") ?: "08:00 - 20:00"
        )
    }

    fun isDarkMode(): Boolean {
        return prefs.getBoolean(KEY_DARK_MODE, false)
    }

    fun setDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }
}
