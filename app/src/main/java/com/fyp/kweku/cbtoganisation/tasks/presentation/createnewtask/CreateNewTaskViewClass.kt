package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fyp.kweku.cbtoganisation.common.ProjectDateTimeUtils
import com.fyp.kweku.cbtoganisation.databinding.FragmentCreateNewTaskBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.threeten.bp.LocalDate
import timber.log.Timber
import org.threeten.bp.LocalTime


class CreateNewTaskViewClass(val inflater: LayoutInflater, val parent: ViewGroup?, private val fragmentListener: CreateNewTaskViewClassInterface.FragmentListener) :
    CreateNewTaskViewClassInterface {



    private val binding: FragmentCreateNewTaskBinding = FragmentCreateNewTaskBinding.inflate(inflater, parent, false)
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
    private var rootView: View = binding.root
    private var saveNewTaskButton: MaterialButton = binding.SaveNewTaskButton
    private val dateFormatter = ProjectDateTimeUtils.getCustomDateFormatter()
    private var listener : CreateNewTaskViewClassInterface.CreateNewTaskListener? = null

  /*  private val calendar: Calendar = Calendar.getInstance()
    private val startDatePicker = object :DatePickerDialog.OnDateSetListener{
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH)
            updateStartDate()
        }

    }*/

    override fun updateStartDate(date: LocalDate){
        taskStartDateInput.setText(date.format(dateFormatter))
    }

    override fun updateEndDate(date: LocalDate){
        taskEndDateInput.setText(date.format(dateFormatter))
    }

    override fun updateStartTime(time:LocalTime) = taskStartTimeInput.setText(time.toString())

    override fun updateEndTime(time:LocalTime) = taskEndTimeInput.setText(time.toString())


   // private var taskDescriptionInput TextInputLayout = binding.




/*
    private fun onDateClicked(): OnClickListener {
        return object : OnClickListener() {
            fun onClick(v: View) {
                val newFragment = object : DatePickerFragment() {
                    fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                        salePaymentCustomView.setBtDateText("" + day + "/" + month + 1 + "/" + year)
                    }
                }
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker")
            }
        }
    }
*/

    init {
        saveNewTaskButton.setOnClickListener {
            listener?.onSaveNewTaskButtonClick(getTaskInput())
            Timber.i("button set")
        }

        taskStartDateInput.setOnClickListener {
            fragmentListener.startDateClicked()
        }

        taskEndDateInput.setOnClickListener {
            fragmentListener.endDateClicked()
        }

        taskStartTimeInput.setOnClickListener {
            fragmentListener.startTimeClicked()
        }

        taskEndTimeInput.setOnClickListener {
            fragmentListener.endTimeClicked()
        }
    }

    override fun setListener(listener: CreateNewTaskViewClassInterface.CreateNewTaskListener?) {
        this.listener = listener
        Timber.i("CreateNewTaskListenerListener set")
    }

    override fun getRootView():View {
        return rootView
    }

    private fun taskNameValid():Boolean{
       val nameInput : String = taskNameInputLayout.editText?.text.toString().trim()

        return if (nameInput.isEmpty()) {taskNameInputLayout.error = "Name Can't Be Empty"
            false
        } else {taskNameInput.error = null
            true
        }
    }

    private fun startDateValid():Boolean{
        val startDateInput : String = taskStartDateInput.text.toString().trim()

        return if (startDateInput.isEmpty()) {taskStartDateInputLayout.error = "Date Can't Be Empty"
            false
        } else {taskStartDateInputLayout.error = null
            true
        }
    }

    private fun endDateValid():Boolean{
        val endDateInput : String = taskEndDateInput.text.toString().trim()

        return when {
            endDateInput.isEmpty() -> {taskEndDateInputLayout.error = "Date Can't Be Empty"
                false
            }
            LocalDate.parse(taskStartDateInput.text.toString().trim(), dateFormatter).isAfter(LocalDate.parse(taskEndDateInput.text.toString().trim(), dateFormatter)) -> {
                taskEndDateInputLayout.error = "End Date can't be before the Start Date"
                return  false
            }
            else -> {taskEndDateInputLayout.error = null
                true
            }
        }
    }

    private fun startTimeValid(): Boolean{
        val startTimeInput: String = taskStartTimeInput.text.toString().trim()

        return if (startTimeInput.isEmpty()){
            taskStartTimeInputLayout.error = "Time Can't Be Empty"
            true
        }

        else{
            taskStartTimeInputLayout.error = null
            true
        }
    }

    private fun startAndEndDateSameDay():Boolean{
        return if (taskStartDateInput.text.toString().trim() != "" && taskEndDateInput.text.toString().trim() != ""){
        LocalDate.parse(taskStartDateInput.text.toString().trim(),dateFormatter)
            .isEqual(LocalDate.parse(taskEndDateInput.text.toString().trim(), dateFormatter))}
        else false
    }

    private fun endTimeValid(): Boolean{
        val endTimeInput: String = taskEndTimeInput.text.toString().trim()

      return when{
          endTimeInput.isEmpty() || endTimeInput == ""->{
                taskEndTimeInputLayout.error = "Time can't be Empty"
                false
            }
          startAndEndDateSameDay()->{
              if (LocalTime.parse(taskStartTimeInput.text.toString().trim())
                      .isAfter(LocalTime.parse(endTimeInput))){
                 taskEndTimeInputLayout.error = "End Time can't be before the Start Time"
                  false
              }
              else{
                  taskEndTimeInputLayout.error = null
                  true
              }
          }
          else ->{taskEndTimeInputLayout.error = null
          true}
        }

    }



    private fun getTaskInput(): Array<String>{
        return if (taskNameValid() and startDateValid() and endDateValid() and startTimeValid() and endTimeValid()){
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