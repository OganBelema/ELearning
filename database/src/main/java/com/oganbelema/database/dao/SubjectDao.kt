package com.oganbelema.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.oganbelema.database.entity.SubjectEntity

/**
 * Created by Belema Ogan on 1/17/21.
 */
@Dao
interface SubjectDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertSubjects(subjects: List<SubjectEntity>)

    @Query("SELECT * FROM subject")
    suspend fun subjects(): List<SubjectEntity>
}