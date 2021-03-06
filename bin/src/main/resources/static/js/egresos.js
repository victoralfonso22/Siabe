$("#becaSeleccionada").change(function(event) {
	$("#logosAgregar").show();
	
	
	tipoBeca = $("#becaSeleccionada").val();

	var datos = {
		idTipoBeca : $("#becaSeleccionada").val()
	}
	
	if( $('input:radio[name=altaCambio]:checked').val() ==1){
	$("#idPeriodo").load("actualizaPeriodos", datos,function( response, status, xhr ) {			  
			  if(response.includes("Sesión inactiva")){				 
				 window.location = "/login?session=false";
				    }
			if(xhr.status==200 && xhr.statusText== "parsererror"){
				window.location = "/login?session=false";
			}
		});
	
	$("#idPeriodoD").load("actualizaPeriodos", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
	
	}
	//	$("#idPeriodoD").load("actualizaPeriodos", datos);

	
	
	
	$("#btn-guardar").show();
	
	if( $('input:radio[name=altaCambio]:checked').val() ==1){
	$("textarea.vacias").val('');
	$("input.vacias").val('');
	$("select.vacias").val(1);
	$("input.deshab").prop("disabled",true);
	$("input.deshab").attr("placeholder","");
	
	
	
	$('input:radio[name=hermanosEscuelas]')[0].checked = true;
	checkIdsHED('hesD');
	
	$('input:radio[name=mismoDomicilio]')[0].checked = true;
	checkIdsDom('siMD');
	
	$('input:radio[name=formaPago]')[0].checked = true;
	checkIdsFP('dFP');
	}
	
	if(tipoBeca == 1 || tipoBeca == 2 || tipoBeca == 3){
		
		$("#datosBecas").show();
		$("#datosBecasDeportivas").hide();
		$("#benefactor").prop('disabled',true);
		if( $('input:radio[name=altaCambio]:checked').val() ==1){
		$("#divBenficiarioAsig").show();
		}else{
			$("#divBenficiarioAsig").hide();
		}
		$('input.becasRequired').prop('required', true);
		$('select.becasRequired').prop('required', true);
		$('textarea.becasRequired').prop('required', true);
		
		$('input.depRequired').prop('required', false);
		$('select.depRequired').prop('required', false);
		$('textarea.depRequired').prop('required', false);
		
		
	}else if(tipoBeca == 4){
		$("#datosBecasDeportivas").show();
		$("#datosBecas").hide();
		
		$('input.becasRequired').prop('required', false);
		$('select.becasRequired').prop('required', false);
		$('textarea.becasRequired').prop('required', false);
		
		$('input.depRequired').prop('required', true);
		$('select.depRequired').prop('required', true);
		$('textarea.depRequired').prop('required', true);
	}
	
	if($("#becaSeleccionada").val() == 3){
		$("#fApoyo").show();
		$("#fApoyo").prop('required', true);
	}else{
		$("#fApoyo").hide();
		$("#fApoyo").prop('required', false);
	}
	
	
	/*
	 * 
	 * 		$("input.requerido").prop('required',false);
		$("div.formula").hide();
		$("select.requerido").prop('required',false);

	 * */
	
});




$("#estatus").change(function(event) {
	if ($("#estatus").val() == 0) {
		$("#motivosEstatus").show();
		$("#motivoEstInactivo").prop('required', true);
	} else {
		$("#motivosEstatus").hide();
		$("#motivoEstInactivo").prop('required', false);
	}
});


$("#estatusD").change(function(event) {
	if ($("#estatusD").val() == 0) {
		$("#motivosEstatusD").show();
		$("#motivoEstInactivoD").prop('required', true);
	} else {
		$("#motivosEstatusD").hide();
		$("#motivoEstInactivoD").prop('required', false);
	}
});



/*$("#idPeriodo").change(function(event) {
 var datos = {
 idPeriodo:$("#idPeriodo").val()
 }
 $("#idRegion").load("actualizaRegion",datos);
 });*/

/***************************
 * Cuando cambia el valor del periodo se habilita la busqueda de región
 * **************************************/
function cambiaPeriodo(id,idRegion){

	if($("#"+id).val() > 0){
	$("#"+idRegion).prop('disabled',false);
	$("#"+idRegion).attr("placeholder","Escribe y selecciona una opción");
	$("#benefactor").prop('disabled',false);
	}

}

function autocompletarRegion(idRegion,idRegionHidden,valRegionHidden,idArea,idFac,nivel,modalidad,idCarrera) {

	$("#"+idRegion).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteRegion",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#idPeriodo").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre,
							value : item.idRegion
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			 
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#"+idRegionHidden).val(ui.item.value);
			$("#"+valRegionHidden).val(this.value);
		//	alert("valor "+$("#valRegionHidden").val()+" id"+$("#idRegionHidden").val());
			$("#"+idArea).val('');
			$("#"+idArea).prop('disabled',false);
			$("#"+idArea).attr("placeholder","Escribe y selecciona una opción");
			$("#"+idArea).focus();
			$("#idAreaHidden").val('');
			$("#valAreaHidden").val('');			
			$("#"+idFac).prop('disabled',true);
			$("#"+idFac).attr("placeholder","");
			$("#"+idFac).val('');
			$("#idFacHidden").val('');
			$("#valFacHidden").val('');	
			$("#"+nivel).prop('disabled',true);
			$("#"+nivel).attr("placeholder","");
			$("#"+nivel).val('');
			$("#valNivelHidden").val('');				
			$("#"+modalidad).prop('disabled',true);
			$("#"+modalidad).attr("placeholder","");
			$("#"+modalidad).val('');
			$("#valModalidadHidden").val('');
			$("#"+idCarrera).prop('disabled',true);
			$("#"+idCarrera).attr("placeholder","");
			$("#"+idCarrera).val('');
			$("#idCarreraHidden").val('');
			$("#valCarreraHidden").val('');
			return false;
		},
		minLength: 0
		

	});
}


function autocompletarRegionD(idRegion,idRegionHidden,valRegionHidden,focoSig) {

	$("#"+idRegion).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteRegion",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#idPeriodoD").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre,
							value : item.idRegion
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			 
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#"+idRegionHidden).val(ui.item.value);
			$("#"+valRegionHidden).val(this.value);		
			$("#"+focoSig).focus();		
			return false;
		},
		minLength: 0
		

	});
}



function autocompletarArea(idRegionHidden,idArea,idAreaHidden,valAreaHidden,idFac,nivel,modalidad,idCarrera) {

	$("#"+idArea).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteArea",
				dataType : "json",
				data : {
					term : request.term,
					idRegion : $("#"+idRegionHidden).val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre,
							value : item.idArea
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#"+idAreaHidden).val(ui.item.value);
			$("#"+valAreaHidden).val(this.value);
			//alert("valor "+$("#valAreaHidden").val()+" id"+$("#idAreaHidden").val());
			$("#"+idFac).val('');
			$("#"+idFac).prop('disabled',false);
			$("#"+idFac).attr("placeholder","Escribe y selecciona una opción");
			$("#"+idFac).focus();
			$("#idFacHidden").val('');
			$("#valFacHidden").val('');	
			$("#"+nivel).prop('disabled',true);
			$("#"+nivel).attr("placeholder","");
			$("#"+nivel).val('');
			$("#valNivelHidden").val('');	
			$("#"+modalidad).prop('disabled',true);
			$("#"+modalidad).attr("placeholder","");
			$("#"+modalidad).val('');
			$("#valModalidadHidden").val('');
			$("#"+idCarrera).prop('disabled',true);
			$("#"+idCarrera).attr("placeholder","");
			$("#"+idCarrera).val('');
			$("#idCarreraHidden").val('');
			$("#valCarreraHidden").val('');
			return false;
			
		},
		minLength: 0

	});
}

