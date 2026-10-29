import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tick {
    int startTime;
    boolean active;
    int time[] = {startTime};
    Timer timer = new Timer();
    boolean master;
    
    public Tick(int s, boolean m, Thread t) {
        Thread myThread = t;
        master = m;
        time[0] = s;
        active = true;
        TimerTask tick = new TimerTask() {
            public void run() {
                time[0] -= 1;
                if (time[0] <= 0) {

                    if (master) {
                        myThread.interrupt();
                    }
                    this.cancel();
                    timer.cancel();
                    
                    
                }
            }
        }; 
        timer.scheduleAtFixedRate(tick, 0, 1000);
    }

    public void setTime(int timeToSet) {
        time[0] = timeToSet;
    }

    public int getTime() {
        return time[0];
    }
}