package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.get

class ViewTasksFragment : Fragment() {

private lateinit var viewTasksController: ViewTasksController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val viewTasksViewClass:ViewTasksViewClassInterface = ViewTasksViewClass(layoutInflater, container)
        viewTasksController = get()
        viewTasksController.bindView(viewTasksViewClass)
        return viewTasksViewClass.getRootView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}