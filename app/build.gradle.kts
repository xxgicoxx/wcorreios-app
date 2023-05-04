plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)

    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.8.10"
}

android {
    namespace = "dev.gico.wcorreios"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.gico.wcorreios"
        minSdk = 30
        targetSdk = 30
        versionCode = 3
        versionName = "3.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.activity.compose)
    implementation(libs.core.ktx)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.percentlayout)
    implementation(libs.play.services.wearable)
    implementation(libs.recyclerview)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(platform(libs.compose.bom))

    implementation(libs.androidx.wear)
    implementation(libs.androidx.wear.compose.foundation)
    implementation(libs.androidx.wear.compose.material)
    implementation(libs.androidx.wear.compose.material3)
    implementation(libs.androidx.wear.compose.navigation)
    implementation(libs.androidx.wear.input)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.com.google.android.horologist)

    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.com.squareup.retrofit2)

    implementation(libs.com.jakewharton.retrofit2.kotlinx.serialization.converter)
    implementation(libs.org.jetbrains.kotlinx.serialization)

    implementation(libs.androidx.compose.runtime.livedata)

    ksp(libs.androidx.room.compiler)

    api(libs.org.jetbrains.kotlinx.coroutines.android)
    api(libs.org.jetbrains.kotlinx.coroutines.core)

    androidTestImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))

    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.ui.tooling)
}
