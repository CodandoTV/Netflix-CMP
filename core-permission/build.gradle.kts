@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        sourceSets {
            commonMain.dependencies {
                implementation(libs.bundles.compose)
                implementation(libs.moko.permissions.core)
                api(libs.moko.permissions.compose)
                implementation(libs.moko.permissions.camera)
                implementation(libs.moko.permissions.gallery)
                implementation(libs.koin.core)
            }
        }
    }
}