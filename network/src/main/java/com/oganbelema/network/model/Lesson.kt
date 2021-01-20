package com.oganbelema.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Belema Ogan on 1/14/21.
 */
@Parcelize
data class Lesson (
    val id: Long,
    val name: String,
    val icon: String,

    @SerializedName("media_url")
    val mediaURL: String,

    @SerializedName("subject_id")
    val subjectID: Long,

    @SerializedName("chapter_id")
    val chapterID: Long
): Parcelable