package eu.frlab.diceware

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.security.SecureRandom

class DieRollTest {

    @Test
    fun rollTest(): Unit {
        val secureRandom = SecureRandom()
        val roll = (0 until 5).joinToString (transform = { _ ->  (secureRandom.nextInt(6) + 1).toString()}, separator = "")

    }

}
