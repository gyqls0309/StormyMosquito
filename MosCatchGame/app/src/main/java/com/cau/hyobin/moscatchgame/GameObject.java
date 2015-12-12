package com.cau.hyobin.moscatchgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by hyobCom on 2015-11-29.
 */
public class GameObject {
    public Vector3 forward;
    public int imageFlag;
    public Vector3 position;
    private Circle body;
    private Bitmap buff;
    public Vector3 screenPos;
    public int stateFlag;
    private int stateTimer = 0;

    public GameObject(){
        body = new Circle(Color.BLACK, 100);
        imageFlag = 0;
        position = new Vector3((float)(Math.random() - 0.5f) * 10, 0 ,(float)(Math.random() - 0.5f) * 10);
        forward = new Vector3((float)(Math.random() - 0.5f) * 2,(float)(Math.random() - 0.5f) * 2,(float)(Math.random() - 0.5f) * 2);
        forward = Vector3.Normalize(forward);
        stateFlag = StateFlag.flying;
    }

    public void SetSize(float size){
        body.Set((int) (Screen.width * 0.15f / size));
    }

    public void Update() {
        Move();
        //WingMove();
    }


    private void Move(){
        if(stateFlag == StateFlag.crazy) {
            GetForward();
            position = Vector3.Add(position, Vector3.ScalarMul(forward, 0.1f));
            PositionBound();
        }else if(stateFlag == StateFlag.flying){
            GetForward();
            position = Vector3.Add(position, Vector3.ScalarMul(forward, 0.03f));
            PositionBound();
        }else if(stateFlag == StateFlag.attack){
            GetForward();
            position = Vector3.Add(position, Vector3.ScalarMul(forward, 0.04f));
            AttackBound();
        }else if(stateFlag == StateFlag.stop){

        }
        stateTimer --;
        if(stateTimer == 0){
            if(Math.random() < 0.7f)    stateFlag = StateFlag.flying;
            else   stateFlag = StateFlag.attack;
            stateTimer = (int)(Math.random() * 1000);
        }
    }

    private void SetImageFlag(){
        float sideDot = Vector3.Dot(Cam.right, forward);
        float frontDot = Vector3.Dot(Cam.forward, forward);
        if(stateFlag == StateFlag.stop) {
            imageFlag = ImageFlag.up;
        }else{
            if (0.3f < frontDot * frontDot - sideDot * sideDot) {
                if ( 0 < frontDot ) {
                    imageFlag = ImageFlag.back;
                } else {
                    imageFlag = ImageFlag.front;
                }
            } else if(-0.3f > frontDot * frontDot - sideDot * sideDot){
                if ( 0 < sideDot ) {
                    imageFlag = ImageFlag.right;
                } else {
                    imageFlag = ImageFlag.left;
                }
            } else {
                if ( 0 < frontDot && 0 < sideDot ) {
                    imageFlag = ImageFlag.rightback;
                } else if( 0 > frontDot && 0 < sideDot ){
                    imageFlag = ImageFlag.rightfront;
                } else if( 0 < frontDot && 0 > sideDot ){
                    imageFlag = ImageFlag.leftback;
                } else {
                    imageFlag = ImageFlag.leftfront;
                }
            }
        }
    }

    private void AttackBound(){
        float Threshold = 2;
        if(-Threshold < position.x && position.x < Threshold){
            if(-Threshold < position.y && position.y < Threshold){
                if(-Threshold < position.z && position.z < Threshold){
                    stateFlag = StateFlag.flying;
                    stateTimer = 100 + (int)(Math.random()*1000);
                    Preview.Damage();
                }
            }
        }
    }

    private void PositionBound(){
        float Threshold = 5;
        if(Threshold < position.x){
            MakeStop();
            forward.x *= -1;
            position.x = Threshold;
        }
        if(Threshold*0.4f < position.y){
            forward.y *= -1;
            position.y = Threshold * 0.4f;
        }
        if(Threshold < position.z){
            MakeStop();
            forward.z *= -1;
            position.z = Threshold;
        }
        if(position.x < -Threshold){
            MakeStop();
            forward.x *= -1;
            position.x = -Threshold;
        }
        if(position.y < -Threshold * 0.4f){
            forward.y *= -1;
            position.y = -Threshold * 0.4f;
        }
        if(position.z < -Threshold){
            MakeStop();
            forward.z *= -1;
            position.z = -Threshold;
        }
    }

    private void GetForward(){
        if(stateFlag == StateFlag.attack) {
            forward = Vector3.Normalize(Vector3.Sub(new Vector3(), position));
        }else{
            Vector3 rand = new Vector3((float) (Math.random() - 0.5f) * 0.2f, (float) (Math.random() - 0.5f) * 0.2f, (float) (Math.random() - 0.5f) * 0.2f);
            forward = Vector3.Normalize(Vector3.Add(forward, rand));
        }
        SetImageFlag();
    }

