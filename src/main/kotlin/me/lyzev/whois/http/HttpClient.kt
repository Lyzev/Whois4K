package me.lyzev.whois.http

import java.io.IOException

/**
 * Used to send HTTP requests.
 *
 * @author Lyzev
 * @see HttpRequest
 * @see HttpResponse
 */
object HttpClient {

    const val USERAGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36"
    const val CHARSET = "UTF-8"

    /**
     * Sends an HTTP request and returns the response.
     */
    @Throws(IOException::class)
    fun request(method: HttpMethod, url: String): HttpResponse =
        HttpRequester.createRequest(method, url, HttpParameterSet())!!.doRequest()

    /**
     * Sends an HTTP request and returns the response.
     */
    @Throws(IOException::class)
    fun request(method: HttpMethod, url: String, vararg headers: HttpHeader): HttpResponse =
        HttpRequester.createRequest(method, url, HttpParameterSet(), *headers)!!.doRequest()

    /**
     * Sends an HTTP request and returns the response.
     */
    @Throws(IOException::class)
    fun request(method: HttpMethod, url: String, httpParameters: HttpParameterSet): HttpResponse =
        HttpRequester.createRequest(method, url, httpParameters)!!.doRequest()

    /**
     * Sends an HTTP request and returns the response.
     */
    @Throws(IOException::class)
    fun request(
        method: HttpMethod,
        url: String,
        httpParameters: HttpParameterSet,
        vararg headers: HttpHeader
    ): HttpResponse = HttpRequester.createRequest(method, url, httpParameters, *headers)!!.doRequest()


    /**
     * Sends an HTTP-POST request and returns the response.
     */
    @Throws(IOException::class)
    fun requestPOST(url: String, data: String): HttpResponse = HttpRequester.createRequest(url, data).doRequest()

    /**
     * Sends an HTTP-POST request and returns the response.
     */
    @Throws(IOException::class)
    fun requestPOST(url: String, data: String, vararg headers: HttpHeader): HttpResponse =
        HttpRequester.createRequest(url, data, *headers).doRequest()
}