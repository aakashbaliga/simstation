package plague;

import mvc.*;
import simstation.*;
import java.util.*;

class Host extends Agent {
    private boolean infected;

    public Host() {
        super();
        heading = Heading.random();
        infected = false;
    }

    public boolean isInfected() {
        return infected;
    }

    public void infect() {
        infected = true;
    }

    public void update() {
        if (!infected) {
            Agent neighbor = world.getNeighbor(this, 5);
            if (neighbor != null && neighbor instanceof Host && ((Host) neighbor).isInfected()) {
                if (Utilities.rng.nextInt(100) < PlagueSimulation.VIRULENCE) {
                    infect();
                }
            }
        }
        move(1);
    }
}

class PlagueFactory extends SimStationFactory {
    public Model makeModel() {
        return new PlagueSimulation();
    }

    public String getTitle() {
        return "Plague Simulation";
    }
}

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection

    public void populate() {
        for (int i = 0; i < 100; i++)
            addAgent(new Host());
    }

    public String[] getStats() {
        int infectedCount = 0;
        for (Agent agent : getAgents()) {
            if (agent instanceof Host && ((Host) agent).isInfected()) {
                infectedCount++;
            }
        }

        int totalAgents = getAgents().size();
        double infectedPercentage = (double) infectedCount / totalAgents * 100;

        return new String[]{"Infected: " + infectedPercentage + "%"};
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }
}
