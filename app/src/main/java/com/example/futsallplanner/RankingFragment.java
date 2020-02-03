package com.example.futsallplanner;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {


    public RankingFragment() {
        // Required empty public constructor
    }

    private ListView list;

    private ScoreAdapter adapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ranking, container, false);

        this.list = root.findViewById(R.id.score_teams) ;

        //Championship c = (Championship) getArguments().getSerializable("championship") ;

        Championship c = ((DisplayChampionshipActivity) getActivity()).getChampionship() ;

        this.adapter = new ScoreAdapter(getContext(), c.getTeams());
        this.list.setAdapter(this.adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("Test", "Position=" + position);

            }
        });

        return root;
    }

}
