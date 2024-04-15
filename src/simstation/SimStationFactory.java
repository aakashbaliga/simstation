package simstation;
import mvc.*;

public class SimStationFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model m) {
        return new SimulationView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{
                "Start",
                "Suspend",
                "Resume",
                "Stop",
                "Stats"
        };
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "Start": {
                return new StartCommand(model);
            }
            case "Suspend": {
                return new SuspendCommand(model);
            }
            case "Resume": {
                return new ResumeCommand(model);
            }
            case "Stop": {
                return new StopCommand(model);
            }
            case "Stats": {
                return new StatsCommand(model);
            }
            default:
                return null;
        }
    }

    @Override
    public String getTitle() {
        return "SimStation";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                //fill when we know what everything does
        };
    }

    @Override
    public String about() {
        return "SimStation. Aakash Baliga, Anson Lau, 2024";
    }
}
