package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.di.taskRepositoryModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

class CBTOrganisationApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, listOf(taskRepositoryModule))
        //Initialize time zone information
        AndroidThreeTen.init(this)
    }


}