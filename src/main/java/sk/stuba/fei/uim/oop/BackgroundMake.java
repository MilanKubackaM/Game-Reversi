package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BackgroundMake extends JFrame {
    static javax.swing.JPanel[][] panelArray = new javax.swing.JPanel[Game.getBoardSize()][Game.getBoardSize()];
    int mainPanelDimension = 0;
    JPanel mainPanel = new JPanel();

    public void setPanelArray(JPanel[][] panelArray) {
        Mouse m = new Mouse();
        for(int y = 0; y < Game.getBoardSize(); y++){
            for(int x = 0; x < Game.getBoardSize(); x++){
                panelArray[x][y] = new JPanel();
                panelArray[x][y].setLayout(null);
                panelArray[x][y].setBorder(BorderFactory.createLineBorder(Color.black));
                panelArray[x][y].setSize(50,50);
                panelArray[x][y].setPreferredSize(new Dimension(50, 50));
                panelArray[x][y].setMaximumSize(new Dimension(50, 50));
                panelArray[x][y].setLocation(x*50,y*50);
                panelArray[x][y].setVisible(true);
                panelArray[x][y].addMouseListener(m);
                mainPanel.add(panelArray[x][y]);
            }
        }
    }

    public static void SetColors(int[][] board){
        for(int y = 0; y < Game.getBoardSize(); y++) {
            for (int x = 0; x < Game.getBoardSize(); x++) {
                if (board[x][y] == 1)
                    panelArray[x][y].setBackground(Color.blue);
                else if (board[x][y] == 2)
                    panelArray[x][y].setBackground(Color.red);
                else if (board[x][y] == 8)
                    panelArray[x][y].setBackground(Color.lightGray);
                else
                    panelArray[x][y].setBackground(new Color(156, 255, 139));
            }
        }
    }
    static JFrame frame = new JFrame("Colored Trails");


    public static void addToFrame(JMenuBar menu) {
        BackgroundMake.frame.setJMenuBar(menu);
    }

    public void resetFrame(){
        int[][] board = new int[Game.getBoardSize()][Game.getBoardSize()];      //pise duplicitny kod, no kvoli SetColors to tak byt musi
        int middle = Game.getBoardSize()/2-1;
        for (int[] row : board)
            Arrays.fill(row, 0);

        board[middle][middle] = 1;
        board[middle + 1][middle + 1] = 1;
        board[middle][middle + 1] = 2;
        board[middle + 1][middle] = 2;

        ShadowCircle.checkBoardForShadow(board, Game.getBoardSize(), 1);
        Game.setBoard(board);

        SetColors(Game.getBoard());
        MenuBar.setPointsCounter(new int[]{0, 0});
        MenuBar.UpdatePoints();

    }


    public void resetFrameBB(){
        int[][] board = new int[Game.getBoardSize()][Game.getBoardSize()];
        int middle = Game.getBoardSize()/2-1;
        for (int[] row : board)
            Arrays.fill(row, 0);

        board[middle][middle] = 1;
        board[middle + 1][middle + 1] = 1;
        board[middle][middle + 1] = 2;
        board[middle + 1][middle] = 2;

        ShadowCircle.checkBoardForShadow(board, Game.getBoardSize(), 1);
        Game.setBoard(board);

        panelArray = new javax.swing.JPanel[Game.getBoardSize()][Game.getBoardSize()];

        mainPanelDimension = 50*Game.getBoardSize()*2;
        mainPanel.setSize(mainPanelDimension,mainPanelDimension);
        mainPanel.setPreferredSize(new Dimension(mainPanelDimension, mainPanelDimension/2));
        mainPanel.setMaximumSize(new Dimension(mainPanelDimension, mainPanelDimension/2));

        setPanelArray(panelArray);

        SetColors(Game.getBoard());
        MenuBar.setPointsCounter(new int[]{0, 0});
        MenuBar.UpdatePoints();

        frame.setSize(mainPanelDimension/2+20,mainPanelDimension/2+65);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void PrintBackground(int[][] board){
        mainPanelDimension = 50*Game.getBoardSize()*2;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(mainPanelDimension/2+20,mainPanelDimension/2+65);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.green);

        mainPanel.setSize(mainPanelDimension,mainPanelDimension);
        mainPanel.setPreferredSize(new Dimension(mainPanelDimension, mainPanelDimension/2));
        mainPanel.setMaximumSize(new Dimension(mainPanelDimension, mainPanelDimension/2));

        setPanelArray(panelArray);
        MenuBar menuBar = new MenuBar();            //warning: nieje pouzite, ale musime ho inicializovat

        frame.add(mainPanel);
        SetColors(board);
        frame.setVisible(true);
    }
}



