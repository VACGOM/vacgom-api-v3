import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(project(":vacgom-core"))
    implementation(project(":vacgom-common"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_21.toString()
    targetCompatibility = JavaVersion.VERSION_21.toString()
}

tasks.register<Delete>("cleanResources") {
    delete(file("src/main/resources"))
}

tasks.register<Copy>("initApiConfig") {
    dependsOn("cleanResources")

    from("API-CONFIG")
    include("*.yml")

    into("src/main/resources")
    doFirst {
        file("src/main/resources").mkdirs()
    }
}

tasks.processResources {
    dependsOn("initApiConfig")
}
