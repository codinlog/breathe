package com.codinlog.audiorecoder

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.util.concurrent.Executors

abstract class AudioRecorder(val pullProxy: IPullProxy, val file: File) : IAudioRecorder {
    private val executorService = Executors.newSingleThreadExecutor()
    private val out = FileOutputStream(file)
    private val recordingTask = Runnable {
        pullProxy.startPull(out)
    }

    override fun startRecording() {
        executorService.submit(recordingTask)
    }

    override fun stopRecording() {
        try {
            pullProxy.stopPull()
            out.flush()
            out.close()
        }catch (e:IOException){
            throw e
        }
    }

    override fun pauseRecording() {
        pullProxy.getPullSource().setCanPulled(false)
    }

    override fun resumeRecording() {
        pullProxy.getPullSource().setCanPulled(true)
        executorService.submit(recordingTask)
    }

    override fun releaseRecording() {

    }
}

class PcmAudioRecorder(pullProxy: IPullProxy, file: File) : AudioRecorder(
    pullProxy,
    file
)

class WavAudioRecorder(pullProxy: IPullProxy, file: File) : AudioRecorder(pullProxy, file) {
    override fun stopRecording() {
        super.stopRecording()
        try {
            writeWavHeader()
        } catch (e:IOException) {
            throw e
        }
    }

    private fun writeWavHeader() {
        val wavFile = RandomAccessFile(file, "rw")
        wavFile.seek(0)
        wavFile.write(WavHeader(pullProxy.getPullSource(),file.length()).toBytes())
    }
}
