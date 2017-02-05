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
	
		<table class="W_100">
		
		<g:if test="${msg != null && msg.length() > 0 }">
		<tr><td class="EMPTY_H10P"></td></tr>
		<g:if test="${msgFlag == "1" }">
		<tr><td><label class="SUCCESS_MSG">${msg }</label></td></tr>
		</g:if>
		<g:else>
		<tr><td><label class="FAILURE_MSG">${msg }</label></td></tr>
		</g:else>
		</g:if>
		
		<tr><td class="EMPTY_H10P"></td></tr>
		
		<tr><td>
		
		<table>
		<tr>
		<td> <g:link controller="Expense" class="LINK_BTN">Add Expense</g:link> </td>
		<td class="EMPTY_W10P"></td>
		<td> <g:link controller="Account" class="LINK_BTN">Add Account</g:link> </td>
		<td class="EMPTY_W10P"></td>
		<td> <g:link controller="Vehicle" class="LINK_BTN">Add Vehicle</g:link> </td>
		</tr>
		</table>
		
		</td></tr>
		
		<tr><td class="EMPTY_H10P"></td></tr>
		
		<tr><td>
		
		<table class="DATA_TBL">
		<thead>
		<tr>
		<th class="DATA_TBL_TH">Account</th><th class="DATA_TBL_TH">Account Type</th>
		<th class="DATA_TBL_TH">Current Balance</th>
		<th class="DATA_TBL_TH">View 30 day statement</th>
		<th class="DATA_TBL_TH">View this month statement</th>
		</tr>
		</thead>
		<tbody>
		<g:each var="account" in="${userAccounts }">
		<tr>
		<td class="DATA_TBL_TD_STR">${account.accountName }</td>
		<td class="DATA_TBL_TD_STR">${account.accountType }</td>
		<td class="DATA_TBL_TD_NUM">${account.currentBalance }</td>
		<td class="DATA_TBL_TD_LINK"><g:link controller="Account" action="get30DayStmt" params="[accountId:account.id ]">view</g:link></td>
		<td class="DATA_TBL_TD_LINK"><g:link controller="Account" action="getThisMonthStmt" params="[accountId:account.id ]">view</g:link></td>
		</tr>
		</g:each>
		</tbody>
		</table>
		
		</td></tr>
		
		
		<tr><td class="EMPTY_H10P"></td></tr>
		
		<tr><td>
		
		<table class="DATA_TBL">
		<thead>
		<tr>
		<th class="DATA_TBL_TH">Vehicle Number</th>
		<th class="DATA_TBL_TH">Engine Type</th>
		<th class="DATA_TBL_TH">View Mileage</th>
		</tr>
		</thead>
		<tbody>
		<g:each var="vehicle" in="${vehicles }">
		<tr>
		<td class="DATA_TBL_TD_STR">${vehicle.VehicleNumber }</td>
		<td class="DATA_TBL_TD_STR">${vehicle.engineType }</td>
		<td class="DATA_TBL_TD_LINK"><g:link controller="Vehicle" action="getMileage" params="[vehicle:vehicle.id ]">view</g:link></td>
		</tr>
		</g:each>
		</tbody>
		</table>
		
		</td></tr>
		
		
		<tr><td class="EMPTY_H10P"></td></tr>
		
		<tr><td>
		
		<table class="DATA_TBL">
		<thead>
		<tr>
		<th class="DATA_TBL_TH">Date</th><th class="DATA_TBL_TH">Loan/Liability</th><th class="DATA_TBL_TH">Description</th>
		<th class="DATA_TBL_TH">From/To Person</th><th class="DATA_TBL_TH">Amount</th>
		<th class="DATA_TBL_TH">Planned Return Date</th>
		</tr>
		</thead>
		<tbody>
		<g:each var="loanLiability" in="${loanAndLiabilities }">
		<tr>
		<td class="DATA_TBL_TD_STR">${loanLiability.date }</td>
		<td class="DATA_TBL_TD_STR">${loanLiability.type }</td>
		<td class="DATA_TBL_TD_NUM">${loanLiability.description }</td>
		<td class="DATA_TBL_TD_STR">${loanLiability.fromToPerson }</td>
		<td class="DATA_TBL_TD_STR">${loanLiability.amount }</td>
		<td class="DATA_TBL_TD_NUM">${loanLiability.plannedReturnDate.format('dd/MM/yyyy') }</td>
		</tr>
		</g:each>
		</tbody>
		</table>
		
		</td></tr>
		
		
		</table>
	
	</body>
</html>
