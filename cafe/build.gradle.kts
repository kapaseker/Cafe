@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `java-gradle-plugin`
    id("org.gradle.kotlin.kotlin-dsl")
    kotlin("jvm")
}
group = "cn.loopon.cafe"
version = "1.0.0"

//repositories {
//    mavenCentral()
//    google()
//}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.gradle)
    implementation(libs.poet)
}


gradlePlugin {
    plugins {
        create("Cafe") {
            id = "cn.loopon.cafe"
            implementationClass = "cn.loopon.cafe.CafePlugin"
        }
    }
}
//android {
//    namespace = "com.azalea.cafe"
//    compileSdk = 34
//
//    defaultConfig {
//        minSdk = 24
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}
//
//dependencies {
//
//    implementation(libs.core.ktx)
//    implementation(libs.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.espresso.core)
//}