package com.fyp.kweku.cbtoganisation.tasks.presentation.createnewtask


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.app.AppCompatActivity
import com.fyp.kweku.cbtoganisation.tasks.presentation.TaskActivity
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateNewTaskViewClassTest {

    private lateinit var inflater: LayoutInflater
    private lateinit var createNewTaskViewClass: CreateNewTaskViewClassInterface
    private lateinit var createNewTaskFragment: CreateNewTaskFragment


    @BeforeEach
    fun init() {
       // inflater = mockk(relaxed = (true))
       // parent = mockk(relaxed = (true))
        createNewTaskFragment = mockk(relaxed = true)
        val inflater = createNewTaskFragment.layoutInflater


    }
    @Test
    fun getRootView() {

    }

    @Test
    fun getSaveNewTaskButtonListener() {
    }

    @Test
    fun getTaskNameInput() {
    }

    @Test
    fun getInflater() {
    }

    @Test
    fun getParent() {
    }
}