package com.ninhttd.vozsticker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageService {
    //https://api.imgur.com/3/album/PyAepyl?client_id=88c1a76b6435585
    @GET("{id}")
    open fun getAll(
        @Path("id") customerId: String,
        @Query("client_id") clientId: String?,
    ): Call<List<Image>>?
}