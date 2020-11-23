package com.gzeinnumer.coroutinesexample.model

import com.google.gson.annotations.SerializedName
import com.gzeinnumer.coroutinesexample.model.ArticlesItem

class ResponseNews {
    @SerializedName("totalResults")
    var totalResults = 0

    @SerializedName("articles")
    var articles: List<ArticlesItem>? = null

    @SerializedName("status")
    var status: String? = null
}