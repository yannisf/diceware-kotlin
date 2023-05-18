package eu.frlab.diceware

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository

@Repository("DictionaryRepository")
@Scope("singleton")
class DicewareRepository() {

    private val effLargeWordlistResource = "/static/eff_large_wordlist.txt"
    private val effShortWordlistResource = "/static/eff_short_wordlist_1.txt"

    private val largeWordList = loadWordList(effLargeWordlistResource)
    private val shortWordList = loadWordList(effShortWordlistResource)

    fun getWord(code: Int) = if (code > 6666) largeWordList.getValue(code) else shortWordList.getValue(code)

    companion object {

        fun loadWordList(file: String) = DicewareRepository::class.java.getResource(file)!!
            .readText()
            .split("\n")
            .filter(String::isNotEmpty)
            .associate { it.split("\t").let { (code, word) -> code.toInt() to word } }

    }

}
