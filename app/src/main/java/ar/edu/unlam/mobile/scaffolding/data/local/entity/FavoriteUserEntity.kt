package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUserEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val name: String,
    val avatarUrl: String,
)
