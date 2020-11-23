package com.gzeinnumer.coroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gzeinnumer.coroutinesexample.network.RetroServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Loading Show")
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val call = RetroServer.instance.getPostFromUserCoroutines("us", "e5430ac2a413408aaafdf60bfa27a874")
                val response = call.await()
                when(response.code()){
                    200->{
                        val data = response.body()
                        val msg = response.message()
                        Log.d(TAG, "onCreate: Loading Dismiss")
                    }
                    else->{
                        Log.d(TAG, "onCreate: Respose")
                    }
                }
            } catch (e: Exception){
                Log.d(TAG, "onCreate: On Error")
            }
        }
    }
}