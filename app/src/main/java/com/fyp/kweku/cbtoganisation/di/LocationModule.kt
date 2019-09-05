package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.di.LocationModule.AbstractLocationModule
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.LocationsOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.LocationsController
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationController
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.LocationViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TasksByLocationViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [AbstractLocationModule::class])
class LocationModule {

        @Provides
        fun providesLocationLocationsController(locationInteractorInterface: LocationInteractorInterface): LocationsController {
            return LocationsController(locationInteractorInterface)
        }

    @Provides
    fun providesTasksByLocationInteractor(tasksByLocationInteractor: TasksByLocationInteractor): TasksByLocationInteractorInterface{
        return tasksByLocationInteractor
    }

    @Provides
    fun providesTasksByLocationsController(tasksByLocationInteractorInterface: TasksByLocationInteractorInterface): TasksByLocationController{
        return TasksByLocationController(tasksByLocationInteractorInterface)
    }

    @Module
    interface AbstractLocationModule {

        @Binds
         fun bindsLocationInteractor(locationInteractor: LocationInteractor
        ): LocationInteractorInterface

        @Binds
         fun providesLocationsOutput(locationViewModel: LocationViewModel): LocationsOutput

        @Binds
         fun bindsTasksByLocationOutput(tasksByLocationViewModel: TasksByLocationViewModel): TasksByLocationOutput

    }

}