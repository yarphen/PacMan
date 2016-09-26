package com.fishteam.pacman.interfaces;

import java.util.List;

public interface Problem {
	ProblemState getState();
	ProblemState getStartState();
	ProblemState getGoalState();
	List<ProblemState> getChildren(ProblemState father);
	List<ProblemState> getFathers(ProblemState child);
	double getPathWeight(List<ProblemState> path);
}
