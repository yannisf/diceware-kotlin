package eu.frlab.diceware

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service("RollService")
@Scope("singleton")
class RollService(private val repository: DicewareRepository) {

    private final val secureRandom = SecureRandom()

    fun singleRoll() = (secureRandom.nextInt(6) + 1).toString()

    fun multipleRoll(times: Int = 5) = (0 until times).joinToString (transform = {singleRoll()}, separator = "").toInt()

    fun repeatedMultipleRoll(numberOfCodes: Int, codeLength: Int = 5) = (0 until  numberOfCodes).map { multipleRoll(codeLength) }

    fun rollForWords(numberOfCodes: Int, codeLength: Int = 5) = repeatedMultipleRoll(numberOfCodes, codeLength).associateWith { repository.getWord(it) }

    fun rollForPassword(numberOfCodes: Int, codeLength: Int = 5, concatMode: ConcatMode = ConcatMode.simple): DiceWareResult {
        val codeWordMap = rollForWords(numberOfCodes, codeLength)
        val words = codeWordMap.map { it.value }
        val password = when (concatMode) {
            ConcatMode.simple -> words.joinToString(separator = "")
            ConcatMode.camel -> words.joinToString(separator = "") { it.replaceFirstChar(Char::uppercaseChar) }.replaceFirstChar(Char::lowercase)
            ConcatMode.pascal -> words.joinToString(separator = "") { it.replaceFirstChar(Char::uppercaseChar) }
            ConcatMode.snake -> words.joinToString(separator = "_")
            ConcatMode.kebab -> words.joinToString(separator = "-")
        }

        return DiceWareResult(codeWordMap.map { CodeWordPair(it.key,it.value) }, password)
    }

}