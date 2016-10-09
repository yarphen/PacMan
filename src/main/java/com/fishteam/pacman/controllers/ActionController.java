package com.fishteam.pacman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fishteam.pacman.interfaces.GameMap;
import com.fishteam.pacman.interfaces.ProblemSolver;
import com.fishteam.pacman.json.GameInfo;
import com.fishteam.pacman.models.Game;
import com.fishteam.pacman.models.PacMan;
/**
 * This is controller to interact with js on page
 */
@Controller
public class ActionController {
	@Autowired
	private GameMap queueMap;
	@Autowired
	private ProblemSolver bfs_solver;
	@Autowired
	private ProblemSolver dfs_solver;
	@Autowired
	private ProblemSolver astar_solver;
	@Autowired
	private ProblemSolver greedy_solver;

/*
	@RequestMapping(value="/moveaction",method = RequestMethod.POST)
	public @ResponseBody ActionResult moveAction(@RequestBody MoveAction moveAction){
		Game game = queueMap.get(moveAction.getId());
		ActionResult result = new ActionResult();
		result.setSuccess(true);
		try{
			switch(moveAction.getDirection()){
			case MoveAction.UP:game.moveGhostTop();break;
			case MoveAction.RIGHT:game.moveGhostRight();break;
			case MoveAction.DOWN:game.moveGhostBot();break;
			case MoveAction.LEFT:game.moveGhostLeft();break;
			default:
				result.setSuccess(false);
				result.setMessage("Unknown command!");
			}
		}catch(BlockException e){
			result.setSuccess(false);
			result.setMessage("WAY IS BLOCKED!!!");
		}
		return result;
	}
	*/
	@RequestMapping(value="/game_bfs",method = RequestMethod.POST)
	public @ResponseBody Game newGameBFS(){
		Game game = new Game();
		queueMap.add(game);
		game.init(bfs_solver.solve(game));
		return game;
	}
	@RequestMapping(value="/game_dfs",method = RequestMethod.POST)
	public @ResponseBody Game newGameDFS(){
		Game game = new Game();
		queueMap.add(game);
		game.init(dfs_solver.solve(game));
		return game;
	}
	@RequestMapping(value="/game_astar",method = RequestMethod.POST)
	public @ResponseBody Game newGameAStar(){
		Game game = new Game();
		queueMap.add(game);
		game.init(astar_solver.solve(game));
		return game;
	}
	@RequestMapping(value="/game_greedy",method = RequestMethod.POST)
	public @ResponseBody Game newGameGreedy(){
		Game game = new Game();
		queueMap.add(game);
		game.init(greedy_solver.solve(game));
		return game;
	}
	@RequestMapping(value="/game",method = RequestMethod.DELETE)
	public void deleteGame(GameInfo game){
		queueMap.remove(game.getId());
	}
	@RequestMapping(value="/pacman",method = RequestMethod.POST)
	public @ResponseBody PacMan getPacMan(GameInfo game){
		Game storedGame = queueMap.get(game.getId());
		storedGame.step();
		return storedGame.getPacman();
	}
}
