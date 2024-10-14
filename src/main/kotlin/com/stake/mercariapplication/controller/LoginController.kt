package com.stake.mercariapplication.controller

import com.stake.mercariapplication.annotation.Authorize
import com.stake.mercariapplication.annotation.NonAuthorize
import com.stake.mercariapplication.auth.JwtUtil
import com.stake.mercariapplication.module.request.LoginRequest
import com.stake.mercariapplication.module.response.LoginResponse
import com.stake.mercariapplication.service.LoginService
import com.stake.mercariapplication.util.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@Authorize
class LoginController() {
    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping("/login")
    @NonAuthorize
    fun login(@Validated @RequestBody request: LoginRequest, bindingResult: BindingResult): ResponseEntity<LoginResponse> {
        if (bindingResult.hasErrors()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "${bindingResult.fieldError?.field} is invalid")
        }

        val username = loginService.login(request.email, request.password) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "login failed")

        return ResponseEntity.ok(LoginResponse().apply { this.token = JwtUtil.generateToken(username) })
    }
}
