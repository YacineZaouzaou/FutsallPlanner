package com.example.futsallplanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class MatchManagement extends AppCompatActivity {

    Match           match;
    Championship    championship;

    Chip        team1Player1;
    Chip        team1Player2;
    Chip        team2Player1;
    Chip        team2Player2;
    TextView    team1Score;
    TextView    team2Score;
    Button      addScoreTeam1;
    Button      removeScoreTeam1;
    Button      addScoreTeam2;
    Button      removeScoreTeam2;
    Button      finishMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_management);

        Intent saved_values = getIntent() ;
        this.match = (Match) saved_values.getSerializableExtra("match");
        this.championship = Championship.getIstance();

        team1Player1 = (Chip) findViewById(R.id.matchTeam1Player1);
        team1Player2 = (Chip) findViewById(R.id.matchTeam1Player2);
        team2Player1 = (Chip) findViewById(R.id.matchTeam2Player1);
        team2Player2 = (Chip) findViewById(R.id.matchTeam2Player2);
        team1Score = (TextView) findViewById(R.id.matchScoreTeam1);
        team2Score = (TextView) findViewById(R.id.matchScoreTeam2);
        addScoreTeam1 = (Button) findViewById(R.id.addScoreTeam1);
        removeScoreTeam1 = (Button) findViewById(R.id.removeScoreTeam1);
        addScoreTeam2 = (Button) findViewById(R.id.addScoreTeam2);
        removeScoreTeam2 = (Button) findViewById(R.id.removeScoreTeam2);
        removeScoreTeam2 = (Button) findViewById(R.id.removeScoreTeam2);
        finishMatch = (Button) findViewById(R.id.finishMatch);

        team1Player1.setText(match.getTeam1().getPlayer1());
        team1Player2.setText(match.getTeam1().getPlayer2());
        team2Player1.setText(match.getTeam2().getPlayer1());
        team2Player2.setText(match.getTeam2().getPlayer2());
        team1Score.setText(""+match.getScore1());
        team2Score.setText(""+match.getScore2());

        addScoreTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.setGoalsTeam1(match.getScore1()+1);
                team1Score.setText(""+match.getScore1());
            }
        });

        removeScoreTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.setGoalsTeam1(match.getScore1()-1);
                team1Score.setText(""+match.getScore1());
            }
        });
        addScoreTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.setGoalsTeam2(match.getScore2()+1);
                team2Score.setText(""+match.getScore2());
            }
        });

        removeScoreTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.setGoalsTeam2(match.getScore2()-1);
                team2Score.setText(""+match.getScore2());
            }
        });

        finishMatch.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                match.play(match.getScore1(), match.getScore2());
                Championship.getIstance().getMatches().stream().filter(m -> m.toString().equals(match.toString())).forEach(m -> m.play(match.getScore1(), match.getScore2()));
                Intent intent = new Intent(getApplicationContext() , DisplayChampionshipActivity.class) ;
                startActivity(intent) ;
            }
        });

    }
}
