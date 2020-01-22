package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String championship_name ;
    private EditText et ;
    private Button create_button ;
    private Button choose_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* get EditText for the name of the championship */
        et = findViewById(R.id.championshipName) ;
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        create_button = findViewById(R.id.create_championship_button) ;
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                championship_name = et.getText().toString() ;
                // run the new activity with passing the name of the championship
                Intent i = new Intent (getApplicationContext() , SetTeamsActivity.class ) ;
                i.putExtra("championshipName" , championship_name) ;
                startActivity(i) ;

            }
        });

        choose_button = findViewById(R.id.choose_championship_button) ;
        choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                // just run the new activity
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
