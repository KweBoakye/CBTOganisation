package com.fyp.kweku.cbtoganisation.common

import android.app.Application
import com.fyp.kweku.cbtoganisation.di.taskRepositoryModule
import org.koin.android.ext.android.startKoin

class CBTOrganisationApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, listOf(taskRepositoryModule))
    }


}