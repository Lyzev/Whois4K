package me.lyzev.whois.http

import java.io.FileNotFoundException
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Represents a HTTP request.
 *
 * @author Lyzev
 * @param httpConnection The HTTP connection to use.
 */
class HttpRequest(val httpConnection: HttpURLConnection) {

    /**
     * @return The HTTP response.
     */
    @Throws(IOException::class)
    fun doRequest(): HttpResponse {
        return try {
            val inStream = httpConnection.inputStream
            HttpResponse(inStream)
        } catch (e: FileNotFoundException) {
            throw IOException("Provider return error: " + httpConnection.responseCode)
        }
    }
}