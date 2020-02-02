package com.example.futsallplanner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.futsallplanner.util.DatabaseManager;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.List;

public class DisplayChampionshipActivity extends AppCompatActivity {

    private Championship championship;

    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_championship);

        mContext = this.getApplicationContext() ;

        //BottomNavigationView navView = findViewById(R.id.nav_view);

        OnBackPressedCallback callBack= new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed () {
                back_pressed();
            }
        } ;

        getOnBackPressedDispatcher().addCallback(this, callBack);



        Intent savedValues = getIntent() ;
        if(!Championship.checkInstanceExist()){
            List<String> players = savedValues.getStringArrayListExtra("players") ;
            Championship.init(players);
            DatabaseManager db = new DatabaseManager (getApplicationContext()) ;
            for (Team t :Championship.getIstance().getTeamsSimple()) {
                db.insertIntoTeams(t.getPlayer1() , t.getPlayer2() , t.getPoints() , t.getScoored() , t.getGot() , t.getMoyenneBut());
            }
        }

        this.championship = Championship.getIstance() ;

        for (Match m : this.championship.getMatches()){
            Log.v("match : ", m.getTeam1().getPlayer1() + m.getTeam1().getPlayer2()) ;
        }

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
                        Log.v("MATHC3 " , championship.getMatches().toString()) ;
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
    }

    private void back_pressed () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setCancelable(true);
        builder.setTitle("EXIT");
        builder.setMessage("Do you really want to exit the tournement");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext() , MainActivity.class) ;
                        startActivity(i) ;
                        Championship.reset () ;
                        DatabaseManager db = new DatabaseManager(getApplicationContext()) ;
                        db.reset();
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public Championship getChampionship () { return this.championship ; }

}
