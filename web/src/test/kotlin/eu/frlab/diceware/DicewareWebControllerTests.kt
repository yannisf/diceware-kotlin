package eu.frlab.diceware

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [WebController::class])
class DicewareWebControllerTests(
	@Autowired private val mockMvc: MockMvc,
) {

	@MockBean
	private lateinit var service: DicewareService

	@Test
	fun `test roll`() {

		doReturn(DicewareResult(emptyList(), "password"))
			.whenever(service).rollForPassword(any(), any(), any())

		this.mockMvc
			.perform(get("/api/roll"))
			.andDo(print())
			.andExpect(status().isOk)

	}

}
