package game;

import grid.Grid;
import main.Main;

public class Game {

  public static boolean isGameWon = false;

  public static int[] nextTurn(int amountOfRows, int lengthOfRows) {
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
    System.out.println("");
    return inputCoords;
  }
}
