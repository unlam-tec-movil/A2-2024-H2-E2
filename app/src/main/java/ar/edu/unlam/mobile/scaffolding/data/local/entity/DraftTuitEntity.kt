package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_tuits")
data class DraftTuitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String,
    val lastModified: Long,
    val userEmail: String,
)
