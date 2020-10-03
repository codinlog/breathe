package com.codinlog.audiorecoder

import java.io.IOException

interface IAudioRecorder {
    fun startRecording()
    fun stopRecording()
    fun pauseRecording()
    fun resumeRecording()
    fun releaseRecording()
}