package io.github.runnlin.roomwordsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room：一个封装了 SQLite 数据库的容器
 * Word: 包含单个字词的实体类
 */
@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name="word") val word: String)
