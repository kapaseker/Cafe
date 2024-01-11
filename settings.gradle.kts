pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(files("./cafe/build/libs/cafe-1.0.0.jar"))
        classpath("com.android.tools.build:gradle:8.2.1")
        classpath("com.squareup:kotlinpoet:1.15.3")
        classpath("org.gradle.kotlin:gradle-kotlin-dsl-plugins:4.0.14")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    }
}

rootProject.name = "Cafe"
include(":app")
include(":cafe")

//includeBuild("cafe")
//includeBuild("cafe-plugin")


include(":night")
include(":dusk")
