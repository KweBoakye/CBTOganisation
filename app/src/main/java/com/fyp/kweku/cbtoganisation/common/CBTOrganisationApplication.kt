package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.common.prepopulation.prepoulator
import com.fyp.kweku.cbtoganisation.di.DataModule
import com.fyp.kweku.cbtoganisation.di.DomainModule
import com.fyp.kweku.cbtoganisation.di.PresentationModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.json.JSONObject
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class CBTOrganisationApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin{
            AndroidLogger()
            androidContext(this@CBTOrganisationApplication)
        modules(DomainModule, DataModule, PresentationModule)}
        //Initialize time zone information
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree())

        readJSONFromAsset()

    }

    fun readJSONFromAsset(){
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("testdata.json")
            json = inputStream.bufferedReader().use{ it.readText() }
            prepoulator.testData = json
        }
        catch (e:IOException){}
    }


}