package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.FragmentCreateNewTaskBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import org.threeten.bp.LocalDate
import timber.log.Timber


class CreateNewTaskViewClass( val inflater: LayoutInflater,  val parent: ViewGroup?, fragmentManager: FragmentManager?) :
    CreateNewTaskViewClassInterface {



    private val binding: FragmentCreateNewTaskBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_create_new_task, parent, false)
    private var taskNameInput: TextInputEditText = binding.TaskNameInput
    private var taskLocationInput: TextInputEditText = binding.TaskLocationInput
    private var taskStartDateInput: TextInputEditText = binding.TaskStatDateInput
    private var taskEndDateInput: TextInputEditText = binding.TaskEndDateInput
    private var taskStartTimeInput: TextInputEditText = binding.TaskStartTimeInput
    private var taskEndTimeInput: TextInputEditText = binding.TaskEndTimeInput
    private var taskDescriptionInput: TextInputEditText = binding.TaskDescriptionInput
    private var taskNameInputLayout: TextInputLayout = binding.TaskNameInputLayout
    private var taskLocationInputLayout: TextInputLayout = binding.TaskLocationInputLayout
    private var taskStartDateInputLayout: TextInputLayout = binding.TaskStartDateInputLayout
    private var taskEndDateInputLayout: TextInputLayout = binding.TaskEndDateInputLayout
    private var taskStartTimeInputLayout: TextInputLayout = binding.TaskStartTimeInputLayout
    private var taskEndTimeInputLayout: TextInputLayout = binding.TaskEndTimeInputLayout


   // private var taskDescriptionInput TextInputLayout = binding.
    private var rootView: View = binding.root
    private var saveNewTaskButton: MaterialButton = binding.SaveNewTaskButton
    private val dateFormatter = ProjectDateTimeUtils.getCustomDateFormatter()

    private var listener : CreateNewTaskViewClassInterface.CreateNewTaskListener? = null


    init {
        Timber.i("${(fragmentManager == null)}")



        saveNewTaskButton.setOnClickListener {
            listener?.onSaveNewTaskButtonClick(getTaskInput())
            Timber.i("button set")


        }
    }

    override fun setListener(listener: CreateNewTaskViewClassInterface.CreateNewTaskListener?) {
        this.listener = listener
        Timber.i("CreateNewTaskListenerListener set")
    }

    override fun getRootView():View {
        return rootView
    }

    fun taskNameValid():Boolean{
       val nameInput : String = taskNameInputLayout.editText?.text.toString().trim()

        return if (nameInput.isEmpty()) {taskNameInputLayout.error = "Name Can't Be Empty"
            false
        } else {taskNameInput.error = null
            true
        }
    }

    fun startDateValid():Boolean{
        val startDateInput : String = taskStartDateInputLayout.editText?.text.toString().trim()

        return if (startDateInput.isEmpty()) {taskStartDateInputLayout.error = "Date Can't Be Empty"
            false
        } else {taskStartDateInputLayout.error = null
            true
        }
    }

    fun endDateValid():Boolean{
        val endDateInput : String = taskEndDateInputLayout.editText?.text.toString().trim()

        return when {
            endDateInput.isEmpty() -> {taskEndDateInputLayout.error = "Date Can't Be Empty"
                false
            }
            LocalDate.parse(taskStartDateInputLayout.editText?.text.toString().trim(), dateFormatter).isAfter(LocalDate.parse(taskEndDateInputLayout.editText?.text.toString().trim(), dateFormatter)) -> {
                taskEndDateInputLayout.error = "End Date can't be Before the Start Date"
                return  false
            }
            else -> {taskEndDateInputLayout.error = null
                true
            }
        }
    }



    fun getTaskInput(): Array<String>{
        return if (taskNameValid() and startDateValid() and endDateValid()){
            val input = arrayOf(
                (taskNameInput.text.toString().trim()),
                (taskLocationInput.text.toString().trim()),
                (taskDescriptionInput.text.toString().trim()),
                (taskStartDateInput.text.toString().trim()),
                (taskEndDateInput.text.toString().trim()),
                (taskStartTimeInput.text.toString().trim()),
                (taskEndTimeInput.text.toString().trim())
            )
            Timber.i("get task input called")
            input
        } else emptyArray()

    }

//Validation functions for Ui that stem from Valdiation functions in the Controller

}