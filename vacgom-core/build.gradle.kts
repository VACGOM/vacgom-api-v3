import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
}

tasks.withType<BootJar> {
    enabled = false
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
