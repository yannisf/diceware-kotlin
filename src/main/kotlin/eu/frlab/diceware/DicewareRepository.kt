package eu.frlab.diceware

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository

@Repository("UUID_Generator")
@Scope("singleton")
class DicewareRepository() {

    private val EFF_LARGE_WORDLIST = "/static/eff_large_wordlist.txt"

    private val dictionary = loadWordList(EFF_LARGE_WORDLIST)

    fun getWord(code: Int) = dictionary.getValue(code)

    companion object {

        fun loadWordList(file: String) = DicewareRepository::class.java.getResource(file)!!
            .readText()
            .split("\n")
            .filter(String::isNotEmpty)
            .associate { it.split("\t").let { (code, word) -> code.toInt() to word } }

    }

}
