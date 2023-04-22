package eu.frlab.diceware

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
class WebController(private val repository: DicewareRepository) {

    @GetMapping(path = ["/word"], produces = ["text/plain"])
    fun getWordsAsText(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.joinToString(transform = repository::getWord)

    @GetMapping(path = ["/word"], produces = ["application/json"] )
    fun getWordsAsMap(@RequestParam(value = "code",  required = true) codes: IntArray) =
        codes.associateWith(repository::getWord)

}