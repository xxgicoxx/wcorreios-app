package dev.gico.wcorreios.data.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(@SerialName("httpCode") var httpCode: Int, @SerialName("data") var data: CorrestData? = null) {
    @SerialName("errorMessage") var errorMessage = ""
}
