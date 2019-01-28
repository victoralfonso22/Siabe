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
    		password :  $("#password").val(),
    		correo :  $("#correo").val()
    		
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
					window.location = "/login?session=false";
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
    	$("#correo").val("");
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
				window.location = "/login?session=false";
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
				window.location = "/login?session=false";
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
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
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
					window.location = "/login?session=false";
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
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
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
					window.location = "/login?session=false";
				}
				//alert(e);
				console.log("ERROR: ", e);				
			}
		});
	

    });

function cambiaPermiso(idMenu,idUsuario, estatus){

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
			window.location = "/login?session=false";
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
			window.location = "/login?session=false";
		}

		console.log("ERROR: ", e);				
	}
});
}


/**************************************************CATALOGOS************************************************************************/

/*************************************PERIODOS DONANTES************************************************/
function nuevoPeriodoDonanteModal() {
    $("#nombre").val('');
    $("#fecha_inicio").val('');
    $("#fecha_final").val('');    
    $("#guardarPeriodoDonanteModal").show();
}

function modificaPeriodoDonanteModal(nombre,fecha_inicio,fecha_final, estatus, idPeriodo) {    
    $("#idPeriodo").val(idPeriodo);
    
    $("#nombreM").val(nombre);
    $("#fecha_inicioM").val(fecha_inicio);
    $("#fecha_finalM").val(fecha_final);    
    $("#estatusM").val(estatus);
    
    $("#nombreA").val(nombre);
    $("#fecha_inicioA").val(fecha_inicio);
    $("#fecha_finalA").val(fecha_final);    
    $("#estatusA").val(estatus);
    $("#modificarPeriodoDonanteModal").show();
    //alert(tBeca+" "+estatus);
}

$("#periodoDonanteForm").submit(function(event) {
		    // Prevent the form from submitting via the browser.
		    event.preventDefault();

		    nombre = $("#nombre").val();
	
		    fecha_inicio = $("#fecha_inicio").val();
	
		    fecha_final = $("#fecha_final").val();
		    
		    cerrarModal('guardarPeriodoDonanteModal');
		    $("#postResultDiv").html("<div class='loader'></div>");
		    ajaxPostPeriodoDonante(nombre,fecha_inicio,fecha_final);
		    
		});

function ajaxPostPeriodoDonante(nombre,fecha_inicio,fecha_final){
	
    var parametrosUpdate = {"nombre" : nombre, "fecha_inicio": fecha_inicio, "fecha_final" : fecha_final};
  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxPeriodoDonante",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Periodo donante guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#periodos").load("refreshTablaPeriodoDonante");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
	

}


$("#periodoDonanteFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    idPeriodo = $("#idPeriodo").val();
    
    nombre = $("#nombreM").val();

    fecha_inicio = $("#fecha_inicioM").val();

    fecha_final = $("#fecha_finalM").val();
    
    estatus = $("#estatusM").val();
    
    nombreA = $("#nombreA").val();

    fecha_inicioA = $("#fecha_inicioA").val();

    fecha_finalA = $("#fecha_finalA").val();
    
    estatusA = $("#estatusA").val();
//    alert(nombre +' '+ $("#fecha_inicio").val() +' '+ $("#fecha_final").val());
    
    cerrarModal('modificarPeriodoDonanteModal');
    if(nombre != nombreA || fecha_inicio != fecha_inicioA || fecha_final != fecha_finalA || estatus != estatusA){
	
	 $("#postResultDiv").html("<div class='loader'></div>");
	var parametrosUpdate = {"nombre" : nombre, "fecha_inicio": fecha_inicio, "fecha_final" : fecha_final, "estatus" : estatus, "idPeriodo" : idPeriodo};
	  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxPeriodoDonanteModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Periodo donante modificado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#periodos").load("refreshTablaPeriodoDonante");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
   
    }
});


/*************************************PERIODOS ************************************************/
function nuevoPeriodoModal() {
    $("#nombre").val('');
    $("#fecha_inicio").val('');
    $("#fecha_final").val('');
 //   $("#tBecaSeleccionado").val('');
    $("#guardarPeriodoModal").show();
}

function modificaPeriodoModal(nombre,fecha_inicio,fecha_final,estatus, idPeriodo) {    
    $("#idPeriodo").val(idPeriodo);
    $("#nombreM").val(nombre);
    $("#fecha_inicioM").val(fecha_inicio);
    $("#fecha_finalM").val(fecha_final);
  //  $("#tBecaSeleccionadoM").val(tBeca);
    $("#estatusM").val(estatus);
  //  $("#periodoDonSelM").val(idPeriodoDonante);
    
    $("#nombreA").val(nombre);
    $("#fecha_inicioA").val(fecha_inicio);
    $("#fecha_finalA").val(fecha_final);
   // $("#tBecaSeleccionadoA").val(tBeca);
    $("#estatusA").val(estatus);
   // $("#periodoDonSelA").val(idPeriodoDonante)
    $("#modificarPeriodoModal").show();
    //alert(tBeca+" "+estatus);
}

