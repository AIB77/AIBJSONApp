package com.example.aibjsonapp

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class TheConvertDetails {

    @SerializedName("date")
    var date: String? = null

    @SerializedName("eur")
    var eur: currencyarray? = null

    class currencyarray {

        @SerializedName("inr")
        var inr: String? = null

        @SerializedName("usd")
        var usd: String? = null

        @SerializedName("aud")
        var aud: String? = null

        @SerializedName("sar")
        var sar: String? = null

        @SerializedName("cny")
        var cny: String? = null

        @SerializedName("jpy")
        var jpy: String? = null

    }
}