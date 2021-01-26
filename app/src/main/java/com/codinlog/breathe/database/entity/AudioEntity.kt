package com.codinlog.breathe.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audioTB")
class AudioEntity{
    /**
     * @author coinlog
     * 数据库：
     *  audioId 录音Id，以日期作为Id 格式化为 yyyy-MM-dd:hh-mm-ss
//   * @param audioName
     *  audioTrack 录音时长
     */
    @PrimaryKey
    @ColumnInfo(name = "audioId")
    var audioId = 0L
//
//    @ColumnInfo(name = "audioName")
//    var audioName = ""

    @ColumnInfo(name = "audioTrack")
    var audioTrack = 0F
}