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
	height: 800px;
	background-color: blue;
	border-radius: 20px;
}

.title {
	color: yellow;
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="title">
				<h2>PACMAN'S GAME!</h2>
				<h3>LET'S PLAY</h3>
			</div>
			<div class="game">
				<button id="test">TEST BUTTON</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#test').click(function() {
			$.ajax({
				url : 'game',
				method : 'POST',
				dataType : 'json',
				success : function(data) {
					console.log(data);
				}
			});
		});
	</script>
</body>
</html>
