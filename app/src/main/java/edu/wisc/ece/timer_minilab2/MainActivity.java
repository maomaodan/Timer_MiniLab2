package edu.wisc.ece.timer_minilab2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    //private boolean enable = false;
    private String txt;
    Handler handler = new Handler();

    myTimer timer1 = new myTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Start = (Button) findViewById(R.id.button_start);
        Button Pause = (Button) findViewById(R.id.button_pause);
        Button Reset = (Button) findViewById(R.id.button_stop);
        textView = (TextView) findViewById((R.id.textView));


        Start.setOnClickListener(new Button.OnClickListener() {
            //@Override
            public void onClick(View v) {
                //v.postDelayed(timer1,1000);

                timer1.run();

            }


        });
        Reset.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView.setText("0:00:00");
                timer1.stop();

            }
        });

        Pause.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                timer1.pause();

            }

        });
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


    class myTimer implements Runnable{
        Timer timer;
        TimerTask ttask;

        long baseTime;
        long elapsedTime;
        long lastTime = 0;
        boolean enable = false;
        public myTimer(){




        }

        @Override
        public void run() {


            if (!enable){
                baseTime = SystemClock.elapsedRealtime();
                timer = new Timer();
                ttask = new TimerTask() {
                    @Override
                    public void run() {


                        enable = true;
                        elapsedTime = SystemClock.elapsedRealtime() - baseTime + lastTime;
                        txt = "";

                        int hours = (int) (elapsedTime / (3600 * 1000));
                        int remaining = (int) (elapsedTime % (3600 * 1000));

                        int minutes = (int) (remaining / (60 * 1000));
                        remaining = (int) (remaining % (60 * 1000));

                        int seconds = (int) (remaining / 1000);
                        remaining = (int) (remaining % (1000));

                        int milliseconds = (int) remaining;
                        txt += minutes + ":" + seconds + ":" + milliseconds;
                        handler.post(new Runnable() {
                            public void run() {
                                textView.setText(txt);
                            }
                        });

                   /* runOnUiThread(new Runnable(){
                        @Override
                        public void run()
                        {
                            textView.setText(txt);
                        }
                    });*/


                    }


                };
                timer.scheduleAtFixedRate(ttask, 0, 50);
//            timer.schedule(ttask,0,100);


                // Message msg = handler.obtainMessage();

                //   textView.setText(txt);

            }

        }

        public void pause()
        {
            if (enable)
            {
                lastTime = elapsedTime;
                timer.cancel();
                enable = false;
            }
            else
                this.run();

        }
        public void stop()
        {
            enable = false;
            timer.cancel();
            elapsedTime = 0;
            lastTime = 0;
        }



        public void updateUI()
        {



                    /*
                    baseTime = SystemClock.currentThreadTimeMillis();
                    elapsedTime = SystemClock.currentThreadTimeMillis() - baseTime;
                    txt = "";

                    int hours = (int) (elapsedTime / (3600 * 1000));
                    int remaining = (int) (elapsedTime % (3600 * 1000));

                    int minutes = (int) (remaining / (60 * 1000));
                    remaining = (int) (remaining % (60 * 1000));

                    int seconds = (int) (remaining / 1000);
                    remaining = (int) (remaining % (1000));

                    int milliseconds = (int) (((int) elapsedTime % 1000) / 100);



                    */








        }





    }







                    /*baseTime = SystemClock.currentThreadTimeMillis();

                    elapsedTime = SystemClock.currentThreadTimeMillis() - baseTime;
                    txt = "";

                    int hours = (int) (elapsedTime / (3600 * 1000));
                    int remaining = (int) (elapsedTime % (3600 * 1000));

                    int minutes = (int) (remaining / (60 * 1000));
                    remaining = (int) (remaining % (60 * 1000));

                    int seconds = (int) (remaining / 1000);
                    remaining = (int) (remaining % (1000));

                    int milliseconds = (int) (((int) elapsedTime % 1000) / 100);



                    */






}


