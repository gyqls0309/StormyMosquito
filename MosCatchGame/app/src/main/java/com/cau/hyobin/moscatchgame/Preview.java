package com.cau.hyobin.moscatchgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by hyobCom on 2015-11-25.
 */
public class Preview extends SurfaceView implements SurfaceHolder.Callback{

    Camera mCamera;
    SurfaceHolder mHolder;
    private Vector3 touchPos;
    //public static Vector3 cam, camXZ;
    public Vector<GameObject> mos;

    private TimerThread time;

    boolean targetSizeInit;

    private static int hp;

    private int attackTimer;
    private int mosTimer;
    private int mosMaxTimer;
    private static int damageTimer;

    public void close(){
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public Preview(Context context, AttributeSet attrs){
        super(context, attrs);
        try{
            if(mCamera == null){
                mCamera = Camera.open();
            }
            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }catch (Exception e){

        }
        time = new TimerThread();
        State.curr = State.menu;
        touchPos = new Vector3();
        new Cam();
        new Image(getResources());
        targetSizeInit = false;
        Screen.width = 1000;
        Screen.height = 1000;


        setWillNotDraw(false);


        //cam = new Vector3();
        mos = new Vector<GameObject>();
        attackTimer = 0;
        damageTimer = 0;
        hp = 10;
        mosTimer = 0;
        mosMaxTimer = 30;
    }

    private void Init(){
        mos.clear();
        attackTimer = 0;
        damageTimer = 0;
        hp = 10;
        mosTimer = 0;
        mosMaxTimer = 30;
        time = new TimerThread();
    }

    public static void Damage(){
        hp--;
        damageTimer+=10;
        if(hp == 0){
            State.curr = State.end;
            TimerThread.isRun = false;
        }
    }

    protected void onDraw(Canvas canvas){
        if(State.curr == State.menu){
            canvas.drawBitmap(Image.game_name, Screen.width * 0.2f, Screen.height * 0.1f, null);
            canvas.drawBitmap(Image.button_start, Screen.width * 0.4f, Screen.height * 0.5f, null);
            canvas.drawBitmap(Image.button_credit, Screen.width * 0.4f, Screen.height * 0.65f, null);
        }else if(State.curr == State.gaming) {
            update();
            drawGameObject(canvas);
            drawAttack(canvas);
            drawGameUI(canvas);
            drawHp(canvas);
            drawMosNum(canvas);
            drawTime(canvas);
            drawDamage(canvas);
        }else if(State.curr == State.credit){
            canvas.drawBitmap(Image.credit, Screen.width * 0.2f, Screen.width * 0.05f, null);
            canvas.drawBitmap(Image.button_back, Screen.width * 0.4f, Screen.height * 0.65f, null);
        }else if(State.curr == State.end){
            canvas.drawBitmap(Image.result, Screen.width * 0.28f, Screen.width * 0.05f, null);
            Number.draw(canvas, new Vector3(Screen.width * 0.67f, Screen.width * 0.1955f, 0), time.sec, 2, true);
            Number.draw(canvas, new Vector3(Screen.width * 0.47f, Screen.width * 0.1955f, 0), time.min, 2, true);
            Number.draw(canvas, new Vector3(Screen.width * 0.27f, Screen.width * 0.1955f, 0), time.hour, 2, true);
            canvas.drawBitmap(Image.button_back, Screen.width * 0.4f, Screen.height * 0.65f, null);
        }
        invalidate();
    }

    protected void update(){
        Cam.Update();
        CreateMos();
        for(int i = 0 ; i < mos.size(); i++)
            mos.get(i).Update();
    }

    private void CreateMos(){
        mosTimer++;
        if(mosMaxTimer < mosTimer){
            if(mos.size() < 30)
                mos.add(new GameObject());
            mosTimer = 0;
        }
    }

    private void drawHp(Canvas canvas){
        for(int i = 0 ; i < hp ; i ++) {
            canvas.drawBitmap(Image.hp, (int) (Screen.width * (0.067f + i * 0.015f)), (int) (Screen.height * 0.03f), null);
        }
    }

    private void drawMosNum(Canvas canvas){
        Number.draw(canvas, new Vector3(Screen.width * 0.94f, Screen.height * 0.025f, 0), mos.size(), false);
    }

    private void drawTime(Canvas canvas){
        Number.draw(canvas, new Vector3(Screen.width * 0.189f, Screen.height * 0.913f, 0), time.milsec, 2, false);
        Number.draw(canvas, new Vector3(Screen.width * 0.150f, Screen.height * 0.913f, 0), time.sec, 2, false);
        Number.draw(canvas, new Vector3(Screen.width * 0.111f, Screen.height * 0.913f, 0), time.min, 2, false);
        Number.draw(canvas, new Vector3(Screen.width * 0.072f, Screen.height * 0.913f, 0), time.hour, 2, false);
    }

    private void drawGameUI(Canvas canvas){
        canvas.drawBitmap(Image.target, (int) (Screen.width * 0.44f), (int) (Screen.height * 0.5f - Screen.width * 0.06f), null);
        canvas.drawBitmap(Image.hpBar, (int) (Screen.width * 0.02f), (int) (Screen.height * 0.02f), null);
        canvas.drawBitmap(Image.timer, (int) (Screen.width * 0.02f), (int) (Screen.height * 0.98f - Screen.width * 0.04f), null);
        canvas.drawBitmap(Image.mosNum, (int) (Screen.width * 0.88f), (int) (Screen.height * 0.02f), null);
    }

    private void drawDamage(Canvas canvas){
        if(0 < damageTimer){
            canvas.drawBitmap(Image.damage, 0, 0, null);
            damageTimer--;
        }
    }

    private void drawAttack(Canvas canvas){
        if(0 < attackTimer) {
            canvas.drawBitmap(Image.attack, (int) (Screen.width * 0.4f), (int) (Screen.height * 0.5f - Screen.width * 0.1f), null);
            attackTimer--;
        }
    }

    protected void drawGameObject(Canvas canvas){
        for(int i = 0 ; i < mos.size(); i++) {
            mos.get(i).draw(canvas);
        }
    }

    public void Attack(){
        for(int i = 0 ; i < mos.size(); i++) {
            if(mos.get(i).hit()){
                mos.remove(i);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        touchPos.x = event.getX();
        touchPos.y = event.getY();
        int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP: {
                if(State.curr == State.menu) {
                    if(Screen.width * 0.4f < touchPos.x && touchPos.x < Screen.width * 0.6f){
                        if(Screen.height * 0.5f < touchPos.y && touchPos.y < Screen.height * 0.5f + Screen.width * 0.065f){
                            State.curr = State.gaming;
                            time.start();
                        }
                    }
                    if(Screen.width * 0.4f < touchPos.x && touchPos.x < Screen.width * 0.6f){
                        if(Screen.height * 0.65f < touchPos.y && touchPos.y < Screen.height * 0.65f + Screen.width * 0.065f){
                            State.curr = State.credit;
                        }
                    }
                }else if (State.curr == State.gaming) {
                    attackTimer++;
                    Attack();
                }else if(State.curr == State.credit){
                    if(Screen.width * 0.4f < touchPos.x && touchPos.x < Screen.width * 0.6f){
                        if(Screen.height * 0.65f < touchPos.y && touchPos.y < Screen.height * 0.65f + Screen.width * 0.065f){
                            State.curr = State.menu;
                        }
                    }
                }else if(State.curr == State.end){
                    if(Screen.width * 0.4f < touchPos.x && touchPos.x < Screen.width * 0.6f){
                        if(Screen.height * 0.65f < touchPos.y && touchPos.y < Screen.height * 0.65f + Screen.width * 0.065f){
                            State.curr = State.menu;
                            time.isRun = false;
                            Init();
                        }
                    }
                }
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                break;
            }
        }
        return true;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void surfaceCreated(SurfaceHolder holder){
        try{
            if(mCamera == null){
                mCamera = Camera.open();
            }

            if(mCamera != null){
                try{
                    mCamera.setPreviewDisplay(holder);
                    Camera.Parameters parameters = mCamera.getParameters();
                    mCamera.setParameters(parameters);
                    mCamera.startPreview();
                }catch(IOException exception){
                    Log.e("Error", "exception:surfaceCreated Camera Open ");
                    mCamera.release();
                    mCamera = null;
                }
            }
        }catch (Exception e){
            Log.e("camera open error", "error");
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int v, int h){
        Screen.width = v;
        Screen.height = h;
        if(!targetSizeInit) {
            Image.button_back = Bitmap.createScaledBitmap(Image.button_back, (int) (Screen.width * 0.2f), (int) (Screen.width * 0.065f), false);
            Image.button_credit = Bitmap.createScaledBitmap(Image.button_credit, (int) (Screen.width * 0.2f), (int) (Screen.width * 0.065f), false);
            Image.button_start = Bitmap.createScaledBitmap(Image.button_start, (int) (Screen.width * 0.2f), (int) (Screen.width * 0.065f), false);
            Image.game_name = Bitmap.createScaledBitmap(Image.game_name, (int) (Screen.width * 0.6f), (int) (Screen.width * 0.1f), false);
            Image.target = Bitmap.createScaledBitmap(Image.target, (int) (Screen.width * 0.12f), (int) (Screen.width * 0.12f), false);
            Image.hpBar = Bitmap.createScaledBitmap(Image.hpBar, (int) (Screen.width * 0.2f), (int) (Screen.width * 0.04f), false);
            Image.hp = Bitmap.createScaledBitmap(Image.hp, (int) (Screen.width * 0.011f), (int) (Screen.width * 0.03f), false);
            Image.timer = Bitmap.createScaledBitmap(Image.timer, (int) (Screen.width * 0.2f), (int) (Screen.width * 0.04f), false);
            Image.mosNum = Bitmap.createScaledBitmap(Image.mosNum, (int) (Screen.width * 0.1f), (int) (Screen.width * 0.04f), false);
            Image.attack = Bitmap.createScaledBitmap(Image.attack, (int)(Screen.width * 0.2f), (int)(Screen.width * 0.2f), false);
            Image.damage = Bitmap.createScaledBitmap(Image.damage, (int)(Screen.width), (int)(Screen.height), false);
            Image.num0Big = Bitmap.createScaledBitmap(Image.num0, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num1Big = Bitmap.createScaledBitmap(Image.num1, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num2Big = Bitmap.createScaledBitmap(Image.num2, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num3Big = Bitmap.createScaledBitmap(Image.num3, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num4Big = Bitmap.createScaledBitmap(Image.num4, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num5Big = Bitmap.createScaledBitmap(Image.num5, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num6Big = Bitmap.createScaledBitmap(Image.num6, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num7Big = Bitmap.createScaledBitmap(Image.num7, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num8Big = Bitmap.createScaledBitmap(Image.num8, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num9Big = Bitmap.createScaledBitmap(Image.num9, (int)(Screen.width * 0.1f), (int)(Screen.width * 0.1f), false);
            Image.num0 = Bitmap.createScaledBitmap(Image.num0, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num1 = Bitmap.createScaledBitmap(Image.num1, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num2 = Bitmap.createScaledBitmap(Image.num2, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num3 = Bitmap.createScaledBitmap(Image.num3, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num4 = Bitmap.createScaledBitmap(Image.num4, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num5 = Bitmap.createScaledBitmap(Image.num5, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num6 = Bitmap.createScaledBitmap(Image.num6, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num7 = Bitmap.createScaledBitmap(Image.num7, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num8 = Bitmap.createScaledBitmap(Image.num8, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.num9 = Bitmap.createScaledBitmap(Image.num9, (int)(Screen.width * 0.03f), (int)(Screen.width * 0.03f), false);
            Image.result = Bitmap.createScaledBitmap(Image.result, (int)(Screen.width * 0.44f), (int)(Screen.width * 0.25f), false);
            Image.credit = Bitmap.createScaledBitmap(Image.credit, (int)(Screen.width * 0.6f), (int)(Screen.width * 0.25f), false);
            Number.size = Screen.width * 0.025f;
            targetSizeInit = true;
        }
    }
}
