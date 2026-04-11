@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kotzilla)
}

kotzilla {
    versionName = Config.versionName
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
            implementation(projects.coreSharedUi)
            implementation(projects.corePermission)
            implementation(projects.coreNavigation)
            implementation(projects.coreNetworking)
            implementation(projects.coreLocalStorage)
            implementation(projects.coreBackgroundWork)

            implementation(libs.bundles.compose)

            implementation(libs.koin.core)
            api(libs.kmpnotifier)

            implementation(libs.kotzilla.sdk.compose)
        }
    }
}
