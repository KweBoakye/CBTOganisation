package com.fyp.kweku.cbtoganisation.di

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractor
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.CreateNewTaskInteractorInterface
import org.koin.dsl.module.module

val DomainModule = module{

    factory<CreateNewTaskInteractorInterface> {CreateNewTaskInteractor(get()) }
}