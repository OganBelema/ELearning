package com.oganbelema.elearning.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oganbelema.elearning.databinding.FragmentSubjectBinding
import com.oganbelema.elearning.ui.adapter.ChapterAdapter


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class SubjectFragment : Fragment() {

    private lateinit var fragmentSubjectBinding: FragmentSubjectBinding

    private lateinit var chapterAdapter: ChapterAdapter

    private val args: SubjectFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSubjectBinding = FragmentSubjectBinding.inflate(inflater, container,
            false)

        return fragmentSubjectBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subject = args.subject

        chapterAdapter = ChapterAdapter { lesson ->
            findNavController().navigate(SubjectFragmentDirections
                .actionSubjectFragmentToVideoPlayerFragment(lesson, subject?.name))
        }

        fragmentSubjectBinding.chaptersRv.layoutManager = LinearLayoutManager(context)
        fragmentSubjectBinding.chaptersRv.adapter = chapterAdapter

        subject?.let {
            fragmentSubjectBinding.subjectNameTv.text = it.name
            chapterAdapter.setData(it.chapters)
        }

        fragmentSubjectBinding.backArrowIv.setOnClickListener {
            findNavController().popBackStack()
        }

    }


}