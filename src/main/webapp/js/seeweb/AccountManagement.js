//
// 初始畫面
//
$(document).ready(function() {
		
	initFunctionBtnGroupStatus();
	initMessageBar('messageBar');
	initQueryResultList();	
	initFormValidation();
	
	loadAccountStatusList('status');
	loadAccountStatusList('queryStatus');
	
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
	
});

// 初始查詢結果表格
function initQueryResultList() {
	$('#queryResultList').dataTable({
		"processing": true,
		"serverSide": true,
		"iDeferLoading": 0,
		"aoColumns": [
				{"mData": "uuid"},
				{"mData": "account"},
				{"mData": "realname"},
				{"mData": "statusCaption"},
				{"mData": "status"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "queryAccount", "value": $('#queryAccount').val()},
				{"name": "queryRealname", "value": $('#queryRealname').val()},
				{"name": "queryStatus", "value": $('#queryStatus').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'AccountManagement/doQuery',
				type     : 'POST',
				dataType : 'json',
				data     : aoData,
				success  : function(jsonResult, status) {
					$.unblockUI();
					// showQuerySuccess('messageBar');
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
				{"bVisible": false, "aTargets": [0, 4]},
				{"width": "0%", targets: 0},
				{"width": "50%", targets: 1}, // 帳號
				{"width": "30%", targets: 2}, // 姓名
				{"width": "20%", targets: 3}, // 狀態
				{"width": "0%", targets: 4},
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
		url      : 'AccountManagement/doSave',
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
			account: {validators: {notEmpty: {message: '必填欄位'}}},
			password: {validators: {notEmpty: {message: '必填欄位'}, 
				                      identical: {field: 'confirmPassword', message: '請確認兩次所輸入密碼一致'},
				                      stringLength: {min: 8, max: 24, message: '請輸入8至24個字'},
				                     }},
			confirmPassword: {validators: {notEmpty: {message: '必填欄位'}, 
				                             identical: {field: 'password', message: '請確認兩次所輸入密碼一致'},				                      
				                     }},
			realname: {validators: {notEmpty: {message: '必填欄位'}}},
			nickname: {validators: {notEmpty: {message: '必填欄位'}}},
			status: {validators: {notEmpty: {message: '必填欄位'}}},
			email: {validators: {notEmpty: {message: '必填欄位'}, emailAddress: {message: '非正確的電子郵件地址格式'}}},
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
		url      : 'CaseExtraDefinitionManagement/doDelete',
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

function loadAccountStatusList(formControlName) {
	$.ajax({
		url      : 'Common/getAccountStatus',
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

function doEdit(record) {	
	$('#uuid').val(record.uuid);
	$('#password').val('');
	$('#confirmPassword').val('');
	toggleUpdateMode();
	
	var formData = {'uuid': record.uuid};
	blockUI();
	$.ajax({
		url      : 'AccountManagement/doQueryDetail',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();
			$('#account').val(jsonResult.account);
			$('#realname').val(jsonResult.realname);
			$('#status').val(jsonResult.status);
			$('#phone').val(jsonResult.phone);
			$('#email').val(jsonResult.email);
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});
		
}
