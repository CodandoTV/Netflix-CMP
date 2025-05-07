rootProject.name = "StreamPlayerApp-KMP"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.1"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven(url = uri("https://oss.sonatype.org/content/repositories/snapshots/"))

    }
}


include(":composeApp")
include(":feature-list-streams")
include(":core-shared")
include(":core-networking")
include(":core-shared-ui")
include(":core-navigation")
include(":feature-profile")
include(":core-local-storage")
include(":feature-detail")
include(":feature-search")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":feature-news")
include(":core-camera-gallery")
include(":core-permission")

kover {
    enableCoverage()

    reports {
        includedProjects.addAll(
            ":composeApp",
            ":feature-detail",
            ":core-networking"
        )
        excludedClasses.add("*.BuildConfig")
        excludedClasses.add("*.ComposableSingletons")
        excludedClasses.add("*ScreenKt*")
        excludedClasses.add("*.di.*")
        excludesAnnotatedBy.add("Generated")

        verify {
            rule {
                name = "Minimum Coverage"
                bound {
                    minValue = 80
                }
            }
        }
    }
}
include(":core-analytics")
