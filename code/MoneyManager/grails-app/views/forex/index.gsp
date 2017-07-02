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
	<g:form controller="Forex" action="calculator" onsubmit="return validateInputs('strDate', 'fromCurrency&&toCurrency', 'jsErrorMsg');">
	
	<table>
	<tr>
	<td colspan="4" class="L_ALIGN PAGE_HDR">
	Forex Convertor
	</td>
	</tr>
	
	<tr><td colspan="4">
	<div id="jsErrorMsg" class="JS_VALIDATION_ERRORS" style="display:none;">Please fill * mandatory fields</div>
	</td></tr>
	
	
	<g:if test="${msg != null && msg.length() > 0 && msgFlag == "0"}">
	<tr><td colspan="4"><label class="FAILURE_MSG">${msg }</label></td></tr>
	<tr><td colspan="4" class="EMPTY_H10P"></td></tr>
	</g:if>
	
	<tr>
	
	<td class="LABEL_CONT">Date(DD/MM/yyyy)*</td><td></td>
	<td><input class="TEXT_INPUT" type="text" name="strDate" id="strDate" value="${date }"> </td><td></td>
	
	</tr>
	
	<tr>
	<td>From Currency*</td><td></td>
	<td>
	
	<select class="SELECT_INPUT" name="fromCurrency" id="fromCurrency">
	<option value="Select">Select</option>
	<g:each var="unit" in="${units }">
	<g:if test="${unit.currencyUnit == fromCurr}">
	<option value="${unit.currencyUnit }" selected="selected">${unit.currencyUnit }</option>
	</g:if>
	<g:else>
	<option value="${unit.currencyUnit }">${unit.currencyUnit }</option>
	</g:else>
	</g:each>
	</select>
	
	</td><td></td>
	</tr>
	
	<tr>
	<td>To Currency*</td><td></td>
	<td>
	
	<select class="SELECT_INPUT" name="toCurrency" id="toCurrency">
	<option value="Select">Select</option>
	<g:each var="unit" in="${units }">
	<g:if test="${unit.currencyUnit == toCurr}">
	<option value="${unit.currencyUnit }" selected="selected">${unit.currencyUnit }</option>
	</g:if>
	<g:else>
	<option value="${unit.currencyUnit }">${unit.currencyUnit }</option>
	</g:else>
	</g:each>
	</select>
	
	</td><td></td>
	</tr>
	
	
	<tr><td colspan="4">
	<button type="submit" class="SUBMIT_BTN">Submit</button>
	</td></tr>
	
	<tr><td colspan="4" class="EMPTY_H10"></td></tr>
	
	<tr>
	<td>Online Exchange Rate</td><td></td>
	<td><input class="TEXT_INPUT_READONLY" type="text" name="exchangeRate" id="exchangeRate" readonly="readonly" value="${exchangeRate }"> </td><td></td>
	<td></td>
	</tr>
	
	
	<tr><td colspan="4" class="EMPTY_H10"></td></tr>
	
	<tr><td colspan="4">
	
	


	<table class="FOREX_DATA_TBL">
	<thead>
	<tr>
	<th class="FOREX_DATA_TBL_TH">Denomination</th>
	<th class="FOREX_DATA_TBL_TH">nos</th>
	<th class="FOREX_DATA_TBL_TH">Value</th>
	<th class="FOREX_DATA_TBL_TH">Forex Convertion Rate</th>
	<th class="FOREX_DATA_TBL_TH">Forex Value</th>
	<th class="FOREX_DATA_TBL_TH">Comments</th>
	<th class="FOREX_DATA_TBL_TH">Online Rate</th>
	<th class="FOREX_DATA_TBL_TH">Online Value</th>
	<th class="FOREX_DATA_TBL_TH">Profit</th>
	<th class="FOREX_DATA_TBL_TH">Profit Per Unit</th>
	</tr>
	</thead>
	<tbody>
	<%--<g:set var="valueTotal" value="${0}"></g:set>
	<g:set var="forexRateTotal" value="${0}"></g:set>
	<g:set var="onlineRateTotal" value="${0}"></g:set>
	<g:set var="profitTotal" value="${0}"></g:set>--%>
	
	<g:set var="i" value="${1}"></g:set>
	<g:if test="${denominations != null }">
	<input type="hidden" name="denominationsCount" id="denominationsCount" value="${denominations.size() }"/>
	</g:if>
	<g:each var="denomination" in="${denominations }">
	<%--<g:set var="totBalance" value="${totBalance+account.currentBalance }"></g:set>--%>
	
	<tr>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationUnit_"+i }" id="${"denominationUnit_"+i }" value="${denomination.denominationUnit }" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM YELLOW_BG"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT YELLOW_BG" type="text" autocomplete="off" name="${"denominationNos_"+i }" id="${"denominationNos_"+i }" value="0" onblur="calculateNos('${i }');" onkeypress="return isPositiveIntKey(event)" ondrag="return false" ondrop="return false" oncopy="return false" onpaste="return false"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationValue_"+i }" id="${"denominationValue_"+i }" value="0" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM YELLOW_BG"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT YELLOW_BG" type="text" autocomplete="off" name="${"denominationForexRate_"+i }" id="${"denominationForexRate_"+i }" value="0" onblur="calculateNos('${i }');" onkeypress="return isPositiveFloatKey(event)" ondrag="return false" ondrop="return false" oncopy="return false" onpaste="return false"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationForexValue_"+i }" id="${"denominationForexValue_"+i }" value="0" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_STR">${denomination.comments }</td>
	<td class="FOREX_DATA_TBL_TD_NUM">${exchangeRate }</td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationOnlineValue_"+i }" id="${"denominationOnlineValue_"+i }" value="0" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationProfit_"+i }" id="${"denominationProfit_"+i }" value="0" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="${"denominationProfitPerUnit_"+i }" id="${"denominationProfitPerUnit_"+i }" value="0" readonly="readonly"></td>
	</tr>
	<g:set var="i" value="${i+1 }"></g:set>
	</g:each>
	<tr>
	<td colspan="2">Value Total</td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input type="text" class="FOREX_DATA_TBL_TD_INPUT_TEXT" autocomplete="off" name="valueTotal" id="valueTotal" value="0" readonly="readonly"></td>
	<td>Forex Value Total</td>
	<td id="forexValueTotalCont" class="FOREX_DATA_TBL_TD_NUM"><input type="text" class="FOREX_DATA_TBL_TD_INPUT_TEXT" autocomplete="off" name="forexValueTotal" id="forexValueTotal" value="0" readonly="readonly"></td>
	<td colspan="2">Online Value Total</td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input type="text" class="FOREX_DATA_TBL_TD_INPUT_TEXT" autocomplete="off" name="onlineValueTotal" id="onlineValueTotal" value="0" readonly="readonly"></td>
	<td id="profitTotalCont" class="FOREX_DATA_TBL_TD_NUM"><input type="text" class="FOREX_DATA_TBL_TD_INPUT_TEXT" autocomplete="off" name="profitTotal" id="profitTotal" value="0" readonly="readonly"></td>
	<td></td>
	</tr>
	<%--<tr>
	<td colspan="2" class="DATA_TBL_TD_STR">Total</td>
	<td class="DATA_TBL_TD_NUM">${totBalance }</td>
	<td colspan="2" ></td>
	</tr>--%>
	
	</tbody>
	</table>


	
	</td></tr>
	
	<tr><td colspan="4" class="EMPTY_H10"></td></tr>

	<tr><td colspan="4" class="L_ALIGN PAGE_HDR">Summary</td></tr>
	
	
	<tr><td colspan="4">
	
	


	<table class="FOREX_DATA_TBL">
	<thead>
	<tr>
	<th class="FOREX_DATA_TBL_TH">Online Rate</th>
	<th class="FOREX_DATA_TBL_TH">Value</th>
	<th class="FOREX_DATA_TBL_TH">Forex Value</th>
	<th class="FOREX_DATA_TBL_TH">Online Value</th>
	<th class="FOREX_DATA_TBL_TH">Profit</th>
	</tr>
	</thead>
	<tbody>
	
	<tr>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="summaryOnlineRate" id="summaryOnlineRate" value="${exchangeRate }" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="summaryValue" id="summaryValue" value="0" readonly="readonly"></td>
	<td id="summaryForexValueCont" class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="summaryForexValue" id="summaryForexValue" value="0" readonly="readonly"></td>
	<td class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="summaryOnlineValue" id="summaryOnlineValue" value="0" readonly="readonly"></td>
	<td id="summaryProfitCont" class="FOREX_DATA_TBL_TD_NUM"><input class="FOREX_DATA_TBL_TD_INPUT_TEXT" type="text" autocomplete="off" name="summaryProfit" id="summaryProfit" value="0" readonly="readonly"></td>
	</tr>
	
	</tbody>
	</table>
	
	</td></tr>
	
	<tr><td colspan="4">
	<button type="submit" class="SUBMIT_BTN">Submit</button>
	</td></tr>
	
	</table>
	
	</g:form>
	</body>
</html>
