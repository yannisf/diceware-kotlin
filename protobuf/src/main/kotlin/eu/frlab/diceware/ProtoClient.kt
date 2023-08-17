package eu.frlab.diceware

import eu.frlab.diceware.proto.DicewareGrpcKt
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareRequest.ConcatMode
import eu.frlab.diceware.proto.dicewareRequest
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 9090)
        .usePlaintext()
        .build()

    val client = DicewareGrpcKt.DicewareCoroutineStub(channel)

    val request = dicewareRequest {
        this.shortCodes = true
        this.numberOfWords = 5
        this.concatMode = ConcatMode.SPACE
    }

    val response = client.roll(request)

    println(response)

}