//
// 初始畫面
//
var attachmentFileSerialNoArray = new Array();
var attachmentDescSerialNoArray = new Array();

$(document).ready(function() {
	
	initFormValidation();
	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#editCaseTab1Form').data('formValidation').validate();
		if (!$('#editCaseTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('messageBar', '基本資訊尚未正確完成填寫');
			return;
		}
		
		doUpdate();		
	});
	
	$("#portraitFile").fileinput({
		'language': 'zh-TW',
		'showUpload': false,
		'allowedFileExtensions': ['gif', 'png', 'bmp', 'jpg', 'jpeg'],
		'uploadAsync': true,
		'uploadUrl': 'EditCase/doUploadPortraitFile',
		'uploadExtraData' : function () {
			var extraData = {'projectUuid': $('#projectUuid').val(), 'uuid': $('#uuid').val()};
			return extraData;
		},
	});
	
	$("#portraitFile").on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();

		// 上傳相關檔案
		initAttachmentSerialNoArray();
		if (attachmentFileSerialNoArray.length > 0) {
			var serialNo = attachmentFileSerialNoArray.shift();
			$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
			$('#attachmentFile' + serialNo).fileinput('upload');
		} else if (attachmentDescSerialNoArray.length > 0) {
			// 單純更新說明內容 
			var serialNo = attachmentDescSerialNoArray.shift();						
			updateAttachmentDesc(serialNo);						
		} else {
			$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());				
		}
	});
	
	$('.file').each(function(idx, item) {
		var id = $(item).attr('id');
		if (id.indexOf('attachmentFile') == 0) {
			initFileInput(id, 'update');
		}
	});
	
	$('[data-toggle="confirmation"]').confirmation({
	  onConfirm: function() {
	  	var serialNO = $(this).attr('id').replace('removeAttachmentBtn', '');
	  	removeUploadedAttachment(serialNO);
		}
	});

	$('button.btn-delete').each(function(){
		$(this).confirmation('hide');
	});
	
});

