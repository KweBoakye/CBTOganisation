package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.common.prepopulation.Prepoulator
import com.fyp.kweku.cbtoganisation.di.AppComponent
import com.fyp.kweku.cbtoganisation.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class CBTOrganisationApplication : Application(){




    companion object{
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent = component
    }

    override fun onCreate() {
        super.onCreate()

         component =DaggerAppComponent
            .builder()
             .application(this)
              .bindContext(this)
            .build()


        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
        readJSONFromAsset()


    }

    private fun readJSONFromAsset(){
        val json: String?
        try {
            val inputStream: InputStream = assets.open("testdata.json")
            json = inputStream.bufferedReader().use{ it.readText() }
            Timber.i("$json")
            Prepoulator.testData = json
        }
        catch (e:IOException){}
    }




}//