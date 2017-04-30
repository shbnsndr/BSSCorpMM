// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}


function getAccountBalance(){

	var account = $("#account").val();
	if(account != 'Select'){
		var url = "/MoneyManager/Account/getBalance/"+account;
		$.ajax({
			type: "GET",
			url: url,
			success: function(data){
				$("#accountBalance").val(data['currentBalance']);
			}
		});
	}
	
	
}

function findExpenseType(){
	var expenseType = $("#expenseType").val();

	if(expenseType == "Fuel"){
		divShowHide('fuelDiv','loanDiv&&lendDiv&&transfersDiv&&payDebtsDiv&&receiveDebtsDiv');
	}else if(expenseType == "Loan"){
		divShowHide('loanDiv','fuelDiv&&lendDiv&&transfersDiv&&payDebtsDiv&&receiveDebtsDiv');
	}else if(expenseType == "Lend"){
		divShowHide('lendDiv','fuelDiv&&loanDiv&&transfersDiv&&payDebtsDiv&&receiveDebtsDiv');
	}else if(expenseType == "Transfer Within Accounts"){
		divShowHide('transfersDiv','fuelDiv&&loanDiv&&lendDiv&&payDebtsDiv&&receiveDebtsDiv');
	}else if(expenseType == "Pay Debts"){
		divShowHide('payDebtsDiv','fuelDiv&&loanDiv&&lendDiv&&transfersDiv&&receiveDebtsDiv');
	}else if(expenseType == "Receive Debts"){
		divShowHide('receiveDebtsDiv','fuelDiv&&loanDiv&&lendDiv&&transfersDiv&&payDebtsDiv');
	}else{
		divShowHide('','fuelDiv&&loanDiv&&lendDiv&&transfersDiv&&payDebtsDiv&&receiveDebtsDiv');
	}
}

function divShowHide(showDiv, hideDivs){

	if(showDiv.length > 0){
		//Show div
		$('#'+showDiv).addClass("DISPLAY_SHOW");
		$('#'+showDiv).removeClass("DISPLAY_HIDE");
	}
	
	//Hide divs
	var hideDivArray = hideDivs.split('&&');
	for(var i=0;i<hideDivArray.length;i++){
		var hideDiv=hideDivArray[i];
		$('#'+hideDiv).addClass("DISPLAY_HIDE");
		$('#'+hideDiv).removeClass("DISPLAY_SHOW");
	}
}

function validateTextFields(textFields){
	var cnt = 0;

	if(textFields.length>0){
		var textFieldArray = textFields.split('&&');
		for(var i=0;i<textFieldArray.length;i++){
			var textField = textFieldArray[i];
			var val = $('#'+textField).val();
			if(val == undefined || val.length == 0 || val.trim().length == 0){
				cnt++;
				$('#'+textField).addClass('EMPTY_INPUT');
			}else{
				$('#'+textField).removeClass('EMPTY_INPUT');
			}
		}
	}
	
	return cnt;
}

function validateComboFields(comboFields){
	var cnt = 0;

	if(comboFields.length>0){
		var comboFieldArray = comboFields.split('&&');
		for(var i=0;i<comboFieldArray.length;i++){
			var comboField = comboFieldArray[i];
			var val = $('#'+comboField).val();
			if(val == undefined || val == "Select"){
				cnt++;
				$('#'+comboField).addClass('EMPTY_INPUT');
			}else{
				$('#'+comboField).removeClass('EMPTY_INPUT');
			}
		}
	}
	
	return cnt;
}

function validateInputs(textFields, comboFields, errorMsg){
	var textErrors=validateTextFields(textFields);
	var comboErrors=validateComboFields(comboFields);

	if((textErrors+comboErrors) > 0){
		$('#'+errorMsg).show();
		return false;
	}else{
		$('#'+errorMsg).hide();
		return true;
	}
	
}

function validateExpense(){
	var textFields = 'expenseName&&accountBalance&&amount';
	var comboFields = 'expenseType&&account';

	var expenseType = $('#expenseType').val();
	var account = $('#account').val();

	if(expenseType == "Fuel"){
		textFields += '&&fuelPrice&&kmsRun';
	}else if(expenseType == "Loan"){
		textFields += '&&loanDescription&&loanFrom&&loanPlannedReturnDate';
	}else if(expenseType == "Lend"){
		textFields += '&&lendedDescription&&lendedTo&&lendedPlannedReturnDate';
	}else if(expenseType == "Transfer Within Accounts"){

		var transfersAccountNewExisting = $('input[name=transfersAccountNewExisting]:checked').val()
		
		if(transfersAccountNewExisting == "New"){
			textFields += '&&transfersAccountName&&transfersAccountType';
		} else{
			comboFields += '&&transfersAccount';
		}
	}else if(expenseType == "Pay Debts"){
		comboFields += '&&debtLoanAccount';
	}else if(expenseType == "Receive Debts"){
		comboFields += '&&debtLendAccount';
	}

	var msgDiv='jsErrorMsg';
	
	return validateInputs(textFields, comboFields, msgDiv);
}

