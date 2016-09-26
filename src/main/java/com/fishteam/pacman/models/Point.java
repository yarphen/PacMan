package com.fishteam.pacman.models;

import java.util.Random;

import com.fishteam.pacman.interfaces.ProblemState;

public class Point implements ProblemState{

    protected int x;
    protected int y;
    private long id = new Random().nextLong();
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

	@Override
	public int hashCode() {
		final int prime = 293;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
