package me.lyzev.whois

import me.lyzev.whois.http.HttpClient
import me.lyzev.whois.http.HttpMethod

class WhoIs(val domain: String) {

    fun doRequest() {
        val response = HttpClient.request(HttpMethod.GET, "https://data.iana.org/rdap/dns.json")

    }

}