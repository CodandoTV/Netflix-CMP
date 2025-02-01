@file:Suppress("UnstableApiUsage")

package extensions

import Config
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun CommonExtension<*, *, *, *, *>.setupPackingOptions() {
    packaging {
        resources {
            with(pickFirsts) {
                add("META-INF/library_release.kotlin_module")
                add("META-INF/LICENSE.md")
                add("META-INF/LICENSE-notice.md")
            }
            with(excludes) {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}

internal fun CommonExtension<*, *, *, *, *>.setupAndroidDefaultConfig() {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = Config.testInstrumentationRunner
    }
}

internal fun CommonExtension<*, *, *, *, *>.setupCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun CommonExtension<*, *, *, *, *>.setupNameSpace(project: Project) {
    val moduleName = project.displayName
        .removePrefix("project ")
        .replace(":", ".")
        .replace("'", "")
        .replace("-", ".")

    namespace = "${Config.applicationId}$moduleName"

    println(">>>> $namespace")
}

internal val Project.libs: VersionCatalog
    get() {
        return project.extensions.getByType<VersionCatalogsExtension>()
            .named("libs")
    }