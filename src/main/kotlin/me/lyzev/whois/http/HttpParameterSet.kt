package me.lyzev.whois.http

/**
 * A set of HTTP parameters.
 *
 * @author Lyzev
 */
class HttpParameterSet {

    private val parameters = HashSet<HttpParameter>()

    /**
     * Adds a parameter to the set.
     */
    fun add(parameter: HttpParameter) = parameters.add(parameter)

    /**
     * @return a list of HTTP parameters.
     */
    fun toList(): List<HttpParameter> = parameters.toList()

    companion object {

        /**
         * Creates a new parameter set.
         */
        fun build(): HttpParameterSet = HttpParameterSet()
    }
}