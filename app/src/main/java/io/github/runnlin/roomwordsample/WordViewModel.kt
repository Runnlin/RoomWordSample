package io.github.runnlin.roomwordsample

import androidx.lifecycle.*
import io.github.runnlin.roomwordsample.data.Word
import io.github.runnlin.roomwordsample.data.WordRepository
import kotlinx.coroutines.launch


/**
 * 提供访问数据层所用的方法，并返回 LiveData，以便 MainActivity 可以设置观察者关系
 */
class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    // 让界面组件的自动更新得以实现。通过调用 flow.toLiveData() 从 Flow 转换成 LiveData
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

//    val newWord: LiveData<Word> = repository.newWord.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}