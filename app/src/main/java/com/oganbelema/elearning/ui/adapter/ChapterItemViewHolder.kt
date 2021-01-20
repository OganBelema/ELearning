package com.oganbelema.elearning.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.elearning.databinding.ChapterItemBinding
import com.oganbelema.network.model.Chapter
import com.oganbelema.network.model.Lesson

/**
 * Created by Belema Ogan on 1/15/21.
 */
class ChapterItemViewHolder(private val chapterItemBinding: ChapterItemBinding):
    RecyclerView.ViewHolder(chapterItemBinding.root) {

    fun bind(clickListener: (lesson: Lesson?) -> Unit, chapter: Chapter?){
        chapterItemBinding.chapterNameTv.text = chapter?.name ?: ""
        val adapter = LessonAdapter(clickListener)
        adapter.setData(chapter?.lessons)
        chapterItemBinding.lessonsRv.layoutManager = LinearLayoutManager(
            chapterItemBinding.root.context, LinearLayoutManager.HORIZONTAL, false)
        chapterItemBinding.lessonsRv.adapter = adapter
    }

    companion object {
        fun from(context: Context, parent: ViewGroup): ChapterItemViewHolder {
            return ChapterItemViewHolder(
                ChapterItemBinding.inflate(LayoutInflater.from(context), parent,
                    false))
        }
    }
}