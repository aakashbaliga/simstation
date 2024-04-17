package flocking;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class Bird extends Agent {
    private int speed;

    public Bird() {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5) + 1; // Random speed between 1 and 5
    }

    public void update() {
        Agent neighbor = world.getNeighbor(this, 50);
        if (neighbor != null) {
            heading = neighbor.heading;
            speed = neighbor.speed;
        }
        move(speed);
    }
}

class FlockingFactory extends SimStationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking Simulation"; }
}

public class FlockingSimulation extends Simulation {

    public void populate() {
        for (int i = 0; i < 50; i++)
            addAgent(new Bird());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
