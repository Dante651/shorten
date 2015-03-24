/**
 * 
 */
$(document).ready(
		function doAjax() {
			$.ajax({
				type : "GET",
				url : "/shortener/user/all",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success : function(response) {
					$(response).each(
							function(index) {
								$('#contentList').append(
										"<tr id=" + (index + 1)
												+ "><td id=\"userName\">"
												+ response[index][0]
												+ "</td><td id=\"email\">"
												+ response[index][1]
												+ "</td><td id=\"role\">"
												+ response[index][2]
												+ "</td></tr>");
							});
					createForm();

				},
				error : function(e) {
					$('#resp').text(e.responseText);
				}
			});
		})

function createForm() {
	$("td").one(
			'click',
			function() {
				$(this).html(
						"<input type=\"text\" class=\"edit\" value=\"" + $(this).text()
								+ "\" />").bind('keypress', function(e){
									var code = (e.keyCode ? e.keyCode : e.which);
									if(code==13){
										endForm($(this), $('input').val());
							}
								});
				
			
			});	
	

}

function endForm(form, text){
	var id = $(form).attr('id');
	var trId = $(form).closest('tr').attr('id');
	$(form).html(text);
	$.ajax({
		type : "PUT",
		url : "/shortener/user/"+trId,
		data : id+"="+text,
		success : function(response) {
		},
		error : function(e) {
			$('#resp').text(e.responseText);
		}
	});
}
