package com.codinlog.breathe

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.codinlog.audiorecoder.*
import com.codinlog.breathe.database.entity.AudioEntity
import com.codinlog.breathe.databinding.ActivityMainBinding
import org.tensorflow.TensorFlow
import java.io.File
import java.time.LocalDateTime

private val permissions = arrayOf(
    android.Manifest.permission.RECORD_AUDIO,
    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
)

private const val PERMISSION_REQUEST_CODE = 1


class MainActivity : AppCompatActivity() {
    private var isRecording = false
    private var audioRecorder: AudioRecorder? = null
    private var audioEntity: AudioEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        checkRequestPermissions(permissions)
        initAction()
    }

    private fun checkRequestPermissions(permissions: Array<String>) {
        permissions.filter { checkSelfPermission(it) == PackageManager.PERMISSION_DENIED }
            .toTypedArray().let {
                if (it.isNotEmpty())
                    requestPermissions(it, PERMISSION_REQUEST_CODE)
            }
    }

    private fun initAction() {
        findNavController(R.id.fragment).apply {
            setupActionBarWithNavController(this)
        }
    }

    fun recordAudio(v: View) {
        if (!isRecording) {
            val pullProxy = PullProxy.default(
                PullSource.default(), object : IPullProxy.CallBack {
                    override fun beforeStartPull(pullProxy: IPullProxy) {
                        audioEntity = AudioEntity()
                        audioEntity?.audioId = System.currentTimeMillis()
                    }

                    override fun processingPull(data: ByteArray) {
                    }

                    override fun afterStopPull(pullProxy: IPullProxy) {
                        WavAudioRecorder.writeWavHeader(pullProxy)
                    }

                },
                File(
                    getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                    LocalDateTime.now().toString() + ".pcm"
                )
            )
            audioRecorder = WavAudioRecorder(pullProxy)
            audioRecorder?.startRecording()
        } else {
            audioRecorder?.stopRecording()
        }
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