package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fyp.kweku.cbtoganisation.tasks.presentation.home.HomeViewClassInterface
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import org.koin.android.ext.android.get
import org.threeten.bp.LocalDate


class CreateNewTaskFragment : Fragment()  {


    lateinit var datePickerDialogStart: DatePickerDialog
lateinit var datePickerDialogEnd: DatePickerDialog
    private lateinit var createNewTaskController: CreateNewTaskController
    private var today = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val createNewTaskViewClass: CreateNewTaskViewClassInterface =
            CreateNewTaskViewClass(layoutInflater, container, fragmentManager)

        createNewTaskController = get()
        createNewTaskController.bindView(createNewTaskViewClass)
         datePickerDialogStart = DatePickerDialog.newInstance(createNewTaskViewClass as DatePickerDialog.OnDateSetListener, today.year, today.monthValue, today.dayOfMonth)
         datePickerDialogEnd= DatePickerDialog.newInstance(createNewTaskViewClass as DatePickerDialog.OnDateSetListener, today.year, today.monthValue, today.dayOfMonth)

        return createNewTaskViewClass.getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        createNewTaskController.onStart()
    }




    override fun onStop() {
        super.onStop()
        createNewTaskController.onStop()
    }

}
