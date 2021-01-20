package com.oganbelema.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Belema Ogan on 1/14/21.
 */
@Parcelize
data class Chapter (
    val id: Long,
    val name: String,
    val lessons: List<Lesson>
): Parcelable