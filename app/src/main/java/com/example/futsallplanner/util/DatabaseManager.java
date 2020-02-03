package com.example.futsallplanner.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.futsallplanner.Match;
import com.example.futsallplanner.Team;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper
{

    private static final String DB_NAME = "BABYFOOTDB" ;
    private static final int VERSION = 1 ;

    public DatabaseManager (Context context) {
        super (context , DB_NAME , null , VERSION) ;
    }

    @Override
    public void onCreate (SQLiteDatabase db)
    {
        String teamTable = "create table teams  (" +
                "                       id_team integer primary key autoincrement," +
                "                       player1 text , player2 text , points integer , " +
                "                       bp integer , bc integer , average integer )" ;
        String matchTable = "create table matchs (" +
                "                       id_match integer primary key autoincrement," +
                "                       player1team1 text , player2team1 text , " +
                "                       player1team2 text , player2team2 text , " +
                "                       score1 integer, score2 integer)" ;

        db.execSQL(teamTable);
        db.execSQL(matchTable);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db , int oldVersion , int newVersion)
    {

    }

    public void updateTeam (String player1 , String player2 , int point , int bp , int bc , int average)
    {
        String update = "update teams set points = "+point+" , bp = "+bp+" , bc = "+bc+" , average = "+average+" " +
            "where player1 = '"+player1+"' and player2 = '"+player2+"'" ;
        this.getWritableDatabase().execSQL(update);
    }

    public void insertIntoTeams (String player1 , String player2 , int point , int bp , int bc , int average)
    {
        String insersion = "insert into teams (player1 , player2 , points , bp , bc , average) values " +
                "('"+player1+"' , '"+player2+"' , "+point+" , "+bp+" , "+bc+" , "+average+")" ;

        this.getWritableDatabase().execSQL(insersion);
    }

    public void insertIntoMatchs (String player1t1 , String player2t1 , String player1t2 , String player2t2 , int score1 , int score2)
    {
        String insersion = "insert into matchs (player1team1 , player2team1 , player1team2 , player2team2 , score1 , score2) values " +
                "('"+player1t1+"','"+player2t1+"','"+player1t2+"','"+player2t2+"',"+score1+","+score2+")" ;
        this.getWritableDatabase().execSQL(insersion);
    }

    public boolean isEmpty ()
    {
        String ask = "select * from teams" ;
        Cursor c = this.getReadableDatabase().rawQuery(ask , null);
        return c.getCount() == 0 ;
    }

    public List<Team> getAllTeams ()
    {
        String ask = "select * from teams" ;
        Cursor c = this.getReadableDatabase().rawQuery(ask , null) ;
        c.moveToFirst() ;
        List<Team> tl = new ArrayList<>() ;
        while ( ! c.isAfterLast() )
        {
            Team t = new Team (c.getString(1) , c.getString(2)  , c.getInt(3)  , c.getInt(4)  , c.getInt(5) ) ;
            tl.add(t) ;
            c.moveToNext() ;
        }
        return  tl ;
    }


    public int [] getResultOfMatch (String p1t1 , String p2t1 , String p1t2 , String p2t2 )
    {
        String ask = "select score1 , score2 from matchs where player1team1 = '"+p1t1+"' and " +
                "player2team1 = '"+p2t1+"' and player1team2 = '"+p1t2+"' and player2team2 = '"+p2t2+"'";
        Cursor c = this.getReadableDatabase().rawQuery(ask , null) ;
        c.moveToFirst() ;
        if (! c.isAfterLast())
        {
            return new int []{ c.getInt(0), c.getInt(1) } ;
        }
        return null ;
    }

    public List<Match> getAllMatch ()
    {
        String ask = "select * from matchs" ;
        Cursor c = this.getReadableDatabase().rawQuery(ask , null) ;
        c.moveToFirst() ;
        List<Match> ml = new ArrayList<>() ;
        while ( ! c.isAfterLast()){
           // Match m = new Match(c.getInt(1) , c.getInt(2) , c.getInt(3) , c.getInt(4 ) , c.getInt(5) , c.getInt(6)) ;
            //ml.add(m) ;
        }
        return null ;
    }


    public void reset ()
    {
        String dropTeam = "delete from teams" ;
        String dropmatchs = "delete from  matchs" ;
        this.getWritableDatabase().execSQL(dropTeam);
        this.getWritableDatabase().execSQL(dropmatchs);
    }

}
