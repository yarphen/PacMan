package com.fishteam.pacman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fishteam.pacman.json.ActionResult;
import com.fishteam.pacman.json.GameInfo;
import com.fishteam.pacman.json.MoveAction;
/**
 * This is controller to interact with js on page
 */
@Controller
public class ActionController {
	@RequestMapping(value="/moveaction",method = RequestMethod.POST)
	public @ResponseBody ActionResult moveAction(@RequestBody MoveAction moveAction){
		return null;
		
	}
	@RequestMapping(value="/game",method = RequestMethod.PUT)
	public @ResponseBody GameInfo newGame(GameInfo game){
		return null;
		
	}
	@RequestMapping(value="/game",method = RequestMethod.POST)
	public @ResponseBody GameInfo stayingAlive(GameInfo game){
		return null;
		
	}
}
