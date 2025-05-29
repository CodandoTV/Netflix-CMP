package com.codandotv.streamplayerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codandotv.streamplayerapp.StreamPlayerApp
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.mmk.kmpnotifier.permission.permissionUtil

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        requestNotificationPermission()
        setContent {
            StreamPlayerApp()
        }
    }

    private fun requestNotificationPermission() {
        val permissionUtil by permissionUtil()
        permissionUtil.askNotificationPermission()
    }
}
