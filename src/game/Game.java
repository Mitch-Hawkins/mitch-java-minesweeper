package game;

import grid.Grid;
import main.Main;

public class Game {

  public static boolean isGameOver = false;

  public static void turnCommand(Grid grid, int amountOfRows, int lengthOfRows)
    throws InterruptedException {
    String userInput = null;
    System.out.println(
      "Available Commands:\n - reveal(Select a cell by coordinate to be revealed)\n - flag (Select a cell by coordinate to be flagged)\n - removeFlag (Remove a flag from a cell that contains a flag)\n - revealBoard (Ends the game and reveals the entire board)\n - reset (Starts a new game with a new grid)"
    );
    while (userInput == null) {
      userInput = Main.myScanner.next();
      switch (userInput.trim().toLowerCase()) {
        case "reveal":
          nextTurnReveal(grid, amountOfRows, lengthOfRows);
          break;
        case "flag":
          nextTurnFlag(grid, amountOfRows, lengthOfRows);
          break;
        case "removeflag":
          nextTurnRemoveFlag(grid, amountOfRows, lengthOfRows);
          break;
        case "revealboard":
          nextTurnRevealBoard(grid, amountOfRows, lengthOfRows);
          break;
        case "":
          break;
        default:
          grid.updateGrid();
          grid.printGrid();
          System.out.println("\nInvalid Command, please try again.\n");
          break;
      }
    }
  }

  public static void nextTurnReveal(
    Grid grid,
    int amountOfRows,
    int lengthOfRows
  ) {
    int[] inputCoords = { 0, 0 };
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt();
      if (Y >= amountOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt();
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    System.out.print("   ");
    for (int i = 0; i <= lengthOfRows - 1; i++) {
      System.out.print(" " + i + " ");
    }
    System.out.println("");
    grid.revealCell(inputCoords[0], inputCoords[1]);
    grid.printGrid();
  }

  public static void nextTurnFlag(
    Grid grid,
    int amountOfRows,
    int lengthOfRows
  ) {
    int[] inputCoords = { 0, 0 };
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt();
      if (Y >= amountOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt();
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    grid.placeFlag(Y, X);
  }

  public static void nextTurnRemoveFlag(
    Grid grid,
    int amountOfRows,
    int lengthOfRows
  ) {
    int[] inputCoords = { 0, 0 };
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt();
      if (Y >= amountOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt();
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    grid.removeFlag(Y, X);
  }

  public static void nextTurnRevealBoard(
    Grid grid,
    int amountOfRows,
    int lengthOfRows
  ) {
    Game.isGameOver = true;
    grid.revealBoard();
    grid.updateGrid();
    grid.printGrid();
  }

  public static void checkGameOver() {
    if (isGameOver) {
      // System.out.println("Oh No! That cell was a mine! Boom!\n");
      System.out.println("GAME OVER\n");
    }
  }
}
