package com.cau.hyobin.moscatchgame;

/**
 * Created by hyobCom on 2015-12-02.
 */
public class TimerThread extends Thread {
    public static boolean isRun;

    int milsec;
    int sec;
    int min;
    int hour;

    public TimerThread(){
        isRun = true;
        hour = min = sec = 0;
    }

    public void Init(){
        isRun = true;
        hour = min = sec = 0;
    }

    public void run() {
        while (isRun) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            milsec++;
            if(milsec == 100){
                sec++;
                milsec = 0;
            }
            if(sec == 60){
                min++;
                sec = 0;
            }
            if(min == 60){
                hour++;
                min = 0;
            }
        }
    }
}
