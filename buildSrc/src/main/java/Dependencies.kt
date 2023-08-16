object Dependencies {
    // Core
    const val coreKTX = "androidx.core:core-ktx:${Versions.coreKTX}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    // UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Tests
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // Jetpack Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.jetpackNavigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.jetpackNavigation}"

    // Koin
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
}