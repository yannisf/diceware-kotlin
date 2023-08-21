package eu.frlab.diceware

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

/**
 * Configuration properties for the application. Can be used as an injectable as bean.
 * @property defaultConcatMode the default password concatenation mode
 * @property defaultUseShortList use the short diceware list by default
 * @property defaultNumWords number of words to use by default when creating a password
 */
@ConfigurationProperties(prefix = "app")
@Validated
data class AppProperties(
    val defaultConcatMode: ConcatMode = ConcatMode.space,
    val defaultUseShortList: Boolean = false,
    @get:[Min(4) Max(20)]
    val defaultNumWords: Int = 5,
    val largeWordList: String,
    val shortWordList: String,
) {

    fun prettyPrint(): String = """
        
        === Application Properties ===
        large_word_list = $largeWordList
        short_word_list = $shortWordList
        default_concat_mode = $defaultConcatMode 
        default_use_short_list = $defaultUseShortList
        default_num_words = $defaultNumWords
        
    """.trimIndent()

}
