package com.example.futsallplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private List<Team> list ;
    private Context context ;


    public ScoreAdapter(Context c , List <Team> l ) {
        this.context = c ;
        this.list = l ;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position) ;
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
        Team t = list.get (position) ;
        svh.teamsName.setText(t.getName()) ;
        svh.points.setText(Integer.toString(t.getPoints())) ;
        svh.bp.setText(Integer.toString(t.getButMarque())) ;
        svh.bc.setText(Integer.toString(t.getButEncaisse())) ;
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
            this.teamsName = v.findViewById(R.id.score_teams_name) ;
            this.points = v.findViewById(R.id.score_teams_points) ;
            this.bp = v.findViewById(R.id.score_teams_bp) ;
            this.bc = v.findViewById(R.id.score_teams_bc) ;
            this.ga = v.findViewById(R.id.score_teams_ga) ;
        }
    }
}
