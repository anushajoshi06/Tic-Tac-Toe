package com.example.gameconnect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    boolean gameisactive =true;
    int[] gameState ={2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2,}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

public void dropin(View view) {
    ImageView counter = (ImageView) view;
    int tapCounter = Integer.parseInt(counter.getTag().toString());
    if (gameState[tapCounter] == 2 && gameisactive) {
        gameState[tapCounter] = activeplayer;
        counter.setTranslationY(-1000f);
        if (activeplayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activeplayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activeplayer = 0;
        }
        counter.animate().translationYBy(1000f).setDuration(300);
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2) {
                gameisactive=false;
                String winner = "red";
                if (gameState[winningPosition[0]] == 0) {
                    winner = "yellow";
                }
                TextView winnermessage = (TextView) findViewById(R.id.editText2);
                winnermessage.setText(winner + " has won");
                LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
                layout.setVisibility(View.VISIBLE);
            } else {
                boolean gameisover = true;
                for (int counterstate : gameState) {
                    if (counterstate == 2) {
                        gameisover = false;
                    }
                }
                    if (gameisover) {
                        TextView winnermessage = (TextView) findViewById(R.id.editText2);
                        winnermessage.setText("it is a draw");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
                        layout.setVisibility(View.VISIBLE);
                    }

            }
        }
    }
}
    public void playAgain(View view){
        gameisactive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout2);
        layout.setVisibility(View.INVISIBLE);
        activeplayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;

        }
        GridLayout gridLayout = (GridLayout) findViewById((R.id.gridLayout));
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
