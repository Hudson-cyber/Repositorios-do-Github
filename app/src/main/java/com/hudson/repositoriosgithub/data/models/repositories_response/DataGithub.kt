package com.hudson.repositoriosgithub.data.models.repositories_response

import ItemsGithub
import com.google.gson.annotations.SerializedName

data class DataGithub(
    @SerializedName("total_count")
    val totalCount: String?,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<DataRepositoryGithub>
)
