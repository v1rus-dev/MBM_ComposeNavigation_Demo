plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    kotlin("plugin.serialization") version "1.9.22"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}