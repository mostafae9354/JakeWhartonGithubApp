package com.moessa.jakeWhartonGithub.module.repos_list.presentation.uimodel.mapper

import com.moessa.jakeWhartonGithub.module.repos_list.domain.entity.RepoEntity
import com.moessa.jakeWhartonGithub.module.repos_list.presentation.uimodel.RepoUiModel

fun RepoEntity.toUiModel() = RepoUiModel(
    id = id,
    name = name,
    fullName = fullName,
    htmlUrl = htmlUrl,
    description = description
)