package me.lyzev.whois

/**
 * AN example of how to use the library.
 */
fun main() {
    val whoIs = WhoIs("example.com")
    whoIs.doRequest().forEach(::println)
}