package com.codinlog.audiorecoder

import android.media.AudioFormat

class AudioConfig(val source: Int, val encoding: Int, val chanel: Int, val frequency: Int) {
    fun bitsPerSample(): Byte = when (encoding) {
        AudioFormat.ENCODING_PCM_8BIT -> 8
        AudioFormat.ENCODING_PCM_16BIT -> 16
        else -> 16
    }
}