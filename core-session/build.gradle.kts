plugins {
    id("com.streamplayer.kmp-library")
    id("com.streamplayer.koin-annotations-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
    }
}