package com.example.futsallplanner;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.example.futsallplanner.util.DatabaseManager;
import com.example.futsallplanner.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import androidx.annotation.RequiresApi;

public class Championship implements Serializable
{

    private List<Team> teams ;
    private List<Match> matches ;
    private static List<String> players = null;

    private static Championship championship = null;

    private Championship (List<String> players){
        computeTeams (players) ;
        computeMatchs () ;
    }

    private Championship () {

    }

    public static void loadChampionship (List<Team> teams , List<Match> matchs )
    {
        Championship.championship = new Championship() ;
        Championship.championship.teams = teams ;
        Championship.championship.matches = matchs ;
        Championship.players = Championship.championship.compputePlayers() ;


    }

    private List<String> compputePlayers ()
    {
        Set<String> s = new HashSet<>() ;
        for (Team t : this.teams){
            s.add(t.getPlayer1()) ;
            s.add(t.getPlayer2()) ;
        }
        return new ArrayList<>(s) ;
    }

    public static Championship getIstance(){
        if (championship == null){
            if (players == null){
                throw new AssertionError("You have to call init first");
            }
            else{
                championship = new Championship(players);
                return championship;
            }
        }
        else return championship;
    }

    public static void init(List<String> params){
        if (championship != null)
        {
            // in my opinion this is optional, but for the purists it ensures
            // that you only ever get the same instance when you call getInstance
            throw new AssertionError("You already initialized me");
        }
        else{
            players = params;
        }
    }

    public static boolean checkInstanceExist(){
        return championship !=null ? true : false;
    }

    private void computeTeams (List<String> players) {
        this.teams = new ArrayList<>() ;
        Collections.shuffle(players);
        for (int i =0 ; i<players.size() ; i+=2){
            this.teams.add(new Team(players.get(i), players.get(i+1))) ;
        }
        Log.d("Team",teams.toString());
        // add to database
    }

    private void computeMatchs () {
        this.matches = new ArrayList<>() ;
        for (int i = 0 ; i < teams.size() - 1 ; i ++)
            for (int j = i + 1 ; j < teams.size() ; j ++)
                this.matches.add(new Match(this.teams.get(i) , this.teams.get(j))) ;

        Log.d("Matchs",matches.toString());
    }

    public static void reset ()
    {
        Championship.championship = null ;
    }

    public List<Match> getMatches () { return this.matches ; }

    public List<Team> getTeamsSimple ()
    {
        return this.teams ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Team> getTeams () { return this.teams.stream().map(t -> new Util(t, t.getPoints())).sorted(Comparator.comparingLong(Util::getPoint).reversed())
            .map(u -> new Team(u.getTeam().getPlayer1(), u.getTeam().getPlayer2(), u.getTeam().getPoints(), u.getTeam().getScoored(), u.getTeam().getGot())).collect(Collectors.toList()) ; }
}
