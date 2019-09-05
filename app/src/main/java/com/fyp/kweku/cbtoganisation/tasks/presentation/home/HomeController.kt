package com.fyp.kweku.cbtoganisation.tasks.presentation.home





import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksInteractorInterface
import javax.inject.Inject


class HomeController @Inject constructor():HomeViewClassInterface.HomeListener {
   // Interface for related View Class, interface can be used to create instance as it due to Interface Depenency Injection
    private lateinit var homeViewClassInterface: HomeViewClassInterface


    fun bindView(homeViewClassInterface: HomeViewClassInterface) {
        this.homeViewClassInterface = homeViewClassInterface

    }

    fun onviewCreated() {
        homeViewClassInterface.setListener(this)
        homeViewClassInterface.setGoToCreateNewTaskFragmentButtonOnClickListener()

    }



}




