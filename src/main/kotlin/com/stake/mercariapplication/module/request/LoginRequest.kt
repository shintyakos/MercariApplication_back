package com.stake.mercariapplication.module.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class LoginRequest(
    @field:NotEmpty
    @field:Size(min = 1, max = 255)
    @field:Email
    val email: String,

    @field:NotEmpty
    @field:Size(min = 8, max = 255)
    @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$")
    val password: String
)