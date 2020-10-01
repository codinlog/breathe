//package com.example.breathdemo.service
//
//import android.media.browse.MediaBrowser
//import android.media.session.MediaSession
//import android.os.Bundle
//import android.service.media.MediaBrowserService
//import androidx.lifecycle.LifecycleEventObserver
//
//private const val MY_MEDIA_ROOT_ID = "media_root_id"
//private const val MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id"
//private const val LOG_TAG = "MediaRecorderService"
//
//class MediaRecorderService : MediaBrowserService() {
//    private var mediaSession: MediaSession? = null
//    private val mediaRecorder by lazy {
//
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        mediaSession = MediaSession(baseContext,LOG_TAG)
//    }
//
//    override fun onGetRoot(
//        clientPackageName: String,
//        clientUid: Int,
//        rootHints: Bundle?
//    ): BrowserRoot? {
//
//    }
//
//    override fun onLoadChildren(
//        parentId: String,
//        result: Result<MutableList<MediaBrowser.MediaItem>>
//    ) {
//
//    }
//}