package `in`.eyehunt.splashscreenandroidkotlin

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.*


class MainActivity : AppCompatActivity() {
    private val url = "http://tutorial.eyehunt.in"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        hlnt_iv_load.bringToFront()

        // Get the web view settings instance
        val settings = web_view.settings

        // Enable java script in web view
        settings.javaScriptEnabled = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.mediaPlaybackRequiresUserGesture = false
        }

        web_view.loadUrl(url);


        // Set web view client
        web_view.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
            }
        }

        // Set web view chrome client
        web_view.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress_bar.progress = newProgress

                //display web page
                if (newProgress > 80){
                    hlnt_iv_load.visibility = View.GONE
                    web_view.visibility = View.VISIBLE
                }

                Log.d("Progress",newProgress.toString())
            }
        }
    }
}



