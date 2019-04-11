package com.example.planyourday

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

const val FORM_ACTIVITY = 1
class MainActivity : AppCompatActivity() {

    var array = arrayListOf<JSONObject>()
    var displayArray = arrayListOf<JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("AFTER CREATE: $array")
        fab.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java).apply {  }
            startActivityForResult(intent, FORM_ACTIVITY)
        }

        // SPINNER
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.types)
        ) as SpinnerAdapter?
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinner = findViewById<Spinner>(R.id.spinner)
                val textItem = spinner.selectedItem
                displayArray.clear()
                if(textItem.toString() == "All types") {
                    array.forEach {
                        displayArray.add(it)
                    }
                }
                else {
                    array.forEach {
                        val item = it.get("icon").toString()
                        if(item == textItem.toString()) {
                            displayArray.add(it)
                        }
                    }
                }
                listActivities.adapter = ListAdapter(this@MainActivity, displayArray)
                val tempAdapter = listActivities.adapter as ListAdapter
                tempAdapter.notifyDataSetChanged()
            }
        }

        // CALENDAR - data ELEMENTS
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // CALENDAR - dataPicker
        dateButton.setOnClickListener {
            val dataDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    var searchingDate = ""
                    val monthCorrect = month + 1
                    if(dayOfMonth > 10 && monthCorrect > 10) {
                        searchingDate = "$dayOfMonth-$monthCorrect-$year"
                    }
                    else if(dayOfMonth < 10 && monthCorrect > 10) {
                        searchingDate = "0$dayOfMonth-$monthCorrect-$year"
                    }
                    else if(dayOfMonth > 10 && monthCorrect < 10) {
                        searchingDate = "$dayOfMonth-0$monthCorrect-$year"
                    }
                    else if(dayOfMonth < 10 && monthCorrect < 10) {
                        searchingDate = "0$dayOfMonth-0$monthCorrect-$year"
                    }
                    println("DATA_BUTTON: $array")
                    displayArray.clear()
                    array.forEach {
                        val item = it.get("date").toString()
                        if(item == searchingDate) {
                            displayArray.add(it)
                            println("WESZŁO")
                        }
                    }
                    listActivities.adapter = ListAdapter(this@MainActivity, displayArray)
                    val tempAdapter = listActivities.adapter as ListAdapter
                    tempAdapter.notifyDataSetChanged()

                }, year, month, day)
            dataDialog.show()
        }

    }

    // RESULT FROM FormActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                val name = data!!.getStringExtra("name")
                val type = data!!.getStringExtra("type")
                val date = data!!.getStringExtra("date")
                val time = data!!.getStringExtra("time")
                val desc = data!!.getStringExtra("desc")
                var json = JSONObject()
                json.put("icon", type)
                json.put("name", name)
                json.put("date", date)
                json.put("time", time)
                json.put("desc", desc)

                array.add(json)
                println(array)
                displayArray.add(json)
                println(displayArray)
                listActivities.adapter = ListAdapter(this, displayArray)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu!!.findItem(R.id.menu_search)

        if(searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text).hint = "Search activity"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {

                    if(text!!.isNotEmpty()) {
                        displayArray.clear()
                        val search = text.toLowerCase()
                        array.forEach{
                            val item = it.get("name").toString()
                            if(item.toLowerCase().contains(search)) {
                                displayArray.add(it)
                            }
                        }
                    }
                    else {
                        displayArray.clear()
                        array.forEach {
                            displayArray.add(it)
                        }
                        println(array)
                        println(displayArray)
                    }
                    listActivities.adapter = ListAdapter(this@MainActivity, displayArray)
                    val tempAdapter = listActivities.adapter as ListAdapter
                    tempAdapter.notifyDataSetChanged()
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun refresh(view:View) {
        displayArray.clear()
        array.forEach {
            displayArray.add(it)
        }
        listActivities.adapter = ListAdapter(this@MainActivity, displayArray)
        val tempAdapter = listActivities.adapter as ListAdapter
        tempAdapter.notifyDataSetChanged()
    }

}