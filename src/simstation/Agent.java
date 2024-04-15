package simstation;

import java.io.*;

public abstract class Agent implements Runnable, Serializable {
    String name;
    //Heading heading??
    int xc;
    int yc;
    boolean suspended = false;
    boolean stopped = false;
    Thread myThread;

    public void run() {

    }
    public void start() {

    }
    public void suspend() {

    }
    public void resume() {

    }

    public void stop() {

    }

    public abstract void update();

    public void move(int steps) {

    }
}
