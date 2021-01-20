package com.oganbelema.database.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oganbelema.database.entity.LessonEntity
import java.lang.reflect.Type


/**
 * Created by Belema Ogan on 1/17/21.
 */
class LessonTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<LessonEntity?>? {
        val listType: Type =
            object : TypeToken<List<LessonEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<LessonEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}