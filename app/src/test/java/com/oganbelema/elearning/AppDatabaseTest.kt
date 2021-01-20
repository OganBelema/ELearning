package com.oganbelema.elearning

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oganbelema.database.AppDatabase
import com.oganbelema.database.dao.SubjectDao
import com.oganbelema.database.dao.TopicDao
import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.elearning.factory.SubjectFactory
import com.oganbelema.elearning.factory.TopicFactory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Belema Ogan on 1/20/21.
 */
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var appDatabase: AppDatabase //the db instance
    private lateinit var subjectDao: SubjectDao //the subject dao
    private lateinit var topicDao: TopicDao //the topic dao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()

        subjectDao = appDatabase.subjectDao()

        topicDao = appDatabase.topicDao()
    }

    @Test
    fun testSubjectDaoOperations() = runBlocking {
        //assertThat subject table has no data
        assertEquals(emptyList<SubjectEntity>(), subjectDao.subjects())

        //create sample subjectEntity
        val subjectEntity = SubjectFactory.makeSubjectEntity()

        //create SubjectEntity list and insert sample
        val subjectEntities = listOf(subjectEntity)

        //insert sample SubjectEntity list
        subjectDao.insertSubjects(subjectEntities)

        //fetch saved entity list from DB
        val subjectEntitiesFromDb = subjectDao.subjects()

        assertEquals(subjectEntity, subjectEntitiesFromDb[0])

    }

    @Test
    fun testTopicDaoOperations() = runBlocking {
        //assertThat topic table has no data
        assertEquals(emptyList<TopicEntity>(), topicDao.topics())

        //create sample topicEntities
        val topicEntity1 = TopicFactory.makeTopicEntity()
        val topicEntity2 = TopicFactory.makeTopicEntity()

        //insert sample TopicEntity
        topicDao.insertTopic(topicEntity1)

        //fetch saved entity list from DB
        var topicEntitiesFromDb = topicDao.topics()

        //confirm that fetched data was inserted data
        assertEquals(topicEntity1, topicEntitiesFromDb[0])

        topicDao.insertTopic(topicEntity2)

        //fetch saved entity list from DB
        topicEntitiesFromDb = topicDao.topics()

        //confirm that data is fetched in latest date order
        assertEquals(topicEntity2, topicEntitiesFromDb[0])
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}