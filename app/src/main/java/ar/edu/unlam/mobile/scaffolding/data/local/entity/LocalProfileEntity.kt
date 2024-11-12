package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class LocalProfileEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 1,
    val name: String,
    val avatarUrl: String,
    val email: String
)
