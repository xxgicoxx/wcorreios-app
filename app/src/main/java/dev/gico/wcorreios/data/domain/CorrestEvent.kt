package dev.gico.wcorreios.data.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CorrestEvent(@SerialName("event") var event: String) {

    @SerialName("date")
    var date: String = ""

    @SerialName("hour")
    var hour: String = ""

    @SerialName("location")
    var location: String = ""

    @SerialName("destination")
    var destination: String = ""
}
