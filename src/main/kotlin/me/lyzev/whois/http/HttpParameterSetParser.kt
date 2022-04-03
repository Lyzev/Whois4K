package me.lyzev.whois.http

/**
 * Parses a set of HTTP parameters.
 *
 * @author Lyzev
 * @param parameters The HTTP parameter sets to parse.
 */
internal class HttpParameterSetParser(private val parameters: HttpParameterSet) {

    fun asString(): String {
        if (parameters.toList().isEmpty()) return ""
        val result = StringBuilder("?")
        for (parameter in parameters.toList()) {
            result.append(parameter.name)
            result.append("=")
            result.append(parameter.value)
            result.append("&")
        }
        return withoutLastCharacter(result)
    }

    private fun withoutLastCharacter(stringBuilder: StringBuilder): String {
        val result = stringBuilder.toString()
        return result.substring(0, result.length - 1)
    }
}