package eu.frlab.diceware

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordlistsTest {

    private val repository = DicewareRepository()

    @Test
    fun largeWordListsExist() {
        assertThat(repository.largeWordList).isNotNull()
        assertThat(repository.largeWordList).isNotEmpty()
    }

    @Test
    fun shortWordListsExist() {
        assertThat(repository.shortWordList).isNotNull()
        assertThat(repository.shortWordList).isNotEmpty()
    }

    @Test
    fun largeWordListIsParsable() {
        assertThat(repository.largeWordList.keys.size).isEqualTo(7776)
        assertThat(repository.largeWordList.getValue(23615)).isEqualTo("document")
    }

    @Test
    fun shortWordListIsParsable() {
        assertThat(repository.shortWordList.keys.size).isEqualTo(1296)
        assertThat(repository.shortWordList.getValue(5432)).isEqualTo("skid")
    }

}
