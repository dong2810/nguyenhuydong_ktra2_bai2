package com.example.nguyenhuydong_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.StringTokenizer;

public class TestActivity extends AppCompatActivity {
    private EditText id,name,day,time;
    private Button dateBtn,timeBtn,okBtn,delBtn,cancelBtn;
    private CheckBox write;
    private Test test1;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        databaseHelper = new DatabaseHelper(this);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        day = findViewById(R.id.day);
        time = findViewById(R.id.time);
        dateBtn = findViewById(R.id.dateBtn);
        okBtn = findViewById(R.id.okBtn);
        delBtn = findViewById(R.id.delBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        timeBtn = findViewById(R.id.timeBtn);
        write = findViewById(R.id.write);

        getData();
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(TestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        day.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdate();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }



    void getData(){
        Intent intent = getIntent();
        if(intent.getSerializableExtra("thi")!= null){
            test1 = (Test) intent.getSerializableExtra("thi");
            id.setText(test1.getId() + "");
            name.setText(test1.getName());
            day.setText(test1.getDate());
            time.setText(test1.getTime());
            write.setChecked(test1.isWrite());
            id.setEnabled(false);

        } else {
            id.setEnabled(false);
            delBtn.setEnabled(false);
        }
    }
    void addUpdate(){
        Test test = new Test();
        test.setName(name.getText().toString());
        test.setDate(day.getText().toString());
        test.setTime(time.getText().toString());
        test.setWrite(write.isChecked());

        if(test1 != null){
            test.setId(test1.getId());
            databaseHelper.update(test);
        } else {
            databaseHelper.insert(test);
        }
        finish();
    }

    void delete(){
        databaseHelper.delete(test1.getId());
        finish();
    }

    void cancel(){
        finish();
    }
}