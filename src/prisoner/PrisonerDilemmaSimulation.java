package prisoner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import mvc.*;
import simstation.*;

// Define the Prisoner class
class Prisoner extends Agent{
    private int fitness = 0;
    private boolean partnerCheated = false;

    public boolean cooperate(Prisoner opponent) {
        // Choose a strategy based on the opponent's behavior
        boolean strategyResult = false;
        if (this instanceof AlwaysCheatPrisoner) {
            strategyResult = false;
        } else if (this instanceof AlwaysCooperatePrisoner) {
            strategyResult = true;
        } else if (this instanceof RandomCooperatePrisoner) {
            strategyResult = new Random().nextBoolean();
        } else if (this instanceof TitForTatPrisoner) {
            strategyResult = !opponent.partnerCheated;
        }

        return strategyResult;
    }

    public void updateFitness(int myScore, int opponentScore) {
        fitness += myScore;
    }

    public void setPartnerCheated(boolean partnerCheated) {
        this.partnerCheated = partnerCheated;
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public void update() {

    }
}

// Define the strategies
class AlwaysCheatPrisoner extends Prisoner {}
class AlwaysCooperatePrisoner extends Prisoner {}
class RandomCooperatePrisoner extends Prisoner {}
class TitForTatPrisoner extends Prisoner {}

public class PrisonersDilemmaSimulation extends Simulation {
    private List<Prisoner> prisoners;

    public void populate() {
        prisoners = new ArrayList<>();

        // Populate with 10 of each strategy
        for (int i = 0; i < 10; i++) {
            prisoners.add(new AlwaysCheatPrisoner());
            prisoners.add(new AlwaysCooperatePrisoner());
            prisoners.add(new RandomCooperatePrisoner());
            prisoners.add(new TitForTatPrisoner());
        }

        // Shuffle the list to randomize the order of strategies
        Collections.shuffle(prisoners);
        addAgents(prisoners);
    }

    public void update() {
        for (Agent agent : getAgents()) {
            Prisoner self = (Prisoner) agent;
            Prisoner opponent = (Prisoner) getRandomNeighbor(self);

            if (opponent != null) {
                boolean myDecision = self.cooperate(opponent);
                boolean opponentDecision = opponent.cooperate(self);

                int myScore = myDecision ? (opponentDecision ? 3 : 0) : (opponentDecision ? 5 : 1);
                int opponentScore = opponentDecision ? (myDecision ? 3 : 5) : (myDecision ? 0 : 1);

                self.updateFitness(myScore, opponentScore);
                opponent.setPartnerCheated(!myDecision);
            }
        }
    }

    public static void main(String[] args) {
        Simulation sim = new PrisonersDilemmaSimulation();
        panel.display();
    }
}
