//
// 初始畫面
//
$(document).ready(function() {
		
	initFunctionBtnGroupStatus();
	disableFunctionBtn('btnNew');
	
	initMessageBar('messageBar');
	initQueryResultList();	
	initFormValidation();
	
	initPeriodDatePicker('queryBeginDate', 'queryEndDate');
	
	loadCommonList('queryStatus', 'getOnlineIssueStatus', true);
	loadCommonList('status', 'getOnlineIssueStatus', true);

	loadCommonList('queryProject', 'getProjectList', true);
	loadCommonList('project', 'getProjectList', true);
	
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
				{"mData": "projectName"},
				{"mData": "title"},
				{"mData": "status"},
    ],
		"fnServerData": function (sSource, aoData, fnCallback, oSettings) {
			blockUI();
			aoData.push(
				{"name": "queryBeginDate", "value": $('#queryBeginDate').val()},
				{"name": "queryEndDate", "value": $('#queryEndDate').val()},
				{"name": "queryProject", "value": $('#queryProject').val()},
				{"name": "queryStatus", "value": $('#queryStatus').val()}
			);
			oSettings.jqXHR = $.ajax({
				url      : 'OnlineIssueManagement/doQuery',
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
				{"width": "50%", targets: 1}, // 專案
				{"width": "30%", targets: 2}, // 問題主旨
				{"width": "20%", targets: 3}, // 狀態
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
		url      : 'OnlineIssueManagement/doSave',
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
				} else {
					$('#updateTime').val(jsonResult.updateTime);
					$('#processor').val(jsonResult.processor);
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
			status: {validators: {notEmpty: {message: '必填欄位'}}},
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
		url      : 'OnlineIssueManagement/doDelete',
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
	$('#figureList').html('');
	
	toggleInitMode();
	disableFunctionBtn('btnNew');
	initMessageBar('messageBar');	
}

function loadIssueStatusList(formControlName) {
	$.ajax({
		url      : 'Common/getOnlineIssueStatus',
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
// 
function doEdit(record) {	
	$('#uuid').val(record.uuid);
	toggleUpdateMode();
	
	var formData = {'uuid': record.uuid};
	blockUI();
	$.ajax({
		url      : 'OnlineIssueManagement/doQueryDetail',
		type     : 'POST',
		dataType : 'json',
		data     : formData,
		success  : function(jsonResult, status) {
			$.unblockUI();
			$('#project').val(jsonResult.project);
			$('#title').val(jsonResult.title);
			$('#content').val(jsonResult.content);
			$('#replyContent').val(jsonResult.replyContent);
			$('#status').val(jsonResult.status);			
			$('#createTime').val(jsonResult.createTime);
			$('#updateTime').val(jsonResult.updateTime);
			$('#processor').val(jsonResult.processor);
			$('#submitter').val(jsonResult.submitter);
			
			$('#figureList').html('');
			$.each(jsonResult.figures, function(idx, item) {
				$('#figureList').append('<div class="col-sm-6 col-md-3">' + 
				                        '<div class="thumbnail">' +
				                        '<img src="OnlineIssueFile/figure?projectUuid=' + jsonResult.project + '&dataUuid=' + record.uuid + '&uuid=' + item.uuid + '" />' + 
				                        '<div class="caption">' + 
				                        '<p>' +	item.fileName + '.' + item.extName + ' ' +
				                        '<button type="button" class="btn btn-xs btn-default" onclick="javascript:location.href=\'OnlineIssueFile/downloadFigure?projectUuid=' + jsonResult.project + '&dataUuid=' + record.uuid + '&uuid=' + item.uuid + '\'"><i class="fa fa-download"></i></button>' + 
				                        '</p>' + 
				                        '</div>' +
				                        '</div>' +
				                        '</div>');				
			});
			
		},
		error : function(xhrInstance, status, xhrException) {
			$.unblockUI();
		}
	});
		
}
