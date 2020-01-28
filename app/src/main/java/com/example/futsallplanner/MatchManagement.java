package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

public class MatchManagement extends AppCompatActivity {

    Match       match;
    Chip        team1Player1;
    Chip        team1Player2;
    Chip        team2Player1;
    Chip        team2Player2;
    TextView    team1Score;
    TextView    team2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_management);

        Intent saved_values = getIntent() ;
        this.match = (Match) saved_values.getSerializableExtra("match");

        team1Player1 = (Chip) findViewById(R.id.matchTeam1Player1);
        team1Player2 = (Chip) findViewById(R.id.matchTeam1Player2);
        team2Player1 = (Chip) findViewById(R.id.matchTeam2Player1);
        team2Player2 = (Chip) findViewById(R.id.matchTeam2Player2);
        team1Score = (TextView) findViewById(R.id.matchScoreTeam1);
        team2Score = (TextView) findViewById(R.id.matchScoreTeam2);

        team1Player1.setText(match.getTeam1().getPlayer1());
        team1Player2.setText(match.getTeam1().getPlayer2());
        team2Player1.setText(match.getTeam2().getPlayer1());
        team2Player2.setText(match.getTeam2().getPlayer2());
        team1Score.setText(""+match.getScore1());
        team2Score.setText(""+match.getScore2());

    }
}
