package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.ChapterEntity
import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.elearning.factory.ChapterFactory
import com.oganbelema.network.model.Chapter
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
class ChapterMapperTest {

    private val lessonMapper = LessonMapper()

    private val chapterMapper = ChapterMapper(lessonMapper)

    @Test
    fun testFromEntity_returnsChapterObjectWithSameProperties() {
        val chapterEntity = ChapterFactory.makeChapterEntity()
        val chapter = chapterMapper.fromEntity(chapterEntity)
        //check that mapper returned Chapter object
        assertThat(chapter, instanceOf(Chapter::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(chapterEntity, chapter)
    }

    @Test
    fun testToEntity_returnsChapterEntityObjectWithSameProperties() {
        val chapter = ChapterFactory.makeChapter()
        val chapterEntity = chapterMapper.toEntity(chapter)
        //check that mapper returned ChapterEntity object
        assertThat(chapterEntity, instanceOf(ChapterEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(chapterEntity, chapter)
    }

    @Test
    fun testFromEntityList_returnsListOfChapterObjectsWithSameProperties() {
        val chapterEntities = listOf(ChapterFactory.makeChapterEntity())
        val chapters = chapterMapper.fromEntityList(chapterEntities)
        //check that mapper returned list of Chapter object
        assertThat(chapters?.get(0), instanceOf(Chapter::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(chapterEntities[0], chapters?.get(0))
    }

    @Test
    fun testToEntityList_returnsListOfChapterEntityObjectsWithSameProperties() {
        val chapters = listOf(ChapterFactory.makeChapter())
        val chapterEntities = chapterMapper.toEntityList(chapters)
        //check that mapper returned list of ChapterEntity object
        assertThat(chapterEntities?.get(0), instanceOf(ChapterEntity::class.java))
        //check that mapper mapped the properties correctly
        assertEqualData(chapterEntities?.get(0), chapters[0])
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