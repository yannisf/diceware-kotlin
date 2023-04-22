package eu.frlab.diceware

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordlistLoadTest {

    private val EFF_LARGE_WORDLIST = "/static/eff_large_wordlist.txt"

    @Test
    fun loadFile() {
        val fileContent = WordlistLoadTest::class.java.getResource(EFF_LARGE_WORDLIST)?.readText()
        assertThat(fileContent).isNotNull()
        assertThat(fileContent).isNotEmpty()
    }

    @Test
    fun parseFile() {
        val dictionary = DicewareRepository.loadWordList(EFF_LARGE_WORDLIST)

        assertThat(dictionary.keys.size).isEqualTo(7776)
        assertThat(dictionary.getValue(23615)).isEqualTo("document")
    }

}
