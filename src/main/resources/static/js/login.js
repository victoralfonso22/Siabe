$( document ).ready(function() {
	
	// SUBMIT FORM
    $("#rescueUPForm").submit(function(event) {
		event.preventDefault();
		$("#resultUP").show();
		$('#respuestaUP').attr("aria-valuenow",50); 
		$('#respuestaUP').attr("style","width:50%");

		
		var formData = {recuperacion: $("#recuperacion").val()}
		$.ajax({
			type : "POST",			
			url :"ajaxRescueUP",
			data : formData,
			
			success : function(result) {
				
				$('#respuestaUP').attr("aria-valuenow",75);		    
		    	$('#respuestaUP').attr("style","width:75%");
		    	$('#respuestaUP').attr("aria-valuenow",100); 
		    	$('#respuestaUP').attr("style","width:100%");
				
		    	$("#respuestaUP").hide();
				if(result == "Done"){
					$("#resultUP").html("<p style='text-align:center;width:100%; font-size:14px;color:#337AB7;' class='divRespuesta'>Correo enviado<br></p>");
					
				}else if(result == "nulo"){
					$("#resultUP").html("<p style='text-align:center;width:100%; font-size:14px;color:red;' class='divRespuesta'><b>"+$("#recuperacion").val()+"</b> no se encuetra registrado<br></p>");
				}else{
					$("#resultUP").html("<p style='text-align:center;width:100%; font-size:14px;color:red;'><strong>Error</strong></p>");
				}
				$("#resultUP").delay(4000).hide(600);
				console.log(result);
				$("#recuperacion").val('');
			},
			error : function(jqXHR,e) {
			//	alert(jqXHR.responseText);
			//	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
		
		
		
	});
});