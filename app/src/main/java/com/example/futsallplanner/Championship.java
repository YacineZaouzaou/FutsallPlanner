package com.example.futsallplanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Championship implements Serializable
{

    private String name ;
    private List<Team> teams ;
    private List<Match> matches ;
    private Map<Team , Integer> classement ;

    public Championship (String name , List<String> teams){
        this.name = name ;
        computeTeams (teams) ;
        computeMatchs () ;

    }

    private void computeTeams (List<String> names) {
        this.teams = new ArrayList<>() ;
        for (String name : names)
            this.teams.add(new Team(name)) ;

    }

    private void computeMatchs () {
        this.matches = new ArrayList<>() ;
        for (int i = 0 ; i < teams.size() - 1 ; i ++)
            for (int j = i + 1 ; j < teams.size() ; j ++)
                this.matches.add(new Match(this.teams.get(i) , this.teams.get(j))) ;
    }

    public List<Match> getMatches () { return this.matches ; }

    public List<Team> getTeams () { return this.teams ; }
}
