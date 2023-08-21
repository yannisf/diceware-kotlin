package eu.frlab.diceware

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint
import org.springframework.stereotype.Component


@Component
@WebEndpoint(id = "checksums")
class EffWordListChecksums(
    private val service: DicewareService
) {

    @ReadOperation
    fun checksums(): Map<String, Checksums> = service.produceChecksums()

}


