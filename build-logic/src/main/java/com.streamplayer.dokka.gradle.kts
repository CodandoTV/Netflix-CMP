@file:Suppress("UnstableApiUsage")

import org.gradle.accessors.dm.LibrariesForLibs


val libs = the<LibrariesForLibs>()

allprojects {
    apply(plugin = libs.plugins.dokka.get().pluginId)
}
