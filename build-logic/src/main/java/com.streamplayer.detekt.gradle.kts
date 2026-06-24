import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("io.gitlab.arturbosch.detekt")
}

val libs = the<LibrariesForLibs>()

detekt {
    config.setFrom(file(project.rootDir.path.plus("/config/detekt/detekt.yml")))
    buildUponDefaultConfig = true

    source.from(
        files(
            "src/commonMain/kotlin",
            "src/androidMain/kotlin",
            "src/iosMain/kotlin"
        )
    )

    dependencies {
        detektPlugins(libs.detekt.formatting)
        detektPlugins(libs.popcornguineapig.detekt.rule)
    }
}
