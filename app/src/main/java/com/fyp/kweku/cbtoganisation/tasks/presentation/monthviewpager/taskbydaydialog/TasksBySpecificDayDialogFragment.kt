package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog


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
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.threeten.bp.LocalDate
import timber.log.Timber
import javax.inject.Inject


class TasksBySpecificDayDialogFragment : DialogFragment(), TasksBySpecificDayViewClassInterface.TaskBySpecificDayViewClassFragmentListener {

    @Inject
    lateinit var tasksBySpecificDayController: TasksBySpecificDayController
    @Inject
    lateinit var taskOutput: TaskOutput
    companion object{
        val TAG: String = "TasksBySpecificDayDialogFragment"
    }

    var day: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
        val dateBundle: Bundle? = arguments
        val dayString = dateBundle?.getString("day")
        day = LocalDate.parse(dayString, ProjectDateTimeUtils.getCustomDateFormatter())
        Timber.i("$day")
        day?.let { tasksBySpecificDayController.loadTasksByDay(it) }

        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tasksBySpecificDayViewClassInterface: TasksBySpecificDayViewClassInterface = TasksBySpecificDayViewClass(inflater, container)
        with(tasksBySpecificDayViewClassInterface){
            setFragmentListener(this@TasksBySpecificDayDialogFragment)
            setDate(day)
            setupToolbar()
            initRecyclerView(this@TasksBySpecificDayDialogFragment.context!!)

        }
        val tasksBySpecificDayObserver = Observer<List<TaskPresentationModel>>{ tasks -> Timber.i("$tasks")
            Timber.i("obs")
            tasksBySpecificDayViewClassInterface.sendDataToAdapter(tasks)}
        tasksByLocationLivedata().observe(this, tasksBySpecificDayObserver)

        return tasksBySpecificDayViewClassInterface.getRoot()
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

    fun tasksByLocationLivedata(): LiveData<MutableList<TaskPresentationModel>> {

        return taskOutput.getMediatorTasksByDay()
    }

    override fun dismissDialogFragment(){
        this.dismiss()
    }
}
