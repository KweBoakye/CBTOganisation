package com.fyp.kweku.cbtoganisation.tasks.presentation.home.recyclerview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat

class HorizontalCalendarUtils {
    companion object {
        fun calculateMonthLength(month: Int): Int {
            var monthLength: Int = 0
            when (month) {
                0 -> {
                    monthLength = 31
                }
                1 -> {
                    monthLength = 28
                }
                2 -> {
                    monthLength = 31
                }
                3 -> {
                    monthLength = 30
                }
                4 -> {
                    monthLength = 31
                }
                5 -> {
                    monthLength = 30
                }
                6 -> {
                    monthLength = 31
                }
                7 -> {
                    monthLength = 31
                }
                8 -> {
                    monthLength = 30
                }
                9 -> {
                    monthLength = 31
                }
                10 -> {
                    monthLength = 30
                }
                11 -> {
                    monthLength = 31
                }

            }
            return monthLength
        }

        fun returnMonthName(month: Int): String {
            var monthName: String = ""
            when (month) {
                0 -> {
                    monthName = "Jan"

                }
                1 -> {
                    monthName = "Feb"

                }
                2 -> {
                    monthName = "Mar"

                }
                3 -> {
                    monthName = "Apr"

                }
                4 -> {
                    monthName = "May";

                }
                5 -> {
                    monthName = "Jun"

                }
                6 -> {
                    monthName = "Jul"

                }
                7 -> {
                    monthName = "Aug"

                }
                8 -> {
                    monthName = "Sep"

                }
                9 -> {
                    monthName = "Oct"

                }
                10 -> {
                    monthName = "Nov"

                }
                11 -> {
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
            var date: String = "$day/$month/$year"
            return date
        }

    }
}