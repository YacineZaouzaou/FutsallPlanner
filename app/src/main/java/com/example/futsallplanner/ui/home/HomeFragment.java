package com.example.futsallplanner.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.futsallplanner.Championship;
import com.example.futsallplanner.DisplayChampionshipActivity;
import com.example.futsallplanner.Match;
import com.example.futsallplanner.MatchAdapter;
import com.example.futsallplanner.R;
import com.example.futsallplanner.Team;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList <Match> debug ;

    private ListView list;

    private MatchAdapter adapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        this.list = root.findViewById(R.id.matchs) ;
        //Championship c = (Championship) getArguments().getSerializable("championship") ;

        Championship c = ((DisplayChampionshipActivity) getActivity()).getChampionship() ;

        this.adapter = new MatchAdapter(getContext(), c.getMatches());
        this.list.setAdapter(this.adapter);




        /*if (savedInstanceState == null) {
            savedInstanceState = new Bundle() ;
        }

        savedInstanceState.putSerializable ("championship" , c);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.navigation_home , savedInstanceState);*/

        return root;
    }

    private void init_debug () {
        this.debug = new ArrayList<>() ;
        /*debug.add(new Match(new Team("a") , new Team("b"))) ;
        debug.add(new Match(new Team("a") , new Team("c"))) ;
        debug.add(new Match(new Team("b") , new Team("c"))) ;*/
    }
}
