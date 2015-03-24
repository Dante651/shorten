/**
 * 
 */
function doAjax() {
	var url = $('#url').val();
	var json = { "url" : url };

	$.ajax({
		type : "PUT",
		url : "/shortener/add",
		data : JSON.stringify(json),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			$('#form').slideUp("slow", function() {
				$('#url').remove();
				$('#button').remove();
				$(this).remove();
				$('#resp').text($(location).attr('href') + response);
				$('h2').text('Your address is:');
			});
		},
		error : function(e) {
			$('#resp').text(e.responseText);
		}
	});
}