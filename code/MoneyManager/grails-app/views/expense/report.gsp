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
	
	
	<table>
	
	<tr><td class="L_ALIGN PAGE_HDR">
	View Report
	</td></tr>

	<tr>
	<td>
	<g:form controller="Expense" action="getExpenseReport">
	<table>
	<tr><td colspan="5">Filter Report using from date and to date</td></tr>
	<tr>
	<td>From Date(DD/MM/yyyy)*</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="fromDate" id="fromDate"> </td><td></td>
	</tr>
	
	<tr>
	<td>To Date(DD/MM/yyyy)*</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="toDate" id="toDate"> </td><td></td>
	<td></td>
	</tr>
	
	<tr>
	<td><g:link controller="Dashboard" class="LINK_BTN">Back to dashboard</g:link></td>
	<td colspan="4">
	<button type="submit" class="SUBMIT_BTN">Submit</button>
	</td></tr>
	</table>
	</g:form>
	</td>
	</tr>
	
	<tr><td class="EMPTY_H10P"></td></tr>
	
	<tr><td>
	
		
	<table class="DATA_TBL">
	<thead>
	<tr>
	<th class="DATA_TBL_TH">Date</th>
	<th class="DATA_TBL_TH">Account</th>
	<th class="DATA_TBL_TH">Account Type</th>
	<th class="DATA_TBL_TH">Expense Type</th>
	<th class="DATA_TBL_TH">Remarks</th>
	<th class="DATA_TBL_TH">Amount</th>
	</tr>
	</thead>
	<tbody>
	<g:each var="expense" in="${expenses }">
	<tr>
	<td class="DATA_TBL_TD_STR">${expense.createdDate.format('dd/MM/yyyy') }</td>
	<td class="DATA_TBL_TD_STR">${expense.userAccount.accountName }</td>
	<td class="DATA_TBL_TD_STR">${expense.userAccount.accountType }</td>
	<td class="DATA_TBL_TD_STR">${expense.txnType }</td>
	<td class="DATA_TBL_TD_STR">${expense.remarks }</td>
	<td class="DATA_TBL_TD_NUM">${expense.amount }</td>
	</tr>
	</g:each>
	</tbody>
	</table>
	
	</td></tr>
	
	<tr><td class="EMPTY_H10P"></td></tr>
	
	</table>
	
	</body>
	</html>