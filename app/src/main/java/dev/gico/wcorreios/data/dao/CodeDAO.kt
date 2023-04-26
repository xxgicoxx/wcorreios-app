package dev.gico.wcorreios.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.gico.wcorreios.data.entity.Code
import kotlinx.coroutines.flow.Flow

@Dao
interface CodeDAO {

    @Query("SELECT * FROM Code")
    fun findAll(): Flow<MutableList<Code>>

    @Insert
    suspend fun insert(vararg code: Code)

    @Query("DELETE FROM Code WHERE code = :code")
    suspend fun deleteById(code: String)
}
