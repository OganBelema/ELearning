package com.oganbelema.elearning.di.component

import com.oganbelema.elearning.di.scope.FragmentScope
import com.oganbelema.elearning.ui.DashboardFragment
import com.oganbelema.elearning.viewmodel.DashboardViewModelFactory
import dagger.Component

/**
 * Created by Belema Ogan on 20/07/2020.
 */
@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface DashboardComponent {
    fun inject(dashboardFragment: DashboardFragment)
    fun getViewModelFactory(): DashboardViewModelFactory
}