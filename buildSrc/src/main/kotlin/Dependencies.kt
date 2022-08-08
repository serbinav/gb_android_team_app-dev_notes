import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "com.example.notesvsshoppinglist"
    const val COMPILE_SDK = 32
    const val MIN_SDK = 24
    const val TARGET_SDK = 30
    const val KOTLIN_JVM_TARGET = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    const val VERSION_CODE = 19
    const val VERSION_NAME = "1.1.6"
}

object Modules {
    const val DATABASE = ":database"
}

object BuildTypes {
    const val RELEASE = "release"
}

object Versions {
    const val CORE_KTX = "1.8.0"
    const val APPCOMPAT = "1.4.2"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val LIFECYCLE_LIVEDATA_KTX = "2.5.0"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.5.0"
    const val NAVIGATION = "2.5.0"
    const val JUNIT = "4.13.2"
    const val JUNIT_ANDROID = "1.1.3"
    const val ESPRESSO = "3.4.0"
    const val ROOM = "2.4.2"
    const val KOIN = "3.2.0"
}

object Libraries {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    // Lifecycle
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA_KTX}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEW_MODEL_KTX}"

    // Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"

    // Koin
    const val KOIN = "io.insert-koin:koin-android:${Versions.KOIN}"

    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val JUNIT_ANDROID = "androidx.test.ext:junit:${Versions.JUNIT_ANDROID}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
}
