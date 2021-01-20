package com.oganbelema.elearning.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.network.model.Chapter
import com.oganbelema.network.model.Lesson

/**
 * Created by Belema Ogan on 1/15/21.
 */
class ChapterAdapter(private val clickListener: (lesson: Lesson?, chapterName: String?) -> Unit):
    RecyclerView.Adapter<ChapterItemViewHolder>() {

    private var chapters: List<Chapter>? = arrayListOf()

    fun setData(chapters: List<Chapter>?){
        val diffResult = DiffUtil.calculateDiff(ChapterDiffUtil(this.chapters, chapters))
        this.chapters = chapters
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterItemViewHolder {
        return ChapterItemViewHolder.from(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return chapters?.size ?: 0
    }

    override fun onBindViewHolder(holder: ChapterItemViewHolder, position: Int) {
        holder.bind(clickListener, chapters?.get(position))
    }
}