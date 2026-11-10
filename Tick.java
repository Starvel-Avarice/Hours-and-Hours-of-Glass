import java.util.Timer;
import java.util.TimerTask;

public class Tick {
    int startTime;
    boolean active;
    int time[] = {startTime};
    Timer timer = new Timer();
    boolean master;
    String type;

    public Tick(int s, boolean m, Thread t, String ty) {
        Thread myThread = t;
        master = m;
        time[0] = s;
        active = true;
        type = ty;
        TimerTask tick = new TimerTask() {
            public void run() {
                time[0] -= 1;
                if (time[0] <= 0) {
                    if (master) {
                        Game.interruption = "endGame";
                        myThread.interrupt();
                    } else if (type.equals("torch")) {
                        Game.hasTorch = false;
                        Inventory.removeItem(8);
                        Game.interruption = "torchBurnedOut";
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