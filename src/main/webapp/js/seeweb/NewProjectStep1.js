//
// 初始畫面
//
$(document).ready(function() {
	$('#newProjectForm')[0].reset();
	
	$('#nextBtn').attr("disabled", true);

	$('#name').on('change', function() {
		if ($('#name').val()) {
			$('#nextBtn').attr("disabled", false);
			$('#nextBtn').removeClass('btn-default').addClass('btn-primary');
		} else {
			$('#nextBtn').attr("disabled", true);
			$('#nextBtn').removeClass('btn-primary').addClass('btn-default');
		}	 
	}); 

	$('#name').on('keyup', function() {
		if ($('#name').val()) {
			$('#nextBtn').attr("disabled", false);
			$('#nextBtn').removeClass('btn-default').addClass('btn-primary');
		} else {
			$('#nextBtn').attr("disabled", true);
			$('#nextBtn').removeClass('btn-primary').addClass('btn-default');
		}	 
	}); 

	
	$('#nextBtn').click(function(){	
		$('#newProjectForm').attr('method', 'POST');
		$('#newProjectForm').attr('action', 'NewProject');
		$('#newProjectForm').submit();		
	});	
	
});