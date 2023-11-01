import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
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

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Core
    implementation(Dependencies.coreKTX)
    implementation(Dependencies.appCompat)

    // UI
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    // Tests
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.androidTestJunit)
    androidTestImplementation(Dependencies.espresso)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.coreTesting)

    implementation(Dependencies.koin)

    implementation(Dependencies.glide)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGsonConverter)

    implementation(Dependencies.skeletonlayout)

}