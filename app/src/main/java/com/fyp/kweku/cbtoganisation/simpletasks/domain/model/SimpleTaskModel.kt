package com.fyp.kweku.cbtoganisation.simpletasks.domain.model

import com.fyp.kweku.cbtoganisation.goals.domain.Goals
import org.threeten.bp.LocalTime

data class SimpleTaskModel(val simpleTaskId: String,
                           val simpleTaskName: String,
                           val location: String,
                           val goal: Goals,
                           val estimatedLength: LocalTime,
                           val urgency: String,
                           val Importance:String) {
}