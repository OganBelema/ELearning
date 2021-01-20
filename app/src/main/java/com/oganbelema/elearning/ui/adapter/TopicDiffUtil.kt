package com.oganbelema.elearning.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oganbelema.elearning.data.Topic

class TopicDiffUtil(private val oldList: List<Topic>?, private val newList: List<Topic>?):
        DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.lessonId == newList?.get(newItemPosition)?.lessonId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition) == newList?.get(newItemPosition)
    }
}