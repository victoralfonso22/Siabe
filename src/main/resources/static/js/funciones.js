$( document ).ready(function() {
	
	// SUBMIT FORM
    $("#usuarioForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		$("#postResultDiv").html("<div class='loader'></div>");
		ajaxPost();
	});
    
    
    function ajaxPost(){

    	var formData = {
    		nombre : $("#nombreCompleto").val(),
    		usuario :  $("#username").val(),
    		password :  $("#password").val()
    		
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"ajaxAlta",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				$("#postResultDiv").show();
				if(result.status == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Usuario guardado ! <br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				console.log(result);
			},
			error : function(jqXHR,e) {
				//alert(jqXHR.status)
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/sesionExpirada";
				}

				console.log("ERROR: ", e);				
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();

    }
    
    function resetData(){
    	$("#username").val("");
    	$("#nombreCompleto").val("");
    	$("#password").val("");
    }
    


})



function desactivarUsuario(id, estatus){
	var parametrosUpdate = {"id": id , "estatus": estatus};
	$("#postResultDiv").html("<div class='loader'></div>");
	$.ajax({
		type : "POST",
		
		url :"ajaxBaja",
		data : parametrosUpdate,
		
		success : function(result) {
			$("#postResultDiv").show();
			if(result == "Done" && estatus == 1){
				$("#postResultDiv").html("<p class='divRespuesta'>! Usuario inactivado ! <br></p>");			
				
			}else if(result == "Done" && estatus == 0){
			    $("#postResultDiv").html("<p class='divRespuesta'>! Usuario activado ! <br></p>");
			    
			}else{				
				window.location = "/sesionExpirada";
			}
			$("#postResultDiv").delay(6000).hide(600);
			console.log(result);
			$("#usuarios").load("refreshEstatusUsuario");
		},
		error : function(jqXHR,e) {
			//alert(jqXHR.status)
			if (jqXHR.status != 200) {
			window.location = "/error";
			}else{
				window.location = "/sesionExpirada";
			}

			console.log("ERROR: ", e);				
		}
	});
    }



function cambiarPassword(id,usuario,nombre){
	var parametrosUpdate = {"id": id};
	$("#nuevoPass").val('');
	$("#nuevoPassRep").val('');
	$("#id_user_pass").val(id);
	$("#userPass").html("<br/><span class='spanClassModal'>Usuario : </span><span class='spanClassImportanteModal'>"+usuario+"</span>");
	$("#nombrePass").html("<br/><span class='spanClassModal'>Nombre completo : </span><span class='spanClassImportanteModal'>"+nombre+"</span>");
	$("#guardarPassModal").show();
	//$("#nuevoPass").html("<label>Password nuevo : </label><input type='text' class='form-control' style='font-size: 17px;	color: #000;	text-align: center; font-weight: bold;' required");	

}


function cerrarModal(id){
    $("#"+id).hide();
}

	// SUBMIT FORM
$("#passForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		
		pass = $("#nuevoPass").val();
		passRep =  $("#nuevoPassRep").val();
		id_user_pass =  $("#id_user_pass").val();
		
		var n = pass.localeCompare(passRep);
		if(n == 0){
		 cerrarModal('guardarPassModal');
		$("#postResultDiv").html("<div class='loader'></div>");
		ajaxPostPassword(pass,id_user_pass);
		}else{
		    $("#respuestaPass").html("<span style='color:#D9534F;'>La la nueva contraseña no es identica en los dos campos.</span>");
		    $("#nuevoPassRep").val('');
		    //$("#id_user_pass").val('');
		}
	});


function ajaxPostPassword(pass,id_user_pass){
	
    var parametrosUpdate = {"password" : pass, "id": id_user_pass};
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxPassword",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("expirado")){
				window.location = "/sesionExpirada";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Password guardado ! <br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				console.log(result);
			},
			error : function(jqXHR,e) {
				//alert(jqXHR.status)
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/sesionExpirada";
				}

				console.log("ERROR: ", e);				
			}
		});
	

}

$("#usuarioSeleccionado").change(function(event) {
    
    
    	idUsuario = $("#usuarioSeleccionado").val();
    
	// DO POST

	
	var parametrosPermisos = {"id": idUsuario};    
	    
    	$.ajax({
	    		type : "POST",
			url :"ajaxPermisos",
			data : parametrosPermisos,
			success : function(result) {			    
			    if(result.includes("expirado")){
				window.location = "/sesionExpirada";
			    }else if(result == 'Nada'){
			    	$("#tablaPermisos").html("");
			    }else{
				$("#tablaPermisos").html(result);
			    }
				//$("#postResultDiv").show();
				/*if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Password guardado ! <br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}*/
				//$("#postResultDiv").delay(6000).hide(600);
				console.log(result);
			},
			error : function(jqXHR,e) {
				//alert(jqXHR.status)
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/sesionExpirada";
				}
				//alert(e);
				console.log("ERROR: ", e);				
			}
		});
	

    });

function cambiaPermiso(idMenu,idUsuario, estatus){
  //  alert(idMenu+" + "+idUsuario+" - "+estatus);
    
    var parametrosPermisos = {"id": idUsuario, "idMenu" : idMenu, "estatus" : estatus};    
    
    $.ajax({
	type : "POST",
	
	url :"ajaxCambioPermiso",
	data : parametrosPermisos,
	
	success : function(result) {
		$("#postResultDiv").show();
		if(result == "Done"){
		 /*   if(estatus == 1){
		    	$("#"+idMenu).removeClass("tdSencilloPermiso").addClass("tdSencillo");
		    }else{
			$("#"+idMenu).removeClass("tdSencillo").addClass("tdSencilloPermiso");
		    }*/
			$("#postResultDiv").html("<p class='divRespuesta'>! Permiso cambiado ! <br></p>");			
			
		}else{				
			window.location = "/sesionExpirada";
		}
		$("#postResultDiv").delay(6000).hide(600);
		console.log(result);
		$("#usuarioSeleccionado").val(idUsuario).trigger('change');
		//$("#tablaPermisos").load("refreshPermisoUsuario");
	},
	error : function(jqXHR,e) {
		//alert(jqXHR.status)
		if (jqXHR.status != 200) {
		window.location = "/error";
		}else{
			window.location = "/sesionExpirada";
		}

		console.log("ERROR: ", e);				
	}
});
}