$("#periodoForm").submit(function(event) {
		    // Prevent the form from submitting via the browser.
		    event.preventDefault();

		    nombre = $("#nombre").val();
	
		    fecha_inicio = $("#fecha_inicio").val();
	
		    fecha_final = $("#fecha_final").val();
		    
	//	    tBeca = $("#tBecaSeleccionado").val();
		    
//		    periodoDonante = $("#periodoDonSel").val();
		//    alert(nombre +' '+ $("#fecha_inicio").val() +' '+ $("#fecha_final").val());
		 //   if(tBeca == 0){
		//	$('#tBecaSeleccionado').attr('required', 'required');			
			 
		 //   }else{
		    cerrarModal('guardarPeriodoModal');
		    $("#postResultDiv").html("<div class='loader'></div>");
		    ajaxPostPeriodo(nombre,fecha_inicio,fecha_final);
		//    }
		});

function ajaxPostPeriodo(nombre,fecha_inicio,fecha_final){
	
    var parametrosUpdate = {"nombre" : nombre, "fecha_inicio": fecha_inicio, "fecha_final" : fecha_final};
  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxPeriodo",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Periodo guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#periodos").load("refreshTablaPeriodo");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
	

}


$("#periodoFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    idPeriodo = $("#idPeriodo").val();
    
    nombre = $("#nombreM").val();

    fecha_inicio = $("#fecha_inicioM").val();

    fecha_final = $("#fecha_finalM").val();
    
 //   tBeca = $("#tBecaSeleccionadoM").val();
    
 //   periodoDonante = $("#periodoDonSelM").val();
    
    estatus = $("#estatusM").val();
    
    nombreA = $("#nombreA").val();

    fecha_inicioA = $("#fecha_inicioA").val();

    fecha_finalA = $("#fecha_finalA").val();
    
 //   tBecaA = $("#tBecaSeleccionadoA").val();
    
//    periodoDonanteA = $("#periodoDonSelA").val();
    
    estatusA = $("#estatusA").val();
//    alert(nombre +' '+ $("#fecha_inicio").val() +' '+ $("#fecha_final").val());
    
    cerrarModal('modificarPeriodoModal');
    if(nombre != nombreA || fecha_inicio != fecha_inicioA || fecha_final != fecha_finalA || estatus != estatusA ){
	
	 $("#postResultDiv").html("<div class='loader'></div>");
	var parametrosUpdate = {"nombre" : nombre, "fecha_inicio": fecha_inicio, "fecha_final" : fecha_final, "estatus" : estatus, "idPeriodo" : idPeriodo};
	  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxPeriodoModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Periodo modificado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#periodos").load("refreshTablaPeriodo");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
   
    }
});


/***************************************TIPOS BECA*****************************************************/

function nuevotBecaModal() {
    $("#nombre").val('');    
    $("#guardartBecaModal").show();
}


$("#tipoBecaForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombre").val();

    cerrarModal('guardartBecaModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtBeca",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tipo de beca guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#tBecas").load("refreshTablatBeca");
			},
			error : function(jqXHR,e) {
			    	alert(e);
			    	console.log(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificartBecaModal(nombre, id) {
    $("#nombreModificar").val(nombre); 
    $("#nombreModificarHidden").val(nombre); 
    $("#idModificar").val(id); 
    $("#modificartBecaModal").show();
}

$("#tipoBecaFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombreModificar").val();
    nombreAnterior = $("#nombreModificarHidden").val();
    id = $("#idModificar").val(); 
    
    
    if(nombre === nombreAnterior){
	cerrarModal('modificartBecaModal');
    }else{
    cerrarModal('modificartBecaModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre, "idTipoBeca" : id};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtBecaModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tipo de beca modificado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#tBecas").load("refreshTablatBeca");
			},
			error : function(jqXHR,e) {
			    	alert(e);
			    	console.log(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    }
});


/*************************************************REGIONES*************************************************************/


$("#idPeriodoSelect").change(function(event) {
    
	idPeriodo = $("#idPeriodoSelect").val();

	// DO POST	
	var parametrosPermisos = {"idPeriodo": idPeriodo};    
	    
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionesPeriodo",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#regionesPeriodo").html("");
			    }else{
				$("#regionesPeriodo").html(result);
			    }
				
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
	

});

