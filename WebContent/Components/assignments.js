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
	var type = ($("#hidAssignmentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AssignmentsAPI",
		type : type,
		data : $("#formAssignment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAssignmentSaveComplete(response.responseText, status);
		}
	});
});

function onAssignmentSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divAssignmentGrid").html(resultSet.data);
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
	$("#hidAssignmentIDSave").val("");
	$("#formAssignment")[0].reset();
}
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
	
			$("#hidAssignmentIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
			$("#assignmentName").val($(this).closest("tr").find('td:eq(0)').text());
			
			
		});

// REMOVE ====================================================

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AssignmentsAPI",
		type : "DELETE",
		data : "id=" + $(this).data("assignmentid"),
		dataType : "text",
		complete : function(response, status) {
			onAssignmentDeleteComplete(response.responseText, status);
		}
	});
});
function onAssignmentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divAssignmentGrid").html(resultSet.data);
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
	// assignment name
	if ($("#assignmentName").val().trim() == "") {
		return "Insert assignment name.";
	}
	
	

	return true;

}
