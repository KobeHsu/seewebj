//
// 初始畫面
//
$(document).ready(function() {
	$('#registrationForm')[0].reset();
	
	$('#nextBtn').attr("disabled", true);

	$('#sign').change(function() {		
		if($(this).is(":checked")) {
			$('#nextBtn').attr("disabled", false);
			$('#nextBtn').removeClass('btn-default').addClass('btn-primary');
    } else {
    	$('#nextBtn').attr("disabled", true);
    	$('#nextBtn').removeClass('btn-primary').addClass('btn-default');
    }
	});
	
	$('#nextBtn').click(function(){
		if ($('#sign').val() == 'Y') {
			$('#registrationForm').attr('method', 'POST');
			$('#registrationForm').attr('action', 'ActivateAccount/');
			$('#registrationForm').submit();
		}
	});	
	
});