function autocompletarFac(idRegionHidden,idAreaHidden,idFac,idFacHidden,valFacHidden,nivel,modalidad,idCarrera) {

	$("#"+idFac).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteFacultad",
				dataType : "json",
				data : {
					term : request.term,
					idRegion : $("#"+idRegionHidden).val(),
					idArea : $("#"+idAreaHidden).val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre,
							value : item.idFacultad
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#"+idFacHidden).val(ui.item.value);
			$("#"+valFacHidden).val(this.value);
			//alert("valor "+$("#valAreaHidden").val()+" id"+$("#idAreaHidden").val());
			$("#"+nivel).val('');
			$("#"+nivel).prop('disabled',false);
			$("#"+nivel).attr("placeholder","Escribe y selecciona una opción");
			$("#"+nivel).focus();
			$("#valNivelHidden").val('');	
			$("#"+modalidad).prop('disabled',true);
			$("#"+modalidad).attr("placeholder","");
			$("#"+modalidad).val('');
			$("#valModalidadHidden").val('');
			$("#"+idCarrera).prop('disabled',true);
			$("#"+idCarrera).attr("placeholder","");
			$("#"+idCarrera).val('');
			$("#idCarreraHidden").val('');
			$("#valCarreraHidden").val('');
			return false;
		},
		minLength: 0

	});
}


function autocompletarNivel(idRegionHidden,idAreaHidden,idFacHidden,nivel,valNivelHidden,modalidad,idCarrera) {

	$("#"+nivel).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteNivel",
				dataType : "json",
				data : {
					term : request.term,
					idRegion : $("#"+idRegionHidden).val(),
					idArea : $("#"+idAreaHidden).val(),
					idFac : $("#"+idFacHidden).val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nivel,
							value : item.nivel
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;			
			$("#"+valNivelHidden).val(this.value);	
			$("#"+modalidad).val('');
			$("#"+modalidad).prop('disabled',false);
			$("#"+modalidad).attr("placeholder","Escribe y selecciona una opción");
			$("#"+modalidad).focus();
			$("#valModalidadHidden").val('');
			$("#"+idCarrera).prop('disabled',true);
			$("#"+idCarrera).attr("placeholder","");
			$("#"+idCarrera).val('');
			$("#idCarreraHidden").val('');
			$("#valCarreraHidden").val('');
			return false;
		},
		minLength: 0

	});
}

function autocompletarModalidad(idRegionHidden,idAreaHidden,idFacHidden,valNivelHidden,modalidad,valModalidadHidden,idCarrera) {

	$("#"+modalidad).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteModalidad",
				dataType : "json",
				data : {
					term : request.term,
					idRegion : $("#"+idRegionHidden).val(),
					idArea : $("#"+idAreaHidden).val(),
					idFac : $("#"+idFacHidden).val(),
					nivel : $("#"+valNivelHidden).val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						return {
							//label: item.nombre + " - " +item.,
							label : item.modalidad,
							value : item.modalidad
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;			
			$("#"+valModalidadHidden).val(this.value);			
			$("#"+idCarrera).val('');
			$("#"+idCarrera).prop('disabled',false);
			$("#"+idCarrera).attr("placeholder","Escribe y selecciona una opción");
			$("#"+idCarrera).focus();
			$("#idCarreraHidden").val('');
			$("#valCarreraHidden").val('');
			return false;
		},
		minLength: 0

	});
}

function autocompletarCarrera(idRegionHidden,idAreaHidden,idFacHidden,valNivelHidden,valModalidadHidden,idCarrera,idCarreraHidden,valCarreraHidden,idSigFoco) {

	$("#"+idCarrera).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteCarrera",
				dataType : "json",
				data : {
					term : request.term,
					idRegion : $("#"+idRegionHidden).val(),
					idArea : $("#"+idAreaHidden).val(),
					idFac : $("#"+idFacHidden).val(),
					nivel : $("#"+valNivelHidden).val(),
					modalidad : $("#"+valModalidadHidden).val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						return {
							//label: item.nombre + " - " +item.,
							label : item.carrera,
							value : item.idCarrera,
							pPromedio : item.periodoPromedio
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;
			$("#pPromedio").val(ui.item.pPromedio);
			$("#idCarreraHidden").val(ui.item.value);
			$("#"+valCarreraHidden).val(this.value);
			$("#"+idSigFoco).focus();
			//alert($("#idCarreraHidden").val());
			return false;
		},
		minLength: 0

	});
}


$("#periodoActual").blur(function(event) {	
	var pRebasa = $("#pPromedio").val()-$("#periodoActual").val();
	$('#pRebasa').val(pRebasa);
});


function checkIdsDom(id){
	if($("#"+id).val() == '1'){
		$("#calleE").prop('disabled',true);
		$("#calleE").prop('required',false);
		$("#calleE").val('');
		$("#nInteriorE").prop('disabled',true);
		$("#nInteriorE").prop('required',false);
		$("#nInteriorE").val('');
		$("#nExteriorE").prop('disabled',true);
		$("#nExteriorE").prop('required',false);
		$("#nExteriorE").val('');
		$("#colE").prop('disabled',true);
		$("#colE").prop('required',false);
		$("#colE").val('');
		$("#localidadE").prop('disabled',true);
		$("#localidadE").prop('required',false);
		$("#localidadE").val('');
		$("#municipioE").prop('disabled',true);
		$("#municipioE").prop('required',false);
		$("#municipioE").val('');
		$("#estadoE").prop('disabled',true);
		$("#estadoE").prop('required',false);
		$("#estadoE").val('');
		$("#cpE").prop('disabled',true);
		$("#cpE").prop('required',false);
		$("#cpE").val('');
	}else if($("#"+id).val() == '0'){
		$("#calleE").prop('disabled',false);
		$("#calleE").prop('required',true);
		$("#calleE").val('');
		$("#nInteriorE").prop('disabled',false);
		$("#nInteriorE").prop('required',false);
		$("#nInteriorE").val('');
		$("#nExteriorE").prop('disabled',false);
		$("#nExteriorE").prop('required',true);
		$("#nExteriorE").val('');
		$("#colE").prop('disabled',false);
		$("#colE").prop('required',true);
		$("#colE").val('');
		$("#localidadE").prop('disabled',false);
		$("#localidadE").prop('required',true);
		$("#localidadE").val('');
		$("#municipioE").prop('disabled',false);
		$("#municipioE").prop('required',true);
		$("#municipioE").val('');
		$("#estadoE").prop('disabled',false);
		$("#estadoE").prop('required',true);
		$("#estadoE").val('');
		$("#cpE").prop('disabled',false);
		$("#cpE").prop('required',true);
		$("#cpE").val('');
	}
}


function checkIdsFP(id){
	if($("#"+id).val() == '1'){
		$("#ncta").show();
		$("#numCuenta").prop('required',true);
		$("#numCuenta").val('');
		$("#ntar").show();
		$("#numTarjeta").prop('required',true);
		$("#numTarjeta").val('');
		$("#clvP").hide();
		$("#clavePago").prop('required',false);
		$("#clavePago").val('');
		$("#vigia").hide();
		$("#vigencia").prop('required',false);
		$("#vigencia").val('');
		
	}else if($("#"+id).val() == '0'){
		$("#ncta").hide();
		$("#numCuenta").prop('required',false);
		$("#numCuenta").val('');
		$("#ntar").hide();
		$("#numTarjeta").prop('required',false);
		$("#numTarjeta").val('');
		$("#clvP").show();
		$("#clavePago").prop('required',true);
		$("#clavePago").val('');
		$("#vigia").show();
		$("#vigencia").prop('required',true);
		$("#vigencia").val('');
		
	}
}


