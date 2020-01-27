package com.example.futsallplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.List;

public class MatchAdapter extends BaseAdapter {

    private Context context ;

    private List<Match> matchs ;

    public MatchAdapter (Context context , List<Match> matchs) {
        this.context = context ;
        this.matchs = matchs ;
    }

    @Override
    public int getCount() {
        return this.matchs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.matchs.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MatchViewHolder mvh = null ;
        View runder = null ;
        if (convertView == null ) {
            LayoutInflater inflater = (LayoutInflater ) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE) ;
            runder = inflater.inflate(R.layout.match_display , parent , false) ;
            mvh = new MatchAdapter.MatchViewHolder( runder ) ;
            runder.setTag(mvh) ;
        }else {
            mvh = (MatchAdapter.MatchViewHolder) convertView.getTag() ;
            runder = convertView ;
        }
        Match m = this.matchs.get(position) ;
        mvh.team1Player1.setText(m.getTeam1().getPlayer1()) ;
        mvh.team1Player2.setText(m.getTeam1().getPlayer2()) ;
        mvh.team2Player1.setText(m.getTeam2().getPlayer1()) ;
        mvh.team2Player2.setText(m.getTeam2().getPlayer2()) ;
        mvh.scoreTeam1.setText(Integer.toString(m.getScore1())) ;
        mvh.scoreTeam2.setText(Integer.toString(m.getScore2())) ;
        return runder;
    }


    public static class MatchViewHolder {

        Chip team1Player1 ;
        Chip team1Player2 ;
        Chip team2Player1 ;
        Chip team2Player2 ;
        TextView scoreTeam1 ;
        TextView scoreTeam2 ;

        public MatchViewHolder (View v) {
            this.team1Player1 = v.findViewById(R.id.team1Player1) ;
            this.team1Player2 = v.findViewById(R.id.team1Player2) ;
            this.team2Player1 = v.findViewById(R.id.team2Player1) ;
            this.team2Player2 = v.findViewById(R.id.team2Player2) ;
            this.scoreTeam1 = v.findViewById(R.id.scoreTeam1) ;
            this.scoreTeam2 = v.findViewById(R.id.scoreTeam2) ;
        }

    }
}
