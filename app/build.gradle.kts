import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import groovy.util.Node
import groovy.xml.XmlParser
import org.gradle.configurationcache.extensions.capitalized
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
        println(target.android.namespace)
        println(target.android.sourceSets["main"].java.srcDirs.first().absolutePath)

        val nameSpace = target.android.namespace.orEmpty()

        val skinFile = target.android.sourceSets["main"].java.srcDirs.first()

        val cafe = FileSpec.builder(ClassName("com.azalea.cafe","Cafe"))
        val cafeObj = TypeSpec.objectBuilder("Cafe")

        val resMap = mutableMapOf<String,TypeSpec.Builder>()
        val mipmapMap = mutableSetOf<String>()
        val drawableMap = mutableSetOf<String>()

        for (resFile in target.android.sourceSets["main"].res.srcDirs) {
            val files = resFile.listFiles().orEmpty()
            for (folder in files) {
//                println(file.name)
                when {
                    folder.name.startsWith("values") -> {
                        // values folder
                        val valueFiles = folder.listFiles().orEmpty()
                        for (valueFile in valueFiles) {
                            println(valueFile.name)
                            val values = XmlParser().parse(valueFile)
//                        println(values.children())
                            for (child in values.children()) {
                                (child as Node).let { node ->
                                    val resName = node.name().toString()
                                    val resSpec = resMap.getOrPut(resName) {
                                        TypeSpec.objectBuilder(resName)
                                    }

                                    val annotationName = resName.capitalized()
                                    val resValue = node.attribute("name").toString().replace('.', '_')
                                    resSpec.addProperty(
                                        PropertySpec.builder(resValue, Int::class).addModifiers(KModifier.PUBLIC)
                                            .addAnnotation(AnnotationSpec.builder(ClassName("androidx.annotation", "${annotationName}Res")).build())
                                            .initializer("${nameSpace}.R." + resName + "." + resValue).build()
                                    )
                                }
//                                println((child as Node).name())
//                                println((child).attribute("name"))
//                                println((child).value())
                            }
                        }
                    }

                    folder.name.startsWith("mipmap") -> {
                        val resName = "mipmap"
                        val resSpec = resMap.getOrPut(resName) {
                            TypeSpec.objectBuilder(resName)
                        }
                        for (valueFile in folder.listFiles().orEmpty()) {
                            val annotationName = "Drawable"
                            val resValue = valueFile.name.substring(0, valueFile.name.indexOf('.'))
                            if (mipmapMap.contains(resValue)) {
                                continue
                            }
                            mipmapMap.add(resValue)
                            resSpec.addProperty(
                                PropertySpec.builder(resValue, Int::class).addModifiers(KModifier.PUBLIC)
                                    .addAnnotation(AnnotationSpec.builder(ClassName("androidx.annotation", "${annotationName}Res")).build()).initializer("${nameSpace}.R." + resName + "." + resValue)
                                    .build()
                            )
                        }
                    }

                    folder.name.startsWith("drawable") -> {
                        val resName = "drawable"
                        val resSpec = resMap.getOrPut(resName) {
                            TypeSpec.objectBuilder(resName)
                        }
                        for (valueFile in folder.listFiles().orEmpty()) {
                            val annotationName = "Drawable"
                            val resValue = valueFile.name.substring(0, valueFile.name.indexOf('.'))
                            if (drawableMap.contains(resValue)) {
                                continue
                            }
                            drawableMap.add(resValue)
                            resSpec.addProperty(
                                PropertySpec.builder(resValue, Int::class).addModifiers(KModifier.PUBLIC)
                                    .addAnnotation(AnnotationSpec.builder(ClassName("androidx.annotation", "${annotationName}Res")).build()).initializer("${nameSpace}.R." + resName + "." + resValue)
                                    .build()
                            )
                        }
                    }
                }
            }
        }
        for (resType in resMap.values) {
            cafeObj.addType(resType.build())
        }
        cafe.addType(cafeObj.build())
        println(cafe.toString())
        cafe.build().writeTo(skinFile)
        println("Cafe!!!")
    }
}

apply<CafePlugin>()