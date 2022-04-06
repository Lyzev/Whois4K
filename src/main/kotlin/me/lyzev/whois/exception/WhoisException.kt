package me.lyzev.whois.exception

/**
 * Throw this exception when the whois server returns an error.
 *
 * @author Lyzev
 * @param message The error message.
 * @param cause The cause of the error.
 */
class WhoisException : Exception {

    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}