import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("kapt") version "2.0.20"
//    kotlin("plugin.jpa") version "2.0.20"
    kotlin("plugin.spring") version "2.0.20"

    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
    group = "kr.co.vacgom"
    version = "1.0.0"
    repositories { mavenCentral() }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<BootJar> {
        enabled = false
    }
}

/**
 * Vacgom API Project Initialize
 */
project(":vacgom-api") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
    }

    tasks.withType<BootJar> {
        enabled = true
    }
}

project(":vacgom-infra") {
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-web")
    }

    tasks.withType<BootJar> {
        enabled = true
    }
}

