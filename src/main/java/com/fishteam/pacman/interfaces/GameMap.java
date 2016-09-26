package com.fishteam.pacman.interfaces;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

import com.fishteam.pacman.models.Game;

public interface GameMap {
	public boolean add(Game g);
	public Game remove(long l) throws NoSuchElementException;
	public void refresh(Game g);
	public Game get(long l);
}
