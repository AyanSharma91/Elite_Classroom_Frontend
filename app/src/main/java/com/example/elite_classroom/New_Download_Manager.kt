package com.example.elite_classroom

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.UiThread
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class New_Download_Manager {

    companion object
    {



    public fun downloadFile(fileUrl: String, fileName: String, context : Context, activity : Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Toast.makeText(context, "You must use device running Android 10 or higher", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("Exceptionss",fileUrl)
        thread {
            try {
                val url = URL(fileUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val inputStream = connection.inputStream
                val bis = BufferedInputStream(inputStream)
                val values = ContentValues()
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
                val uri = context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
                if (uri != null) {
                    val outputStream = context.contentResolver.openOutputStream(uri)
                    if (outputStream != null) {
                        val bos = BufferedOutputStream(outputStream)
                        val buffer = ByteArray(1024)
                        var bytes = bis.read(buffer)
                        while (bytes >= 0) {
                            bos.write(buffer, 0 , bytes)
                            bos.flush()
                            bytes = bis.read(buffer)
                        }
                        bos.close()


                        activity.runOnUiThread {
                            Toast.makeText(context, "$fileName is in Download directory now.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                bis.close()
            } catch (e: Exception) {
                activity.runOnUiThread {
                    Log.d("Exceptionss",e.toString())
                }
            }
        }
    }

    }
}