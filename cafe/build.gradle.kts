@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "1.3.0"
}

val cafe_id = "io.github.kapaseker.cafe"
val cafe_group = "io.github.kapaseker"
val cafe_artifact = "cafe"
val cafe_version = "1.0.0"

group = cafe_id
version = cafe_version

dependencies {
//    implementation(kotlin("stdlib"))
    compileOnly(libs.gradle)
    implementation(libs.poet)
}


gradlePlugin {
    website = "https://github.com/kapaseker/Cafe"
    vcsUrl  = "https://github.com/kapaseker/Cafe.git"
    plugins {
        create("Cafe") {
            id = cafe_id
            displayName = "Adroid compose theme plugin"
            description = "A plugin that helps you change theme in android app developed by compose"
            tags = listOf("theme","compose","android")
            implementationClass = "cn.loopon.cafe.CafePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = cafe_group
            artifactId = cafe_artifact
            version = cafe_version

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