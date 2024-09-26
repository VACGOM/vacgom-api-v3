plugins {
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(project(":vacgom-core"))
    implementation(project(":vacgom-common"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.register<Delete>("cleanResources") {
    delete(file("src/main/resources"))
}

tasks.register<Copy>("initApiConfig") {
    dependsOn("cleanResources")

    from("API-CONFIG")
    include("*")

    into("src/main/resources")
    doFirst {
        file("src/main/resources").mkdirs()
    }
}

tasks.processResources {
    dependsOn("initApiConfig")
}
