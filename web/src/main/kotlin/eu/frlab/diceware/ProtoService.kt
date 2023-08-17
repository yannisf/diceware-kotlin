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
    override suspend fun roll(request: DicewareRequest): DicewareResponse {
        val useShortCodes = request.shortCodes
        val concatMode = request.concatMode
        val numberOfWords = request.numberOfWords

        val result = service.rollForPassword(numberOfWords, if (useShortCodes) 4 else 5, ConcatMode.space)

        val protoCodeWordPairs = result.rollData.map {
            codeWordPair {
                code = it.code
                word = it.word
            }
        }

        return dicewareResponse {
            protoCodeWordPairs.forEach{
                codeWordPairs += it
            }
            password = result.password
        }

    }

}

