plugins {
    id("com.streamplayer.kmp-library")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            // introducing failure
            implementation(projects.featureNews)
        }
    }
}
