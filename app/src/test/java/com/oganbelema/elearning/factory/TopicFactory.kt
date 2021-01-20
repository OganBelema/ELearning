package com.oganbelema.elearning.factory

import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.elearning.data.Topic


/**
 * Created by Belema Ogan on 1/20/21.
 */
object TopicFactory {

    fun makeTopicEntity(): TopicEntity {
        return with(DataFactory){
            TopicEntity(randomLong(), randomString(), randomString(),
                randomString(), currentDate())
        }
    }

    fun makeTopic(): Topic {
        return  with(DataFactory){
            Topic(randomLong(), randomString(), randomString(),
                randomString(), currentDate())
        }
    }
}