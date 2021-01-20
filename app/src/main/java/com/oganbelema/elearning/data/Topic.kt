package com.oganbelema.elearning.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Belema Ogan on 1/20/21.
 */
@Parcelize
data class Topic (
    val lessonId: Long,
    val lessonName: String,
    val subjectName: String,
    val chapterName: String,
    val lessonMediaUrl: String,
    var date: Date
): Parcelable