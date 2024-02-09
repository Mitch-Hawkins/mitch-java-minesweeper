package grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Grid {

  private int amountOfRows;
  private ArrayList<Row> rows;

  public Grid(int amountOfRows, ArrayList<Row> rows) {
    this.amountOfRows = amountOfRows;
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
      int[] randomCoord = generateRandomCoordinate(5, 3);
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
      Row row = new Row(new ArrayList<Cell>(3), 3);
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

  public String getCellToLeft(int Y, int X) {
    if (X - 1 >= 0) {
      return rows.get(Y).getRow().get(X - 1).getAscii();
    } else {
      return "N/A";
    }
  }

  public String getCellToRight(int Y, int X) {
    if (X + 1 <= rows.get(Y).getRow().size() - 1) {
      return rows.get(Y).getRow().get(X + 1).getAscii();
    } else {
      return "N/A";
    }
  }

  public String getCellAbove(int Y, int X) {
    if (Y - 1 >= 0) {
      return rows.get(Y - 1).getRow().get(X).getAscii();
    } else {
      return "N/A";
    }
  }

  public String getCellBelow(int Y, int X) {
    if (Y + 1 <= rows.size() - 1) {
      return rows.get(Y + 1).getRow().get(X).getAscii();
    } else {
      return "N/A";
    }
  }

  public String getAdjacentCells(int Y, int X) {
    String currentCellAscii = rows.get(Y).getRow().get(X).getAscii();
    String leftCellAscii = getCellToLeft(Y, X);
    String rightCellAscii = getCellToRight(Y, X);
    String topCellAscii = getCellAbove(Y, X);
    String bottomCellAscii = getCellBelow(Y, X);
    //Compile all the information
    return String.format(
      "Selected Cell:\n%d,%d : %s\n\nHoriz Adjacent Cells:\n%d,%d : %s\n%d,%d : %s\n\nVert Adjacent Cells:\n%d,%d : %s\n" + //
      "%d,%d : %s",
      Y,
      X,
      currentCellAscii,
      Y,
      (X - 1),
      leftCellAscii,
      Y,
      X + 1,
      rightCellAscii,
      (Y - 1),
      X,
      topCellAscii,
      (Y + 1),
      X,
      bottomCellAscii
    );
  }
}
//Expected output of getAdjacentAscii
//Selected Cell:
//<CellCoords>:<CellAscii>
//
//Adjacent Cells:
//<CellCoords>:<CellAscii>
//<CellCoords>:<CellAscii>
