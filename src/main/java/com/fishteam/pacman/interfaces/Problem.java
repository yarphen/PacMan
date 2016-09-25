package com.fishteam.pacman.interfaces;

import java.util.List;

public interface Problem {
	State getState();
	State getStartState();
	State getGoalState();
	List<State> getChildren(State father);
	double getPathWeight(List<State> path);
}
