package eu.frlab.diceware

interface DicewareService {

    fun rollForPassword(
        numberOfWords: Int = 5,
        shortCodes: Boolean = false,
        concatMode: ConcatMode = ConcatMode.simple): DicewareResult

    fun getWord(code: Int): String

    fun produceChecksums(): Map<String, Checksums>
}

data class Checksums(val short: String, val large: String)