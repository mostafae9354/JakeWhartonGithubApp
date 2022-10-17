package com.moessa.jakeWhartonGithub.module.repos_list.domain.entity

data class RepoEntity(

    var id: Int? = null,
    var name: String? = null,
    var fullName: String? = null,
    var htmlUrl: String? = null,
    var description: String? = null
)