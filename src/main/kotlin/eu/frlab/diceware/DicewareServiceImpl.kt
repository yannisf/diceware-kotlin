package eu.frlab.diceware

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service("RollService")
@Scope("singleton")
class DicewareServiceImpl(
    private val repository: DicewareRepository,
    appProperties: AppProperties,
) : DicewareService {

    private val log = LoggerFactory.getLogger(javaClass)

    private final val secureRandom = SecureRandom()

    /**
     * Performs a simple die roll.
     */
    fun singleRoll() = (secureRandom.nextInt(6) + 1).toString()

    /**
     * Performs a repeated die roll.
     *
     * @param times: The number of rolls
     */
    fun repeatedRolls(times: Int = 5) =
        (0 until times).joinToString(transform = { singleRoll() }, separator = "").toInt()

    /**
     * Rolls a die [numberOfCodeSequences]*[sequenceCodeLength], effectively producing [numberOfCodeSequences] code sequences,
     * with each sequence having [sequenceCodeLength] codes, from 1 to 6.
     *
     * @param numberOfCodeSequences effectively, the number of requested words
     * @param sequenceCodeLength the number of codes, typically 5 for the long list, 4 for the short list
     */
    fun consecutiveRepeatedRolls(numberOfCodeSequences: Int, sequenceCodeLength: Int = 5) =
        (0 until numberOfCodeSequences).map { repeatedRolls(sequenceCodeLength) }

    /**
     * Dereferences the requested [numberOfCodeSequences] of [sequenceCodeLength] length, to a dictionary of words.
     */
    fun rollForWords(numberOfCodeSequences: Int, sequenceCodeLength: Int = 5): Map<Int, String> =
        consecutiveRepeatedRolls(numberOfCodeSequences, sequenceCodeLength).associateWith { repository.getWord(it) }

    /**
     * Produces the final password, given all the requested generation parameters.
     */
    override fun rollForPassword(
        numberOfCodeSequences: Int,
        sequenceCodeLength: Int,
        concatMode: ConcatMode
    ): DicewareResult {
        log.info("Rolling...")
        val codeWordMap = rollForWords(numberOfCodeSequences, sequenceCodeLength)
        val words = codeWordMap.map { it.value }
        val password = concatWords(concatMode, words)

        return DicewareResult(codeWordMap.map { CodeWordPair(it.key, it.value) }, password)
    }

    fun concatWords(concatMode: ConcatMode, words: List<String>): String = when (concatMode) {
        ConcatMode.simple -> words.joinToString(separator = "")
        ConcatMode.space -> words.joinToString(separator = " ")
        ConcatMode.camel -> words.joinToString(separator = "") { it.replaceFirstChar(Char::uppercaseChar) }
            .replaceFirstChar(Char::lowercase)

        ConcatMode.pascal -> words.joinToString(separator = "") { it.replaceFirstChar(Char::uppercaseChar) }
        ConcatMode.snake -> words.joinToString(separator = "_")
        ConcatMode.kebab -> words.joinToString(separator = "-")
    }

    override fun getWord(code: Int): String {
        return repository.getWord(code)
    }

}