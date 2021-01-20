package com.oganbelema.elearning.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Belema Ogan on 1/20/21.
 */
class NetworkUtil (private val context: Context?) {

    fun isConnected(): Boolean {
        val connectivityManager = context?.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetwork = connectivityManager?.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}