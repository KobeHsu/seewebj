//
// 初始畫面
//
$(document).ready(function() {
		
	initFunctionBtnGroupStatus();
	initMessageBar('messageBar');
	
	initQueryResultList();	
	initFormValidation();
	loadCommonList('valueType', 'getCaseExtraDefinitionTypeList');
	
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
	
	doQuery();
});

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
				{"mData": "valueTypeCaption"},
				{"mData": "valueLength"},
				{"mData": "valueMeasure"},
				{"mData": "valueType"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "functionName", "value": $('#functionName').val()},
				{"name": "projectUuid", "value": $('#projectUuid').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'CaseExtraDefinitionManagement/doQuery',
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
				{"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]},
				{"bVisible": false, "aTargets": [0, 6]},
				{"width": "0%", targets: 0},
				{"width": "40%", targets: 1},
				{"width": "20%", targets: 2},
				{"width": "20%", targets: 3},
				{"width": "10%", targets: 4},
				{"width": "10%", targets: 5},
				{"width": "0%", targets: 6},
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
		var valueType = aData[aPos].valueType;
		var valueLength = aData[aPos].valueLength;
		var valueMeasure = aData[aPos].valueMeasure;

		$('#uuid').val(uuid);
		$('#name').val(name);
		$('#serialNo').val(serialNo);
		$('#valueType').val(valueType);
		$('#valueLength').val(valueLength);
		$('#valueMeasure').val(valueMeasure);
		toggleUpdateMode();
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
		url      : 'CaseExtraDefinitionManagement/doSave',
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
			name: {validators: {notEmpty: {message: '必填欄位'}}},
			serialNo: {validators: {notEmpty: {message: '必填欄位'}, numeric: {message: '請輸入數字'}}},
			valueLength: {validators: {numeric: {message: '請輸入數字'}}},
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
