@file:Suppress("UnstableApiUsage")

import extensions.iosTarget
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupPackingOptions
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-parcelize")
    id("com.streamplayer.dokka")
    id("com.streamplayer.detekt")
}

kotlin {
    jvmToolchain(21)

    androidTarget {
        compilerOptions {
            jvmTarget.set(Config.jvmTarget)
        }
    }

    iosTarget()
}

android {
    namespace = Config.applicationId

    setupCompileOptions()
    setupPackingOptions()
    setupAndroidDefaultConfig()

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = true
    }
}

dependencies {
    add("dokkaPlugin", libs.dokka)
}

tasks.register("coverageReport") {
    dependsOn(":app:koverHtmlReportDebug")
}
