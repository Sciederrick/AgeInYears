package ke.solvitlabs.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val rightNow = Calendar.getInstance()
        val year = rightNow.get(Calendar.YEAR)
        val month = rightNow.get(Calendar.MONTH)
        val day = rightNow.get(Calendar.DAY_OF_MONTH)

        // get reference to textViews
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val tvAgeInYears = findViewById<TextView>(R.id.tvAgeInYears)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)

            // DATE format for selected date
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val formattedSelectedDate = sdf.parse(selectedDate)

            // current date
            val currentDate = "$day/${month+1}/$year"
            val formattedCurrentDate = sdf.parse(currentDate)

            // difference
            val diffInMilliseconds = formattedCurrentDate.getTime() - formattedSelectedDate.getTime()
            val ageInYears = TimeUnit.MILLISECONDS.toHours(diffInMilliseconds) / (24 * 365)
            tvAgeInYears.setText(ageInYears.toString())


        }, year, month, day).show()
    }
}