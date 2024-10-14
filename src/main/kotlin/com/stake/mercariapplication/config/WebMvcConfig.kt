package com.stake.mercariapplication.config

import com.stake.mercariapplication.auth.AuthorizationHandlerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Bean
    fun authorizationHandlerInterceptor(): AuthorizationHandlerInterceptor {
        return AuthorizationHandlerInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationHandlerInterceptor()).addPathPatterns("/**")
    }
}
