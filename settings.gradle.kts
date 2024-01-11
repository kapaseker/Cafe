pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.6.18:8081/repository/public/")
        }
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
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.6.18:8081/repository/public/")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.1")
        classpath("cn.loopon:cafe:1.0.1")
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
