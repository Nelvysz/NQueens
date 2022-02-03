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

        board.setQ(row, col);
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

  public void CheckBoard(int row, int col) {
    // System.out.println("Hello world");
  }

  public void setQ(int r, int c) {
    board[r - 1][c - 1] = 'Q';
  }
}

class Board2{
  private Node
}

class Node {
  private char Q = "=";
  private boolean Viable = True;
}