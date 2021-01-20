package com.oganbelema.network

import com.oganbelema.network.model.NetworkResponse
import retrofit2.http.GET

/**
 * Created by Belema Ogan on 1/14/21.
 */
public interface Api {

    @GET("/3p/api/content/grade")
    suspend fun fetchData(): NetworkResponse
}