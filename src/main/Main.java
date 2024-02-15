package main;

import game.Game;
import java.util.Scanner;

public class Main {

  public static final Scanner myScanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {
    while (Game.isNewGameRequired) {
      Game.startNewGame();
      Game.isGameOver = false;
      while (!Game.isGameOver) {
        System.out.println("Please enter a command!\n");
        Game.turnCommand();
        System.out.println("");
      }
    }
    myScanner.close();
  }

  public static void cleanScreen() {
    String os = System.getProperty("os.name").toLowerCase();
    try {
      ProcessBuilder processBuilder;

      if (os.contains("win")) {
        //Windows
        processBuilder = new ProcessBuilder("cmd", "/c", "cls");
      } else {
        //Mac
        processBuilder = new ProcessBuilder("clear");
      }

      Process process = processBuilder.inheritIO().start();
      process.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  // public static void cleanScreen() {
  //   System.out.print("\033[H\033[2J");
  //   System.out.flush();
  // }
}
