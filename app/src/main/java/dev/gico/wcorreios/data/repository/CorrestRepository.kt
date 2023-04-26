package dev.gico.wcorreios.data.repository

import dev.gico.wcorreios.data.service.CorrestService

class CorrestRepository constructor(private val retrofitService: CorrestService) {

    fun getTrack(code: String) = retrofitService.getTrack(code)
}
