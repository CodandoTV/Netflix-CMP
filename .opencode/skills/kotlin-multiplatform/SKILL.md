---
name: kotlin-multiplatform
description: Use when working on Kotlin Multiplatform code — module structure, source set layout, build configuration, convention plugins, Koin DI, and project architecture conventions for this KMP project.
---

# Kotlin Multiplatform Conventions

## Project Structure

This is a **KMP** project targeting **Android + iOS** (no desktop, no JS). Root project name: `StreamPlayerApp-KMP`.

### Module Naming

| Prefix | Purpose | Examples |
|---|---|---|
| `:composeApp` | Umbrella app module, assembles all features and core modules | `composeApp/` |
| `:androidApp` | Android-specific entry point (Application, MainActivity) | `androidApp/` |
| `:core-*` | Shared infrastructure (networking, storage, UI, navigation, permissions, etc.) | `core-shared`, `core-networking`, `core-shared-ui` |
| `:feature-*` | Feature modules (screen-level functionality, isolated from each other) | `feature-detail`, `feature-list-streams`, `feature-search` |

### Source Set Layout

Every KMP module applies `com.streamplayer.kmp-library` and configures:

```
src/
  commonMain/    # Shared code (business logic, models, interfaces)
  androidMain/   # Android-specific expect/actual implementations
  iosMain/       # iOS-specific expect/actual implementations
  commonTest/    # Multiplatform tests (kotlin.test)
  androidUnitTest/  # Android-only tests (JUnit4, MockK)
```

Package root: `com.codandotv.streamplayerapp`

## Build System

### Convention Plugins (`build-logic/src/main/java/`)

| Plugin ID | Purpose |
|---|---|
| `com.streamplayer.kmp-library` | Base for all KMP modules: applies `kotlin.multiplatform`, `android.kotlin.multiplatform.library`, `serialization`, `parcelize`, `dokka`, `detekt`. Configures Android namespace, compileSdk/minSdk, iOS targets. |
| `com.streamplayer.koin-annotations-setup` | Koin annotations + KSP compiler config with `KOIN_CONFIG_CHECK=true` |
| `com.streamplayer.detekt` | Static analysis via Detekt (`config/detekt/detekt.yml`) |
| `com.streamplayer.dokka` | Dokka documentation generation |
| `popcorngp-setup-plugin` | Enforces: core modules don't depend on other core modules; feature modules don't depend on other feature modules |

### Version Catalog (`gradle/libs.versions.toml`)

Key versions:
- Kotlin: `2.3.20`
- AGP: `9.1.0`
- Koin: `4.2.0` / Koin Annotations: `2.3.1` / KSP: `2.3.5`
- Compose Multiplatform: `1.10.0` / Material3: `1.9.0`
- Ktor: `3.0.1`
- Room: `2.7.0-alpha13`
- Kotzilla SDK: `2.1.3`

### Build Config (`build-logic/src/main/java/Config.kt`)

Central config with applicationId, SDK versions, JVM target, API URLs, TMDB bearer token (resolved from environment variable).

### iOS Framework

Exported as a **static framework** with `baseName = "streamplayerapp"`. Three architectures: `iosX64`, `iosArm64`, `iosSimulatorArm64`. Configured via `iosTarget()` extension in `build-logic/src/main/java/extensions/KotlinMultiPlatformExt.kt`.

## Dependency Injection (Koin)

### Two complementary styles

**Style A — Traditional DSL (`module {}`)**
Used by most modules. Example (`feature-list-streams`):
```kotlin
val ListStreamModule = module {
    viewModel { ListStreamViewModel(get(), get()) }
    factory { GetStreamsUseCaseImpl(get()) as GetStreamsUseCase }
    single { ListStreamRepositoryImpl(get()) as ListStreamRepository }
}
```

**Style B — Koin Annotations (`@Module`, `@Single`, `@ComponentScan`) + KSP**
Used by `core-networking`, `feature-news`, `feature-search`, `feature-profile`. Generates `.module` extension via KSP.

### Central Assembly

In `composeApp/src/commonMain/kotlin/.../di/AppModule.kt`:
```kotlin
fun streamPlayerApplication(platformBlock: KoinApplication.() -> Unit): KoinApplication {
    return startKoin {
        platformBlock()
        lazyModules(PermissionsModule.module)
        modules(
            module { single(QualifierDispatcherIO) { Dispatchers.IO } },
            NetworkModule().module,
            LocalStorageModule.module,
            SyncModule.module,
            ListStreamModule.module,
            SearchModule().module,
            NewsScreenModule().module,
            ProfilePickerStreamModule().module
        )
        monitoring { onConfig { useIosCrashReport = false } }
    }
}
```

### Koin Configuration Check

The `com.streamplayer.koin-annotations-setup` plugin enables `KOIN_CONFIG_CHECK=true` by default (set via KSP options). This validates all Koin definitions at compile time.

## Key Libraries

- **Networking**: Ktor with platform engines (OkHttp Android, Darwin iOS)
- **Local DB**: Room with KSP and bundled SQLite
- **Image loading**: Coil 3 (multiplatform)
- **Navigation**: JetBrains Navigation Compose (multiplatform)
- **Secrets**: BuildKonfig plugin for compile-time build config fields
- **Monitoring**: Kotzilla SDK
- **Notifications**: KMPNotifier
- **Permissions**: moko-permissions

## Code Quality

- **Detekt**: configured at `config/detekt/detekt.yml`
- **Dokka**: applied to all modules via convention plugin
- **Popcorn Guinea Pig**: enforces module dependency rules
