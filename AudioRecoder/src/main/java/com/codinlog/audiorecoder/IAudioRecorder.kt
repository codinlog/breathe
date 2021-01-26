package com.codinlog.audiorecoder

import java.io.RandomAccessFile

interface IAudioRecorder {
    fun startRecording()
    fun stopRecording()
    fun pauseRecording()
    fun resumeRecording()
}

abstract class AudioRecorder(private val pullProxy: IPullProxy) : IAudioRecorder {

    override fun startRecording() {
        pullProxy.getPullSource().setCanPulled(true)
        pullProxy.startPull()
    }

    override fun stopRecording() {
        pullProxy.getPullSource().setCanPulled(false)
        pullProxy.stopPull()
    }

    override fun pauseRecording() {
        pullProxy.getPullSource().setCanPulled(false)
    }

    override fun resumeRecording() {
        pullProxy.getPullSource().setCanPulled(true)
    }
}

class PcmAudioRecorder(private val pullProxy: IPullProxy) : AudioRecorder(pullProxy)
class WavAudioRecorder(private val pullProxy: IPullProxy) : AudioRecorder(pullProxy) {
    companion object {
        fun writeWavHeader(pullProxy: IPullProxy) {
            val wavFile = RandomAccessFile(pullProxy.getPullFile(), "rw")
            wavFile.seek(0)
            wavFile.write(
                WavHeader(
                    pullProxy.getPullSource(),
                    pullProxy.getPullFile().length()
                ).toBytes()
            )
            wavFile.close()
        }
    }
}