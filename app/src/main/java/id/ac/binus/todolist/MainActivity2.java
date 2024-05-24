package id.ac.binus.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    ImageButton btnDate;
    Button btnCancel;
    EditText edtDate;
    private int tahun, bulan, tanggal;
    private int tahun2,bulan2,tanggal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupbar);

        btnDate = findViewById(R.id.btnDate);
        edtDate = findViewById(R.id.edtDate);
        btnCancel = findViewById(R.id.btncancel);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(MainActivity2.this, R.style.CustomDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun2 = year;
                        bulan2 = month;
                        tanggal2 = dayOfMonth;

                        edtDate.setText(tanggal2 + "/" + (bulan2 + 1) + "/" + tahun2);
                    }
                }, tahun, bulan, tanggal);
                dialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun2 = calendar.get(Calendar.YEAR);
                bulan2 = calendar.get(Calendar.MONTH);
                tanggal2 = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(MainActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun2 = year;
                        bulan2 = month;
                        tanggal2 = dayOfMonth;

                        edtDate.setText(tanggal2 + "/" + (bulan2 + 1) + "/" + tahun2);
                    }
                },tahun2,bulan2,tanggal2);
                dialog.show();
            }
        });
    }

    public void saveButtonClicked(View v){
        String txtMessage = ((EditText)findViewById(R.id.addTask)).getText().toString();
        if(txtMessage.equals("")){

        }else {
            Intent intent = new Intent();
            intent.putExtra(Intent_Constant.INTENT_MESSAGE,txtMessage);
            intent.putExtra(Intent_Constant.INTENT_DAY, tanggal2);
            intent.putExtra(Intent_Constant.INTENT_MONTH, bulan2 + 1);
            intent.putExtra(Intent_Constant.INTENT_YEAR, tahun2);
            setResult(Intent_Constant.INTENT_RESULT_CODE,intent);
            finish();
        }
    }

}