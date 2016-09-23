//
// 初始畫面
//
$(document).ready(function() {
	$('#newProjectForm')[0].reset();
	
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