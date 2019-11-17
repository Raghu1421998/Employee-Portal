setTimeout(fade, 1000);
function fade() {
	$(".success").fadeOut(1000);
}
$(document).ready(function() {
	$('#Comp').on('click', 'td:nth-child(4)', function() {
		var id = $(this).closest('tr').find('td:nth-child(3)').text();
		$.ajax({
			url : 'RowDetails/' + id,
			type : 'GET',
			success : function(RowDetails1) {
				$('#result1').html(RowDetails1);
			}
		});
	});
});
$(function() {
	$("#dialog-2").dialog({
		autoOpen : false,
		buttons : {
			OK : function() {
				$(this).dialog("close");
			}
		},
		title : "Company Details",
		position : {
			my : "center",
			at : "center"
		}
	});

	$('#Comp').on('click', 'td:nth-child(4)', function() {
		$("#dialog-2").dialog("open");
	});
});
