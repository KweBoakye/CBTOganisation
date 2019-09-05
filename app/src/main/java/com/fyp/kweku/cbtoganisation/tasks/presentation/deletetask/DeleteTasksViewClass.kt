package com.fyp.kweku.cbtoganisation.tasks.presentation.deletetask

import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar

class DeleteTasksViewClass(private val CoordinatorLayout: CoordinatorLayout): DeleteTasksViewClassInterface {

    private lateinit var listener: DeleteTasksViewClassInterface.DeleteTasksViewClassListener

    override fun setListener(listener: DeleteTasksViewClassInterface.DeleteTasksViewClassListener){
        this.listener = listener
    }

    override fun showSnackbar(){
        Snackbar.make(CoordinatorLayout, "Task Deleted", Snackbar.LENGTH_INDEFINITE)
            .setAction("Undo Delete") {
                listener.restoreTask()
            }.show()
    }

    fun showConfirmTaskRestoredSnackbar(){
       Snackbar.make(CoordinatorLayout, "Task Restored", Snackbar.LENGTH_INDEFINITE).show()
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