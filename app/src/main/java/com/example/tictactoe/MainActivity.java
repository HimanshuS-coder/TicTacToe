package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //  0:yellow  1:red    2:empty

    int activeplayer=0;
    int[] gamestate={2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winings={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean activegame=true;

    public void dropit(View view){



        ImageView counter = (ImageView) view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());

        //gamestate[tappedcounter]=activeplayer;


        if (gamestate[tappedcounter]==2 && activegame) {

            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1500).setDuration(300);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).setDuration(300);
                activeplayer = 0;

            }

            Log.i("Array", Arrays.toString(gamestate));

            for (int[] wining : winings) {
                if ((gamestate[wining[0]] == gamestate[wining[1]]) && (gamestate[wining[1]] == gamestate[wining[2]]) && (gamestate[wining[0]] != 2)) {

                    activegame=false;
                    String winner;
                    if (activeplayer == 0) {
                        //Toast.makeText(this, "Red won", Toast.LENGTH_SHORT).show();
                        winner="Red";
                    } else {
                        //Toast.makeText(this, "Yellow won", Toast.LENGTH_SHORT).show();
                        winner="Yellow";
                    }
                    Button playagainbutton=(Button) findViewById(R.id.playagainbutton);
                    TextView winnertext=(TextView) findViewById(R.id.winnertext);
                    winnertext.setText(winner + " has won");
                    playagainbutton.setTranslationY(-400);
                    playagainbutton.setRotation(-36000);
                    winnertext.setTranslationY(-400);
                    playagainbutton.setVisibility(View.VISIBLE);
                    playagainbutton.animate().translationYBy(400).rotation(36000).alpha(1).setDuration(1000);
                    winnertext.animate().translationYBy(400).alpha(1).setDuration(1000);
                    winnertext.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    public void plantain(View view){

        Button playagainbutton=(Button) findViewById(R.id.playagainbutton);

        TextView winnertext=(TextView) findViewById(R.id.winnertext);

        playagainbutton.setVisibility(View.INVISIBLE);

        winnertext.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=findViewById(R.id.gridlayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){

            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gamestate.length;i++) {
            gamestate[i] = 2;
        }
        activeplayer=0;
        activegame=true;
        playagainbutton.setAlpha(0);

        Log.i("INfo","Button pressed");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
