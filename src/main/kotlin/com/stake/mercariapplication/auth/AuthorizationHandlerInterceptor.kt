package com.stake.mercariapplication.auth

import com.stake.mercariapplication.annotation.Authorize
import com.stake.mercariapplication.annotation.NonAuthorize
import com.stake.mercariapplication.util.Logger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor

class AuthorizationHandlerInterceptor() : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = (handler as? HandlerMethod)?.method ?: return true

        if (AnnotationUtils.findAnnotation(method, NonAuthorize::class.java) != null) {
            return true
        }

        val controller = method.declaringClass
        if (AnnotationUtils.findAnnotation(controller, Authorize::class.java) != null
            || AnnotationUtils.findAnnotation(method, Authorize::class.java) != null) {
                if (JwtUtil.authorize(request)) {
                    return true
                } else {
                    throw ResponseStatusException(HttpStatus.FORBIDDEN)
                }
            }

        return true
    }
}
