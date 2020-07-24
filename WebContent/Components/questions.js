$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidQuestionIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "QuestionsAPI",
		type : type,
		data : $("#formQuestion").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onQuestionSaveComplete(response.responseText, status);
		}
	});
});

function onQuestionSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divQuestionGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidQuestionIDSave").val("");
	$("#formQuestion")[0].reset();
}
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
	
			$("#hidQuestionIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
			$("#questionNo").val($(this).closest("tr").find('td:eq(0)').text());
			$("#question").val($(this).closest("tr").find('td:eq(1)').text());
			$("#answer").val($(this).closest("tr").find('td:eq(2)').text());
		
			
		});

// REMOVE ====================================================

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "QuestionsAPI",
		type : "DELETE",
		data : "id=" + $(this).data("questionid"),
		dataType : "text",
		complete : function(response, status) {
			onQuestionDeleteComplete(response.responseText, status);
		}
	});
});
function onQuestionDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divQuestionGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENTMODEL=========================================================================
function validateItemForm() {
	// questionNo
	if ($("#questionNo").val().trim() == "") {
		return "Insert question Number.";
	}
	// question
	if ($("#question").val().trim() == "") {
		return "Insert question.";
	}
	// answer
	if ($("#answer").val().trim() == "") {
		return "Insert answer.";
	}
	

	return true;

}
