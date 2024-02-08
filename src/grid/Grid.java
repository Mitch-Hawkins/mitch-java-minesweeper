package grid;

import java.util.ArrayList;

public class Grid {

  // private Row rowStructure;
  private int amountOfRows;
  private ArrayList<Row> rows;

  public Grid(int amountOfRows, ArrayList<Row> rows) {
    // this.rowStructure = rowStructure;
    this.amountOfRows = amountOfRows;
    this.rows = rows; //Array of Data for each row
  }

  public ArrayList<Row> getRows() {
    return this.rows;
  }

  public void setRows(ArrayList<Row> rows) {
    this.rows = rows;
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
      row.genRow();
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
    //I want to print what the Cell ascii contents are
    //I want to then find the Cell class of each horizontally adjecent Cell based on the Y and X coordinates
    //I want to print what THOSE cell ascii contents are and label them as the adjacent cells (With Coords listed too)
  }
}
//Expected output of getAdjacentAscii
//Selected Cell:
//<CellCoords>:<CellAscii>
//
//Adjacent Cells:
//<CellCoords>:<CellAscii>
//<CellCoords>:<CellAscii>
