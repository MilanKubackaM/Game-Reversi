package sk.stuba.fei.uim.oop;

public class ShadowCircle {
    public ShadowCircle(){}

    public static void checkBoardForShadow(int [][] boardCondition, int boardSize, int playerColor) {

        for (int y = 0; y < boardSize; y++){
            for (int x = 0; x < boardSize; x++){
                if (boardCondition[x][y] == 8)
                    boardCondition[x][y] = 0;
            }
        }

        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                if (boardCondition[x][y] == playerColor) {
                    boolean enemyCircle = false;

//                                                  Prehladavanie do STRANY
// ---------------------------------------------------------------------------------------------------------------------------------------

                    // Zlava ku bodu
                    for(int count = 0; count <= x; count++){
                        if(boardCondition[count][y] == 0){
                            for (int k = count + 1; k <= x; k++){
                                if(boardCondition[k][y] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][y] == playerColor  &&  boardCondition[count+1][y] != 8 &&  boardCondition[count+1][y] != 0 &&  boardCondition[count+1][y] != playerColor ){
                                        boardCondition[count][y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }

// ---------------------------------------------------------------------------------------------------------------------------------------

                    // Zprava ku bodu
                    for(int count = boardSize - 1; count >= x; count--){
                        if(boardCondition[count][y] == 0){
                            for (int k = count - 1; k >= x; k--){
                                if(boardCondition[k][y] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][y] == playerColor &&  boardCondition[count-1][y] != 8 &&  boardCondition[count-1][y] != 0 &&  boardCondition[count-1][y] != playerColor){
                                        boardCondition[count][y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                        }
                    }
                    enemyCircle = false;
//                                                  Prehladavanie po VYSKE
// ---------------------------------------------------------------------------------------------------------------------------------------

                    // Zhora ku bodu
                    for(int count = 0; count <= y; count++){
                        if(boardCondition[x][count] == 0){
                            for (int k = count + 1; k <= y; k++){
                                if(boardCondition[x][k] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[x][k] == playerColor &&  boardCondition[x][count+1] != 8 &&  boardCondition[x][count+1] != 0 &&  boardCondition[x][count+1] != playerColor ){
                                        boardCondition[x][count] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }

// ---------------------------------------------------------------------------------------------------------------------------------------

                    // Zdola ku bodu
                    for(int count = boardSize - 1; count >= y; count--){
                        if(boardCondition[x][count] == 0){
                            for (int k = count - 1; k >= y; k--){
                                if(boardCondition[x][k] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[x][k] == playerColor &&  boardCondition[x][count-1] != 8 &&  boardCondition[x][count-1] != 0 &&  boardCondition[x][count-1] != playerColor ){
                                        boardCondition[x][count] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                        }
                    }

//                                                  Prehladavanie DIAGONALNE
// ---------------------------------------------------------------------------------------------------------------------------------------

                    int temp_y = y, temp_x = x;
                    if (y>x){
                        for(int v = 0; v < x; v++){
                            if(temp_x <= 0 || temp_y <= 0)
                                break;
                            temp_y--;
                            temp_x--;
                        }
                    }
                    else if (y<x){
                        for(int v = 0; v < y; v++){
                            if(temp_x <= 0 || temp_y <= 0)
                                break;
                            temp_y--;
                            temp_x--;
                        }
                    }
                    else if (y==x){                 //is always true lebo v tomto pripade je hrac 1 len na diagonale
                        temp_y = 0;
                        temp_x = 0;
                    }

// ------------------------------- Z laveho horneho ku bodu ----------------------------------

                    for(int count = temp_x; count <= x; count++, temp_y++) {
                        if (boardCondition[count][temp_y] == 0) {
                            int j = temp_y ;
                            for(int k = count + 1; k <= x; k++){
                                j++;
                                if(boardCondition[k][j] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][j] == playerColor && boardCondition[count + 1][temp_y + 1] != 8 &&  boardCondition[count + 1][temp_y + 1] != 0 &&  boardCondition[count + 1][temp_y + 1] != playerColor){
                                        boardCondition[count][temp_y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }

// ---------------------------------------------------------------------------------------------------------------------------------------

                    temp_y = y;
                    temp_x = x;

                    if (y>x){
                        for(int v = 0; v < x; v++){
                            if(temp_x >= (boardSize - 1) || temp_y <= 0)
                                break;
                            temp_y--;
                            temp_x++;
                        }
                    }
                    else if (y<x){
                        for(int v = 0; v < y; v++){
                            if(temp_x >= (boardSize - 1) || temp_y <= 0)
                                break;
                            temp_y--;
                            temp_x++;
                        }
                    }
                    else if (y==x){
                        temp_y = 0;
                        temp_x = boardSize - 1;
                    }

// -----------------------   Z praveho horneho rohu ku bodu ---------------------------

                    for(int count = temp_x; count >= x; count--, temp_y++) {
                        if (boardCondition[count][temp_y] == 0) {
                            int j = temp_y ;
                            for(int k = count - 1; k >= x; k--){
                                j++;
                                if(boardCondition[k][j] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][j] == playerColor && boardCondition[count - 1][temp_y + 1] != 8 &&  boardCondition[count - 1][temp_y + 1] != 0 &&  boardCondition[count - 1][temp_y + 1] != playerColor){
                                        boardCondition[count][temp_y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }

// ---------------------------------------------------------------------------------------------------------------------------------------

                    temp_y = y;
                    temp_x = x;

                    if (y>x){
                        for(int v = 0; v < x; v++){
                            if(temp_x >= (boardSize - 1) || temp_y >= (boardSize - 1))
                                break;
                            temp_y++;
                            temp_x--;
                        }
                    }
                    else if (y<x){
                        for(int v = 0; v < y; v++){
                            if(temp_x <= 0 || temp_y >= (boardSize - 1))
                                break;
                            temp_y++;
                            temp_x--;
                        }
                    }
                    else if (y==x){
                        temp_y = boardSize - 1;
                        temp_x = 0;
                    }

// -------------------------- Z laveho dolneho rohu do ku bodu ---------------------------

                    for(int count = temp_x; count <= x; count++, temp_y--) {
                        if (boardCondition[count][temp_y] == 0) {
                            int j = temp_y;
                            for(int k = count + 1; k <= x; k++){
                                j--;
                                if(boardCondition[k][j] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][j] == playerColor && boardCondition[count + 1][temp_y - 1] != 8 &&  boardCondition[count + 1][temp_y - 1] != 0 &&  boardCondition[count + 1][temp_y - 1] != playerColor){
                                        boardCondition[count][temp_y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }

// ---------------------------------------------------------------------------------------------------------------------------------------

                    temp_y = y;
                    temp_x = x;

                    if (y>x){
                        for(int v = 0; v < x; v++){
                            if(temp_x >= (boardSize - 1) || temp_y >= (boardSize - 1))
                                break;
                            temp_y--;
                            temp_x--;
                        }
                    }
                    else if (y<x){
                        for(int v = 0; v < y; v++){
                            if(temp_x >= (boardSize - 1) || temp_y >= (boardSize - 1))
                                break;
                            temp_y--;
                            temp_x--;
                        }
                    }
                    else if (y==x){
                        temp_y = boardSize - 1;
                        temp_x = boardSize - 1;
                    }

//-------------------------------------  Z praveho dolneho rohu ku bodu ---------------------------

                    for(int count = temp_x; count >= x; count--, temp_y--) {
                        if (boardCondition[count][temp_y] == 0) {
                            int j = temp_y;
                            for(int k = count - 1; k >= x; k--){
                                j--;
                                if(boardCondition[k][j] == playerColor){
                                    if(!(enemyCircle))
                                        break;
                                    if (boardCondition[k][j] == playerColor && boardCondition[count - 1][temp_y - 1] != 8 &&  boardCondition[count - 1][temp_y - 1] != 0 &&  boardCondition[count - 1][temp_y - 1] != playerColor){
                                        boardCondition[count][temp_y] = 8;
                                        break;
                                    }
                                }
                                enemyCircle = true;
                            }
                            enemyCircle = false;
                        }
                    }
                }
            }
        }
    }
}
