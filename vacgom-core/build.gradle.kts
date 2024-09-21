import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("io.freefair.lombok") version "8.10"
    kotlin("plugin.lombok") version "2.0.20"
}

dependencies {
    implementation(project(":vacgom-common"))
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}


tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Jar> {
    enabled = true
}

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
