package com.fishteam.pacman.json;

import java.util.Random;

/**
 * Short game summary
 * enough info to identify session
 */
public class GameInfo {
	private long id = new Random().nextLong();
	private int width;
	private int height;
	public GameInfo() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	@Override
	public int hashCode() {
		final int prime = 97;
		int result = 1;
		result = prime * result + height;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + width;
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
		GameInfo other = (GameInfo) obj;
		if (height != other.height)
			return false;
		if (id != other.id)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
