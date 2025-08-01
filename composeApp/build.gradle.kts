@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
}
kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.lottie)
            implementation(compose.preview)
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.analytics)
        }
        commonMain.dependencies {
            implementation(projects.featureListStreams)
            implementation(projects.featureDetail)
            implementation(projects.featureSearch)
            implementation(projects.featureProfile)
            implementation(projects.featureNews)
            implementation(projects.coreShared)
            implementation(projects.coreSharedUi)
            implementation(projects.corePermission)
            implementation(projects.coreNavigation)
            implementation(projects.coreNetworking)
            implementation(projects.coreLocalStorage)
            implementation(projects.coreBackgroundWork)

            implementation(libs.navigation.compose)

            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)

            implementation(libs.koin.core)
            api(libs.kmpnotifier)
        }
    }
}