package com.hudson.repositoriosgithub.data.repositories

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import com.hudson.repositoriosgithub.R
import com.hudson.repositoriosgithub.domain.models.AppDataGithub

sealed class StatusApi<out L, out R> {
    data class Successful<R>(var successful:R ) : StatusApi<Nothing, R>()
    data class Failure<L>(val error: L) : StatusApi<L,Nothing>()
}