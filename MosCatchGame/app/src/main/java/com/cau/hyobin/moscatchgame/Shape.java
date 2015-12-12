package com.cau.hyobin.moscatchgame;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by hyobCom on 2015-11-25.
 */
public abstract class Shape {
    public static int Circle = 0;
    public static int Rectangle = 1;
    public static int Triangle = 2;
    public Paint paint;
    public abstract void draw(Canvas canvas, Vector3 position, float angle);
    public abstract int getModel();
    public int height;
    public int width;
    public Vector3 position;
    public float angle;
}
