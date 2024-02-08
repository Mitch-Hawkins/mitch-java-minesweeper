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

  public String createGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      // rows.add(new Row(new ArrayList<Cell>(3), 3));
      Row row = new Row(new ArrayList<Cell>(3), 3);
      row.genRow();
      rows.add(row);
    }
    ArrayList<String> tempString = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      tempString.add(rows.get(i).printRow());
    }
    return String.join("\n", tempString);
  }

  public String changeGrid() {
    for (int i = 0; i < this.amountOfRows; i++) {
      for (int j = 0; j < rows.get(i).getRow().size(); j++) {
        rows.get(i).getRow().set(j, rows.get(i).getRow().get(j));
      }
    }
    ArrayList<String> tempString = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      tempString.add(rows.get(i).printRow());
    }
    return String.join("\n", tempString);
  }
}
//    1   2   3
// A [ ] [ ] [ ]
// B [ ] [ ] [ ]
// C [ ] [ ] [ ]
// D [ ] [ ] [ ]
// E [ ] [ ] [ ]
//    A   B   C
// 1 [ ] [ ] [ ]
// 2 [ ] [ ] [ ]
// 3 [ ] [ ] [ ]
// 4 [ ] [ ] [ ]
// 5 [ ] [ ] [ ]
