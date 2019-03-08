package com.fyp.kweku.cbtoganisation.tasks.presentation.ViewTasks

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetAllTasksInteractor
import org.koin.standalone.KoinComponent

class ViewTasksPresenter (private val getAllTasksInteractor: GetAllTasksInteractor):ViewTasksContract.Presenter, KoinComponent {

    override lateinit var view: ViewTasksContract.View


    override fun loadTasks() {
      getAllTasksInteractor.allTasks()
    }

    override fun start() {
       loadTasks()
    }
}
