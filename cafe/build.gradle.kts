@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    id("maven-publish")
}

val cafe_id = "cn.loopon.cafe"
val cafe_group = "cn.loopon"
val cafe_artifact = "cafe"
val cafe_version = "1.0.3"

group = "cn.loopon.cafe"
version = cafe_version

dependencies {
//    implementation(kotlin("stdlib"))
    compileOnly(libs.gradle)
    implementation(libs.poet)
}


gradlePlugin {
    plugins {
        create("Cafe") {
            id = cafe_id
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