package com.oganbelema.elearning.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.network.model.Subject

/**
 * Created by Belema Ogan on 1/15/21.
 */
class SubjectAdapter(private val clickListener: (subject: Subject?) -> Unit):
    RecyclerView.Adapter<SubjectItemViewHolder>() {

    private var subjects: List<Subject>? = arrayListOf()

    fun setData(subjects: List<Subject>?){
        val diffResult = DiffUtil.calculateDiff(SubjectDiffUtil(this.subjects, subjects))
        this.subjects = subjects
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder {
        return SubjectItemViewHolder.from(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return subjects?.size ?: 0
    }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        holder.bind(clickListener, subjects?.get(position))
    }


}