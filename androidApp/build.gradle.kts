import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupPackingOptions

plugins {
    id("com.android.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kotzilla)
    alias(libs.plugins.kotlin.android)
}

kotzilla {
    versionName = Config.versionName
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

    dependencies {
        implementation(libs.core.ktx)
        implementation(projects.composeApp)
        implementation(libs.koin.android)
        implementation(libs.lottie)
        implementation(project.dependencies.platform(libs.firebase.bom))
        implementation(libs.firebase.analytics)
        implementation(libs.firebase.crashlytics)
        implementation(libs.koin.core)
    }
}