package com.codinlog.audiorecoder

import java.io.IOException
import java.io.OutputStream

interface IPullProxy {
    fun getPullSource(): IPullSource
    fun startPull(outputStream: OutputStream)
    fun stopPull()
}

class PullProxy(private val pullSource: IPullSource) : IPullProxy {
    override fun getPullSource(): IPullSource = pullSource

    override fun startPull(outputStream: OutputStream) {

    }

    override fun stopPull() {

    }

}