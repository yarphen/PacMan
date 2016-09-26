package com.fishteam.pacman;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.HashSet;

import com.fishteam.pacman.interfaces.Problem;
import com.fishteam.pacman.interfaces.ProblemState;

public class BidirectionalProblemSolver {
	public List<ProblemState> solve(final Problem problem){
		Set<ProblemState> fromStart = new HashSet<ProblemState>();
		Set<ProblemState> fromEnd = new HashSet<ProblemState>();
		Object locker = new Object();
		SolverThread thread1 = new SolverThread(
				new Function<ProblemState, List<ProblemState>>() {
					public List<ProblemState> apply(ProblemState t) {
						return problem.getChildren(t);
					}
				}, problem.getStartState(), fromStart, fromEnd, locker);
		SolverThread thread2 = new SolverThread(
				new Function<ProblemState, List<ProblemState>>() {
					public List<ProblemState> apply(ProblemState t) {
						return problem.getFathers(t);
					}
				}, problem.getGoalState(), fromEnd, fromStart, locker);
		return null;
	}

}
class SolverThread extends Thread{
	private Function<ProblemState, List<ProblemState>> childFunction;
	private ProblemState startState;
	private Set<ProblemState> fromStart;
	private Set<ProblemState> fromEnd;
	private Object locker;
	private Node endNode;
	public SolverThread(Function<ProblemState, List<ProblemState>> childFunction, 
			ProblemState state, 
			Set<ProblemState> fromStart,
			Set<ProblemState> fromEnd,
			Object locker) {
		super();
		this.childFunction = childFunction;
		this.startState = state;
		this.fromStart = fromStart;
		this.fromEnd = fromEnd;
		this.locker = locker;
	}
	@Override
	public void run() {
		super.run();
		Node head = new Node(null);
		this.endNode = findFromHead(head, childFunction, fromStart, fromEnd, locker);
	}
	private class Node{
		private ProblemState state;
		private Node father;
		private Set<Node> children = new HashSet<Node>();
		public Node(Node father) {
			this.father = father;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((state == null) ? 0 : state.hashCode());
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
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (state == null) {
				if (other.state != null)
					return false;
			} else if (!state.equals(other.state))
				return false;
			return true;
		}
		private SolverThread getOuterType() {
			return SolverThread.this;
		}
		
	}
	private Node findFromHead(Node head, Function<ProblemState, List<ProblemState>> childFunction, Set<ProblemState> fromStart, Set<ProblemState> fromEnd, Object locker){
		Set<Node> current = new HashSet<Node>();
		Set<Node> children = new HashSet<Node>();
		current.add(head);
		return findInChildren(childFunction, current, children, fromStart, fromEnd, locker);
	}
	private Node findInChildren(Function<ProblemState, List<ProblemState>> childFunction, Set<Node> current, Set<Node> children, Set<ProblemState> fromStart, Set<ProblemState> fromEnd, Object locker){
		children.clear();
		for(Node node:current){
			List<ProblemState> states = childFunction.apply(node.state);
			for(ProblemState state:states){
				if (!fromStart.contains(state)){
					synchronized (locker) {
						if (!fromStart.contains(state)){
							Node newNode = new Node(node);
							newNode.state = state;
							node.children.add(newNode);
							if (fromEnd.contains(state)){
								return newNode;
							}else{
								fromStart.add(state);
								children.add(newNode);
							}
						}
					}
				}
			}
		}
		return findInChildren(childFunction, children, current, fromStart, fromEnd, locker);
	}
}