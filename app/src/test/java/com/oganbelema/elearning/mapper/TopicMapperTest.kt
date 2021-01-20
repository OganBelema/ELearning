package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.elearning.data.Topic
import com.oganbelema.elearning.factory.TopicFactory
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Belema Ogan on 1/20/21.
 */
@RunWith(JUnit4::class)
class TopicMapperTest {

    private val topicMapper = TopicMapper()

    @Test
    fun testFromEntity_returnsTopicObjectWithSameProperties() {
        val topicEntity = TopicFactory.makeTopicEntity()
        val topic = topicMapper.fromEntity(topicEntity)
        //check that mapper returned Topic object
        assertThat(topic, instanceOf(Topic::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(topicEntity, topic)
    }

    @Test
    fun testToEntity_returnsTopicEntityObjectWithSameProperties() {
        val topic = TopicFactory.makeTopic()
        val topicEntity = topicMapper.toEntity(topic)
        //check that mapper returned TopicEntity object
        assertThat(topicEntity, instanceOf(TopicEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(topicEntity, topic)
    }

    @Test
    fun testFromEntityList_returnsListOfTopicObjectsWithSameProperties() {
        val topicEntities = listOf(TopicFactory.makeTopicEntity())
        val topics = topicMapper.fromEntityList(topicEntities)
        //check that mapper returned list of Topic object
        assertThat(topics?.get(0), instanceOf(Topic::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(topicEntities[0], topics?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfTopicEntityObjectsWithSameProperties() {
        val topics = listOf(TopicFactory.makeTopic())
        val topicEntities = topicMapper.toEntityList(topics)
        //check that mapper returned list of TopicEntity object
        assertThat(topicEntities?.get(0), instanceOf(TopicEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(topicEntities?.get(0), topics[0])
    }

    private fun assertEqualData(entity: TopicEntity?, model: Topic?) {
        assertEquals(entity?.lessonId, model?.lessonId)
        assertEquals(entity?.lessonName, model?.lessonName)
        assertEquals(entity?.lessonMediaUrl, model?.lessonMediaUrl)
        assertEquals(entity?.subjectName, model?.subjectName)
        assertEquals(entity?.date, model?.date)
    }
}