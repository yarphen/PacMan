package com.fishteam.pacman.interfaces;

import java.util.List;


public interface Problem {
	ProblemState getStartState();
	ProblemState getGoalState();
	List<ProblemState> getChildren(ProblemState father);
	List<ProblemState> getFathers(ProblemState child);
	int getWeight(ProblemState selected);
}
