package dev.gico.wcorreios

import android.app.Application
import dev.gico.wcorreios.data.database.AppDatabase
import dev.gico.wcorreios.data.repository.CodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
        AppDatabase.getDatabase(this,applicationScope)
    }

    val codeRepository by lazy {
        CodeRepository(database.correiosDAO())
    }
}
