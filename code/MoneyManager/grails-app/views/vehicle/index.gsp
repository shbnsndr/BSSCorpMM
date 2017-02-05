<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="app"/>
		
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

	</head>
	<body>
	
	<g:form controller="Vehicle" action="addVehicle" onsubmit="return validateInputs('vehicleNumber','engineType','jsErrorMsg')">
	<table>
	<tr><td colspan="4" class="L_ALIGN PAGE_HDR">
	Add Vehicle
	</td></tr>
	
	<tr><td colspan="4" class="EMPTY_H10P"></td></tr>
	
	<tr><td colspan="4">
	<div id="jsErrorMsg" class="JS_VALIDATION_ERRORS" style="display:none;">Please fill the * Mandatory Fields</div>
	</td></tr>
	
	<tr>
	<td>Vehicle Number*</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="vehicleNumber" id="vehicleNumber"> </td><td></td>
	</tr>
	
	<tr>
	<td>Engine Type*</td><td></td>
	<td>
	<select name="engineType" id="engineType" class="SELECT_INPUT">
	<option value="Select">Select</option>
	<option value="Diesel">Diesel</option>
	<option value="Petrol">Petrol</option>
	</select>
	</td><td></td>
	<td></td>
	</tr>
	
	<tr><td colspan="4" class="EMPTY_H10P"></td></tr>
	
	<tr>
	<td><g:link controller="Dashboard" class="LINK_BTN">Back to dashboard</g:link></td>
	<td colspan="3">
	<button type="submit" class="SUBMIT_BTN">Submit</button>
	</td></tr>
	
	
	</table>
	</g:form>
	
	</body>
	</html>