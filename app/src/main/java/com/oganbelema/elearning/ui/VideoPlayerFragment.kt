package com.oganbelema.elearning.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.oganbelema.elearning.MyApplication
import com.oganbelema.elearning.data.Topic
import com.oganbelema.elearning.databinding.FragmentVideoPlayerBinding
import com.oganbelema.elearning.di.component.DaggerVideoPlayerComponent
import com.oganbelema.elearning.viewmodel.VideoPlayerViewModel
import com.oganbelema.elearning.viewmodel.VideoPlayerViewModelFactory
import com.oganbelema.network.model.Lesson
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class VideoPlayerFragment : Fragment() {

    @Inject
    lateinit var videoplayerviewmodelfactory: VideoPlayerViewModelFactory

    private lateinit var videoPlayerViewModel: VideoPlayerViewModel

    private lateinit var fragmentVideoPlayerBinding: FragmentVideoPlayerBinding

    private var simpleExoPlayer: SimpleExoPlayer? = null

    private lateinit var videoUri: Uri

    private lateinit var progressDialog: CustomProgressDialog

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentVideoPlayerBinding = FragmentVideoPlayerBinding.inflate(layoutInflater, container,
            false)

        return fragmentVideoPlayerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerVideoPlayerComponent.builder().applicationComponent(MyApplication.applicationComponent)
            .build().inject(this)

        videoPlayerViewModel = ViewModelProviders.of(this, videoplayerviewmodelfactory)
            .get(VideoPlayerViewModel::class.java)

        progressDialog = CustomProgressDialog(context)

        val lesson: Lesson? = args.lesson

        val topic = args.topic

        val subjectName = args.subjectName

        val chapterName = args.chapterName

        if (lesson != null) {
            fragmentVideoPlayerBinding.lessonNameTv.text = lesson.name
            fragmentVideoPlayerBinding.chapterNameTv.text = chapterName
            videoUri = Uri.parse(lesson.mediaURL)

            if (subjectName != null && chapterName != null) {
                val topicToSave = Topic(lesson.id, lesson.name, subjectName, chapterName,
                    lesson.mediaURL, Date())
                videoPlayerViewModel.saveTopic(topicToSave)
            }
        } else if (topic != null) {
            fragmentVideoPlayerBinding.lessonNameTv.text = topic.lessonName
            fragmentVideoPlayerBinding.chapterNameTv.text = topic.chapterName
            videoUri = Uri.parse(topic.lessonMediaUrl)
            topic.date = Date()
            videoPlayerViewModel.saveTopic(topic)
        }


        val loadControl: LoadControl = DefaultLoadControl()

        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()

        val trackSelector: TrackSelector = DefaultTrackSelector(AdaptiveTrackSelection
            .Factory(bandwidthMeter))

        simpleExoPlayer = ExoPlayerFactory
            .newSimpleInstance(context, trackSelector, loadControl)

        val dataSourceFactory: DefaultHttpDataSourceFactory = DefaultHttpDataSourceFactory(
            "exoplayer_video")

        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()

        val mediaSource: MediaSource = ExtractorMediaSource(videoUri, dataSourceFactory,
            extractorsFactory, null, null)

        fragmentVideoPlayerBinding.playerView.player = simpleExoPlayer

        fragmentVideoPlayerBinding.playerView.keepScreenOn = true

        fragmentVideoPlayerBinding.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT)
        simpleExoPlayer?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING

        simpleExoPlayer?.prepare(mediaSource)

        simpleExoPlayer?.playWhenReady = true

        simpleExoPlayer?.addListener(object : Player.EventListener {
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {

            }

            override fun onSeekProcessed() {

            }

            override fun onTracksChanged(trackGroups: TrackGroupArray?,
                                         trackSelections: TrackSelectionArray?) {

            }

            override fun onPlayerError(error: ExoPlaybackException?) {

            }

            override fun onLoadingChanged(isLoading: Boolean) {

            }

            override fun onPositionDiscontinuity(reason: Int) {

            }

            override fun onRepeatModeChanged(repeatMode: Int) {

            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

            }

            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING){
                    progressDialog.show()
                } else if (playbackState == Player.STATE_READY){
                    progressDialog.dismiss()
                }
            }

        })


        fragmentVideoPlayerBinding.backArrowIv.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer?.playWhenReady = false
        simpleExoPlayer?.playbackState
    }

    override fun onResume() {
        super.onResume()
        simpleExoPlayer?.playWhenReady = true
        simpleExoPlayer?.playbackState
    }


}