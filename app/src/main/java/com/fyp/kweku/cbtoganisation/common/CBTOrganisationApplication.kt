package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.di.DataModule
import com.fyp.kweku.cbtoganisation.di.DomainModule
import com.fyp.kweku.cbtoganisation.di.PresentationModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class CBTOrganisationApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, listOf(DataModule, PresentationModule, DomainModule))
        //Initialize time zone information
        AndroidThreeTen.init(this)
        Timber.plant(Timber.DebugTree()


        )

    }


}