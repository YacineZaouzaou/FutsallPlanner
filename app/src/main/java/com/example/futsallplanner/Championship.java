package com.example.futsallplanner;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Championship implements Serializable
{

    private List<Team> teams ;
    private List<Match> matches ;
    private Map<Team , Integer> classement ;

    public Championship (List<String> players){
        computeTeams (players) ;
        computeMatchs () ;

    }

    private void computeTeams (List<String> players) {
        this.teams = new ArrayList<>() ;
        Collections.shuffle(players);
        for (int i =0 ; i<players.size() ; i+=2){
            this.teams.add(new Team(players.get(i), players.get(i+1))) ;
        }
        Log.d("Team",teams.toString());
    }

    private void computeMatchs () {
        this.matches = new ArrayList<>() ;
        for (int i = 0 ; i < teams.size() - 1 ; i ++)
            for (int j = i + 1 ; j < teams.size() ; j ++)
                this.matches.add(new Match(this.teams.get(i) , this.teams.get(j))) ;

        Log.d("Matchs",matches.toString());
    }

    public List<Match> getMatches () { return this.matches ; }

    public List<Team> getTeams () { return this.teams ; }
}
