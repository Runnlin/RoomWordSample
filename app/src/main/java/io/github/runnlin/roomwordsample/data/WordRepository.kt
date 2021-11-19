package io.github.runnlin.roomwordsample.data

import androidx.annotation.WorkerThread
import io.github.runnlin.roomwordsample.data.Word
import io.github.runnlin.roomwordsample.data.WordDao
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

/**
 * 管理一个或多个数据源。
 * 用于提供 ViewModel 与底层数据提供程序交互的方法。
 * 在此应用中，后端是一个 Room 数据库
 */
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

//    var newWord: Flow<Word> = wordDao.getLastestWord()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll()
    }
}