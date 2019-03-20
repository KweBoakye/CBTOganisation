package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractor
import org.koin.standalone.KoinComponent

class ViewTasksPresenter (private val getTasksInteractor: GetTasksInteractor):ViewTasksContract.Presenter, KoinComponent {

    override lateinit var view: ViewTasksContract.View


    override fun loadTasks() {
      getTasksInteractor.allTasks()
    }

    override fun start() {
       loadTasks()
    }
}
