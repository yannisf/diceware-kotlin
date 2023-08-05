package eu.frlab.diceware

interface DicewareService {

    fun rollForPassword(
        numberOfCodeSequences: Int,
        sequenceCodeLength: Int = 5,
        concatMode: ConcatMode = ConcatMode.simple): DicewareResult

    fun getWord(code: Int): String

}
