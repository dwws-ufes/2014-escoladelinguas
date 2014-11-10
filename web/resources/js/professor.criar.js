
$(function() {
	//	Hide <div> on load
	$('div#brasileiro').hide();
	$('div#estrangeiro').hide();

	$('#professorForm\\:nacionalidade').on('change', function() {
		var selectedOption = $(this).children('option:selected').val();
		if (selectedOption === 'BRASILEIRO') {
			$('div#brasileiro').show();
			$('div#estrangeiro').hide();
		} else if (selectedOption === 'ESTRANGEIRO') {
			$('div#brasileiro').hide();
			$('div#estrangeiro').show();
		} else {
			$('div#brasileiro').hide();
			$('div#estrangeiro').hide();
		}
	});
});
