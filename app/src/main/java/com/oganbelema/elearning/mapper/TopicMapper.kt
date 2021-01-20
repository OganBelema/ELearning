package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.elearning.data.Topic
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/20/21.
 */
class TopicMapper @Inject constructor(): EntityMapper<TopicEntity, Topic> {

    override fun fromEntity(entity: TopicEntity): Topic {
        return Topic(entity.lessonId, entity.lessonName, entity.subjectName, entity.chapterName,
            entity.lessonMediaUrl, entity.date)
    }

    override fun toEntity(model: Topic): TopicEntity {
        return TopicEntity(model.lessonId, model.lessonName, model.subjectName, model.chapterName,
            model.lessonMediaUrl, model.date)
    }

    override fun fromEntityList(entities: List<TopicEntity>?): List<Topic>? {
        return if (entities != null) {
            val topics = ArrayList<Topic>()
            for (topicEntity in entities) {
                topics.add(fromEntity(topicEntity))
            }
            topics
        } else {
            null
        }
    }

    override fun toEntityList(models: List<Topic>?): List<TopicEntity>? {
        return if (models != null) {
            val topicEntities = ArrayList<TopicEntity>()
            for (topic in models) {
                topicEntities.add(toEntity(topic))
            }
            topicEntities
        } else {
            null
        }
    }
}