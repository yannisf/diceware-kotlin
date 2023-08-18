package eu.frlab.diceware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
@PropertySource(value = ["classpath:/application.properties","classpath:/core.properties"])
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
