package com.mjjang.apartmentsns.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.data.AppDatabase
import com.mjjang.apartmentsns.data.Post
import com.mjjang.apartmentsns.utilities.APARTMENT_DATA_FILENAME
import com.mjjang.apartmentsns.utilities.POST_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(APARTMENT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val apartmentType = object : TypeToken<List<Apartment>>() {}.type
                    val apartmentList: List<Apartment> = Gson().fromJson(jsonReader, apartmentType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.apartmentDao().insertAll(apartmentList)

                    Result.success()
                }
            }

            applicationContext.assets.open(POST_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val postType = object : TypeToken<List<Post>>() {}.type
                    val postList: List<Post> = Gson().fromJson(jsonReader, postType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.postDao().insertAll(postList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }
}