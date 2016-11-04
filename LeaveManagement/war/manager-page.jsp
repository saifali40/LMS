<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Leave Management System|UserPage</title>
		<!--  style sheets and font awesome  -->
		<link rel="stylesheet" href="assets/css/index.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
		<script src="dependencies/jquery.min.js"></script>
		
		<style>
		body {
		    font-family: "Lato", sans-serif;
		}

		</style>
	</head>
	<!--  Main section starts here for the webpage -->
	<body class="bodyClass">
		<div class="container-fluid">
			<div id="mySidenav" class="sidenav">
				<!-- <center><img src="https://d2q79iu7y748jz.cloudfront.net/s/_logo/e73546e57d7d852d4dd83de5e6cf3b29" class="head" width="90%"></center> -->
	  			<a href="#" class="demo"><h1><i class="fa fa-user" aria-hidden="true"></i></h1>Profile</a>
	  			<a href="#leaveDetail" class="demo"><h1><i class="fa fa-info" aria-hidden="true"></i></h1>Leave Summary</a>
	  			<a href="#ApplyLeave" class="demo"><h1><i class="fa fa-wpforms" aria-hidden="true"></i></h1>Apply Leave</a>
	  			<a href="#Status" class="demo"><h1><i class="fa fa-wpforms" aria-hidden="true" disabled></i></h1>Status of Application</a>
	  			
	  			<a href="#Teamstatus" class="demo"><h1><i class="fa fa-users" aria-hidden="true"></i></i></h1>Team status</a>
	  			<a href="#TeamLeaveStatus" class="demo"><h1><i class="fa fa-line-chart" aria-hidden="true"></i></i></h1>Team Leave Status</a>
	  			
	  			<a href="/Logout.do" class="demo"><h1><i class="fa fa-sign-out " aria-hidden="true" alt="logout"></i></h1>Logout</a>
			</div>
			
			<div class="col-md-offset-2 col-xs-offset-2 col-sm-offset-2 col-md-7 col-xs-10 col-sm-10 display" id="container">
			<div class="row" style="">
                <div class="col-md-12" id="primary-content">
                </div>

            </div>
			
			</div>
			
		</div>
