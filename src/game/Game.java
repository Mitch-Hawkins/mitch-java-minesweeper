package game;

import grid.Cell;
import grid.Grid;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
      if (X >= lengthOfRows || X < numberOfRows) {
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
      if (Y >= numberOfRows || Y < numberOfRows) {
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
      if (X >= lengthOfRows || X < lengthOfRows) {
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
      if (Y >= numberOfRows || Y < numberOfRows) {
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
      if (X >= lengthOfRows || X < lengthOfRows) {
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
      if (Y >= numberOfRows || Y < numberOfRows) {
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
    Integer tmp1 = null;

    System.out.println(
      "Welcome to Minesweeper! First, how many rows do you want in your grid? (2-30):"
    );

    while (tmp1 == null) {
      try {
        if (Main.myScanner.hasNextInt()) {
          tmp1 = Main.myScanner.nextInt();

          if (tmp1 <= 1 || tmp1 > 30) {
            System.out.println(
              "\nThat number is not within the limits! Please try again with a number between 2-30:"
            );
            tmp1 = null;
          } else {
            numberOfRows = tmp1;
          }
        } else {
          System.out.println("\nPlease enter a valid integer between 2-30:");
          Main.myScanner.next();
        }
      } catch (InputMismatchException e) {
        System.out.println("\nPlease enter a valid integer between 2-30:");
        Main.myScanner.next();
      }
    }
    Main.cleanScreen();
    tmp1 = null;
    System.out.println(
      String.format(
        "Okay, %d rows it is! How long do you want each row to be?",
        numberOfRows
      )
    );
    while (tmp1 == null) {
      try {
        if (Main.myScanner.hasNextInt()) {
          tmp1 = Main.myScanner.nextInt();

          if (tmp1 <= 1 || tmp1 > 30) {
            System.out.println(
              "\nThat number is not within the limits! Please try again with a number between 2-30:"
            );
            tmp1 = null;
          } else {
            lengthOfRows = tmp1;
          }
        } else {
          System.out.println("\nPlease enter a valid integer between 2-30:");
          Main.myScanner.next();
        }
      } catch (InputMismatchException e) {
        System.out.println("\nPlease enter a valid integer between 2-30:");
        Main.myScanner.next();
      }
    }
    Main.cleanScreen();
    tmp1 = null;
    System.out.println(
      String.format(
        "Okay, lets make a grid with %d rows that are %d cells long. Lastly, how many mines would you like to randomly plant?",
        numberOfRows,
        lengthOfRows
      )
    );
    while (tmp1 == null) {
      try {
        if (Main.myScanner.hasNextInt()) {
          tmp1 = Main.myScanner.nextInt();

          if (tmp1 <= 0 || tmp1 >= (numberOfRows * lengthOfRows)) {
            System.out.println(
              String.format(
                "\nThat number is not within the limits! Please try again with a number between 1-%d:",
                (numberOfRows * lengthOfRows) - 1
              )
            );
            tmp1 = null;
          } else {
            numberOfMines = tmp1;
          }
        } else {
          System.out.println(
            "\nPlease enter a valid integer between 1 and " +
            (numberOfRows * lengthOfRows - 1) +
            ":"
          );
          Main.myScanner.next();
        }
      } catch (InputMismatchException e) {
        System.out.println(
          "\nPlease enter a valid integer between 1 and " +
          (numberOfRows * lengthOfRows - 1) +
          ":"
        );
        Main.myScanner.next();
      }
    }
    Main.cleanScreen();
    grid = new Grid(numberOfRows, lengthOfRows, new ArrayList<>(numberOfRows));
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
