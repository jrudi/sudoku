public class SudokuState {
    private int[][] board = new int[9][9];

    public void getFirstBoard(){
        this.board = new int[][]{
                {8,0,0,0,0,0,0,0,0},
                {0,0,3,6,0,0,0,0,0},
                {0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
                {0,0,0,0,4,5,7,0,0},
                {0,0,0,1,0,0,0,3,0},
                {0,0,1,0,0,0,0,6,8},
                {0,0,8,5,0,0,0,1,0},
                {0,9,0,0,0,0,4,0,0}
        };
    }

    public void getSecondBoard(){
        this.board = new int[][]{
                {0,7,0,0,0,6,0,0,0},
                {9,0,0,0,0,0,0,4,1},
                {0,0,8,0,0,9,0,5,0},
                {0,9,0,0,0,7,0,0,2},
                {0,0,3,0,0,0,8,0,0},
                {4,0,0,8,0,0,0,1,0},
                {0,8,0,3,0,0,9,0,0},
                {1,6,0,0,0,0,0,0,7},
                {0,0,0,5,0,0,0,8,0}
        };
    }

    public void getThirdBoard(){
        this.board = new int[][]{
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };
    }
    public boolean isMove(int x, int y, int n){
        for(int i=0;i<9;i++){
            if (board[x][i] == n || board[i][y]==n){
                return false;
            }
            int x_st = (x/3)*3;
            int y_st = (y/3)*3;

            for(int j=x_st;j<x_st+3;j++){
                for(int k=y_st;k<y_st+3;k++){
                    if (board[j][k] == n) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


}

