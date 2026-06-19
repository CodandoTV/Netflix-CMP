# StreamPlayerApp — KMP

## Project Overview

- **Purpose:** Open-source Netflix-like app built by the CodandoTV community for learning and mentoring.
- **Type:** Kotlin Multiplatform (KMP) Application
- **Primary Language:** Kotlin 2.3.20
- **Runtime/Platform:** Multiplatform — Android, iOS, Desktop (Compose Multiplatform)
- **Min SDK:** 28 (Android) | **Compile SDK:** 36 | **Target SDK:** 36

## Build System

| Item | Value |
|---|---|
| Gradle | 9.3.1 |
| Kotlin | 2.3.20 |
| AGP | 9.1.0 |
| JDK | 21 |
| Version Catalog | `gradle/libs.versions.toml` |
| Convention Plugins | `build-logic/` (precompiled script plugins) |
| Application ID | `com.codandotv.streamplayerapp` |

### Convention Plugins (build-logic)

- `com.streamplayer.kmp-library` — base KMP module setup (multiplatform, serialization, parcelize, dokka, detekt)
- `com.streamplayer.dokka` — documentation generation
- `com.streamplayer.detekt` — static analysis
- `com.streamplayer.koin-annotations-setup` — Koin annotation processing
- `popcorngp-setup-plugin` — Popcorn Guinea Pig test setup

### Key Dependencies

- Compose Multiplatform 1.10.0
- Koin 4.2.0 / Koin Annotations 2.3.1
- Ktor 3.0.1 / OkHttp 4.12.0
- Room 2.7.0-alpha13
- Coil 3.1.0
- Navigation Compose 2.9.2
- Kotlinx Datetime 0.8.0
- Firebase (BOM 33.14.0, Crashlytics, Analytics)
- Detekt 1.23.6
- Dokka 1.9.10

## Project Structure

### Main Application

| Module | Type | Description |
|---|---|---|
| `composeApp` | KMP + Compose | Main UI module, aggregates features |
| `androidApp` | Android App | Android entry point wrapper |

### Feature Modules

| Module | Description |
|---|---|
| `feature-list-streams` | Stream listing |
| `feature-detail` | Detail screen |
| `feature-search` | Search functionality |
| `feature-profile` | User profile |
| `feature-news` | News feed |

### Core Modules

| Module | Description |
|---|---|
| `core-shared` | Shared domain models and utilities |
| `core-networking` | Networking layer (Ktor + OkHttp) |
| `core-shared-ui` | Shared UI components and theming |
| `core-navigation` | Navigation infrastructure |
| `core-local-storage` | Local persistence (Room) |
| `core-camera-gallery` | Camera and gallery features |
| `core-permission` | Permission handling (MOKO Permissions) |
| `core-background-work` | Background task management (WorkManager, KMPNotifier) |
| `core-session` | Session management |

### Build Infrastructure

| Module | Description |
|---|---|
| `build-logic` | Convention plugins and shared build config |
| `Config.kt` | Centralized version and configuration constants |

### Test Infrastructure

- JUnit 4.13.2
- MockK 1.13.7
- Kotlinx Coroutines Test 1.8.1
- Koin Test
- Popcorn Guinea Pig (test fixtures)

### Documentation

- **Generator:** Dokka 1.9.10
- **Source:** API docs generated via `dokka` task
- **Project docs:** README.md, README_pt-br.md

## Version Management

Versions are managed in:

| File | Content |
|---|---|
| `gradle/libs.versions.toml` | Dependency and plugin versions |
| `build-logic/src/main/java/Config.kt` | `versionName = "3.2"`, `versionCode`, SDK versions, JDK target |
| `gradle.properties` | Gradle daemon config, AndroidX settings |

## Distribution

This is an application project and is **not** published as a library. No Maven Central, Gradle Plugin Portal, or pub.dev distribution.

## Git Workflow

| Item | Value |
|---|---|
| Default Branch | `main` |
| CI | GitHub Actions (`build.yml`, `linter.yaml`, `popcorn.yaml`) |
| Versioning | SemVer (current: 3.2) |
| Tags | Standard Git tags |
| PR Language | pt-br |
| Review | Via GitHub PRs, CODEOWNERS in `.github/CODEOWNERS` |
