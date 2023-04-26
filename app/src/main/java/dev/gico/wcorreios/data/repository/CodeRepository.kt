package dev.gico.wcorreios.data.repository

import androidx.annotation.WorkerThread
import dev.gico.wcorreios.data.dao.CodeDAO
import dev.gico.wcorreios.data.entity.Code
import kotlinx.coroutines.flow.Flow

class CodeRepository(private val codeDAO: CodeDAO) {

    val codes:Flow<MutableList<Code>> = codeDAO.findAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(code: Code){
        codeDAO.insert(code)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteById(code: String){
        codeDAO.deleteById(code)
    }
}
