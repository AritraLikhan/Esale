plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.e_sale"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.e_sale"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

//dependencies {
//
//
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.firebase:firebase-storage:20.3.0")
//    implementation("com.google.firebase:firebase-auth:22.3.1")
//    implementation("com.google.firebase:firebase-database:20.3.1")
//    implementation("androidx.annotation:annotation:1.7.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
//    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
//    debugImplementation("androidx.fragment:fragment-testing:1.7.1")
//    testImplementation("junit:junit:4.13.2")
//    testImplementation("org.mockito:mockito-core:3.12.4")
//    testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test:core:1.4.0")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.test:runner:1.4.0")
//    androidTestImplementation("androidx.test:rules:1.4.0")
//    implementation("androidx.navigation:navigation-runtime:2.7.4")
//    implementation("androidx.navigation:navigation-fragment:2.1.0")
//    implementation("androidx.navigation:navigation-ui:2.7.4")
//    implementation("androidx.cardview:cardview:1.0.0")
//    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
//    implementation ("com.google.firebase:firebase-analytics")
//    implementation ("com.github.bumptech.glide:glide:4.16.0")
//    implementation("de.hdodenhof:circleimageview:3.0.1")
//    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
//    implementation("com.android.volley:volley:1.2.1")
//    implementation("com.squareup.okhttp3:okhttp:4.10.0")
//    implementation("com.squareup.picasso:picasso:2.71828")
//
//
//}


dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    debugImplementation("androidx.fragment:fragment-testing:1.7.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")
    implementation("androidx.navigation:navigation-runtime:2.7.4")
    implementation("androidx.navigation:navigation-fragment:2.1.0")
    implementation("androidx.navigation:navigation-ui:2.7.4")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("de.hdodenhof:circleimageview:3.0.1")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")


}
