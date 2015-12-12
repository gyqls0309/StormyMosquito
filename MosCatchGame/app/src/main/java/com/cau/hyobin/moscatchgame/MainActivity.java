
package com.cau.hyobin.moscatchgame;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    private SensorManager sensorManager;
    private MySensor sensor;
    private Vector3 gyroValue;
    private Vector3 accelValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = new MySensor(sensorManager);
        setContentView(R.layout.activity_main);
        //final GraphicsView graphicsView = new GraphicsView(this);
        //setContentView(graphicsView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //리스너 등록
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensor, sensor.GetSensor_Gravity(), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensor, sensor.GetSensor_Magnetic(), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensor, sensor.GetSensor_Orient(), SensorManager.SENSOR_DELAY_FASTEST);
    }

    //리스너 해제
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensor);
    }
}
