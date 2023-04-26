package dev.gico.wcorreios.data.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CorrestData(@SerialName("code") var code: String) {

    @SerialName("events")
    var events: List<CorrestEvent> = ArrayList()
}
