package com.fishteam.pacman.models;

/**
 * In Labyrinth we have all cells' states
 * 0 - Empty
 * 1 - Wall
 * 2 - Cherry
 * 3 - Pill (if needed)
 *
 * x0y0 x1y0 x1y0
 * x0y1 x1y1 x2y1
 * x0y2 x1y2 x2y2
 */

public class Labyrinth {
    private int[][] cells;

    Labyrinth(){
        //only 5 first rows are done for testing
        cells = new int[][] {
                {0,0,0,0,0,0, 0,0,0,0, 0,0,1,1,0,0, 0,0,0,0, 0,0,0,0,0,0},
                {0,1,1,1,1, 0,1,1,1,1, 1,0,1,1,0,1, 0,0,0,0, 0,0,0,0,0,0},
                {0,1,1,1,1, 0,1,1,1,1, 1,0,1,1,0,1, 0,0,0,0, 0,0,0,0,0,0},
                {0,1,1,1,1, 0,1,1,1,1, 1,0,1,1,0,1, 0,0,0,0, 0,0,0,0,0,0},
                {0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0, 0,0,0,0, 0,0,0,0,0,0}
        };
    }

    /** Constructor for predefined board */
    Labyrinth(int[][] cells){
        this.cells = cells;
    }

    /** See the state of labyrinth in terminal (for testing purposes) */
    public String toString(){
        String maze = "";
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                switch (cells[i][j]) {
                    case 0:
                        maze+="+";
                        break;
                    case 1:
                        maze+="=";
                        break;
                    case 2:
                        maze+="*";
                        break;
                    case 3:
                        break;
                }
            }
            maze+="\n";
        }
        return maze;
    }

    /** Probably you won't need this */
    public int[][] getCells(){
        return cells;
    }

    /** Get the state of needed cell */
    public int getCellState(int x, int y){
        //care, here might be wrong size of cells array
        if (!exists(x,y))
            return -1;
        return cells[y][x];
    }

    /** Check if Ghost or Pacman can go through this cell */
    public boolean isOpened(int x, int y){
        return exists(x,y) && cells[y][x] != 1;
    }

    /** Check if Ghost or Pacman can go through this cell */
    public boolean isOpened(Point p){
        int x = p.getX();
        int y = p.getY();
        return exists(x,y) && cells[y][x] != 1;
    }

    /** Check if cell exists */
    private boolean exists(int x, int y){
        return !(x < 0 || y < 0 || y > cells.length-1 || x > cells[y].length-1 );
    }

    /** Check if cell exists */
    private boolean exists(Point p){
        int x = p.getX();
        int y = p.getY();
        return !(x < 0 || y < 0 || y > cells.length-1 || x > cells[y].length-1 );
    }

    public Point topPoint(Point current){
        if(isOpened(current.getX(),current.getY()-1))
            return new Point(current.getX(),current.getY()-1);
        return null;
    }

    public Point botPoint(Point current){
        if(isOpened(current.getX(),current.getY() + 1))
            return new Point(current.getX(),current.getY() + 1);
        return null;
    }

    public Point rightPoint(Point current){
        if(isOpened(current.getX() + 1 ,current.getY()))
            return new Point(current.getX() + 1,current.getY());
        return null;
    }

    public Point leftPoint(Point current){
        if(isOpened(current.getX() - 1 ,current.getY()))
            return new Point(current.getX() - 1,current.getY());
        return null;
    }
}
