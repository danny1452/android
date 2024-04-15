package com.example.player

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.player.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.webView.settings.setJavaScriptEnabled(true);
        binding.webView.loadUrl("https://www.youtube.com")
    }
}
