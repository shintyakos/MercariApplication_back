package com.stake.mercariapplication.util

import com.stake.mercariapplication.auth.JwtUtil
import org.aspectj.lang.annotation.Around
import org.slf4j.LoggerFactory

object Logger {
    private val logger = LoggerFactory.getLogger(JwtUtil::class.java)

    @Around("@annotation(com.stake.mercariapplication.util.Logger)")
    fun debug(value: String) {
        logger.debug(value)
    }

    @Around("@annotation(com.stake.mercariapplication.util.Logger)")
    fun info(value: String) {
        logger.info(value)
    }

    @Around("@annotation(com.stake.mercariapplication.util.Logger)")
    fun error(value: String) {
        logger.error(value)
    }
}
