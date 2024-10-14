package com.stake.mercariapplication.service

import com.stake.mercariapplication.repository.UserRepository
import com.stake.mercariapplication.util.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService() {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun login(email: String, password: String): String? {
        userRepository.findByEmail(email)?.let { user ->
            if (passwordEncoder.matches(password, user.password)) {
                return user.userName
            }
        }

        return null
    }
}
