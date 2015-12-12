package com.cau.hyobin.moscatchgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by hyobCom on 2015-12-02.
 */
public class Image {

    public static Bitmap front, back, left, right, up, leftfront, rightfront, leftback, rightback, target, hpBar, hp, timer, mosNum, damage, attack, result, credit;
    public static Bitmap num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    public static Bitmap num0Big, num1Big, num2Big, num3Big, num4Big, num5Big, num6Big, num7Big, num8Big, num9Big;
    public static Bitmap button_start, button_credit, button_back, game_name;
    public Image(Resources resources){
        button_start = BitmapFactory.decodeResource(resources, R.drawable.button_start);
        button_credit = BitmapFactory.decodeResource(resources, R.drawable.button_credit);
        button_back = BitmapFactory.decodeResource(resources, R.drawable.button_back);
        game_name = BitmapFactory.decodeResource(resources, R.drawable.game_name);
        damage = BitmapFactory.decodeResource(resources, R.drawable.damage);
        attack = BitmapFactory.decodeResource(resources, R.drawable.attack);
        target = BitmapFactory.decodeResource(resources, R.drawable.ui);
        front = BitmapFactory.decodeResource(resources, R.drawable.front);
        back = BitmapFactory.decodeResource(resources, R.drawable.back);
        right = BitmapFactory.decodeResource(resources, R.drawable.right);
        left = BitmapFactory.decodeResource(resources, R.drawable.left);
        up = BitmapFactory.decodeResource(resources, R.drawable.up);
        hpBar = BitmapFactory.decodeResource(resources, R.drawable.hobar);
        hp = BitmapFactory.decodeResource(resources, R.drawable.hpui);
        timer = BitmapFactory.decodeResource(resources, R.drawable.time_ui);
        mosNum = BitmapFactory.decodeResource(resources, R.drawable.mos_ui);
        num0 = BitmapFactory.decodeResource(resources, R.drawable.num0);
        num1 = BitmapFactory.decodeResource(resources, R.drawable.num1);
        num2 = BitmapFactory.decodeResource(resources, R.drawable.num2);
        num3 = BitmapFactory.decodeResource(resources, R.drawable.num3);
        num4 = BitmapFactory.decodeResource(resources, R.drawable.num4);
        num5 = BitmapFactory.decodeResource(resources, R.drawable.num5);
        num6 = BitmapFactory.decodeResource(resources, R.drawable.num6);
        num7 = BitmapFactory.decodeResource(resources, R.drawable.num7);
        num8 = BitmapFactory.decodeResource(resources, R.drawable.num8);
        num9 = BitmapFactory.decodeResource(resources, R.drawable.num9);
        result = BitmapFactory.decodeResource(resources, R.drawable.result);
        credit = BitmapFactory.decodeResource(resources, R.drawable.credit);
        leftfront = BitmapFactory.decodeResource(resources, R.drawable.leftfront);
        rightfront = BitmapFactory.decodeResource(resources, R.drawable.rightfront);
        leftback = BitmapFactory.decodeResource(resources, R.drawable.leftback);
        rightback = BitmapFactory.decodeResource(resources, R.drawable.rightback);
    }
}