function  asignaDependencia(idRegion){
    
    idPeriodo = $("#idPeriodoSelect").val();
    //alert(idRegion + " 9999 "+idPeriodo);
	// DO POST	
	var parametrosPermisos = {"idPeriodo": idPeriodo , "idRegion": idRegion};    
	    
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionesDependiente",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#tablaDependencia").html("");
			    }else{
				$("#tablaDependencia").html(result);
			    }
				
				console.log(result);
			},
			error : function(jqXHR,e) {
				alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
	

}


function nuevaRegionModal() {
   // alert(idPeriodo);
    $("#nombre").val('');    
    $("#abreviatura").val('');    
   // $("#idPeriodo").val(idPeriodo);    
    $("#guardarRegionModal").show();
}


function nuevaRegionPeriodoModal(idPeriodo) {
	 
	
	var datos = {
			idPeriodo : idPeriodo
		}
	
		$("#nuevaRegionPeriodo").load("refreshModalRegionesPeriodo", datos,function( response, status, xhr ) {			  
			  if(response.includes("Sesión inactiva")){				 
				 window.location = "/login?session=false";
				    }
			if(xhr.status==200 && xhr.statusText== "parsererror"){
				window.location = "/login?session=false";
			}
		});
	   // $("#idPeriodo").val(idPeriodo);    
	$("#idPeriodoRP").val(idPeriodo);  
	    $("#guardarRegionPeriodoModal").show();
	}


function cambiaRelacionRegion(idRegionHijo){
    idRegionPadre = $("#regionPadre").val();
   
    var parametrosPermisos = {"idRegionPadre": idRegionPadre , "idRegionHijo": idRegionHijo, "idPeriodo" : $("#idPeriodoSelect").val()};    
    
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionesDependienteInsert",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    $("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Región dependiente guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#regionPadre").val(idRegionPadre).trigger('change');
				
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
}


$("#regionForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombre").val();
    abreviatura = $("#abreviatura").val();

    cerrarModal('guardarRegionModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre, "abreviatura" : abreviatura};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxRegion",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Región guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				//$("#idPeriodoSelect").val(idPeriodo).trigger('change');
				//$("#tBecas").load("refreshTablatBeca");
				$("#tablaRegiones").load("refreshTablaRegionesPrin");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});

$("#regionFormRP").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    idRegion = $("#idRegionRP").val();
    idPeriodo = $("#idPeriodoRP").val();

    //alert(idRegion+" per "+idPeriodo);
    cerrarModal('guardarRegionPeriodoModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"idRegion" : idRegion, "idPeriodo" : idPeriodo};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionPeriodoN",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Región guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#idPeriodoSelect").val(idPeriodo).trigger('change');
				//$("#tBecas").load("refreshTablatBeca");
				//$("#tablaRegionesPeriodo").load("tablaRegionesPeriodo");
			},
			error : function(jqXHR,e) {
			    	alert(jqXHR.statusText);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificarRegionModal(idRegion,nombre, abreviatura) {
    $("#idRegion").val(idRegion);
    
    $("#nombreM").val(nombre);
    $("#abreviaturaM").val(abreviatura);    
    //$("#idPeriodoM").val(idPeriodo);
    //$("#estatusM").val(estatus);
    
    $("#nombreA").val(nombre);
    $("#abreviaturaA").val(abreviatura);    
    //$("#idPeriodoA").val(idPeriodo);
    //$("#estatusA").val(estatus);  
    
    $("#modificarRegionModal").show();
}


function modificarRegionPeriodoModal(idRegion,nombre, idPeriodo,estatus) {
    $("#idRegionMP").val(idRegion);
   // alert(estatus);
    $("#nombreMP").text(nombre);
    $("#idPeriodoMP").val(idPeriodo);
    $("#estatusA").val(estatus);  
    
    $("#estatusM").val(estatus);  
    
    $("#modificarRegionPeriodoModal").show();
}


$("#regionFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    idRegion = $("#idRegion").val();
    
    nombre = $("#nombreM").val();
    abreviatura = $("#abreviaturaM").val();
  
    nombreA = $("#nombreA").val();
    abreviaturaA = $("#abreviaturaA").val();  
//    alert(nombre +' '+ $("#fecha_inicio").val() +' '+ $("#fecha_final").val());
    
    cerrarModal('modificarRegionModal');
   // alert(nombre +" "+nombreA +" "+ abreviatura +" "+ abreviaturaA +" "+ idPeriodo +" "+ idPeriodoA +" "+ estatus +" "+ estatusA);
    if(nombre != nombreA || abreviatura != abreviaturaA ){
	
	 $("#postResultDiv").html("<div class='loader'></div>");
	var parametrosUpdate = {"nombre" : nombre, "abreviatura": abreviatura, "idRegion" : idRegion};
	  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Región modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				//$("#idPeriodoSelect").val(idPeriodo).trigger('change');
				console.log(result);
				$("#tablaRegiones").load("refreshTablaRegionesPrin");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
   
    }
});





