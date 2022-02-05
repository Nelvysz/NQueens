import java.util.*;

class Nqueen {
  private static final int startRow = 0;
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int N = 0, row = 0, col = 0;
    char placeQ = 'a', Continue = 'b';
    while (N < 4 || N > 8) {
      try {
        System.out.println("Enter N (atleast 4) =");
        N = input.nextInt();
      } catch (InputMismatchException e) {
        continue;
      }
    }

    while (true) {
      System.out.println("Place first queen manually (y for yes) ?");
      placeQ = input.next().charAt(0);
      Character.toLowerCase(placeQ);
      Board board = new Board(N);
      if (placeQ == 'y') {
        while (row <= 0 || row > N) {
          try {
            System.out.println("Enter row of first queen =");
            row = input.nextInt();
          } catch (InputMismatchException e) {
            row = 0;
            continue;
          }
        }
        while (col <= 0 || col > N) {
          try {
            System.out.println("Enter col of first queen =");
            col = input.nextInt();
          } catch (InputMismatchException e) {
            col = 0;
            continue;
          }
        }
        board.setQ(row-1, col-1);
        if (board.CheckBoard(0) == 0) System.out.println(" no solution");
        else board.printBoard();
      } else if (placeQ == 'n') {
        board.CheckBoard(row);
        board.printBoard();
      } else {
        continue;
      }
      System.out.println("Continue (y for yes) ?");
      Continue = input.next().charAt(0);
      Character.toLowerCase(Continue);
      row = 0;
      col = 0;
      if (Continue == 'y')
        continue;
      else {
        break;
      }
    }
  }
}

class Board {
    private char board[][];
    private int size;
  
    public Board(int s) {
      board = new char[s][s];
      size = s;
  
      for (int i = 0; i < s; i++) {
        for (int j = 0; j < s; j++) {
          board[i][j] = '=';
        }
      }
    }
  
    public void printBoard() {
      for (int i = 0; i <= size; i++) {
  
        for (int j = 0; j < size; j++) {
          if (i == 0) {
            if (j == 0) {
              System.out.print(" ");
            }
            System.out.printf(" %d", j + 1);
          } else {
            if (j == 0) {
              System.out.printf("%d", i);
            }
            System.out.printf(" %c", board[i - 1][j]);
          }
        }
  
        System.out.println();
      }
    }
  
    private boolean foundQ(int r,int c){
      //check row
      int i = 0;
      for(i = 0;i<size;i++) {  if(board[r][i] == 'Q') return true; } //check row
      for(i = 0;i<size;i++) {  if(board[i][c] == 'Q') return true; } //check col
      for(int ld_row = r-1,ld_col = c-1; ld_row>=0 && ld_col>=0;ld_row--,ld_col--) { //check upper left diagonal
        if(board[ld_row][ld_col] == 'Q') return true; }
      for(int ld_row = r+1,ld_col = c+1; ld_row<size && ld_col<size;ld_row++,ld_col++) { //check lower left diagonal
        if(board[ld_row][ld_col] == 'Q') return true; }
      for(int rd_row = r+1,rd_col = c-1;rd_row<size && rd_col>=0;rd_row++,rd_col--)  { //check lower right diagonal
        if(board[rd_row][rd_col] == 'Q') return true; }
      for(int rd_row = r-1,rd_col = c+1; rd_row>=0 && rd_col<size;rd_row--, rd_col++){ //check upper right diagonal
        if(board[rd_row][rd_col] == 'Q') return true; }
  
      return false;
    }
  
    public int CheckBoard(int row) {
      if(row >=size) return 1;
      for(int col =0;col<size;col++){ //check each col
          if(!foundQ(row, col)){
            this.setQ(row, col);
            if(row >=size) return 1;
            
            if(CheckBoard(row+1) == 1) return 1;
          }
          else if( board[row][col] == 'Q') {if(CheckBoard(row+1) == 1) return 1; else return 0;}
          if(col == size-1) break;
          this.renoveQ(row, col);
      }
      return 0;
    }
  
    public void setQ(int r, int c) {
      board[r][c] = 'Q';
    }
  
    public void renoveQ(int r,int c){
      board[r][c] = '=';
    }
    public ArrayDeque<Integer> getQArray(){
      ArrayDeque<Integer> QA = new ArrayDeque<>();
      for(int i= 0;i<size;i++){
        for(int j = 0;j<size;j++){
          if(board[i][j] == 'Q') {
            QA.addLast(j);
            break;
          }
        }
      }
      return QA;
    }
  }