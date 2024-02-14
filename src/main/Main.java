package main;

import game.Game;
// import grid.Cell;
import grid.Grid;
import grid.Row;
//
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static final Scanner myScanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    // New Game
    cleanScreen();
    System.out.println(
      "Welcome to Minesweeper! First, how many rows do you want in your grid?:"
    );
    int numberOfRows = myScanner.nextInt();
    cleanScreen();
    System.out.println(
      String.format(
        "Okay, %d rows it is! How long do you want each row to be?",
        numberOfRows
      )
    );
    int lengthOfRows = myScanner.nextInt();
    cleanScreen();
    System.out.println(
      String.format(
        "Okay, lets make a grid with %d rows that are %d cells long. Lastly, how many mines would you like to randomly plant?",
        numberOfRows,
        lengthOfRows
      )
    );
    int numberOfMines = myScanner.nextInt();
    cleanScreen();
    Grid grid = new Grid(
      numberOfRows,
      lengthOfRows,
      new ArrayList<Row>(numberOfRows)
    );
    grid.createGrid();
    grid.printGrid();
    System.out.println("\nStarting Grid: \n");
    grid.plantMines(numberOfMines);
    grid.determineTypes();
    while (!Game.isGameOver) {
      System.out.println("Please enter a command!\n");
      Game.turnCommand(grid, numberOfRows, lengthOfRows);
      System.out.println("");
      Game.checkGameOver();
    }
    myScanner.close();
  }

  //=======================================================================
  //=======================================================================
  //=======================================================================

  public static void cleanScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void oldStartup(Grid grid) {}
}
