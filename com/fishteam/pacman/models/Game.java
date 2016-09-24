package com.fishteam.pacman.models;

public class Game {
    Labyrinth labyrinth;
    Ghost ghost;

    Game(){
        labyrinth = new Labyrinth();
        ghost = new Ghost();
    }


    public void moveGhostTop(){
        int current_x = ghost.getX();
        int current_y = ghost.getY();
        if(labyrinth.isOpened(current_x,--current_y))
            ghost.setY(--current_y);
    }

    public void moveGhostBot(){
        int current_x = ghost.getX();
        int current_y = ghost.getY();
        if(labyrinth.isOpened(current_x,++current_y)){
            ghost.setY(++current_y);
        }

    }

    public void moveGhostRight(){
        int current_x = ghost.getX();
        int current_y = ghost.getY();
        if(labyrinth.isOpened(current_x,++current_x))
            ghost.setX(++current_x);
    }

    public void moveGhostLeft(){
        int current_x = ghost.getX();
        int current_y = ghost.getY();
        if(labyrinth.isOpened(current_x,--current_x))
            ghost.setX(--current_x);
    }
}
