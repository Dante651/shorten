/**
 * 
 */
function doAjax() {
	var userName = $('#userName').val();
	var password = $('#password').val();
	var json = {
		"userName" : userName,
		"password" : password,
	};

	$.ajax({
		type : "POST",
		url : "/shortener/login",
		data : JSON.stringify(json),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(response) {
				$('#resp').text(response);
				window.setTimeout(function(){
					location.replace("/shortener");
				}, 3000);
		},
		error : function(e) {
			$('#resp').text(e.responseText);
		}
	});
}
