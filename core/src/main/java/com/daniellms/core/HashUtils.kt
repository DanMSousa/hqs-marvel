package com.daniellms.core

import java.math.BigInteger
import java.security.MessageDigest

object HashUtils {

    fun getTimeStamp(): String {
        return System.currentTimeMillis().toString()
    }

    private fun generateMD5Hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun generateHash(ts: String, privateKey: String, publicKey: String): String {
        return generateMD5Hash(ts + privateKey + publicKey)
    }

}