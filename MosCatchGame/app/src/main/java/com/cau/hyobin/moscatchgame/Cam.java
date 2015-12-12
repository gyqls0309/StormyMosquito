package com.cau.hyobin.moscatchgame;

import java.util.Vector;

/**
 * Created by hyobCom on 2015-12-02.
 */
public class Cam {
    public static Vector3 forward;
    public static Vector3 right;
    public static Vector3 up;
    public static float angle;

    public Cam() {
        forward = new Vector3();
        right = new Vector3();
        up = new Vector3();
    }

    public static void Update(){
        /*
        forward.y = MySensor.gravity.z * 0.0001f;
        if(1 < forward.y) forward.y = 1;
        if(forward.y < -1) forward.y = -1;
        float XZdist = (float)Math.sin(Math.acos(forward.y));
        forward.x = (float)((Math.cos(MySensor.orient.x * Math.PI / 180) + MySensor.gravity.x * 0.0001f)/XZdist);
        forward.z = (float)((Math.sin(MySensor.orient.x * Math.PI / 180) + MySensor.gravity.x * 0.0001f)/XZdist);

        right.y = -MySensor.gravity.x * 0.0001f;
        if(1 < right.y) right.y = 1;
        if(right.y < -1) right.y = -1;
        XZdist = (float)Math.sin(Math.acos(right.y));
        right.x = (float)((Math.cos((MySensor.orient.x + 90) * Math.PI / 180))/XZdist);
        right.z = (float)((Math.sin((MySensor.orient.x + 90) * Math.PI / 180))/XZdist);

        up = Vector3.Product(right, forward);

        forward = Vector3.Normalize(forward);
        right = Vector3.Normalize(right);
        up = Vector3.Normalize(up);

        angle = -(float)(MySensor.gravity.x * 0.000098f);
        */
        //*
        forward.y = MySensor.gravity.z * 0.0001f;
        if(1 < forward.y) forward.y = 1;
        if(forward.y < -1) forward.y = -1;
        float XZdist = (float)Math.sin(Math.acos(forward.y));
        forward.x = (float)((Math.cos(MySensor.orient.x * Math.PI / 180) + MySensor.gravity.x * 0.0001f)/XZdist);
        forward.z = (float)((Math.sin(MySensor.orient.x * Math.PI / 180) + MySensor.gravity.x * 0.0001f)/XZdist);

        right.y = -MySensor.gravity.x * 0.0001f;
        if(1 < right.y) right.y = 1;
        if(right.y < -1) right.y = -1;
        XZdist = (float)Math.sin(Math.acos(right.y));
        right.x = (float)((Math.cos((MySensor.orient.x + 90) * Math.PI / 180))/XZdist);
        right.z = (float)((Math.sin((MySensor.orient.x + 90) * Math.PI / 180))/XZdist);

        up = Vector3.Product(right, forward);

        forward = Vector3.Normalize(forward);
        right = Vector3.Normalize(right);
        up = Vector3.Normalize(up);

        angle = -(float)(MySensor.gravity.x * 0.000098f);
        //*/
    }

    public static Vector3 ToScreen(Vector3 pos){
        Vector3 ret = new Vector3();
        if(0 < Vector3.Dot(forward, pos)) {
            Vector3 local = Vector3.Normalize(pos);
            ret.y = Vector3.Dot(local, up)*Screen.width * 1.2f + 0.5f* Screen.height;
            ret.x = (Vector3.Dot(local, right) * 1.2f + 0.5f)*Screen.width;
        }else{
            ret.x = -1000;
            ret.y = -1000;
        }
        return ret;
    }

}
