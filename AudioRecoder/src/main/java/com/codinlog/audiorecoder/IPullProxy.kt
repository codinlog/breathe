package com.codinlog.audiorecoder

import android.media.AudioRecord
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executors

interface IPullProxy {
    fun getPullFile(): File
    fun getPullSource(): IPullSource
    fun startPull()
    fun stopPull()
    interface CallBack {
        fun beforeStartPull(pullProxy: IPullProxy)
        fun processingPull(data: ByteArray)
        fun afterStopPull(pullProxy: IPullProxy)
    }
}

abstract class PullProxy(
    private val pullSource: IPullSource,
    private val callBack: IPullProxy.CallBack?,
    private val file: File
) : IPullProxy {
    private val executorService = Executors.newSingleThreadExecutor()
    private val out = FileOutputStream(file)
    private val recordingTask = Runnable {
        val data = ByteArray(pullSource.getBufferSize())
        while (pullSource.getCanPulled()) {
            val result = pullSource.getAudioRecord().read(data, 0, pullSource.getBufferSize())
            if ((result != AudioRecord.ERROR_INVALID_OPERATION) && (result != AudioRecord.ERROR_BAD_VALUE)) {
                callBack?.processingPull(data)
                out.write(data)
            }
        }
    }

    override fun getPullFile(): File = file
    override fun getPullSource(): IPullSource = pullSource

    override fun startPull() {
        callBack?.beforeStartPull(this)
        pullSource.getAudioRecord().startRecording()
        executorService.submit(recordingTask)
    }

    override fun stopPull() {
        out.flush()
        out.close()
        pullSource.getAudioRecord().stop()
        pullSource.getAudioRecord().release()
        callBack?.afterStopPull(this)
    }

    companion object {
        class Default(pullSource: IPullSource, callBack: IPullProxy.CallBack?, file: File) :
            PullProxy(pullSource, callBack, file)

        fun default(pullSource: IPullSource, callBack: IPullProxy.CallBack?, file: File) =
            Default(pullSource, callBack, file)
    }

}