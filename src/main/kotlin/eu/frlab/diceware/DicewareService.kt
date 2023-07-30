package eu.frlab.diceware

interface DicewareService {

    fun rollForPassword(
        numberOfCodeSequences: Int,
        sequenceCodeLength: Int = 5,
        concatMode: ConcatMode = ConcatMode.simple): DiceWareResult

    fun getWord(code: Int): String

}
