package com.codinlog.audiorecoder

import android.media.AudioRecord

class PullSource(private val config: AudioConfig):IPullSource {
    private var canPulled = false
    private val bufferSize = AudioRecord.getMinBufferSize(config.frequency, config.chanel, config.encoding)
    private val audioRecorder: AudioRecord =
        AudioRecord(config.source, config.frequency, config.chanel, config.encoding, bufferSize)

    override fun pullSizeInBytes(): Int {
        return 0
    }

    override fun setCanPulled(enabled: Boolean){canPulled = enabled}

    override fun preparePulled(): AudioRecord = audioRecorder

    override fun getAudioConfig(): AudioConfig = config
}