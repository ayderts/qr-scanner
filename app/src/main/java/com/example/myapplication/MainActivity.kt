package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.qropen.QrWebView
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val str: String = "http://www.google.com"
        val pattern = Regex("https://")
        val pattern2 = Regex("http://")

        val result = pattern.containsMatchIn(str)
        val result2 = pattern2.containsMatchIn(str)

        val btnScan: Button = findViewById(R.id.activity_main_qr_scan)
        btnScan.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
        }
    Log.d("AYDER",checkValidURL("httpzz://www.google.com").toString())
    }



    fun checkValidURL (url: String): Boolean {
        val pattern = """(?i)(https?|ftp)://(?:-\.)?([^\s/?.#-]+\.?)+(/\S*)?""".toRegex()
        if (pattern.matches(url))
            return true
        return false
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK)
        {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                }
                else if(!checkValidURL(result.contents)){

                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                }
                else if(checkValidURL(result.contents)){
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity,QrWebView::class.java)
                    intent.putExtra("qrUrl",result.contents)
                    startActivity(intent)

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}