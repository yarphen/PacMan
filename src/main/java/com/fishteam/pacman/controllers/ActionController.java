package com.fishteam.pacman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fishteam.pacman.GameQueueMap;
import com.fishteam.pacman.interfaces.GameMap;
import com.fishteam.pacman.interfaces.ProblemSolver;
import com.fishteam.pacman.json.ActionResult;
import com.fishteam.pacman.json.GameInfo;
import com.fishteam.pacman.json.MoveAction;
import com.fishteam.pacman.models.Game;
import com.fishteam.pacman.models.PacMan;
import com.fishteam.pacman.models.Point;
/**
 * This is controller to interact with js on page
 */
@Controller
public class ActionController {
	@Autowired
	private GameMap queueMap;
	@Autowired
	private ProblemSolver solver;
	
	
	@RequestMapping(value="/moveaction",method = RequestMethod.POST)
	public @ResponseBody ActionResult moveAction(@RequestBody MoveAction moveAction){
		return null;
	}
	@RequestMapping(value="/game",method = RequestMethod.POST)
	public @ResponseBody Game newGame(){
		Game game = new Game();
		queueMap.add(game);
		game.init(solver.solve(game));
		game.start();
		return game;
	}
	@RequestMapping(value="/game",method = RequestMethod.DELETE)
	public void deleteGame(GameInfo game){
		queueMap.remove(game.getId()).stop();
	}
	@RequestMapping(value="/pacman",method = RequestMethod.POST)
	public @ResponseBody PacMan getPacMan(GameInfo game){
		return queueMap.get(game.getId()).getPacman();
	}
}
