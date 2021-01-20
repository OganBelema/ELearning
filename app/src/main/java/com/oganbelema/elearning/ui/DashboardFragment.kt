package com.oganbelema.elearning.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.oganbelema.elearning.MyApplication
import com.oganbelema.elearning.R
import com.oganbelema.elearning.databinding.FragmentDashboardBinding
import com.oganbelema.elearning.di.component.DaggerDashboardComponent
import com.oganbelema.elearning.ui.adapter.SubjectAdapter
import com.oganbelema.elearning.ui.adapter.TopicAdapter
import com.oganbelema.elearning.util.NetworkUtil
import com.oganbelema.elearning.viewmodel.DashboardViewModel
import com.oganbelema.elearning.viewmodel.DashboardViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private lateinit var fragmentDashboardBinding: FragmentDashboardBinding

    @Inject
    lateinit var dashboardViewModelFactory: DashboardViewModelFactory


    private lateinit var networkUtil: NetworkUtil

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var progressDialog: CustomProgressDialog




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentDashboardBinding = FragmentDashboardBinding.inflate(layoutInflater, container,
            false)

        return fragmentDashboardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerDashboardComponent.builder().applicationComponent(MyApplication.applicationComponent)
            .build().inject(this)

        progressDialog = CustomProgressDialog(context)

        networkUtil = NetworkUtil(context)

        val subjectAdapter = SubjectAdapter { subject ->
            findNavController().navigate(DashboardFragmentDirections
                .actionDashboardFragmentToSubjectFragment(subject))
        }

        val topicAdapter = TopicAdapter { topic ->
            findNavController().navigate(DashboardFragmentDirections
                .actionDashboardFragmentToVideoPlayerFragment(null, topic?.subjectName, topic))
        }

        fragmentDashboardBinding.subjectRv.layoutManager = GridLayoutManager(context, 2)
        fragmentDashboardBinding.subjectRv.adapter = subjectAdapter

        fragmentDashboardBinding.recentlyWatchedRv.layoutManager = LinearLayoutManager(context)
        fragmentDashboardBinding.recentlyWatchedRv.adapter = topicAdapter

        fragmentDashboardBinding.button.setOnClickListener {
            if (::dashboardViewModel.isInitialized){
                if (dashboardViewModel.isExpanded.value == true){
                    dashboardViewModel.collapse()
                } else {
                    dashboardViewModel.expand()
                }
            }
        }

        dashboardViewModel = ViewModelProviders.of(this, dashboardViewModelFactory)
            .get(DashboardViewModel::class.java)

        dashboardViewModel.getData(networkUtil.isConnected())

        dashboardViewModel.getRecentlyWatched()

        dashboardViewModel.data.observe(viewLifecycleOwner, Observer { networkResponse ->
            networkResponse?.let {
                if (it.isEmpty()){
                    fragmentDashboardBinding.noSubjectsTv.visibility = View.VISIBLE
                } else {
                    subjectAdapter.setData(it)
                    fragmentDashboardBinding.noSubjectsTv.visibility = View.GONE
                }
            }
        })

        dashboardViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading){
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })

        dashboardViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Snackbar.make(fragmentDashboardBinding.root, it.localizedMessage,
                    Snackbar.LENGTH_LONG).show()
            }
        })

        dashboardViewModel.topics.observe(viewLifecycleOwner, Observer { topics ->
            topics?.let {
                topicAdapter.setData(it)
            }
        })

        dashboardViewModel.isExpanded.observe(viewLifecycleOwner, Observer { isExpanded ->
            if (isExpanded){
                fragmentDashboardBinding.buttonText.text = getString(R.string.show_less)
            } else {
                fragmentDashboardBinding.buttonText.text = getString(R.string.view_all)
            }
        })

    }

}