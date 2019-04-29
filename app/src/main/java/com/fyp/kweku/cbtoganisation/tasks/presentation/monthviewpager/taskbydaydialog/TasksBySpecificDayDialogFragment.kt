package com.fyp.kweku.cbtoganisation.tasks.presentation.monthviewpager.taskbydaydialog


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get
import org.threeten.bp.LocalDate


class TasksBySpecificDayDialogFragment : DialogFragment(), TasksBySpecificDayViewClassInterface.TaskBySpecificDayViewClassFragmentListener {


    lateinit var tasksBySpecificDayController: TasksBySpecificDayController
    companion object{
        val TAG: String = "TasksBySpecificDayDialogFragment"
    }

    var day: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksBySpecificDayController = get()
        val dateBundle: Bundle? = arguments
        val dayString = dateBundle?.getString("day")
        day = LocalDate.parse(dayString, ProjectDateTimeUtils.getCustomDateFormatter())
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
        val tasksBySpecificDayObserver = Observer<List<TaskPresentationModel>>{ tasks -> tasksBySpecificDayViewClassInterface.sendDataToAdapter(tasks)}
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

    fun tasksByLocationLivedata(): MutableLiveData<List<TaskPresentationModel>> {
        @Suppress("UNCHECKED_CAST")
        return tasksBySpecificDayController.getTasksInteractorInterface.getTasksByLiveDataAsAny() as MutableLiveData<List<TaskPresentationModel>>
    }

    override fun dismissDialogFragment(){
        this.dismiss()
    }
}
