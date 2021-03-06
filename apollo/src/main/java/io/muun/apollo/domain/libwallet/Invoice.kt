package io.muun.apollo.domain.libwallet

import org.threeten.bp.ZonedDateTime

class Invoice (var original: String,
               val amountInSat: Long?,
               val description: String,
               val expirationTime: ZonedDateTime,
               var destinationPublicKey: String) {
}