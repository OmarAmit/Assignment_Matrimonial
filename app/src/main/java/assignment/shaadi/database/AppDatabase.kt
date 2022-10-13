package assignment.shaadi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betaout.GOQii.room.dao.ProfileDao
import assignment.shaadi.database.entity.ProfileEntity

@Database(
    entities = [ProfileEntity::class],
    version = RoomConstants.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    internal abstract fun getProfileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    RoomConstants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}