package com.ninhttd.vozsticker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageService {
    @GET("{id}")
    open fun getAlbum(
        @Path("id") customerId: String,
        @Query("client_id") clientId: String?,
    ): Call<ImageEntity.Album>?
}