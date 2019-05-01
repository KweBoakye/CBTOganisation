package com.fyp.kweku.cbtoganisation.tasks.presentation.edittasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.FragmentEditTaskBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.threeten.bp.LocalDate
import timber.log.Timber

class EditTaskViewClass(val inflater: LayoutInflater, val parent: ViewGroup?): EditTaskViewClassInterface {

    private val editTaskViewClassBinding: FragmentEditTaskBinding = FragmentEditTaskBinding.inflate(inflater, parent, false )
    private val root: View = editTaskViewClassBinding.root
    private var taskNameInput: TextInputEditText = editTaskViewClassBinding.TitleTextView
    private var taskLocationInput: TextInputEditText = editTaskViewClassBinding.locationTextView
    private var taskStartDateInput: TextInputEditText = editTaskViewClassBinding.startDateTextView
    private var taskEndDateInput: TextInputEditText= editTaskViewClassBinding.endDateTextView
    private var taskStartTimeInput: TextInputEditText = editTaskViewClassBinding.startTimeTextView
    private var taskEndTimeInput: TextInputEditText = editTaskViewClassBinding.endTimeTextView
    private var taskDescriptionInput: TextInputEditText = editTaskViewClassBinding.descriptionTextView
    private var taskNameInputLayout: TextInputLayout = editTaskViewClassBinding.TitleTextViewLayout
    private var taskLocationInputLayout: TextInputLayout= editTaskViewClassBinding.locationTextViewLayout
    private var taskStartDateInputLayout: TextInputLayout= editTaskViewClassBinding.startDateTextViewLayout
    private var taskEndDateInputLayout: TextInputLayout = editTaskViewClassBinding.endDateTextViewLayout
    private var taskStartTimeInputLayout: TextInputLayout= editTaskViewClassBinding.startTimeTextViewLayout
    private var taskEndTimeInputLayout: TextInputLayout = editTaskViewClassBinding.endTimeTextViewLayout
    private val dateFormatter = ProjectDateTimeUtils.getCustomDateFormatter()

    override fun getRoot(): View = this.root

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

}