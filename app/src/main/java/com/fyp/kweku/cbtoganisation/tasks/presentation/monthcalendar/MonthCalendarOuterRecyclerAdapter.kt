package com.fyp.kweku.cbtoganisation.tasks.presentation.monthcalendar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.databinding.MonthCalendarFrameBinding
import org.threeten.bp.YearMonth

class MonthCalendarOuterRecyclerAdapter: RecyclerView.Adapter<MonthCalendarRecyclerAdapter.MonthCalendarViewHolder>() {

    private lateinit var month: MutableList<YearMonth>
    private lateinit var monthCalendarFrameBinding: MonthCalendarFrameBinding
inner class MonthCalendarOuterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MonthCalendarRecyclerAdapter.MonthCalendarViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MonthCalendarRecyclerAdapter.MonthCalendarViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}