package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button create_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_button = findViewById(R.id.create_championship_button) ;
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //championship_name = et.getText().toString() ;
                // run the new activity with passing the name of the championship
                Intent i = new Intent (getApplicationContext() , SetPlayersActivity.class ) ;

                startActivity(i) ;

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
