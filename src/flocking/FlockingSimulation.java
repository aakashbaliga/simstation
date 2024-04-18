package flocking;

import mvc.*;
import simstation.*;

//Anson Lau
class Bird extends Agent {
    private int speed;

    public Bird() {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(5) + 1; // Random speed between 1 and 5
    }

    public int getSpeed() {
        return speed;
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

    public void getStats() {
        int[] speedCounts = new int[6];
        for (Agent agent : getAgents()) {
            if (agent instanceof Bird) {
                Bird bird = (Bird) agent;
                int speed = bird.getSpeed();
                speedCounts[speed]++;
            }
        }

        String[] stats = new String[5];
        for (int i = 1; i <= 5; i++) {
            stats[i - 1] = "Speed " + i + ": " + speedCounts[i];
        }
        Utilities.inform(stats);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
