package main;

import grid.Cell;
import grid.Grid;
import grid.Row;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    Row row = new Row(new ArrayList<Cell>(3), 8);
    Grid grid = new Grid(row, 7);

    row.genRow();
    // System.out.println(row.getRow().size());
    // System.out.println(row.printRow());
    System.out.println("Grid: \n");
    System.out.println(grid.genGrid());
  }
}
