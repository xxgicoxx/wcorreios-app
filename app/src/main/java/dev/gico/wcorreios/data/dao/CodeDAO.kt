package dev.gico.wcorreios.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.gico.wcorreios.data.entity.Code
import kotlinx.coroutines.flow.Flow

@Dao
interface CodeDAO {

    @Query("SELECT * FROM Code")
    fun findAll(): Flow<MutableList<Code>>

    @Query("SELECT * FROM Code WHERE code IN (:codes)")
    fun findByCodes(codes: List<String>): Flow<MutableList<Code>>

    @Insert
    suspend fun insert(vararg code: Code)

    @Update
    suspend fun update(code: Code)

    @Delete
    suspend fun delete(code: Code)

    @Query("DELETE FROM Code WHERE code = :code")
    suspend fun deleteById(code: String)
}
