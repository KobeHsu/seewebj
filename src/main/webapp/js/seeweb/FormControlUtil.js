// 初始日期元件
function initDatePicker(formControlName) {
	$('#' + formControlName).datepicker({format: 'yyyy/mm/dd', autoclose: true});
}
// 初始日期元件
function initPeriodDatePicker(beginFormControlName, endFormControlName) {
	$('#' + beginFormControlName).datepicker({
		format    : 'yyyy/mm/dd', 
		autoclose : true
	}).on('changeDate', function (selected) {
  	var startDate = new Date(selected.date.valueOf());
  	$('#' + endFormControlName).datepicker('setStartDate', startDate);
	}).on('clearDate', function (selected) {
  	$('#' + endFormControlName).datepicker('setStartDate', null);
	});

	$('#' + endFormControlName).datepicker({
		format    : 'yyyy/mm/dd', 
		autoclose : true
	}).on('changeDate', function (selected) {
  	var endDate = new Date(selected.date.valueOf());
  	$('#' + beginFormControlName).datepicker('setEndDate', endDate);
	}).on('clearDate', function (selected) {
  	$('#' + beginFormControlName).datepicker('setEndDate', null);
	});
}
//
function blockUI() {
	$.blockUI({message: '<h4> 執行中, 請稍候</h4>'});
}
// 初始日期元件
function initPeriodDatePickerWithVaildation(beginFormControlName, endFormControlName, formName) {
	$('#' + beginFormControlName).datepicker({
		format    : 'yyyy/mm/dd', 
		autoclose : true
	}).on('changeDate', function (selected) {
  	var startDate = new Date(selected.date.valueOf());
  	$('#' + endFormControlName).datepicker('setStartDate', startDate);
  	$('#' + formName).formValidation('revalidateField', beginFormControlName);
	}).on('clearDate', function (selected) {
  	$('#' + endFormControlName).datepicker('setStartDate', null);
	});

	$('#' + endFormControlName).datepicker({
		format    : 'yyyy/mm/dd', 
		autoclose : true
	}).on('changeDate', function (selected) {
  	var endDate = new Date(selected.date.valueOf());
  	$('#' + beginFormControlName).datepicker('setEndDate', endDate);
  	$('#' + formName).formValidation('revalidateField', endFormControlName);  	
	}).on('clearDate', function (selected) {
  	$('#' + beginFormControlName).datepicker('setEndDate', null);
	});
}
// 清除文字選取
function clearTextSelection() {	
	if (window.getSelection) {
  	if (window.getSelection().empty) {  // Chrome
    	window.getSelection().empty();
  	} else if (window.getSelection().removeAllRanges) {  // Firefox
    	window.getSelection().removeAllRanges();
  	}
	} else if (document.selection) {  // IE
  document.selection.empty();
	}
}
// 載入共通下拉式清單
function loadCommonList(formControlName, actionName, requireBlankOption) {
	$.ajax({
		url      : 'Common/' + actionName,
		type     : 'POST',
		dataType : 'json',
		success  : function(resultList) {
			$.each(resultList, function(idx, item) {
				if (idx == 0) {
					if (requireBlankOption) {
						$('#' + formControlName).append('<option value="" selected="selected"></option>');
						$('#' + formControlName).append('<option value="' + item.code + '">' + item.desc + '</option>');
					} else {
						$('#' + formControlName).append('<option value="' + item.code + '" selected="selected">' + item.desc + '</option>');
					}
				} else {
					$('#' + formControlName).append('<option value="' + item.code + '">' + item.desc + '</option>');
				}
			});
		},
		error : function(xhrInstance, status, xhrException) {
		}
	});
}

