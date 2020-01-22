package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class SelectChampionshipActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_select_championship) ;
        Intent savedValues = getIntent() ;
        List<String> names = savedValues.getStringArrayListExtra("names") ;
        String championshipName = savedValues.getStringExtra("championshipName") ;


    }
}
