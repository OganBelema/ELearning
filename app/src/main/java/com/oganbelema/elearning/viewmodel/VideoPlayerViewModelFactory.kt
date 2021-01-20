package com.oganbelema.elearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oganbelema.elearning.mapper.TopicMapper
import com.oganbelema.elearning.repo.MyRepository
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/20/21.
 */
class VideoPlayerViewModelFactory @Inject constructor(
    private val repository: MyRepository, private val topicMapper: TopicMapper):
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoPlayerViewModel(repository, topicMapper) as T
    }
}