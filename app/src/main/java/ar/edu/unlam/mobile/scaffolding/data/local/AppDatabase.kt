package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.dao.DraftTuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.dao.FavoriteUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.entity.DraftTuitEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity

@Database(
    entities = [FavoriteUserEntity::class, DraftTuitEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteUserDao(): FavoriteUserDao
    abstract fun draftTuitDao(): DraftTuitDao
}
