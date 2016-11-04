	<html>
	<head>
		<title>Leave Management System | Login</title>
		<!--  CSS File  -->
		<link rel="stylesheet" href="assets/css/index.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
	</head>
	<body class="bodyClass">
			
			<div class="container-fluid">
					
					<div class="col-md-4 col-sm-4 col-sm-offset-4 col-md-offset-4 loginForm">
						<center><img src="assets/img/logo.jpg" class="logoHead"></center>
						<form method="POST" action="/Login.do">
						<div class="row">
							<div class="col-md-1 form-group">
	                        	<h4><i class="fa fa-user" aria-hidden="true"></i></h4>
							</div>
							
							<div class="col-md-11 form-group">
	                        	<input type="email" class="loginField" name="email" value="" id="email" placeholder="Enter Your Email" required/>
							</div>
							
							<div class="col-md-1 form-group">
	                        	<h4><i class="fa fa-lock" aria-hidden="true"></i></h4>
							</div>
							
							<div class="col-md-11 form-group">
	                        	<input type="password" class="loginField" name="password" value="" id="password" placeholder="Enter Your Password " pattern=".{4,}" title="Six or more characters" required/>
							</div>
						</div>
						<input type="submit" class="submitButton" value="Login">
						</form>
					</div>
					<div class="col-md-4 col-sm-4 col-sm-offset-4 col-md-offset-4 google">
					<input type="submit" class="googleAuth" Value="Google+">
					</div>
			</div>
		
	</body>
</html>
</html>