package com.codinlog.breathe

import android.app.Activity
import android.content.pm.PackageManager

const val PERMISSION_REQUEST_CODE = 1
val permissions = arrayOf(
    android.Manifest.permission.RECORD_AUDIO,
    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
)

fun checkRequestPermissions(permissions: Array<String>, activity: Activity) {
    permissions.filter { activity.checkSelfPermission(it) == PackageManager.PERMISSION_DENIED }
        .toTypedArray().let {
            if (it.isNotEmpty())
                activity.requestPermissions(it, PERMISSION_REQUEST_CODE)
        }
}