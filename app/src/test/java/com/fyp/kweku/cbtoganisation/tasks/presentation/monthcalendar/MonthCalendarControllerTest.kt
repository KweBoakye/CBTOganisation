package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar



import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterController
import com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar.outerrecyclerview.MonthCalendarOuterViewClassInterface
import com.jakewharton.threetenabp.AndroidThreeTen
import io.mockk.mockk
import org.junit.Before
import org.junit.Test



import org.junit.runner.RunWith
import org.threeten.bp.LocalDate
import org.robolectric.RobolectricTestRunner

import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = CBTOrganisationApplication::class)
 class MonthCalendarControllerTest{

    private lateinit var monthCalendarController: MonthCalendarOuterController
    private lateinit var monthCalendarOuterViewClassInterface: MonthCalendarOuterViewClassInterface
    private lateinit var dateTestList:MutableList<LocalDate>
    private lateinit var getTasksInteractorInterface: GetTasksInteractorInterface


    @Before
    fun init(){
val app = getApplicationContext<CBTOrganisationApplication>()
        AndroidThreeTen.init(app)
       getTasksInteractorInterface = mockk(relaxed = true)
        monthCalendarOuterViewClassInterface = mockk(relaxed = true)
        monthCalendarController =
            MonthCalendarOuterController(
                getTasksInteractorInterface
            )
        monthCalendarController.bindView(monthCalendarOuterViewClassInterface)
        val testList = mutableListOf<String>(
            "2018-02-26", "2018-02-27", "2018-02-28", "2018-03-01", "2018-03-02", "2018-03-03", "2018-03-04",
            "2018-03-05", "2018-03-06", "2018-03-07", "2018-03-08", "2018-03-09", "2018-03-10", "2018-03-11",
            "2018-03-12", "2018-03-13", "2018-03-14", "2018-03-15", "2018-03-16", "2018-03-17", "2018-03-18",
            "2018-03-19", "2018-03-20", "2018-03-21", "2018-03-22", "2018-03-23", "2018-03-24", "2018-03-25",
            "2018-03-26", "2018-03-27", "2018-03-28", "2018-03-29", "2018-03-30", "2018-03-31", "2018-04-01",
            "2018-04-02", "2018-04-03", "2018-04-04", "2018-04-05", "2018-04-06", "2018-04-07", "2018-04-08")
        dateTestList= testList.map { LocalDate.parse(it)}.toMutableList()
    }

    @Test
    fun testGetListOfDates(){
        val testDate: LocalDate = LocalDate.parse("2018-02-26")
        val listfromfunction = monthCalendarController.getListOfDates(testDate)
        print("$listfromfunction")
        assert(listfromfunction.equals(dateTestList))
    }


}