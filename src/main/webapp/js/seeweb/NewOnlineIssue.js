//
// 初始畫面
//
var figureSerialNoArray = new Array();

$(document).ready(function() {
	
	initFormValidation();

	$('#confirmBtn').click(function(){
		$('.nav-tabs a[href="#tab1"]').tab('show');
		$('#newOnlineIssueTab1Form').data('formValidation').validate();
		if (!$('#newOnlineIssueTab1Form').data('formValidation').isValid()) {
			showErrorMesssage('主題及內容尚未正確完成填寫');
			return;
		}		
		doCreate();		
	});
	
});

// 表單驗證設定
function initFormValidation() {
	$('#newOnlineIssueTab1Form').formValidation({
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
	var formData = form2js('newOnlineIssueTab1Form');		
	formData.functionName = 'INSERT';
	
	$.blockUI({message: '<h4> 問題回報資料新增中, 請稍候</h4>'});
	$.ajax({
		url      : 'NewOnlineIssue/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			if (jsonResult.functionStatus == "SUCCESS") {
				$('#uuid').val(jsonResult.uuid);
				initFigureSerialNoArray();
				if (figureSerialNoArray.length > 0) {
					var serialNo = figureSerialNoArray.shift();
					$.blockUI({message: '<h4> 上傳檔案' + $('#figureFile' + serialNo).data('fileinput').filenames[0] + ', 請稍候</h4>'});
					$('#figureFile' + serialNo).fileinput('upload');
				} else {
					$(location).attr('href', 'NewOnlineIssueSuccess?projectUuid=' + $('#projectUuid').val());
				}				
			} else if (jsonResult.functionStatus == "FAILED") {
				if (jsonResult.errorMessage == 'AA-0004') {
					$(location).attr('href', '');
				} else {
					showErrorMesssage(jsonResult.errorMessage);			
				}
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

function initFigureSerialNoArray() {
	figureSerialNoArray = new Array();
	$('.file').each(function(idx, item) {
		if ($(item).val() ||  $(item).data('fileinput').filenames.length > 0) {
			var id = $(item).attr('id');
			if (id.indexOf('figureFile') == 0) {
				var serialNo = id.replace('figureFile', '');
				figureSerialNoArray.push(serialNo);
			}
		}
	});	
}

function getMaxSerialNoOfFigure() {
	var maxSerialNo = 0;	
	$('.file').each(function(idx, item) {
		var id = $(item).attr('id');
		if (id.indexOf('figureFile') == 0) {
			var serialNo = parseInt(id.replace('figureFile', ''));		
			if (maxSerialNo < serialNo) {
				maxSerialNo = serialNo;
			}		
		}
	});
	return maxSerialNo;
}

function addFigure() {
	var maxSerialNo = getMaxSerialNoOfFigure();
	maxSerialNo++;
		
	var fileNode = $('<div/>').addClass('row')
	                          .addClass('margin-bottom-lg')
	                          .html('<div class="col-md-1 col-sm-1 col-xs-1 text-right">' + 
	                                '<button type="button" id="removeFigureBtn' + maxSerialNo + '" name="removeFigureBtn' + maxSerialNo + '" class="btn btn-default" onclick="removeFigure(' + maxSerialNo + ')"><i class="fa fa-times"></i></button>' +
	                                '</div>' + 
	                                '<div class="col-md-6">' + 
	                                '<input type="file" class="file" id="figureFile' + maxSerialNo + '" name="figureFile" />' +
	                                '</div>'
	                               );	                               	                               
	$(fileNode).insertBefore('#dummyFigureDiv');	
	
	$("#figureFile" + maxSerialNo).fileinput({
		'language': 'zh-TW',
		'showUpload': false,
		'allowedFileExtensions': ['gif', 'png', 'bmp', 'jpg', 'jpeg'],
		'uploadAsync': true,
		'uploadUrl': 'NewOnlineIssue/doUploadFigure',
		'uploadExtraData': function () {
			var serialNo = parseInt($(this).attr('id').replace('figureFile', ''));			
			var extraData = {'projectUuid': $('#projectUuid').val(), 'uuid': $('#uuid').val(), 'serialNo': serialNo};
			return extraData;
		},
	});
	
	$("#figureFile" + maxSerialNo).on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();

		// 上傳下一個檔案
		if (figureSerialNoArray.length > 0) {
			var serialNo = figureSerialNoArray.shift();
			$.blockUI({message: '<h4> 上傳檔案' + $('#figureFile' + serialNo).data('fileinput').filenames[0] + ', 請稍候</h4>'});
			$('#figureFile' + serialNo).fileinput('upload');
		} else {
			$(location).attr('href', 'NewOnlineIssueSuccess?projectUuid=' + $('#projectUuid').val());
		}		
	});	
}

function removeFigure(serialNo) {
	$("#figureFile" + serialNo).fileinput('destroy');
	$("#figureFile" + serialNo).parent().parent().remove();
}
