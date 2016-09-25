package com.fishteam.pacman.models;

public class Ghost {
    private int x;
    private int y;

    Ghost(){
        //it's might be a bad idea to start from here
        //better change this
        x = 1;
        y = 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public Point getLocation(){
        return new Point(x,y);
    }

    public void setLocation(Point p){
        x = p.getX();
        y = p.getY();
    }

    public String toString(){
        return "ghost(" + x +"," + y +")";
    }
}
