@file:Suppress("UnstableApiUsage")

import extensions.iosTarget
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
    id("com.streamplayer.dokka")
    id("com.streamplayer.detekt")
}

kotlin {
    android {
        val moduleName = project.displayName
            .removePrefix("project ")
            .replace(":", ".")
            .replace("'", "")
            .replace("-", ".")

        namespace = "${Config.applicationId}$moduleName"

        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion

        androidResources.enable = true
    }

    iosTarget()
}
