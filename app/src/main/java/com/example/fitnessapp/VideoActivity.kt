package com.example.fitnessapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        webView = findViewById(R.id.webView)
        val videoId = intent.getStringExtra("VIDEO_ID")
        setupWebView()
        if (videoId != null) {
            loadYouTubeVideo(videoId)
        }
    }

    private fun setupWebView() {
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
    }

    private fun loadYouTubeVideo(videoId: String) {
        val iframeHtml = """
            <html>
                <body style='margin:0;padding:0;'>
                    <iframe width='100%' height='100%' 
                            src='https://www.youtube.com/embed/$videoId?enablejsapi=1' 
                            frameborder='0' allowfullscreen>
                    </iframe>
                </body>
            </html>
        """.trimIndent()

        webView.loadData(iframeHtml, "text/html", "utf-8")
    }
}