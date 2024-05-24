package id.ac.binus.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import kotlinx.coroutines.scheduling.Task;

public class HomeMain extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;
    FloatingActionButton btnAction;

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String txtMessage;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        calendarView = findViewById(R.id.calenderView);
        calendar = Calendar.getInstance();

        btnAction = findViewById(R.id.actionBtn);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        sharedPreference = new SharedPreference(this);
        arrayList.addAll(sharedPreference.loadTasks());
        arrayAdapter.notifyDataSetChanged();

        //setDate(21, 5, 2024);
        // Set the calender view to the current date
        long currDate = System.currentTimeMillis();
        calendarView.setDate(currDate, false, true);

        getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                Toast.makeText(HomeMain.this,  day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent(HomeMain.this, MainActivity2.class);

        startActivityForResult(intent, Intent_Constant.INTENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Intent_Constant.INTENT_REQUEST_CODE){
            if(resultCode == Intent_Constant.INTENT_RESULT_CODE) {
                String txtMessage = data.getStringExtra(Intent_Constant.INTENT_MESSAGE);
                int day = data.getIntExtra(Intent_Constant.INTENT_DAY, -1); // Mendapatkan tanggal
                int month = data.getIntExtra(Intent_Constant.INTENT_MONTH, -1); // Mendapatkan bulan
                int year = data.getIntExtra(Intent_Constant.INTENT_YEAR, -1); // Mendapatkan tahun

                if(day != -1 && month != -1 && year != -1){
                    String taskWithDate = txtMessage + " - " + day + "/" + month + "/" + year;
                    arrayList.add(taskWithDate);
                    arrayAdapter.notifyDataSetChanged();
                    sharedPreference.saveTasks(arrayList);
                }
            }
        }
    }

    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}