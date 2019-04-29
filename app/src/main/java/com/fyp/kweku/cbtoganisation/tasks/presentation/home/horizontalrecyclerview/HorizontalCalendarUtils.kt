package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import org.threeten.bp.Month
import org.threeten.bp.Year

class HorizontalCalendarUtils {
    companion object {
        fun calculateMonthLength(month: Int,year: Int): Int {
            var monthLength: Int = 0
            when (month) {
                1,3,5,8,10,12 -> {
                    monthLength = 31
                }
                2 -> {monthLength =if (Year.of(year).isLeap)return 29
                    else 28
                }

                4,6,7,9,11 -> {
                    monthLength = 30
                }

            }
            return monthLength
        }

        fun returnMonthName(month: Int): String {
            return Month.of(month).name.subSequence(0,3).toString()

        }


        fun configCallLayout(context: Context, layout: View, color: Int) {
            val shape: GradientDrawable = GradientDrawable()
            // instead of context.getResources().getColor(color))as this depreciated and the
            // version
            shape.setColor(ContextCompat.getColor(context, color))
            shape.setShape(GradientDrawable.OVAL)
            with(layout){
                background = shape
                elevation = 6f
                translationZ = 6f
                alpha = 1f
            }

        }





        fun returnStringDate(day: Int, month: Int, year: Int): String {

            val date: String = if(day <10 && month< 10  ) "0$day/0$month/$year"
            else if (day<10) "0$day/$month/$year"
            else if (month< 10) "$day/0$month/$year"
            else
                "$day/$month/$year"
            return date
        }


    }
}