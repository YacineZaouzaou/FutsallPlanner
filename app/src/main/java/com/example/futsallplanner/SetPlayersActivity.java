package com.example.futsallplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SetPlayersActivity extends AppCompatActivity {

    private ListView        teams ;
    private EditText        teamName ;
    private TeamsAdapter    teamsAdapter ;
    private Button          addTeamButton ;
    private Button          createChampionship ;
    private TextView        championshipName ;

    MaterialButton      btnAddPlayer, btnGenerateTournament;
    ChipGroup           chipGroup;
    TextInputEditText   addPlayerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_players);

        //view
        btnAddPlayer = (MaterialButton) findViewById(R.id.btnAddPlayer);
        btnGenerateTournament = (MaterialButton) findViewById(R.id.btnGenerateTournament);
        chipGroup = (ChipGroup) findViewById(R.id.chioGroup);
        addPlayerText = (TextInputEditText) findViewById(R.id.addPlayerText);

        btnAddPlayer.setEnabled(false);
        btnGenerateTournament.setEnabled(false);

        addPlayerText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //refreshButtons(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0)
                    btnAddPlayer.setEnabled(false);
                else
                    btnAddPlayer.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnAddPlayer.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v){
                String player = addPlayerText.getText().toString().split(" ")[0];
                addPlayerText.setText("");

                if(checkPlayerExist(player))
                    Toast.makeText(SetPlayersActivity.this, "Player Already exist", Toast.LENGTH_LONG).show();
                else {
                    LayoutInflater inflater = LayoutInflater.from(SetPlayersActivity.this);
                    Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
                    chip.setText(player);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipGroup.removeView(v);
                            refreshGeneratButton();
                        }
                    });
                    chipGroup.addView(chip);
                }
                refreshGeneratButton();
            }
        });

        btnGenerateTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> players = new ArrayList<>();
                for(int i = 0 ; i< chipGroup.getChildCount(); i++){
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if(i <= chipGroup.getChildCount())
                        players.add(chip.getText().toString());
                }
                Intent intent = new Intent(getApplicationContext() , DisplayChampionshipActivity.class) ;
                intent.putExtra("names" , players) ;
                intent.putExtra("championshipName" , "Babyfoot Tournament") ;
                Log.v("DEBUG" , "start matche activity") ;
                startActivity(intent) ;
            }
        });


        /*championshipName = findViewById(R.id.championshipName_create_team) ;
        Intent saved_values = getIntent() ;
        championshipName.setText(saved_values.getStringExtra("championshipName")) ;

        *//* dealing with the list view *//*

        this.teams = (ListView) findViewById(R.id.teams) ;
        this.teamsAdapter = new TeamsAdapter( this ) ;
        this.teams.setAdapter(this.teamsAdapter) ;


        *//* dealing with name team field ( text area ) *//*

        this.teamName = findViewById(R.id.team_name) ;

        *//* dealing with add team button  *//*

        this.addTeamButton = (Button) findViewById(R.id.add_team_button) ;
        this.addTeamButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v){
                teamsAdapter.addItem(teamName.getText().toString() ) ;
                teamsAdapter.notifyDataSetChanged() ;

            }
        });

        *//* dealing with the create championship button  *//*

        this.createChampionship = (Button) findViewById(R.id.create_championship) ;
        this.createChampionship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                start_matchActivity () ;
            }
        });*/
    }

    public void start_matchActivity () {
        Intent intent = new Intent(getApplicationContext() , DisplayChampionshipActivity.class) ;
        ArrayList<String> names = (ArrayList<String>) this.teamsAdapter.getNames () ;
        intent.putExtra("names" , names) ;
        intent.putExtra("championshipName" , this.championshipName.getText().toString()) ;
        Log.v("DEBUG" , "start matche activity") ;
        startActivity(intent) ;
    }

    public boolean checkPlayerExist(String player){
        for(int i = 0 ; i< chipGroup.getChildCount(); i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if(chip.getText().equals(player))
                return true;
        }
        return false;
    }

    public void refreshGeneratButton(){
        if(chipGroup.getChildCount()%2 == 0)
            if (chipGroup.getChildCount() < 6)
                btnGenerateTournament.setEnabled(false);
            else
                btnGenerateTournament.setEnabled(true);
        else
            btnGenerateTournament.setEnabled(false);
    }


}
