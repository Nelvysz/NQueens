import java.util.*;

class Main {
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

    Board board = new Board(N);
    board.printBoard();
    while (true) {
      System.out.println("Place first queen manually (y for yes) ?");
      placeQ = input.next().charAt(0);
      Character.toLowerCase(placeQ);
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

        board.CheckBoard(row,col);
        board.printBoard();
      } else if (placeQ == 'n') {
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

  public boolean CheckBoard(int r, int c) {
    int i = 0;
    boolean canPlace = true;
    //row check row
    for(i = 0;i<size;i++) { 
      if(board[r-1][i] == 'Q') canPlace = false;
    }
    //col update
    for(i = 0;i<size;i++){
      if(board[i][c-1] == 'Q') canPlace = false;
    }
    //left diagonal update
    int ld_row = r-2, ld_col = c-2; // upper update
    while(ld_row >= 0 && ld_col >= 0){
      if(board[ld_row][ld_col] == 'Q') canPlace = false;
      ld_row--; ld_col--;
    }
    ld_row = r; ld_col = c; // lower update
    while(ld_row < size && ld_col < size){
      if(board[ld_row][ld_col] == 'Q') canPlace = false;
      ld_row++; ld_col++;
    }

    //right diaginal update
    int rd_row = r, rd_col = c-2; //lower update
    while(rd_row<size && rd_col>=0){
      if(board[rd_row][rd_col] == 'Q') canPlace = false;
      rd_row++; rd_col--;
    }
    rd_row = r-2; rd_col=c;
    while(rd_row>=0 && rd_col<size){
      if(board[rd_row][rd_col] == 'Q') canPlace = false;
      rd_row--; rd_col++;
    }

    if(canPlace) return true;
    else return false;
  }

  public void setQ(int r, int c) {
    board[r][c] = 'Q';
  }

  public void setC(int r,int c){
    board[r ][c] = 'C';
  }

}
