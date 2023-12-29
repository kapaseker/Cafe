import groovy.util.Node
import groovy.xml.XmlParser
import org.jetbrains.kotlin.incremental.createDirectory

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.azalea.cafe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.azalea.cafe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

//fun Project.android(): BaseExtension {
//   val android = project.extensions.findByType(BaseExtension::class.java)
//   if (android != null) {
//       return android
//   } else {
//       throw GradleException("Project $name is not an Android project")
//   }
//}
//
//fun BaseExtension.variants(): DomainObjectSet<out BaseVariant> {
//   return when (this) {
//       is BaseAppModuleExtension -> {
//           applicationVariants
//       }
//
//       is LibraryExtension -> {
//           libraryVariants
//       }
//
//       else -> throw GradleException("Unsupported BaseExtension type!")
//   }
//}

class CafePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("Cafe...")
        println(target.android.sourceSets["main"].java.srcDirs.first().absolutePath)
        val skinFile = File(target.android.sourceSets["main"].java.srcDirs.first(),"com/azalea/cafe/Cafe.kt")
        skinFile.createDirectory()
        skinFile.createNewFile()
        for (resFile in target.android.sourceSets["main"].res.srcDirs) {
            val files = resFile.listFiles()
            for (file in files) {
//                println(file.name)
                if (file.name.startsWith("values")) {
                    val valueFiles = file.listFiles()
                    for (valueFile in valueFiles) {
                        println(valueFile.name)
                        val values = XmlParser().parse(valueFile)
//                        println(values.children())
                        for (child in values.children()) {
                            println((child as Node).name())
                            println((child).attribute("name"))
                            println((child).value())
                        }
                    }
                }
            }
        }
        println("Cafe!!!")
    }
}

apply<CafePlugin>()