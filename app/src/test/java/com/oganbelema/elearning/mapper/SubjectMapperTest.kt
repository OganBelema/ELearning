package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.ChapterEntity
import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.elearning.factory.SubjectFactory
import com.oganbelema.network.model.Chapter
import com.oganbelema.network.model.Lesson
import com.oganbelema.network.model.Subject
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Belema Ogan on 1/19/21.
 */
@RunWith(JUnit4::class)
class SubjectMapperTest {

    private val lessonMapper = LessonMapper()

    private val chapterMapper = ChapterMapper(lessonMapper)

    private val subjectMapper = SubjectMapper(chapterMapper)

    @Test
    fun testFromEntity_returnsSubjectObjectWithSameProperties() {
        val subjectEntity = SubjectFactory.makeSubjectEntity()
        val subject = subjectMapper.fromEntity(subjectEntity)
        //check that mapper returned Subject object
        assertThat(subject, instanceOf(Subject::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(subjectEntity, subject)
    }

    @Test
    fun testToEntity_returnsSubjectEntityObjectWithSameProperties() {
        val subject = SubjectFactory.makeSubject()
        val subjectEntity = subjectMapper.toEntity(subject)
        //check that mapper returned SubjectEntity object
        assertThat(subjectEntity, instanceOf(SubjectEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(subjectEntity, subject)
    }

    @Test
    fun testFromEntityList_returnsListOfSubjectObjectsWithSameProperties() {
        val subjectEntities = listOf(SubjectFactory.makeSubjectEntity())
        val subjects = subjectMapper.fromEntityList(subjectEntities)
        //check that mapper returned list of Subject object
        assertThat(subjects?.get(0), instanceOf(Subject::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(subjectEntities[0], subjects?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfSubjectEntityObjectsWithSameProperties() {
        val subjects = listOf(SubjectFactory.makeSubject())
        val subjectEntities = subjectMapper.toEntityList(subjects)
        //check that mapper returned list of SubjectEntity object
        assertThat(subjectEntities?.get(0), instanceOf(SubjectEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(subjectEntities?.get(0), subjects[0])
    }

    private fun assertEqualData(entity: SubjectEntity?, model: Subject?) {
        assertEquals(entity?.id, model?.id)
        assertEquals(entity?.name, model?.name)
        val chapterEntity = entity?.chapters?.get(0)
        val chapter = model?.chapters?.get(0)
        assertEqualData(chapterEntity, chapter)
    }

    private fun assertEqualData(entity: ChapterEntity?, model: Chapter?) {
        assertEquals(entity?.id, model?.id)
        assertEquals(entity?.name, model?.name)
        val lessonEntity = entity?.lessons?.get(0)
        val lesson = model?.lessons?.get(0)
        assertEqualData(lessonEntity, lesson)
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