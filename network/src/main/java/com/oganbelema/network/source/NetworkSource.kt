package com.oganbelema.network.source

import com.oganbelema.network.Api
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/14/21.
 */
class NetworkSource @Inject constructor (private val api: Api) {

    suspend fun fetchData() = api.fetchData()

}