package grid;

import game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import main.Main;

public class Grid {

  private int amountOfRows;
  private int lengthOfRows;
  private ArrayList<Row> rows;

  public Grid(int amountOfRows, int lengthOfRows, ArrayList<Row> rows) {
    this.amountOfRows = amountOfRows;
    this.lengthOfRows = lengthOfRows;
    this.rows = rows;
  }

  public ArrayList<Row> getRows() {
    return this.rows;
  }

  public void setRows(ArrayList<Row> rows) {
    this.rows = rows;
  }

  public static int[] generateRandomCoordinate(int maxY, int maxX) {
    Random random = new Random();
    int y = random.nextInt(maxY);
    int x = random.nextInt(maxX);
    return new int[] { y, x };
  }

  public void plantMines(int amountOfMines) {
    ArrayList<int[]> coordinatesOfMines = new ArrayList<>();
    for (int i = 0; i <= amountOfMines - 1; i++) {
      int[] randomCoord = generateRandomCoordinate(
        this.amountOfRows,
        this.lengthOfRows
      );
      boolean containsDuplicate = false;
      for (int[] existingCoord : coordinatesOfMines) {
        if (Arrays.equals(existingCoord, randomCoord)) {
          containsDuplicate = true;
          break;
        }
      }
      if (containsDuplicate) {
        i--;
      } else {
        coordinatesOfMines.add(randomCoord);
      }
    }
    for (int i = 0; i <= coordinatesOfMines.size() - 1; i++) {
      rows
        .get(coordinatesOfMines.get(i)[0])
        .getRow()
        .get(coordinatesOfMines.get(i)[1])
        .setType(10);
    }
  }

