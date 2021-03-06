package com.fyp.kweku.cbtoganisation.tasks.presentation.home.horizontalrecyclerview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.databinding.HorizontalCalendarItemBinding
import timber.log.Timber


class HorizontalCalendarAdapter(private val context: Context,
                                private val onDaySelectedListener: OnDaySelectedListener,

                                private val onEndReachedListener: OnEndReachedListener): RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>() {

    fun onScrollStopped(layoutPosition: Int) {
        onDaySelectedListener.onDayScrolled( HorizontalCalendarUtils.returnStringDate(data[layoutPosition].day,data[layoutPosition].month, data[layoutPosition].year))

    }

    private lateinit var data: MutableList<HorizontalCalendarItem>

   fun getData():MutableList<HorizontalCalendarItem> {
       return this.data
   }

    private lateinit var horizontalCalendarItemBinding: HorizontalCalendarItemBinding
    private lateinit var  horizontalCalendarViewHolder: HorizontalCalendarViewHolder
    private var isFirstBind: Boolean = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalCalendarViewHolder {
        horizontalCalendarItemBinding  = HorizontalCalendarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false) // DataBindingUtil.inflate
        horizontalCalendarViewHolder = HorizontalCalendarViewHolder( horizontalCalendarItemBinding.root)
        horizontalCalendarViewHolder.day = horizontalCalendarItemBinding.calendarDay
        horizontalCalendarViewHolder.month = horizontalCalendarItemBinding.calendarMonth
        horizontalCalendarViewHolder.year = horizontalCalendarItemBinding.calendarYear
        horizontalCalendarViewHolder.itemLayout = horizontalCalendarItemBinding.calendarItem
        horizontalCalendarViewHolder.dateLayout = horizontalCalendarItemBinding.dateLayout

        return horizontalCalendarViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: HorizontalCalendarViewHolder, position: Int) {

        if (position == 0 && !isFirstBind) {notifyEndReached()}
        else if (isFirstBind) {notifyEndReached()}
        else if ((position ) >= (itemCount-1)){notifyStartReached()}
        isFirstBind = false
        holder.day.text = data[position].day.toString()
        holder.month.text = HorizontalCalendarUtils.returnMonthName(data[position].month)
        holder.year.text = data[position].year.toString()
        holder.itemLayout.setOnClickListener{view ->   onDaySelectedListener.onDaySelected(view,
            HorizontalCalendarUtils.returnStringDate(data[position].day,data[position].month, data[position].year), position)
        Timber.i("${data[position].month}")}

        HorizontalCalendarUtils.configCallLayout(context,holder.dateLayout,data[position].backgroundColor )


    }

    class HorizontalCalendarViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    lateinit var day: TextView
        lateinit var month: TextView
        lateinit var year: TextView
        lateinit var itemLayout: ConstraintLayout
        lateinit var dateLayout: LinearLayout

    }

    fun addItemsAtBottom(bottomList: MutableList<HorizontalCalendarItem>) {

        if (bottomList.isEmpty() ) return

        val adapterSize: Int = itemCount
        data.addAll(adapterSize, bottomList)
        notifyItemRangeInserted(adapterSize, adapterSize + bottomList.size)
    }

    fun addItemsAtTop(topList: MutableList<HorizontalCalendarItem>){


        if (topList.isEmpty()) return

        data.addAll(0, topList)
        notifyItemRangeInserted(0, topList.size)
    }



    private fun notifyEndReached(){
        val handler = Handler(Looper.getMainLooper())
        Timber.i("End called")
        handler.postDelayed( { onEndReachedListener.onEndReached()},50)
    }

    private fun notifyStartReached(){
        val handler = Handler(Looper.getMainLooper())
        Timber.i("Start called")
        handler.postDelayed({ onEndReachedListener.onStartReached()},50)
    }



    //this function is used to populate adapter with data
    //It takes a list of Horizontal Calendar Items
    fun setData(data: MutableList<HorizontalCalendarItem>) {
        this.data = data
    }

    interface OnDaySelectedListener {
        fun onDaySelected(view: View, date: String, position: Int)
        fun onDayScrolled(date: String)
    }

    interface OnEndReachedListener {
        fun onEndReached()
        fun onStartReached()
    }
}
