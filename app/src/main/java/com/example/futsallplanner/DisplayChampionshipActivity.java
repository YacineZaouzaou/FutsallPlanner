package com.example.futsallplanner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class DisplayChampionshipActivity extends AppCompatActivity {

    private Championship championship; ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_championship);

        //BottomNavigationView navView = findViewById(R.id.nav_view);


        Intent savedValues = getIntent() ;
        if(!Championship.checkInstanceExist()){
            List<String> players = savedValues.getStringArrayListExtra("players") ;
            Championship.init(players);
        }
        this.championship = Championship.getIstance() ;

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle() ;
        }

        savedInstanceState.putSerializable ("championship" , this.championship);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new OrderPageAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager, new TabLayoutMediator.OnConfigureTabCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 : {
                        tab.setText("Games");
                        tab.setIcon(R.drawable.ic_games);
                        long matchNotPlayed = championship.getMatches().stream().filter(m -> m.isPlayed()==false).count();
                        if(matchNotPlayed > 0){
                            BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                            badgeDrawable.setBackgroundColor(
                                    ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)
                            );
                            badgeDrawable.setVisible(true);
                            badgeDrawable.setNumber((int)matchNotPlayed);
                        }
                        break;
                    }
                    case 1 : {
                        tab.setText("Ranking");
                        tab.setIcon(R.drawable.ic_view_list);
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.navigate(R.id.navigation_home , savedInstanceState);
        navController.navigate(R.id.navigation_dashboard , savedInstanceState);*/


        /* getting values from the the creating activity */


        //Bundle savedInstance = new Bundle ( )  ;



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
    /*private static class ComputeNewChampionship extends AsyncTask <String , Integer , Championship>
    {
        @Override
        public Championship doInBackground (String ... params) {
            String championshipName = params[0] ;
            ArrayList<String> teams = new ArrayList<>() ;
            for (int i = 1 ; i < params.length ; i ++) {
                teams.add(params[i]) ;
            }
            return new Championship(teams);
        }
    }*/

}