<!--  templates from here to the script cdnjs s -->		
        <script type="text/jst" id="profileTemplate">
				<@ _.each(users,function(entry){ @>
				<table class="Table1" id="table">
					<h4>ABOUT <@= entry.get('firstName').toUpperCase() @><br /></h4>
					<hr Style="border:1px Solid grey">
					<tr><td class="td1">First Name &nbsp</td><td class="td2"><@= entry.get('firstName') @><br /></td></tr>
					<tr><td class="td1">Last Name </td><td class="td2"><@= entry.get('lastName') @><br /></td></tr>
					<tr><td class="td1">Email </td><td class="td2"><@= entry.get('email') @><br /></td></tr>
					<tr><td class="td1">Phone </td><td class="td2"><@= entry.get('phone') @><br /></td></tr>
					<tr><td class="td1">Date Of Birth </td><td class="td2"><@= entry.get('dateOfBirth') @><br /></td></tr>
					<tr><td class="td1">Gender </td><td class="td2"><@= entry.get('gender') @><br /></td></tr>
				</table>
				<br>
				<b>PROFESSIONAL DETAILS</b> <hr Style="border:1px Solid grey">
				<table class="Table1">
					<tr><td class="td1">Team &nbsp</td><td class="td2"><@= entry.get('team') @><br /></td></tr>
					<tr><td class="td1">Role </td><td class="td2"><@= entry.get('role') @><br /></td></tr>
					<tr><td class="td1">EmpCode </td><td class="td2"><@= entry.get('empCode') @><br /></td></tr>
					<tr><td class="td1">EmpId </td><td class="td2"><@= entry.get('empId') @><br /></td></tr>
					<tr><td class="td1">Date Of Join </td><td class="td2"><@= entry.get('dateOfJoin') @><br /></td></tr>
				</table>
				<br />
				<b>ACCOUNT DETAILS</b> <hr Style="border:1px Solid grey">
				<table class="Table1">
					<tr class="tr1"><td class="td1">Account Details &nbsp</td><td class="td2"><@= entry.get('accNum') @><br /></td></tr>
					<tr><td class="td1">Pan Number </td><td class="td2"><@= entry.get('panNo') @><br /></td></tr>
				</table>
				<br>

				<@ }); @>
		</script>
		
		<script type="text/jst" id="leaveTemplate">
				<h3>Leave Summary</h3>
				<table class="table" id="Table">
				<thead class="thead thead-inverse">
				<tr><th>Leave Type</th><th>Entitled Leave</th><th>Leave Taken</th><th>Leave Remaining</th></tr>
				</thead>
				<tbody class="tbody1">
				<@ _.each(leaves,function(entry){ @>
					<tr>
						<th scope="row"><@= entry.get('leaveType') @></th>
						<td><@= entry.get('entitledLeave') @></td>
						<td><@= entry.get('leaveTaken') @></td>
						<td><@= entry.get('leaveRemaining') @></td>
					</tr>
				<@ }); @>
				</tbody>
				</table>
				<button id="btnExport" onclick="teamDetails();"> EXPORT </button>
		</script>
		
		<script type="text/jst" id="statusTemplate">
		<table class="table" id="Table">
			<thead class="thead thead-inverse">
			<tr><th>Start date</th><th>End Date</th><th>Leave Type</th><th>Status</th></tr>
			</thead>
			<@ _.each(status,function(entry) { @>
			<tr>
				<td><@= entry.get('SDate') @></td>
				<td><@= entry.get('EDate') @></td>
				<td><@= entry.get('leaveType') @></td>
				<td><@= entry.get('approval') @></td>
			</tr>
			<@ }); @>

		</table>
		<button id="btnExport" onclick="teamDetails();"> EXPORT </button>
		</script>
		
		<script type="text/jst" id="applyTemplate">
			<div class="form">
				<form class="leaveApply">
					<div class="row1">
						<div class="col-md-4 label">Start Date</div>
						<div class="col-md-4 field"><input type="date" name="Sdate"></div>
					</div>

					<div class="row1">
						<div class="col-md-4 label">End Date</div>
						<div class="col-md-4 field"><input type="date" name="Edate"></div>
					</div>

					<div class="row1">
						<div class="col-md-4 label">Leave Type</div>
						<div class="col-md-4 field"><select name="leaveType" value="">
		                        									<option value="Casual">Casual</option>
		                        									<option value="Sick Leave" >Sick Leave</option>
																	</select></div>
					</div>

					<div class="row1">
						<div class="col-md-4 label">Comments</div>
						<div class="col-md-4 field"><textarea name="comments"></textarea></div>
					</div>

					<div class="row1">
						<div class="col-md-2 col-md-offset-3 field"> <button class="save btn btn-large btn-info" type="submit" style="font-size:20px;"><i class="fa fa-floppy-o"></i></button></div>
					</div>
				</form>
			</div>

		</script>
		
		<script type="text/jst" id="TeamPeople">
		
			<table class="table2" id="Table">
			<tr class="tr1">
				<th>Name</th>
				<th>Email</th>
				<th>Contact</th>
				<th>Role</th>
				<th>empcode</th>
				<th>empId</th>
				<th>Date of Join</th>
			</tr>
		<@ _.each(members,function(entry){ @>	
				<tr class="tr2">
					<td><@= entry.get('firstName') @></td>
					<td><@= entry.get('email') @></td>
					<td><@= entry.get('phone') @></td>
					<td><@= entry.get('role') @></td>
					<td><@= entry.get('empCode') @></td>
					<td><@= entry.get('empId') @></td>
					<td><@= entry.get('dateOfJoin') @></td>
					
				</tr>
		<@ }); @>						
			</table>
		<button id="btnExport" onclick="teamDetails();"> EXPORT </button>

		</script>
		<script type="text/jst" id="teamLeaveStatus">
				<table class="table2" id="Table">
			<tr class="tr1">
				<th>Email</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Leave Type</th>
				<th>Status</th>
				<th>Action</th>
			
			</tr>
			<@ _.each(teamStatus, function(entry) { @>
			<tr>
				<td>	<@= entry.get('email') @> </td>
				<td><@= entry.get('SDate') @></td>
				<td><@= entry.get('EDate') @></td>
				<td><@= entry.get('leaveType') @></td>
				<td><@= entry.get('approval') @></td>
				<td>
				<a href="#/approve/<@= entry.get('Id') @>" style="font-size:20px;padding:0px;color:#212B92"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i></a>
				
				</td>
			<@ }); @>	
	</table>
	<button id="btnExport" onclick="teamDetails();"> EXPORT </button>
		</script>
		
		<script type="text/jst" id="leaveEdit">
				<div class="form">
				<form class="leaveApply">
					
					<input type="hidden" name="id" value="<@= leave.get('Id') @>">				

					<div class="row1">
						<div class="col-md-4 label">Start Date</div>
						<div class="col-md-4 field"><input type="text" name="Sdate" value="<@= leave.get('SDate') @>" readonly></div>
					</div>

					<div class="row1">
						<div class="col-md-4 label">End Date</div>
						<div class="col-md-4 field"><input type="input" name="Edate" value="<@= leave.get('EDate') @>" readonly></div>
					</div>

					<div class="row1">
						<div class="col-md-4 label">Leave Type</div>
						<div class="col-md-4 field"><Input type="text" name="leaveType" value="<@= leave.get('leaveType') @>" readonly></div>
					</div>
					<div class="row1">
						<div class="col-md-4 label">Comments</div>
						<div class="col-md-4 field"><textarea name="comments"></textarea></div>
					</div>
					<div class="row1">
						<div class="col-md-4 label">Approval</div>
						<div class="col-md-4 field"><select name="Status" value="" id="Status">
																	<option value="Submitted"></option>
		                        									<option value="Approved">Approved</option>
		                        									<option value="Declined" >Declined</option>
																	</select></div>
					</div>
					<div class="row1 toggle">
						<div class="col-md-4 label">Number of days</div>
						<div class="col-md-4 field"><Input type="number" name="days" value="0" min="0" max="100"></div>
					</div>

					<div class="row1">
						<div class="col-md-2 col-md-offset-3 field"> <button class="update btn btn-large btn-info" type="submit" style="font-size:20px;"><i class="fa fa-floppy-o"></i></button></div>
					</div>
				</form>
			</div>
		</script>


 		
		
<!--  scripts starts form here -->		

		<script type="text/javascript" src="/_ah/channel/jsapi"></script>
		<script type="assets/js/channel.js"></script>
		
		<script src="dependencies/underscore-min.js"></script>
        <script src="dependencies/backbone-min.js"></script>
        <script src="backboneJs/Leave.js"></script>
 		<script src="backboneJs/Profile.js"></script>
 		<script src="assets/js/functional.js"></script>	
 		<script src="assets/nanobar/nanobar.js"></script>
	
 		<script>
	 	_.templateSettings = { 
	 		    interpolate: /\<\@\=(.+?)\@\>/gim,
	 		    evaluate: /\<\@([\s\S]+?)\@\>/gim,
	 		    escape: /\<\@\-(.+?)\@\>/gim
	 		};

			var simplebar = new Nanobar();
			simplebar.go(100);
			
       	
			
		</script>
 		
	</body>
</html>