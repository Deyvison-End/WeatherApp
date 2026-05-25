plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false

    kotlin("android") version "2.2.20" apply false
    kotlin("plugin.serialization") version "2.2.20" apply false
}