package eu.frlab.diceware

import org.junit.jupiter.api.Test
import java.security.SecureRandom
import org.assertj.core.api.Assertions.*
import org.mockito.kotlin.mock


class DicewareServiceTest {

    //TODO: This is more of a stub test
    @Test
    fun rollTest() {
        val secureRandom = SecureRandom()
        val roll = (0 until 5).joinToString (transform = { _ ->  (secureRandom.nextInt(6) + 1).toString()}, separator = "")
    }

    @Test
    fun concatWordsTest() {
        val repositoryMock: DicewareRepository = mock()
        val service = DicewareServiceImpl(repositoryMock, AppProperties())
        val words = listOf("to", "kill", "a", "mockingbird")

        val space = service.concatWords(ConcatMode.space, words)
        val snake = service.concatWords(ConcatMode.snake, words)
        val simple = service.concatWords(ConcatMode.simple, words)
        val camel = service.concatWords(ConcatMode.camel, words)
        val kebab = service.concatWords(ConcatMode.kebab, words)
        val pascal = service.concatWords(ConcatMode.pascal, words)

        assertThat(space).isEqualTo("to kill a mockingbird")
        assertThat(snake).isEqualTo("to_kill_a_mockingbird")
        assertThat(kebab).isEqualTo("to-kill-a-mockingbird")
        assertThat(simple).isEqualTo("tokillamockingbird")
        assertThat(camel).isEqualTo("toKillAMockingbird")
        assertThat(pascal).isEqualTo("ToKillAMockingbird")
    }


}
