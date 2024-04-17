package simstation;
import mvc.*;
import java.util.*;

public class Simulation extends Model {
    int clock = 0;
    List<Agent> agents;

    public void start() {
        for (Agent agent : agents) {
            agent.start();
        }
    }
    public void suspend() {
        for (Agent agent : agents) {
            agent.suspend();
        }
    }
    public void resume() {
        for (Agent agent : agents) {
            agent.resume();
        }
    }

    public void stop() {
        for (Agent agent : agents) {
            agent.stop();
        }
    }

    //Anson Lau
    public Agent getNeighbor(Agent a, double radius) {
        Random random = new Random();
        int startIndex = random.nextInt(agents.size());
        int index = startIndex;

        do {
            index = (index + 1) % agents.size();
            Agent neighbor = agents.get(index);

            if (neighbor != a && distance(a, neighbor) <= radius) {
                return neighbor;
            }
        } while (index != startIndex);

        return null;
    }

    private double distance(Agent a1, Agent a2) {
        int dx = a1.xc - a2.xc;
        int dy = a1.yc - a2.yc;
        return Math.sqrt(dx * dx + dy * dy);
    }


    public void populate() {

    }
}
