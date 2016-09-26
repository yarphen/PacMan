package com.fishteam.pacman.json;
/**
 * Short game summary
 * enough info to identify session
 */
public class GameInfo {
	private long id;
	private long alivetime;
	private int width;
	private int height;
	public GameInfo() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAlivetime() {
		return alivetime;
	}
	public void setAlivetime(long alivetime) {
		this.alivetime = alivetime;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
