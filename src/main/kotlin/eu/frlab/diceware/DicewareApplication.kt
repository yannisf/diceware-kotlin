package eu.frlab.diceware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class DicewareApplication

fun main(args: Array<String>) {
    runApplication<DicewareApplication>(*args)
}
