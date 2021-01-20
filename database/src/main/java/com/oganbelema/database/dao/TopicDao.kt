package com.oganbelema.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.oganbelema.database.entity.TopicEntity

/**
 * Created by Belema Ogan on 1/19/21.
 */
@Dao
interface TopicDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertTopic(topicEntity: TopicEntity)

    @Query("SELECT * FROM topic ORDER BY date DESC")
    suspend fun topics(): List<TopicEntity>
}