function checkIdsHED(id){
	$("#escuelaHD").val('');
	if($("#"+id).val() == '1'){
		$("#eqeD").hide();
		$("#escuelaHD").prop('required',false);				
	}else if($("#"+id).val() == '0'){
		$("#eqeD").show();
		$("#escuelaHD").prop('required',true);
	}
}



$("#beneficiarioForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
    
    bandera = true;
   
    if( $('input:radio[name=altaCambio]:checked').val() ==1){
    	
    	mensaje ="<p class='divRespuesta'>! Beneficiario agregado !<br></p>";
    	
    	tBeca = $("#becaSeleccionada").val();
        
        if(tBeca== 1 || tBeca == 2 || tBeca == 3){
        	
       // 	if($("#idBenefactorHidden").val() == ''){
       // 		idBenefactorAsignado = null;
      //  	}else{
        		idBenefactorAsignado = $("#idBenefactorHidden").val();
       /// 	}
        	
        	_url= "ajaxAgregarBeneficiarioBeca";
        	
        	parametros = { "idPeriodo" : $("#idPeriodo").val(),  "matricula" : $("#matricula").val(),  "nombre" : $("#nombres").val(),  "apellidoPaterno" : $("#aPaterno").val(),  
        			"apellidoMaterno" : $("#aMaterno").val(), "estatus" : $("#estatus").val(), "motivoEstatus" : $("#motivoEstInactivo").val(), "tipoBecario" : $("#tBecario").val(), "adscripcion" : $("#adscripcion").val(),  
        			"idRegion" : $("#idRegionHidden").val(), "idCarrera" : $("#idCarreraHidden").val(), "periodoActual" : $("#periodoActual").val(), "promedioGeneral" : $("#pGeneral").val(), "edad" : $("#edad").val(), 
        			"genero" : $("#genero").val(), "lenguaIndigena" : $("#lIndigena").val(), "discapacidad" : $("#discapacidad").val(), "estadoCivil" : $("#edoCivil").val(), "lugarNacimiento" : $("#lNacimiento").val(), 
        			"fechaNacimiento" : $("#fechaNacimiento").val(), "breveHistoria" : $("#bHistoria").val(), "integrantesFamiliares" : $("#iFamiliares").val(),  "ingresosFamiliares" : $("#ingresos").val(),  
        			"calleVivFam" : $("#calle").val(), "numEVivFam" : $("#nExterior").val(),  "numIVivFam" : $("#nInterior").val(),  "colVivFam" : $("#col").val(),  "locVivFam" : $("#localidad").val(), 
        			"munVivFam" : $("#municipio").val(),  "edoVivFam" : $("#estado").val(), "cpVivFam" : $("#cp").val(),  "enlaceMaps" : $("#maps").val(), "mismoVivFam" : $('input:radio[name=mismoDomicilio]:checked').val(),
        			"calleEst" : $("#calleE").val(),  "numEEst" : $("#nExteriorE").val(), "numIEst" : $("#nInteriorE").val(),  "colEst" : $("#colE").val(),  "locEst" : $("#localidadE").val(), 
        			"munEst" : $("#municipioE").val(),  "edoEst" : $("#estadoE").val(),  "cpEst" : $("#cpE").val(),  "celular" : $("#celular").val(),  "telDomicilio" : $("#telDom").val(),
        			 "tipoTelRef" :  $("#tTelRef").val(),  "numTelRef" : $("#tNumRef").val(), "parentescoRef" : $("#tParenRef").val(),  "observacionesRef" : $("#tObserRef").val(),  "email" : $("#correo").val(),
        			 "facebook" : $("#face1").val(),  "facebook2" : $("#face2").val(),  "facebook3" : $("#face3").val(),  "formaPago" : $('input:radio[name=formaPago]:checked').val(),  "banco" : $("#banco").val(), 
        			 "cuentaDeposito" : $("#numCuenta").val(), "tarjetaDeposito" : $("#numTarjeta").val(),  "claveReferenciado" : $("#clavePago").val(), "vigenciaReferenciado" : $("#vigencia").val(), 
        			 "montoBeca" : $("#montoApoyo").val(), "finalidadApoyo" : $("#finalidadApoyo").val(),  "observaciones" : $("#observaciones").val(), "idBenefactor" : idBenefactorAsignado, "idUsuario" : $("#idUsuario").val()}
        	
        	
        	
        }else if(tBeca == 4){
        	 
        	_url= "ajaxAgregarBeneficiarioDeportiva";
        	
        	parametros = { "idPeriodo" : $("#idPeriodoD").val(),  "nombre" : $("#nombresD").val(),  "apellidoPaterno" : $("#aPaternoD").val(),  
        			"apellidoMaterno" : $("#aMaternoD").val(), "estatus" : $("#estatusD").val(), "motivoEstatus" : $("#motivoEstInactivoD").val(), "tipoBecario" : $("#tBecarioD").val(), "adscripcion" : $("#adscripcionD").val(),  
        			"idRegion" : $("#idRegionHiddenD").val(), "escuelaDeportiva" : $("#eDeportivaD").val(), "fechaIngEscDep" : $("#fechaIngresoD").val(), "nivelEduCursa" : $("#nivelED").val(), "turno" : $("#turnoD").val(), 
        			"tipoInstitucion" : $("#tipoID").val(), "grado" : $("#gradoD").val(), "nombreEdu" : $("#nombreIED").val(),"calleEdu" : $("#calleIED").val(), "numExtEdu" : $("#nExteriorIED").val(), 
        			"numIntEdu" : $("#nInteriorIED").val(), "colEdu" : $("#colIED").val(), "locEdu" : $("#localidadIED").val(),
        			"munEdu" : $("#municipioIED").val(), "edoEdu" : $("#estadoIED").val(), "cpEdu" : $("#cpIED").val(), "telEdu" : $("#telIED").val(),"promedioGeneral" : $("#pGeneralD").val(), "edad" : $("#edadD").val(), 
        			"genero" : $("#generoD").val(), "lugarNacimiento" : $("#lNacimientoD").val(), 
        			"fechaNacimiento" : $("#fechaNacimientoD").val(), "breveHistoria" : $("#bHistoriaD").val(), "integrantesFamiliares" : $("#iFamiliaresD").val(),  "ingresosFamiliares" : $("#ingresosD").val(),  
        			"calleVivFam" : $("#calleD").val(), "numEVivFam" : $("#nExteriorD").val(),  "numIVivFam" : $("#nInteriorD").val(),  "colVivFam" : $("#colD").val(),  "locVivFam" : $("#localidadD").val(), 
        			
        			"munVivFam" : $("#municipioD").val(),  "edoVivFam" : $("#estadoD").val(), "cpVivFam" : $("#cpD").val(),  "enlaceMaps" : $("#mapsD").val(), 
        
        			
        			
        			"nombreTutor" : $("#tutorND").val(),  "parentescoTutor" : $("#parentescoTD").val() , "celular" : $("#celularD").val(),  "telDomicilio" : $("#telDomD").val(),
        			 "tipoTelRef" :  $("#tTelRefD").val(),  "numTelRef" : $("#tNumRefD").val(), "parentescoRef" : $("#tParenRefD").val(),  "observacionesRef" : $("#tObserRefD").val(),  "email" : $("#correoD").val(),
        			 "facebook" : $("#face1D").val(),  "facebook2" : $("#face2D").val(),  "facebook3" : $("#face3D").val(), "ocupacionTutor" : $("#ocupacionD").val(), "hermanosInscritos" : $('input:radio[name=hermanosEscuelas]:checked').val(),
        			 "escuelaHermanosInscritos" : $("#escuelaHD").val(),    			 
        			 "montoBeca" : $("#montoApoyoD").val(), "observaciones" : $("#observacionesD").val(), "idUsuario" : $("#idUsuario").val()}
        	    	   	
        }
    	
    	
    }else if( $('input:radio[name=altaCambio]:checked').val() ==0){
    	
    	
		
    	
    	mensaje ="<p class='divRespuesta'>! Beneficiario modificado !<br></p>";
    	
    	tBeca = $("#becaSeleccionada").val();
        
		        if(tBeca== 1 || tBeca == 2 || tBeca == 3){
		        	
		        	
		        	if($("#idPeriodoH").val() != $("#idPeriodo").val() || $("#matriculaH").val() != $("#matricula").val() || $("#nombresH").val() != $("#nombres").val() || $("#aPaternoH").val() !=  $("#aPaterno").val() ||
		        			$("#aMaternoH").val() != $("#aMaterno").val() || $("#estatusH").val() != $("#estatus").val() || $("#motivoEstInactivoH").val() != $("#motivoEstInactivo").val() || $("#tBecarioH").val() != $("#tBecario").val() ||
		        			$("#adscripcionH").val() != $("#adscripcion").val() || $("#idRegionH").val() != $("#idRegionHidden").val() || $("#idCarreraH").val() != $("#idCarreraHidden").val() || $("#periodoActualH").val() != $("#periodoActual").val() ||
		        			$("#pGeneralH").val() != $("#pGeneral").val() || $("#edadH").val() != $("#edad").val() || $("#generoH").val() != $("#genero").val() || $("#lIndigenaH").val() != $("#lIndigena").val() ||
		        			$("#discapacidadH").val() != $("#discapacidad").val() || $("#edoCivilH").val() != $("#edoCivil").val() || $("#lNacimientoH").val() != $("#lNacimiento").val() || $("#fechaNacimientoH").val() !=  $("#fechaNacimiento").val() ||
		        			$("#iFamiliaresH").val() != $("#iFamiliares").val() || $("#ingresosH").val() != $("#ingresos").val() || $("#bHistoriaH").val() != $("#bHistoria").val() || $("#calleH").val() != $("#calle").val() ||
		        			$("#nExteriorH").val() != $("#nExterior").val() || $("#nInteriorH").val() != $("#nInterior").val() || $("#colH").val() != $("#col").val() || $("#localidadH").val() != $("#localidad").val() ||
		        			$("#municipioH").val() != $("#municipio").val() || 	$("#estadoH").val() != $("#estado").val() || $("#cpH").val() != $("#cp").val() || $("#mapsH").val() != $("#maps").val() ||
		        			$('#mismoDomicilioH').val() != $('input:radio[name=mismoDomicilio]:checked').val() || $("#calleEH").val() != $("#calleE").val() || $("#nExteriorEH").val() != $("#nExteriorE").val() ||
		        			$("#nInteriorEH").val() != $("#nInteriorE").val() || $("#colEH").val() != $("#colE").val() || $("#localidadEH").val() != $("#localidadE").val() || $("#municipioEH").val() != $("#municipioE").val() ||
		        			$("#estadoEH").val() != $("#estadoE").val() || $("#cpEH").val() != $("#cpE").val() || $("#celularH").val() != $("#celular").val() || $("#telDomH").val() != $("#telDom").val() ||
		        			$("#tTelRefH").val() != $("#tTelRef").val() || $("#tNumRefH").val() != $("#tNumRef").val() || $("#tParenRefH").val() != $("#tParenRef").val() || $("#tObserRefH").val() != $("#tObserRef").val() ||
		        			$("#correoH").val() != $("#correo").val() || $("#face1H").val() != $("#face1").val() || $("#face2H").val() != $("#face2").val() || $("#face3H").val() != $("#face3").val() ||
		        			$("#formaPagoH").val() != $('input:radio[name=formaPago]:checked').val() || $("#bancoH").val() != $("#banco").val() || $("#numCuentaH").val() != $("#numCuenta").val() || $("#numTarjetaH").val() != $("#numTarjeta").val() ||
		        			$("#clavePagoH").val() != $("#clavePago").val() || $("#vigenciaH").val() != $("#vigencia").val() || $("#montoApoyoH").val() != $("#montoApoyo").val() || $("#finalidadApoyoH").val() != $("#finalidadApoyo").val() ||
		        			$("#observacionesH").val() != $("#observaciones").val() ){
		        		
		        	
		        	
		        	_url= "ajaxModificarBeneficiarioBeca";
		        	
		        	parametros = { "idPeriodo" : $("#idPeriodo").val(),  "matricula" : $("#matricula").val(),  "nombre" : $("#nombres").val(),  "apellidoPaterno" : $("#aPaterno").val(),  
		        			"apellidoMaterno" : $("#aMaterno").val(), "estatus" : $("#estatus").val(), "motivoEstatus" : $("#motivoEstInactivo").val(), "tipoBecario" : $("#tBecario").val(), "adscripcion" : $("#adscripcion").val(),  
		        			"idRegion" : $("#idRegionHidden").val(), "idCarrera" : $("#idCarreraHidden").val(), "periodoActual" : $("#periodoActual").val(), "promedioGeneral" : $("#pGeneral").val(), "edad" : $("#edad").val(), 
		        			"genero" : $("#genero").val(), "lenguaIndigena" : $("#lIndigena").val(), "discapacidad" : $("#discapacidad").val(), "estadoCivil" : $("#edoCivil").val(), "lugarNacimiento" : $("#lNacimiento").val(), 
		        			"fechaNacimiento" : $("#fechaNacimiento").val(), "breveHistoria" : $("#bHistoria").val(), "integrantesFamiliares" : $("#iFamiliares").val(),  "ingresosFamiliares" : $("#ingresos").val(),  
		        			"calleVivFam" : $("#calle").val(), "numEVivFam" : $("#nExterior").val(),  "numIVivFam" : $("#nInterior").val(),  "colVivFam" : $("#col").val(),  "locVivFam" : $("#localidad").val(), 
		        			"munVivFam" : $("#municipio").val(),  "edoVivFam" : $("#estado").val(), "cpVivFam" : $("#cp").val(),  "enlaceMaps" : $("#maps").val(), "mismoVivFam" : $('input:radio[name=mismoDomicilio]:checked').val(),
		        			"calleEst" : $("#calleE").val(),  "numEEst" : $("#nExteriorE").val(), "numIEst" : $("#nInteriorE").val(),  "colEst" : $("#colE").val(),  "locEst" : $("#localidadE").val(), 
		        			"munEst" : $("#municipioE").val(),  "edoEst" : $("#estadoE").val(),  "cpEst" : $("#cpE").val(),  "celular" : $("#celular").val(),  "telDomicilio" : $("#telDom").val(),
		        			 "tipoTelRef" :  $("#tTelRef").val(),  "numTelRef" : $("#tNumRef").val(), "parentescoRef" : $("#tParenRef").val(),  "observacionesRef" : $("#tObserRef").val(),  "email" : $("#correo").val(),
		        			 "facebook" : $("#face1").val(),  "facebook2" : $("#face2").val(),  "facebook3" : $("#face3").val(),  "formaPago" : $('input:radio[name=formaPago]:checked').val(),  "banco" : $("#banco").val(), 
		        			 "cuentaDeposito" : $("#numCuenta").val(), "tarjetaDeposito" : $("#numTarjeta").val(),  "claveReferenciado" : $("#clavePago").val(), "vigenciaReferenciado" : $("#vigencia").val(), 
		        			 "montoBeca" : $("#montoApoyo").val(), "finalidadApoyo" : $("#finalidadApoyo").val(),  "observaciones" : $("#observaciones").val(), "idUsuario" : $("#idUsuario").val(), 
		        			 "idBeneficiario" : $("#idBeneficiario").val()}
		        
		        	}else{
		        		bandera = false;
		        	}
    	
    	
        }else if(tBeca == 4){
        	       	
        	if($("#idPeriodoH").val() != $("#idPeriodoD").val() || $("#nombresH").val() != $("#nombresD").val() || $("#aPaternoH").val() !=  $("#aPaternoD").val() ||
        			$("#aMaternoH").val() != $("#aMaternoD").val() || $("#estatusH").val() != $("#estatusD").val() || $("#motivoEstInactivoH").val() != $("#motivoEstInactivoD").val() || $("#tBecarioH").val() != $("#tBecarioD").val() ||
        			$("#adscripcionH").val() != $("#adscripcionD").val() || $("#idRegionH").val() != $("#idRegionHiddenD").val() || $("#eDeportivaH").val() != $("#eDeportivaD").val() || $("#fechaIngresoH").val() != $("#fechaIngresoD").val() ||
        			$("#nivelEH").val() != $("#nivelED").val() || $("#turnoDH").val() != $("#turnoD").val() || $("#tipoIDH").val() != $("#tipoID").val() || $("#gradoDH").val() != $("#gradoD").val() ||
        			$("#nombreIEDH").val() != $("#nombreIED").val() || $("#calleIEDH").val() != $("#calleIED").val() || $("#nExteriorIEDH").val() != $("#nExteriorIED").val() || $("#nInteriorIEDH").val() != $("#nInteriorIED").val() ||
        			$("#colIEDH").val() != $("#colIED").val() || $("#localidadIEDH").val() != $("#localidadIED").val() || $("#municipioIEDH").val() != $("#municipioIED").val() || $("#estadoIEDH").val() != $("#estadoIED").val() ||
        			$("#cpIEDH").val() != $("#cpIED").val() || $("#telIEDH").val() != $("#telIED").val() ||
        			
        			$("#pGeneralH").val() != $("#pGeneralD").val() || $("#edadH").val() != $("#edadD").val() || $("#generoH").val() != $("#generoD").val() ||
        			$("#lNacimientoH").val() != $("#lNacimientoD").val() || $("#fechaNacimientoH").val() !=  $("#fechaNacimientoD").val() ||
        			$("#iFamiliaresH").val() != $("#iFamiliaresD").val() || $("#ingresosH").val() != $("#ingresosD").val() || $("#bHistoriaH").val() != $("#bHistoriaD").val() || 
        			
        			$("#calleH").val() != $("#calleD").val() ||
        			$("#nExteriorH").val() != $("#nExteriorD").val() || $("#nInteriorH").val() != $("#nInteriorD").val() || $("#colH").val() != $("#colD").val() || $("#localidadH").val() != $("#localidadD").val() ||
        			
        			$("#municipioH").val() != $("#municipioD").val() || $("#estadoH").val() != $("#estadoD").val() || $("#cpH").val() != $("#cpD").val() || $("#mapsH").val() != $("#mapsD").val() ||
        			
        			$('#tutorNDH').val() != $("#tutorND").val() || $("#parentescoTDH").val() != $("#parentescoTD").val() || 
        			
        			$("#celularH").val() != $("#celularD").val() || $("#telDomH").val() != $("#telDomD").val() || $("#ocupacionH").val() != $("#ocupacionD").val() ||
        			$("#tTelRefH").val() != $("#tTelRefD").val() || $("#tNumRefH").val() != $("#tNumRefD").val() || $("#tParenRefH").val() != $("#tParenRefD").val() || $("#tObserRefH").val() != $("#tObserRefD").val() ||
        			$("#correoH").val() != $("#correoD").val() || $("#face1H").val() != $("#face1D").val() || $("#face2H").val() != $("#face2D").val() || $("#face3H").val() != $("#face3D").val() ||
        			
        			$("#hermanosEscuelasH").val() != $('input:radio[name=hermanosEscuelas]:checked').val() || $("#escuelaHDH").val() != $("#escuelaHD").val() ||
        			$("#montoApoyoH").val() != $("#montoApoyoD").val() || $("#observacionesH").val() != $("#observacionesD").val() ){
        	 
        		
        	_url= "ajaxModificarBeneficiarioDeportiva";
        	
        	parametros = { "idPeriodo" : $("#idPeriodoD").val(),  "nombre" : $("#nombresD").val(),  "apellidoPaterno" : $("#aPaternoD").val(),  
        			"apellidoMaterno" : $("#aMaternoD").val(), "estatus" : $("#estatusD").val(), "motivoEstatus" : $("#motivoEstInactivoD").val(), "tipoBecario" : $("#tBecarioD").val(), "adscripcion" : $("#adscripcionD").val(),  
        			"idRegion" : $("#idRegionHiddenD").val(), "escuelaDeportiva" : $("#eDeportivaD").val(), "fechaIngEscDep" : $("#fechaIngresoD").val(), "nivelEduCursa" : $("#nivelED").val(), "turno" : $("#turnoD").val(), 
        			"tipoInstitucion" : $("#tipoID").val(), "grado" : $("#gradoD").val(), "nombreEdu" : $("#nombreIED").val(),"calleEdu" : $("#calleIED").val(), "numExtEdu" : $("#nExteriorIED").val(), 
        			"numIntEdu" : $("#nInteriorIED").val(), "colEdu" : $("#colIED").val(), "locEdu" : $("#localidadIED").val(),
        			"munEdu" : $("#municipioIED").val(), "edoEdu" : $("#estadoIED").val(), "cpEdu" : $("#cpIED").val(), "telEdu" : $("#telIED").val(),"promedioGeneral" : $("#pGeneralD").val(), "edad" : $("#edadD").val(), 
        			"genero" : $("#generoD").val(), "lugarNacimiento" : $("#lNacimientoD").val(), 
        			"fechaNacimiento" : $("#fechaNacimientoD").val(), "breveHistoria" : $("#bHistoriaD").val(), "integrantesFamiliares" : $("#iFamiliaresD").val(),  "ingresosFamiliares" : $("#ingresosD").val(),  
        			"calleVivFam" : $("#calleD").val(), "numEVivFam" : $("#nExteriorD").val(),  "numIVivFam" : $("#nInteriorD").val(),  "colVivFam" : $("#colD").val(),  "locVivFam" : $("#localidadD").val(), 
        			
        			"munVivFam" : $("#municipioD").val(),  "edoVivFam" : $("#estadoD").val(), "cpVivFam" : $("#cpD").val(),  "enlaceMaps" : $("#mapsD").val(), 
        
        			
        			
        			"nombreTutor" : $("#tutorND").val(),  "parentescoTutor" : $("#parentescoTD").val() , "celular" : $("#celularD").val(),  "telDomicilio" : $("#telDomD").val(),
        			 "tipoTelRef" :  $("#tTelRefD").val(),  "numTelRef" : $("#tNumRefD").val(), "parentescoRef" : $("#tParenRefD").val(),  "observacionesRef" : $("#tObserRefD").val(),  "email" : $("#correoD").val(),
        			 "facebook" : $("#face1D").val(),  "facebook2" : $("#face2D").val(),  "facebook3" : $("#face3D").val(), "ocupacionTutor" : $("#ocupacionD").val(), "hermanosInscritos" : $('input:radio[name=hermanosEscuelas]:checked').val(),
        			 "escuelaHermanosInscritos" : $("#escuelaHD").val(),    			 
        			 "montoBeca" : $("#montoApoyoD").val(), "observaciones" : $("#observacionesD").val(), "idUsuario" : $("#idUsuario").val(),"idBeneficiario" : $("#idBeneficiario").val()}
        	}else{
        		bandera = false;
        	}
        	    	   	
        }
    }
    
    
	// DO POST
    if(bandera){
    	 $("#postResultDiv").html("<div class='loader'></div>");
	$.ajax({
	    		type : "POST",
			url :_url,
			data : parametros,
			success : function(result) {
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }
			    
			    if(result != "Duplicado"){
			    
				$("#postResultDiv").show();
				if(result == "Done"){
					$("#postResultDiv").html(mensaje);
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				$("#postResultDiv").delay(6000).hide(600);
				
				console.log(result);
				
				$("#becaSeleccionada").val(tBeca).trigger('change');
			    }else{
			    	$("#postResultDiv").delay(1000).hide(100);
			    	alert("La matrícula ya se encuentra registrada en este periodo");
			    }
			},
			error : function(jqXHR,e) {
			    //	alert(jqXHR.responseText);
				if (jqXHR.status != 200) {
				window.location = "/error";
				}else{
					window.location = "/login?session=false";
				}

				console.log("ERROR: ", e);				
			}
		});
    }else{
    	alert("No has realizado ningun cambio");
    }
    
});



