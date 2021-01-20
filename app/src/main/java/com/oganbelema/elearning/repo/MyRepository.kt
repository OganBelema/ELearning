package com.oganbelema.elearning.repo

import com.oganbelema.database.AppDatabase
import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.network.source.NetworkSource
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/15/21.
 */
class MyRepository @Inject constructor(private val networkSource: NetworkSource,
                                       private val databaseSource: AppDatabase) {

    suspend fun insertSubjects(subjects: List<SubjectEntity>) =
        databaseSource.subjectDao().insertSubjects(subjects)

    suspend fun getDataFromNetwork() = networkSource.fetchData()

    suspend fun getSubjects() = databaseSource.subjectDao().subjects()

    suspend fun getTopics() = databaseSource.topicDao().topics()

    suspend fun insertTopic(topicEntity: TopicEntity) =
        databaseSource.topicDao().insertTopic(topicEntity)


}