//
// 初始畫面
//
$(document).ready(function() {
		
	initFunctionBtnGroupStatus();
	
	initMessageBar('messageBar');
	initQueryResultList();	
	initFormValidation();
		
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
		$('#confirmDeleteModal').modal('show');
	});
			
	$('#btnConfirmDelete').click(function(){
		doDelete();
	});		
	
});

// 初始查詢結果表格
function initQueryResultList() {
	$('#queryResultList').dataTable({
		"processing": true,
		"serverSide": true,
		"iDeferLoading": 0,
		"aoColumns": [
				{"mData": "uuid"},
				{"mData": "name"},
				{"mData": "description"},
				{"mData": "url"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "queryName", "value": $('#queryName').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'ToolManagement/doQuery',
				type     : 'POST',
				dataType : 'json',
				data     : aoData,
				success  : function(jsonResult, status) {
					$.unblockUI();
					if (jsonResult.functionStatus == 'FAILED' && jsonResult.errorMessage == 'AA-0004') {
						$(location).attr('href', '');
					} else {
						fnCallback(jsonResult);
					}
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
				{"bSortable": false, "aTargets": [0, 1, 2, 3]},
				{"bVisible": false, "aTargets": [0]},
				{"width": "0%", targets: 0},
				{"width": "20%", targets: 1}, // 名稱
				{"width": "40%", targets: 2}, // 說明
				{"width": "40%", targets: 3}, // URL
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
		doEdit(aData[aPos]);
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
		url      : 'ToolManagement/doSave',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();			
			var functionName = $('#functionName').val();
			if (jsonResult.functionStatus == "SUCCESS") {
				showSaveSuccess(functionName, 'messageBar');
				if (functionName == FUNCTION_NAME_INSERT) {
					$('#uuid').val(jsonResult.uuid);					
				}				
				
				var queryResultList = $('#queryResultList').dataTable();
				queryResultList.fnDraw();				
			} else if (jsonResult.functionStatus == 'FAILED') {
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
// 表單驗證設定
function initFormValidation() {
	$('#editForm1').formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'fa fa-check',
      invalid: 'fa fa-times',
      validating: 'fa fa-circle-o-notch'
    },
		fields: {
			name: {validators: {notEmpty: {message: '必填欄位'}}},
			url: {validators: {notEmpty: {message: '必填欄位'}}},
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
		url      : 'ToolManagement/doDelete',
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
	$('#uuid').val('');
	$('#functionName').val('');
	
	toggleInitMode();
	initMessageBar('messageBar');	
}

// 
function doEdit(record) {	
	$('#uuid').val(record.uuid);
	$('#name').val(record.name);
	$('#description').val(record.description);
	$('#url').val(record.url);
	toggleUpdateMode();			
}
