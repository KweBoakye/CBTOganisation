package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask


import android.view.View


interface CreateNewTaskViewClassInterface {



    interface CreateNewTaskListener {
        fun onSaveNewTaskButtonClick(input: Array<String>)
    }



    fun getRootView(): View


    fun setListener(listener: CreateNewTaskListener?)



}