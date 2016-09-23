//
// 初始畫面
//
$(document).ready(function() {
	
	initFormValidation();

	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#newForumThreadTab1Form').data('formValidation').validate();
		if (!$('#newForumThreadTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('討論主題及內容尚未正確完成填寫');
			return;
		}		
		doCreate();		
	});
	
});

// 表單驗證設定
function initFormValidation() {
	$('#newForumThreadTab1Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			title: {validators: {notEmpty: {message: '必填欄位'}}},
			content: {validators: {notEmpty: {message: '必填欄位'}}},
		}
	});			
}
// 新增
function doCreate() {
	var formData = form2js('newForumThreadTab1Form');		
	formData.functionName = 'INSERT';
	
	$.blockUI({message: '<h4> 討論區主題資料新增中, 請稍候</h4>'});
	$.ajax({
		url      : 'NewForumThread/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				$('#uuid').val(jsonResult.uuid);
				$(location).attr('href', 'ViewForum?projectUuid=' + $('#projectUuid').val() + '&dataUuid=' + $('#forumUuid').val());
			} else if (jsonResult.functionStatus == "FAILED") {
				showErrorMesssage(jsonResult.errorMessage);			
			}
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});	
}

function showErrorMesssage(message) {
	$('#messageBar').html('<div class="alert alert-danger alert-dismissible" role="alert">' + 
			                  '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' + 
			                  '<strong>' + message + '</strong>' +
			                  '</div>');				
}

