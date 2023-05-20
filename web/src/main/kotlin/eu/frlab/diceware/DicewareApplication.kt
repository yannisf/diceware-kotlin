package eu.frlab.diceware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DicewareApplication

fun main(args: Array<String>) {
    runApplication<DicewareApplication>(*args)
}
