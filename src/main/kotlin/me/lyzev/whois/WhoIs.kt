package me.lyzev.whois

import com.google.gson.JsonParser
import me.lyzev.network.http.HttpClient
import me.lyzev.network.http.HttpMethod
import me.lyzev.whois.response.WhoIsResponse

/**
 * WhoIs is a simple library for querying whois information.
 *
 * @author Lyzev
 * @param hostname The hostname to query.
 */
class WhoIs(val hostname: String) {

    /**
     * Queries whois information for the hostname.
     *
     * @return The whois information.
     */
    fun doRequest(): List<WhoIsResponse> {
        val response = HttpClient.request(HttpMethod.GET, "https://lookup.icann.org/api/whois?q=$hostname")
        val root = JsonParser.parseString(response.toString()).asJsonObject
        val whoIs = mutableListOf<WhoIsResponse>()
        root["records"].asJsonArray.forEach { whoIs += WhoIsResponse.from(it.asJsonObject) }
        return whoIs
    }
}