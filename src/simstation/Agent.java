package simstation;

import java.io.*;

public abstract class Agent implements Runnable, Serializable {
    public int speed;
    String name;
    public Heading heading;
    int xc;
    int yc;
    boolean suspended = false;
    boolean stopped = false;
    transient protected Thread myThread;
    protected Simulation world;

    //Anson Lau
    public void run() {
        while (!stopped) {
            if (!suspended) {
                update();
                move(1);
            } try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWorld(Simulation world) {
        this.world = world;
    }

    public void start() {
        if (myThread == null) {
            myThread = new Thread(this);
            myThread.start();
        }
    }

    public void suspend() {
        suspended = true;
    }

    public void resume() {
        suspended = false;
    }

    public void stop() {
        stopped = true;
    }

    public abstract void update();

    public void move(int steps) {
        double radians = Math.toRadians(heading.ordinal() * 90);
        xc += steps * Math.cos(radians);
        yc += steps * Math.sin(radians); 
    }
}
