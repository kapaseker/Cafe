@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "1.3.0"
}

val cafeId:String = "io.github.kapaseker.cafe"
val cafeGroup = "io.github.kapaseker"
val cafeArtifact = "cafe"
val cafeVersion = "1.0.2"

group = cafeId
version = cafeVersion

dependencies {
    compileOnly(libs.gradle)
    implementation(libs.poet)
}


gradlePlugin {
    website = "https://github.com/kapaseker/Cafe"
    vcsUrl  = "https://github.com/kapaseker/Cafe.git"
    plugins {
        create("Cafe") {
            id = cafeId
            displayName = "Adroid compose theme plugin"
            description = "A plugin that helps you change theme in android app developed by compose"
            tags = listOf("theme","compose","android")
            implementationClass = "io.github.kapaseker.cafe.CafePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = cafeGroup
            artifactId = cafeArtifact
            version = cafeVersion

            from(components["java"])
        }
    }
    repositories {
        maven {
            credentials {
                username = "deployment"
                password = "deployment123"
            }
            isAllowInsecureProtocol = true
            url = uri("http://192.168.6.18:8081/repository/releases/")
        }
    }
}