// 表單驗證設定
function initFormValidation() {
	$('#editCaseTab1Form').formValidation({
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
	$('#editCaseTab2Form').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
	});
	
	$('input.NUMERIC').each(function(){
		$('#editCaseTab2Form').formValidation('addField', $(this).attr('id'), {validators: {numeric: {message: '請輸入數字'}}});
	});
	
}
// 新增
function doUpdate() {
	var formData1 = form2js('editCaseTab1Form');		
	formData1.functionName = 'UPDATE';
	
	var formData2 = form2js('editCaseTab2Form');
	var wholeFormData = $.extend({}, formData1, formData2);	
	
	$.blockUI({message: '<h4> 個案資料更新中, 請稍候</h4>'});
	$.ajax({
		url      : 'EditCase/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : wholeFormData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				if (hasFile('portraitFile')) {
					$.blockUI({message: '<h4> 上傳個案影像檔案中, 請稍候</h4>'});
					$('#portraitFile').fileinput('upload');
				} else {
					// 上傳相關檔案
					initAttachmentSerialNoArray();
					if (attachmentFileSerialNoArray.length > 0) {
						var serialNo = attachmentFileSerialNoArray.shift();
						$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
						$('#attachmentFile' + serialNo).fileinput('upload');
					} else if (attachmentDescSerialNoArray.length > 0) {
						// 單純更新說明內容 
						var serialNo = attachmentDescSerialNoArray.shift();						
						updateAttachmentDesc(serialNo);
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

function removeAttachment(serialNo) {	
	$("#removeAttachmentBtn" + serialNo).parent().parent().remove();	
	$("#attachmentDesc" + serialNo).parent().parent().remove();

	$("#attachmentFile" + serialNo).fileinput('destroy');
	$("#attachmentFile" + serialNo).parent().parent().remove();
}

function removeUploadedAttachment(serialNo) {
	var formData = {'projectUuid': $('#projectUuid').val(),
		              'dataUuid': $('#uuid').val(),
		              'attachmentUuid': $('#attachmentUuid' + serialNo).val(),
		             };	
		             
	$.blockUI({message: '<h4> 檔案刪除中, 請稍候</h4>'});
	$.ajax({
		url      : 'EditCase/doDeleteAttachment',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {	
				// 移除畫面顯示
				removeAttachment(serialNo);				
			} else if (jsonResult.functionStatus == "FAILED") {			
				// TODO: 顯示錯誤訊息
			}			
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});		
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
	
	initFileInput('attachmentFile' + maxSerialNo, 'insert');	
}

function initFileInput(controlName, insertOrUpdate) {	
	var uploadUrl = (insertOrUpdate == 'insert') ? 'EditCase/doUploadAttachment' : 'EditCase/doUpdateAttachment';
	$('#' + controlName).fileinput({
		'language': 'zh-TW',
		'showPreview': false,
		'showUpload': false,
		'uploadAsync': true,
		'uploadUrl': uploadUrl,
		'uploadExtraData': function () {
			var serialNo = parseInt(controlName.replace('attachmentFile', ''));			
			var extraData = {'projectUuid': $('#projectUuid').val(), 
						           'uuid': $('#uuid').val(),
						           'attachmentUuid': $('#attachmentUuid' + serialNo).val(),
						           'serialNo': serialNo,
						           'attachmentDesc': $('#attachmentDesc' + serialNo).val(),
						          };
			return extraData;
		},
	});
	
	$('#' + controlName).on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();

		// 上傳下一個檔案
		if (attachmentFileSerialNoArray.length > 0) {
			var serialNo = attachmentFileSerialNoArray.shift();
			$.blockUI({message: '<h4> 上傳檔案' + $('#attachmentFile' + serialNo).val() + ', 請稍候</h4>'});
			$('#attachmentFile' + serialNo).fileinput('upload');
		} else if (attachmentDescSerialNoArray.length > 0) {
			// 單純更新說明內容 
			var serialNo = attachmentDescSerialNoArray.shift();						
			updateAttachmentDesc(serialNo);						
		} else {
			$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());				
		}		
	});				
}

function initAttachmentSerialNoArray() {
	attachmentFileSerialNoArray = new Array();
	$('.file').each(function(idx, item) {
		if ($(item).val()) {
			var id = $(item).attr('id');
			if (id.indexOf('attachmentFile') == 0) {
				var serialNo = id.replace('attachmentFile', '');
				attachmentFileSerialNoArray.push(serialNo);
			}
		} else {
			// 未更新檔案
			var id = $(item).attr('id');
			if (id.indexOf('attachmentFile') == 0) {
				var serialNo = id.replace('attachmentFile', '');
				if ($("#attachmentUuid" + serialNo).length > 0) {
					attachmentDescSerialNoArray.push(serialNo);				
				}	
			}
		}
	});	
}

function updateAttachmentDesc(serialNo) {
	var formData = {'projectUuid': $('#projectUuid').val(),
		              'dataUuid': $('#uuid').val(),
		              'attachmentUuid': $('#attachmentUuid' + serialNo).val(),
		              'attachmentDesc': $('#attachmentDesc' + serialNo).val(),
		             };	

	$.blockUI({message: '<h4> 更新說明中, 請稍候</h4>'});
	$.ajax({
		url      : 'EditCase/doUpdateAttachmentDescription',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {	
				// 更新下一個
			if (attachmentDescSerialNoArray.length > 0) {
				var nextSerialNo = attachmentDescSerialNoArray.shift();						
				updateAttachmentDesc(nextSerialNo);						
			} else {
				$(location).attr('href', 'CaseList?projectUuid=' + $('#projectUuid').val());
			}
		} else if (jsonResult.functionStatus == "FAILED") {			
				// TODO: 顯示錯誤訊息
			}			
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});		

}
// 圖檔提供拖拉, 須以此方法確認是否有選取檔案
function hasFile(controlName) {
	return ($('#' + controlName).data('fileinput').filenames.length > 0);
}