package com.stake.mercariapplication.auth

import lombok.Getter
import java.util.*

class Hash {
    val secretKey: String = UUID.randomUUID().toString()
    val tokenIssuer: String = "system"
    val tokenSubject: String = "service_token"
}
