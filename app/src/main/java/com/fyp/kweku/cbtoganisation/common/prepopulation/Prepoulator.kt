package com.fyp.kweku.cbtoganisation.common.prepopulation


import com.fyp.kweku.cbtoganisation.tasks.data.model.TaskDataModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber


class Prepoulator {
    companion object {
        private val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

lateinit var testData: String
fun getResult():MutableList<TaskDataModel>?{
        val listType = Types.newParameterizedType(MutableList::class.java, TaskDataModel::class.java)
        val adapter: JsonAdapter<MutableList<TaskDataModel>> = moshi.adapter(listType)
    Timber.i("prepopulator.result called")
        return adapter.fromJson(testData)

}


    }


}