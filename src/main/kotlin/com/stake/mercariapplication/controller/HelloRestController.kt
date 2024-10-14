package com.stake.mercariapplication.controller

import com.stake.mercariapplication.annotation.Authorize
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Authorize
class HelloRestController {
    @GetMapping("/hello")
    @Authorize
    fun hello(request: HttpServletRequest): String {
        return "Hello, World! ${request.getAttribute("username")}"
    }
}