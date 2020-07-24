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
	var type = ($("#hidAnswerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "StudentAnswersAPI",
		type : type,
		data : $("#formAnswer").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAnswerSaveComplete(response.responseText, status);
		}
	});
});

function onAnswerSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divAnswerGrid").html(resultSet.data);
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
	$("#hidAnswerIDSave").val("");
	$("#formAnswer")[0].reset();
}
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
	
			$("#hidAnswerIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
			$("#stuAnswer").val($(this).closest("tr").find('td:eq(0)').text());
			
		});

// REMOVE ====================================================

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "StudentAnswersAPI",
		type : "DELETE",
		data : "id=" + $(this).data("answerid"),
		dataType : "text",
		complete : function(response, status) {
			onAnswerDeleteComplete(response.responseText, status);
		}
	});
});
function onAnswerDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAnswerGrid").html(resultSet.data);
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
	// answer
	if ($("#stuAnswer").val().trim() == "") {
		return "Insert answer.";
	}
	

	return true;

}
