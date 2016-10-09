$(document).ready(function() {
	var bfs = $('#bfs');
	var dfs = $('#dfs');
	var a_star = $('#a_star');
	var greedy = $('#greedy');
	var game = $('.game');
	function GameStage(){
		var renderer = new PIXI.CanvasRenderer(game.width(), game.width());
		game.append(renderer.view);
		var stage = new PIXI.Stage;
		var draw = function () {
			renderer.render(stage);
			requestAnimationFrame(draw);
		}
		this.draw = draw;
		this.stage = stage;
		this.renderer = renderer;
	};
	var gameStage = new GameStage();
	var renderLabyrinth = function(data){
		var createSprite = function(url, x, y, w, h){
			var object = PIXI.Sprite
			.fromImage(url);
			object.width = w;
			object.height = h;
			object.x = x;
			object.y = y;
			return object;
		};
		console.log(data);
		var w_size = game.width()/(data.info.width);
		var h_size = game.height()/(data.info.height);
		for (var i = 0; i < data.info.width; i++) {
			for (var j = 0; j < data.info.height; j++) {
				if (data.labyrinth.cells[j][i] == 1 ) {
					var wall = createSprite(
							'resources/wall.jpg', 
							w_size * i, 
							h_size * j, 
							w_size,
							h_size);
					gameStage.stage.addChild(wall);
				}
			}
		}
		var resultObj = {};
		var pacman = createSprite(
				'resources/pacman.gif', 
				data.pacman.x*w_size+w_size*0.5,  
				data.pacman.y*h_size+h_size*0.5, 
				w_size,
				h_size);
		pacman.anchor.x = 0.5;
		pacman.anchor.y = 0.5;
		var cherry = createSprite(
				'resources/cherry.png',  
				data.cherry.x*w_size+w_size*0.5, 
				data.cherry.y*h_size+h_size*0.5,  
				w_size,
				h_size);
		cherry.anchor.x = 0.5;
		cherry.anchor.y = 0.5;
		gameStage.stage.addChild(pacman);
		gameStage.stage.addChild(cherry);
		resultObj.pacmanSprite=pacman;
		resultObj.cherrySprite=cherry;
		resultObj.pacmanPoint=data.pacman;
		resultObj.cherryPoint=data.cherry;
		resultObj.id=data.info.id;
		resultObj.w=w_size;
		resultObj.h=h_size;
		return resultObj;
	};
	var block = $('.start-container');
	bfs.click(function(){
		startGame('game_bfs',block);
	});
	dfs.click(function(){
		startGame('game_dfs', block);
	});
	a_star.click(function(){
		startGame('game_a_star', block);
	});
	

	function startGame(solver, block){
		game.hide();
		block.fadeOut(2000, function() {
			$.ajax({
				url : solver,
				method : 'POST',
				dataType : 'json',
				success : function(data) {
					var gameInfo = renderLabyrinth(data);
					game.fadeIn(4000, function(){
						var plainMove = function(sprite, from, to, duration, success, steps, step){
							if (step === undefined){
								step = 0;
							}
							if (step>steps){
								success();
							}else{
								var coef1 = (step+0.0)/steps;
								var coef2 = 1 - coef1;
								sprite.x = coef2*from.x + coef1*to.x;
								sprite.y = coef2*from.y + coef1*to.y;
								setTimeout(function(){
									plainMove(sprite, from, to, duration, success, steps, step+1);
								}, duration/steps);
							}
						};
						var scaleOut = function(sprite, delay, q, steps){
							sprite.width*=q;
							sprite.height*=q;
							if (steps>0){
								setTimeout(function(){
									scaleOut(sprite, delay, q, steps-1);
								}, delay);
							}
						};
						var step = function(){
							$.ajax({
								url : 'pacman',
								method : 'POST',
								data:{
									id: gameInfo.id
								},
								dataType : 'json',
								success : function(pacman) {
									if (gameInfo.pacmanPoint.x==gameInfo.cherryPoint.x
											&&
											gameInfo.pacmanPoint.y==gameInfo.cherryPoint.y){
										scaleOut(
												gameInfo.cherrySprite,
												10,
												0.9,
												100
										);
										block.fadeIn(2000);
									}else{
										var currentPacman = {
												x:gameInfo.pacmanPoint.x*gameInfo.w+gameInfo.w*0.5,
												y:gameInfo.pacmanPoint.y*gameInfo.h+gameInfo.h*0.5
										};
										var futurePacman = {
												x:pacman.x*gameInfo.w+gameInfo.w*0.5,
												y:pacman.y*gameInfo.h+gameInfo.h*0.5
										};
										gameInfo.pacmanSprite.rotation = 
										Math.atan2(
												futurePacman.y-currentPacman.y,
												futurePacman.x-currentPacman.x
												);
										plainMove(
												gameInfo.pacmanSprite,
												currentPacman, 
												futurePacman,
												50,
												step,
												5
										);
										gameInfo.pacmanPoint = pacman;
									}
								}
							});
						};
						step();
					});
				}
			});
		});
	}
	gameStage.draw();
});