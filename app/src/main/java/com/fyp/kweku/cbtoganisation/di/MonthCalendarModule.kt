package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.MonthCalendarInteractorInterface

import dagger.Binds
import dagger.Module


    @Module
abstract class MonthCalendarModule{

        @Binds
        abstract fun bindsMonthCalendarInteractorInterface(monthCalendarInteractor: MonthCalendarInteractor): MonthCalendarInteractorInterface

    }
