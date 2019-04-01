package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview

//Keeps track of the currently visible month and which ones are before and after the visible one
//will be used for pagination and infinite scroll
class HorizontalCalendarProperties(val currentMonth: Int, val currentYear: Int) {
    var monthAtEnd: Int = 0
    var monthAtStart: Int = 0
    var visibleYear: Int = 0

    init {
        this.monthAtEnd = currentMonth
        this.monthAtStart = currentMonth
        this.visibleYear = currentYear
    }
}
