package com.oganbelema.elearning.factory

import java.util.*
import kotlin.random.Random

/**
 * Created by Belema Ogan on 1/18/21.
 */
internal object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomLong(): Long {
        return Random.nextLong()
    }

    fun currentDate(): Date {
        return Date()
    }

}