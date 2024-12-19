package api.dataobject

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ContactsUserBody @JsonCreator constructor(
    @JsonProperty("_id") var id: String? = null,
    @JsonProperty("firstName") var firstName: String? = null,
    @JsonProperty("lastName") var lastName: String? = null,
    @JsonProperty("birthdate") var birthdate: String? = null,
    @JsonProperty("email") var email: String? = null,
    @JsonProperty("phone") var phone: String? = null,
    @JsonProperty("street1") var street1: String? = null,
    @JsonProperty("street2") var street2: String? = null,
    @JsonProperty("city") var city: String? = null,
    @JsonProperty("stateProvince") var stateProvince: String? = null,
    @JsonProperty("postalCode") var postalCode: String? = null,
    @JsonProperty("country") var country: String? = null,
    @JsonProperty("owner") var owner: String? = null,
    @JsonProperty("__v") var version: Int? = null,
)