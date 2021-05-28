package com.example.nguyenhuydong_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<Test> testList;
    private RecyclerView recyclerView;
    private TestAdapter adapter;
    private FloatingActionButton fab;
    private TextView tongTv;

    @Override
    protected void onRestart() {
        super.onRestart();
        testList = databaseHelper.getAllTest();
        adapter.setData(testList);
        tong();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        testList = databaseHelper.getAllTest();

        tongTv = findViewById(R.id.tongTv);
        tong();
        recyclerView = findViewById(R.id.recycleView);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
        adapter = new TestAdapter(this);
        adapter.setData(testList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                testList = databaseHelper.search(query);
                adapter.setData(testList);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                testList = databaseHelper.search(newText);
                adapter.setData(testList);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    void tong(){
        int tong = 0;
        for(int i = 0; i < testList.size(); i++){
            if(testList.get(i).isWrite()){
                tong++;
            }
        }
        tongTv.setText("Tong: " + tong);
    }
}