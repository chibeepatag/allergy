package com.chibee.allergy.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chibee.allergy.data.AllergyDatabase
import com.chibee.allergy.data.Drug
import com.chibee.allergy.data.Reaction
import com.chibee.allergy.utilities.DRUG_DATA_FILENAME
import com.chibee.allergy.utilities.REACTION_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope


class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(DRUG_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val drugType = object : TypeToken<List<Drug>>() {}.type
                    val drugList: List<Drug> = Gson().fromJson(jsonReader, drugType)

                    val database = AllergyDatabase.getInstance(applicationContext)
                    database.drugDao().insertAll(drugList)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }

    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}