package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.elearning.factory.LessonFactory
import com.oganbelema.network.model.Lesson
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Belema Ogan on 1/18/21.
 */
@RunWith(JUnit4::class)
class LessonMapperTest {

    private val lessonMapper = LessonMapper()

    @Test
    fun testFromEntity_returnsLessonObjectWithSameProperties() {
        val lessonEntity = LessonFactory.makeLessonEntity()
        val lesson = lessonMapper.fromEntity(lessonEntity)
        //check that mapper returned Lesson object
        assertThat(lesson, instanceOf(Lesson::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(lessonEntity, lesson)
    }

    @Test
    fun testToEntity_returnsLessonEntityObjectWithSameProperties() {
        val lesson = LessonFactory.makeLesson()
        val lessonEntity = lessonMapper.toEntity(lesson)
        //check that mapper returned LessonEntity object
        assertThat(lessonEntity, instanceOf(LessonEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(lessonEntity, lesson)
    }

    @Test
    fun testFromEntityList_returnsListOfLessonObjectsWithSameProperties() {
        val lessonEntities = listOf(LessonFactory.makeLessonEntity())
        val lessons = lessonMapper.fromEntityList(lessonEntities)
        //check that mapper returned list of Lesson object
        assertThat(lessons?.get(0), instanceOf(Lesson::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(lessonEntities[0], lessons?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfLessonEntityObjectsWithSameProperties() {
        val lessons = listOf(LessonFactory.makeLesson())
        val lessonEntities = lessonMapper.toEntityList(lessons)
        //check that mapper returned list of LessonEntity object
        assertThat(lessonEntities?.get(0), instanceOf(LessonEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(lessonEntities?.get(0), lessons[0])
    }

    private fun assertEqualData(entity: LessonEntity?, model: Lesson?) {
        assertEquals(entity?.id, model?.id)
        assertEquals(entity?.name, model?.name)
        assertEquals(entity?.icon, model?.icon)
        assertEquals(entity?.mediaURL, model?.mediaURL)
        assertEquals(entity?.chapterID, model?.chapterID)
        assertEquals(entity?.subjectID, model?.subjectID)
    }
}