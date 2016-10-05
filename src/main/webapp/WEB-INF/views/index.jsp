<html>
<head>
<style>
body {
	margin: 0;
}

.container {
	width: 100%;
	height: 100%;
	position: fixed;
	background-color: black;
	padding: 10px;
}

.content {
	padding: 10px;
	margin-right: auto;
	margin-left: auto;
	width: 800px;
	border: solid 3px blue;
	border-radius: 20px;
}

.game {
	background-color: blue;
	border-radius: 20px;
	padding: 10px;
}

.title {
	color: yellow;
	text-align: center;
}

#start {
	
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pixi.js/4.0.0/pixi.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="title">
				<h2>PACMAN'S GAME!</h2>
				<h3>LET'S PLAY</h3>
				<div id="start">START GAME</div>
			</div>
			<div class="game"></div>
		</div>
	</div>
	<script type="text/javascript">
		(function() {
			var start = $('#start');
			var game = $('.game');
			var renderer = new PIXI.CanvasRenderer(game.width(), game.width());
			game.append(renderer.view);
			var stage = new PIXI.Stage;
			start.click(function() {
				$.ajax({
					url : 'game',
					method : 'POST',
					dataType : 'json',
					success : function(data) {
						console.log(data);
						var w_size = game.width()/data.info.width;
						var h_size = game.height()/data.info.height;
						for (var i = 0; i < data.info.width; i++) {
							for (var j = 0; j < data.info.height; j++) {
								if (data.labyrinth.cells[j][i] == 1) {
									var wall = PIXI.Sprite
											.fromImage('resources/wall.png');
									wall.x = w_size * i;
									wall.y = h_size * j;
									wall.width = w_size;
									wall.height = h_size;
									stage.addChild(wall);
								}
							}
						}
						start.hide();
					}
				});
			});
			function draw() {
				renderer.render(stage);
				requestAnimationFrame(draw);
			}
			draw();
		})();
	</script>
</body>
</html>
