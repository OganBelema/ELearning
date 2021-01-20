package com.oganbelema.elearning.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.elearning.data.Topic

/**
 * Created by Belema Ogan on 1/20/21.
 */
class TopicAdapter(private val clickListener: (topic: Topic?) -> Unit): RecyclerView.Adapter<TopicItemViewHolder>() {

    private var topics: List<Topic>? = arrayListOf()

    fun setData(topics: List<Topic>?){
        val diffResult = DiffUtil.calculateDiff(TopicDiffUtil(this.topics, topics))
        this.topics = topics
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        return TopicItemViewHolder.from(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return topics?.size ?: 0
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        holder.bind(clickListener, topics?.get(position))
    }
}