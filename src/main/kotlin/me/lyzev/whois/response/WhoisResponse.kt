package me.lyzev.whois.response

import com.google.gson.JsonObject

/**
 * Represents a WHOIS response.
 *
 * @author Lyzev
 * @property name the domain name
 * @property registryDomainID the registry domain ID
 * @property domainStatus the domain status
 * @property nameservers the nameservers
 * @property registryExpiration the registry expiration date
 * @property updated the last update date
 * @property created the creation date
 */
class WhoisResponse private constructor(val name: String, val registryDomainID: String, val domainStatus: List<String>,
                                        val nameservers: List<String>, val registryExpiration: String,
                                        val updated: String, val created: String) {

    val rawResponse: String = "=======================================================\nName: $name\n\nRegistry Domain ID: $registryDomainID\n\nDomain Status:\n${domainStatus.joinToString("\n")}\n\nNameservers:\n${nameservers.joinToString("\n")}\n\nRegistry Expiration Date: $registryExpiration\nUpdated Date: $updated\nCreation Date: $created\n======================================================="

    /**
     * @return the raw response
     */
    override fun toString(): String = rawResponse

    companion object {

        /**
         * Creates a new instance of [WhoisResponse] from the given [JsonObject].
         *
         * @param jsonObject the JSON object
         * @return the new instance
         */
        fun from(root: JsonObject): WhoisResponse {
            return WhoisResponse(root["ldhName"].run { if (isJsonNull) "null" else asString },
                root["handle"].run { if (isJsonNull) "null" else asString },
                root["status"].asJsonArray.map { it.run { if (isJsonNull) "null" else asString } },
                root["nameservers"].asJsonArray.map { it.run { if (isJsonNull) "null" else asJsonObject["ldhName"].asString } },
                root["events"].run { if (isJsonNull) "null" else asJsonArray[1].asJsonObject["eventDate"].asString },
                root["events"].run { if (isJsonNull) "null" else asJsonArray[2].asJsonObject["eventDate"].asString },
                root["events"].run { if (isJsonNull) "null" else asJsonArray[0].asJsonObject["eventDate"].asString })
        }
    }
}