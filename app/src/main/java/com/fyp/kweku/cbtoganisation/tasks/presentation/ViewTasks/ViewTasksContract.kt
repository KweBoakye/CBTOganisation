package com.fyp.kweku.cbtoganisation.tasks.presentation.ViewTasks

import com.fyp.kweku.cbtoganisation.common.presentation.BasePresenter
import com.fyp.kweku.cbtoganisation.common.presentation.BaseView

interface ViewTasksContract {

    interface View : BaseView<Presenter> {
        fun showTasks()
    }

    interface Presenter : BasePresenter<View> {
        fun loadTasks()
    }

    }