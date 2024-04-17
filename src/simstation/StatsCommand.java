package simstation;
import mvc.*;
import javax.swing.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }
    public void execute() {
        Simulation s = (Simulation) model;
        String message = "#agents = " + s.getAgents().size() + ", clock = " + s.getClock();
        JOptionPane.showMessageDialog(null, message, "Simulation Stats", JOptionPane.INFORMATION_MESSAGE);
    }
}
