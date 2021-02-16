package com.example.assiproximitandacce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class ChangeQeustion extends AppCompatActivity implements SensorEventListener {

  

    SensorManager sensorManager;
    Sensor sensor;
   TextView tvQuestions;
    ArrayList<String> questionsArr;
    Intent intent;
    Sensor sensor2;
    boolean isRun=true;
  public   String indexOfArr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_qeustion);

        tvQuestions=findViewById(R.id.tv_changing_question);
        intent=getIntent();
        questionsArr=intent.getStringArrayListExtra("questions");
        //Log.d("dddd1" ,questionsArr.toString() );
        sensorManager=(SensorManager) getSystemService(this.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) !=null){
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null){
            sensor2=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

    }

    @Override
    public void onSensorChanged(final SensorEvent sensorEvent) {
       // Log.d("dddd1", "onSensorChanged" );

        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){

        //    Log.d("dddd1", "sensorEvent Values = "+ sensorEvent.values[0] );

            if (sensorEvent.values[0]>0) {
                isRun=true;
               final Handler mHandler=new Handler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {


        while (isRun) {

          for (int i = 0; i < questionsArr.size(); i++) {
           indexOfArr =   questionsArr.get(i).toString();
           if (!isRun)
               break;
       // Log.d("daniasaqqa", "arrloopdata" + questionsArr.get(i).toString());

             try {
              
            Thread.sleep(200);
            } catch (InterruptedException e) {
            e.printStackTrace();
           }

              mHandler.post(new Runnable() {

                @Override
                public void run() {
                   intent = getIntent();
                   questionsArr = intent.getStringArrayListExtra("questions");
                   tvQuestions.setText(indexOfArr);
            }
        });

    }

} }

                }).start();


        }else{
                isRun=false;
            }

        }

         if (sensorEvent.sensor.getType()== Sensor.TYPE_ACCELEROMETER){

            if (sensorEvent.values[2]<0){
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
