//
// 初始畫面
//
$(document).ready(function() {
		
	initFunctionBtnGroupStatus();
	initMessageBar('messageBar');
	
	initQueryResultList();	
	initFormValidation();

	$("#formFile").fileinput({
		'language': 'zh-TW',
		'showPreview': false,
		'showUpload': false,
		'uploadAsync': true,
		'uploadUrl': 'UsibilityTestFormManagement/doUploadFormTemplateFile',
		'uploadExtraData': function () {			
			var extraData = {'projectUuid': $('#projectUuid').val(), 
				               'uuid': $('#uuid').val(),
				               };
			return extraData;
		},
	});

	$("#formFile").on('fileuploaded', function(event, data, previewId, index) {
		$.unblockUI();
		var functionName = $('#functionName').val();
		showSaveSuccess(functionName, 'messageBar');
		loadExistingFile(data.filenames[0]);
						
		var queryResultList = $('#queryResultList').dataTable();
		queryResultList.fnDraw();				
	});
	
	$('#btnSave').click(function(){
		doSave();
	});		
	$('#btnQuery').click(function(){
		doQuery();
	});		
	$('#btnCancel').click(function(){
		doClear();
	});		
	$('#btnDelete').click(function(){
		doDelete();
	});		
	
	$('#btnViewFile').click(function(){
		doViewFile();
	});		
	
	doQuery();
});

function onNew() {
	$('#viewFormFileBtn').prop('disabled', true);
	$('#editForm1').formValidation('addField', 'formFile', {validators: {notEmpty: {message: '請選取表單範本檔案'}}});
}

// 初始查詢結果表格
function initQueryResultList() {
	$('#queryResultList').dataTable({
		"processing": true,
		"serverSide": true,
		"iDeferLoading": 0,
		"sDom": 't',
		"aoColumns": [
				{"mData": "uuid"},
				{"mData": "name"},
				{"mData": "serialNo"},
				{"mData": "description"},
				{"mData": "fileName"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "functionName", "value": $('#functionName').val()},
				{"name": "projectUuid", "value": $('#projectUuid').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'UsibilityTestFormManagement/doQuery',
				type     : 'POST',
				dataType : 'json',
				data     : aoData,
				success  : function(jsonResult, status) {
					$.unblockUI();
					fnCallback(jsonResult);
				},
				error : function(xhrInstance, status, xhrException) {
					$.unblockUI();
					$('#functionName').val('');
				}				
			});
		},
		"oLanguage": {"sUrl": "/seewebj/res/language/datatable_zh_tw.txt"},
		"bFilter": false,
		"aaSorting": [],
		"aoColumnDefs": [
				{"bSortable": false, "aTargets": [0, 1, 2, 3, 4]},
				{"bVisible": false, "aTargets": [0]},
				{"width": "0%", targets: 0},
				{"width": "30%", targets: 1},
				{"width": "10%", targets: 2},
				{"width": "30%", targets: 3},
				{"width": "30%", targets: 4},
		],
	});	
	
	
	$('#queryResultList tbody').on('dblclick', 'tr', function() {		
		if ($(this).hasClass('selected')) {
			;
		} else {
			var queryResultList = $('#queryResultList').dataTable();
			queryResultList.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}				
		clearTextSelection();
		var queryResultList = $('#queryResultList').dataTable();
		var aPos = queryResultList.fnGetPosition(this);
		var aData = queryResultList.fnGetData(aPos[0]);
		
		var uuid = aData[aPos].uuid;
		var name = aData[aPos].name;
		var serialNo = aData[aPos].serialNo;
		var description = aData[aPos].description;
		var fileName = aData[aPos].fileName;

		$('#uuid').val(uuid);
		$('#name').val(name);
		$('#serialNo').val(serialNo);
		$('#description').val(description);
		loadExistingFile(fileName);		
		
		$('#formFile').fileinput('reset');
		
		toggleUpdateMode();
		$('#editForm1').formValidation('removeField', 'formFile');
	});

}
// 存檔
function doSave() {
	$('#editForm1').data('formValidation').validate();
	if (!$('#editForm1').data('formValidation').isValid()) {
		showInputError('messageBar');
		return;
	}
	var formData = form2js('editForm1');
	
	blockUI();
	$.ajax({
		url      : 'UsibilityTestFormManagement/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			var functionName = $('#functionName').val();
			if (jsonResult.functionStatus == "SUCCESS") {				
				if (functionName == FUNCTION_NAME_INSERT) {
					$('#uuid').val(jsonResult.uuid);
					$('#editForm1').formValidation('removeField', 'formFile');			
				}
				// 上傳檔案
				if ($('#formFile').data('fileinput').filenames.length > 0) {
					$.blockUI({message: '<h4> 上傳檔案' + $('#formFile').val() + ', 請稍候</h4>'});
					$('#formFile').fileinput('upload');
				} else {
					showSaveSuccess(functionName, 'messageBar');
					var queryResultList = $('#queryResultList').dataTable();
					queryResultList.fnDraw();				
				}
			} else if (jsonResult.functionStatus == "FAILED") {
				showSaveFailed(functionName, 'messageBar', jsonResult.errorMessage);
			}
			$('#editForm1').data('formValidation').resetForm();
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
			$('#editForm1').data('formValidation').resetForm();
		}
	});
}

function initFormValidation() {
	// 表單驗證設定
	$('#editForm1').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			name: {validators: {notEmpty: {message: '必填欄位'}}},
			serialNo: {validators: {notEmpty: {message: '必填欄位'}, numeric: {message: '請輸入數字'}}},
		}
	});	
}
// 執行查詢
function doQuery() {
	doClear();
	var queryResultList = $('#queryResultList').dataTable();
	queryResultList.fnDraw();
}
// 刪除
function doDelete() {
	var formData = form2js('editForm1');
	blockUI();
	$.ajax({
		url      : 'UsibilityTestFormManagement/doDelete',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			var functionName = $('#functionName').val();
			if (jsonResult.functionStatus == "SUCCESS") {
				showDeleteSuccess('messageBar');
				doClear();
				var queryResultList = $('#queryResultList').dataTable();
				queryResultList.fnDraw();	
			} else if (jsonResult.functionStatus == "FAILED") {
				showDeleteFailed('messageBar', jsonResult.errorMessage);
			}
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});
}

// 清除表單資料
function doClear() {
	// Tab#1
	$('#editForm1')[0].reset();	
	$('#editForm1').data('formValidation').resetForm();
	// $('#uuid').val('');
	$('#functionName').val('');
	
	toggleInitMode();
	initMessageBar('messageBar');	
	
	$('#viewFormFileBtn').prop('disabled', true);
	if ($('#existingFile').length > 0) {
		$('#existingFile').remove();
	}	
}

function loadExistingFile(fileName) {
	if ($('#existingFile').length > 0) {
		$('#existingFile').remove();
	}	
	
	var existingFileBtn = $('<button/>').attr('id', 'existingFile')
	                                    .addClass('btn')
	                                    .addClass('btn-default')
	                                    .prop('disabled', true)
	                                    .html(fileName);	                               	                               
	$(existingFileBtn).insertAfter('#viewFormFileDiv');
	$('#viewFormFileBtn').prop('disabled', false);
}

function doViewFile() {
	location.href = 'UsibilityTestFormFile/formTemplate?projectUuid=' + $('#projectUuid').val() + '&uuid=' + $('#uuid').val();
}
