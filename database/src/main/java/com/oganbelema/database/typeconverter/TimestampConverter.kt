package com.oganbelema.database.typeconverter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Belema Ogan on 1/19/21.
 */
class TimestampConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}