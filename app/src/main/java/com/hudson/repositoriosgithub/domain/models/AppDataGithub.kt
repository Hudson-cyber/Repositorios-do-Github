package com.hudson.repositoriosgithub.domain.models

data class AppDataGithub(
    val nameRepository: String,
    val author: String,
    val photoAuthor: String,
    val numberOfStars: Long
)