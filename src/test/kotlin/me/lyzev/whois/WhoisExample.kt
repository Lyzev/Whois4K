package me.lyzev.whois

/**
 * An example of how to use the library.
 */
fun main() {
    val whois = Whois("example.com")
    whois.doRequest().forEach(::println)
}