$("#regionFormModalM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    idRegion = $("#idRegionMP").val();
    
    idPeriodo = $("#idPeriodoMP").val();
    
    estatus = $("#estatusM").val();
    
    estatusA = $("#estatusA").val();
  

    cerrarModal('modificarRegionPeriodoModal');
   // alert(nombre +" "+nombreA +" "+ abreviatura +" "+ abreviaturaA +" "+ idPeriodo +" "+ idPeriodoA +" "+ estatus +" "+ estatusA);
    if(estatus != estatusA){
	
	 $("#postResultDiv").html("<div class='loader'></div>");
	var parametrosUpdate = {"estatus" : estatus, "idPeriodo": idPeriodo, "idRegion" : idRegion};
	  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionPeriodoModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Región modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				$("#idPeriodoSelect").val(idPeriodo).trigger('change');
				console.log(result);
				//$("#tablaRegiones").load("refreshTablaRegionesPrin");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
   
    }
});



/*********************************************************************CAMPAÑAS***********************************************************************************/

$("#idPeriodoCampana").change(function(event) {
    
	idPeriodo = $("#idPeriodoCampana").val();
	// DO POST	
	var parametrosPermisos = {"idPeriodo": idPeriodo};    
	    
	$.ajax({
	    		type : "POST",
			url :"ajaxCampanaPeriodo",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#regionesPeriodo").html("");
			    }else{
				$("#regionesPeriodo").html(result);
			    }
				
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});

});

function verCampanas(idRegion){
    var parametrosPermisos = {"idRegion": idRegion};    
    
	$.ajax({
	    		type : "POST",
			url :"ajaxCampanaRegion",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#tablaCampanas").html("");
			    }else{
				$("#tablaCampanas").html(result);
			    }
			  //  $("#idRegion").val(idRegion).trigger('change');
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
}

function nuevaCampanaModal() {
     $("#nombre").val('');
     $("#guardarCampanaModal").show();
 }

function modificarCampanaModal(nombre, idRegion, estatus, idCampana){
    $("#idCampana").val(idCampana);
    
    $("#nombreM").val(nombre);        
    
    $("#estatusM").val(estatus);
    
    $("#nombreA").val(nombre);        
    $("#idRegionA").val(idRegion);
    $("#estatusA").val(estatus);  
    
    idPeriodo = $("#idPeriodoCampana").val();
    
    var parametrosUpdate = {"idPeriodo" : idPeriodo, "idRegion" : idRegion};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxCampanaRegionM",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#idRegionM").html("");
			    }else{
				$("#idRegionM").html(result);
			    }			
				console.log(result);
	
				//$("#tBecas").load("refreshTablatBeca");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    $("#idRegionM").val(idRegion);
    $("#modificarCampanaModal").show();
}

$("#campanaForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombre").val();
    
    idRegion = $("#idRegion").val();

    cerrarModal('guardarCampanaModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre, "idRegion" : idRegion};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxCampana",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Campaña guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#idRegion").val(idRegion).trigger('change');
				//$("#tBecas").load("refreshTablatBeca");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
});


$("#campanaFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    idCampana = $("#idCampana").val();    
    
    nombre = $("#nombreM").val();    
    idRegion = $("#idRegionM").val();    
    estatus = $("#estatusM").val();
    
    nombreA = $("#nombreA").val();    
    idRegionA = $("#idRegionA").val();    
    estatusA = $("#estatusA").val();
    
    cerrarModal('modificarCampanaModal');

    if(nombre != nombreA || idRegion != idRegionA || estatus != estatusA){
	
	 $("#postResultDiv").html("<div class='loader'></div>");
	var parametrosUpdate = {"nombre" : nombre, "idRegion" : idRegion, "estatus" : estatus, "idCampana" : idCampana};
	  
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxCampanaModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Campaña modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				$("#idRegion").val(idRegion).trigger('change');
				console.log(result);
				//$("#periodos").load("refreshTablaPeriodo");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
   
    }
});

/***************************************TIPOS DONATIVO*****************************************************/

