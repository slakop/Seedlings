plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.seedlings"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.seedlings"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.fragment:fragment:1.8.5")
    implementation("androidx.databinding:databinding-runtime:8.10.0")
    implementation("it.xabaras.android:recyclerview-swipedecorator:1.4")
    implementation(libs.net.okhttp)
    implementation(libs.net.okhttp.logging)
    implementation(libs.net.retrofit)
    implementation(libs.net.retrofit.kotlin)
    implementation(libs.kotlin.dateTime)
    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.lifecycle.viewmodel)

    testImplementation(libs.junit.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.mockito)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.androidx.testing)

    implementation(libs.hilt.android)
}