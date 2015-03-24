/**
 * 
 */
$(document).ready(function getUrl() {
	var path = $(location).attr('pathname');
	var temp = path.split("/");
	var id = temp[temp.length - 1];
	$.ajax({
		type : "GET",
		dataType : "json",
		url : "/shortener/user/" + id,
		success : function(response) {
			$("h2").text(response.userName + " profile");
			$("#email").val(response.email);
			$("#password").val(response.password);
			$('form').after(tableCreate(response));
		},
		error : function(e) {
			$('#resp').val(e.responseText);
		}
	});
});

function tableCreate(response) {
	var shorten = response.shortenUrl;
	var head = "<table id='urlList'>";
	var titles = "<tr><th>Date</th><th>URL</th><th>Shorten URL</th><th>Counter</th></tr>"
	var body = "";
	var footer = "</table>";
	$(shorten).each(
			function(index) {
				body += "<tr id='" + index + "'>" 
				
				+ "<td id='date'>"+ shorten[index].date + "</td>" + 
				
						"<td id='url'><a href='" + shorten[index].url+ "' target='_blank'>"+shorten[index].url+"</a></td>" +
						
						"<td id='shorten'><a href='http://" 
						+ $(location).attr('host') +"/shortener/"+ shorten[index].shorten + "' target='_blank'>http://"
						+$(location).attr('host') +"/shortener/"+ shorten[index].shorten+
						"</td>"
						
						+ "<td id ='count'>" + shorten[index].count + "</td>"
				"</tr>";
			}

	);
	return head + titles + body + footer;
}

function doAjax() {
	var path = $(location).attr('pathname');
	var temp = path.split("/");
	var id = temp[temp.length - 1];
	var email = $('#email').val();
	var password = $('#password').val();

	$.ajax({
		type : "PUT",
		url : "/shortener/user/" + id,
		data : {
			email : email,
			password : password
		},

		success : function(response) {
			$("h2").text("Profile updated");
		},
		error : function(e) {
			$('h2').text(e.responseText);
		}
	});
}