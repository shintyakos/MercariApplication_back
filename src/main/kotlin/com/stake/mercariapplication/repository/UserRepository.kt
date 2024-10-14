package com.stake.mercariapplication.repository

import com.stake.mercariapplication.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    fun findByEmail(email: String): User?

    @Query("SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    fun findByUserName(username: String): User?
}
