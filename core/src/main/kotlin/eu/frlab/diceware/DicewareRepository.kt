package eu.frlab.diceware

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Repository

@Repository("DictionaryRepository")
@Scope("singleton")
class DicewareRepository(
    @Autowired private val appProperties: AppProperties
) {

    val largeWordList = loadWordList(appProperties.largeWordList)
    val shortWordList = loadWordList(appProperties.shortWordList)

    fun getShortWordListByteArray() = DicewareRepository::class.java.getResource(appProperties.shortWordList)!!.readBytes()

    fun getLargeWordListByteArray() = DicewareRepository::class.java.getResource(appProperties.largeWordList)!!.readBytes()

    fun getWord(code: Int) = if (code > 6666) largeWordList.getValue(code) else shortWordList.getValue(code)

    companion object {

        fun loadWordList(file: String) = DicewareRepository::class.java.getResource(file)!!
            .readText()
            .split("\n")
            .filter(String::isNotEmpty)
            .associate { it.split("\t").let { (code, word) -> code.toInt() to word } }

    }

}
