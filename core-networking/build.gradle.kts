import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildkonfig.plugin)
}

buildkonfig {
    packageName = "core.networking"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "HOST", Config.BuildField.host_debug)
        buildConfigField(FieldSpec.Type.STRING, "API_BEARER_AUTH", Config.BuildField.api_bearer_debug)
        buildConfigField(FieldSpec.Type.STRING, "PROFILE", Config.BuildField.api_profile_debug)
    }

    defaultConfigs("release") {
        buildConfigField(FieldSpec.Type.STRING, "HOST", Config.BuildField.host_release)
        buildConfigField(FieldSpec.Type.STRING, "API_BEARER_AUTH", Config.BuildField.api_bearer_release)
        buildConfigField(FieldSpec.Type.STRING, "PROFILE", Config.BuildField.api_profile_release)
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)

            implementation(libs.koin.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.serialization.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logger)
            implementation(libs.ktor.client.auth)
            implementation(libs.bundles.compose)
        }

        androidMain.dependencies {
            implementation(libs.okhttp)

            implementation(libs.interceptor)

            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}