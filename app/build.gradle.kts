plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
//    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.daniellms.marvelcomics"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.daniellms.marvelcomics"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
        buildConfigField("String", "PUBLIC_API", "\"222814b849ca4231c81fc3a7fed9a977\"")
        buildConfigField("String", "PRIVATE_API", "\"92be8a3a9f59adc94d006f160f06c54fad07ab62\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.compose.livedata)

    kapt(libs.androidx.room.compiler.kapt)
    implementation(libs.daggerhilt)
    kapt(libs.daggerhilt.kapt)
    implementation(libs.daggerhilt.navigation.compose)
    implementation(libs.coil.compose)

    implementation(libs.squareup.gson)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.gson.converter)
    implementation(libs.squareup.okhttp3)

    implementation(project(":core"))

    testImplementation(libs.junit)
    testImplementation(libs.io.mockk.test)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
    testImplementation(libs.org.jetbrains.kotlinx.test)
    testImplementation(libs.androidx.arch.core.test)
    testImplementation(libs.appCash.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
