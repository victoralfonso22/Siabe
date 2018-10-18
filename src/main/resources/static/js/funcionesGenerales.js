$( document ).ready(function() {
	if($("#idUsuario").val() == null || $("#idUsuario").val() == ''){
    	window.location = "/login?session=false";
    }
});

/****************************************************************************VALIDACION SOLO NUMEROS*********************************************************************/

function valida(e){
    tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla==8){
        return true;
    }
        
    // Patron de entrada, en este caso solo acepta numeros
    patron =/[0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}


/********************************************************************NUMERO CON PUNTO DECIMAL**************************************************************************************/


function filterFloat(evt,input){
    // Backspace = 8, Enter = 13, ‘0′ = 48, ‘9′ = 57, ‘.’ = 46, ‘-’ = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter(__val__){
    var preg = /^([0-9]+\.?[0-9]{0,2})$/; 
    if(preg.test(__val__) === true){
        return true;
    }else{
       return false;
    }
    
}
/************************************************************************CERRAR MODAL********************************************************************************************************/

function cerrarModal(id){
    $("#"+id).hide();
}

/*********************************************************************REGIONES DEL PERIODO*///////////////////////////////////////////////////////////////////////////////////////////////

function regionesPeriodoSlt(idPeriodo,nombreIdRegion,idRegion,divIdRegion,nombreIdArea,divIdArea,nombreIdFac,divIdFac,idArea) {
	
	var parametrosPermisos = {"idPeriodo": idPeriodo, "nombreIdRegion" : nombreIdRegion, "idRegion": idRegion, "nombreIdArea" : nombreIdArea, "divIdArea" : divIdArea,"nombreIdFac" : nombreIdFac,"divIdFac" : divIdFac};  
	
	$.ajax({
	    		type : "POST",
			url :"ajaxRegionesPeriodoSlt",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#"+divIdRegion).html("");
			    }else{
				$("#"+divIdRegion).html(result);

				if(idRegion > 0 ){
						areasRegionesSlt(nombreIdArea,$("#"+nombreIdRegion).val(),divIdArea,idArea,nombreIdFac,divIdFac);
					
				}
				
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


/*********************************************************************AREAS DE LA REGION*///////////////////////////////////////////////////////////////////////////////////////////////

function areasRegionesSlt(nombreIdArea,idRegion,divIdArea,idArea,nombreIdFac,divIdFac) {
	
	if(idRegion != ''){
	//alert(nombreIdArea+' '+idRegion+' '+divIdArea+' '+idArea+' '+nombreIdFac+' '+divIdFac);
	var parametrosPermisos = {"nombreIdArea" : nombreIdArea, "idRegion": idRegion, "idArea": idArea, "nombreIdFac" : nombreIdFac, "divIdFac" : divIdFac};  
	$.ajax({
	    		type : "POST",
			url :"ajaxAreasRegionesSlt",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#"+divIdArea).html("");
			    }else{
				$("#"+divIdArea).html(result);
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
}


/*********************************************************************FACULTADES DE LAS AREAS*///////////////////////////////////////////////////////////////////////////////////////////////

function facultadesAreasSlt(nombreIdFac,idArea,divIdFac,idFacultad,idRegion) {
//	alert('entre');
	var parametrosPermisos = {"nombreIdFac" : nombreIdFac, "idArea": idArea, "idFacultad": idFacultad, "idRegion" : idRegion};  
	
	$.ajax({
	    		type : "POST",
			url :"ajaxFacultadesAreasSlt",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == 'Nada'){
			    	$("#"+divIdFac).html("");
			    }else{
				$("#"+divIdFac).html(result);
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

/*****************************************PROXIMO FOCO EN ELEMENTO DE FORMULARIO********************************************/


$(".nextFoco").on("keypress", function(e){
	  $(this).next().trigger("focus");
	})


/*****************************************OBTENER LOS CHECKBOX SELECCIONADOS********************************************/	
	
function getValueCheckboxPorClase(clase){
	/* declare an checkbox array */
	var chkArray = [];
	
	/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
	$("."+clase+":checked").each(function() {
		chkArray.push($(this).val());
	});
	
	/* we join the array separated by the comma */
	var selected;
	selected = chkArray.join(',') ;
	
	/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
	/*if(selected.length > 0){
		alert("You have selected " + selected);	
	}else{
		alert("Debes seleccionar al menos un checkbox");	
	}*/
	
	return selected;
}


function getValuePorClase(clase){
	/* declare an checkbox array */
	var chkArray = [];
	
	/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
	$("."+clase).each(function() {
		chkArray.push($(this).val());
	});
	
	/* we join the array separated by the comma */
	var selected;
	selected = chkArray.join(',') ;
	
	/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
	/*if(selected.length > 0){
		alert("You have selected " + selected);	
	}else{
		alert("Debes seleccionar al menos un checkbox");	
	}*/
	
	return selected;
}