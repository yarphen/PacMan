package com.fishteam.pacman.solvers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.fishteam.pacman.interfaces.Problem;
import com.fishteam.pacman.interfaces.ProblemSolver;
import com.fishteam.pacman.interfaces.ProblemState;



public class BFSProblemSolverV2 implements ProblemSolver {

	@Override
	public List<ProblemState> solve(Problem problem) {
		List<ProblemState> path = new LinkedList<ProblemState>();
		Set<ProblemState> visited = new HashSet<ProblemState>();
		Queue<Node> queue = new LinkedList<Node>();
		Node head = new Node(problem.getStartState(), null);
		queue.add(head);
		visited.add(head.element);
		int expanded = 0;
		int known = 0;
		outer:while(!queue.isEmpty()){
			Node current = queue.poll();
			List<ProblemState> children = problem.getChildren(current.element);
			for(ProblemState state: children){
				if (!visited.contains(state)){
					if (state.equals(problem.getGoalState())){
						path.add(state);
						path.add(current.element);
						while(current.father != null){
							current = current.father;
							path.add(current.element);
						}

						break outer;
					}else{
						known++;
						Node node = new Node(state, current);
						visited.add(state);
						queue.add(node);
					}
				}
			}
			expanded++;
		}
		System.err.println("BFSv2: expanded: "+expanded+"; known: "+known+".");
		return path;
	}
	private class Node{
		private final ProblemState element;
		private final Node father;
		public Node(ProblemState element, Node father) {
			super();
			this.element = element;
			this.father = father;
		}
	}
}
