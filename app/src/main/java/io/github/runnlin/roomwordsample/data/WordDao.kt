package io.github.runnlin.roomwordsample.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


/**
 * 将方法映射到数据库查询，以便在存储中调用 getAlphabetizedWords() 等方法时，Room可以执行
 * "SELECT * FROM word_table ORDER BY word ASC"
 */
@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

//    @Query("SELECT * FROM word_table ORDER BY word DESC LIMIT 1;")
//    fun getLastestWord(): Flow<Word>

    /**
     * 如果希望在数据库发生变化时接受通知，DAO可以提供适用于单发请求的 suspend 查询以及 Flow 查询
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}