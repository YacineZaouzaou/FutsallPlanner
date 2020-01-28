package com.example.futsallplanner.util;

import com.example.futsallplanner.Team;

public class Util {

    private Team team;
    private int point;

    public Util(Team team, int point) {
        this.team = team;
        this.point = point;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
