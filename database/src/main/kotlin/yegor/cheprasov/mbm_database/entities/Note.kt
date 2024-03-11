package yegor.cheprasov.mbm_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("body") val body: String,
    @ColumnInfo("isFavorite") val isFavorite: Boolean
)
