//
// 初始畫面
//
$(document).ready(function() {
	
	initFormValidation();
	loadForumStatusList('status');
	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#newForumTab1Form').data('formValidation').validate();
		if (!$('#newForumTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('基本資訊尚未正確完成填寫');
			return;
		}		
		doCreate();		
	});
	
});

// 表單驗證設定
function initFormValidation() {
	$('#newForumTab1Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			title: {validators: {notEmpty: {message: '必填欄位'}}},
			summary: {validators: {notEmpty: {message: '必填欄位'}}},
			status: {validators: {notEmpty: {message: '必填欄位'}}},
			serialNo: {validators: {numeric: {message: '請輸入數字'}}},
		}
	});			
}
// 新增
function doCreate() {
	var formData = form2js('newForumTab1Form');		
	formData.functionName = 'INSERT';
	
	$.blockUI({message: '<h4> 討論區資料新增中, 請稍候</h4>'});
	$.ajax({
		url      : 'NewForum/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				$('#uuid').val(jsonResult.uuid);
				$(location).attr('href', 'ForumList?projectUuid=' + $('#projectUuid').val());
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

function loadForumStatusList(formControlName) {
	$.ajax({
		url      : 'Common/getForumStatus',
		type     : 'POST',
		dataType : 'json',
		success  : function(resultList) {
			$.each(resultList, function(idx, item) {
				if (idx == 0) {
					$('#' + formControlName).append('<option value="' + item.code + '" selected="selected">' + item.desc + '</option>');
				} else {
					$('#' + formControlName).append('<option value="' + item.code + '">' + item.desc + '</option>');
				}
			});
		},
		error : function(xhrInstance, status, xhrException) {
		}
	});
}