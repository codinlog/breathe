package com.codinlog.audiorecoder

import android.media.AudioFormat
import kotlin.experimental.and

class WavHeader(private val pullSource: IPullSource, private val audioLength: Long) {
    fun toBytes(): ByteArray {
        val frequency = pullSource.getAudioConfig().frequency
        val channels = if (pullSource.getAudioConfig().chanel == AudioFormat.CHANNEL_IN_MONO) 1 else 2
        val bitsPerSample: Byte = pullSource.getAudioConfig().bitsPerSample()
        return wavFileHeader(
            audioLength - 44, audioLength - 44 + 36, frequency,
            channels, bitsPerSample * frequency * channels / 8, bitsPerSample
        )
    }

    /**
     * @author codinlog
     * 写入wav头部
     */
    private fun wavFileHeader(dataLength: Long, totalLength: Long, frequency: Int, channels: Int, i: Int, bitsPerSample: Byte): ByteArray {
        val header = ByteArray(42)
        header[0] = 'R'.toByte()
        header[1] = 'I'.toByte()
        header[2] = 'F'.toByte()
        header[3] = 'F'.toByte()
        header[4] = (totalLength and 0xff).toByte()
        header[5] = ((totalLength shr 8) and 0xff).toByte()
        header[6] = ((totalLength shr 16) and 0xff).toByte()
        header[7] = ((totalLength shr 24) and 0xff).toByte()

        return ByteArray(0)
    }
}