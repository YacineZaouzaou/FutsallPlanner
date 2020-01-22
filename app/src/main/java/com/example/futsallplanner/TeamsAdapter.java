package com.example.futsallplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TeamsAdapter extends BaseAdapter {

    private Context context ;

    private List<String> list ;


    public TeamsAdapter (Context c ) {
        this.context = c ;
        this.list = new ArrayList<>() ;
    }




    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position) ;
    }

    public void addItem (String value) { this.list.add(value) ; }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamsViewHolder tvh ;
        View v = null ;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater ) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE) ;
            v = inflater.inflate(R.layout.team_s_name_created , parent , false) ;
            tvh = new TeamsViewHolder( (TextView ) v.findViewById(R.id.team_s_name_created_value) ) ;
            v.setTag(tvh) ;
        } else {
            tvh = (TeamsViewHolder) convertView.getTag() ;
            v = convertView ;
        }

        tvh.name.setText(list.get(position));

        return v ;
    }

    public List<String> getNames () {
        return this.list ;
    }


    public static class TeamsViewHolder
    {
        TextView name ;
        public TeamsViewHolder (TextView name) {
            this.name = name ;
        }
    }
}
