package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.common.prepopulation.prepoulator
import com.fyp.kweku.cbtoganisation.di.*
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






        // Start Koin
        /*startKoin{
            AndroidLogger()
            androidContext(this@CBTOrganisationApplication)
        modules(DomainModule, DataModule, PresentationModule)}*/
        //Initialize time zone information
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())
        readJSONFromAsset()


    }



    //Needs to be here due to assets.open
    fun readJSONFromAsset(){
        val json: String?
        try {
            val inputStream: InputStream = assets.open("testdata.json")
            json = inputStream.bufferedReader().use{ it.readText() }
            Timber.i("$json")
            prepoulator.testData = json
        }
        catch (e:IOException){}
    }




}//