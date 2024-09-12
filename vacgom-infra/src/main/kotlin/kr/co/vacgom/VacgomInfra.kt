package kr.co.vacgom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class VacgomInfra

fun main(args: Array<String>) {
    runApplication<VacgomInfra>(*args)
}
