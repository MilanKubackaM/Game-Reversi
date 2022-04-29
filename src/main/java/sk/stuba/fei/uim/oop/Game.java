package sk.stuba.fei.uim.oop;

import java.util.Arrays;

public class Game {
    public Game(){
        PlayGame();
    }
    private static int boardSize = 8;
    private static int[][] board = new int[boardSize][boardSize];

    static BackgroundMake backgroundMake = new BackgroundMake();

    public static int[][] getBoard() {
        return board;
    }

    public static void setBoard(int[][] board) {
        Game.board = board;
    }

    public static int getBoardSize() {
        return boardSize;
    }

    public static void setBoardSize(int boardSize) {
        Game.boardSize = boardSize;
    }

    // ----------------------------------------------------------------------------
    public static void PlayGame() {

        int middle = boardSize / 2 - 1;
        for (int[] row : board)
            Arrays.fill(row, 0);

        PlayersCircle[] playersCircle = new PlayersCircle[2];

        playersCircle[0] = new PlayersCircle();
        playersCircle[1] = new PlayersCircle();

        playersCircle[0].setColor(1);
        playersCircle[1].setColor(2);


        board[middle][middle] = playersCircle[0].getColor();
        board[middle + 1][middle + 1] = playersCircle[0].getColor();
        board[middle][middle + 1] = playersCircle[1].getColor();
        board[middle + 1][middle] = playersCircle[1].getColor();

//----------------------------------------------------------------------------

        int playerOnMoveColor = playersCircle[0].getColor();

        ShadowCircle.checkBoardForShadow(board, boardSize, playerOnMoveColor);
        backgroundMake.PrintBackground(board);
    }
}

