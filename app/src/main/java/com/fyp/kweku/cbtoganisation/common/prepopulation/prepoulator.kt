package com.fyp.kweku.cbtoganisation.common.prepopulation

import android.content.res.AssetManager
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import timber.log.Timber
import java.io.InputStream

class prepoulator {
    companion object {
        private val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

lateinit var testData: String
fun getResult():MutableList<TaskDataModel>?{
        val jsonAdapter: JsonAdapter<TaskDataModel> = moshi.adapter(TaskDataModel::class.java)
        val listType = Types.newParameterizedType(MutableList::class.java, TaskDataModel::class.java)
        val adapter: JsonAdapter<MutableList<TaskDataModel>> = moshi.adapter(listType)
    Timber.i("prepopulator.result called")
    //Timber.i("$testData")
        return adapter.fromJson(testData)

}


    }


}