function calculateNos(i){
	var unit = parseFloat($("#denominationUnit_"+i).val());
	var nos = parseFloat($("#denominationNos_"+i).val());
	var value = (unit*nos).toFixed(5);
	$("#denominationValue_"+i).val(value);
	var forexRate = parseFloat($("#denominationForexRate_"+i).val());
	var forexValue = (value*forexRate).toFixed(5);
	$("#denominationForexValue_"+i).val(forexValue);
	var exchangeRate = parseFloat($("#exchangeRate").val());
	var onlineValue = (value*exchangeRate).toFixed(5);
	$("#denominationOnlineValue_"+i).val(onlineValue);
	var profit = (forexValue - onlineValue).toFixed(5);
	
	if(forexRate != 0){
		$("#denominationProfit_"+i).val(profit);
		var profitPerUnit = (forexRate-exchangeRate).toFixed(5);
		$("#denominationProfitPerUnit_"+i).val(profitPerUnit);
		
		if(profitPerUnit < 0){
			$("#denominationProfitPerUnit_"+i).addClass("RED_TEXT");
			$("#denominationProfitPerUnit_"+i).removeClass("GREEN_TEXT");
			$("#denominationProfit_"+i).addClass("RED_TEXT");
			$("#denominationProfit_"+i).removeClass("GREEN_TEXT");
		}else{
			$("#denominationProfitPerUnit_"+i).addClass("GREEN_TEXT");
			$("#denominationProfitPerUnit_"+i).removeClass("RED_TEXT");
			$("#denominationProfit_"+i).addClass("GREEN_TEXT");
			$("#denominationProfit_"+i).removeClass("RED_TEXT");
		}
	}
	
	
	var size = parseInt($("#denominationsCount").val());
	
	var valueTotal=0, forexValueTotal=0, onlineValueTotal=0, profitTotal=0;
	
	for(var j=1;j<=size;j++){
		valueTotal += parseFloat($("#denominationValue_"+j).val());
		forexValueTotal += parseFloat($("#denominationForexValue_"+j).val());
		onlineValueTotal += parseFloat($("#denominationOnlineValue_"+j).val());
		profitTotal += parseFloat($("#denominationProfit_"+j).val());
	}
	
	$("#valueTotal").val(valueTotal);
	$("#forexValueTotal").val(forexValueTotal);
	$("#onlineValueTotal").val(onlineValueTotal);
	$("#profitTotal").val(profitTotal);
	
	$("#summaryValue").val(valueTotal);
	$("#summaryForexValue").val(forexValueTotal);
	$("#summaryOnlineValue").val(onlineValueTotal);
	$("#summaryProfit").val(profitTotal);
	
	if(profitTotal <0 && forexRate != 0){
		$("#profitTotalCont").addClass("RED_BG");
		$("#profitTotalCont").removeClass("GREEN_BG");
		$("#forexValueTotalCont").addClass("RED_BG");
		$("#forexValueTotalCont").removeClass("GREEN_BG");
		$("#summaryForexValueCont").addClass("RED_BG");
		$("#summaryForexValueCont").removeClass("GREEN_BG");
		$("#summaryProfitCont").addClass("RED_BG");
		$("#summaryProfitCont").removeClass("GREEN_BG");
		
		$("#profitTotal").addClass("RED_BG");
		$("#profitTotal").removeClass("GREEN_BG");
		$("#forexValueTotal").addClass("RED_BG");
		$("#forexValueTotal").removeClass("GREEN_BG");
		$("#summaryForexValue").addClass("RED_BG");
		$("#summaryForexValue").removeClass("GREEN_BG");
		$("#summaryProfit").addClass("RED_BG");
		$("#summaryProfit").removeClass("GREEN_BG");
	}else{
		$("#profitTotalCont").addClass("GREEN_BG");
		$("#profitTotalCont").removeClass("RED_BG");
		$("#forexValueTotalCont").addClass("GREEN_BG");
		$("#forexValueTotalCont").removeClass("RED_BG");
		$("#summaryForexValueCont").addClass("GREEN_BG");
		$("#summaryForexValueCont").removeClass("RED_BG");
		$("#summaryProfitCont").addClass("GREEN_BG");
		$("#summaryProfitCont").removeClass("RED_BG");
		
		$("#profitTotal").addClass("GREEN_BG");
		$("#profitTotal").removeClass("RED_BG");
		$("#forexValueTotal").addClass("GREEN_BG");
		$("#forexValueTotal").removeClass("RED_BG");
		$("#summaryForexValue").addClass("GREEN_BG");
		$("#summaryForexValue").removeClass("RED_BG");
		$("#summaryProfit").addClass("GREEN_BG");
		$("#summaryProfit").removeClass("RED_BG");
	}
	
}

function isPositiveIntKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function isPositiveFloatKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}