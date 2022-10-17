package com.moessa.jakeWhartonGithub.module.repos_list.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("html_url") var htmlUrl: String? = null,
    @SerializedName("description") var description: String? = null
)