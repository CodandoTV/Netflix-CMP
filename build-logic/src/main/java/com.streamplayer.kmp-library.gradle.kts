@file:Suppress("UnstableApiUsage")

import extensions.dokkaPlugin
import extensions.getLibrary
import extensions.iosTarget
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupNameSpace
import extensions.setupPackingOptions
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
    id("com.streamplayer.dokka")
    id("com.streamplayer.detekt")
}

kotlin {
    jvmToolchain(21)

    androidTarget {
        compilerOptions {
            jvmTarget.set(Config.jvmTarget)
            freeCompilerArgs.add("-Xstring-concat=inline")
        }
    }

    iosTarget()
}

android {
    setupNameSpace(project)

    setupCompileOptions()

    setupPackingOptions()

    setupAndroidDefaultConfig()
    defaultConfig.targetSdk = Config.targetSdkVersion

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    dokkaPlugin(libs.getLibrary("dokka"))
}
