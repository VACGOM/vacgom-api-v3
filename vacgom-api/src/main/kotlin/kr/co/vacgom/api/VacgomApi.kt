package kr.co.vacgom.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class VacgomApi

fun main(args: Array<String>) {
    runApplication<VacgomApi>(*args)
}
