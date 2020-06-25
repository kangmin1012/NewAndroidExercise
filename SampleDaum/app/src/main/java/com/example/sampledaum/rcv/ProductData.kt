package com.example.sampledaum.rcv

import java.io.Serializable

data class ProductData(
    val productImg : String,
    val productTitle : String,
    val productContent : String
) : Serializable