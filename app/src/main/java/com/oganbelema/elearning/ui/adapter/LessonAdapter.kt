package com.oganbelema.elearning.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.network.model.Lesson

/**
 * Created by Belema Ogan on 1/15/21.
 */
class LessonAdapter(private val clickListener: (lesson: Lesson?, chapterName: String?) -> Unit,
                    private val chapterName: String?): RecyclerView.Adapter<LessonItemViewHolder>() {

    private var lessons: List<Lesson>? = arrayListOf()

    fun setData(lessons: List<Lesson>?){
        val diffResult = DiffUtil.calculateDiff(LessonDiffUtil(this.lessons, lessons))
        this.lessons = lessons
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        return LessonItemViewHolder.from(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return lessons?.size ?: 0
    }

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {
        holder.bind(clickListener, lessons?.get(position), chapterName)
    }
}