    public void draw(Canvas canvas){
        float distance = Vector3.Distance(position);
        SetSize(distance);
        screenPos = Cam.ToScreen(position);
        if(imageFlag == ImageFlag.up) drawImage(canvas, Image.up, screenPos, new Vector3(1, 1, 1), Cam.angle);
        if(imageFlag == ImageFlag.right) drawImage(canvas, Image.right, screenPos, new Vector3(2, 1, 1), Cam.angle);
        if(imageFlag == ImageFlag.left) drawImage(canvas, Image.left, screenPos, new Vector3(2, 1, 1), Cam.angle);
        if(imageFlag == ImageFlag.back) drawImage(canvas, Image.back, screenPos, new Vector3(2, 1, 1), Cam.angle);
        if(imageFlag == ImageFlag.front) drawImage(canvas, Image.front, screenPos, new Vector3(2, 1, 1), Cam.angle);
        if(imageFlag == ImageFlag.rightfront) drawImage(canvas, Image.rightfront, screenPos, new Vector3(2, 1.3f, 1), Cam.angle);
        if(imageFlag == ImageFlag.leftfront) drawImage(canvas, Image.leftfront, screenPos, new Vector3(2, 1.3f, 1), Cam.angle);
        if(imageFlag == ImageFlag.rightback) drawImage(canvas, Image.rightback, screenPos, new Vector3(2, 1.3f, 1), Cam.angle);
        if(imageFlag == ImageFlag.leftback) drawImage(canvas, Image.leftback, screenPos, new Vector3(2, 1.3f, 1), Cam.angle);
    }


    //localPos 는 상대값, x, y 만 쓴다.
    private void drawImage(Canvas canvas, Bitmap image, Vector3 pos, Vector3 scale){
        if(-100 < pos.x) {
            Vector3 size = new Vector3(body.width * scale.x * 2, body.width * scale.y * 2, 0);
            if (size.x < 1) size.x = 1;
            if (size.y < 1) size.y = 1;
            buff = Bitmap.createScaledBitmap(image, (int) (size.x), (int) (size.y), false);
            canvas.drawBitmap(buff, pos.x - size.x / 2, pos.y - size.y / 2, null);
            buff.recycle();
        }
    }

    private void drawImage(Canvas canvas, Bitmap image, Vector3 pos, Vector3 scale, float angle){
        if(-100 < pos.x) {
            Vector3 size = new Vector3(body.width * scale.x, body.width * scale.y, 0);
            if(size.x > Screen.width*0.3f * scale.x)  size.x = Screen.width*0.3f * scale.x;
            if(size.y > Screen.width*0.3f * scale.y)  size.y = Screen.width*0.3f * scale.y;
            buff = Bitmap.createScaledBitmap(image, (int) (size.x), (int) (size.y), false);
            canvas.rotate(angle, (int) pos.x, (int) pos.y);
            canvas.drawBitmap(buff, pos.x - size.x / 2, pos.y - size.y / 2, null);
            canvas.rotate(-angle, (int) pos.x, (int) pos.y);
            buff.recycle();
        }
    }

    private void MakeStop(){
        imageFlag = ImageFlag.up;
        stateFlag = StateFlag.stop;
        stateTimer = (int)(Math.random() * 1000);
    }

    private void MakeCrazy(){
        stateFlag = StateFlag.crazy;
        stateTimer = 1000;
    }

    public boolean hit(){
        if(Screen.width/2 - body.width*1.1f <= screenPos.x && screenPos.x <= Screen.width/2 + body.width*1.1f){
            if(Screen.height/2 - body.width*1.1f <= screenPos.y && screenPos.y <= Screen.height/2 + body.width*1.1f){
                return true;
            }
        }

        if(Screen.width/2 - body.width*2f <= screenPos.x && screenPos.x <= Screen.width/2 + body.width*2f){
            if(Screen.height/2 - body.width*2f <= screenPos.y && screenPos.y <= Screen.height/2 + body.width*2f){
                MakeCrazy();
            }
        }
        return false;
    }
}

class ImageFlag{
    public static int front = 0;
    public static int back = 1;
    public static int right = 2;
    public static int left = 3;
    public static int up = 4;
    public static int leftfront = 5;
    public static int rightfront = 6;
    public static int leftback = 7;
    public static int rightback = 8;
}

class StateFlag{
    public static int stop = 0;
    public static int flying = 1;
    public static int attack = 2;
    public static int crazy = 3;
}
