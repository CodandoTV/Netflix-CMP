import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
    // this allow us to access libs.anylibrary using type-safe accessors
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.koin.compiler.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.serialization)
    implementation(libs.com.google.devtools.ksp.gradle.plugin)
    implementation(libs.popcorn.guineapig)
}

gradlePlugin {
    plugins {
        create("koinConvention") {
            id = "com.streamplayer.koin-compiler-setup"
            implementationClass = "KoinCompilerSetupPlugin"
        }
    }
}