function autocompletarBeneficiarioMod() {

	$("#idBene").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/egresos/autocompleteBeneficiarioMod",
				dataType : "json",
				data : {
					term : request.term
				//	idPeriodo : $("#idPeriodo").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre +" "+item.apellidoPaterno+" "+item.apellidoMaterno,
							value : item.idBeneficiario,
							tBeca : item.idTipoBeca,
							idPeriodo : item.idPeriodo,
							matricula : item.matricula,
							nombre : item.nombre,
							apellidoPaterno : item.apellidoPaterno,
							apellidoMaterno : item.apellidoMaterno,
							estatus : item.estatus,
							motivoEstatus : item.motivoEstatus,
							tipoBecario : item.tipoBecario,
							adscripcion : item.adscripcion,
							idRegion : item.idRegion,
							region : item.region,
						    carrera : item.carrera,
						    nivel : item.nivel,
						    modalidad : item.modalidad,
						    facultad : item.facultad,
						    idFacultad : item.idFacultad,
						    idArea : item.idArea,
						    area : item.area,
						    idCarrera : item.idCarrera,						    
						    escuelaDeportiva: item.escuelaDeportiva,
						    fechaIngEscDep : item.fechaIngEscDep,
						    nivelEduCursa : item.nivelEduCursa,
						    turno : item.turno,
						    tipoInstitucion : item.tipoInstitucion,
						    grado : item.grado,
						    nombreEdu : item.nombreEdu,
						    calleEdu : item.calleEdu,
							 numExtEdu : item.numExtEdu,
						     numIntEdu : item.numIntEdu,
						     colEdu : item.colEdu,
						     locEdu : item.locEdu,
						     munEdu : item.munEdu,
						     edoEdu : item.edoEdu,
						     cpEdu : item.cpEdu,
						     telEdu : item.telEdu,
						     periodoActual : item.periodoActual,					
						     promedioGeneral : item.promedioGeneral,						
						     edad : item.edad,
						     genero : item.genero,						
						     lenguaIndigena : item.lenguaIndigena,
						     discapacidad : item.discapacidad,
						     estadoCivil : item.estadoCivil,
						     lugarNacimiento : item.lugarNacimiento,
						     fechaNacimiento : item.fechaNacimiento,
						     breveHistoria : item.breveHistoria,
						     integrantesFamiliares : item.integrantesFamiliares,
						     ingresosFamiliares : item.ingresosFamiliares,
						     calleVivFam : item.calleVivFam,
						     numEVivFam : item.numEVivFam,
						     numIVivFam : item.numIVivFam,
						     colVivFam : item.colVivFam,
						     locVivFam : item.locVivFam,
						     munVivFam : item.munVivFam,
						     edoVivFam : item.edoVivFam,
						     cpVivFam : item.cpVivFam,
						     enlaceMaps : item.enlaceMaps,
						     mismoVivFam : item.mismoVivFam,
						     calleEst : item.calleEst,
						     numEEst : item.numEEst,
						     numIEst : item.numIEst,
						     colEst : item.colEst,
						     locEst : item.locEst,
						     munEst : item.munEst,
						     edoEst : item.edoEst,
						     cpEst : item.cpEst,
						     nombreTutor : item.nombreTutor,
						     parentescoTutor : item.parentescoTutor,
						     celular : item.celular,
						     telDomicilio : item.telDomicilio,
						     tipoTelRef : item.tipoTelRef,
						     numTelRef : item.numTelRef,
						     parentescoRef : item.parentescoRef,
						     observacionesRef : item.observacionesRef,
						     email : item.email,
						     facebook : item.facebook,
						     facebook2 : item.facebook2,
						     facebook3 : item.facebook3,
						     ocupacionTutor : item.ocupacionTutor,
						     hermanosInscritos : item.hermanosInscritos,
						     escuelaHermanosInscritos : item.escuelaHermanosInscritos,
						     
						     formaPago : item.formaPago,
						     banco : item.banco,
						     cuentaDeposito : item.cuentaDeposito,
						     tarjetaDeposito : item.tarjetaDeposito,
						     claveReferenciado : item.claveReferenciado,
						     vigenciaReferenciado : item.vigenciaReferenciado,
						     montoBeca : item.montoBeca,
						     finalidadApoyo : item.finalidadApoyo,
						     observaciones : item.observaciones,
						 //    idBenefactor : item.idBenefactor,
						     idBeneficiario : item.idBeneficiario
							};
					}));

				},
				error : function(jqXHR,textStatus) {
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			 
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			//this.value = ui.item.label;
			$("#becaSeleccionada").val(ui.item.tBeca).trigger('change');
			var datos = {
					idTipoBeca : ui.item.tBeca
				}
			if(ui.item.tBeca == 1 || ui.item.tBeca == 2 || ui.item.tBeca == 3){
				
				$("#idPeriodo").load("actualizaPeriodos", datos,function( response, status, xhr ) {			  
					  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	$("#idPeriodo").val(ui.item.idPeriodo);	
						    }
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				});
							
				$("#matricula").val(ui.item.matricula);
				$("#nombres").val(ui.item.nombre);
				$("#aPaterno").val(ui.item.apellidoPaterno);
				$("#aMaterno").val(ui.item.apellidoMaterno);
				$("#aMaterno").val(ui.item.apellidoMaterno);
				$("#estatus").val(ui.item.estatus);
				
				if(ui.item.motivoEstatus != ''){
				$("#motivoEstInactivo").show();
				$("#motivoEstInactivo").val(ui.item.motivoEstatus);
				}
				
				$("#tBecario").val(ui.item.tipoBecario);
				$("#adscripcion").val(ui.item.adscripcion);
				
				$("#idRegion").val(ui.item.region);
				$("#idRegionHidden").val(ui.item.idRegion);
				$("#valRegionHidden").val(ui.item.region);
				
				$("#idArea").val(ui.item.area);
				$("#idAreaHidden").val(ui.item.idArea);
				$("#valAreaHidden").val(ui.item.area);
				
				$("#idFac").val(ui.item.facultad);
				$("#idFacHidden").val(ui.item.idFacultad);
				$("#valFacHidden").val(ui.item.facultad);
				
				$("#nivel").val(ui.item.nivel);				
				$("#valNivelHidden").val(ui.item.nivel);
				
				$("#modalidad").val(ui.item.modalidad);				
				$("#valModalidadHidden").val(ui.item.modalidad);
				
				$("#idCarrera").val(ui.item.carrera);
				$("#idCarreraHidden").val(ui.item.idCarrera);
				$("#valCarreraHidden").val(ui.item.carrera);
				
				$("#periodoActual").val(ui.item.periodoActual);
				
				$("#pGeneral").val(ui.item.promedioGeneral);
				$("#edad").val(ui.item.edad);
				$("#genero").val(ui.item.genero);
			    
				$("#lIndigena").val(ui.item.lenguaIndigena);
				$("#discapacidad").val(ui.item.discapacidad);			    
				$("#edoCivil").val(ui.item.estadoCivil);
				
				
				$("#lNacimiento").val(ui.item.lugarNacimiento);
				$("#fechaNacimiento").val(ui.item.fechaNacimiento);			    
				$("#iFamiliares").val(ui.item.integrantesFamiliares);
				$("#ingresos").val(ui.item.ingresosFamiliares);
				$("#bHistoria").val(ui.item.breveHistoria);			    
								
				$("#calle").val(ui.item.calleVivFam);
				$("#nExterior").val(ui.item.numEVivFam);
				$("#nInterior").val(ui.item.numIVivFam);
				$("#col").val(ui.item.colVivFam);
				$("#localidad").val(ui.item.locVivFam);
				$("#municipio").val(ui.item.munVivFam);
				$("#estado").val(ui.item.edoVivFam);
				$("#cp").val(ui.item.cpVivFam);
				$("#maps").val(ui.item.enlaceMaps);
				
				
				if(ui.item.mismoVivFam == 1){
					$('input:radio[name="mismoDomicilio"][value="1"]').prop('checked', true);
					checkIdsDom('siMD');
				}else if(ui.item.mismoVivFam == 0){
					$('input:radio[name="mismoDomicilio"][value="0"]').prop('checked', true);
					checkIdsDom('noMD');
				}
				
				$("#calleE").val(ui.item.calleEst);
				$("#nExteriorE").val(ui.item.numEEst);
				$("#nInteriorE").val(ui.item.numIEst);
				$("#colE").val(ui.item.colEst);
				$("#localidadE").val(ui.item.locEst);
				$("#municipioE").val(ui.item.munEst);
				$("#estadoE").val(ui.item.edoEst);
				$("#cpE").val(ui.item.cpEst);
				
				$("#celular").val(ui.item.celular);
				$("#telDom").val(ui.item.telDomicilio);
				$("#tTelRef").val(ui.item.tipoTelRef);
				$("#tNumRef").val(ui.item.numTelRef);
				
				$("#tParenRef").val(ui.item.parentescoRef);
				$("#tObserRef").val(ui.item.observacionesRef);
				$("#correo").val(ui.item.email);
				$("#face1").val(ui.item.facebook);
				$("#face2").val(ui.item.facebook2);
				$("#face3").val(ui.item.facebook3);
				
				
				
				if(ui.item.formaPago == 1){
					$('input:radio[name="formaPago"][value="1"]').prop('checked', true);
					checkIdsFP('dFP');
				}else if(ui.item.formaPago == 0){
					$('input:radio[name="formaPago"][value="0"]').prop('checked', true);
					checkIdsFP('pFP');
				}
				 
				$("#banco").val(ui.item.banco);
				$("#numCuenta").val(ui.item.cuentaDeposito);
				$("#numTarjeta").val(ui.item.tarjetaDeposito);
				$("#clavePago").val(ui.item.claveReferenciado);
				$("#vigencia").val(ui.item.vigenciaReferenciado);
				
				$("#montoApoyo").val(ui.item.montoBeca);
			    
				$("#finalidadApoyo").val(ui.item.finalidadApoyo);
				
				$("#observaciones").val(ui.item.observaciones);
			     
			//	$("#benefactor").val(ui.item.idBenefactor);
				
				
			}else if(ui.item.tBeca == 4){
				
				//autocompletarRegionD('idRegionD','idRegionHiddenD','valRegionHiddenD','eDeportivaD');
				
				$("#idPeriodoD").load("actualizaPeriodos", datos,function( response, status, xhr ) {			  
					  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	$("#idPeriodoD").val(ui.item.idPeriodo);	
						    }
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				}); 
				
				//cambiaPeriodo('idPeriodoD','idRegionD');
								
				$("#nombresD").val(ui.item.nombre);
				$("#aPaternoD").val(ui.item.apellidoPaterno);
				$("#aMaternoD").val(ui.item.apellidoMaterno);
				$("#estatus").val(ui.item.estatus);
				
				if(ui.item.motivoEstatus != ''){
					$("#motivoEstInactivoD").show();
					$("#motivoEstInactivoD").val(ui.item.motivoEstatus);
				}
				
				$("#tBecarioD").val(ui.item.tipoBecario);
				$("#adscripcionD").val(ui.item.adscripcion);
				$("#idRegionD").val(ui.item.region);
				
				$("#idRegionD").val(ui.item.region);
				$("#idRegionHiddenD").val(ui.item.idRegion);
				$("#valRegionHiddenD").val(ui.item.region);
				
				$("#eDeportivaD").val(ui.item.escuelaDeportiva);				
				$("#fechaIngresoD").val(ui.item.fechaIngEscDep); 
			    $("#nivelED").val(ui.item.nivelEduCursa);
			    
			    $("#turnoD").val(ui.item.turno);
			    $("#tipoID").val(ui.item.tipoInstitucion);
			    $("#gradoD").val(ui.item.grado);
			    $("#nombreIED").val(ui.item.nombreEdu);
			    $("#calleIED").val(ui.item.calleEdu);
			    $("#nExteriorIED").val(ui.item.numExtEdu);
			    $("#nInteriorIED").val(ui.item.numIntEdu);
			    $("#colIED").val(ui.item.colEdu);
			    $("#localidadIED").val(ui.item.locEdu);
			    $("#municipioIED").val(ui.item.munEdu);
			    $("#estadoIED").val(ui.item.edoEdu);
			    $("#cpIED").val(ui.item.cpEdu);
			    $("#telIED").val(ui.item.telEdu);
				
			    $("#pGeneralD").val(ui.item.promedioGeneral);
			    
			    $("#edadD").val(ui.item.edad);
			    $("#generoD").val(ui.item.genero);
			    
			    
				$("#lNacimientoD").val(ui.item.lugarNacimiento);
				$("#fechaNacimientoD").val(ui.item.fechaNacimiento);			    
				$("#iFamiliaresD").val(ui.item.integrantesFamiliares);
				$("#ingresosD").val(ui.item.ingresosFamiliares);
				$("#bHistoriaD").val(ui.item.breveHistoria);		
				
				$("#calleD").val(ui.item.calleVivFam);
				$("#nExteriorD").val(ui.item.numEVivFam);
				$("#nInteriorD").val(ui.item.numIVivFam);
				$("#colD").val(ui.item.colVivFam);
				$("#localidadD").val(ui.item.locVivFam);
				$("#municipioD").val(ui.item.munVivFam);
				$("#estadoD").val(ui.item.edoVivFam);
				$("#cpD").val(ui.item.cpVivFam);
				$("#mapsD").val(ui.item.enlaceMaps);
			    
				$("#tutorND").val(ui.item.nombreTutor);
				$("#parentescoTD").val(ui.item.parentescoTutor);
				
				
				$("#celularD").val(ui.item.celular);
				$("#telDomD").val(ui.item.telDomicilio);
				$("#ocupacionD").val(ui.item.ocupacionTutor);
				
				
				$("#tTelRefD").val(ui.item.tipoTelRef);
				$("#tNumRefD").val(ui.item.numTelRef);
				
				$("#tParenRefD").val(ui.item.parentescoRef);				
				$("#tObserRefD").val(ui.item.observacionesRef);
				$("#correoD").val(ui.item.email);
				$("#face1D").val(ui.item.facebook);
				$("#face2D").val(ui.item.facebook2);
				$("#face3D").val(ui.item.facebook3);
				
				
				if(ui.item.hermanosInscritos == 1){
					$('input:radio[name="hermanosEscuelas"][value="1"]').prop('checked', true);
					checkIdsHED('hesD');
				}else if(ui.item.hermanosInscritos == 0){
					$('input:radio[name="hermanosEscuelas"][value="0"]').prop('checked', true);
					checkIdsHED('henD');
				}
				$("#escuelaHD").val(ui.item.escuelaHermanosInscritos);
				
				$("#montoApoyoD").val(ui.item.montoBeca);		    
				
				$("#observacionesD").val(ui.item.observaciones);
				 
			}
			
			$("#tBecaH").val(ui.item.tBeca);
			$("#idPeriodoH").val(ui.item.idPeriodo);
			$("#matriculaH").val(ui.item.matricula);
			$("#nombresH").val(ui.item.nombre);
			$("#aPaternoH").val(ui.item.apellidoPaterno);			
			$("#aMaternoH").val(ui.item.apellidoMaterno);
			$("#estatusH").val(ui.item.estatus);
			$("#motivoEstInactivoH").val(ui.item.motivoEstatus);
			$("#tBecarioH").val(ui.item.tipoBecario);
			$("#adscripcionH").val(ui.item.adscripcion);
			$("#idRegionH").val(ui.item.idRegion);		
			$("#idAreaH").val(ui.item.idArea);	
			$("#idFacH").val(ui.item.idFacultad);
			$("#nivelH").val(ui.item.nivel);
			$("#modalidadH").val(ui.item.modalidad);		
			$("#idCarreraH").val(ui.item.idCarrera);
			$("#eDeportivaH").val(ui.item.escuelaDeportiva);			
			$("#fechaIngresoH").val(ui.item.fechaIngEscDep); 
		    $("#nivelEH").val(ui.item.nivelEduCursa); 
		    $("#turnoDH").val(ui.item.turno);
		    $("#tipoIDH").val(ui.item.tipoInstitucion);
		    $("#gradoDH").val(ui.item.grado);
		    $("#nombreIEDH").val(ui.item.nombreEdu);
		    $("#calleIEDH").val(ui.item.calleEdu);
		    $("#nExteriorIEDH").val(ui.item.numExtEdu);
		    $("#nInteriorIEDH").val(ui.item.numIntEdu);
		    $("#colIEDH").val(ui.item.colEdu);
		    $("#localidadIEDH").val(ui.item.locEdu);
		    $("#municipioIEDH").val(ui.item.munEdu);
		    $("#estadoIEDH").val(ui.item.edoEdu);
		    $("#cpIEDH").val(ui.item.cpEdu);
		    $("#telIEDH").val(ui.item.telEdu);
		    $("#periodoActualH").val(ui.item.periodoActual);
		    
		    $("#pGeneralH").val(ui.item.promedioGeneral);
		    $("#edadH").val(ui.item.edad);
		    $("#generoH").val(ui.item.genero);
		    $("#lIndigenaH").val(ui.item.lenguaIndigena);
			$("#discapacidadH").val(ui.item.discapacidad);
			$("#edoCivilH").val(ui.item.estadoCivil);
			
			$("#lNacimientoH").val(ui.item.lugarNacimiento);
			$("#fechaNacimientoH").val(ui.item.fechaNacimiento);			    
			$("#iFamiliaresH").val(ui.item.integrantesFamiliares);
			$("#ingresosH").val(ui.item.ingresosFamiliares);
			$("#bHistoriaH").val(ui.item.breveHistoria);	
			
			$("#calleH").val(ui.item.calleVivFam);
			$("#nExteriorH").val(ui.item.numEVivFam);
			$("#nInteriorH").val(ui.item.numIVivFam);
			$("#colH").val(ui.item.colVivFam);
			$("#localidadH").val(ui.item.locVivFam);
			$("#municipioH").val(ui.item.munVivFam);
			$("#estadoH").val(ui.item.edoVivFam);
			$("#cpH").val(ui.item.cpVivFam);
			$("#mapsH").val(ui.item.enlaceMaps);
			
			$('#mismoDomicilioH').val(ui.item.mismoVivFam);
			
			$("#calleEH").val(ui.item.calleEst);
			$("#nExteriorEH").val(ui.item.numEEst);
			$("#nInteriorEH").val(ui.item.numIEst);
			$("#colEH").val(ui.item.colEst);
			$("#localidadEH").val(ui.item.locEst);
			$("#municipioEH").val(ui.item.munEst);
			$("#estadoEH").val(ui.item.edoEst);
			$("#cpEH").val(ui.item.cpEst);
			$("#tutorNDH").val(ui.item.nombreTutor);
			$("#parentescoTDH").val(ui.item.parentescoTutor);
			
			$("#celularH").val(ui.item.celular);
			$("#telDomH").val(ui.item.telDomicilio);
			$("#ocupacionH").val(ui.item.ocupacionTutor);
			
			
			$("#tTelRefH").val(ui.item.tipoTelRef);
			$("#tNumRefH").val(ui.item.numTelRef);
			
			$("#tParenRefH").val(ui.item.parentescoRef);
			$("#tObserRefH").val(ui.item.observacionesRef);
			$("#correoH").val(ui.item.email);
			$("#face1H").val(ui.item.facebook);
			$("#face2H").val(ui.item.facebook2);
			$("#face3H").val(ui.item.facebook3);
			$('#hermanosEscuelasH').val(ui.item.hermanosInscritos);
			$("#escuelaHDH").val(ui.item.escuelaHermanosInscritos);
			
			$("#formaPagoH").val(ui.item.formaPago);
			$("#bancoH").val(ui.item.banco);
			$("#numCuentaH").val(ui.item.cuentaDeposito);
			$("#numTarjetaH").val(ui.item.tarjetaDeposito);
			$("#clavePagoH").val(ui.item.claveReferenciado);
			$("#vigenciaH").val(ui.item.vigenciaReferenciado);			
			$("#montoApoyoH").val(ui.item.montoBeca);		    
			$("#finalidadApoyoH").val(ui.item.finalidadApoyo);			
			$("#observacionesH").val(ui.item.observaciones);		     
		//	$("#benefactorH").val(ui.item.idBenefactor);
			$("#idBeneficiario").val(ui.item.idBeneficiario);
		//	$("#"+idRegionHidden).val(ui.item.value);
			return false;
		},
		minLength: 0
		

	});
}


