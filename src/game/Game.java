package game;

import grid.Cell;
import grid.Grid;
import grid.Row;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.Main;

public class Game {

  public static int numberOfRows;
  public static int lengthOfRows;
  public static int numberOfMines;

  public static Grid grid;

  public static boolean isGameOver = false;
  public static boolean isNewGameRequired = true;

  public static final Scanner myScanner = new Scanner(System.in);

  public static void turnCommand() throws InterruptedException {
    String userInput = null;
    System.out.println(
      "Available Commands:\n\n - reveal(Select a cell by coordinate to be revealed)\n - flag (Select a cell by coordinate to be flagged)\n - removeFlag (Remove a flag from a cell that contains a flag)\n - revealBoard (Ends the game and reveals the entire board)\n - reset (Starts a new game with a new grid)"
    );
    while (userInput == null) {
      userInput = myScanner.next();
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
    int[] inputCoords = getCoordinates(
      "Please enter an X coordinate",
      "Please enter a Y coordinate"
    );
    grid.revealCell(inputCoords[0], inputCoords[1]);
    grid.printGrid();
    checkGameOver();
  }

  public static void nextTurnFlag() {
    int[] inputCoords = getCoordinates(
      "Please enter an X coordinate",
      "Please enter a Y coordinate"
    );
    grid.placeFlag(inputCoords[0], inputCoords[1]);
  }

  public static void nextTurnRemoveFlag() {
    int[] inputCoords = getCoordinates(
      "Please enter an X coordinate",
      "Please enter a Y coordinate"
    );
    grid.removeFlag(inputCoords[0], inputCoords[1]);
  }

  private static int[] getCoordinates(String xPrompt, String yPrompt) {
    int[] inputCoords = { 0, 0 };

    Integer X = null;
    while (X == null) {
      try {
        System.out.println(xPrompt);
        X = myScanner.nextInt() - 1;

        if (X >= lengthOfRows || X < 0) {
          X = null;
          System.out.println("Invalid X coordinate, please try again");
        } else {
          inputCoords[1] = X;
        }
      } catch (InputMismatchException e) {
        myScanner.nextLine();
        System.out.println("Invalid input! Please enter a valid integer.");
      }
    }

    Integer Y = null;
    while (Y == null) {
      try {
        System.out.println(yPrompt);
        Y = myScanner.nextInt() - 1;

        if (Y >= numberOfRows || Y < 0) {
          Y = null;
          System.out.println("Invalid Y coordinate, please try again");
        } else {
          inputCoords[0] = Y;
        }
      } catch (InputMismatchException e) {
        myScanner.nextLine();
        System.out.println("Invalid input! Please enter a valid integer.");
      }
    }

    return inputCoords;
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
      System.out.println("");

      System.out.println(
        "  ____   ____  ___ ___    ___       ___   __ __    ___  ____   __ "
      );
      System.out.println(
        " /    | /    ||   |   |  /  _]     /   \\ |  |  |  /  _]|    \\ |  |"
      );
      System.out.println(
        "|   __||  o  || _   _ | /  [_     |     ||  |  | /  [_ |  D  )|  |"
      );
      System.out.println(
        "|  |  ||     ||  \\_/  ||    _]    |  O  ||  |  ||    _]|    / |__|"
      );
      System.out.println(
        "|  |_ ||  _  ||   |   ||   [_     |     ||  :  ||   [_ |    \\  __ "
      );
      System.out.println(
        "|     ||  |  ||   |   ||     |    |     | \\   / |     ||  .  \\|  |"
      );
      System.out.println(
        "|___,_||__|__||___|___||_____|     \\___/   \\_/  |_____||__|\\_||__|"
      );
      System.out.println(
        "                                                                  "
      );
      checkNewGame();
    }
  }

