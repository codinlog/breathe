package com.codinlog.audiorecoder

import android.media.AudioFormat

class WavHeader(private val pullSource: IPullSource, private val audioLength: Long) {
    fun toBytes(): ByteArray {
        val frequency = pullSource.getAudioConfig().frequency
        val channel =
            if (pullSource.getAudioConfig().channel == AudioFormat.CHANNEL_IN_MONO) 1 else 2
        val bitsPerSample: Byte = pullSource.getAudioConfig().bitsPerSample()
        return wavFileHeader(
            audioLength - 44, audioLength - 44 + 36, frequency,
            channel, bitsPerSample * frequency * channel / 8, bitsPerSample
        )
    }

    /**
     * @author codinlog
     * 写入wav头部
     */
    private fun wavFileHeader(
        dataLength: Long,
        totalLength: Long,
        frequency: Int,
        channel: Int,
        byteRate: Int,
        bitsPerSample: Byte
    ): ByteArray {
        val header = ByteArray(44)
        header[0] = 'R'.toByte()
        header[1] = 'I'.toByte()
        header[2] = 'F'.toByte()
        header[3] = 'F'.toByte()
        header[4] = (totalLength and 0xff).toByte()
        header[5] = ((totalLength shr 8) and 0xff).toByte()
        header[6] = ((totalLength shr 16) and 0xff).toByte()
        header[7] = ((totalLength shr 24) and 0xff).toByte()
        header[8] = 'W'.toByte()
        header[9] = 'A'.toByte()
        header[10] = 'V'.toByte()
        header[11] = 'E'.toByte()
        header[12] = 'f'.toByte()
        header[13] = 'm'.toByte()
        header[14] = 't'.toByte()
        header[15] = ' '.toByte()
        header[16] = 16
        header[17] = 0
        header[18] = 0
        header[19] = 0
        header[20] = 1
        header[21] = 0
        header[22] = channel.toByte()
        header[23] = 0
        header[24] = (frequency and 0xff).toByte()
        header[25] = ((frequency shr 8) and 0xff).toByte()
        header[26] = ((frequency shr 16) and 0xff).toByte()
        header[27] = ((frequency shr 24) and 0xff).toByte()
        header[28] = (byteRate and 0xff).toByte()
        header[29] = ((byteRate shr 8) and 0xff).toByte()
        header[30] = ((byteRate shr 16) and 0xff).toByte()
        header[31] = ((byteRate shr 24) and 0xff).toByte()
        header[32] = (channel * bitsPerSample / 8).toByte()
        header[33] = 0
        header[34] = bitsPerSample
        header[35] = 0
        header[36] = 'd'.toByte()
        header[37] = 'a'.toByte()
        header[38] = 't'.toByte()
        header[39] = 'a'.toByte()
        header[40] = (dataLength and 0xff).toByte()
        header[41] = ((dataLength shr 8) and 0xff).toByte()
        header[42] = ((dataLength shr 16)and 0xff).toByte()
        header[43] = ((dataLength shr 24) and 0xff).toByte()
        return header
    }
}