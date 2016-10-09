package com.fishteam.pacman.models;

import com.fishteam.pacman.interfaces.Problem;
import com.fishteam.pacman.interfaces.ProblemState;
import com.fishteam.pacman.json.GameInfo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Game implements Problem{
	private Labyrinth labyrinth = new Labyrinth();
	private Ghost ghost = new Ghost();
	private PacMan pacman = new PacMan();
	private Cherry cherry = new Cherry();
	private GameInfo info = new GameInfo();
	private Iterator<Point> way;
	public Game(){
		pacman.setLocation(new Point(1, 1));
		cherry.setLocation(new Point(23, 23));
		//ghost.setLocation(new Point(0, 25));
		info.setHeight(labyrinth.getCells().length);
		info.setWidth(labyrinth.getCells()[0].length);
	}
	public void moveGhostTop() throws BlockException {
		Point newPoint = labyrinth.topPoint(ghostLocation());
		if(newPoint != null){
			ghost.setLocation(newPoint);
		}
		else
			throw new BlockException("you can't move there");
	}

	public void moveGhostBot() throws BlockException {
		Point newPoint = labyrinth.botPoint(ghostLocation());
		if(newPoint != null){
			ghost.setLocation(newPoint);
		}
		else
			throw new BlockException("you can't move there");
	}

	public void moveGhostRight() throws BlockException {
		Point newPoint = labyrinth.rightPoint(ghostLocation());
		if(newPoint != null){
			ghost.setLocation(newPoint);
		}
		else
			throw new BlockException("you can't move there");
	}

	public void moveGhostLeft() throws BlockException {
		Point newPoint = labyrinth.leftPoint(ghostLocation());
		if(newPoint != null){
			ghost.setLocation(newPoint);
		}
		else
			throw new BlockException("you can't move there");
	}

	public ProblemState getStartState() {
		return pacman;
	}

	public ProblemState getGoalState() {
		return cherry;
	}

	public List<ProblemState> getChildren(ProblemState father) {
		List<ProblemState> children = new LinkedList<ProblemState>();
		Point point = (Point)father;
		ProblemState top = getLabyrinth().topPoint(point);
		ProblemState bot = getLabyrinth().botPoint(point);
		ProblemState right = getLabyrinth().rightPoint(point);
		ProblemState left = getLabyrinth().leftPoint(point);
		if (top!=null)children.add(top);
		if (bot!=null)children.add(bot);
		if (right!=null)children.add(right);
		if (left!=null)children.add(left);
		return children;
	}

	public Point ghostLocation(){
		return new Point(ghost.getX(),ghost.getY());
	}


	public List<ProblemState> getFathers(ProblemState child) {
		/*
		 * 'cause steps are bidirectional
		 */
		return getChildren(child);
	}


	public Labyrinth getLabyrinth() {
		return labyrinth;
	}


	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}


	public Ghost getGhost() {
		return ghost;
	}


	public void setGhost(Ghost ghost) {
		this.ghost = ghost;
	}


	public PacMan getPacman() {
		return pacman;
	}


	public void setPacman(PacMan pacman) {
		this.pacman = pacman;
	}


	public Cherry getCherry() {
		return cherry;
	}


	public void setCherry(Cherry cherry) {
		this.cherry = cherry;
	}
	public GameInfo getInfo() {
		return info;
	}
	public void setInfo(GameInfo info) {
		this.info = info;
	}
	@Override
	public int hashCode() {
		final int prime = 89;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
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
		Game other = (Game) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}
	
	public void init(List<ProblemState> way){
		LinkedList<Point> listOfPoints = new LinkedList<Point>();
		way.forEach(new Consumer<ProblemState>() {
			@Override
			public void accept(ProblemState p) {
				listOfPoints.addFirst((Point)p);
			}
		});
		this.way = listOfPoints.iterator();
	}
	public boolean step(){
		if (way.hasNext()){
			pacman.setLocation(way.next());
			return true;
		}else{
			return false;
		}
	}
	@Override
	public int getWeight(ProblemState selected) {
		Point point = (Point) selected;
		return Math.abs(cherry.x-point.x)+Math.abs(cherry.y-point.y);
	}
}
/*
class PacManThread extends Thread{
	private static final long DELAY = 500;
	private List<Point> way;
	private Game game;
	public PacManThread(List<Point> way, Game game) {
		super();
		this.way = way;
		this.game = game;
	}

	@Override
	public void run() {
		Iterator<Point> iterator = way.iterator();
		try {
			while(!isInterrupted()&&iterator.hasNext()){
				game.getPacman().setLocation(iterator.next());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			System.err.println("Game has been interrupted!");
		}
	}
}
*/