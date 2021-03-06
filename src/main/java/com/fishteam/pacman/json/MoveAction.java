package com.fishteam.pacman.json;
/**
 * Action of moving in some direction
 */
public class MoveAction {
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	private int direction;
	private long id;
	public MoveAction() {}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
