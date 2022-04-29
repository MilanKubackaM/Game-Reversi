package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame implements ActionListener {

    static int[] pointsCounter = new int[2];

    public static void setPointsCounter(int[] pointsCounter) {
        MenuBar.pointsCounter[0] = pointsCounter[0];
        MenuBar.pointsCounter[1] = pointsCounter[1];
    }


    public static void UpdatePoints(){
        points.setText("Player: " + pointsCounter[0] + "  AI: " + pointsCounter[1]);
    }

    JMenuBar mb = new JMenuBar();
    JMenu dimensions;
    JMenuItem reset;
    JMenuItem dim6;
    JMenuItem dim8;
    JMenuItem dim10;
    JMenuItem dim12;
    private static final JMenuItem points = new JMenuItem("Player: " + pointsCounter[0] + "  AI: " + pointsCounter[1]);

    MenuBar(){
        dimensions = new JMenu("Set dimensions");
        reset = new JMenuItem("Reset");

        dim6 = new JMenuItem("6 x 6");             // Resize ma problem s refreshom, policka sa refreshnu az po prejdeni mysou po polickach
        dim8 = new JMenuItem("8 x 8");
        dim10 = new JMenuItem("10 x 10");
        dim12 = new JMenuItem("12 x 12");

        dim6.addActionListener(this);
        dim8.addActionListener(this);
        dim10.addActionListener(this);
        dim12.addActionListener(this);
        reset.addActionListener(this);

        dimensions.add(dim6);
        dimensions.add(dim8);
        dimensions.add(dim10);
        dimensions.add(dim12);

        mb.add(dimensions);
        mb.add(reset);
        mb.add(points);

        BackgroundMake.addToFrame(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==dim6){
            Game.setBoardSize(6);
            Game.backgroundMake.resetFrameBB();
        }
        else if(e.getSource()==dim8){
            Game.setBoardSize(8);
            Game.backgroundMake.resetFrameBB();
        }

        else if (e.getSource()==dim10) {
            Game.setBoardSize(10);
            Game.backgroundMake.resetFrameBB();
        }
        else if (e.getSource()==dim12) {
            Game.setBoardSize(12);
            Game.backgroundMake.resetFrameBB();
        }
        else if (e.getSource()==reset){
            Game.backgroundMake.resetFrame();
        }

    }
}
