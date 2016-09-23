//
// 初始畫面
//

$(document).ready(function() {
	
	initFormValidation();
	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#editPersonaTab1Form').data('formValidation').validate();
		if (!$('#editPersonaTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('基本資訊尚未正確完成填寫');
			return;
		}		
		doUpdate();		
	});
	
	$("#portraitFile").fileinput({
		'language': 'zh-TW',
		'showUpload': false,
		'allowedFileExtensions': ['gif', 'png', 'bmp', 'jpg', 'jpeg'],
		'uploadAsync': true,
		'uploadUrl': 'EditPersona/doUploadPortraitFile',
		'uploadExtraData' : function () {
			var extraData = {'projectUuid': $('#projectUuid').val(), 'uuid': $('#uuid').val()};
			return extraData;
		},
	});

	$("#portraitFile").on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();
		$(location).attr('href', 'PersonaList?projectUuid=' + $('#projectUuid').val());				
	});

});

// 表單驗證設定
function initFormValidation() {
	$('#editPersonaTab1Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			personaNo: {validators: {notEmpty: {message: '必填欄位'}}},
			realname: {validators: {notEmpty: {message: '必填欄位'}}},
			category: {validators: {notEmpty: {message: '必填欄位'}}},
			background: {validators: {notEmpty: {message: '必填欄位'}}},
			behavior: {validators: {notEmpty: {message: '必填欄位'}}},
			target: {validators: {notEmpty: {message: '必填欄位'}}},
		}
	});	
	
}
// 新增
function doUpdate() {
	var formData = form2js('editPersonaTab1Form');		
	formData.functionName = 'UPDATE';
		
	$.blockUI({message: '<h4> 人物角色原型資料更新中, 請稍候</h4>'});
	$.ajax({
		url      : 'EditPersona/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {				
				if (hasFile('portraitFile')) {
					$.blockUI({message: '<h4> 上傳個案影像檔案中, 請稍候</h4>'});
					$('#portraitFile').fileinput('upload');
				} else {
					$(location).attr('href', 'PersonaList?projectUuid=' + $('#projectUuid').val());				
				}
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

// 圖檔提供拖拉, 須以此方法確認是否有選取檔案
function hasFile(controlName) {
	return ($('#' + controlName).data('fileinput').filenames.length > 0);
}