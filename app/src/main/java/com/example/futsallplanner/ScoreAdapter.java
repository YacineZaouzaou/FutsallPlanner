package com.example.futsallplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private List<Team> teams ;
    private Context context ;


    public ScoreAdapter(Context c , List <Team> teams ) {
        this.context = c ;
        this.teams = teams ;
    }

    @Override
    public int getCount() {
        return this.teams.size();
    }

    @Override
    public Object getItem(int position) {
        return this.teams.get(position) ;
    }


    /* not used */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScoreViewHolder svh = null ;
        View runder  = null ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater ) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE) ;
            runder = inflater.inflate(R.layout.score_display , parent , false) ;
            svh = new ScoreAdapter.ScoreViewHolder ( runder ) ;
            runder.setTag(svh) ;
        } else {
           svh = (ScoreAdapter.ScoreViewHolder) convertView.getTag() ;
           runder = convertView ;
        }
        Team t = teams.get (position) ;
        svh.teamsName.setText(t.getPlayer1()+" "+t.getPlayer2()) ;
        svh.points.setText(Integer.toString(t.getPoints())) ;
        svh.bp.setText(Integer.toString(t.getScoored())) ;
        svh.bc.setText(Integer.toString(t.getGot())) ;
        svh.ga.setText(Integer.toString(t.getMoyenneBut())) ;

        return runder;
    }


    public static class ScoreViewHolder {

        TextView teamsName ;
        TextView points ;
        TextView bp ;
        TextView bc ;
        TextView ga ;

        public ScoreViewHolder (View v) {
            this.teamsName = v.findViewById(R.id.team) ;
            this.points = v.findViewById(R.id.teamPoint) ;
            this.bp = v.findViewById(R.id.scored) ;
            this.bc = v.findViewById(R.id.got) ;
            this.ga = v.findViewById(R.id.moyen) ;
        }
    }
}
