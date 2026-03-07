@file:Suppress("UnstableApiUsage")

import extensions.dokkaPlugin
import extensions.getLibrary
import extensions.iosTarget
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupPackingOptions

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.multiplatform")
    id("kotlin-parcelize")
    id("com.streamplayer.dokka")
    id("com.streamplayer.detekt")
}
val catalog: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

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
    dokkaPlugin(libs.getLibrary("dokka"))
}

tasks.register("coverageReport") {
    dependsOn(":app:koverHtmlReportDebug")
}
