package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fyp.kweku.cbtoganisation.common.presentation.DatePickerFragment
import org.koin.android.ext.android.get


class CreateNewTaskFragment : Fragment() {


    private lateinit var createNewTaskController: CreateNewTaskController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val createNewTaskViewClass: CreateNewTaskViewClassInterface =
            CreateNewTaskViewClass(layoutInflater, container)
        createNewTaskController = get()
        createNewTaskController.bindView(createNewTaskViewClass)


        return createNewTaskViewClass.getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        createNewTaskController.onStart()
    }


    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(getChildFragmentManager(), "datePicker")
    }

    override fun onStop() {
        super.onStop()
        createNewTaskController.onStop()
    }

}
