//
// 初始畫面
//
$(document).ready(function() {

	$('[data-toggle="confirmation"]').confirmation({
	  onConfirm: function() {
	  	var dataUuid = $(this).attr('id').replace('deleteBtn', '');
	  	doDelete(dataUuid);
		}
	});

	$('button.btn-delete').each(function(){
		$(this).confirmation('hide');
	});
	
});

function doDelete(dataUuid) {
	var formData = {'projectUuid': $('#projectUuid').val(), 'dataUuid': dataUuid};	
	
	$.blockUI({message: '<h4> 個案資料刪除中, 請稍候</h4>'});
	$.ajax({
		url      : 'CaseList/doDelete',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				location.reload();
			} else if (jsonResult.functionStatus == "FAILED") {			
				showErrorMesssage('messageBar', jsonResult.errorMessage);
			}			
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});		
}