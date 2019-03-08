package com.fyp.kweku.cbtoganisation.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.fyp.kweku.cbtoganisation.R

import kotlinx.android.synthetic.main.activity_create_new_task.*

class CreateNewTaskActivity : AppCompatActivity() {

    lateinit var createNewTaskViewMVC: CreateNewTaskViewMVC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        createNewTaskViewMVC = CreateNewTaskViewMVC(LayoutInflater.from(this),null)

        setContentView(createNewTaskViewMVC.rootView)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
