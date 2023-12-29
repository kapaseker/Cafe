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
    }
    dependencies {
//        classpath("com.squareup:kotlinpoet:1.15.3")
        classpath("com.squareup:kotlinpoet:1.15.3")
    }
}

rootProject.name = "Cafe"
include(":app")
 