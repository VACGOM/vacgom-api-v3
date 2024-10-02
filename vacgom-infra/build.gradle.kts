import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.3"

    kotlin("plugin.lombok") version "2.0.20"
    id("io.freefair.lombok") version "8.10"
}

dependencies {
    implementation(project(":vacgom-common"))
    implementation(project(":vacgom-core"))

    implementation("org.postgresql:postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.withType<BootJar> {
    enabled = false
}
