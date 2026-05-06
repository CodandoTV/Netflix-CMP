@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.lottie)
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.crashlytics)
        }
        commonMain.dependencies {
            implementation(projects.featureListStreams)
            implementation(projects.featureDetail)
            implementation(projects.featureSearch)
            implementation(projects.featureProfile)
            implementation(projects.featureNews)
            implementation(projects.coreShared)
            api(projects.coreSharedUi)
            api(projects.coreCameraGallery)
            api(projects.coreBackgroundWork)
            implementation(projects.corePermission)
            implementation(projects.coreNavigation)
            implementation(projects.coreNetworking)
            implementation(projects.coreLocalStorage)

            implementation(libs.bundles.compose)

            implementation(libs.koin.core)
            implementation(libs.koin.core.coroutines)
            api(libs.kmpnotifier)

            implementation(libs.kotzilla.sdk.compose)
        }
    }
}
