package com.example.sjsm.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_score;
    private static MainActivity mainActivity=null;
    private int score=0;

    public MainActivity() {
        mainActivity = this;
    }

    public static MainActivity getMainActivity(){
        return mainActivity;
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_score= (TextView) findViewById(R.id.tv_score);
    }

    public void clearScore(){
        score=0;
        showScore();
    }

    public void addScore(int s){
        score+=s;
        showScore();
    }

    public void showScore(){
        tv_score.setText(score+"");
    }
}
