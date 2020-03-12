package com.example.s350db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.ConsumerIrManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageButton volumePlus;
    ImageButton volumeMinus;
    ImageButton next;
    ImageButton previous;
    ImageButton  play_pause;
    ImageButton  pc;
    ImageButton  aux;
    ImageButton  optical;
    ImageButton  coaxial;
    ImageButton  bluetooth;
    ImageButton  power;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        volumePlus = (ImageButton) findViewById(R.id.plus);
        volumeMinus = (ImageButton) findViewById(R.id.minus);
        next = (ImageButton) findViewById(R.id.next);
        pc = (ImageButton) findViewById(R.id.pc);
        aux = (ImageButton) findViewById(R.id.aux);
        optical = (ImageButton) findViewById(R.id.optical);
        previous = (ImageButton) findViewById(R.id.previous);
        play_pause = (ImageButton) findViewById(R.id.play_pause);
        coaxial = (ImageButton) findViewById(R.id.coaxial);
        bluetooth = (ImageButton) findViewById(R.id.bluetooth);
        power = (ImageButton) findViewById(R.id.power);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final String errIr = "The device is not equipped with an IR port";
        final ConsumerIrManager consumerIrManager = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        final int frequency = 36000;
        final int vibratemilsec = 50;
        final int delaysender = 150;
        final int[] powerCode = new int[]{9000,4500, 600,500, 600,500, 600,500, 650,1650, 550,1650, 600,1650, 600,1650, 550,600, 550,550, 550,1700, 550,1700, 550,1650, 550,600, 550,600, 500,600, 550,550, 550,600, 550,550, 550,1700, 550,1700, 550,1650, 600,600, 550,550, 550,550, 550,1700, 550,1700, 550,550, 550,600, 550,550, 550,1700, 550,1700, 550,1700, 550};  // NEC 1E7038C7
        final int[] vplusCode = new int[]{9050,4450, 600,500, 600,550, 600,500, 650,1600, 600,1650, 600,1600, 650,1650, 600,500, 600,500, 600,1650, 650,1600, 600,1650, 650,450, 650,450, 650,500, 600,550, 600,500, 650,450, 650,1600, 650,1600, 650,450, 650,500, 650,450, 650,500, 600,1650, 600,1600, 650,500, 600,500, 650,1600, 650,1600, 600,1650, 650,1600, 600};  // NEC 1E7030CF
        final int[] vminusCode = new int[]{9000,4500, 550,550, 600,550, 600,500, 550,1700, 600,1650, 550,1650, 600,1650, 650,500, 600,550, 500,1700, 600,1650, 600,1650, 550,550, 600,550, 600,500, 600,500, 600,1700, 550,1650, 600,1650, 550,1700, 650,450, 650,500, 550,550, 600,550, 550,550, 600,500, 600,550, 600,500, 550,1700, 650,1600, 600,1650, 600,1600, 600};  // NEC 1E70F00F
        final int[] pcCode = new int[]{9000,4400, 650,550, 550,550, 550,600, 550,1650, 600,1650, 550,1700, 600,1600, 600,550, 600,550, 550,1650, 600,1650, 600,1650, 600,550, 600,450, 650,500, 650,500, 550,550, 600,550, 550,550, 550,550, 600,550, 600,500, 600,550, 550,550, 550,1700, 600,1650, 550,1650, 650,1650, 550,1650, 600,1650, 600,1650, 600,1650, 550};  // NEC 1E7000FF
        final int[] auxCode = new int[]{9000,4500, 550,550, 550,600, 550,550, 550,1700, 600,1650, 550,1700, 550,1650, 600,550, 550,600, 550,1650, 550,1700, 550,1700, 550,600, 500,600, 550,550, 600,550, 550,1650, 600,550, 550,550, 600,550, 550,600, 500,600, 550,550, 600,500, 600,550, 550,1700, 550,1650, 600,1700, 550,1650, 550,1700, 600,1650, 550,1700, 550};  // NEC 1E70807F
        final int[] opticalCode = new int[]{8850,4600, 500,600, 550,600, 500,650, 450,1750, 500,1750, 550,1700, 500,1750, 450,650, 600,550, 500,1750, 550,1650, 600,1650, 550,550, 550,600, 500,600, 500,650, 550,550, 550,1700, 550,1650, 600,1700, 550,550, 550,600, 500,600, 500,700, 450,1700, 550,600, 550,600, 550,500, 550,1700, 550,1650, 600,1700, 550,1700, 550};  // UNKNOWN C5F2F7F6
        final int[] coaxialCode = new int[]{9000,4450, 600,500, 600,550, 600,500, 600,1650, 600,1650, 600,1650, 600,1600, 600,550, 600,550, 550,1700, 550,1650, 600,1650, 600,500, 600,550, 600,500, 600,550, 600,500, 600,1650, 600,1650, 600,1650, 550,550, 600,500, 650,500, 600,550, 550,1650, 600,550, 600,500, 600,500, 600,1650, 600,1650, 600,1650, 600,1650, 600};  // NEC 1E70708F
        final int[] nextCode = new int[]{9050,4450, 600,500, 650,500, 600,550, 550,1600, 650,1600, 650,1650, 600,1600, 650,500, 600,550, 600,1600, 600,1650, 650,1600, 600,500, 600,550, 600,500, 600,500, 600,1650, 600,1650, 600,500, 650,550, 550,550, 550,550, 600,500, 650,500, 600,500, 600,550, 600,1650, 600,1650, 550,1650, 600,1650, 600,1650, 600,1650, 600};  // NEC 1E70C03F
        final int[] previousCode = new int[]{9000,4500, 500,650, 500,600, 500,600, 550,1700, 550,1700, 500,1750, 550,1700, 550,550, 500,650, 500,1750, 500,1700, 550,1700, 550,600, 500,600, 550,600, 500,600, 500,600, 550,600, 500,650, 500,600, 500,600, 500,600, 550,1700, 550,600, 550,1650, 550,1700, 550,1700, 550,1700, 500,1750, 550,1700, 500,600, 550,1700, 550};  // UNKNOWN 8640BF85
        final int[] playPauseCode = new int[]{9000,4500, 550,550, 600,550, 550,550, 600,1650, 600,1650, 550,1650, 600,1650, 600,550, 600,500, 600,1650, 600,1650, 600,1650, 600,500, 600,550, 550,550, 600,550, 550,550, 550,1700, 600,500, 600,550, 550,550, 600,550, 550,550, 600,550, 550,1650, 600,550, 600,1650, 550,1700, 550,1650, 600,1650, 600,1650, 600,1600, 650};  // NEC 1E7040BF
        final int[] bluetoothCode = new int[]{9000,4500, 550,550, 500,650, 500,600, 550,1750, 500,1700, 550,1700, 550,1650, 600,550, 550,550, 550,1650, 650,1650, 550,1650, 650,500, 550,600, 600,500, 600,550, 550,1650, 600,500, 650,1600, 600,1650, 600,550, 550,600, 500,600, 550,550, 550,600, 500,1700, 600,550, 550,550, 600,1650, 550,1700, 550,1700, 550,1650, 600};  // UNKNOWN E0CC026F
        SwitchCompat onOffSwitch = (SwitchCompat)  findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", ""+isChecked);
                state = isChecked;
            }

        });


        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, bluetoothCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, playPauseCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, previousCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, nextCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        coaxial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, coaxialCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        optical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, opticalCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, auxCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, pcCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(state == true) {
                        v.vibrate(vibratemilsec);
                    }
                    consumerIrManager.transmit(frequency, powerCode);
                }
                catch(UnsupportedOperationException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            errIr, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });



        volumePlus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vplusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vplusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });

        volumeMinus.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        vibro.run();
                        mHandler.postDelayed(mAction, 500);
                        Log.e("DOWN","DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        Log.e("UP","UP");
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    System.out.println("Performing action...");
                    try {
                        consumerIrManager.transmit(frequency, vminusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {

                    }
                    mHandler.postDelayed(this, delaysender);
                }
            };

            Runnable vibro = new Runnable() {
                @Override
                public void run() {
                    Log.e("Vibrate","VIBRO");
                    Log.e("BUT","WORK");
                    try {
                        if(state == true) {
                            v.vibrate(vibratemilsec);
                        }
                        consumerIrManager.transmit(frequency, vminusCode);
                    }
                    catch(UnsupportedOperationException e)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                errIr, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            };

        });



    }
}
