package com.codinlog.breathe

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.breathe.databinding.ActivityMainBinding
import com.codinlog.breathe.widget.MediaRecorder
import com.example.breathe.R
import java.io.File
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    private lateinit var recorder: MediaRecorder
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recorder = MediaRecorder().apply {
            setAudioSource(android.media.MediaRecorder.AudioSource.MIC)
            setOutputFormat(android.media.MediaRecorder.OutputFormat.DEFAULT)
            setAudioEncoder(android.media.MediaRecorder.AudioEncoder.DEFAULT)
            setOutputFile(
                (getExternalFilesDir(Environment.DIRECTORY_MUSIC)).toString() + File.separator + LocalTime.now()
                    .toString()
            )
            prepare()
        }
        lifecycle.addObserver(recorder)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        findNavController(R.id.fragment).apply {
            setupActionBarWithNavController(this)
        }
        checkRequestPermissions(permissions, this)
        initWidget()
    }

    private fun initWidget() {

    }

    fun recordAudio(v: View) {
        if (isRecording)
            recorder.stop()
        else
            recorder.start()
        isRecording = !isRecording

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).popBackStack() || super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {

        }
    }
}