function nuevotDonativoModal() {
    $("#nombre").val('');    
    $("#guardartDonativoModal").show();
}


$("#tipoDonativoForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombre").val();

    cerrarModal('guardartDonativoModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtDonativo",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tipo de donativo guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#tDonativos").load("refreshTablatDonativo");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificartDonativoModal(nombre, id) {
    $("#nombreModificar").val(nombre); 
    $("#nombreModificarHidden").val(nombre); 
    $("#idModificar").val(id); 
    $("#modificartDonativoModal").show();
}

$("#tipoDonativoFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    nombre = $("#nombreModificar").val();
    nombreAnterior = $("#nombreModificarHidden").val();
    id = $("#idModificar").val(); 
    
    
    if(nombre === nombreAnterior){
	cerrarModal('modificartDonativoModal');
    }else{
    cerrarModal('modificartDonativoModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre, "idTipoDonativo" : id};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtDonativoModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tipo de donativo modificado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#tDonativos").load("refreshTablatDonativo");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    }
});


/***************************************CUENTAS BANCARIAS*****************************************************/

function nuevomCobroModal() {
    $("#nombre").val('');   
    $("#numCuenta").val('');
    $("#sucursal").val('');
    $("#clabe").val('');
    $("#guardarmCobroModal").show();
}


$("#tipoMedioCobroForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    nombre = $("#nombre").val();
    numCuenta = $("#numCuenta").val();
    sucursal = $("#sucursal").val();
    clabe = $("#clabe").val();
    

    cerrarModal('guardarmCobroModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre,"numCuenta" : numCuenta, "sucursal" : sucursal, "clabe" : clabe};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxmCobro",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Cuenta bancaria guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#cuentasBancarias").load("refreshTablamCobro");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificarmCobroModal(nombre,numCuenta,sucursal, clabe, id) {
    
    $("#nombreModificar").val(nombre); 
    $("#nombreModificarHidden").val(nombre);
    
    $("#numCuentaModificar").val(numCuenta); 
    $("#numCuentaModificarHidden").val(numCuenta); 
    
    $("#sucursalModificar").val(sucursal); 
    $("#sucursalModificarHidden").val(sucursal); 
    
    $("#clabeModificar").val(clabe); 
    $("#clabeModificarHidden").val(clabe);
    
    $("#idModificar").val(id); 
    
    
    $("#modificarmCobroModal").show();
}

$("#tipoMedioCobroFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    nombre = $("#nombreModificar").val();
    nombreAnterior = $("#nombreModificarHidden").val();

    numCuenta = $("#numCuentaModificar").val();
    numCuentaAnterior = $("#numCuentaModificarHidden").val();
    
    
    sucursal = $("#sucursalModificar").val(); 
    sucursalAnterior = $("#sucursalModificarHidden").val(); 
    
    clabe = $("#clabeModificar").val(); 
    clabeAnterior = $("#clabeModificarHidden").val();
    
    
    
    id = $("#idModificar").val(); 
    
    
    if(nombre === nombreAnterior  && numCuenta === numCuentaAnterior &&  sucursal === sucursalAnterior && clabe === clabeAnterior){
	cerrarModal('modificarmCobroModal');
    }else{
    cerrarModal('modificarmCobroModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"nombre" : nombre ,"numCuenta" : numCuenta, "sucursal" : sucursal, "clabe" : clabe, "idCuentaBancaria" : id};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxmCobroModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Cuenta bancaria modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#cuentasBancarias").load("refreshTablamCobro");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    }
});


/**************************************************************************TIEMPOS PERIODO**********************************************************************************/

$("#peridoTSeleccionado").change(function(event) {
    
	idPeriodo = $("#peridoTSeleccionado").val();

	// DO POST	
	var parametrosPermisos = {"idPeriodo": idPeriodo};    
	    
	$.ajax({
	    		type : "POST",
			url :"ajaxTiempoPeriodoSelect",
			data : parametrosPermisos,
			success : function(result) {
				$("#logosReportes").show();
				$("#logosAgregar").show();
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#regionesTiempoPeriodo").html("");
			    }else{
			    $("#buscarTiempoPromedio").show();
				$("#regionesTiempoPeriodo").html(result);
				tablaTiempoPromerdioP(idPeriodo);
				$("#buscarTiempoPromedio").val('');
				
			    }
				
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
	

});


function tablaTiempoPromerdioP(idPeriodo){
	
var parametrosPermisos = { "idPeriodo": idPeriodo};    

$.ajax({
    		type : "POST",
		url :"ajaxTiempoPeriodoTablaP",
		data : parametrosPermisos,
		success : function(result) {	
		  //  alert(result);
		    if(result.includes("Sesión inactiva")){
			window.location = "/login?session=false";
		    }else if(result == 'Nada'){
		    	$("#tablaTiempoPeriodo").html("");
		    }else{
			$("#tablaTiempoPeriodo").html(result);
		    }
			
			console.log(result);
		},
		error : function(jqXHR,e) {			
			if (jqXHR.status != 200) {
			window.location = "/error";
			}else{
				window.location = "/login?session=false";
			}			
			console.log("ERROR: ", e);				
		}
	});
	
}


function verTiempoPromerdio() {
	//alert(idRegion);
	// DO POST	

	var parametrosPermisos = {"idRegion": $("#idRegionTiempo").val(), "idPeriodo": $("#peridoTSeleccionado").val(), "buscarInput" : $("#buscarTiempoPromedio").val()};  
	
	$.ajax({
	    		type : "POST",
			url :"ajaxTiempoRegionSelect",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#tablaTiempoPeriodo").html("");
			    }else{
				$("#tablaTiempoPeriodo").html(result);
			    }
				
				console.log(result);
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});
}

function nuevoTiempoPeriodoModal() {
	idRegion = $("#idRegionTiempo").val();
    $("#carrera").val('');   
    $("#nivel").val('');
    $("#modalidad").val('');
    $("#plan").val('');
    $("#pPromedio").val('');

    $("#idRegionN").val(idRegion);

    //regionesPeriodoSlt($("#peridoTSeleccionado").val(),'idRegionAlta',idRegion,'regionAl','idArea','areaAl','idFac','facAl',0);
        
   $("#idRegionAlta").val('');
   $("#idArea").val('');
   $("#idFac").val('');
    
    $("#nuevoTiempoPeriodoModal").show();

}

function nuevoAreaModal() {

	$("#nuevaAr").prop("checked", true);
	$("#nuevaAr").trigger('click');
	$("#areaNuevaTxt").val("");
    
    $("#nuevoAreaModal").show();

}

function nuevoFacultadModal(){
	$("#nuevaFacd").prop("checked", true);
	$("#nuevaFacd").trigger('click');
	$("#facultadNuevaTxt").val("");
    
    $("#nuevoFacultadModal").show();
}


function asignarFacModificar(idArea, idFacultad){
    

	// DO POST	
	var parametrosPermisos = {"idArea": idArea, "idFacultad" : idFacultad};    
	    
	$.ajax({
	    		type : "POST",
			url :"ajaxTiempoPeriodoAreaM",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#idFacM").html("");
			    }else{
				$("#idFacM").html(result);
			    }
				
				console.log(result);
				//$("#idRegion").val(idRegion).trigger('change');
			},
			error : function(jqXHR,e) {			
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}			
				console.log("ERROR: ", e);				
			}
		});

}

$("#timepoPeriodoForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    carrera = $("#carrera").val();
    nivel = $("#nivel").val();
    modalidad = $("#modalidad").val();
    plan = $("#plan").val();
    pPromedio = $("#pPromedio").val();
    idArea = $("#idArea").val();
    idRegionAntes = $("#idRegionN").val();
    idRegionAlta = $("#idRegionAlta").val();
    if(idRegionAntes != idRegionAlta){
    	idRegion = idRegionAlta;
    }else{
    	idRegion = idRegionAntes;
    }
    idFac = $("#idFac").val();
    idPeriodo = $("#peridoTSeleccionado").val();
    

    cerrarModal('nuevoTiempoPeriodoModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"carrera" : carrera,"nivel" : nivel, "modalidad" : modalidad, "plan" : plan, "pPromedio": pPromedio, "idRegion" : idRegion, "idFac" : idFac, "idArea" : idArea, "idPeriodo" : idPeriodo};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtPromedio",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tiempo periodo guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#idRegionTiempo").val(idRegion).trigger('change');
				//$("#cuentasBancarias").load("refreshTablamCobro");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificarTiempoPromedio(carrera,nivel,modalidad, plan, pPromedio, idFacultad, idArea,idRegion, idCarrera, estatus){
    $("#carreraA").val(carrera);
    $("#carreraM").val(carrera);
    
    $("#nivelA").val(nivel);
    $("#nivelM").val(nivel);
    
    $("#modalidadA").val(modalidad);
    $("#modalidadM").val(modalidad);
    
    $("#planA").val(plan);
    $("#planM").val(plan);
    
    $("#pPromedioA").val(pPromedio);
    $("#pPromedioM").val(pPromedio);
    
    $("#idRegionA").val(idRegion);
    $("#idRegionM").val(idRegion);
    
    //asignarFacModificar(idArea, idFacultad);
    
    $("#idAreaA").val(idArea);
    $("#idAreaM").val(idArea);
    
    $("#idFacA").val(idFacultad);
    $("#idFacM").val(idFacultad);
    
    $("#estatusA").val(estatus);
    $("#estatusM").val(estatus);
        
    $("#idCarreraA").val(idCarrera);

    
    //regionesPeriodoSlt($("#peridoTSeleccionado").val(),"idRegionM",idRegion,"regionMl","idAreaM","areaMl","idFacM","facMl",idArea);
    
  //  areasRegionesSlt("idAreaM",idRegion,"areaMl",idArea,"idFacM","facMl");
    
  //  facultadesAreasSlt("idFacM",idArea,"facMl",idFacultad,idRegion);
    
    $("#modificartPromedio").show();
}


$("#tPromedioFormM").submit(function(event) {

    event.preventDefault();
    
    carrera = $("#carreraM").val();
    carreraAnterior = $("#carreraA").val();
    
    nivel = $("#nivelM").val();
    nivelAnterior = $("#nivelA").val();    
    
    modalidad = $("#modalidadM").val();
    modalidadAnterior = $("#modalidadA").val();    
    
    plan = $("#planM").val();
    planAnterior = $("#planA").val();    
    
    pPromedio = $("#pPromedioM").val();
    pPromedioAnterior = $("#pPromedioA").val();
    
    idRegion = $("#idRegionM").val();
    idRegionActual = $("#idRegionA").val();    
    
    idArea = $("#idAreaM").val();
    idAreaAnterior = $("#idAreaA").val();
        
    idFacultad = $("#idFacM").val();
    idFacultadAnterior = $("#idFacA").val();
    
    estatus = $("#estatusM").val();
    estatusAnterior = $("#estatusA").val();
    
    id = $("#idCarreraA").val();
    
	cerrarModal('modificartPromedio');    
    if(carrera === carreraAnterior && nivel === nivelAnterior &&  modalidad === modalidadAnterior && plan === planAnterior && 
    		pPromedio === pPromedioAnterior && idRegion === idRegionActual && idArea === idAreaAnterior && idFacultad === idFacultadAnterior && estatus === estatusAnterior){

    }else{

    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"idFacultad" : idFacultad, "idRegion" : idRegion, "carrera" : carrera ,"nivel" : nivel, "modalidad" : modalidad, "plan" : plan, "pPromedio" : pPromedio ,"estatus" : estatus,"idArea" : idArea, "idCarrera" : id};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtPromedioModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Periodo promedio modificado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				verTiempoPromerdio();
				//$("#cuentasBancarias").load("refreshTablamCobro");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    }
});


$("#nuevaAreaForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    if($("#nuevaAr").is(':checked')){
    //	alert('Nueva');
    	idAreaModifica = 0;
    	tipo = 'N';
    	nuevoNombreArea = $("#areaNuevaTxt").val();
    }else if($("#modificacionAr").is(':checked')){
    	idAreaModifica = $("#idAreaNM").val();    	
    	nuevoNombreArea = $("#areaNuevaTxt").val();
    	tipo = 'M';
    //	alert('Modificacion');    	
    }
    
    cerrarModal('nuevoAreaModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"tipo" : tipo, "nombreArea" : nuevoNombreArea, "idAreaModifica" : idAreaModifica};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxAreaTPro",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Área guardada / modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				idPeriodo = $("#peridoTSeleccionado").val();
				console.log(result);
				//$("#idRegionTiempo").val($("#idRegionTiempo").val()).trigger('change');
				$("#idArea").load("refreshAreaNuevaTiemposPromedio");
				$("#idAreaM").load("refreshAreaModificarTiempoPromedio");
				$("#idAreaNM").load("refreshAreaNMTiempoPromedio");
				verTiempoPromerdio();
				
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});

$("#nuevaFacultadForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    if($("#nuevaFacd").is(':checked')){
    //	alert('Nueva');
    	idFacModifica = 0;
    	tipo = 'N';
    	
    }else if($("#modificarFacd").is(':checked')){
    	idFacModifica = $("#idFacNM").val();
    	tipo = 'M';
    //	alert('Modificacion');    	
    }
    nuevoNombreFac = $("#facultadNuevaTxt").val();
    cerrarModal('nuevoFacultadModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"tipo" : tipo, "nombreFac" : nuevoNombreFac, "idFacModifica" : idFacModifica};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxFacTPro",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Facultad guardada / modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				idPeriodo = $("#peridoTSeleccionado").val();
				console.log(result);
				//$("#idRegionTiempo").val($("#idRegionTiempo").val()).trigger('change');
				$("#idFac").load("refreshFacNuevaTiemposPromedio");
				$("#idFacM").load("refreshFacModificarTiempoPromedio");
				$("#idFacNM").load("refreshFacNMTiempoPromedio");
				verTiempoPromerdio();
				
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


/*
$("#areaRegionForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    

    idRegion = $("#idRegionN").val();
    idRegionAlta = $("#idRegionAlta").val();
    if(idRegionAntes != idRegionAlta){
    	idRegion = idRegionAlta;
    }else{
    	idRegion = idRegionAntes;
    }
    idFac = $("#idFac").val();
    idPeriodo = $("#peridoTSeleccionado").val();
    

    cerrarModal('nuevoTiempoPeriodoModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"carrera" : carrera,"nivel" : nivel, "modalidad" : modalidad, "plan" : plan, "pPromedio": pPromedio, "idRegion" : idRegion, "idFac" : idFac, "idPeriodo" : idPeriodo};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxtPromedio",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Tiempo periodo guardado !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#idRegionTiempo").val(idRegion).trigger('change');
				//$("#cuentasBancarias").load("refreshTablamCobro");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});

*/


function checkIds(id){
	if($("#"+id).val() == 'N'){
		$("#idAreaNM").hide();
		$("#areaNuevaTxt").attr("placeholder","Nueva área");
		$("#idAreaNM").val('');
		$("#idAreaNM").prop('required',false);
	}else if($("#"+id).val() == 'M'){
		$("#idAreaNM").show();
		$("#areaNuevaTxt").attr("placeholder","Nuevo nombre del área");
		$("#idAreaNM").prop('required',true);
	}
}

function checkIdsFac(id){
	if($("#"+id).val() == 'N'){
		$("#idFacNM").hide();
		$("#facultadNuevaTxt").attr("placeholder","Nueva facultad");
		$("#idFacNM").val('');
		$("#idFacNM").prop('required',false);
	}else if($("#"+id).val() == 'M'){
		$("#idFacNM").show();
		$("#facultadNuevaTxt").attr("placeholder","Nuevo nombre de la facultad");
		$("#idFacNM").prop('required',true);
	}
}


/****************************************************************************QUINCENAS*********************************************************************************************************/

function nuevaQuincenaModal(){
	$("#numQuincena").val('');
	$("#nombre").val('');
	$("#anio").val('');
	
	$("#nuevaQuincenaModal").show();
}

$("#quincenaForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();

    numQuincena = $("#numQuincena").val();
    nombre = $("#nombre").val();
    anio = $("#anio").val();
    
    cerrarModal('nuevaQuincenaModal');
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"numQuincena" : numQuincena, "nombre" : nombre, "anio" : anio};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxQuincenas",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Quincena guardada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
			
				console.log(result);
				$("#quincenasF").load("actualizaTablaQuincenas");
			
				
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    

});


