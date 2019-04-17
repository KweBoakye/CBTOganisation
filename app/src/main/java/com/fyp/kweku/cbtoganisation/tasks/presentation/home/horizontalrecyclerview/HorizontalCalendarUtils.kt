package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import org.threeten.bp.Year

class HorizontalCalendarUtils {
    companion object {
        fun calculateMonthLength(month: Int,year: Int): Int {
            var monthLength: Int = 0
            when (month) {
                1 -> {
                    monthLength = 31
                }
                2 -> {monthLength =if (Year.of(year).isLeap)return 29
                    else 28
                }
                3 -> {
                    monthLength = 31
                }
                4 -> {
                    monthLength = 30
                }
                5 -> {
                    monthLength = 31
                }
                6 -> {
                    monthLength = 30
                }
                7 -> {
                    monthLength = 31
                }
                8 -> {
                    monthLength = 31
                }
                9 -> {
                    monthLength = 30
                }
                10 -> {
                    monthLength = 31
                }
                11 -> {
                    monthLength = 30
                }
                12 -> {
                    monthLength = 31
                }

            }
            return monthLength
        }

        fun returnMonthName(month: Int): String {
            var monthName: String = ""
            when (month) {
                1 -> {
                    monthName = "Jan"

                }
                2 -> {
                    monthName = "Feb"

                }
                3 -> {
                    monthName = "Mar"

                }
                4 -> {
                    monthName = "Apr"

                }
                5 -> {
                    monthName = "May"

                }
                6 -> {
                    monthName = "Jun"

                }
                7 -> {
                    monthName = "Jul"

                }
                8-> {
                    monthName = "Aug"

                }
                9 -> {
                    monthName = "Sep"

                }
                10 -> {
                    monthName = "Oct"

                }
                11 -> {
                    monthName = "Nov"

                }
                12 -> {
                    monthName = "Dec"

                }
            }
            return monthName
        }

        fun configCallLayout(context: Context, layout: View, color: Int) {
            val shape: GradientDrawable = GradientDrawable()
            // instead of context.getResources().getColor(color))as this depreciated and the
            // version
            shape.setColor(ContextCompat.getColor(context, color))
            shape.setShape(GradientDrawable.OVAL)
            layout.setBackground(shape)
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