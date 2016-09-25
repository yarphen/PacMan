package com.fishteam.pacman.models;

import com.fishteam.pacman.interfaces.Problem;
import com.fishteam.pacman.interfaces.State;

import java.util.List;

public class Game implements Problem{
    private Labyrinth labyrinth;
    private Ghost ghost;

    public Game(){
        labyrinth = new Labyrinth();
        ghost = new Ghost();
    }


    public void moveGhostTop() throws BlockException {
        Point newPoint = labyrinth.topPoint(ghostLocation());
        if(newPoint != null){
            ghost.setLocation(newPoint);
        }
        else
            throw new BlockException("you can't move there");
    }

    public void moveGhostBot() throws BlockException {
        Point newPoint = labyrinth.botPoint(ghostLocation());
        if(newPoint != null){
            ghost.setLocation(newPoint);
        }
        else
            throw new BlockException("you can't move there");
    }

    public void moveGhostRight() throws BlockException {
        Point newPoint = labyrinth.rightPoint(ghostLocation());
        if(newPoint != null){
            ghost.setLocation(newPoint);
        }
        else
            throw new BlockException("you can't move there");
    }

    public void moveGhostLeft() throws BlockException {
        Point newPoint = labyrinth.leftPoint(ghostLocation());
        if(newPoint != null){
            ghost.setLocation(newPoint);
        }
        else
            throw new BlockException("you can't move there");
    }

    public State getState() {
        return null;
    }

    public State getStartState() {
        return null;
    }

    public State getGoalState() {
        return null;
    }

    public List<State> getChildren(State father) {
        return null;
    }

    public double getPathWeight(List<State> path) {
        return 0;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public Point ghostLocation(){
        return new Point(ghost.getX(),getGhost().getY());
    }

    public class BlockException extends Exception {
        public BlockException(String s) {
        }
    }
}
