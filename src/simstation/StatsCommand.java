package simstation;
import mvc.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }
    public void execute() {
        Simulation s = (Simulation) model;
        //make a jframe and print stats
    }
}
