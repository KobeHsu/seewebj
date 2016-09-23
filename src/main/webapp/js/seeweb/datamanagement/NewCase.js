//
// 初始畫面
//
var attachmentSerialNoArray = new Array();

$(document).ready(function() {
	
	initFormValidation();
	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#newCaseTab1Form').data('formValidation').validate();
		if (!$('#newCaseTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('messageBar', '基本資訊尚未正確完成填寫');
			return;
		}		
		doCreate();		
	});
	
	$("#portraitFile").fileinput({
		'language': 'zh-TW',
		'showUpload': false,
		'allowedFileExtensions': ['gif', 'png', 'bmp', 'jpg', 'jpeg'],
		'uploadAsync': true,
		'uploadUrl': 'NewCase/doUploadPortraitFile',
		'uploadExtraData' : function () {
			var extraData = {'projectUuid': $('#projectUuid').val(), 'uuid': $('#uuid').val()};
			return extraData;
		},
	});

	$("#portraitFile").on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();

		// 上傳相關檔案
		initAttachmentSerialNoArray();
		if (attachmentSerialNoArray.length > 0) {
			var serialNo = attachmentSerialNoArray.shift();
			$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
			$('#attachmentFile' + serialNo).fileinput('upload');
		} else {
			$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());				
		}
		
	});

});

// 表單驗證設定
function initFormValidation() {
	$('#newCaseTab1Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			caseNo: {validators: {notEmpty: {message: '必填欄位'}}},
			realname: {validators: {notEmpty: {message: '必填欄位'}}},
			sex: {validators: {notEmpty: {message: '必填欄位'}}},
			age: {validators: {numeric: {message: '請輸入數字'}}},
			height: {validators: {numeric: {message: '請輸入數字'}}},	
			weight: {validators: {numeric: {message: '請輸入數字'}}},
			income: {validators: {numeric: {message: '請輸入數字'}}},
			sex: {validators: {notEmpty: {message: '必填欄位'}}},
			businessPhone: {validators: {notEmpty: {message: '必填欄位'}}},
			mobilePhone: {validators: {notEmpty: {message: '必填欄位'}, mobilePhoneNumber: {message: '非正確的手機號碼格式'}}},
			address: {validators: {notEmpty: {message: '必填欄位'}}},
		}
	});	
	
	// 增加進階欄位檢核
	$('#newCaseTab2Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
	});
	
	$('input.NUMERIC').each(function(){
		$('#newCaseTab2Form').formValidation('addField', $(this).attr('id'), {validators: {numeric: {message: '請輸入數字'}}});
	});
	
}
// 新增
function doCreate() {
	var formData1 = form2js('newCaseTab1Form');		
	formData1.functionName = 'INSERT';
	
	var formData2 = form2js('newCaseTab2Form');
	var formData3 = form2js('newCaseTab3Form');
	var wholeFormData = $.extend({}, formData1, formData2, formData3);	
	
	$.blockUI({message: '<h4> 個案資料新增中, 請稍候</h4>'});
	$.ajax({
		url      : 'NewCase/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : wholeFormData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				$('#uuid').val(jsonResult.uuid);
				
				if (hasFile('portraitFile')) {
					$.blockUI({message: '<h4> 上傳個案影像檔案中, 請稍候</h4>'});
					$('#portraitFile').fileinput('upload');
				} else {
					// 上傳相關檔案
					initAttachmentSerialNoArray();
					if (attachmentSerialNoArray.length > 0) {
						var serialNo = attachmentSerialNoArray.shift();
						$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
						$('#attachmentFile' + serialNo).fileinput('upload');
					} else {
						$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());
					}
				}
			} else if (jsonResult.functionStatus == "FAILED") {
				showErrorMesssage('messageBar', jsonResult.errorMessage);			
			}
			
			
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});	
}

function addAttachment() {

	var maxSerialNo = getMaxSerialNo();
	maxSerialNo++;
		
	var descNode = $('<div/>').addClass('row')
	                          .addClass('margin-bottom-xs')
	                          .html('<div class="col-md-1 col-sm-1 col-xs-1 text-right">' + 
	                                '<button type="button" id="removeAttachmentBtn' + maxSerialNo + '" name="removeAttachmentBtn' + maxSerialNo + '" class="btn btn-default" onclick="removeAttachment(' + maxSerialNo + ')"><i class="fa fa-times"></i></button>' +
	                                '</div>' + 
	                                '<div class="col-md-11 col-sm-11 col-xs-11">' + 
	                                '<input type="text" class="form-control" id="attachmentDesc' + maxSerialNo + '" name="attachmentDesc' + maxSerialNo + '" maxlength="128" placeholder="請輸入檔案說明" />' +
	                                '</div>'
	                               );
	var fileNode = $('<div/>').addClass('row')
	                          .addClass('margin-bottom-lg')
	                          .html('<div class="col-md-1 col-sm-1 col-xs-1">' + 
	                                '</div>' + 
	                                '<div class="col-md-11 col-sm-11 col-xs-11">' + 
	                                '<input type="file" class="file" id="attachmentFile' + maxSerialNo + '" name="attachmentFile" />' +
	                                '</div>'
	                               );	                               	                               
	$(descNode).insertBefore('#dummyDiv');	
	$(fileNode).insertAfter(descNode);	
	
	$("#attachmentFile" + maxSerialNo).fileinput({
		'language': 'zh-TW',
		'showPreview': false,
		'showUpload': false,
		'uploadAsync': true,
		'uploadUrl': 'NewCase/doUploadAttachment',
		'uploadExtraData': function () {
			var serialNo = parseInt($(this).attr('id').replace('attachmentFile', ''));			
			var extraData = {'projectUuid': $('#projectUuid').val(), 
				               'uuid': $('#uuid').val(),
				               'serialNo': serialNo,
				               'attachmentDesc': $('#attachmentDesc' + serialNo).val(),
				               };
			return extraData;
		},
	});
	
	$("#attachmentFile" + maxSerialNo).on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();

		// 上傳下一個檔案
		if (attachmentSerialNoArray.length > 0) {
			var serialNo = attachmentSerialNoArray.shift();
			$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
			$('#attachmentFile' + serialNo).fileinput('upload');
		} else {
			$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());				
		}		
	});
	
}

function removeAttachment(serialNo) {
	$("#attachmentDesc" + serialNo).parent().parent().remove();

	$("#attachmentFile" + serialNo).fileinput('destroy');
	$("#attachmentFile" + serialNo).parent().parent().remove();
}

function getMaxSerialNo() {
	var maxSerialNo = 0;	
	$('.file').each(function(idx, item) {
		var id = $(item).attr('id');
		if (id.indexOf('attachmentFile') == 0) {
			var serialNo = parseInt(id.replace('attachmentFile', ''));		
			if (maxSerialNo < serialNo) {
				maxSerialNo = serialNo;
			}		
		}
	});
	return maxSerialNo;
}

function initAttachmentSerialNoArray() {
	attachmentSerialNoArray = new Array();
	$('.file').each(function(idx, item) {
		if ($(item).val()) {
			var id = $(item).attr('id');
			if (id.indexOf('attachmentFile') == 0) {
				var serialNo = id.replace('attachmentFile', '');
				attachmentSerialNoArray.push(serialNo);
			}
		}
	});	
}
// 圖檔提供拖拉, 須以此方法確認是否有選取檔案
function hasFile(controlName) {
	return ($('#' + controlName).data('fileinput').filenames.length > 0);
}