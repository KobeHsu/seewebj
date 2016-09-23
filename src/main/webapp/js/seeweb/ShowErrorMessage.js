function showErrorMesssage(messageBar, message) {
	if (message == 'AA-0004') {
		$(location).attr('href', '');
	} else {
		$('#' + messageBar).html('<div class="alert alert-danger alert-dismissible" role="alert">' + 
				                     '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' + 
				                     '<strong>' + message + '</strong>' +
				                     '</div>');				
	}
}
