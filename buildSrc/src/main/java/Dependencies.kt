object Dependencies {
    // Core
    const val coreKTX =  "androidx.core:core-ktx:${Versions.coreKTX}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    // UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Tests
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx"
}