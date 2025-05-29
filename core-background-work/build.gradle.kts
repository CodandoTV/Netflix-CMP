@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    id("com.google.devtools.ksp")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.work.runtime)
        }

        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)
            implementation(projects.coreShared)
            implementation(projects.featureListStreams)
            api(libs.kmpnotifier)
        }
    }
}