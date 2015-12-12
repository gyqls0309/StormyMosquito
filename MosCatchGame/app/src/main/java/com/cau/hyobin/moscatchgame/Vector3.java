package com.cau.hyobin.moscatchgame;

/**
 * Created by hyobCom on 2015-11-25.
 */
public class Vector3 {
    static int X_axis = 0;
    static int Y_axis = 1;
    static int Z_axis = 2;

    public float x;
    public float y;
    public float z;

    public Vector3(){
        x = y = z = 0;
    }

    public Vector3(float x, float y){
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 vec){
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public void set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static float Dot(Vector3 a, Vector3 b){
        return (a.x * b.x + a.y * b.y + a.z * b.z);
    }

    public static Vector3 Product(Vector3 a, Vector3 b){
        Vector3 ret = new Vector3();
        ret.x = a.y * b.z - a.z * b.y;
        ret.y = a.z * b.x - a.x * b.z;
        ret.z = a.x * b.y - a.y * b.x;
        return ret;
    }

    public static Vector3 ScalarMul(Vector3 vec, float val){
        return new Vector3(vec.x * val, vec.y * val, vec.z * val);
    }

    public static Vector3 Sub(Vector3 a, Vector3 b){
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vector3 Add(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x , a.y + b.y, a.z + b.z);
    }

    public static float LookAt(Vector3 at){
        float ret = 0;
        ret = (float)Math.atan2(at.x,at.y) * 57;
        return -ret;
    }

    public static Vector3 Normalize(Vector3 vec){
        float dist = Distance(vec);
        return new Vector3(vec.x/dist, vec.y/dist, vec.z/dist);
    }

    public static float Distance(Vector3 vec){
        return (float)Math.sqrt(vec.x * vec.x + vec.y * vec.y + vec.z * vec.z);
    }

}

