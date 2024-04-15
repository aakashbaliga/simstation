package simstation;
import mvc.*;

import javax.swing.*;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        JButton start = new JButton("Start");
        JButton suspend = new JButton("Suspend");
        JButton resume = new JButton("Resume");
        JButton stop = new JButton("Stop");
        JButton stats = new JButton("Stats");
        controlPanel.add(start);
        controlPanel.add(suspend);
        controlPanel.add(resume);
        controlPanel.add(stop);
        controlPanel.add(stats);
        start.addActionListener(this);
        suspend.addActionListener(this);
        resume.addActionListener(this);
        stop.addActionListener(this);
        stats.addActionListener(this);

    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
