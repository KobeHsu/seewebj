//
// 初始畫面
//
$(document).ready(function() {
		
	$('#loginBtn').click(function(){
		doLogin();
	});		
	
	$('#privacyStatementBtn').click(function(){
		$('#privacyStatementModal').modal('show');
	});		
	
});

function doLogin() {	
	if ($('#userAccount').val() == '' || $('#userPassword').val() == '') {
		showErrorMessage('loginBtn', '請先輸入帳號及密碼');
		return;
	}	
	
	var formData = { account  : $('#userAccount').val(),
		               password : $.md5($('#userPassword').val())
	               };
	$.blockUI({message: '<h4> 登入中, 請稍候</h4>'});
	$.ajax({
		url      : 'doLogin',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
					
				if (jsonResult.accountStatus == 'ACTIVE') {
					if (jsonResult.accountAgreementRequired == 'Y') {
						$(location).attr('href', 'SignAgreement');
					} else {
						$(location).attr('href', 'Index');
					}
				}
			} else if (jsonResult.functionStatus == "FAILED") {
				showErrorMessage('loginBtn', jsonResult.errorMessage)
			}
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});
}

function showErrorMessage(controlName, errorMessage) {
	if ($('#'+controlName).data('bs.popover')) {
		$('#'+controlName).popover('destroy');
	}
	$('#'+controlName).popover({
						content: errorMessage,
						container: 'body',
						tigger: 'manual', 
						placement: 'bottom'
	});
	$('#'+controlName).popover('show');
}

