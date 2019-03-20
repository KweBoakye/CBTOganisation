package com.fyp.kweku.cbtoganisation.tasks.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.common.presentation.DatePickerFragment
import com.fyp.kweku.cbtoganisation.databinding.FragmentCreateNewTaskBinding
import com.google.android.material.textfield.TextInputEditText



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CreateNewTaskFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CreateNewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CreateNewTaskFragment : Fragment() {

    private lateinit var taskNameInput: TextInputEditText
    private lateinit var taskLocationInput: TextInputEditText
    private lateinit var taskDateInput: TextInputEditText
    private lateinit var binding: FragmentCreateNewTaskBinding
    private lateinit var createNewTaskViewClass: CreateNewTaskViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

createNewTaskViewClass = CreateNewTaskViewClass(this.layoutInflater, container )

        return createNewTaskViewClass.RootView
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onDetach() {
        super.onDetach()

    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(getChildFragmentManager(), "datePicker")
    }



    /*companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CreateNewTaskFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }*/
}
