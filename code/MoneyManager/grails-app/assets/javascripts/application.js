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