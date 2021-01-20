package com.oganbelema.elearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oganbelema.elearning.data.Topic
import com.oganbelema.elearning.mapper.TopicMapper
import com.oganbelema.elearning.repo.MyRepository
import kotlinx.coroutines.launch

/**
 * Created by Belema Ogan on 1/20/21.
 */
class VideoPlayerViewModel(private val repository: MyRepository,
                           private val topicMapper: TopicMapper): ViewModel() {

    fun saveTopic(topic: Topic){
        val topicEntity = topicMapper.toEntity(topic)
        viewModelScope.launch {
            repository.insertTopic(topicEntity)
        }
    }
}