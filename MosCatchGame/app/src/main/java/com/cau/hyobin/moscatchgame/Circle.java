package com.cau.hyobin.moscatchgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hyobCom on 2015-11-25.
 */
public class Circle extends Shape {

    public Circle(){
        paint = new Paint();
        position = new Vector3();
        angle = 0;
        SetColor(Color.BLACK);
        Set(10);
    }
    public Circle(int color,int radious){
        paint = new Paint();
        position = new Vector3();
        angle = 0;
        SetColor(color);
        Set(radious);
    }

    public Circle(Shape model){
        SetColor(model.paint.getColor());
        Set(model.height);
    }

    void SetColor(int color){
        paint.setColor(color);
    }

    void Set(int radious){
        this.height = radious;
        this.width = radious;
    }
    public void draw(Canvas canvas, Vector3 position, float angle){
        canvas.drawCircle(position.x + this.position.x, position.y + this.position.y, width/2, paint);
    }

    public int getModel(){
        return Circle;
    }
}