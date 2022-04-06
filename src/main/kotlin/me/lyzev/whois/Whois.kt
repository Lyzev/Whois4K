package me.lyzev.whois

import com.google.common.net.InternetDomainName
import com.google.gson.JsonParser
import me.lyzev.network.http.HttpClient
import me.lyzev.network.http.HttpMethod
import me.lyzev.whois.exception.WhoisException
import me.lyzev.whois.response.WhoisResponse


/**
 * WhoIs is a simple library for querying whois information.
 *
 * @author Lyzev
 * @property hostname The hostname to query.
 */
class Whois(val hostname: String) {

    val tld = InternetDomainName.from(hostname).run {
        if (hasPublicSuffix()) publicSuffix().toString()
        else throw WhoisException("$hostname is not a valid domain name")
    }

    /**
     * Queries whois information for the hostname.
     *
     * @return The whois information.
     */
    fun doRequest(): List<WhoisResponse> {
        val whois = mutableListOf<WhoisResponse>()
        for (service in getService().also {
            if (it.isEmpty()) throw WhoisException(
                "No services found for $hostname"
            )
        }) {
            val response = HttpClient.request(HttpMethod.GET, "$service/domain/$hostname")
            whois += WhoisResponse.from(JsonParser.parseString(response.toString()).asJsonObject)
        }
        return whois
    }

    /**
     * Gets the list of services to query.
     *
     * @return The list of services.
     */
    fun getService(): List<String> {
        val services = mutableListOf<String>()
        val response = HttpClient.request(HttpMethod.GET, "https://data.iana.org/rdap/dns.json")
        val root = JsonParser.parseString(response.toString()).asJsonObject
        root["services"].asJsonArray.map { it.asJsonArray }.forEach { service ->
            service[0].asJsonArray.forEach {
                if (it.asString == tld) {
                    services += service[1].asJsonArray.map { it.asString }
                }
            }
        }
        return services
    }
}