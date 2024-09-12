plugins {
    id("io.freefair.lombok") version "8.10"
    kotlin("plugin.lombok") version "2.0.20"
}

dependencies {
//    runtimeOnly("org.postgresql:postgresql")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
}
