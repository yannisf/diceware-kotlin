package eu.frlab.diceware

import eu.frlab.diceware.proto.DicewareGrpcKt
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareRequest.ConcatMode
import eu.frlab.diceware.proto.dicewareRequest
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(value = ["classpath:/application-test.properties"])
class DicewareGrpcTests(
	@Value("\${grpc.port}")
	private val port: Int
) {

	/*
	 * NOTE: A different configuration is used to set the GRPC port to a different one.
	 * It seems that a previous integration tests left the port still bound
	 */
	@Test
	fun `test grpc roll`() {
		val channel = ManagedChannelBuilder
			.forAddress("localhost", port)
			.usePlaintext()
			.build()

		val client = DicewareGrpcKt.DicewareCoroutineStub(channel)

		val request = dicewareRequest {
			this.shortCodes = true
			this.numberOfWords = 5
			this.concatMode = ConcatMode.SPACE
		}

		runBlocking {
			val response = client.roll(request)
			println(response)
		}

	}

}
