/**
 * 
 */
function doAjax() {
	var userName = $('#userName').val();
	var password = $('#password').val();
	var email = $('#email').val();
	var json = {
		"userName" : userName,
		"password" : password,
		"email" : email
	};

	$.ajax({
		type : "PUT",
		url : "/shortener/registration",
		data : JSON.stringify(json),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
			$('#form').slideUp("slow", function() {
				$('#userName').remove();
				$('#password').remove();
				$('#email').remove();
				$('h2').remove();
				$('#bt').remove();
				$('#resp').text(response);
			});
			window.setTimeout(function(){
				location.replace("/shortener");
			}, 3500);
		},
		error : function(e) {
			$('#resp').text(e.responseText);
		}
	});
}