plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Config.JAVA_VERSION
        targetCompatibility = Config.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = Config.KOTLIN_JVM_TARGET
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.DATABASE))

    implementation(Libraries.CORE_KTX)
    implementation(Libraries.APPCOMPAT)
    implementation(Libraries.MATERIAL)
    implementation(Libraries.CONSTRAINT_LAYOUT)

    // Lifecycle
    implementation(Libraries.LIFECYCLE_LIVEDATA_KTX)
    implementation(Libraries.LIFECYCLE_VIEW_MODEL_KTX)

    // Navigation
    implementation(Libraries.NAVIGATION_FRAGMENT_KTX)
    implementation(Libraries.NAVIGATION_UI_KTX)

    // Koin
    implementation(Libraries.KOIN)

    // Test libraries
    testImplementation(Libraries.JUNIT)
    androidTestImplementation(Libraries.JUNIT_ANDROID)
    androidTestImplementation(Libraries.ESPRESSO)
    debugImplementation(Libraries.FRAGMENT_TEST)
    debugImplementation(Libraries.ESPRESSO_CONTRIB)

    androidTestImplementation(Libraries.RUNNER)
    androidTestImplementation(Libraries.RULES)
    debugImplementation(Libraries.ESPRESSO_CONTRIB)

}