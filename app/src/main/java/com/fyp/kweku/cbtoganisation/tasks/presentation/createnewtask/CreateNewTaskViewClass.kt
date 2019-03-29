package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentCreateNewTaskBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import timber.log.Timber


class CreateNewTaskViewClass( val inflater: LayoutInflater,  val parent: ViewGroup?) :
    CreateNewTaskViewClassInterface {



    private val binding: FragmentCreateNewTaskBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_create_new_task, parent, false)
    private var taskNameInput: TextInputEditText = binding.TaskNameInput
    private var taskLocationInput: TextInputEditText = binding.TaskLocationInput
    private var taskDateInput: TextInputEditText = binding.TaskDateInput
    private var taskDescriptionInput: TextInputEditText = binding.TaskDescriptionInput
    private var taskStartTimeInput: TextInputEditText = binding.TaskStartTimeInput
    private var taskEndTimeInput: TextInputEditText = binding.TaskEndTimeInput
    private var rootView: View = binding.root
    private var saveNewTaskButton: MaterialButton = binding.SaveNewTaskButton
    private var listener : CreateNewTaskViewClassInterface.CreateNewTaskListener? = null

    init {

        saveNewTaskButton.setOnClickListener {
            listener?.onSaveNewTaskButtonClick(getTaskInput())
            Timber.i("button set")
        }
    }

    override fun setListener(listener: CreateNewTaskViewClassInterface.CreateNewTaskListener?) {
        this.listener = listener
        Timber.i("listener set")
    }

    override fun getRootView():View {
        return rootView
    }



    fun getTaskInput(): Array<String>{
        val input = arrayOf(
            (taskNameInput.text.toString()),
            (taskLocationInput.text.toString()),
            (taskDateInput.text.toString()),
            (taskDescriptionInput.text.toString()),
            (taskStartTimeInput.text.toString()),
            (taskEndTimeInput.text.toString())
        )
       return input
        Timber.i("get task input called")
    }

//Validation functions for Ui that stem from Valdiation functions in the Controller

}