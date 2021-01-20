package com.oganbelema.elearning.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oganbelema.elearning.R
import com.oganbelema.elearning.databinding.SubjectItemBinding
import com.oganbelema.network.model.Subject
import com.oganbelema.network.model.SubjectName


/**
 * Created by Belema Ogan on 1/15/21.
 */
class SubjectItemViewHolder(private val subjectItemBinding: SubjectItemBinding):
    RecyclerView.ViewHolder(subjectItemBinding.root){

    fun bind(clickListener: (subject: Subject?) -> Unit, subject: Subject?){
        subjectItemBinding.subjectName.text = subject?.name

        //decided to use the icons in the design instead
        /*subject?.icon?.let {
            Glide.with(subjectItemBinding.root.context).load(it).into(subjectItemBinding.subjectIcon)
        }*/
        when(subject?.name){
            SubjectName.PHY.subject -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                    R.drawable.phy_card_background)
                subjectItemBinding.subjectIcon.setImageResource(R.drawable.ic_physics_icon)
            }

            SubjectName.MATH.subject -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                R.drawable.math_card_background)
                subjectItemBinding.subjectIcon.setImageResource(R.drawable.ic_math_icon)
            }

            SubjectName.ENG.subject -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                    R.drawable.eng_card_background)
                subjectItemBinding.subjectIcon.setImageResource(R.drawable.ic_english_icon)
            }

            SubjectName.CHEM.subject -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                    R.drawable.chem_card_background)
                subjectItemBinding.subjectIcon.setImageResource(R.drawable.ic_chemistry_icon)
            }

            SubjectName.BIO.subject -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                R.drawable.bio_card_background)
                subjectItemBinding.subjectIcon.setImageResource(R.drawable.ic_biology_icon)
            }

            else -> {
                subjectItemBinding.root.background = ContextCompat.getDrawable(subjectItemBinding.root.context,
                R.drawable.math_card_background)
                subject?.icon?.let {
                    Glide.with(subjectItemBinding.root.context).load(it).into(subjectItemBinding.subjectIcon)
                }
            }
        }


        subjectItemBinding.root.setOnClickListener {
            clickListener(subject)
        }
    }

    companion object {
        fun from(context: Context, parent: ViewGroup): SubjectItemViewHolder {
            return SubjectItemViewHolder(SubjectItemBinding.inflate(
                LayoutInflater.from(context),
                parent, false))
        }
    }
}