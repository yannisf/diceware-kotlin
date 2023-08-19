package eu.frlab.diceware

import eu.frlab.diceware.proto.DicewareGrpcKt
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareRequest
import eu.frlab.diceware.proto.DicewareOuterClass.DicewareResponse
import eu.frlab.diceware.proto.DicewareResponseKt.codeWordPair
import eu.frlab.diceware.proto.dicewareResponse
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class ProtoService(
    private val service: DicewareService
) : DicewareGrpcKt.DicewareCoroutineImplBase() {

    fun DicewareRequest.ConcatMode.toModel(): ConcatMode =
        ConcatMode.valueOf(this.name.lowercase())

    override suspend fun roll(request: DicewareRequest): DicewareResponse {
        val shortCodes = request.shortCodes
        val concatMode = request.concatMode.toModel()
        val numberOfWords = request.numberOfWords

        val result = service.rollForPassword(numberOfWords, shortCodes, concatMode)

        val protoCodeWordPairs = result.rollData.map {
            codeWordPair {
                code = it.code
                word = it.word
            }
        }

        return dicewareResponse {
            password = result.password
            codeWordPairs.addAll(protoCodeWordPairs)
        }

    }

}

