package sk.stuba.fei.uim.oop;

public class PcPlayer {

    public static int[] computerPlay( int boardSize, int player, int enemyPlayer) {

        int pickedPosition_X;
        int pickedPosition_Y;
        int counter = 0;
        int[] finalPos = new int[2];

        for (int y = 0; y < boardSize; y++){
            for (int x = 0; x < boardSize; x++){
                if (Game.getBoard()[x][y] == 8){
                    pickedPosition_X = x;
                    pickedPosition_Y = y;
                    CircleHandler.pickCircle(Game.getBoard(), boardSize, pickedPosition_X, pickedPosition_Y, player, enemyPlayer);

                    if(CircleHandler.getCounter() > counter){
                        counter = CircleHandler.getCounter();
                        finalPos[0] = x;
                        finalPos[1] = y;
                    }
                    CircleHandler.setCounter(0);
                }
            }
        }
        return finalPos;
    }
}
