package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

@Suppress("FunctionNaming")
fun DependencyHandler.`dokkaPlugin`(dependencyNotation: Any): Dependency? =
    add("dokkaPlugin", dependencyNotation)
