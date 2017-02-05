<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>BSS Corp - Money Manager</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
<script>
function sendRequest(){
	alert('Sending Data')
	$.ajax({
		type: "GET",
		url: "/eTrace/ReqHndlr/Requests/123",
		//data: {"requestId":"1002", "phoneNum":"9677084883", "requestType":"Fire Truck", "cmts":"Fire in SP Info City", "requesterName":"Shoban", "requesterLocation":"Perungundy"},
		success: function(data){
			alert(data['id'])
			alert(data['requesterPhNum'])
			alert('Data Inserted');
		}
});
}

function sendRequestIVR(){
	alert('Sending IVR Data')
	$.ajax({
		type: "GET",
		url: "/eTrace/ReqHndlr/getAllRequestsCount",
		data: {"phNum":"1002"},
		success: function(data){
			alert(data['count'])
		}
});
}

function sendRequestIVR1(){
	alert('Sending IVR Data')
	$.ajax({
		type: "GET",
		url: "/eTrace/ReqHndlr/GetReqSrvcs",
		data: {"phNum":"1002"},
		success: function(data){
			alert(data[0])
		}
});
}

function sendRequestIVR2(){
	alert('Sending IVR Data')
	$.ajax({
		type: "GET",
		url: "/eTrace/ReqHndlr/GetSingleReqDtls",
		data: {"phNum":"1002", "srvcNm":"Fire Service"},
		success: function(data){
			alert(data['addr'])
		}
});
}

</script>
	</head>
	<body>
	<g:form controller="Login" action="authUsr">
		<table>
		
		<tr>
		<td colspan="3">
		<label class="err_msg">${flash.message}</label>
		</td>
		</tr>
		
		<tr>
		<td><label class="label_name">UserName</label></td><td></td>
		<td><input type="text" name="userName"/></td>
		</tr>
		
		<tr>
		<td><label class="label_name">Password</label></td><td></td>
		<td><input type="password" name="password"></td>
		</tr>
		
		<tr>
		<td>
		<button type="submit" value="Login" class="SUBMIT_BTN">Sign In</button>
		</td><td></td><td>
		<button style="display: none;" type="button" onClick="sendRequestIVR()">Test Rest Api</button>
		</td>
		</tr>
		<tr><td>
		<button style="display: none;" type="button" onClick="sendRequestIVR2()">Test Rest Api 2</button>
		</td><td></td><td><button style="display: none;" type="button" onClick="sendRequestIVR1()">Test Rest Api 1</button>
		</td></tr>
		</table>
	</g:form>
	</body>
</html>
