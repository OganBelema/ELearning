package com.oganbelema.elearning.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.oganbelema.network.model.Chapter

class ChapterDiffUtil(private val oldList: List<Chapter>?, private val newList: List<Chapter>?):
        DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id == newList?.get(newItemPosition)?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition) == newList?.get(newItemPosition)
    }
}