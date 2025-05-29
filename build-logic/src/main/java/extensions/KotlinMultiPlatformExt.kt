package extensions

import Config
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.iosTarget() {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = Config.appName
            isStatic = true

            export("io.github.mirzemehdi:kmpnotifier:1.5.1")
        }
    }
}
