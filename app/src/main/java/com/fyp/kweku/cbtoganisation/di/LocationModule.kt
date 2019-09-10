package com.fyp.kweku.cbtoganisation.di


import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.TasksByLocationInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.LocationsOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.LocationViewModel
import com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels.TasksByLocationViewModel
import dagger.Binds
import dagger.Module


@Module
    abstract class LocationModule {

        @Binds
        abstract fun bindsLocationInteractorInterface(locationInteractor: LocationInteractor
        ): LocationInteractorInterface

       @Binds
        abstract fun bindsTasksByLocationInteractorInterface(tasksByLocationInteractor: TasksByLocationInteractor):TasksByLocationInteractorInterface

        @Binds
        abstract  fun providesLocationsOutput(locationViewModel: LocationViewModel): LocationsOutput

        @Binds
        abstract fun bindsTasksByLocationOutput(tasksByLocationViewModel: TasksByLocationViewModel): TasksByLocationOutput

    }

