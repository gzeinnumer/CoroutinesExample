package com.gzeinnumer.coroutinesexample.model

import com.google.gson.annotations.SerializedName

class Source {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null
}