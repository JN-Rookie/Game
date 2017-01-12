package com.example.sjsm.game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjsm on 2017/1/12.
 */

public class GameView extends GridLayout {

    private CardView[][] cardsMap=new CardView[4][4];
    private List<Point> emptyPoints=new ArrayList<>();

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView() {
        setColumnCount(4);//设置layout共有4列
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {

            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {

                            if (offsetX < -5) {//向左
                                swipeLeft();
                            } else if (offsetX > -5) {//向右
                                swipeRight();
                            }
                            break;

                        } else {

                            if (offsetY < -5) {//向上
                                swipeUp();
                            } else if (offsetY > -5) {//向下
                                swipeDown();
                            }
                            break;
                        }


                }

                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth=(Math.min(w,h)-10)/4;

        addCard(cardWidth,cardWidth);
        startGame();
    }

    private void addCard(int cardWidth,int cardHeight){

        CardView c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c=new CardView(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardsMap[x][y]=c;
            }
        }
    }

    private void startGame(){

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum(){

        emptyPoints.clear();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }

        Point p=emptyPoints.remove((int) (Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }

    private void swipeLeft() {

        boolean marge=false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                for (int x1 = x+1; x1 < 4; x1++) {
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            marge=true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);

                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge=true;
                        }
                        break;
                    }
                }
            }
        }
        if (marge){
            addRandomNum();
            checkcomplete();
        }
    }

    private void swipeRight() {

        boolean marge=false;

        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >=0; x--) {

                for (int x1 = x-1; x1 >=0; x1--) {
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            marge=true;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkcomplete();
        }
    }

    private void swipeUp() {

        boolean marge=false;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y+1; y1 < 4; y1++) {
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            marge=true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkcomplete();
        }
    }

    private void swipeDown() {

        boolean marge=false;

        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >=0; y--) {

                for (int y1 = y-1; y1 >=0; y1--) {
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            marge=true;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            marge=true;
                        }
                        break;
                    }
                }
            }
        }
        if(marge){
            addRandomNum();
            checkcomplete();
        }
    }

    private void checkcomplete(){

        boolean complete=true;

        All:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(cardsMap[x][y].getNum()==0||(x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3)&&cardsMap[x][y].equals(cardsMap[x][y+1])){
                    complete=false;
                    break All;
                }
            }
        }

        if(complete){
            new AlertDialog.Builder(getContext()).setTitle("您好").setMessage("游戏结束！").setPositiveButton("是否开始新游戏？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
            }).show();
        }
    }
}