  public void printGrid() {
    Main.cleanScreen();
    System.out.print("   ");
    for (int i = 0; i <= lengthOfRows - 1; i++) {
      if (i >= 9) {
        System.out.print("" + (i + 1) + " ");
      } else {
        System.out.print(" " + (i + 1) + " ");
      }
    }
    System.out.println("");
    ArrayList<String> tempString = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      if (i >= 9) {
        tempString.add(String.format("%d %s", (i + 1), rows.get(i).printRow()));
      } else {
        tempString.add(
          String.format(" %d %s", (i + 1), rows.get(i).printRow())
        );
      }
    }
    System.out.println(String.join("\n", tempString));
  }

  public void createGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      Row row = new Row(
        new ArrayList<Cell>(this.lengthOfRows),
        this.lengthOfRows
      );
      row.createRow();
      rows.add(row);
    }
  }

  public void updateGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      for (int j = 0; j < rows.get(i).getRow().size(); j++) {
        rows.get(i).getRow().set(j, rows.get(i).getRow().get(j));
      }
    }
  }

  public int getCellTypeByCoordinate(int Y, int X) {
    if (
      Y >= 0 &&
      Y <= rows.size() - 1 &&
      X <= rows.get(Y).getRow().size() - 1 &&
      X >= 0
    ) {
      return rows.get(Y).getRow().get(X).getType();
    } else return -1;
  }

  public String getCellAsciiByCoordinate(int Y, int X) {
    if (
      Y >= 0 &&
      Y <= rows.size() - 1 &&
      X <= rows.get(Y).getRow().size() - 1 &&
      X >= 0
    ) {
      return rows.get(Y).getRow().get(X).getAscii();
    } else return "N/A";
  }

  public void determineTypes() {
    for (int x = 0; x <= rows.get(0).getRow().size() - 1; x++) {
      for (int y = 0; y <= rows.size() - 1; y++) {
        Cell cell = rows.get(y).getRow().get(x);
        if (cell.getType() != 10) {
          int cellNumberCount = 0;
          ArrayList<Integer> cellTypes = getAdjacentCellsType(y, x);
          for (int i = 0; i <= cellTypes.size() - 1; i++) {
            if (cellTypes.get(i) == 10) {
              cellNumberCount++;
            }
            cell.setType(cellNumberCount);
          }
        }
      }
    }
  }

  public void revealBoard() {
    for (int x = 0; x <= rows.get(0).getRow().size() - 1; x++) {
      for (int y = 0; y <= rows.size() - 1; y++) {
        Cell cell = rows.get(y).getRow().get(x);
        if (cell.getType() <= 8) {
          cell.setAscii(
            String.format(
              "\u001B[3%dm[%d]\u001B[0m",
              cell.getType(),
              cell.getType()
            )
          );
        } else if (cell.getType() == 10) {
          cell.setAscii("[*]");
        }
      }
    }
  }

  public void revealCell(int Y, int X) {
    Cell cell = rows.get(Y).getRow().get(X);
    if (cell.getIsRevealed()) {
      updateGrid();
    } else {
      cell.setIsRevealed(true);

      if (cell.getType() == 0) {
        cell.setAscii("\u001B[30m[0]\u001B[0m");
        try {
          revealAdjacentCells(Y, X);
        } catch (Exception e) {
          System.out.println("Error");
        }
      } else if (cell.getType() >= 1 && cell.getType() <= 8) {
        cell.setAscii(
          String.format(
            "\u001B[3%dm[%d]\u001B[0m",
            cell.getType(),
            cell.getType()
          )
        );
      } else if (cell.getType() == 10) {
        Game.isGameOver = true;
        revealBoard();
      }
      updateGrid();
    }
  }

  private void revealAdjacentCells(int Y, int X) {
    ArrayList<int[]> adjCells = getAdjacentCellsCoordinates(Y, X);
    ArrayList<Integer> adjTypes = getAdjacentCellsType(Y, X);

    if (!adjTypes.contains(10)) {
      for (int i = 1; i < adjCells.size(); i++) {
        revealCell(adjCells.get(i)[0], adjCells.get(i)[1]);
      }
    }
  }

  public void placeFlag(int Y, int X) {
    Cell cell = rows.get(Y).getRow().get(X);
    if (cell.getIsRevealed()) {
      updateGrid();
      printGrid();
      System.out.print("\nThat Cell is already Revealed!\n");
    } else {
      cell.setAscii("\u001B[41m[!]\u001B[0m");
      updateGrid();
      printGrid();
    }
  }

  public void removeFlag(int Y, int X) {
    Cell cell = rows.get(Y).getRow().get(X);
    if (cell.getAscii() != "\u001B[41m[!]\u001B[0m") {
      updateGrid();
      printGrid();
      System.out.print("\nThat Cell has no flag to remove!\n");
    } else {
      cell.setAscii("[ ]");
      updateGrid();
      printGrid();
    }
  }

  public int limitY(int Y) {
    if (Y < 0) {
      return 0;
    } else if (Y > amountOfRows - 1) {
      return amountOfRows - 1;
    } else {
      return Y;
    }
  }

  public int limitX(int X) {
    if (X < 0) {
      return 0;
    } else if (X > lengthOfRows - 1) {
      return lengthOfRows - 1;
    } else {
      return X;
    }
  }

  public ArrayList<int[]> getAdjacentCellsCoordinates(int Y, int X) {
    ArrayList<int[]> cellCoordinates = new ArrayList<>();

    //===
    cellCoordinates.add(new int[] { limitY(Y), limitX(X) });
    cellCoordinates.add(new int[] { limitY(Y - 1), limitX(X) });
    cellCoordinates.add(new int[] { limitY(Y), limitX(X + 1) });
    cellCoordinates.add(new int[] { limitY(Y + 1), limitX(X) });
    cellCoordinates.add(new int[] { limitY(Y), limitX(X - 1) });
    cellCoordinates.add(new int[] { limitY(Y - 1), limitX(X + 1) });
    cellCoordinates.add(new int[] { limitY(Y + 1), limitX(X + 1) });
    cellCoordinates.add(new int[] { limitY(Y + 1), limitX(X - 1) });
    cellCoordinates.add(new int[] { limitY(Y - 1), limitX(X - 1) });

    return cellCoordinates;
  }

  public ArrayList<Integer> getAdjacentCellsType(int Y, int X) {
    Cell cell = rows.get(Y).getRow().get(X);
    ArrayList<Integer> cellTypes = new ArrayList<>();
    cellTypes.add(cell.getType());
    //Orthagonal
    cellTypes.add(getCellTypeByCoordinate(Y - 1, X)); //Top
    cellTypes.add(getCellTypeByCoordinate(Y, X + 1)); //Right
    cellTypes.add(getCellTypeByCoordinate(Y + 1, X)); //Bottom
    cellTypes.add(getCellTypeByCoordinate(Y, X - 1)); //Left
    //Diagonal
    cellTypes.add(getCellTypeByCoordinate(Y - 1, X + 1)); //Top Right
    cellTypes.add(getCellTypeByCoordinate(Y + 1, X + 1)); //Bottom Right
    cellTypes.add(getCellTypeByCoordinate(Y + 1, X - 1)); //Bottom Left
    cellTypes.add(getCellTypeByCoordinate(Y - 1, X - 1)); //Top Left
    return cellTypes;
  }
}
