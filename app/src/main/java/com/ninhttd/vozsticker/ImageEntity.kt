package com.ninhttd.vozsticker

class ImageEntity {
    data class Album (
        val data: Data,
    )
    data class Image (
        val id: String,
        val link: String,
    )
    data class Data (
        val id: String,
        val title: String,
        val description: String,
        val datetime: Long,
        val cover: String,
        val cover_edited: String,
        val cover_width: Long,
        val cover_height: Long,
        val account_url: String,
        val account_id: String,
        val privacy: String,
        val layout: String,
        val views: Long,
        val link: String,
        val favorite: Boolean,
        val nsfw: Boolean,
        val section: String,
        val images_count: Long,
        val in_gallery: Boolean,
        val is_ad: Boolean,
        val include_album_ads: Boolean,
        val is_album: Boolean,
        val images: List<Image>,
        val ad_config: AdConfig,
    )

    data class AdConfig (
        val safeFlags: List<String>,
        val highRiskFlags: List<String>,
        val unsafeFlags: List<String>,
        val wallUnsafeFlags: List<String>,
        val showsAds: Boolean
            )
}

