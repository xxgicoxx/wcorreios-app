package dev.gico.wcorreios.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.gico.wcorreios.data.dao.CodeDAO
import dev.gico.wcorreios.data.entity.Code
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 3,
    entities = [
        Code::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun correiosDAO(): CodeDAO

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "wcorreios-database"
                )
                .addCallback(AppDatabaseCallback(scope))
                .build()

                INSTANCE = instance

                instance
            }
        }
    }

    private class AppDatabaseCallback(val scope: CoroutineScope): Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { appDatabase ->
                scope.launch {
//                    appDatabase.correiosDAO().insert(Code("TE007302221BR"))
//                    appDatabase.correiosDAO().insert(Code("OZ948193843BR"))
                }
            }
        }
    }
}
