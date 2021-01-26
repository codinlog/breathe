package com.codinlog.breathe.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codinlog.breathe.database.entity.AudioEntity

@Dao
interface AudioDao {
    @Query("select * from audioTB")
    suspend fun queryAudios(): LiveData<List<AudioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAudio(vararg audioEntities: AudioEntity)
}