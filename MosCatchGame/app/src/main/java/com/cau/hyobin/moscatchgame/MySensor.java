package com.cau.hyobin.moscatchgame;

/**
 * Created by hyobCom on 2015-11-29.
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

class MySensor implements SensorEventListener {

    public static Vector3 gravity;
    //public static Vector3 magnetic;
    public static Vector3 orient;

    private static boolean isAlive = false;


    private SensorManager mSensorManager;
    private Sensor gravity_sensor;
    private Sensor magnetic_sensor;
    private Sensor orientation_sensor;

    public MySensor(SensorManager sensormanager){
        isAlive = true;
        gravity = new Vector3();
        //magnetic = new Vector3();
        orient = new Vector3();
        //센서 매니저 얻기
        mSensorManager = sensormanager;
        gravity_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
       // magnetic_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation_sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public Sensor GetSensor_Gravity(){  return gravity_sensor;  }

    public Sensor GetSensor_Magnetic(){
        return magnetic_sensor;
    }
    public Sensor GetSensor_Orient(){
        return orientation_sensor;
    }


    //센서값 얻어오기
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravity.x = Math.round(event.values[0] * 1000);
            gravity.y = Math.round(event.values[1] * 1000);
            gravity.z = Math.round(event.values[2] * 1000);
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
           // magnetic.x = Math.round(event.values[0] * 10);
           // magnetic.y = Math.round(event.values[1] * 10);
           // magnetic.z = Math.round(event.values[2] * 10);
        }
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            orient.x = event.values[0];    //방위값
            orient.y = event.values[1];    //경사도
            orient.z = event.values[2];    //회전값
        }
    }

    // 주기 설명
    // SENSOR_DELAY_UI 갱신에 필요한 정도 주기
    // SENSOR_DELAY_NORMAL 화면 방향 전환 등의 일상적인  주기
    // SENSOR_DELAY_GAME 게임에 적합한 주기
    // SENSOR_DELAY_FASTEST 최대한의 빠른 주기

}
