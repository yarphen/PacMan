package com.fishteam.pacman;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import com.fishteam.pacman.interfaces.GameMap;
import com.fishteam.pacman.models.Game;

public class GameQueueMap implements GameMap{
	private final Queue<Game> queue = new LinkedList<Game>();
	private final Map<String, Game> map = new HashMap<String, Game>();
	private final int maxCapacity;
	public GameQueueMap(int maxCapacity) {
		this.maxCapacity=maxCapacity;
	}
	public boolean add(Game g) {
		// Only add element to queue if the set does not contain the specified element.
		String id = g.getInfo().getId();
		if (map.put(id, g) == null) {
			queue.add(g);
			if (queue.size()>maxCapacity){
				map.remove(id);
			}
		}
		return true; // Must always return true as per API def.
	}
	public Game remove(String id) throws NoSuchElementException {
		Game game = map.remove(id);
		if (!queue.remove(game)) throw new NoSuchElementException();
		return game;
	}
	public void refresh(Game g){
		remove(g.getInfo().getId());
		add(g);
	}
	@Override
	public Game get(String id) {
		return map.get(id);
	}
}
