package me.lyzev.whois

/**
 * AN example of how to use the library.
 */
fun main() {
    val whoIs = WhoIs("google.com")
    whoIs.doRequest().forEach(::println)
}