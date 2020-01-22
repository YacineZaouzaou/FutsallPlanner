package com.example.futsallplanner;

import java.io.Serializable;

public class Team implements Serializable
{

    private String name ;
    private int points ;
    private int butMarque ;
    private int butEncaisse ;


    public Team(String name) {
        this.name = name ;
        this.points = 0 ;
        this.butEncaisse = 0 ;
        this.butMarque = 0 ;
    }

    public void processPlay (int scoored , int got) {
        this.butMarque += scoored ;
        this.butEncaisse += got ;
        if (scoored == got) {
            this.points++ ;
        }else if (scoored > got) {
            this.points += 3 ;
        }
    }

    public int getPoints () { return this.points ; }

    public int getButMarque () { return this.butMarque ; }

    public int getButEncaisse () { return this.butEncaisse ; }

    public String getName () { return  this.name ; }

    public int getMoyenneBut () { return (this.butMarque - this.butEncaisse) ; }


}
