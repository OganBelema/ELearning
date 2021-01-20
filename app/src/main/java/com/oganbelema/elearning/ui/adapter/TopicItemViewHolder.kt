package com.oganbelema.elearning.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oganbelema.elearning.R
import com.oganbelema.elearning.data.Topic
import com.oganbelema.elearning.databinding.TopicItemBinding
import com.oganbelema.network.model.SubjectName

/**
 * Created by Belema Ogan on 1/20/21.
 */
class TopicItemViewHolder(private val topicItemBinding: TopicItemBinding):
    RecyclerView.ViewHolder(topicItemBinding.root) {

    fun bind(clickListener: (topic: Topic?) -> Unit, topic: Topic?){
        with(topicItemBinding){
            topicImage.clipToOutline = true
            subjectNameTv.text = topic?.subjectName ?: ""
            topicName.text = topic?.lessonName ?: ""
            Glide.with(root.context).load(topic?.lessonMediaUrl)
                .placeholder(R.drawable.rotate_spinner)
                .into(topicImage)

            when(topic?.subjectName){
                SubjectName.MATH.subject -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                        R.color.math_color))
                }

                SubjectName.BIO.subject -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                        R.color.biology_color))
                }

                SubjectName.CHEM.subject -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                        R.color.chemistry_color))
                }

                SubjectName.ENG.subject -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                    R.color.english_color))
                }

                SubjectName.PHY.subject -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                    R.color.physics_color))
                }

                else -> {
                    subjectNameTv.setTextColor(ContextCompat.getColor(root.context,
                        R.color.text_black))
                }
            }

            root.setOnClickListener {
                clickListener(topic)
            }
        }

    }

    companion object {
        fun from(context: Context, parent: ViewGroup): TopicItemViewHolder {
            return TopicItemViewHolder(
                TopicItemBinding.inflate(LayoutInflater.from(context), parent,
                    false))
        }
    }
}