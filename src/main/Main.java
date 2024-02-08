package main;

import grid.Cell;
import grid.Grid;
import grid.Row;
import items.Flag;
import items.Mine;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    // Row row = new Row(new ArrayList<Cell>(3), 3);
    Grid grid = new Grid(5, new ArrayList<Row>(5));

    System.out.println("New Grid: \n");
    System.out.println(grid.createGrid());
    System.out.println("\n");
    // System.out.println(grid.getRows().get(0).getRow().get(0).getAscii());
    grid.getRows().get(4).getRow().get(2).setAscii(new Mine().getMine());
    // System.out.println(grid.getRows().get(0).printRow());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.changeGrid());
    System.out.println("\n");
    //
    grid.getRows().get(3).getRow().get(2).setAscii(new Mine().getMine());
    //
    System.out.println("Changed Grid: \n");
    System.out.println(grid.changeGrid());
    System.out.println("\n");
    //
    grid.getRows().get(3).getRow().get(0).setAscii(new Flag().getFlag());
    //
    System.out.println("Changed Grid: \n");
    System.out.println(grid.changeGrid());
    System.out.println("\n");
  }
}
