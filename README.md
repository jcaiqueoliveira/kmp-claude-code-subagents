# Cloud Code Subagents for Kotlin Multiplatform

![CI Status](https://github.com/jcaiqueoliveira/kmp-claude-code-subagents/workflows/CI%20-%20Build%20%26%20Test/badge.svg)

This repository is a curated collection of Claude Code subagents designed for Kotlin Multiplatform (KMP) projects. Each subagent targets a specific layer or concern in a typical KMP application, providing best practices and automation for modern development.

## Projects

### Radio Player Brasil KMP 📻
A full-featured radio player app for Android and iOS built with Kotlin Multiplatform and Compose Multiplatform. Features include:
- 100% shared UI code using Compose Multiplatform (including iOS)
- Kotlin 2.0.20 with the latest K2 compiler
- Audio streaming with ExoPlayer (Android) and AVPlayer (iOS)
- Favorites management and search functionality
- [See full documentation →](radioplayerbr/README.md)

## Subagents

### 1. translation-updater
Automates updating and managing localizations for your project, supporting the most common languages and formats.

### 2. datalayer-architect
Assists in building and maintaining the data layer using Android-standard architecture. Includes support for Room database, dependency injection with Koin, and best practices for repository and data source patterns.

### 3. compose-architect
Guides UI development using Jetpack Compose and the MVVM pattern. Provides default patterns for handling UI state, events, and component decomposition, following modern Android and Compose Multiplatform best practices.

---

Adjust and use these subagents as needed to fit your Kotlin Multiplatform project structure and requirements.
