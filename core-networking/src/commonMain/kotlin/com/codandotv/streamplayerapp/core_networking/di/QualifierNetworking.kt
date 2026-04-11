package com.codandotv.streamplayerapp.core_networking.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object QualifierProfileHttpClient : Qualifier {
    override val value: QualifierValue
        get() = "QualifierProfileRetrofit"
}
