package com.kotlincoroutine.sample1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Album")
data class Album(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val albumId: Int,
    val title: String,
    val url: String = "",
    val thumbnailUrl: String = ""
) : Serializable {
    override fun toString(): String = title
}