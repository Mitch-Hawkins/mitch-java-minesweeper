package grid;

import java.util.ArrayList;

public class Row {

  private ArrayList<Cell> row;
  private int length;

  public Row(ArrayList<Cell> row, int length) {
    this.row = row; // Array of Data for each Cell in the row
    this.length = length; // how many ascii cells per row
  }

  public ArrayList<Cell> getRow() {
    return this.row;
  }

  public void setRow(ArrayList<Cell> row) {
    this.row = row;
  }

  public String printRow() {
    ArrayList<String> tmp = new ArrayList<>();
    for (int i = 0; i < this.row.size(); i++) {
      tmp.add(this.row.get(i).getAscii());
    }
    return String.join("", tmp);
  }

  //^^^Combine these together like in grid

  public ArrayList<Cell> genRow() {
    for (int i = 0; i < this.length; i++) {
      Cell cell = new Cell();
      this.row.add(cell);
    }
    return this.row;
  }
}
