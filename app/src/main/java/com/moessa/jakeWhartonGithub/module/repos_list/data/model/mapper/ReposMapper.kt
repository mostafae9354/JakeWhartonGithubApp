package com.moessa.jakeWhartonGithub.module.repos_list.data.model.mapper

import com.moessa.jakeWhartonGithub.module.repos_list.data.model.RepoModel
import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity

fun RepoModel.toEntity() = RepoEntity(
    id = id,
    name = name,
    fullName = fullName,
    htmlUrl = htmlUrl,
    description = description
)