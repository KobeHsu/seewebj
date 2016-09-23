// 初始功能按鈕
var FUNCTION_NAME_INSERT = 'INSERT';
var FUNCTION_NAME_UPDATE = 'UPDATE';
var FUNCTION_NAME_DELETE = 'DELETE';
var FUNCTION_NAME_QUERY = 'QUERY';
function initFunctionBtnGroupStatus() {
	try {
		initMessageBar('messageBar');

		toggleInitMode();		
		
		$('#btnNew').click(function(){
			disableFunctionBtn('btnNew');
			enableFunctionBtn('btnSave');
			disableFunctionBtn('btnDelete');
			enableFunctionBtn('btnCancel');			
			$('.nav-tabs a[href="#editPanel1"]').tab('show');
			$('#functionName').val(FUNCTION_NAME_INSERT);
			onNew();
		});
	} catch (e) {
	}
}
// 設定啟用及停用狀態按鈕樣式
var CLASS_ENABLED = 'btn-info';
var CLASS_DISABLED = 'btn-default';
// 啟用功能按鈕
function enableFunctionBtn(functionBtnName) {
	$('#' + functionBtnName).attr('disabled', false);
	$('#' + functionBtnName).removeClass(CLASS_DISABLED);
	$('#' + functionBtnName).addClass(CLASS_ENABLED);
}
// 停用功能按鈕
function disableFunctionBtn(functionBtnName) {
	$('#' + functionBtnName).attr('disabled', true);
	$('#' + functionBtnName).removeClass(CLASS_ENABLED);
	$('#' + functionBtnName).addClass(CLASS_DISABLED);
}
// 顯示新增或更新成功
function showSaveSuccess(functionName, messageBarName) {
	if (functionName == FUNCTION_NAME_INSERT) {
		$('#' + messageBarName).html('<i class="fa fa-file-o fa-lg"></i>   資料新增成功');	
		toggleUpdateMode();
	} else if (functionName == FUNCTION_NAME_UPDATE) {
		$('#' + messageBarName).html('<i class="fa fa-floppy-o fa-lg"></i>   資料更新成功');	
	}
}
// 顯示新增或更新失敗
function showSaveFailed(functionName, messageBarName, errorMessage) {
	if (errorMessage == 'AA-0004') {
		$(location).attr('href', '');
	} else {
		if (functionName == FUNCTION_NAME_INSERT) {
			$('#' + messageBarName).html('<i class="fa fa-exclamation fa-lg"></i>   資料新增失敗, 錯誤原因為' + errorMessage);	
		} else if (functionName == FUNCTION_NAME_UPDATE) {
			$('#' + messageBarName).html('<i class="fa fa-exclamation fa-lg"></i>   資料更新失敗, 錯誤原因為' + errorMessage);	
		}
	}
}
// 初始訊息
function initMessageBar(messageBarName) {
	$('#' + messageBarName).html('<i class="fa fa-info-circle fa-lg"></i>  ');	
}
// 切換為更新模式
function toggleInitMode() {
		enableFunctionBtn('btnNew');
		disableFunctionBtn('btnSave');
		disableFunctionBtn('btnDelete');
		disableFunctionBtn('btnCancel');
		$('.nav-tabs a[href="#queryPanel"]').tab('show');
		$('#functionName').val('');	
}
// 切換為更新模式
function toggleUpdateMode() {
		disableFunctionBtn('btnNew');
		enableFunctionBtn('btnSave');
		enableFunctionBtn('btnDelete');
		enableFunctionBtn('btnCancel');			
		$('.nav-tabs a[href="#editPanel1"]').tab('show');
		$('#functionName').val(FUNCTION_NAME_UPDATE);	
}
// 顯示刪除成功
function showDeleteSuccess(messageBarName) {
	$('#' + messageBarName).html('<i class="fa fa-trash-o fa-lg"></i>   資料刪除成功');	
	toggleInitMode();
}
// 顯示刪除失敗
function showDeleteFailed(messageBarName, errorMessage) {
	if (errorMessage == 'AA-0004') {
		$(location).attr('href', '');
	} else {
		$('#' + messageBarName).html('<i class="fa fa-exclamation fa-lg"></i>   資料刪除失敗, 錯誤原因為' + errorMessage);	
	}
}
// 顯示輸入驗證錯誤
function showInputError(messageBarName) {
	$('#' + messageBarName).html('<i class="fa fa-exclamation fa-lg"></i>   部分必填欄位缺漏或輸入錯誤值, 請修正');	
}
// 顯示查詢成功
function showQuerySuccess(messageBarName) {
	$('#' + messageBarName).html('<i class="fa fa-filter fa-lg"></i>   資料查詢成功');	
}
// 顯示查詢失敗
function showQueryFailed(messageBarName, errorMessage) {
	$('#' + messageBarName).html('<i class="fa fa-exclamation fa-lg"></i>   資料查詢失敗, 錯誤原因為' + errorMessage);	
}

function onNew() {
}