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
	
	<g:form controller="Expense" action="addExpense" onsubmit="return validateExpense();">
	<table>
	<tr><td colspan="4" class="L_ALIGN PAGE_HDR">
	Add Expense
	</td></tr>
	
	<tr><td colspan="4" class="EMPTY_H10P"></td></tr>
	
	<tr><td colspan="4">
	<div id="jsErrorMsg" class="JS_VALIDATION_ERRORS" style="display:none;">Please fill the * mandatory fields</div>
	</td></tr>
	
	<tr><td colspan="4">
	<div id="jsNumericErrorMsg" class="JS_VALIDATION_ERRORS" style="display:none;">Please enter numeric values</div>
	</td></tr>
	
	<tr>
	<td class="LABEL_CONT">Expense Name*</td><td></td>
	<td class="INPUT_CONT"><input class="TEXT_INPUT" type="text" name="expenseName" id="expenseName"> </td><td></td>
	</tr>
	
	<tr>
	<td>Expense Type*</td><td></td>
	<td>
	
	<select class="SELECT_INPUT" name="expenseType" id="expenseType" onchange="findExpenseType();">
	<option value="Select">Select</option>
	<option value="General">General</option>
	<option value="Food">Food</option>
	<option value="Fuel">Fuel</option>
	<option value="Income">Income</option>
	<option value="Income From Other Sources">Income From Other Sources</option>
	<option value="Transfer Within Accounts">Transfer Within Accounts</option>
	<option value="Loan">Loan</option>
	<option value="Lend">Lend</option>
	<option value="Pay Debts">Pay Debts</option>
	<option value="Receive Debts">Receive Debts</option>
	</select>
	
	</td>
	<td></td>
	</tr>
	
	<tr>
	<td>Account*</td><td></td>
	<td>
	
	<select class="SELECT_INPUT" name="account" id="account" onchange="getAccountBalance()">
	<option value="Select">Select</option>
	<g:each var="account" in="${userAccounts }">
	<option value="${account.id }">${account.accountName }</option>
	</g:each>
	</select>
	
	</td>
	</tr>
	
	<tr>
	<td>Account Balance</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="accountBalance" id="accountBalance" readonly="readonly"> </td><td></td>
	</tr>
	
	<tr>
	<td>Amount*</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="amount" id="amount"> </td><td></td>
	</tr>
	
	<tr><td colspan="4" class="EMPTY_H10P"></td></tr>
	
	</table>
	
	<div id="fuelDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Fuel Expense</td>
	</tr>
	
	<tr>
	
	<td class="LABEL_CONT">Vehicle Number*</td>
	
	<td class="INPUT_CONT">
	
	<select class="SELECT_INPUT" name="vehicleNumber" id="vehicleNumber">
	<option value="Select">Select</option>
	<g:each var="vehicle" in="${vehicles }">
	<option value="${vehicle.id }">${vehicle.VehicleNumber }</option>
	</g:each>
	</select>
	
	</td>
	
	</tr>
	
	<tr>
	
	<td>Fuel Price*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="fuelPrice" id="fuelPrice"></td>
	
	</tr>
		
	<tr>
	
	<td>KMs run*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="kmsRun" id="kmsRun"></td>
	
	</tr>
		
	</table>
	</div>
	
	
	
	<div id="loanDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Loan Details</td>
	</tr>
	
	<tr>
	
	<td class="LABEL_CONT">Description*</td>
	
	<td class="INPUT_CONT"><input class="TEXT_INPUT" type="text" name="loanDescription" id="loanDescription"></td>
	
	</tr>
	
	<tr>
	
	<td>From*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="loanFrom" id="loanFrom"></td>
	
	</tr>
	
	<tr>
	
	<td>Planned Return Date(dd/mm/yyyy)*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="loanPlannedReturnDate" id="loanPlannedReturnDate"></td>
	
	</tr>
		
	</table>
	</div>
	
	
	
	
	
	
	<div id="lendDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Lended Details</td>
	</tr>
	
	<tr>
	
	<td class="LABEL_CONT">Description*</td>
	
	<td class="INPUT_CONT"><input class="TEXT_INPUT" type="text" name="lendedDescription" id="lendedDescription"></td>
	
	</tr>
	
	<tr>
	
	<td>To*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="lendedTo" id="lendedTo"></td>
	
	</tr>
	
	
	<tr>
	
	<td>Planned Return Date(dd/mm/yyyy)*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="lendedPlannedReturnDate" id="lendedPlannedReturnDate"></td>
	
	</tr>
		
	</table>
	</div>
	
	
	
	
	
	
	<div id="transfersDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Transfer Account</td>
	</tr>
	
	<tr>
	<td colspan="2">
	<input type="radio" name="transfersAccountNewExisting" id="transfersAccountNew" value="New" onclick="divShowHide('newTransferAccount','ExistingTransferAccount');">
	<label class="RADIO_CONT">New</label>
	<input type="radio" name="transfersAccountNewExisting" id="transfersAccountExisting" value="Existing" checked="checked" onclick="divShowHide('ExistingTransferAccount','newTransferAccount');">
	<label class="RADIO_CONT">Existing</label>
	</td>
	</tr>
	
	<tr><td>
	
	<div id="ExistingTransferAccount">
	<table>
	<tr>
	
	<td class="LABEL_CONT">To account*</td>
	
	<td class="INPUT_CONT">
	<select class="SELECT_INPUT" name="transfersAccount" id="transfersAccount">
	<option value="Select">Select</option>
	<g:each var="account" in="${userAccounts }">
	<option value="${account.id }">${account.accountName }</option>
	</g:each>
	</select>
	</td>
	
	</tr>	
	</table>
	</div>
	
	</td></tr>
	
	<tr><td>
	
	<div id="newTransferAccount" class="DISPLAY_HIDE">
	
	<table>
	
	<tr>
	
	<td class="LABEL_CONT">Account Name*</td>
	
	<td class="INPUT_CONT">
	<input class="TEXT_INPUT" type="text" name="transfersAccountName" id="transfersAccountName">
	</td>
	
	</tr>
	
	<tr>
	
	<td>Account Type*</td>
	
	<td><input class="TEXT_INPUT" type="text" name="transfersAccountType" id="transfersAccountType"></td>
	
	</tr>
	
	</table>
	
	</div>
	
	</td></tr>
	
	</table>
	</div>
	
	
	
	
	
	
	
	<div id="payDebtsDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Debts Details</td>
	</tr>
	
	<tr>
	
	<td class="LABEL_CONT">Loan Account*</td>
	<td class="INPUT_CONT">
	<select class="SELECT_INPUT" name="debtLoanAccount" id="debtLoanAccount">
	<option value="Select">Select</option>
	<g:each var="loan" in="${loans }">
	<option value="${loan.id }">${loan.description }</option>
	</g:each>
	</select>
	</td>
	
	</tr>
		
	</table>
	</div>
	
	
	
	
	<div id="receiveDebtsDiv" class="DISPLAY_HIDE">
	<table>
	<tr>
	<td colspan="2" class="L_ALIGN PAGE_HDR">Debts Details</td>
	</tr>
	
	<tr>
	
	<td class="LABEL_CONT">Lended Account*</td>
	<td class="INPUT_CONT">
	<select class="SELECT_INPUT" name="debtLendAccount" id="debtLendAccount">
	<option value="Select">Select</option>
	<g:each var="lended" in="${lendeds }">
	<option value="${lended.id }">${lended.description }</option>
	</g:each>
	</select>
	</td>
	
	</tr>
		
	</table>
	</div>
	
	
	
	
	<div>
	<table>
	<tr><td colspan="2" class="EMPTY_H10"></td></tr>
	<tr>
	<td><g:link controller="Dashboard" class="LINK_BTN">Back to dashboard</g:link></td>
	<td>
	<button type="submit" class="SUBMIT_BTN">Submit</button>
	</td>
	</tr>
	</table>
	</div>

	</g:form>
	
	</body>
	</html>