package com.example.s350db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.ConsumerIrManager;
import android.media.Image;
import android.media.ImageReader;
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
        final int[] powerCode = new int[]{9061,4384,689,433,694,436,692,432,668,451,690,1548,686,431,706,437,686,444,672,1528,705,1546,685,1542,695,428,718,428,679,1553,691,1542,670,1543,703,448,686,1521,694,1538,692,432,693,431,699,429,686,1547,686,433,693,1549,663,458,687,442,686,1545,688,1543,684,1542,731,418,670,1541,695};
        final int[] vplusCode = new int[]{9114,4384,687,433,689,433,687,436,687,431,692,1548,686,437,690,434,705,419,691,1539,703,1532,692,1540,689,432,691,433,702,1539,693,1533,705,1530,689,1545,689,432,692,1546,687,436,693,420,703,433,694,451,667,431,698,427,690,1550,698,422,688,1548,686,1565,670,1540,693,1540,694,1539,682};
        final int[] vminusCode = new int[]{9055,4439,635,475,635,498,639,473,639,486,616,1622,609,511,608,501,628,507,649,1597,632,1593,608,1631,603,512,618,506,616,1619,609,1624,615,1618,609,1623,610,1624,607,1624,624,513,610,501,610,528,596,1637,610,514,599,511,611,514,611,513,615,1624,609,1627,602,1611,622,512,616,1620,613};
        final int[] pcCode = new int[]{9084,4382,694,432,679,433,689,443,681,433,696,1538,690,434,694,435,684,452,684,1533,695,1542,687,1547,690,432,734,383,683,1565,681,1542,689,1544,690,1542,703,1530,702,421,705,418,706,432,666,442,692,432,724,402,691,438,690,442,702,1512,693,1553,691,1581,636,1556,689,1540,695,1541,680};
        final int[] auxCode = new int[]{9101,4394,663,457,690,435,689,430,667,455,673,1561,664,462,663,456,666,456,697,1534,701,1543,685,1543,702,420,690,445,667,1554,703,1529,685,1564,650,471,665,1575,648,457,665,458,666,457,683,449,703,414,675,455,662,1560,679,456,665,1555,686,1549,681,1555,682,1563,663,1564,670,1571,659};
        final int[] opticalCode = new int[]{9115,4367,681,435,692,441,679,435,694,435,705,1530,688,429,704,422,695,433,690,1539,692,1542,687,1546,689,433,689,435,689,1545,689,1548,675,1554,689,1567,678,431,693,433,683,427,706,445,680,418,681,443,665,460,673,452,692,1542,666,1566,695,1536,689,1549,659,1570,663,1564,670,1568,666};
        final int[] coaxialCode = new int[]{9115,4377,686,457,673,427,696,432,696,429,682,1548,691,436,701,420,701,418,705,1541,678,1556,678,1555,680,425,700,432,696,1542,684,1550,687,1559,678,1539,689,432,691,425,698,1552,690,427,680,448,687,433,695,428,698,426,692,1543,690,1556,690,432,683,1541,702,1528,711,1536,678,1542,680};
        final int[] nextCode = new int[]{9098,4394,670,449,686,435,689,433,692,465,658,1543,691,432,691,447,692,417,691,1543,706,1528,690,1546,691,433,674,431,728,1510,689,1561,686,1541,690,432,690,1545,691,1582,655,1542,689,1540,690,432,692,433,713,410,690,1543,704,433,679,447,675,433,689,431,705,1544,677,1541,692,1548,690};
        final int[] previousCode = new int[]{9045,4473,615,514,627,487,592,513,611,529,610,1624,608,514,634,492,631,487,636,1594,639,1598,650,1595,623,502,610,512,596,1626,608,1623,611,1629,609,1622,607,1615,607,1642,604,519,647,453,662,471,651,487,632,493,604,521,608,508,616,509,629,1619,624,1601,632,1611,609,1612,631,1591,670};
        final int[] playPauseCode = new int[]{9080,4427,610,528,594,518,653,462,636,489,637,1602,641,467,621,510,625,498,628,1622,594,1627,596,1628,622,498,636,500,638,1581,652,1602,630,1599,641,470,647,478,649,1597,603,1628,638,1585,666,477,600,1625,609,497,643,1619,595,1637,659,431,632,512,649,474,649,1585,637,492,611,1623,612};
        final int[] bluetoothCode = new int[]{9134,4377,664,446,689,437,694,426,687,440,690,1545,683,439,689,433,693,439,680,1544,695,1541,689,1550,687,436,696,422,691,1544,675,1558,688,1544,687,1559,665,458,675,454,697,427,688,436,673,456,668,1550,688,430,689,440,671,1563,685,1545,674,1558,700,1523,706,1543,689,434,672,1559,687};
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
