package main;

import game.Game;
// import grid.Cell;
import grid.Grid;
import grid.Row;
//
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static final Scanner myScanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // New Game
    System.out.println(
      "Welcome to Minesweeper! First, how many rows do you want in your grid?:"
    );
    int numberOfRows = myScanner.nextInt();
    System.out.println(
      String.format(
        "Okay, %d rows it is! How long do you want each row to be?",
        numberOfRows
      )
    );
    int lengthOfRows = myScanner.nextInt();
    System.out.println(
      String.format(
        "Okay, lets make a grid with %d rows that are %d cells long. Lastly, how many mines would you like to randomly plant?",
        numberOfRows,
        lengthOfRows
      )
    );
    int numberOfMines = myScanner.nextInt();
    System.out.println(
      String.format("%d Mines Planted, now lets play!", numberOfMines)
    );
    Grid grid = new Grid(
      numberOfRows,
      lengthOfRows,
      new ArrayList<Row>(numberOfRows)
    );
    System.out.println("Starting Grid: \n");
    System.out.println(grid.createGrid());
    System.out.println("\n");
    grid.plantMines(numberOfMines);
    grid.determineTypes();
    // myScanner.close();
    while (!Game.isGameWon) {
      System.out.println(
        "Please enter the coordinate of a cell you'd like to check!"
      );
      int[] returnedCoords = Game.nextTurn(numberOfRows, lengthOfRows);
      System.out.println(grid.revealCell(returnedCoords[0], returnedCoords[1]));
      System.out.println("");
    }
    myScanner.close();
  }

  //=======================================================================
  //=======================================================================
  //=======================================================================

  public static void oldStartup(Grid grid) {}
}