  public static void checkNewGame() {
    String userInput = null;
    System.out.print("\nWould you like to play another game? (Y/N):");
    while (userInput == null || userInput == "y" || userInput == "n") {
      userInput = myScanner.next();
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
    printWelcome();
    numberOfRows =
      getInputWithinRange(
        "Welcome to Minesweeper! First, how many rows do you want in your grid? (2-16):",
        2,
        16
      );
    Main.cleanScreen();
    lengthOfRows =
      getInputWithinRange(
        String.format(
          "Okay, %d rows it is! How long do you want each row to be? (Between 2-30):",
          numberOfRows
        ),
        2,
        30
      );
    Main.cleanScreen();
    numberOfMines =
      getInputWithinRange(
        String.format(
          "Okay, lets make a grid with %d rows that are %d cells long. Lastly, how many mines would you like to randomly plant?",
          numberOfRows,
          lengthOfRows
        ),
        1,
        numberOfRows * lengthOfRows - 1
      );

    Main.cleanScreen();
    grid =
      new Grid(numberOfRows, lengthOfRows, new ArrayList<Row>(numberOfRows));
    grid.createGrid();
    grid.printGrid();
    System.out.println("\nStarting Grid: \n");
    grid.plantMines(numberOfMines);
    grid.determineTypes();
  }

  private static int getInputWithinRange(String prompt, int min, int max) {
    Integer input = null;

    System.out.println(prompt);

    while (input == null) {
      try {
        if (myScanner.hasNextInt()) {
          input = myScanner.nextInt();

          if (input < min || input > max) {
            System.out.println(
              String.format(
                "\nThat number is not within the limits! Please try again with a number between %d-%d:",
                min,
                max
              )
            );
            input = null;
          }
        } else {
          System.out.println(
            String.format(
              "\nPlease enter a valid integer between %d-%d:",
              min,
              max
            )
          );
          myScanner.next();
        }
      } catch (InputMismatchException e) {
        System.out.println(
          String.format(
            "\nPlease enter a valid integer between %d-%d:",
            min,
            max
          )
        );
        myScanner.next();
      }
    }

    return input;
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
      System.out.println("");

      System.out.println(" __    __  ____  ____   ____     ___  ____   __ ");
      System.out.println("|  |__|  ||    ||    \\ |    \\   /  _]|    \\ |  |");
      System.out.println("|  |  |  | |  | |  _  ||  _  | /  [_ |  D  )|  |");
      System.out.println("|  |  |  | |  | |  |  ||  |  ||    _]|    / |__|");
      System.out.println("|  `  '  | |  | |  |  ||  |  ||   [_ |    \\  __ ");
      System.out.println(" \\      /  |  | |  |  ||  |  ||     ||  .  \\|  |");
      System.out.println("  \\_/\\_/  |____||__|__||__|__||_____||__|\\_||__|");
      System.out.println("                                                ");
      checkNewGame();
    }
  }

  public static void printWelcome() {
    System.out.println(
      " ___ ___  ____  ____     ___  _____ __    __    ___    ___  ____   ___  ____  "
    );
    System.out.println(
      "|   |   ||    ||    \\   /  _]/ ___/|  |__|  |  /  _]  /  _]|    \\ /  _]|    \\ "
    );
    System.out.println(
      "| _   _ | |  | |  _  | /  [_(   \\_ |  |  |  | /  [_  /  [_ |  o  )  [_ |  D  )"
    );
    System.out.println(
      "|  \\_/  | |  | |  |  ||    _]\\__  ||  |  |  ||    _]|    _]|   _/    _]|    / "
    );
    System.out.println(
      "|   |   | |  | |  |  ||   [_ /  \\ ||  `  '  ||   [_ |   [_ |  | |   [_ |    \\ "
    );
    System.out.println(
      "|   |   | |  | |  |  ||     |\\    | \\      / |     ||     ||  | |     ||  .  \\"
    );
    System.out.println(
      "|___|___||____||__|__||_____| \\___|  \\_/\\_/  |_____||_____||__| |_____||__|\\_|"
    );
    System.out.println(
      "                                                                              "
    );
  }
}
