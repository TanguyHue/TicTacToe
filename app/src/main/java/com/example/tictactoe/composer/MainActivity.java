package com.example.tictactoe.composer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.modele.map;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static boolean mIsPlayerA;
    private static map mCarte;
    private static TextView mTextView;
    private static Button mButton00;
    private static Button mButton01;
    private static Button mButton02;
    private static Button mButton10;
    private static Button mButton11;
    private static Button mButton12;
    private static Button mButton20;
    private static Button mButton21;
    private static Button mButton22;
    private static int mTours;

    private static List<Button> mButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.MainActivity_TextView_player);
        mIsPlayerA = true;

        mButtons = new ArrayList<Button>();

        mButton00 = findViewById(R.id.MainActivity_Button_00);
        mButton01 = findViewById(R.id.MainActivity_Button_01);
        mButton02 = findViewById(R.id.MainActivity_Button_02);
        mButton10 = findViewById(R.id.MainActivity_Button_10);
        mButton11 = findViewById(R.id.MainActivity_Button_11);
        mButton12 = findViewById(R.id.MainActivity_Button_12);
        mButton20 = findViewById(R.id.MainActivity_Button_20);
        mButton21 = findViewById(R.id.MainActivity_Button_21);
        mButton22 = findViewById(R.id.MainActivity_Button_22);

        mButtons.add(mButton00);
        mButtons.add(mButton01);
        mButtons.add(mButton02);
        mButtons.add(mButton10);
        mButtons.add(mButton11);
        mButtons.add(mButton12);
        mButtons.add(mButton20);
        mButtons.add(mButton21);
        mButtons.add(mButton22);

        for (Button button : mButtons)
        {
            button.setOnClickListener(this);
        }

        mCarte = new map();
        mTextView.setText("Au tour du joueur : A");
        mTours = 0;
    }

    private void reset(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                mCarte.setValue(i, j, 0);
            }
        }

        for (Button button : mButtons){
            button.setText(" ");
            button.setEnabled(true);
        }

        mIsPlayerA = true;
        mTextView.setText("Au tour du joueur : A");
        mTours = 0;
    }

    @Override
    public void onClick(View v) {
        String affichage;
        if(mIsPlayerA){
            mTextView.setText("Au tour du joueur : B");
            affichage = "X";
        }
        else {
            mTextView.setText("Au tour du joueur : A");
            affichage = "O";
        }

        int a = 0;
        int b = 0;
        for(Button button : mButtons){
            if (v == button){
                System.out.println(a + ", " + b);
                if(mIsPlayerA){
                    mCarte.setValue(a, b, 1);
                }
                else{
                    mCarte.setValue(a, b, 2);
                }
                button.setText(affichage);
                button.setEnabled(false);
                break;
            }
            b ++;
            if(b > 2){
                b = 0;
                a ++;
            }
        }

        if(mCarte.win()){
            System.out.println("Gagné !");
            String gagnant;
            if(mIsPlayerA){
                gagnant = "joueur A";
            }
            else{
                gagnant = "joueur B";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Partie terminé")
                    .setMessage("Félicitation au " + gagnant + " pour sa victoire ! ")
                    .setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                        }
                    })
                    .create()
                    .show();

        } else {
            mTours++;
            mIsPlayerA = !mIsPlayerA;
            if(mTours == 9){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Partie terminé")
                        .setMessage("Match nul !")
                        .setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                reset();
                            }
                        })
                        .create()
                        .show();
            }
        }



        System.out.println(mIsPlayerA);
    }
}