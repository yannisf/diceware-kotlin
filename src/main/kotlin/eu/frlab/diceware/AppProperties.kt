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
)
