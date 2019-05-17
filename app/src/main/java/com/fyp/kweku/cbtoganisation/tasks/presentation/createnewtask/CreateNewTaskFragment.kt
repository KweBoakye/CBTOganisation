package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import org.koin.android.ext.android.get
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime


class CreateNewTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener ,
    CreateNewTaskViewClassInterface.FragmentListener, TimePickerDialog.OnTimeSetListener{



    lateinit var datePickerDialogStart: DatePickerDialog
lateinit var datePickerDialogEnd: DatePickerDialog
    lateinit var timePickerDialogStart: TimePickerDialog
    lateinit var timePickerDialogEnd: TimePickerDialog
    private lateinit var createNewTaskController: CreateNewTaskController
    private var today = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val createNewTaskViewClass: CreateNewTaskViewClassInterface =
            CreateNewTaskViewClass(layoutInflater, container, this)

        createNewTaskController = get()
        createNewTaskController.bindView(createNewTaskViewClass)
        initDatePickers()
        initTimePickers()

        return createNewTaskViewClass.getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        createNewTaskController.onStart()
    }

    private fun initDatePickers(){
        val today = LocalDate.now()

        datePickerDialogStart = DatePickerDialog.newInstance(this,
            today.year,
            today.monthValue-1,
            today.dayOfMonth)
        datePickerDialogStart.isThemeDark = true

        datePickerDialogEnd = DatePickerDialog.newInstance(this,
            today.year,
            today.monthValue-1,
            today.dayOfMonth)
        datePickerDialogEnd.isThemeDark = true
    }

    private fun showDatePickerStart(){
        //val today = LocalDate.now()
        /*datePickerDialogStart = DatePickerDialog.newInstance(this,
            today.year,
            today.monthValue-1,
            today.dayOfMonth)
        datePickerDialogStart.isThemeDark = true*/

        fragmentManager?.let { datePickerDialogStart.show(it, "Datepickerdialog") }
    }

    private fun showDatePickerEnd(){
        //val today = LocalDate.now()
       /* datePickerDialogEnd = DatePickerDialog.newInstance(this,
            today.year,
            today.monthValue-1,
            today.dayOfMonth)
        datePickerDialogEnd.isThemeDark = true*/

        fragmentManager?.let { datePickerDialogEnd.show(it, "Datepickerdialog") }
    }

    private fun initTimePickers(){
        val currentTime = LocalTime.now()
        timePickerDialogStart = TimePickerDialog.newInstance(this,currentTime.hour, currentTime.minute, true)
        timePickerDialogStart.isThemeDark = true

        timePickerDialogEnd = TimePickerDialog.newInstance(this,currentTime.hour, currentTime.minute, true)
        timePickerDialogEnd.isThemeDark = true

    }

    private fun showTimePickerDialogStart(){
       /* val currentTime = LocalTime.now()
        timePickerDialogStart = TimePickerDialog.newInstance(this,currentTime.hour, currentTime.minute, true)
        timePickerDialogStart.isThemeDark = true*/

        fragmentManager?.let { timePickerDialogStart.show(it,"Timepickerdialog" ) }
    }

    private fun showTimePickerDialogEnd(){
       // val currentTime = LocalTime.now()
        /*timePickerDialogEnd = TimePickerDialog.newInstance(this,currentTime.hour, currentTime.minute, true)
        timePickerDialogEnd.isThemeDark = true*/

        fragmentManager?.let { timePickerDialogEnd.show(it,"Timepickerdialog" ) }
    }


    override fun onStop() {
        super.onStop()
        createNewTaskController.onStop()
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date: LocalDate = LocalDate.of(year, monthOfYear+1, dayOfMonth)
        if (view == datePickerDialogStart){
            createNewTaskController.updateStartDate(date)

        }
        else if (view == datePickerDialogEnd){
            createNewTaskController.updateEndDate(date)
        }
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val time: LocalTime = LocalTime.of(hourOfDay, minute)

        when(view){
            timePickerDialogStart ->{
                createNewTaskController.updateStartTime(time)
            }
            timePickerDialogEnd->{
                createNewTaskController.updateEndTime(time)
            }
        }
    }

   override fun startDateClicked(){
       showDatePickerStart()
   }


    override fun endDateClicked(){
       showDatePickerEnd()
    }

    override fun startTimeClicked() {
        showTimePickerDialogStart()
    }

    override fun endTimeClicked() {
        showTimePickerDialogEnd()
    }


}
