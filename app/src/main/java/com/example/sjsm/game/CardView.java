package com.example.sjsm.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by sjsm on 2017/1/12.
 */

public class CardView extends FrameLayout {

    private int num;
    private TextView lable;

    public CardView(Context context) {
        super(context);
        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setGravity(Gravity.CENTER);
        lable.setBackgroundColor(0x33ffffff);
        LayoutParams lp = new LayoutParams(-1, -1);//填充满整个父view
        lp.setMargins(10, 10, 0, 0);
        addView(lable, lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            lable.setText("");
        } else {
            lable.setText(num + "");
        }
    }

    public boolean equals(CardView obj) {
        return getNum() == obj.getNum();
    }
}
