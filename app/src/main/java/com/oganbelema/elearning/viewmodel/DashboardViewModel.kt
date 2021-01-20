package com.oganbelema.elearning.viewmodel

import androidx.lifecycle.*
import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.elearning.data.Topic
import com.oganbelema.elearning.mapper.SubjectMapper
import com.oganbelema.elearning.mapper.TopicMapper
import com.oganbelema.elearning.repo.MyRepository
import com.oganbelema.network.model.NetworkResponse
import com.oganbelema.network.model.Subject
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

/**
 * Created by Belema Ogan on 1/15/21.
 */
class DashboardViewModel(private val repository: MyRepository,
                         private val subjectMapper: SubjectMapper,
                         private val topicMapper: TopicMapper): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _data = MutableLiveData<List<Subject>>()
    val data: LiveData<List<Subject>> = _data

    private val _topics = MutableLiveData<List<Topic>>()
    val topics: LiveData<List<Topic>> = _topics

    private var topicList: List<Topic>? = listOf()

    private val _isExpanded = MutableLiveData<Boolean>()
    val isExpanded: LiveData<Boolean> = _isExpanded


    fun getData(isOnline: Boolean){
        _isLoading.value = true

        viewModelScope.launch {
            try {
                if (isOnline){
                    //get data from remote source
                    val networkResponse = makeRemoteRequest()
                    val subjects = networkResponse.data.subjects

                    //map list of subjects to list of subjectEntities
                    val subjectEntities = mapToSubjectEntity(subjects)

                    //save to database
                    saveSubjectEntityInDatabase(subjectEntities)
                }

                //fetch from database
                fetchSubjectFromDatabase()

                _isLoading.value = false
            } catch (exception: Exception){
                _isLoading.value = false
                _error.value = exception
                Timber.d(exception)
            }

        }

    }

    private suspend fun fetchSubjectFromDatabase() {
        val subjects = repository.getSubjects()
        _data.postValue(mapToSubject(subjects))
    }

    fun mapToSubject(subjects: List<SubjectEntity>): List<Subject>? {
        return subjectMapper.fromEntityList(subjects)
    }

    private suspend fun saveSubjectEntityInDatabase(subjectEntities: List<SubjectEntity>?) {
        if (subjectEntities != null) {
            repository.insertSubjects(subjectEntities)
        }
    }

    fun mapToSubjectEntity(subjects: List<Subject>): List<SubjectEntity>? {
        return subjectMapper.toEntityList(subjects)
    }

    private suspend fun makeRemoteRequest(): NetworkResponse {
        return repository.getDataFromNetwork()
    }

    fun getRecentlyWatched(){
        viewModelScope.launch {
            val topicEntities = repository.getTopics()
            val topics = mapToTopic(topicEntities)
            topicList = topics
            collapse()
        }
    }

    fun mapToTopic(topicEntities: List<TopicEntity>): List<Topic>? {
        return topicMapper.fromEntityList(topicEntities)
    }

    fun expand(){
        _isExpanded.value = true
        if (topicList != null){
            _topics.value = topicList
        }
    }

    fun collapse(){
        _isExpanded.value = false
        if (topicList != null && topicList!!.size > 2){
            _topics.value = topicList?.take(2)
        } else {
            _topics.value = topicList
        }
    }


}