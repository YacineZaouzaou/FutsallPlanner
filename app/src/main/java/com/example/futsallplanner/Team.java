package com.example.futsallplanner;

import java.io.Serializable;

public class Team implements Serializable
{

    private String player1 ;
    private String player2 ;
    private int points ;
    private int scoored ;
    private int got ;


    public Team(String player1, String player2) {
        this.points = 0 ;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void processPlay (int scoored , int got) {
        this.scoored += scoored ;
        this.got += got ;
        if (scoored == got) {
            this.points++ ;
        }else if (scoored > got) {
            this.points += 3 ;
        }
    }


    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getScoored() {
        return scoored;
    }

    public void setScoored(int scoored) {
        this.scoored = scoored;
    }

    public int getGot() {
        return got;
    }

    public void setGot(int got) {
        this.got = got;
    }

    public int getMoyenneBut () { return (this.scoored - this.got) ; }

    @Override
    public String toString() {
        return "("+player1+", "+player2+")";
    }
}
