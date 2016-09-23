//
// 初始畫面
//
$(document).ready(function() {
	$('#newProjectForm')[0].reset();
	
	$('#nextBtn').attr("disabled", true);

	$('input[type=radio][name=projectTemplateUuid]').change(function() {
		if (this.value) {
			$('#nextBtn').attr("disabled", false);
			$('#nextBtn').removeClass('btn-default').addClass('btn-primary');
		} else {
    	$('#nextBtn').attr("disabled", true);
    	$('#nextBtn').removeClass('btn-primary').addClass('btn-default');
		}		
	});
	
	$('#nextBtn').click(function(){
		$('#next').val('Y');
		$('#newProjectForm').attr('method', 'POST');
		$('#newProjectForm').attr('action', 'NewProject');
		$('#newProjectForm').submit();
	});	

	$('#prevBtn').click(function(){
		$('#next').val('N');
		$('#newProjectForm').attr('method', 'POST');
		$('#newProjectForm').attr('action', 'NewProject');
		$('#newProjectForm').submit();
	});	
	
});