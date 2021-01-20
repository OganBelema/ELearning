package com.oganbelema.network.model

/**
 * Created by Belema Ogan on 1/14/21.
 */
data class Data (
    val status: String,
    val message: String,
    val subjects: List<Subject>
)