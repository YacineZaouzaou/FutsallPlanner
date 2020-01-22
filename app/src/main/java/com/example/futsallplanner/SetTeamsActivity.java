package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SetTeamsActivity extends AppCompatActivity {

    private ListView        teams ;
    private EditText        teamName ;
    private TeamsAdapter    teamsAdapter ;
    private Button          addTeamButton ;
    private Button          createChampionship ;
    private TextView        championshipName ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_teams);
        championshipName = findViewById(R.id.championshipName_create_team) ;
        Intent saved_values = getIntent() ;
        championshipName.setText(saved_values.getStringExtra("championshipName")) ;

        /* dealing with the list view */

        this.teams = (ListView) findViewById(R.id.teams) ;
        this.teamsAdapter = new TeamsAdapter( this ) ;
        this.teams.setAdapter(this.teamsAdapter) ;


        /* dealing with name team field ( text area ) */

        this.teamName = findViewById(R.id.team_name) ;

        /* dealing with add team button  */

        this.addTeamButton = (Button) findViewById(R.id.add_team_button) ;
        this.addTeamButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v){
                teamsAdapter.addItem(teamName.getText().toString() ) ;
                teamsAdapter.notifyDataSetChanged() ;

            }
        });

        /* dealing with the create championship button  */

        this.createChampionship = (Button) findViewById(R.id.create_championship) ;
        this.createChampionship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                start_matchActivity () ;
            }
        });
    }

    public void start_matchActivity () {
        Intent intent = new Intent(getApplicationContext() , DisplayChampionshipActivity.class) ;
        ArrayList<String> names = (ArrayList<String>) this.teamsAdapter.getNames () ;
        intent.putExtra("names" , names) ;
        intent.putExtra("championshipName" , this.championshipName.getText().toString()) ;
        Log.v("DEBUG" , "start matche activity") ;
        startActivity(intent) ;
    }


}
