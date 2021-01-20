package com.oganbelema.database.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oganbelema.database.entity.ChapterEntity
import java.lang.reflect.Type

/**
 * Created by Belema Ogan on 1/17/21.
 */
class ChapterTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<ChapterEntity?>? {
        val listType: Type =
            object : TypeToken<List<ChapterEntity?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<ChapterEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}