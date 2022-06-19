import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.marvel.characterskmm.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val androidMaterialVersion = "1.4.0"
    val appCompatVersion = "1.3.1"
    val constraintLayoutVersion = "2.1.0"
    val coroutinesVersion = "1.6.0"
    val picassoVersion = "2.71828"
    val lifecycleVersion = "2.4.0-alpha03"
    val droidsonroidsVersion = "1.2.23"
    val recyclerViewVersion = "1.2.1"
    val coreKtxVersion = "1.6.0"

    implementation(project(":shared"))

    // General
    implementation("com.google.android.material:material:$androidMaterialVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation("androidx.core:core-ktx:$coreKtxVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Picasso
    implementation("com.squareup.picasso:picasso:$picassoVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

    // Droidsonroids
    implementation("pl.droidsonroids.gif:android-gif-drawable:$droidsonroidsVersion")

    // Recycler view
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")
}