package com.fyp.kweku.cbtoganisation.tasks.presentation.viewtasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentViewTasksBinding

class ViewTasksViewClass(val  inflater: LayoutInflater, val parent: ViewGroup?): ViewTasksViewClassInterface {

    private val binding: FragmentViewTasksBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_view_tasks,parent,false)
    private var rootView: View = binding.root

    override fun getRootView():View {
        return rootView
    }

    override fun setListener(listener: ViewTasksViewClassInterface.ViewTasksListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}