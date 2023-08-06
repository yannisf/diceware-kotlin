package eu.frlab.diceware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class DicewareApplication(
    @Autowired appProperties: AppProperties
) {

    init {
        println(appProperties.prettyPrint())
    }

}

fun main(args: Array<String>) {
    runApplication<DicewareApplication>(*args)
}
