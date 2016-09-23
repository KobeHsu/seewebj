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
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "functionName", "value": $('#functionName').val()},
				{"name": "projectUuid", "value": $('#projectUuid').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'ProjectWizard/doQuery',
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
				{"bSortable": false, "aTargets": [0, 1, 2]},
				{"bVisible": false, "aTargets": [0]},
				{"width": "0%", targets: 0},
				{"width": "80%", targets: 1},
				{"width": "20%", targets: 2},
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

		$('#uuid').val(uuid);
		$('#name').val(name);
		$('#serialNo').val(serialNo);
		$('#toolUuids').val('');
		
		var queryToolList = $('#queryToolList').dataTable();
		queryToolList.fnDraw();

		toggleUpdateMode();
	});

	$('#queryToolList').dataTable({
		"processing": true,
		"serverSide": true,
		"iDeferLoading": 0,
		"sDom": 't',
		"aoColumns": [
				{
					"mData": "isSelected",
					render: function (data, type, row) {
						if (type === 'display') {
							if (row.isSelected === 'N') {
								return '<input type="checkbox" class="editor-active" id="' + row.uuid + '" onclick="toogleSelect(this)">';
							} else if (row.isSelected === 'Y') {
								// var groupMembers = $('#groupMembers').val();
								// groupMembers = groupMembers + row.memberUuid + ',';
								// $('#groupMembers').val(groupMembers);
								return '<input type="checkbox" class="editor-active" id="' + row.uuid + '" onclick="toogleSelect(this)" checked="checked">';
							}
						}
						return '';
					},
					className: "dt-body-center"
				},
				{"mData": "uuid"},
				{"mData": "name"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "phaseUuid", "value": $('#uuid').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'ProjectWizard/doQueryTool',
				type     : 'POST',
				dataType : 'json',
				data     : aoData,
				success  : function(jsonResult, status) {
					$.unblockUI();
					fnCallback(jsonResult);
				},
				error : function(xhrInstance, status, xhrException) {
					$.unblockUI();
				}				
			});
		},
		"oLanguage": {"sUrl": "/seewebj/res/language/datatable_zh_tw.txt"},
		"bFilter": false,
		"aaSorting": [],
		"aoColumnDefs": [
				{"bSortable": false, "aTargets": [0, 1, 2]},
				{"bVisible": false, "aTargets": [1]},
				{"width": "10%", targets: 0},
				{"width": "0%", targets: 1},
				{"width": "90%", targets: 2},
		],
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
		url      : 'ProjectWizard/doSave',
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
		}
	});	
}
// 執行查詢
function doQuery() {
	doClear();
	var queryResultList = $('#queryResultList').dataTable();
	queryResultList.fnDraw();

	var queryToolList = $('#queryToolList').dataTable();
	queryToolList.fnDraw();

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

function toogleSelect(control) {
	var toolUuid = $(control).attr('id');
	var toolUuids = $('#toolUuids').val();
	var idx = (toolUuids) ? toolUuids.indexOf(toolUuid) : -1;
	
	if ($(control).prop('checked') && idx < 0) { 
		toolUuids = toolUuids + toolUuid + ',';
		$('#toolUuids').val(toolUuids);
	} else if (!$(control).prop('checked') && idx >= 0) {
		toolUuids = toolUuids.replace(toolUuid + ',', '');
		$('#toolUuids').val(toolUuids);
	}
}
