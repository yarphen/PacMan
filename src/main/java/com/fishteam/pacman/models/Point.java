package com.fishteam.pacman.models;

import com.fishteam.pacman.interfaces.State;

public class Point implements State{

    protected int x;
    protected int y;

    public Point(){

    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setLocation(Point newPoint) {
    	setX(newPoint.x);
    	setY(newPoint.y);
	}
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
