package com.example.planyourday

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import java.util.*

class ListAdapter(private val activity: Activity,
                  private val dataSource: ArrayList<JSONObject>) : BaseAdapter() {
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private val inflater : LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override  fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_layout, parent, false)

        val iconActivity = rowView.findViewById<ImageView>(R.id.iconActivity)
        val dateDay = rowView.findViewById<TextView>(R.id.dateDay)
        val nameActivity = rowView.findViewById<TextView>(R.id.nameActivity)
        val timeActivity = rowView.findViewById<TextView>(R.id.timeActivity)
        val dateActivity = rowView.findViewById<TextView>(R.id.dateActivity)

        val item = getItem(position) as JSONObject
        println(item)
        when(item.get("icon")) {
            "Travel" -> { iconActivity.setImageResource(R.drawable.travel) }
            "Information" -> { iconActivity.setImageResource(R.drawable.information) }
            "House" -> { iconActivity.setImageResource(R.drawable.house) }
            "Shopping" -> { iconActivity.setImageResource(R.drawable.shopping) }
        }

        nameActivity.text = item.get("name").toString()
        timeActivity.text = item.get("time").toString()
        dateActivity.text = item.get("date").toString()

        val tempDay = item.get("date").toString()
        val day = tempDay.split("-")[0].toInt()
        val month = tempDay.split("-")[1].toInt() - 1
        val year = tempDay.split("-")[2].toInt()
        val calendar = Calendar.getInstance()
        calendar.clear()
        println("CZAY: $year $month $day")
        calendar.set(year, month, day)

        val weekday = calendar.get(Calendar.DAY_OF_WEEK)
        println("WEEK_DAY $weekday")
        when(weekday) {
            1 -> { dateDay.text = "Monday, $day" }
            2 -> { dateDay.text = "Tuesday, $day" }
            3 -> { dateDay.text = "Wednesday, $day" }
            4 -> { dateDay.text = "Thursday, $day" }
            5 -> { dateDay.text = "Friday, $day" }
            6 -> { dateDay.text = "Saturday, $day" }
            7 -> { dateDay.text = "Sunday, $day" }
        }

        return rowView
    }
}