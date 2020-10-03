package com.codinlog.audiorecoder

import android.media.AudioRecord

interface IPullSource {
    fun pullSizeInBytes(): Int
    fun setCanPulled(enabled: Boolean)
    fun preparePulled(): AudioRecord
    fun getAudioConfig(): AudioConfig
}