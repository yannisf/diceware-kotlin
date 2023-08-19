package eu.frlab.diceware

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class WebController(
    private val service: DicewareService,
) {

    @GetMapping(path = ["/word"], produces = ["text/plain"])
    fun getWordsAsText(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.joinToString(transform = service::getWord)

    @GetMapping(path = ["/word"], produces = ["application/json"] )
    fun getWordsAsJson(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.associateWith(service::getWord)

    @GetMapping(path = ["/roll"], produces = ["text/plain"] )
    fun rollForPassword(
        @RequestParam(value = "word_num",  required = false, defaultValue = "\${app.default_num_words}")
        numberOfWords: Int,
        @RequestParam(value = "short",  required = false, defaultValue = "\${app.default_use_short_list}")
        useShortList: Boolean,
        @RequestParam(value="concat", required = false, defaultValue = "\${app.default_concat_mode}")
        concatMode: ConcatMode,
    ) = service.rollForPassword(numberOfWords, useShortList, concatMode).password

    @GetMapping(path = ["/roll"], produces = ["application/json"] )
    fun rollForWords(
        @RequestParam(value = "word_num",  required = false, defaultValue = "\${app.default_num_words}")
        numberOfWords: Int,
        @RequestParam(value = "short",  required = false, defaultValue = "\${app.default_use_short_list}")
        useShortList: Boolean,
        @RequestParam(value="concat", required = false, defaultValue = "\${app.default_concat_mode}")
        concatMode: ConcatMode,
    ) = service.rollForPassword(numberOfWords, useShortList, concatMode)

}