package com.oganbelema.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Belema Ogan on 1/14/21.
 */
@Parcelize
data class Subject (
    val id: Long,
    val name: String,
    val icon: String,
    val chapters: List<Chapter>
): Parcelable