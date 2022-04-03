package me.lyzev.whois.response

import com.google.gson.JsonObject

/**
 * Represents a WHOIS response.
 *
 * @author Lyzev
 * @param domain the domain name
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
class WhoIsResponse private constructor(
    val domain: String,
    val registryDomainID: String,
    val updatedDate: String,
    val creationDate: String,
    val referralURL: String,
    val registrar: String,
    val registrant: String,
    val admin: String,
    val tech: String,
    val nameservers: List<String>,
    val lastWhoisDatabaseUpdate: String,
    val rawResponse: String
) {

    /**
     * @return the raw response
     */
    override fun toString(): String = rawResponse

    companion object {

        /**
         * Creates a new instance of [WhoIsResponse] from the given [JsonObject].
         *
         * @param jsonObject the JSON object
         * @return the new instance
         */
        fun from(root: JsonObject): WhoIsResponse {
            return WhoIsResponse(
                root["domainName"].run { if (isJsonNull) "null" else asString },
                root["registryDomainID"].run { if (isJsonNull) "null" else asString },
                root["updatedDate"].run { if (isJsonNull) "null" else asString },
                root["creationDate"].run { if (isJsonNull) "null" else asString },
                root["referralURL"].run { if (isJsonNull) "null" else asString },
                root["registrar"].asJsonObject.get("registrar").run { if (isJsonNull) "null" else asString },
                root["registrant"].asJsonObject.get("organization").run { if (isJsonNull) "null" else asString },
                root["admin"].asJsonObject.get("organization").run { if (isJsonNull) "null" else asString },
                root["tech"].asJsonObject.get("organization").run { if (isJsonNull) "null" else asString },
                root["nameServer"].asJsonArray.map { it.run { if (isJsonNull) "null" else asString } },
                root["lastWhoisDatabaseUpdate"].run { if (isJsonNull) "null" else asString },
                root["serverResponse"].asJsonObject["rawResponse"].run { if (isJsonNull) "null" else asString }
            )
        }
    }
}