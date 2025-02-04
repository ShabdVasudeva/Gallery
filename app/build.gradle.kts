
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "apw.codebase.gallery"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "apw.codebase.gallery"
        minSdk = 26
        targetSdk = 31
        versionCode = 1501
        versionName = "15.0.1"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.13.0-alpha09")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("androidx.preference:preference:1.2.1")
    implementation("com.jaredrummler:colorpicker:1.1.0")
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
}
