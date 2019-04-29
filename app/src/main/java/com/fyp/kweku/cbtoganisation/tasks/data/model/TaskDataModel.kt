package com.fyp.kweku.cbtoganisation.tasks.data.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/* DatabaseModel class, with defenitions to allow room to define an Interface for it*/

@Entity(tableName = "tasks")
data class TaskDataModel (@PrimaryKey
    val taskID: String,
    val taskName: String,       /*Denotes task Name*/
    val taskLocation: String,   /*Denotes Location Task will take place */
    val taskDescription: String,/*Description of the task*/
    val taskStartDate: String, /* Date task will take place*/
    val taskEndDate: String,
    val taskStartTime: String, /* Time task will start*/
    val taskEndTime: String /* Length/duration of task*/

)
