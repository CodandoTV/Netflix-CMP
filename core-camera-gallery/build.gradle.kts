@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        sourceSets {
            androidMain.dependencies {
                implementation(libs.activity.compose)
                implementation(libs.bundles.camera)
            }
            commonMain.dependencies {
                implementation(projects.coreShared)
                implementation(projects.coreSharedUi)
                implementation(libs.bundles.compose)
            }
        }
    }
}