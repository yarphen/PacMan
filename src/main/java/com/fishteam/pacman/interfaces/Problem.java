package com.fishteam.pacman.interfaces;

import java.util.List;

public interface Problem {
	State getState();
	State getStartState();
	State getGoalState();
	List<State> getChildren(State father);
	List<State> getFathers(State child);
	double getPathWeight(List<State> path);
}
