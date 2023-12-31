plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.calendar"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.calendar"
        minSdk = 21
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("org.apache.commons:commons-text:1.10.0")
    runtimeOnly("androidx.lifecycle:lifecycle-extensions:2.0.0")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.0.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.0.0")
    implementation("androidx.room:room-runtime:2.0.0")
    annotationProcessor("androidx.room:room-compiler:2.0.0")
}