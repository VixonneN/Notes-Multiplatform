package com.khomichenko.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform