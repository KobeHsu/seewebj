$(document).ready(function() {
	$('#tutorialDiv').BootSideMenu({side:"right"});
	
	$('#prevBtn').click(function(){
		var currentPage = parseInt($('#currentPage').html());
		if (currentPage > 1) {
			currentPage--;
			$('#currentPage').html(currentPage);
			
			var imageSrc = $('#tuturialFigure').attr('src');
			var idx = imageSrc.indexOf('.png');
			
			var seqStr = '' + currentPage;
			if (seqStr.length == 2) seqStr = '0' + seqStr;
			if (seqStr.length == 1) seqStr = '00' + seqStr;
			$('#tuturialFigure').attr('src', imageSrc.substring(0, idx-3) + seqStr + '.png');			
		}
	});

	$('#nextBtn').click(function(){
		var currentPage = parseInt($('#currentPage').html());
		var totalPage = parseInt($('#totalPage').html());
		if (currentPage < totalPage) {
			currentPage++;			
			$('#currentPage').html(currentPage);

			var imageSrc = $('#tuturialFigure').attr('src');
			var idx = imageSrc.indexOf('.png');
			
			var seqStr = '' + currentPage;
			if (seqStr.length == 2) seqStr = '0' + seqStr;
			if (seqStr.length == 1) seqStr = '00' + seqStr;
			$('#tuturialFigure').attr('src', imageSrc.substring(0, idx-3) + seqStr + '.png');			
		}		
	});
	
});