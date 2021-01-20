package com.oganbelema.elearning.viewmodel

import com.oganbelema.elearning.factory.SubjectFactory
import com.oganbelema.elearning.factory.TopicFactory
import com.oganbelema.elearning.mapper.SubjectMapper
import com.oganbelema.elearning.mapper.TopicMapper
import com.oganbelema.elearning.repo.MyRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by Belema Ogan on 1/19/21.
 */
class DashboardViewModelTest {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var subjectMapper: SubjectMapper

    private lateinit var topicMapper: TopicMapper

    @Before
    fun setup() {
        val repository = mock(MyRepository::class.java)

        subjectMapper = mock(SubjectMapper::class.java)

        topicMapper = mock(TopicMapper::class.java)

        dashboardViewModel = DashboardViewModel(repository, subjectMapper, topicMapper)
    }

    @Test
    fun mapToSubjectEntity_callsSubjectMapper_toEntityList(){
        val subjects = listOf(SubjectFactory.makeSubject())

        dashboardViewModel.mapToSubjectEntity(subjects)

        verify(subjectMapper).toEntityList(subjects)
    }

    @Test
    fun mapToSubject_callsSubjectMapper_fromEntityList(){
        val subjectEntities = listOf(SubjectFactory.makeSubjectEntity())

        dashboardViewModel.mapToSubject(subjectEntities)

        verify(subjectMapper).fromEntityList(subjectEntities)
    }

    @Test
    fun mapToTopic_callsTopicMapper_fromEntityList(){
        val topicEntities = listOf(TopicFactory.makeTopicEntity())

        dashboardViewModel.mapToTopic(topicEntities)

        verify(topicMapper).fromEntityList(topicEntities)
    }


}