function modificarQuincenaModal(idQuincena,numQuincena,nombre,anio){
	
	$("#numQuincenaM").val(numQuincena);
	$("#nombreM").val(nombre);
	$("#anioM").val(anio);
	
	$("#numQuincenaA").val(numQuincena);
	$("#nombreA").val(nombre);
	$("#anioA").val(anio);
	
	$("#idQuincenaA").val(idQuincena);
	
	$("#modificarQuincenaModal").show();
	
}


$("#quincenaFormM").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    numQuincena = $("#numQuincenaM").val();
    numQuincenaAnterior = $("#numQuincenaA").val();
    
    nombre = $("#nombreM").val();
    nombreAnterior = $("#nombreA").val();
    
    anio = $("#anioM").val();
    anioAnterior = $("#anioA").val();
    
    
    id = $("#idQuincenaA").val(); 
    
    cerrarModal('modificarQuincenaModal');
    if(numQuincena !== numQuincenaAnterior || nombre !== nombreAnterior || anio !== anioAnterior){
    $("#postResultDiv").html("<div class='loader'></div>");
    
    var parametrosUpdate = {"numQuincena" : numQuincena,"nombre" : nombre , "anio" : anio,  "idQuincena" : id};
    
	// DO POST
	$.ajax({
	    		type : "POST",
			url :"ajaxQuincenasModificar",
			data : parametrosUpdate,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html("<p class='divRespuesta'>! Quincena modificada !<br></p>");
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				$("#quincenasF").load("actualizaTablaQuincenas");
			},
			error : function(jqXHR,e) {
			    	alert(e);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    
    }
});
