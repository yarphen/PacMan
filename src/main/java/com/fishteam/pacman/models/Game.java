package com.fishteam.pacman.models;

import com.fishteam.pacman.interfaces.Problem;
import com.fishteam.pacman.interfaces.ProblemState;

import java.util.List;

public class Game implements Problem{
    private Labyrinth labyrinth;
    private Ghost ghost;
    private PacMan pacman;
    private Cherry cherry;
    public Game(){
        labyrinth = new Labyrinth();
        ghost = new Ghost();
        pacman = new PacMan();
        cherry = new Cherry();
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

    public ProblemState getState() {
        return pacman;
    }

    public ProblemState getStartState() {
        return null;
    }

    public ProblemState getGoalState() {
        return null;
    }

    public List<ProblemState> getChildren(ProblemState father) {
        return null;
    }

    public double getPathWeight(List<ProblemState> path) {
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


	public List<ProblemState> getFathers(ProblemState child) {
		/*
		 * 'cause steps are bidirectional
		 */
		return getChildren(child);
	}
}
class BlockException extends Exception {
    public BlockException(String s) {
    }
}