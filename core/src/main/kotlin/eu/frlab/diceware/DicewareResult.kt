package eu.frlab.diceware

data class CodeWordPair(val code: Int, val word: String)
data class DicewareResult(val rollData: List<CodeWordPair>, val password: String)
