package com.example.futsallplanner;

import android.util.Log;

import java.io.Serializable;


public class Match implements Serializable
{

    private Team team1 ;
    private Team team2 ;
    private boolean played ;
    private int goalsTeam1 ;
    private int goalsTeam2 ;

    public Match ( Team team1 , Team team2 ){
        if (team1.equals(team2)){
            Log.v("ERROR" , "un match entre une équipe et elle même") ;
        }
        this.team1 = team1 ;
        this.team2 = team2 ;
        this.goalsTeam1 = 0 ;
        this.goalsTeam2 = 0 ;
        this.played = false ;

    }

    public Match (Team t1 , Team t2 , int s1 , int s2)
    {
        this.team1 = t1 ;
        this.team2 = t2 ;
        this.goalsTeam1 = s1 ;
        this.goalsTeam2 = s2 ;
        this.played = true ;
    }


    public Team getTeam1 () { return this.team1 ; }

    public Team getTeam2 () { return this.team2 ; }

    public int getScore1 () { return this.goalsTeam1 ; }

    public int getScore2 () { return this.goalsTeam2 ; }

    public void setGoalsTeam1(int goalsTeam1) {
        this.goalsTeam1 = goalsTeam1;
    }

    public void setGoalsTeam2(int goalsTeam2) {
        this.goalsTeam2 = goalsTeam2;
    }

    public boolean isPlayed () { return this.played ; }

    public void play (int goals1 , int goals2) {
        this.goalsTeam1 = goals1 ;
        this.goalsTeam2 = goals2 ;
        this.team1.processPlay(goals1 , goals2) ;
        this.team2.processPlay(goals2 , goals1) ;
        this.played = true ;
    }

    @Override
    public String toString() {
        return "["+team1+"] ["+team2+"]";
    }
}
