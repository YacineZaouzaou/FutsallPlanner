package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.futsallplanner.util.DatabaseManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button create_button ;
    private DatabaseManager dbm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbm = new DatabaseManager( getApplicationContext() ) ;
        if ( ! this.dbm.isEmpty()) {

            List<Team> teams = this.dbm.getAllTeams () ;

            for (Team t : teams)
            {
                Log.e ("TEAMS" , t.getPlayer1()+" "+t.getPlayer2()+" "+t.getPoints()) ;
            }

            List<Match> matchs = this.computeMatch (teams) ;

            Championship.loadChampionship(teams , matchs);

            Intent i = new Intent (getApplicationContext() , DisplayChampionshipActivity.class ) ;

            startActivity(i) ;
        }
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


    private List<Match> computeMatch (List<Team> t)
    {
        List<Match> ret = new ArrayList<>() ;
        for (int i = 0 ; i < t.size() - 1 ; i ++){
            for (int j = i + 1 ; j < t.size() ; j ++ ) {
                int [] res = this.dbm.getResultOfMatch(t.get(i).getPlayer1() , t.get(i).getPlayer2() , t.get(j).getPlayer1() , t.get(j).getPlayer2()) ;
                Match m ;
                if (res != null) {
                     m = new Match(t.get(i) , t.get(j) , res[0] , res[1]) ;
                    Log.e("add" ,m.getTeam2().getPlayer1() + t.get(j).getPlayer2()) ;
                    Log.e(" jjj" , res[0] +" "+ res[1]) ;

                }else {
                     m = new Match(t.get(i) , t.get(j)) ;
                }
                ret.add(m) ;

            }
        }
        return ret ;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
