package game;

import grid.Cell;
import grid.Grid;
import grid.Row;
import java.util.ArrayList;
import main.Main;

public class Game {

  public static int numberOfRows;
  public static int lengthOfRows;
  public static int numberOfMines;

  public static Grid grid;

  public static boolean isGameOver = false;
  public static boolean isNewGameRequired = true;

  public static void turnCommand() throws InterruptedException {
    String userInput = null;
    System.out.println(
      "Available Commands:\n\n - reveal(Select a cell by coordinate to be revealed)\n - flag (Select a cell by coordinate to be flagged)\n - removeFlag (Remove a flag from a cell that contains a flag)\n - revealBoard (Ends the game and reveals the entire board)\n - reset (Starts a new game with a new grid)"
    );
    while (userInput == null) {
      userInput = Main.myScanner.next();
      switch (userInput.trim().toLowerCase()) {
        case "reveal":
          nextTurnReveal();
          break;
        case "flag":
          nextTurnFlag();
          break;
        case "removeflag":
          nextTurnRemoveFlag();
          break;
        case "revealboard":
          nextTurnRevealBoard();
          break;
        case "reset":
          checkNewGame();
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
    checkVictory();
  }

  public static void nextTurnReveal() {
    int[] inputCoords = { 0, 0 };
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt() - 1;
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt() - 1;
      if (Y >= numberOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    System.out.print("   ");
    for (int i = 0; i <= lengthOfRows - 1; i++) {
      System.out.print(" " + i + " ");
    }
    System.out.println("");
    grid.revealCell(inputCoords[0], inputCoords[1]);
    grid.printGrid();
    checkGameOver();
  }

  public static void nextTurnFlag() {
    int[] inputCoords = { 0, 0 };
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt() - 1;
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt() - 1;
      if (Y >= numberOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    grid.placeFlag(Y, X);
  }

  public static void nextTurnRemoveFlag() {
    int[] inputCoords = { 0, 0 };
    Integer X = null;
    while (X == null) {
      System.out.println("Please enter an X coordinate");
      X = Main.myScanner.nextInt() - 1;
      if (X >= lengthOfRows) {
        X = null;
        System.out.println("Invalid X coordinate, please try again");
      } else {
        inputCoords[1] = X;
      }
    }
    Integer Y = null;
    while (Y == null) {
      System.out.println("Please enter a Y coordinate");
      Y = Main.myScanner.nextInt() - 1;
      if (Y >= numberOfRows) {
        Y = null;
        System.out.println("Invalid Y coordinate, please try again");
      } else {
        inputCoords[0] = Y;
      }
    }
    grid.removeFlag(Y, X);
  }

  public static void nextTurnRevealBoard() {
    Game.isGameOver = true;
    grid.revealBoard();
    grid.updateGrid();
    grid.printGrid();
    checkGameOver();
  }

  public static void checkGameOver() {
    if (isGameOver) {
      System.out.println("\nGAME OVER");
      checkNewGame();
    }
  }

  public static void checkNewGame() {
    String userInput = null;
    System.out.print("\nWould you like to play another game? (Y/N):");
    while (userInput == null || userInput == "y" || userInput == "n") {
      userInput = Main.myScanner.next();
      switch (userInput.trim().toLowerCase()) {
        case "y":
          isGameOver = true;
          isNewGameRequired = true;
          break;
        case "n":
          isGameOver = true;
          isNewGameRequired = false;
          break;
        case "":
          break;
        default:
          grid.updateGrid();
          grid.printGrid();
          System.out.println("\nInvalid Response, please try again (Y/N):\n");
          userInput = null;
          break;
      }
    }
  }

  public static void startNewGame() {
    Main.cleanScreen();
    System.out.println(
      "Welcome to Minesweeper! First, how many rows do you want in your grid?:"
    );
    numberOfRows = Main.myScanner.nextInt(); // Can this be Public?
    Main.cleanScreen();
    System.out.println(
      String.format(
        "Okay, %d rows it is! How long do you want each row to be?",
        numberOfRows
      )
    );
    lengthOfRows = Main.myScanner.nextInt();
    Main.cleanScreen();
    System.out.println(
      String.format(
        "Okay, lets make a grid with %d rows that are %d cells long. Lastly, how many mines would you like to randomly plant?",
        numberOfRows,
        lengthOfRows
      )
    );
    numberOfMines = Main.myScanner.nextInt();
    Main.cleanScreen();
    grid =
      new Grid(numberOfRows, lengthOfRows, new ArrayList<Row>(numberOfRows));
    grid.createGrid();
    grid.printGrid();
    System.out.println("\nStarting Grid: \n");
    grid.plantMines(numberOfMines);
    grid.determineTypes();
  }

  public static void checkVictory() {
    ArrayList<Cell> unrevealedCells = new ArrayList<>();
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < grid.getRows().get(i).getRow().size(); j++) {
        if (
          !grid.getRows().get(i).getRow().get(j).getIsRevealed()
        ) unrevealedCells.add(grid.getRows().get(i).getRow().get(j));
      }
    }
    if (unrevealedCells.size() <= numberOfMines) {
      System.out.print("\nWinner Winner Chicken Dinner\n");
      checkNewGame();
    }
  }
}
