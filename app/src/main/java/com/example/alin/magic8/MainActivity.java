package com.example.alin.magic8;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private OnShakeListener aSensorListener;
    private GestureDetectorCompat mDetector;
    private MagicEightBall aMagicEightBall ;
    private SensorManager aSensorManager;
    private Sensor aAccelerometer;
    private ShakeDetector aShakeDetector;
    public TextView aAnswerLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aMagicEightBall = new MagicEightBall(getApplicationContext());
        //aAnswerLabel = (TextView) findViewById(R.id.textView1);
        //aCrystalBallImage = (ImageView) findViewById(R.id.imageView1);

        aAnswerLabel = (TextView) findViewById(R.id.name);
        aSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        aAccelerometer = aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        aShakeDetector = new ShakeDetector(new OnShakeListener() {
            @Override
            public void onShake() {
                handleAnswer();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        aSensorManager.registerListener(aShakeDetector, aAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause(){
        super.onPause();
        aSensorManager.unregisterListener(aShakeDetector);
    }
   /* private void animateCrystalBall(){

        aCrystalBallImage.setImageResource(R.drawable.ball_animation);
        AnimationDrawable ballAnimation = (AnimationDrawable) aCrystalBallImage.getDrawable();
        if(ballAnimation.isRunning()){
            //ballanimation.stop();
        }
    }
*/
    private void handleAnswer() {
        String answer = aMagicEightBall.getAnswer();
        aAnswerLabel.setText(answer);
        //animateMagicEight();
        //animateAnswer();
        //playSound();
    }


}

