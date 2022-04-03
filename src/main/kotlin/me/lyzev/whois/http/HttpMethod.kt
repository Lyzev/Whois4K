package me.lyzev.whois.http

/**
 * Represents HTTP methods.
 *
 * @author Lyzev
 */
enum class HttpMethod(private val value: String) {

    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

    /**
     * Returns the value of this HTTP method.
     */
    fun asString(): String = value
}