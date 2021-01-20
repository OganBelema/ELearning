package com.oganbelema.elearning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oganbelema.elearning.mapper.SubjectMapper
import com.oganbelema.elearning.mapper.TopicMapper
import com.oganbelema.elearning.repo.MyRepository
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/15/21.
 */
class DashboardViewModelFactory @Inject constructor(
    private val repository: MyRepository, private val subjectMapper: SubjectMapper,
    private val topicMapper: TopicMapper): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository, subjectMapper, topicMapper) as T
    }
}