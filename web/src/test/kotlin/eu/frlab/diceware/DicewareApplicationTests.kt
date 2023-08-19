package eu.frlab.diceware

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class DicewareApplicationTests(
	@Autowired private val webController: WebController,
	@Autowired private val mockMvc: MockMvc,
) {

	@Test
	fun contextLoads() {
		assertThat(webController).isNotNull
	}

	@Test
	fun `test roll`() {
		this.mockMvc
			.perform(get("/api/roll"))
			.andDo(print())
			.andExpect(status().isOk)
	}

}
