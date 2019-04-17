package com.fyp.kweku.cbtoganisation.classlinks.domain

import com.fyp.kweku.cbtoganisation.goals.domain.Goals
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

data class GoalsAndTasks(val goalsAndTasksID:String,
                         val goal: Goals,
                         val task: Task
) {
}