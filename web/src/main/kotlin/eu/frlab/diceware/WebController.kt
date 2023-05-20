package eu.frlab.diceware

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
class WebController(
    private val repository: DicewareRepository,
    private val service: RollService
) {

    @GetMapping(path = ["/word"], produces = ["text/plain"])
    fun getWordsAsText(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.joinToString(transform = repository::getWord)

    @GetMapping(path = ["/word"], produces = ["application/json"] )
    fun getWordsAsJson(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.associateWith(repository::getWord)

    @GetMapping(path = ["/roll"], produces = ["text/plain"] )
    fun rollForPassword(
        @RequestParam(value = "word_num",  required = false, defaultValue = "5")
        numberOfWords: Int,
        @RequestParam(value="concat", required = false, defaultValue = "simple")
        concatMode: ConcatMode,
    ) = service.rollForPassword(numberOfCodeSequences = numberOfWords, concatMode = concatMode).password

    @GetMapping(path = ["/roll"], produces = ["application/json"] )
    fun rollForWords(
        @RequestParam(value = "word_num",  required = false, defaultValue = "5")
        numberOfWords: Int,
        @RequestParam(value = "short",  required = false, defaultValue = "false")
        useShortList: Boolean,
        @RequestParam(value="concat", required = false, defaultValue = "simple")
        concatMode: ConcatMode,
    ) = service.rollForPassword(numberOfCodeSequences = numberOfWords, sequenceCodeLength = if (useShortList) 4 else 5, concatMode = concatMode)

}