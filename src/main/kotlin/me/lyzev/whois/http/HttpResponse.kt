package me.lyzev.whois.http

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Represents a HTTP response.
 *
 * @author Lyzev
 * @param inStream the input stream to read the response from
 */
class HttpResponse(var inStream: InputStream) {

    /**
     * Encodes the input stream to a string.
     *
     * @return the response as a string
     */
    @Throws(IOException::class)
    fun asString(): String? {
        val `in` = BufferedReader(InputStreamReader(inStream, HttpClient.CHARSET))
        val response = StringBuilder()
        var inputLine: String?
        while (`in`.readLine().also { inputLine = it } != null) response.append(inputLine)
        `in`.close()
        return response.toString()
    }
}