package com.example.planyourday

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_form.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val DEFAULT_TEXT_TIMER:String = "Choose time"

class FormActivity : AppCompatActivity() {

    private val dataJSON = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        // DEFAULT RADIO BUTTON
        radioGroup.check(information_radio.id)

        // CURRENT DATE
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            dataPicker.text = current.format(formatter)
        }
        else {
            var date = Date()
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            dataPicker.text = formatter.format(date)
        }

        // CALENDAR - data ELEMENTS
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // CALENDAR - dataPicker
        dataPicker.setOnClickListener {
            val dataDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val monthCorrect = month + 1
                    if(dayOfMonth > 10 && monthCorrect > 10) {
                        dataPicker.text = "$dayOfMonth-$monthCorrect-$year"
                    }
                    else if(dayOfMonth < 10 && monthCorrect > 10) {
                        dataPicker.text = "0$dayOfMonth-$monthCorrect-$year"
                    }
                    else if(dayOfMonth > 10 && monthCorrect < 10) {
                        dataPicker.text = "$dayOfMonth-0$monthCorrect-$year"
                    }
                    else if(dayOfMonth < 10 && monthCorrect < 10) {
                        dataPicker.text = "0$dayOfMonth-0$monthCorrect-$year"
                    }
                }, year, month, day)
            dataDialog.show()
        }

        // CALENDAR - timerPicker
        timerPicker.setOnClickListener {
            val timer = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                timer.set(Calendar.HOUR_OF_DAY, hourOfDay)
                timer.set(Calendar.MINUTE, minute)
                println(SimpleDateFormat("HH:mm").format(timer.time))
                timerPicker.text = SimpleDateFormat("HH:mm").format(timer.time)
            }
            TimePickerDialog(this, timeSetListener,
                timer.get(Calendar.HOUR_OF_DAY),
                timer.get(Calendar.MINUTE),
                true).show()
        }

        // RADIO BUTTONS LISTENER
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{ _, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                when(radio.text.toString()) {
                    "Information" -> { icon.setImageResource(R.drawable.information) }
                    "House" -> { icon.setImageResource(R.drawable.house) }
                    "Shopping" -> { icon.setImageResource(R.drawable.shopping) }
                    "Travel" -> { icon.setImageResource(R.drawable.travel) }
                }
            }
        )

        // CONFIRM BUTTON
        confirm_button.setOnClickListener {
            val valueOfName =  name_EditText.text.toString()
            val valueOfType = findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
            val valueOfDate = dataPicker.text.toString()
            val valueOfTime = timerPicker.text.toString()
            val valueOfDescriber = describer.text.toString()

            if (valueOfName.isNotEmpty() && valueOfTime != DEFAULT_TEXT_TIMER) {
                /**
                 * JSON problems

                dataJSON.put("name", valueOfName)
                dataJSON.put("type", valueOfType)
                dataJSON.put("date", valueOfDate)
                dataJSON.put("time", valueOfTime)
                dataJSON.put("desc", valueOfDescriber)
                println(dataJSON)
                */

                val intent = Intent()
                intent.putExtra("name", valueOfName)
                intent.putExtra("type", valueOfType)
                intent.putExtra("date", valueOfDate)
                intent.putExtra("time", valueOfTime)
                intent.putExtra("desc", valueOfDescriber)
                setResult(Activity.RESULT_OK, intent)
                super.finish()
            }
            else if(valueOfName == "") {
                Toast.makeText(applicationContext, R.string.alert_empty,
                    Toast.LENGTH_SHORT).show()
            }
            else if(valueOfTime == "Choose time") {
                Toast.makeText(applicationContext, R.string.alert_time,
                    Toast.LENGTH_SHORT).show()
            }

        }
    }
}
