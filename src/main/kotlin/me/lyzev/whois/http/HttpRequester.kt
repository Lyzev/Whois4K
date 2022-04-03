package me.lyzev.whois.http

import me.lyzev.whois.http.HttpMethod.GET
import me.lyzev.whois.http.HttpMethod.POST
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * Does HTTP requests.
 *
 * @author Lyzev
 */
object HttpRequester {

    /**
     * Sends a GET request to the specified URL.
     *
     * @param method The HTTP method to use.
     * @param url The URL to send the request to.
     * @param parameters The parameters to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    fun createRequest(
        method: HttpMethod,
        url: String,
        parameters: HttpParameterSet,
        vararg headers: HttpHeader
    ): HttpRequest? = when (method) {
        GET -> createGET(url, parameters, *headers)
        POST -> createPOST(url, parameters, *headers)
        else -> null
    }


    /**
     * Sends a POST request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param data The data to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    fun createRequest(url: String, data: String, vararg headers: HttpHeader): HttpRequest =
        createPOST(url, data, *headers)

    /**
     * Sends a GET request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param parameters The parameters to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    private fun createGET(url: String, parameters: HttpParameterSet, vararg headers: HttpHeader): HttpRequest {
        val request = StringBuilder(url)
        val paramaters = HttpParameterSetParser(parameters).asString()
        request.append(paramaters)
        val httpConnection = getConnection(request.toString(), *headers)
        httpConnection.requestMethod = HttpMethod.GET.asString()
        return HttpRequest(httpConnection)
    }

    /**
     * Sends a POST request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param parameters The parameters to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    private fun createPOST(url: String, parameters: HttpParameterSet, vararg headers: HttpHeader): HttpRequest =
        getConnectionPOST(url, HttpParameterSetParser(parameters).asString(), *headers)

    /**
     * Sends a POST request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param data The data to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    private fun createPOST(url: String, data: String, vararg headers: HttpHeader): HttpRequest =
        getConnectionPOST(url, data, *headers)

    /**
     * Sends a POST request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param data The data to send with the request.
     * @param headers The headers to send with the request.
     * @return The HTTP Request to the server.
     */
    @Throws(IOException::class)
    private fun getConnectionPOST(url: String, data: String, vararg headers: HttpHeader): HttpRequest {
        val httpConnection = getConnection(url, *headers)
        httpConnection.requestMethod = POST.asString()
        httpConnection.setRequestProperty("Content-Length", data.toByteArray().contentToString())
        httpConnection.doOutput = true
        val writer = OutputStreamWriter(httpConnection.outputStream)
        writer.write(data)
        writer.flush()
        return HttpRequest(httpConnection)
    }

    /**
     * Sends a GET request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param headers The headers to send with the request.
     * @return The HTTP Connection to the server.
     */
    @Throws(IOException::class)
    private fun getConnection(url: String, vararg headers: HttpHeader): HttpURLConnection {
        val urlRequest = URL(url)
        val httpConnection = urlRequest.openConnection() as HttpURLConnection
        httpConnection.addRequestProperty("User-Agent", HttpClient.USERAGENT)
        for (header in headers) httpConnection.addRequestProperty(header.key, header.value)
        return httpConnection
    }
}