package com.mindyapps.fairytales.feature.fairytale.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.session.MediaSession
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.OptIn
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerNotificationManager
import androidx.transition.TransitionInflater
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mindyapps.fairytales.R
import com.mindyapps.fairytales.base.presentation.BaseFragment
import com.mindyapps.fairytales.core.observe
import com.mindyapps.fairytales.databinding.FragmentFairyTaleBinding
import com.mindyapps.fairytales.extension.parcelable
import com.mindyapps.fairytales.feature.fairytales.domain.FairyTaleViewData

class FairyTaleFragment : BaseFragment(R.layout.fragment_fairy_tale) {

    private val view: FragmentFairyTaleBinding by viewBinding()

    private val viewModel: FairyTaleViewModel by viewModels()

    private var fairyTaleViewData: FairyTaleViewData? = null


    private lateinit var player: ExoPlayer
    private lateinit var mediaSession: MediaSession
    private lateinit var notificationManager: PlayerNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        observe(viewModel.events, ::onEvent)
        fairyTaleViewData = arguments?.parcelable("fairyTale")
        initFairyTaleData()
        initPlayer()
    }

    @OptIn(UnstableApi::class)
    private fun initPlayer() {
        with(view) {
            // Инициализация плеера ExoPlayer
            player = ExoPlayer.Builder(requireContext()).build()
            //playerView.player = player

            // Создание экземпляра MediaSessionCompat
            mediaSession = MediaSession(requireContext(), "AudioPlayerFragment")
            mediaSession.isActive = true

            // Установка медиа-контроллера для плеера
            player.playWhenReady = true
            val mediaItem =
                MediaItem.fromUri("http://cc67181.tw1.ru/test.mp3")
            player.setMediaItem(mediaItem)
            player.prepare()

            // Создание уведомления плеера
            notificationManager = PlayerNotificationManager.Builder(
                requireContext(),
                1,
                "fairy tale"
            )
                .setChannelNameResourceId(R.string.notification_channel_name)
                .setChannelDescriptionResourceId(R.string.notification_channel_Description)
                .setMediaDescriptionAdapter(
                    object : PlayerNotificationManager.MediaDescriptionAdapter {
                        override fun getCurrentContentTitle(player: Player): String {
                            return fairyTaleViewData?.title ?: ""
                        }

                        override fun createCurrentContentIntent(player: Player): PendingIntent? {
                            return null
                            // При желании, здесь можно указать intent для перехода в приложение при нажатии на уведомление
                        }

                        override fun getCurrentContentText(player: Player): String {
                            return fairyTaleViewData?.description ?: ""
                        }

                        override fun getCurrentLargeIcon(
                            player: Player,
                            callback: PlayerNotificationManager.BitmapCallback
                        ): Bitmap? {
                            return null
                            // При желании, здесь можно установить большую иконку для уведомления
                        }
                    }
                )
                .setNotificationListener(notificationListener)
                .build()

            notificationManager.setUseChronometer(true)
            notificationManager.setPlayer(player)

            playButton.setOnClickListener {
                if (player.isPlaying) {
                    player.pause()
                    playButton.text = "play"
                } else {
                    player.playWhenReady = true
                    playButton.text = "pause"
                }
            }

            // Устанавливаем максимальное значение для seekbar в миллисекундах
            player.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    super.onPlaybackStateChanged(state)
                    if (state == Player.STATE_READY) {
                        seekBar.max = player.duration.toInt()
                    }
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    if (isPlaying) {
                        // Обновляем положение seekbar в соответствии с текущим временем воспроизведения
                        seekBar.post {
                            updateSeekBar()
                        }
                    }
                }
            })

            // Обработчик перемотки аудио при изменении положения seekbar
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        player.seekTo(progress.toLong())
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // Остановка обновления seekbar во время перемотки
                    player.playWhenReady = false
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // Возобновление воспроизведения после перемотки
                    player.playWhenReady = true
                }
            })
        }

    }

    private fun updateSeekBar() {
        val currentPosition = player.currentPosition.toInt()
        view.seekBar.progress = currentPosition
        if (player.playWhenReady) {
            // Обновление seekbar каждые 100 миллисекунд
            view.seekBar.postDelayed({ updateSeekBar() }, 100)
        }
    }


    private val notificationListener = object : PlayerNotificationManager.NotificationListener {
        @SuppressLint("UnsafeOptInUsageError")
        override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
            NotificationManagerCompat.from(requireContext()).apply {
                cancel(notificationId)
            }
        }

        @SuppressLint("UnsafeOptInUsageError")
        override fun onNotificationPosted(
            notificationId: Int,
            notification: Notification,
            ongoing: Boolean
        ) {
            if (ongoing) {
                NotificationManagerCompat.from(requireContext()).apply {
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            requireActivity().requestPermissions(
                                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                                1
                            )
                        }
                        return
                    }
                    notify(notificationId, notification)
                }
            } else {
                onNotificationCancelled(notificationId, true)
            }
        }
    }

    private fun initFairyTaleData() {
        fairyTaleViewData?.let { fairyTale ->
            ViewCompat.setTransitionName(view.tvTitle, "title_${fairyTale.id}")
            ViewCompat.setTransitionName(view.tvDescription, "description_${fairyTale.id}")
            view.tvTitle.text = fairyTale.title
            view.tvDescription.text = fairyTale.description
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onDestroyView() {
        super.onDestroyView()

        // Освобождение ресурсов при уничтожении фрагмента
        player.release()
        mediaSession.release()
        notificationManager.setPlayer(null)
    }
}