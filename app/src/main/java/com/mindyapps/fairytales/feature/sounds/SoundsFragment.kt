package com.mindyapps.fairytales.feature.sounds

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mindyapps.fairytales.R
import com.mindyapps.fairytales.base.presentation.BaseFragment
import com.mindyapps.fairytales.core.observe
import com.mindyapps.fairytales.databinding.FragmentSoundsBinding

class SoundsFragment : BaseFragment(R.layout.fragment_sounds), OnSeekBarChangeListener {

    private val view: FragmentSoundsBinding by viewBinding()

    private val viewModel: SoundsViewModel by viewModels()

    private var mediaPlayer1: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.events, ::onEvent)
        setUpMedia()
    }

    private fun setUpMedia() {
        with(view) {
            mediaPlayer1 =
                MediaPlayer.create(requireContext(), R.raw.rain).apply { isLooping = true }
            mediaPlayer2 =
                MediaPlayer.create(requireContext(), R.raw.water).apply { isLooping = true }

            button1.setOnClickListener {
                mediaPlayer1?.let { player ->
                    if (player.isPlaying) {
                        player.pause()
                        seekBar1.visibility = View.GONE
                        tvSound1.visibility = View.VISIBLE
                    } else {
                        player.start()
                        seekBar1.visibility = View.VISIBLE
                        tvSound1.visibility = View.GONE
                    }
                }
            }
            button2.setOnClickListener {
                mediaPlayer2?.let { player ->
                    if (player.isPlaying) {
                        player.pause()
                        seekBar2.visibility = View.GONE
                        tvSound2.visibility = View.VISIBLE
                    } else {
                        player.start()
                        seekBar2.visibility = View.VISIBLE
                        tvSound2.visibility = View.GONE
                    }
                }
            }

            seekBar1.setOnSeekBarChangeListener(this@SoundsFragment)
            seekBar2.setOnSeekBarChangeListener(this@SoundsFragment)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        when (seekBar.id) {
            R.id.seekBar1 -> {
                mediaPlayer1?.setVolume(
                    progress.toFloat() / seekBar.max,
                    progress.toFloat() / seekBar.max
                )
            }

            R.id.seekBar2 -> {
                mediaPlayer2?.setVolume(
                    progress.toFloat() / seekBar.max,
                    progress.toFloat() / seekBar.max
                )
            }
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {}

    override fun onStopTrackingTouch(p0: SeekBar?) {}

}