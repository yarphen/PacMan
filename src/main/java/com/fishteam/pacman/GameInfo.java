package com.fishteam.pacman;

public class GameInfo {
	private long id;
	private long alivetime;
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
	@Override
	public int hashCode() {
		final int prime = 997;
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
		GameInfo other = (GameInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
