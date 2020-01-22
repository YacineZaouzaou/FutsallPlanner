package com.example.futsallplanner.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.futsallplanner.Championship;
import com.example.futsallplanner.DisplayChampionshipActivity;
import com.example.futsallplanner.MatchAdapter;
import com.example.futsallplanner.R;
import com.example.futsallplanner.ScoreAdapter;

public class DashboardFragment extends Fragment {

    private ListView list;

    private ScoreAdapter adapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false) ;

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