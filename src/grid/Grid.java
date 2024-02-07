package grid;

import java.util.ArrayList;

public class Grid {

  private Row rowStructure;
  private int amountOfRows;

  public Grid(Row rowStructure, int amountOfRows) {
    this.rowStructure = rowStructure;
    this.amountOfRows = amountOfRows;
  }

  public String genGrid() {
    ArrayList<Row> rows = new ArrayList<Row>();
    for (int i = 0; i < this.amountOfRows; i++) {
      rows.add(rowStructure);
    }
    ArrayList<String> tempString = new ArrayList<>();
    for (int i = 0; i < rows.size(); i++) {
      tempString.add(rows.get(i).printRow());
    }
    return String.join("\n\n", tempString);
  }
}
