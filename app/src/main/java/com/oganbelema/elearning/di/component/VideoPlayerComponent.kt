package com.oganbelema.elearning.di.component

import com.oganbelema.elearning.di.scope.FragmentScope
import com.oganbelema.elearning.ui.VideoPlayerFragment
import com.oganbelema.elearning.viewmodel.VideoPlayerViewModelFactory
import dagger.Component

/**
 * Created by Belema Ogan on 20/07/2020.
 */
@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface VideoPlayerComponent {
    fun inject(videoPlayerFragment: VideoPlayerFragment)
    fun getViewModelFactory(): VideoPlayerViewModelFactory
}