package sk.stuba.fei.uim.oop;

public class CircleHandler {

    public static void print2D(int[][] board, int boardSize){       //funkcia na vypisovanie do konzole
        for (int y = 0; y < boardSize; y++){
            for (int x = 0; x < boardSize; x++){
                System.out.print(board[x][y] + ", ");
            }
            System.out.print("\n");
        }
    }

    static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        CircleHandler.counter = counter;
    }

    public static int[] countPoints(){
        int[] points = new int[2];
        for (int y = 0; y < Game.getBoardSize(); y++){
            for (int x = 0; x < Game.getBoardSize(); x++){
                if(Game.getBoard()[x][y] == 1)
                    points[0]++;
                else if (Game.getBoard()[x][y] == 2)
                    points[1]++;
            }
        }
        return points;
    }

    public static int[][] pickCircle(int[][] secondBoard, int boardSize, int pickedPosition_X, int pickedPosition_Y, int player, int enemyPlayer) {
        int[][] board = new int[boardSize][boardSize];
        int[][] checkBoard = new int[boardSize][boardSize];
        boolean check = false;
        boolean checkIfThereWasShadow = false;

        for(int y = 0; y < boardSize; y++)
            for (int x = 0; x < boardSize; x++){
                checkBoard[x][y] = secondBoard[x][y];
                board[x][y] = secondBoard[x][y];
            }

// ----------------------------   Zachytavanie po sirke    ----------------------------------------------------------

        for (int i = 0; i < boardSize; i++) {
            if (checkBoard[i][pickedPosition_Y] == player || pickedPosition_X == i){
                if(checkIfThereWasShadow && checkBoard[i][pickedPosition_Y] == 8)
                    break;
                else if(checkBoard[i][pickedPosition_Y] == 8)
                    checkIfThereWasShadow = true;

                for(int x = i+1; x < boardSize; x++){
                    if (checkBoard[x][pickedPosition_Y] == enemyPlayer){
                        check = true;
                        continue;
                    }
                    if(!check)
                        break;
                    if (checkBoard[x][pickedPosition_Y] == player || i == pickedPosition_X){ // pri
                        if (x > i){
                            for(int c = i; c <= x; c++){
                                board[c][pickedPosition_Y] = player;
                                counter++;
                            }
                        }
                        else if (x < i){
                            for(int c = i; c >= x; c--){
                                board[c][pickedPosition_Y] = player;
                                counter++;
                            }
                        }
                        break;
                    }
                }
            }
        }

        check = false;
        checkIfThereWasShadow = false;

// ----------------------------   Zachytavanie po vyske    ----------------------------------------------------------

        boolean checkIfItsDone = false;

        for (int i = 0; i < boardSize; i++) {
            if (checkBoard[pickedPosition_X][i] == player || pickedPosition_Y == i){
                if(checkIfThereWasShadow && checkBoard[pickedPosition_X][i] == 8)
                    break;
                else if(checkBoard[pickedPosition_X][i] == 8)
                    checkIfThereWasShadow = true;
                for(int x = i+1; x < boardSize; x++){
                    if (checkBoard[pickedPosition_X][x] == enemyPlayer){
                        check = true;
                        continue;
                    }
                    if(!check)
                        break;
                    if (checkBoard[pickedPosition_X][x] == player || x == pickedPosition_Y){
                        if(x > i){
                            for(int c = i; c <= x; c++){
                                board[pickedPosition_X][c] = player;
                                counter++;
                                checkIfItsDone = true;
                            }
                            break;
                        }
                        else if (x < i){
                            for(int c = i; c >= x; c--){
                                board[pickedPosition_X][c] = player;
                                counter++;
                                checkIfItsDone = true;
                            }
                            break;
                        }
                    }
                }
                if(checkIfItsDone) break;
            }
        }

        check = false;
        checkIfThereWasShadow = false;

// ----------------------------   Zachytavanie diagonalne zlaveho horneho rohu dole   -----------------------------------------------------

        int temp_y = pickedPosition_Y;
        int temp_x = pickedPosition_X;

        if (pickedPosition_Y>pickedPosition_X){
            for(int v = 0; v < boardSize; v++){
                if (temp_x == (boardSize - 1) || temp_y == (boardSize - 1) || temp_x == 0 || temp_y == 0)
                    break;
                temp_y--;
                temp_x--;
            }
        }
        else if (pickedPosition_Y==pickedPosition_X){
            temp_y = 0;
            temp_x = 0;
        }

        while (temp_x < boardSize  && temp_y < boardSize) {
            if(temp_x < 0 || temp_y < 0 || temp_x >= boardSize || temp_y >= boardSize)
                break;
            if (checkBoard[temp_x][temp_y] == player || checkBoard[temp_x][temp_y] == 8){
                if(checkIfThereWasShadow && checkBoard[temp_x][temp_y] == 8) {
                    temp_x++;
                    temp_y++;
                    break;
                }
                else if(checkBoard[temp_x][temp_y] == 8)
                    checkIfThereWasShadow = true;
                int check_y = temp_y;
                for(int x = temp_x; x < boardSize; x++, check_y++){
                    if(check_y >= boardSize)
                        break;
                    if (checkBoard[x][check_y] == enemyPlayer){
                        check = true;
                        continue;
                    }
                    if (check && checkBoard[x][check_y] == player || x == pickedPosition_X && check_y == pickedPosition_Y) {
                        int yyy = temp_y;
                        for (int c = temp_x; c <= x; c++, yyy++) {
                            board[c][yyy] = player;
                            counter++;
                        }
                    }
                }
            }
            check = false;
            temp_x++;
            temp_y++;
        }


        check = false;
        checkIfThereWasShadow = false;

// ----------------------------   Zachytavanie diagonalne praveho horneho rohu dole   -----------------------------------------------------

        temp_y = pickedPosition_Y;
        temp_x = pickedPosition_X;

        if (pickedPosition_Y>pickedPosition_X){
            for(int v = 0; v < boardSize; v++){
                if (temp_x == (boardSize - 1) || temp_y == (boardSize - 1) || temp_x == 0 || temp_y == 0)
                    break;
                temp_y--;
                temp_x++;
            }
        }
        else if (pickedPosition_Y==pickedPosition_X){
            temp_y = boardSize - 1;
            temp_x = boardSize - 1;
        }

        for (int p = 0; p < boardSize; p++) {
            if(temp_x < 0 || temp_y < 0 || temp_x >= boardSize || temp_y >= boardSize)
                break;
            if (checkBoard[temp_x][temp_y] == player || checkBoard[temp_x][temp_y] == 8){
                if(checkIfThereWasShadow && checkBoard[temp_x][temp_y] == 8){
                    break;
                }
                else if(checkBoard[temp_x][temp_y] == 8)
                    checkIfThereWasShadow = true;

                int check_y = temp_y;
                for(int check_x = temp_x; check_x > 0; check_x--, check_y++){
                    if(check_y == boardSize)
                        break;
                    if(check_y == 0)
                        break;
                    if (checkBoard[check_x][check_y] == enemyPlayer){
                        check = true;
                        continue;
                    }
                    if (check && checkBoard[check_x][check_y] == player || check_x == pickedPosition_X && check_y == pickedPosition_Y) {
                        int yyy = temp_y;
                        for (int c = temp_x; c >= check_x; c--, yyy++) {
                            board[c][yyy] = player;
                            counter++;
                        }
                    }
                }
            }
            check = false;
            temp_x--;
            temp_y++;
        }
        return board;
    }



}

