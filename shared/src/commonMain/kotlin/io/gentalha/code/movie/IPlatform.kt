package io.gentalha.code.movie

import org.koin.core.module.Module

interface IPlatform {
    val name: String
    val version: String
    val appVersion: String
    val language: String
}

expect fun getPlatform(): IPlatform

expect fun platformModule(): Module