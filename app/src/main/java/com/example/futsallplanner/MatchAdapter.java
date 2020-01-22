package com.example.futsallplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        mvh.team1.setText(m.getTeam1().getName()) ;
        mvh.team2.setText(m.getTeam2().getName()) ;
        mvh.score1.setText(Integer.toString(m.getScore1())) ;
        mvh.score2.setText(Integer.toString(m.getScore2())) ;
        return runder;
    }


    public static class MatchViewHolder {

        TextView team1 ;
        TextView team2 ;
        TextView score1 ;
        TextView score2 ;

        public MatchViewHolder (View v) {
            this.team1 = v.findViewById(R.id.team1_name) ;
            this.team2 = v.findViewById(R.id.team2_name) ;
            this.score1 = v.findViewById(R.id.team1_score) ;
            this.score2 = v.findViewById(R.id.team2_score) ;
        }

    }
}
