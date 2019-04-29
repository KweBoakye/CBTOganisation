package com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask

import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AlertDialog

class DeleteTasksViewClass: DeleteTasksViewClassInterface {

    private lateinit var listener: DeleteTasksViewClassInterface.DeleteTasksViewClassListener

    override fun setListener(listener: DeleteTasksViewClassInterface.DeleteTasksViewClassListener){
        this.listener = listener
    }



    fun deleteAlert(view: View) {
        val builder = AlertDialog.Builder(view.context)
        with(builder) {
            setTitle("Delete Task?")
            setMessage("This Cannot Be Undone")
            setPositiveButton("DELETE", null)
            setNegativeButton("CANCEL", null)


        }
        val alertDialog = builder.create()
        alertDialog.show()

        val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        with(button) {
            setBackgroundColor(Color.RED)
            setPadding(0, 0, 20, 0)
            setTextColor(Color.WHITE)

        }
    }
}