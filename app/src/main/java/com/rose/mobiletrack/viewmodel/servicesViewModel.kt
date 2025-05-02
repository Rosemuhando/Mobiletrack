package com.rose.mobiletrack.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rose.mobiletrack.data.servicesDatabase
import com.rose.mobiletrack.model.services
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.io.copyTo
import kotlin.io.use

class servicesViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val servicesDao = servicesDatabase.Companion.getDatabase(app). servicesDao()


    val allservices: LiveData<List<services>> = servicesDao.getAllservices()

    fun addservices(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newservices = services(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            servicesDao.insertservices(newservices)
        }
    }

    fun updateServices(updatedServices: services) {
        viewModelScope.launch(Dispatchers.IO) {
            servicesDao.updateServices(updatedServices)
        }
    }

    fun deleteservices(services: services) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(services.imagePath)
            servicesDao.deleteservices(services)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}