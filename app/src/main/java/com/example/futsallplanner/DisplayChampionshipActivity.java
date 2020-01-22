package com.example.futsallplanner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class DisplayChampionshipActivity extends AppCompatActivity {

    private Championship championship ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_championship);

        BottomNavigationView navView = findViewById(R.id.nav_view);


        Intent savedValues = getIntent() ;
        List<String> names = savedValues.getStringArrayListExtra("names") ;
        String championshipName = savedValues.getStringExtra("championshipName") ;
        this.championship = new Championship (championshipName , names) ;
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle() ;
        }
        savedInstanceState.putSerializable ("championship" , this.championship);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.navigate(R.id.navigation_home , savedInstanceState);
        navController.navigate(R.id.navigation_dashboard , savedInstanceState);


        /* getting values from the the creating activity */


        Bundle savedInstance = new Bundle ( )  ;



        /*
        String [] args = new String[names.size() + 1] ;
        args[0] = championshipName ;
        for (int i = 0 ; i < args.length ; i ++ ){
            args[0] = names.get(i - 1) ;
        }
        new ComputeNewChampionship().execute(args) ;
        */


    }



    public Championship getChampionship () { return this.championship ; }



    /* if i had more time */
    private static class ComputeNewChampionship extends AsyncTask <String , Integer , Championship>
    {
        @Override
        public Championship doInBackground (String ... params) {
            String championshipName = params[0] ;
            ArrayList<String> teams = new ArrayList<>() ;
            for (int i = 1 ; i < params.length ; i ++) {
                teams.add(params[i]) ;
            }
            return new Championship(championshipName , teams);
        }
    }

}
