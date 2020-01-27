package com.example.futsallplanner;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends Fragment {


    public MatchFragment() {
        // Required empty public constructor
    }

    private ArrayList<Match> debug ;

    private ListView list;

    private MatchAdapter adapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_match, container, false);
        this.list = root.findViewById(R.id.matchs) ;
        //Championship c = (Championship) getArguments().getSerializable("championship") ;

        Championship c = ((DisplayChampionshipActivity) getActivity()).getChampionship() ;

        this.adapter = new MatchAdapter(getContext(), c.getMatches());
        this.list.setAdapter(this.adapter);


        return root;
    }

}
