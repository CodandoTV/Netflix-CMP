plugins {
    id("com.streamplayer.kmp-library")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            // Just for test purposes
            implementation(projects.coreNavigation)
        }
    }
}