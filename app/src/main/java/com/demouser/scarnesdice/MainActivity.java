package com.demouser.scarnesdice;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.util.Random;





public class MainActivity extends AppCompatActivity {

    private static int USER_TSCORE = 0;
    private static int COMP_TSCORE = 0;
    private static int USER_FSCORE = 0;
    private static int COMP_FSCORE = 0;
    private static int numOfComprolls = 0;
    private final int maxNumOfComprolls = 4;


    TextView your_score;
    TextView comp_score;
    TextView turn_score;
    TextView comp_turn_score;
    Button rollButton;
    Button holdButton;
    Button resetButton;
    ImageView myImage;
    //Create an integer array of the photos. Number returned is the ID of the images, not the photos themselves
    Integer[] dice_photo = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    Random myRandom = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rollButton = findViewById(R.id.button);
        holdButton = findViewById(R.id.button2);
        resetButton = findViewById(R.id.button3);
        myImage = findViewById(R.id.imageView);
        your_score = findViewById(R.id.textView);
        comp_score = findViewById(R.id.textView2);
        turn_score = findViewById(R.id.textView3);
        comp_turn_score = findViewById(R.id.textView4);


        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int result = myRandom.nextInt(6);
                int dice_value = dice_photo[result];
                myImage.setImageResource(dice_value);
                if (result == 0) {
                    USER_TSCORE = 0;
                    turn_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , USER_TSCORE));
                } else {
                    Log.v("my", String.format("%d", result));
                    USER_TSCORE += result + 1;
                    turn_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , USER_TSCORE));
                }
            }

        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                USER_FSCORE += USER_TSCORE;
                USER_TSCORE = 0;
                your_score.setText(String.format("%s: %d",getResources().getString(R.string.textView) , USER_FSCORE));
                turn_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , USER_TSCORE));
                //start of the computer's turn.
                holdButton.setEnabled(false);
                rollButton.setEnabled(false);
                computer_turn();
            }

        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                USER_TSCORE = 0;
                USER_FSCORE = 0;
                COMP_TSCORE = 0;
                COMP_FSCORE = 0;
                your_score.setText(String.format("%s: %d",getResources().getString(R.string.textView) , USER_FSCORE));
                turn_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , USER_TSCORE));
                comp_score.setText(String.format("%s: %d", getResources().getString(R.string.textView2), COMP_FSCORE));
                comp_turn_score.setText(String.format("%s: %d", getResources().getString(R.string.textView4), COMP_TSCORE));
            }
        });

    }

    private int roll_dice(){
        int result = myRandom.nextInt(6);
        int dice_value = dice_photo[result];
        myImage.setImageResource(dice_value);
        return result;
    }

    private void computer_turn(){
        while (numOfComprolls < maxNumOfComprolls){
            int result = roll_dice();
            if (result == 0) {
                COMP_TSCORE = 0;
                comp_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , COMP_TSCORE));
            } else {
                Log.v("my", String.format("%d", result));
                COMP_TSCORE += result + 1;
                comp_score.setText(String.format("%s: %d",getResources().getString(R.string.textView3) , COMP_TSCORE));
            }
            numOfComprolls+=1;

        }
    }

}



