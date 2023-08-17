package eu.frlab.diceware.eu.frlab.diceware

import eu.frlab.diceware.proto.DicewareGrpcKt
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareRequest
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareResponse
import eu.frlab.diceware.proto.DicewareResponseKt.codeWordPair
import eu.frlab.diceware.proto.dicewareResponse
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService

fun main() {
    val server = ServerBuilder
        .forPort(9090)
        .addService(Server)
        .addService(ProtoReflectionService.newInstance())
        .build()
        .start()

    println("Server started")
    server.awaitTermination()
}

object Server : DicewareGrpcKt.DicewareCoroutineImplBase() {

    override suspend fun roll(request: DicewareRequest): DicewareResponse =
        dicewareResponse {
            this.password = "my_password"
            this.codeWordPairs += codeWordPair {
                this.code = 1111
                this.word = "wordfor1111"
            }
        }

}
