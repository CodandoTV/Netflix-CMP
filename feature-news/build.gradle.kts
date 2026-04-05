@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(projects.coreNetworking)
            implementation(projects.coreNavigation)
            implementation(projects.coreShared)
            implementation(projects.coreCameraGallery)
            implementation(projects.coreSharedUi)
            implementation(projects.corePermission)
            implementation(libs.bundles.compose)

            implementation(libs.kotlinx.coroutines.core)
        }
        androidUnitTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}
