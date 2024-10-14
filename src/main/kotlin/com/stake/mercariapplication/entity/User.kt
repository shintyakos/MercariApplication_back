package com.stake.mercariapplication.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.Date

@Table(name = "user")
@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Int? = null

    @Column(name = "user_name", nullable = false)
    open var userName: String? = null

    @Column(name = "phone", nullable = false)
    open var phone: String? = null

    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Column(name = "password", nullable = false)
    open var password: String? = null

    @field:CreatedDate
    @Column(name = "created_at", updatable = true)
    open var createdAt: Date? = null

    @field:LastModifiedDate
    @Column(name = "updated_at")
    open var updatedAt: Date? = null

    @field:ColumnDefault("0")
    @Column(name = "is_deleted")
    open var isDeleted: Boolean? = false
}