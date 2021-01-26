package com.codinlog.audiorecoder

import android.media.AudioRecord
import java.io.OutputStream
import java.util.concurrent.atomic.AtomicBoolean

interface IPullSource {
    fun getBufferSize(): Int
    fun setCanPulled(enabled: Boolean)
    fun getCanPulled(): Boolean
    fun getAudioRecord(): AudioRecord
    fun getAudioConfig(): AudioConfig
}

class PullSource(private val config: AudioConfig) : IPullSource {
    private var canPulled: AtomicBoolean = AtomicBoolean(false)
    private val bufferSize =
        AudioRecord.getMinBufferSize(config.frequency, config.channel, config.encoding)
    private val audioRecorder: AudioRecord =
        AudioRecord(config.source, config.frequency, config.channel, config.encoding, bufferSize)


    override fun setCanPulled(enabled: Boolean): Unit {
        canPulled.set(enabled)
    }

    override fun getBufferSize(): Int = bufferSize

    override fun getCanPulled(): Boolean = canPulled.get()

    override fun getAudioRecord(): AudioRecord = audioRecorder

    override fun getAudioConfig(): AudioConfig = config

    companion object {
        fun default() = PullSource(AudioConfig.default())
    }
}