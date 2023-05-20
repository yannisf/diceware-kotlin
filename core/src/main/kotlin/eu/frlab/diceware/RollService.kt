package eu.frlab.diceware

interface RollService {

    fun rollForPassword(
        numberOfCodeSequences: Int,
        sequenceCodeLength: Int = 5,
        concatMode: ConcatMode = ConcatMode.simple): DiceWareResult

}
