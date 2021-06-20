package com.kotl.jetpack.login.dto

/**
 * Created by chenguang
 */
data class LoginResponse(val id: String, val firstName: String, val lastName: String,
                         val streetName: String, val buildingNumber: String,
                         val postalCode: String, val state: String,
                         val country: String, val email: String)
