plugins {
    id("java")
    id("io.freefair.lombok") version "8.10"
    kotlin("plugin.lombok") version "2.0.20"
}

group = "kr.co.vacgom"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.test {
    useJUnitPlatform()
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

// resources 디렉토리 삭제 작업
tasks.register<Delete>("cleanResources") {
    delete(file("src/main/resources"))
}

tasks.register<Copy>("initCoreConfig") {
    dependsOn("cleanResources")

    from("CORE-CONFIG")
    include("*.yml")

    into("src/main/resources")
    doFirst {
        file("src/main/resources").mkdirs()
    }
}

tasks.processResources {
    dependsOn("initCoreConfig")
}
