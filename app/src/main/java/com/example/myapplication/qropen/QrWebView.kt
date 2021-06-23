package com.example.myapplication.qropen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.myapplication.R

class QrWebView : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_web_view)
        val intent = intent
        val qrUrl = intent.getStringExtra("qrUrl")
        progressBar = findViewById(R.id.progress_bar)
        webView = findViewById(R.id.activity_qr_webview_btn)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(qrUrl.toString())
        webView.webViewClient = object: WebViewClient()
        {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }


    }
}