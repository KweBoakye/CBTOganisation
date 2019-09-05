package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TasksByLocationOutput

import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import javax.inject.Inject


class TasksByLocationDialogFragment : DialogFragment() , TasksByLocationViewClassInterface.TasksByLocationViewClassFragmentListener{

    companion object{
        val TAG: String = "TasksByLocationDialogFragment"
    }

    @Inject lateinit var tasksByLocationController: TasksByLocationController
    var location: String? = null
    @Inject lateinit var tasksByLocationOutput: TasksByLocationOutput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
        val locationBundle: Bundle? = arguments


            location = locationBundle?.getString("location")

        location?.let { tasksByLocationController.loadTasksByLocations(it) }
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val tasksByLocationViewClassInterface: TasksByLocationViewClassInterface = TasksByLocationViewClass(inflater, container)
        tasksByLocationViewClassInterface.also {
           it.setFragmentListener(this)
            it.setLocation(location)
            it.setupToolbar()
            it.initRecyclerView(this.context!!)
        }

        val tasksByLocationObserver = Observer<List<TaskPresentationModel>>{tasks -> tasksByLocationViewClassInterface.sendDataToAdapter(tasks)}

        tasksByLocationLivedata().observe(this, tasksByLocationObserver)

        return tasksByLocationViewClassInterface.getRoot()
    }


    override fun dismissDialogFragment(){
        this.dismiss()
    }


    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    private fun tasksByLocationLivedata(): LiveData<List<TaskPresentationModel>> {
       return tasksByLocationOutput.getTasksByLocation()
    }
}
