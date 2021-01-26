package com.codinlog.audiorecoder

import android.media.AudioFormat
import android.media.MediaRecorder

class AudioConfig(val source: Int, val encoding: Int, val channel: Int, val frequency: Int) {
    fun bitsPerSample(): Byte = when (encoding) {
        AudioFormat.ENCODING_PCM_8BIT -> 8
        AudioFormat.ENCODING_PCM_16BIT -> 16
        else -> 16
    }

    companion object {
        fun default() = AudioConfig(
            MediaRecorder.AudioSource.MIC,
            AudioFormat.ENCODING_PCM_16BIT,
            AudioFormat.CHANNEL_IN_MONO,
            44100
        )
    }
}