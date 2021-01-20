package com.oganbelema.elearning.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oganbelema.elearning.R
import com.oganbelema.elearning.databinding.LessonItemBinding
import com.oganbelema.network.model.Lesson

/**
 * Created by Belema Ogan on 1/15/21.
 */
class LessonItemViewHolder(private val lessonItemBinding: LessonItemBinding):
    RecyclerView.ViewHolder(lessonItemBinding.root) {

    fun bind(clickListener: (lesson: Lesson?, chapterName: String?) -> Unit,lesson: Lesson?,
             chapterName: String?){
        lessonItemBinding.lessonNameTv.text = lesson?.name ?: ""
        Glide.with(lessonItemBinding.root.context).load(lesson?.mediaURL)
            .placeholder(R.drawable.rotate_spinner)
            .into(lessonItemBinding.lessonIconIv)
        lessonItemBinding.root.setOnClickListener {
            clickListener(lesson, chapterName)
        }
    }

    companion object {
        fun from(context: Context, parent: ViewGroup): LessonItemViewHolder {
            return LessonItemViewHolder(
                LessonItemBinding.inflate(LayoutInflater.from(context), parent,
                    false))
        }
    }
}