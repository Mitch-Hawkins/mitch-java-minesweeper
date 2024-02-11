package grid;

import game.Game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Grid {

  private int amountOfRows;
  private ArrayList<Row> rows;
  private int lengthOfRows;

  public Grid(int amountOfRows, int lengthOfRows, ArrayList<Row> rows) {
    this.amountOfRows = amountOfRows;
    this.lengthOfRows = lengthOfRows;
    this.rows = rows; //Array of Data for each row
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
        .setType(6);
      System.out.println(Arrays.toString(coordinatesOfMines.get(i)));
    }
    System.out.println("Mines Planted!");
  }

  public String printGrid() {
    ArrayList<String> tempString = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      tempString.add(rows.get(i).printRow());
    }
    return String.join("\n", tempString);
  }

  public String createGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      Row row = new Row(
        new ArrayList<Cell>(this.lengthOfRows),
        this.lengthOfRows
      );
      row.createRow();
      rows.add(row);
    }
    return printGrid();
  }

  public String updateGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      for (int j = 0; j < rows.get(i).getRow().size(); j++) {
        rows.get(i).getRow().set(j, rows.get(i).getRow().get(j));
      }
    }
    return printGrid();
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
        if (rows.get(y).getRow().get(x).getType() != 6) {
          int cellNumberCount = 0;
          ArrayList<Integer> cellTypes = getAdjacentCellsType(y, x);
          for (int i = 0; i <= cellTypes.size() - 1; i++) {
            if (cellTypes.get(i) == 6) {
              cellNumberCount++;
            }
            rows.get(y).getRow().get(x).setType(cellNumberCount);
          }
        }
      }
    }
  }

  public String revealBoard() {
    for (int x = 0; x <= rows.get(0).getRow().size() - 1; x++) {
      for (int y = 0; y <= rows.size() - 1; y++) {
        if (rows.get(y).getRow().get(x).getType() <= 4) {
          rows
            .get(y)
            .getRow()
            .get(x)
            .setAscii(
              String.format("[%d]", rows.get(y).getRow().get(x).getType())
            );
        } else if (rows.get(y).getRow().get(x).getType() == 6) {
          rows.get(y).getRow().get(x).setAscii("[*]");
        }
      }
    }
    return updateGrid();
  }

  public String revealCell(int Y, int X) {
    if (rows.get(Y).getRow().get(X).getType() <= 4) {
      rows
        .get(Y)
        .getRow()
        .get(X)
        .setAscii(String.format("[%d]", rows.get(Y).getRow().get(X).getType()));
      System.out.println(
        String.format(
          "Cell %d,%d was [%s]\n",
          Y,
          X,
          rows.get(Y).getRow().get(X).getAscii()
        )
      );
      return updateGrid();
    } else if (rows.get(Y).getRow().get(X).getType() == 6) {
      System.out.println(
        String.format("Oh No! Cell %d,%d was a mine! [*]!\n", Y, X)
      );
      System.out.println("GAME OVER");
      Game.isGameWon = true;
      return revealBoard();
    } else {
      return "Invalid Coordinate, please try again";
    }
  }

  public ArrayList<Integer> getAdjacentCellsType(int Y, int X) {
    ArrayList<Integer> cellTypes = new ArrayList<>();
    cellTypes.add(rows.get(Y).getRow().get(X).getType());
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

  public String getAdjacentCellsAscii(int Y, int X) {
    String currentCellAscii = rows.get(Y).getRow().get(X).getAscii();
    String topCellAscii = getCellAsciiByCoordinate(Y - 1, X);
    String rightCellAscii = getCellAsciiByCoordinate(Y, X + 1);
    String bottomCellAscii = getCellAsciiByCoordinate(Y + 1, X);
    String leftCellAscii = getCellAsciiByCoordinate(Y, X - 1);
    //Compile all the information
    return String.format(
      "Selected Cell:\n%d,%d : %s\n\nAdjacent Cells:\n%d,%d : %s\n%d,%d : %s\n%d,%d : %s\n" + //
      "%d,%d : %s",
      Y,
      X,
      currentCellAscii,
      (Y - 1),
      X,
      topCellAscii,
      Y,
      X + 1,
      rightCellAscii,
      (Y + 1),
      X,
      bottomCellAscii,
      Y,
      (X - 1),
      leftCellAscii
    );
  }
}
