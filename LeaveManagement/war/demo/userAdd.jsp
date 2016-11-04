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
						<form method="POST" action="../Signup.do">
								<input type="text" class="loginField" name="fname" placeholder="fname" />
								<input type="text" class="loginField" name="lname" placeholder="lname"/>
	                        	<input type="email" class="loginField" name="email" placeholder="email"/>
								<input type="password" class="loginField" name="password" placeholder="password"/>
	                        	<input type="text" class="loginField" name="empCode" value="" placeholder="empcode"/>
								<input type="text" class="loginField" name="empId" placeholder="empid"/>
								<input type="date" class="loginField" name="dateOfJoin" placeholder="doj"/>
	                        	<input type="text" class="loginField" name="phone" value="" placeholder="phone"/>
								<input type="text" class="loginField" name="team" placeholder="team"/>
								<input type="text" class="loginField" name="role" placeholder="role"/>
	                        	<input type="date" class="loginField" name="dateOfBirth" value="" placeholder="dob"/>
								<input type="text" class="loginField" name="panNo" placeholder="panno"/>
								
								<select class="loginField" name="gender" value="">
		                        	<option value="Male">Male</option>
		                        	<option value="Female" >Female</option>	                        	
	                        	</select>
	                        	
	                        	<input type="text" class="loginField" name="accNum" placeholder="accNo"/>
								<select class="loginField" name="access" value="" >
		                        	<option value="user" selected>User</option>
		                        	<option value="manager" >Manager</option>
		                        	<option value="admin" >Admin</option>	                        	
	                        	</select>
								
	                        	<select class="loginField" name="status" value="">
		                        	<option value="1" selected>Active</option>
		                        	<option value="2" >Inactive</option>	                        	
	                        	</select>
	                        	
	                        	<input type="text" class="loginField" name="reportTo" placeholder="reportTo"/>
								
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