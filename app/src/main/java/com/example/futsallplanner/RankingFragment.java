package com.example.futsallplanner;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        /*
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle() ;
        }

        savedInstanceState.putSerializable ("championship" , c);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_home , savedInstanceState);
        */

        return root;
    }

}