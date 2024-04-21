plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.source.incoming_calling_process"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.source.incoming_calling_process"
        minSdk = 27
        targetSdk = 34
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // https://mvnrepository.com/artifact/net.sourceforge.jtds/jtds
    implementation("net.sourceforge.jtds:jtds:1.3.1")
    implementation ("com.android.volley:volley:1.2.1")
    implementation("com.google.code.gson:gson:2.8.9")
}