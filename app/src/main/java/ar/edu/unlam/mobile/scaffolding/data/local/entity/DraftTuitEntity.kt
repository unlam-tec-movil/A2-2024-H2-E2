package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft_tuit")
data class DraftTuitEntity(
    @PrimaryKey(autoGenerate = true) val draftId: Int = 0,
    val message: String,
    val lastModified: String,
)
