import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
    group = "kr.co.vacgom"
    version = "0.0.1"
    repositories { mavenCentral() }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }
}

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Jar> {
    enabled = false
}
