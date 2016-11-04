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
						<form method="POST" action="../addLeave.do">
								<input type="text" class="loginField" name="userId" placeholder="userId"/>
	                        	<input type="email" class="loginField" name="email" placeholder="email"/>
								<input type="number" class="loginField" name="entitledLeave" placeholder="entitledLeave"/>
	                        	<input type="number" class="loginField" name="leaveTaken" value="" placeholder="leaveTaken"/>
								
								<select class="loginField" name="leaveType" value="">
		                        	<option value="Casual">Casual</option>
		                        	<option value="Sick Leave" >Sick Leave</option>
		                        	<option value="Priv. Leave">Priv. Leave</option>
		                        	<option value="Others (LOP)" >Others (LOP)</option>
		                        	<option value="Seminar (Official)">Seminar (Official)</option>
		                        	<option value="Comp Off" >Comp Off</option>
		                       		<option value="Others" >Others</option>
		                        	<option value="Encashed PL">Encashed PL</option>
		                        	<option value="PL Avld till 31MAR15" >PL Avld till 31MAR15</option>
		                        		                        	
	                        	</select>
	                        	
						<input type="submit" class="submitButton" value="Login">
						</form>
					</div>
			</div>
		
	</body>
</html>
</html>