function checkIdAccion(id){
	$("#idBene").val('');
	$("#becaSeleccionada").val('');
	$("#datosBecasDeportivas").hide();
	$("#datosBecas").hide();
	$("#btn-guardar").hide();
	if($("#"+id).val() == '1'){
		$("#becaAlta").show();
		$("#completeCambio").hide();				
	}else if($("#"+id).val() == '0'){
		$("#divBenficiarioAsig").hide();
		$("#becaAlta").hide();
		$("#completeCambio").show();
	}
}

function autocompleteDonanteBecas() {

	$("#benefactor").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/ingresos/autocompleteBenefactorNoPatrocinador",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#idPeriodo").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						return {
							label: item.nombreCompletoDon,
							
							value : item.idDonativo							
						};
					}));

				},
				error : function(jqXHR,textStatus) {
					alert(jqXHR.responseText);
					if(jqXHR.status==200 && textStatus== "parsererror"){
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus : function() {
			 
			// prevent value inserted on focus
			return false;
		},
		select : function(event, ui) {
			this.value = ui.item.label;		
			if(ui.item.value=='Sin donante'){
			$("#idBenefactorHidden").val(0);
			}else{
				$("#idBenefactorHidden").val(ui.item.value);
			}
			$("#valBenefactorHidden").val(this.value);
		
			return false;
		},
		minLength: 0
		

	});
}

