package sk.stuba.fei.uim.oop;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

class Mouse extends Frame implements MouseListener {

    private int playerOnMove = 1;

    Mouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = (e.getXOnScreen()-8)/50;
        int y = (e.getYOnScreen()-54)/50;
        int[] tempPos;

        if(Game.getBoard()[x][y] == 8){
            int enemyPlayer = 2;

            Game.setBoard(CircleHandler.pickCircle(Game.getBoard(), Game.getBoardSize(), x, y, playerOnMove, enemyPlayer));
            ShadowCircle.checkBoardForShadow(Game.getBoard(), Game.getBoardSize(), enemyPlayer);
            BackgroundMake.SetColors(Game.getBoard());

            playerOnMove = 2;
            enemyPlayer = 1;
            tempPos = PcPlayer.computerPlay(Game.getBoardSize(), playerOnMove, enemyPlayer);
            x = tempPos[0];
            y = tempPos[1];

            Game.setBoard(CircleHandler.pickCircle(Game.getBoard(), Game.getBoardSize(), x, y, playerOnMove, enemyPlayer));
            ShadowCircle.checkBoardForShadow(Game.getBoard(), Game.getBoardSize(), enemyPlayer);
            BackgroundMake.SetColors(Game.getBoard());

            playerOnMove = 1;
            MenuBar.setPointsCounter(CircleHandler.countPoints());

            boolean checkIfContinue = false;
            for (int yy = 0; yy < Game.getBoardSize(); yy++){
                for (int xx = 0; xx < Game.getBoardSize(); xx++){
                    if (Game.getBoard()[xx][yy] == 8){
                        checkIfContinue = true;
                        break;
                    }
                }
            }

            if (!checkIfContinue){
                if (MenuBar.pointsCounter[0] > MenuBar.pointsCounter[1])
                    JOptionPane.showMessageDialog(BackgroundMake.frame,"You won against AI!\n      SCORE \nYours points: " + MenuBar.pointsCounter[0] + "\nAI points: " + MenuBar.pointsCounter[1]);
                else if (MenuBar.pointsCounter[0] < MenuBar.pointsCounter[1])
                    JOptionPane.showMessageDialog(BackgroundMake.frame,"AI won, try another time!\n      SCORE \nYours points: " + MenuBar.pointsCounter[0] + "\nAI points: " + MenuBar.pointsCounter[1]);
                else
                    JOptionPane.showMessageDialog(BackgroundMake.frame,"Its draw!\n  SCORE \nYours points: " + MenuBar.pointsCounter[0] + "\nAI points: " + MenuBar.pointsCounter[1]);

            }
        }
        MenuBar.UpdatePoints();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        int x = (e.getXOnScreen()-8)/50;
        int y = (e.getYOnScreen()-54)/50;
        if (x >= 8) x = 7;
        else if (x < 0) x = 0;
        if (y >= 8) y = 7;
        else if (y < 0) y = 0;
        BackgroundMake.panelArray[x][y].setBackground((Color.gray));
    }

    int[] reminder = new int[2];

    @Override
    public void mouseExited(MouseEvent e) {
        int x = (e.getXOnScreen()-8)/50;
        int y = (e.getYOnScreen()-54)/50;

        if (x >= 8) x = 7;
        else if (x < 0) x = 0;
        if (y >= 8) y = 7;
        else if (y < 0) y = 0;

        if (Game.getBoard()[reminder[0]][reminder[1]] == 1) {
            BackgroundMake.panelArray[reminder[0]][reminder[1]].setBackground(Color.blue);
        } else if (Game.getBoard()[reminder[0]][reminder[1]] == 2) {
            BackgroundMake.panelArray[reminder[0]][reminder[1]].setBackground(Color.red);
        } else if (Game.getBoard()[reminder[0]][reminder[1]] == 8) {
            BackgroundMake.panelArray[reminder[0]][reminder[1]].setBackground(Color.lightGray);
        } else {
            BackgroundMake.panelArray[reminder[0]][reminder[1]].setBackground(new Color(156, 255, 139));
        }

        reminder[0] = x;
        reminder[1] = y;
    }
}
