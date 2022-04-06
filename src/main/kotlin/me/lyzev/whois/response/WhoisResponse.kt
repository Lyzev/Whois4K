package me.lyzev.whois.response

import com.google.gson.JsonObject

/**
 * Represents a WHOIS response.
 *
 * @author Lyzev
 * @param name the domain name
 * @param registryDomainID the registry domain ID
 * @param updatedDate the updated date
 * @param creationDate the creation date
 * @param referralURL the referral URL
 * @param registrar the registrar
 * @param registrant the registrant
 * @param admin the admin
 * @param tech the tech
 * @param nameservers the nameservers
 * @param lastWhoisDatabaseUpdate the last WHOIS database update
 * @param rawResponse the raw response
 */
class WhoisResponse private constructor(val name: String, val registryDomainID: String, val domainStatus: List<String>,
                                        val nameservers: List<String>, val registryExpiration: String,
                                        val updated: String, val created: String) {

    val rawResponse: String = """
        Name: $name
        
        Registry Domain ID: $registryDomainID
        
        Domain Status:
        ${domainStatus.joinToString("\n")}
                
        Nameservers:
        ${nameservers.joinToString("\n")}
        
        Registry Expiration Date: $registryExpiration
        Updated Date: $updated
        Creation Date: $created
    """.trimIndent()

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