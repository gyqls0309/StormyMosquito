package com.cau.hyobin.moscatchgame;

import android.graphics.Canvas;

/**
 * Created by hyobCom on 2015-12-02.
 */
public class Number {
    public static float size = 10;

    public static void draw(Canvas canvas, Vector3 pos, int num, boolean isBig){
        int jari = 1;
        for(int i = num; i > 9 ; i/=10)
            jari ++;
        int number = num;
        for(int i = 0 ; i < jari ; i ++){
            if(!isBig) drawOneNum(canvas, pos, number%10, i);
            else drawOneNumBig(canvas, pos, number%10, i);
            number /= 10;
        }
    }

    public static void draw(Canvas canvas, Vector3 pos, int num, int jariNum, boolean isBig){
        int jari = 1;
        for(int i = num; i > 9 ; i/=10)
            jari ++;
        int number = num;
        if(jariNum < jari) {
            for (int i = 0; i < jari; i++) {
                if(!isBig) drawOneNum(canvas, pos, number%10, i);
                else drawOneNumBig(canvas, pos, number%10, i);
                number /= 10;
            }
        } else {
            for (int i = 0; i < jariNum; i++) {
                if(!isBig) drawOneNum(canvas, pos, number% 10, i);
                else drawOneNumBig(canvas, pos, number%10, i);
                number /= 10;
            }
        }
    }

    private static void drawOneNum(Canvas canvas, Vector3 pos, int num, int jari){
        if(num == 0) canvas.drawBitmap(Image.num0, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 1) canvas.drawBitmap(Image.num1, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 2) canvas.drawBitmap(Image.num2, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 3) canvas.drawBitmap(Image.num3, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 4) canvas.drawBitmap(Image.num4, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 5) canvas.drawBitmap(Image.num5, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 6) canvas.drawBitmap(Image.num6, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 7) canvas.drawBitmap(Image.num7, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 8) canvas.drawBitmap(Image.num8, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
        else if(num == 9) canvas.drawBitmap(Image.num9, (int)(pos.x - jari * size * 0.4f), (int)(pos.y), null);
    }

    private static void drawOneNumBig(Canvas canvas, Vector3 pos, int num, int jari){
        if(num == 0) canvas.drawBitmap(Image.num0Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 1) canvas.drawBitmap(Image.num1Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 2) canvas.drawBitmap(Image.num2Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 3) canvas.drawBitmap(Image.num3Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 4) canvas.drawBitmap(Image.num4Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 5) canvas.drawBitmap(Image.num5Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 6) canvas.drawBitmap(Image.num6Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 7) canvas.drawBitmap(Image.num7Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 8) canvas.drawBitmap(Image.num8Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
        else if(num == 9) canvas.drawBitmap(Image.num9Big, (int)(pos.x - jari * size * 1.2f), (int)(pos.y), null);
    }
}
