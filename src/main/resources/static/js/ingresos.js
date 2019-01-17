$( document ).ready(function() {
	$('input.donativosRequired').prop('required', true);
	$('select.donativosRequired').prop('required', true);
	$('textarea.donativosRequired').prop('required', true);
	
	/*$("textarea.vacias").val('');
	$("input.vacias").val('');
	$("select.vacias").val(1);*/
	
/*	$('input.patrocRequired').prop('required', false);
	$('select.patrocRequired').prop('required', false);
	$('textarea.patrocRequired').prop('required', false);*/
});



$("#tDonativo").change(function() {
	
	$("textarea.vacias").val('');
	$("input.vacias").val('');
	$("select.vacias").val(1);
	$("textarea.vacias").val('');
	$("input.vacias").val('');
	$("select.selectInicial").val("");
	$("#anioQuincena").val('2019');
/*	$("#tcTipoDon").val('Crédito');
	$("#tcTipoDonaDon").val('Recurrente');
	*/
	
	
	if($("#tDonativo").val() == 1){
		$('input:radio[name=mismoDF]')[0].checked = true;
		$("#medioCobroDon").val(1).trigger('change');
		
		$("#divBeneAsignado").show();
		$("#divBotonGuardar").show();		
		
		$("#idRegionDon").prop('disabled',true);
		$("#idBeneficiarioDon").prop('disabled',true);
		$("#idCampaniaDon").prop('disabled',true);
		
		if( $('input:radio[name=altaCambioDon]:checked').val() ==1){
		$("#donante").show();
		$("#patrocinadores").hide();		
		$("#completeCambio").hide();
		}else{
			$("#divBotonGuardar").hide();
			$("#lblAutoDon").html('Nombre de donante');			
			$("#donante").hide();
			$("#patrocinadores").hide();
		}
		
		$('input.donativosRequired').prop('required', true);
		$('select.donativosRequired').prop('required', true);
		$('textarea.donativosRequired').prop('required', true);
		
		$('input.patrocRequired').prop('required', false);
		$('select.patrocRequired').prop('required', false);
		$('textarea.patrocRequired').prop('required', false);
	}else{
		$('input:radio[name=mismoDFP]')[0].checked = true;
		$('input.mcNominaDonClass').prop('required', false);
		$('select.mcNominaDonClass').prop('required', false);
		$('textarea.mcNominaDonClass').prop('required', false);
		
		$('input.mcDepTransDonClass').prop('required', false);
		$('select.mcDepTransDonClass').prop('required', false);
		$('textarea.mcDepTransDonClass').prop('required', false);
		
		$('input.mcTarjetaCreditoDonClass').prop('required', false);
		$('select.mcTarjetaCreditoDonClass').prop('required', false);
		$('textarea.mcTarjetaCreditoDonClass').prop('required', false);
		
		
		$("#idRegionP").prop('disabled',true);
		$("#donante").hide();
		if( $('input:radio[name=altaCambioDon]:checked').val() ==1){
		
		$("#patrocinadores").show();
		$("#completeCambio").hide();
		}else{		
			$("#divBotonGuardar").hide();			
			$("#patrocinadores").hide();
			$("#lblAutoDon").html('Nombre de patrocinador');
		}
		
		$('input.donativosRequired').prop('required', false);
		$('select.donativosRequired').prop('required', false);
		$('textarea.donativosRequired').prop('required', false);
		
		$('input.patrocRequired').prop('required', true);
		$('select.patrocRequired').prop('required', true);
		$('textarea.patrocRequired').prop('required', true);
	}
	

	
});

function cambiaPeriodoDon(id,idRegion){

	if($("#"+id).val() > 0){
	$("#"+idRegion).prop('disabled',false);
	$("#idBeneficiarioDon").prop('disabled',false);
	$("#idBeneficiarioDon").val('');
	$("#idBeneficiarioHidden").val(''); 
	$("#valBeneficiarioHidden").val('');
	$("#"+idRegion).attr("placeholder","Escribe y selecciona una opción");
	}

}


function checkInicioDonante(id){
	$("#idDon").val('');
	$("#tipoDonativo").show();
	$("#patrocinadores").hide();
	
	
	if($("#"+id).val() == '1'){
		
		$("#donativoForm")[0].reset();
		$('input:radio[name=mismoDF]')[0].checked = true;
		$("#donante").show();	
		$("#lblAutoDon").html('Nombre de donante');
		//$("#tDonativo").val(1);
		$("#completeCambio").hide();	
		$("#tDonativo").val(1).trigger('change');
	}else if($("#"+id).val() == '0'){
		$('input:radio[name=mismoDFP]')[0].checked = true;
		$("#donante").hide();
		$("#divBotonGuardar").hide();
		if($("#tDonativo").val() == 1){
			$("#lblAutoDon").html('Nombre de donante');
		}else{
			$("#lblAutoDon").html('Nombre de patrocinador');
		}
	
		$("#completeCambio").show();
	}
}


$("#medioCobroDon").change(function() {
	
	$("#numPersonalDon").val('');
	$("#depAdscripcionDon").val('');
	$("#donaQnaDon").val('');
	$("#numQuincenasDon").val('');
	$("#idQuincenaHidden").val('');
	$("#donaTotalDon").val('');
	$("#quincenaInicioDon").val('');
	$("#idCuentaBanHidden").val('');
	$("#cscBanDon").val('');
	$("#depTraReferenciaDon").val('');
	$("#donaTotalDTDon").val('');
	$("#numPagosDTDon").val('');
	$("#importePagoDTDon").val('');
	$("#tcBanDon").val('');
	$("#tcNombreTarHabDon").val('');    			
	$("#tcRedDon").val('Visa');    			
	$("#tcTipoDon").val('Crédito');    			
	$("#tcNumTarjetaDon").val('');    			
	$("#tcFechaMesDon").val(''); 
	$("#tcFechaAnioDon").val(''); 
	$("#tcTipoDonaDon").val('Recurrente');
	$("#tcfechaIniAporDon").val('');
	$("#tcNumPagosDon").val('');
	$("#tcDonaTotalDon").val('');
	$("#tcImportePagoDon").val(''); 
	
	
	if($("#medioCobroDon").val() == 1){
		$("#mcNominaDon").show();
		$("#mcDepTransDon").hide();
		$("#mcTarjetaCreditoDon").hide();
		
		$('input.mcNominaDonClass').prop('required', true);
		$('select.mcNominaDonClass').prop('required', true);
		$('textarea.mcNominaDonClass').prop('required', true);
		
		$('input.mcDepTransDonClass').prop('required', false);
		$('select.mcDepTransDonClass').prop('required', false);
		$('textarea.mcDepTransDonClass').prop('required', false);
		
		$('input.mcTarjetaCreditoDonClass').prop('required', false);
		$('select.mcTarjetaCreditoDonClass').prop('required', false);
		$('textarea.mcTarjetaCreditoDonClass').prop('required', false);
	}else if($("#medioCobroDon").val() == 2){
		$("#mcNominaDon").hide();
		$("#mcDepTransDon").show();
		$("#mcTarjetaCreditoDon").hide();
		
		$('input.mcNominaDonClass').prop('required', false);
		$('select.mcNominaDonClass').prop('required', false);
		$('textarea.mcNominaDonClass').prop('required', false);
		
		$('input.mcDepTransDonClass').prop('required', true);
		$('select.mcDepTransDonClass').prop('required', true);
		$('textarea.mcDepTransDonClass').prop('required', true);
		
		$('input.mcTarjetaCreditoDonClass').prop('required', false);
		$('select.mcTarjetaCreditoDonClass').prop('required', false);
		$('textarea.mcTarjetaCreditoDonClass').prop('required', false);
	}else if($("#medioCobroDon").val() == 3){
		$("#mcNominaDon").hide();
		$("#mcDepTransDon").hide();
		$("#mcTarjetaCreditoDon").show();
		
		$('input.mcNominaDonClass').prop('required', false);
		$('select.mcNominaDonClass').prop('required', false);
		$('textarea.mcNominaDonClass').prop('required', false);
		
		$('input.mcDepTransDonClass').prop('required', false);
		$('select.mcDepTransDonClass').prop('required', false);
		$('textarea.mcDepTransDonClass').prop('required', false);
		
		$('input.mcTarjetaCreditoDonClass').prop('required', true);
		$('select.mcTarjetaCreditoDonClass').prop('required', true);
		$('textarea.mcTarjetaCreditoDonClass').prop('required', true);
	}
	
});

$("#tcTipoDonaDon").change(function() {
	$("#tcNumPagosDon").val('');
	$("#tcImportePagoDon").val('');
	if($("#tcTipoDonaDon").val()=="Recurrente"){
		$("#nPagoTDon").show();
		$("#tcNumPagosDon").prop('required', true);		
	}else if($("#tcTipoDonaDon").val()=="Único"){
		$("#nPagoTDon").hide();
		$("#tcNumPagosDon").prop('required', false);
		$("#tcNumPagosDon").val(0);
		
	}
});


function autocompletarRegionDon(idPeriodo,idRegion,idRegionHidden,valRegionHidden,idCampania) {

	$("#"+idRegion).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteRegion",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#"+idPeriodo).val()
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
			
			if(idCampania!=''){
			
			$("#"+idCampania).prop('disabled',false);
			var datos = {
					idRegion : $("#"+idRegionHidden).val()
				}
			$("#"+idCampania).load("actualizaCampanias", datos,function( response, status, xhr ) {			  
				  if(response.includes("Sesión inactiva")){				 
					 window.location = "/login?session=false";
					    }
				if(xhr.status==200 && xhr.statusText== "parsererror"){
					window.location = "/login?session=false";
				}
			});

			}
			
			return false;
		},
		minLength: 0
		

	});
}


function autocompletarQuincenaDon() {

	$("#quincenaInicioDon").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteQuincena",
				dataType : "json",
				data : {
					term : request.term					
				},
				success : function(data) {

					response($.map(data, function(item) {
						return {
		//					label: item.nombre + " - " +item.anio,
							label: item.nombre ,
							//label : item.nombre,
							value : item.idQuincena
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
			$("#idQuincenaHidden").val(ui.item.value);
			$("#valQuincenaHidden").val(this.value);
			
			return false;
		},
		minLength: 0
		

	});
}


function autocompletarCuentaBanDon() {

	$("#cscBanDon").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/catalogos/autocompleteCuentaBancaria",
				dataType : "json",
				data : {
					term : request.term					
				},
				success : function(data) {

					response($.map(data, function(item) {
						return {
							label: item.nombre + " / " +item.numeroCuenta +" / "+item.sucursal+" / "+item.clabe,
							
							value : item.idCuentasBancarias							
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
			$("#idCuentaBanHidden").val(ui.item.value);
			$("#valCuentaBanHidden").val(this.value);
			
			return false;
		},
		minLength: 0
		

	});
}

function autocompleteBeneficiarioDon() {

	$("#idBeneficiarioDon").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/egresos/autocompleteBeneficiarioNoDepor",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#idPeriodoDon").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						return {
							label: item.nombreCompletoBene,
							
							value : item.idBeneficiario							
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
			if(ui.item.value=='Sin beneficiario'){
				$("#idBeneficiarioHidden").val(0);
				}else{
				$("#idBeneficiarioHidden").val(ui.item.value);
				}			
			$("#valBeneficiarioHidden").val(this.value);
			
			return false;
		},
		minLength: 0
		

	});
}


function valorBlur(idInput, idIDHidden, idValHidden){
	if($("#"+idInput).val() != $("#"+idValHidden).val()){
		$("#"+idIDHidden).val('');
		$("#"+idInput).val('');
		$("#"+idValHidden).val('');
	}
}



$("#estatusDon").change(function(event) {
	if ($("#estatusDon").val() == 0) {
		$("#motivosEstatusDon").show();
		$("#motivoEstInactivoDon").prop('required', true);
	} else {
		$("#motivosEstatusDon").hide();
		$("#motivoEstInactivoDon").prop('required', false);
	}
});


function calcularCobro(idCobroTotal, idNumCobros, idCobroResultante){
	if($("#"+idCobroTotal).val() != '' && $("#"+idNumCobros).val() != '' && $("#"+idCobroTotal).val() != 0 && $("#"+idNumCobros).val() != 0){
		cobroResultante = $("#"+idCobroTotal).val() / $("#"+idNumCobros).val();
		$("#"+idCobroResultante).val(cobroResultante.toFixed(2));
	}else{
		$("#"+idCobroResultante).val('');
	}
}


$("#donativoForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
    event.preventDefault();
  
    tDonativo = $("#tDonativo").val();
    
    
    if(tDonativo== 1){
    	if($("#medioCobroDon").val()==1){
    		numPersonal = $("#numPersonalDon").val();
    		dependenciaAdscripcion = $("#depAdscripcionDon").val();
    		donativoQuincenal = $("#donaQnaDon").val();
    		numQuincenas = $("#numQuincenasDon").val();
    		idQuincenaInicio = $("#idQuincenaHidden").val();
    		anioQuincena = $("#anioQuincena").val();
    		
    		idCuentasBancarias = 0;
    		referencia = "";
    		
    		banco = "";
			nombreTarjetahabiente = "";    			
			red = "";    			
			tipoTarjeta = "";    			
			numTarjeta = "";    			
			mesVencimiento = 0; 
    		anioVencimiento = 0; 
    		tipoDonativo = "";
			mesInicioAportacion = 0;
    		
    		donativoTotal = $("#donaTotalDon").val();
    		numPagos = 0;
    		importePago = 0;
    	}else if($("#medioCobroDon").val()==2){
    		numPersonal = "";
    		dependenciaAdscripcion = "";
    		donativoQuincenal = 0;
    		numQuincenas = 0;
    		idQuincenaInicio = 0;
    		anioQuincena = "";
    		
    		idCuentasBancarias = $("#idCuentaBanHidden").val();
    		referencia = $("#depTraReferenciaDon").val();
    		
    		banco = "";
			nombreTarjetahabiente = "";    			
			red = "";    			
			tipoTarjeta = "";    			
			numTarjeta = "";    			
			mesVencimiento = 0; 
    		anioVencimiento = 0; 
    		tipoDonativo = "";
			mesInicioAportacion = 0;
    		
    		donativoTotal = $("#donaTotalDTDon").val();
    		numPagos = $("#numPagosDTDon").val();
    		importePago = $("#importePagoDTDon").val();
    	}else if($("#medioCobroDon").val()==3){
    		numPersonal = "";
    		dependenciaAdscripcion = "";
    		donativoQuincenal = 0;
    		numQuincenas = 0;
    		idQuincenaInicio = 0;
    		anioQuincena = "";
    		
    		idCuentasBancarias = 0;
    		referencia = "";
    		
    		banco = $("#tcBanDon").val();
			nombreTarjetahabiente = $("#tcNombreTarHabDon").val();    			
			red = $("#tcRedDon").val();    			
			tipoTarjeta = $("#tcTipoDon").val();    			
			numTarjeta = $("#tcNumTarjetaDon").val();    			
			mesVencimiento = $("#tcFechaMesDon").val(); 
    		anioVencimiento = $("#tcFechaAnioDon").val(); 
    		tipoDonativo = $("#tcTipoDonaDon").val();
			mesInicioAportacion = $("#tcfechaIniAporDon").val();
    		
    		numPagos = $("#tcNumPagosDon").val();
    		donativoTotal = $("#tcDonaTotalDon").val();
    		importePago = $("#tcImportePagoDon").val(); 
    	}
    	
    	
    	
    }
  
    
    bandera = true;
   
    if( $('input:radio[name=altaCambioDon]:checked').val() ==1){
    	
    	
        
        if(tDonativo== 1){
        	
        //	if($("#idBeneficiarioHidden").val() == ''){
        //		idBeneficiarioAsignado = null;
        //	}else{
        		idBeneficiarioAsignado = $("#idBeneficiarioHidden").val();
       // 	}
        	
        	_url= "ajaxAgregarDonante";
        	
        	mensaje ="<p class='divRespuesta'>! Donante agregado !<br></p>";
        	
        	
        	
        	
        	
        	parametros = { "idPeriodo" : $("#idPeriodoDon").val(),  "razonSocial" : $("#razonSocialDon").val(),  "nombre" : $("#nombresDon").val(),  "apellidoPaterno" : $("#aPaternoDon").val(),  
        			"apellidoMaterno" : $("#aMaternoDon").val(), "estatus" : $("#estatusDon").val(), "motivoEstatus" : $("#motivoEstInactivoDon").val(),  "adscripcion" : $("#adscripcionDon").val(), "tipoDonante" : $("#tDonanteDon").val(),  
        			"idRegion" : $("#idRegionHiddenDon").val(), "sector" : $("#sectorDon").val(), "idCampania" : $("#idCampaniaDon").val(), "medioAutorizacion" : $("#medioAutorizacionDon").val(), "observacionesMedioAutorizacion" : $("#observacionesMADon").val(),        			
        			"medioCobro" : $("#medioCobroDon").val(), "numPersonal" : numPersonal, "dependenciaAdscripcion" : dependenciaAdscripcion,        			
        			"donativoTotal" : donativoTotal,         			
        			"donativoQuincenal" : donativoQuincenal,        			
        			"numQuincenas" : numQuincenas,         			
        			"idQuincenaInicio" : idQuincenaInicio,  
        			"anioQuincena" : anioQuincena,
        			"idCuentasBancarias" : idCuentasBancarias,        			
        			"referencia" : referencia,       			
        			"numPagos" : numPagos,        			
        			"importeNumPagos" : importePago,        			
        			"banco" : banco,
        			"nombreTarjetahabiente" : nombreTarjetahabiente,        			
        			"red" : red,         			
        			"tipoTarjeta" : tipoTarjeta,        			
        			"numTarjeta" : numTarjeta,        			
        			"mesVencimiento" : mesVencimiento, "anioVencimiento" : anioVencimiento, "tipoDonativo" : tipoDonativo,
        			"mesInicioAportacion" : mesInicioAportacion, 
        			"email" : $("#correoDon").val(), "celular" : $("#celularDon").val(),  "telefono1" : $("#tel1Don").val(), "telefono2" : $("#tel2Don").val(),
        			"calle" : $("#calleDon").val(), "numE" : $("#nExteriorDon").val(),  "numI" : $("#nInteriorDon").val(),  "col" : $("#colDon").val(),  "loc" : $("#localidadDon").val(), 
        			"mun" : $("#municipioDon").val(),  "edo" : $("#estadoDon").val(), "cp" : $("#cpDon").val(),
        			"calleFiscal" : $("#calleFDon").val(),  "numEFiscal" : $("#nExteriorFDon").val(), "numIFiscal" : $("#nInteriorFDon").val(),  "colFiscal" : $("#colFDon").val(),  "locFiscal" : $("#localidadFDon").val(), 
        			"munFiscal" : $("#municipioFDon").val(),  "edoFiscal" : $("#estadoFDon").val(),  "cpFiscal" : $("#cpFDon").val(),  "rfc" : $("#rfcFDon").val(),
        			"observaciones" : $("#observacionesDon").val(), "idBeneficiarioAsignado" : idBeneficiarioAsignado, "idUsuario" : $("#idUsuario").val()}
        	
        
        	
        }else if(tDonativo== 2){
        	_url= "ajaxAgregarPatrocinador";
        	
        	mensaje ="<p class='divRespuesta'>! Patrocinador agregado !<br></p>";
        	
        	parametros = { "idPeriodo" : $("#idPeriodoP").val(),  "razonSocial" : $("#razonSocialP").val(),  "nombre" : $("#nombresP").val(),  "apellidoPaterno" : $("#aPaternoP").val(),  
        			"apellidoMaterno" : $("#aMaternoP").val(), "adscripcion" : $("#adscripcionP").val(), "idRegion" : $("#idRegionHiddenP").val(), "sector" : $("#sectorP").val(), 
        			"descripcionDonativo": $("#descripcionDP").val(), "email": $("#correoP").val(), "celular" : $("#celularP").val(),  "telefono1" : $("#tel1P").val(), "telefono2" : $("#tel2P").val(),
        			"calle" : $("#calleP").val(), "numE" : $("#nExteriorP").val(),  "numI" : $("#nInteriorP").val(),  "col" : $("#colP").val(),  "loc" : $("#localidadP").val(), 
        			"mun" : $("#municipioP").val(),  "edo" : $("#estadoP").val(), "cp" : $("#cpP").val(),	"calleFiscal" : $("#calleFP").val(), 
        			"numEFiscal" : $("#nExteriorFP").val(), "numIFiscal" : $("#nInteriorFP").val(),  "colFiscal" : $("#colFP").val(),  "locFiscal" : $("#localidadFP").val(), 
        			"munFiscal" : $("#municipioFP").val(),  "edoFiscal" : $("#estadoFP").val(),  "cpFiscal" : $("#cpFP").val(),  "rfc" : $("#rfcFP").val(),
        			"observaciones" : $("#observacionesP").val(),"idUsuario" : $("#idUsuario").val()
        			}
        }
    	
    	
    }else if( $('input:radio[name=altaCambioDon]:checked').val() ==0){
    	

        
		        if(tDonativo== 1){
		        	
		        	
		        	mensaje ="<p class='divRespuesta'>! Donante modificado !<br></p>";
		        	if($("#idPeriodoDonH").val() != $("#idPeriodoDon").val() || $("#razonSocialDonH").val() != $("#razonSocialDon").val() || $("#nombresDonH").val() != $("#nombresDon").val() || $("#aPaternoDonH").val() != $("#aPaternoDon").val() ||
						$("#aMaternoDonH").val() != $("#aMaternoDon").val()  || $("#estatusDonH").val() != $("#estatusDon").val()  || $("#motivoEstInactivoDonH").val() != $("#motivoEstInactivoDon").val()  ||
						$("#tDonanteDonH").val() != $("#tDonanteDon").val()  || $("#adscripcionDonH").val() != $("#adscripcionDon").val()  ||
						$("#idRegionHiddenDonH").val() != $("#idRegionHiddenDon").val()  || $("#sectorDonH").val() != $("#sectorDon").val()  || $("#idCampaniaDonH").val() != $("#idCampaniaDon").val()  ||
						$("#medioAutorizacionDonH").val() != $("#medioAutorizacionDon").val()  || $("#observacionesMADonH").val() != $("#observacionesMADon").val()  || $("#medioCobroDonH").val() != $("#medioCobroDon").val()  ||
					    $("#numPersonalDonH").val() != numPersonal || $("#depAdscripcionDonH").val() != dependenciaAdscripcion  || $("#donaTotalDonH").val() != donativoTotal  ||
						$("#donaQnaDonH").val() != donativoQuincenal  || $("#numQuincenasDonH").val() != numQuincenas  || $("#idQuincenaHiddenH").val() != idQuincenaInicio  || $("#anioQuincenaH").val() != anioQuincena,
						$("#idCuentaBanHidden").val() != idCuentasBancarias  || $("#depTraReferenciaDonH").val() != referencia || $("#donaTotalDTDonH").val() != donativoTotal ||
						$("#numPagosDTDonH").val() != numPagos  || $("#importePagoDTDonH").val() != importePago  || $("#tcBanDonH").val() != banco  ||
						$("#tcNombreTarHabDonH").val() != nombreTarjetahabiente || $("#tcRedDonH").val() != red || $("#tcTipoDonH").val() != tipoTarjeta ||
						$("#tcNumTarjetaDonH").val() != numTarjeta ||	$("#tcFechaMesDonH").val() != mesVencimiento  || $("#tcFechaAnioDonH").val() != anioVencimiento ||
						$("#tcTipoDonaDonH").val() != tipoDonativo  ||	$("#tcNumPagosDonH").val() != numPagos  || $("#tcDonaTotalDonH").val() != donativoTotal ||
						$("#tcImportePagoDonH").val() != importePago || $("#tcfechaIniAporDonH").val() != mesInicioAportacion || $("#correoDonH").val() != $("#correoDon").val()  ||
						$("#celularDonH").val() != $("#celularDon").val()  || $("#tel1DonH").val() != $("#tel1Don").val()  || $("#tel2DonH").val() != $("#tel2Don").val()  || $("#calleDonH").val() != $("#calleDon").val()  ||
						$("#nExteriorDonH").val() != $("#nExteriorDon").val()  || $("#nInteriorDonH").val() != $("#nInteriorDon").val()  || $("#colDonH").val() != $("#colDon").val()  || $("#localidadDonH").val() != $("#localidadDon").val()  ||
						$("#municipioDonH").val() != $("#municipioDon").val()  || $("#estadoDonH").val() != $("#estadoDon").val()  || $("#cpDonH").val() != $("#cpDon").val()  || $("#calleFDonH").val() != $("#calleFDon").val()  ||
						$("#nExteriorFDonH").val() != $("#nExteriorFDon").val()  || $("#nInteriorFDonH").val() != $("#nInteriorFDon").val()  || $("#colFDonH").val() != $("#colFDon").val()  || $("#localidadFDonH").val() != $("#localidadFDon").val()  ||
						$("#municipioFDonH").val() != $("#municipioFDon").val()  || $("#estadoFDonH").val() != $("#estadoFDon").val()  || $("#cpFDonH").val() != $("#cpFDon").val()  || $("#rfcFDonH").val() != $("#rfcFDon").val()  ||
						$("#observacionesDonH").val() != $("#observacionesDon").val() ){
		        		
		        	
		        	
		        	_url= "ajaxModificarDonante";
		        	
		        	parametros = { "idPeriodo" : $("#idPeriodoDon").val(),  "razonSocial" : $("#razonSocialDon").val(),  "nombre" : $("#nombresDon").val(),  "apellidoPaterno" : $("#aPaternoDon").val(),  
		        			"apellidoMaterno" : $("#aMaternoDon").val(), "estatus" : $("#estatusDon").val(), "motivoEstatus" : $("#motivoEstInactivoDon").val(),  "adscripcion" : $("#adscripcionDon").val(), "tipoDonante" : $("#tDonanteDon").val(),  
		        			"idRegion" : $("#idRegionHiddenDon").val(), "sector" : $("#sectorDon").val(), "idCampania" : $("#idCampaniaDon").val(), "medioAutorizacion" : $("#medioAutorizacionDon").val(), "observacionesMedioAutorizacion" : $("#observacionesMADon").val(),
		        			"medioCobro" : $("#medioCobroDon").val(), "numPersonal" : numPersonal, "dependenciaAdscripcion" : dependenciaAdscripcion,"donativoTotal" : donativoTotal,"donativoQuincenal" : donativoQuincenal, 
		        			"numQuincenas" : numQuincenas,"idQuincenaInicio" : idQuincenaInicio, "anioQuincena" : anioQuincena, "idCuentasBancarias" : idCuentasBancarias,"referencia" : referencia,"numPagos" : numPagos,"importeNumPagos" : importePago,		        			
		        			"banco" : banco,"nombreTarjetahabiente" : nombreTarjetahabiente,"red" : red,"tipoTarjeta" : tipoTarjeta,"numTarjeta" : numTarjeta,"mesVencimiento" : mesVencimiento, "anioVencimiento" : anioVencimiento, "tipoDonativo" : tipoDonativo,        			
		        			"mesInicioAportacion" : mesInicioAportacion,
		        			"email" : $("#correoDon").val(), "celular" : $("#celularDon").val(),  "telefono1" : $("#tel1Don").val(), "telefono2" : $("#tel2Don").val(),
		        			"calle" : $("#calleDon").val(), "numE" : $("#nExteriorDon").val(),  "numI" : $("#nInteriorDon").val(),  "col" : $("#colDon").val(),  "loc" : $("#localidadDon").val(), 
		        			"mun" : $("#municipioDon").val(),  "edo" : $("#estadoDon").val(), "cp" : $("#cpDon").val(),
		        			"calleFiscal" : $("#calleFDon").val(),  "numEFiscal" : $("#nExteriorFDon").val(), "numIFiscal" : $("#nInteriorFDon").val(),  "colFiscal" : $("#colFDon").val(),  "locFiscal" : $("#localidadFDon").val(), 
		        			"munFiscal" : $("#municipioFDon").val(),  "edoFiscal" : $("#estadoFDon").val(),  "cpFiscal" : $("#cpFDon").val(),  "rfc" : $("#rfcFDon").val(),
		        			"observaciones" : $("#observacionesDon").val(), "idUsuario" : $("#idUsuario").val(), "idDonativo" : $("#idDonativoH").val()}
		        
		        	}else{
		        		bandera = false;
		        	}
    	
    	
        }else  if(tDonativo== 2){
        	
        	
        	mensaje ="<p class='divRespuesta'>! Patrocinador modificado !<br></p>";
        	if($("#idPeriodoDonH").val() != $("#idPeriodoP").val() || $("#razonSocialDonH").val() != $("#razonSocialP").val() || $("#nombresDonH").val() != $("#nombresP").val() || $("#aPaternoDonH").val() != $("#aPaternoP").val() ||
				$("#aMaternoDonH").val() != $("#aMaternoP").val()  ||
				$("#adscripcionDonH").val() != $("#adscripcionP").val()  || $("#descripcionDPH").val() != $("#descripcionDP").val() || 
				$("#idRegionHiddenDonH").val() != $("#idRegionHiddenP").val()  || $("#sectorDonH").val() != $("#sectorP").val()  ||  $("#correoDonH").val() != $("#correoP").val()  ||
				$("#celularDonH").val() != $("#celularP").val()  || $("#tel1DonH").val() != $("#tel1P").val()  || $("#tel2DonH").val() != $("#tel2P").val()  || $("#calleDonH").val() != $("#calleP").val()  ||
				$("#nExteriorDonH").val() != $("#nExteriorP").val()  || $("#nInteriorDonH").val() != $("#nInteriorP").val()  || $("#colDonH").val() != $("#colP").val()  || $("#localidadDonH").val() != $("#localidadP").val()  ||
				$("#municipioDonH").val() != $("#municipioP").val()  || $("#estadoDonH").val() != $("#estadoP").val()  || $("#cpDonH").val() != $("#cpP").val()  || $("#calleFDonH").val() != $("#calleFP").val()  ||
				$("#nExteriorFDonH").val() != $("#nExteriorFP").val()  || $("#nInteriorFDonH").val() != $("#nInteriorFP").val()  || $("#colFDonH").val() != $("#colFP").val()  || $("#localidadFDonH").val() != $("#localidadFP").val()  ||
				$("#municipioFDonH").val() != $("#municipioFP").val()  || $("#estadoFDonH").val() != $("#estadoFP").val()  || $("#cpFDonH").val() != $("#cpFP").val()  || $("#rfcFDonH").val() != $("#rfcFP").val()  ||
				$("#observacionesDonH").val() != $("#observacionesP").val() ){
        		
        	
        	
        	_url= "ajaxModificarPatrocinador";
        	
        	parametros = { "idPeriodo" : $("#idPeriodoP").val(),  "razonSocial" : $("#razonSocialP").val(),  "nombre" : $("#nombresP").val(),  "apellidoPaterno" : $("#aPaternoP").val(),  
        			"apellidoMaterno" : $("#aMaternoP").val(), "adscripcion" : $("#adscripcionP").val(), "idRegion" : $("#idRegionHiddenP").val(), "sector" : $("#sectorP").val(), 
        			"descripcionDonativo": $("#descripcionDP").val(), "email": $("#correoP").val(), "celular" : $("#celularP").val(),  "telefono1" : $("#tel1P").val(), "telefono2" : $("#tel2P").val(),
        			"calle" : $("#calleP").val(), "numE" : $("#nExteriorP").val(),  "numI" : $("#nInteriorP").val(),  "col" : $("#colP").val(),  "loc" : $("#localidadP").val(), 
        			"mun" : $("#municipioP").val(),  "edo" : $("#estadoP").val(), "cp" : $("#cpP").val(),	"calleFiscal" : $("#calleFP").val(), 
        			"numEFiscal" : $("#nExteriorFP").val(), "numIFiscal" : $("#nInteriorFP").val(),  "colFiscal" : $("#colFP").val(),  "locFiscal" : $("#localidadFP").val(), 
        			"munFiscal" : $("#municipioFP").val(),  "edoFiscal" : $("#estadoFP").val(),  "cpFiscal" : $("#cpFP").val(),  "rfc" : $("#rfcFP").val(),
        			"observaciones" : $("#observacionesP").val(),"idUsuario" : $("#idUsuario").val(), "idDonativo" : $("#idDonativoH").val()}
        
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
				/*$("textarea.vacias").val('');
				$("input.vacias").val('');
				$("select.vacias").val(1);
				*/
				//$("#donativoForm")[0].reset();
				
				$("textarea.vacias").val('');
				$("input.vacias").val('');
				$("select.vacias").val(1);
				$("textarea.vacias").val('');
				$("input.vacias").val('');
				$('input:radio[name="mismoDF"][value="0"]').prop('checked', true);
				$('input:radio[name="mismoDFP"][value="0"]').prop('checked', true);
				
				$("select.selectInicial").val("");
			    }else{
			    	$("#postResultDiv").delay(1000).hide(100);
			    	alert("El RFC ya se encuentra registrado");
			    }
			//	$("#becaSeleccionada").val(tBeca).trigger('change');
			},
			error : function(jqXHR,e) {
			    alert(jqXHR.responseText);
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



function autocompletarDonanteMod() {

	$("#idDon").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/ingresos/autocompleteDonanteMod",
				dataType : "json",
				data : {
					term : request.term,
					tipoDonativo : $("#tDonativo").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label : item.razonSocial+"  -  "+item.nombre +" "+item.apellidoPaterno+" "+item.apellidoMaterno,
							value : item.idDonativo,
							idDonativo : item.idDonativo,
							idPeriodo : item.idPeriodo,
							periodo : item.periodo,
							razonSocial : item.razonSocial,
							nombre : item.nombre,
							apellidoPaterno : item.apellidoPaterno,
							apellidoMaterno : item.apellidoMaterno,
							estatus : item.estatus,
							estatusDon : item.estatusDon,
							motivoEstatus : item.motivoEstatus,
							tipoDonante : item.tipoDonante,
							tipoDon : item.tipoDon,
							adscripcion : item.adscripcion,
							idRegion : item.idRegion,
							region : item.region,
						    
							sector : item.sector,
							sectorDon : item.sectorDon,
							descripcionDonativo : item.descripcionDonativo,
							
							 idCampania : item.idCampania ,
						     campania : item.campania ,
						    
						     medioAutorizacionDonativo : item.medioAutorizacionDonativo ,
						     medioAutorizacionDon : item.medioAutorizacionDon ,    
						     observacionMedioAutorizacion : item.observacionMedioAutorizacion ,
						    
						     medioCobro : item.medioCobro ,
						     medioCobroDon : item.medioCobroDon ,
						    
						     numPersonal : item.numPersonal ,
						     dependenciaAdscripcion : item.dependenciaAdscripcion ,
						    
						    donativoTotal : item.donativoTotal ,
						    donativoQuincenal : item.donativoQuincenal ,
						    
						     numQuincenas : item.numQuincenas ,
						     idQuincenaInicio : item.idQuincenaInicio ,
						     quincenaInicio : item.quincenaInicio ,
						    
						     idCuentaBancaria : item.idCuentaBancaria ,
						     cuentaBancaria : item.cuentaBancaria ,
						    
						     referencia : item.referencia ,
						    
						     numPagos : item.numPagos ,
						    importeNumPagos : item.importeNumPagos ,
						    
						     banco : item.banco ,
						     nombreTarjetahabiente : item.nombreTarjetahabiente ,
						     red : item.red ,
						     tipoTarjeta : item.tipoTarjeta ,
						     numTarjeta : item.numTarjeta ,
						     mesVencimiento : item.mesVencimiento ,
						     anioVencimiento : item.anioVencimiento ,
						    
						     tipoDonativo : item.tipoDonativo ,
						     mesInicioAportacion : item.mesInicioAportacion ,
						    
						     email : item.email ,
						     celular : item.celular ,
						     telefono1 : item.telefono1 ,
						     telefono2 : item.telefono2 ,
							
					
						    calle : item.calle,
							 numExt : item.numE,
						     numInt : item.numI,
						     col : item.col,
						     loc : item.loc,
						     mun : item.mun,
						     edo : item.edo,
						     cp : item.cp,
						     
						     
							    calleFiscal : item.calleFiscal,
								 numExtFiscal : item.numEFiscal,
							     numIntFiscal : item.numIFiscal,
							     colFiscal : item.colFiscal,
							     locFiscal : item.locFiscal,
							     munFiscal : item.munFiscal,
							     edoFiscal : item.edoFiscal,
							     cpFiscal : item.cpFiscal,
						    
						     rfc : item.rfc,
						     observaciones : item.observaciones,
						     idBeneficiario : item.idBeneficiario,
						     beneficiario : item.beneficiario
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

			if($("#tDonativo").val() == 1){
				
			/*	$("#idPeriodoDon").load("actualizaPeriodosDon", datos,function( response, status, xhr ) {			  
					  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	
						    }
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				});*/
				
				$("#tDonativo").val(1).trigger('change');
				
				$("#donante").show();
				$("#patrocinadores").hide();
				
				$('input:radio[name="altaCambioDon"][value="0"]').prop('checked', true);
				
				$("#idPeriodoDon").val(ui.item.idPeriodo);	
							
				$("#razonSocialDon").val(ui.item.razonSocial);
				$("#nombresDon").val(ui.item.nombre);
				$("#aPaternoDon").val(ui.item.apellidoPaterno);
				$("#aMaternoDon").val(ui.item.apellidoMaterno);
				
				$("#estatusDon").val(ui.item.estatus);
				
				if(ui.item.motivoEstatus != ''){
				$("#motivosEstatusDon").show();
				$("#motivoEstInactivoDon").val(ui.item.motivoEstatus);
				}else{
				$("#motivosEstatusDon").hide();
				}
				
				$("#tDonanteDon").val(ui.item.tipoDonante);
				$("#adscripcionDon").val(ui.item.adscripcion);
				
				$("#idRegionDon").val(ui.item.region);
				$("#idRegionHiddenDon").val(ui.item.idRegion);
				$("#valRegionHiddenDon").val(ui.item.region);
								
				$("#sectorDon").val(ui.item.sector);
			
				var datos = {
						idRegion : $("#idRegionHiddenDon").val()
					}
				$("#idCampaniaDon").load("actualizaCampanias", datos,function( response, status, xhr ) {			  
					  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }
					  $("#idCampaniaDon").val(ui.item.idCampania);
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				});
				
				
				
				$("#medioAutorizacionDon").val(ui.item.medioAutorizacionDonativo);
				$("#observacionesMADon").val(ui.item.observacionMedioAutorizacion);
			    
				$("#medioCobroDon").val(ui.item.medioCobro).trigger('change');
			    
				if(ui.item.medioCobro==1){
					$("#numPersonalDon").val(ui.item.numPersonal);
					$("#depAdscripcionDon").val(ui.item.dependenciaAdscripcion);
					$("#donaTotalDon").val(ui.item.donativoTotal);
					$("#donaQnaDon").val(ui.item.donativoQuincenal);
					$("#numQuincenasDon").val(ui.item.numQuincenas);
					$("#quincenaInicioDon").val(ui.item.quincenaInicio);
					$("#idQuincenaHidden").val(ui.item.idQuincenaInicio);
					$("#valQuincenaHidden").val(ui.item.quincenaInicio);
					
				}else if (ui.item.medioCobro==2){
					$("#cscBanDon").val(ui.item.cuentaBancaria);
					$("#idCuentaBanHidden").val(ui.item.idCuentaBancaria);
					$("#valCuentaBanHidden").val(ui.item.cuentaBancaria);
					$("#depTraReferenciaDon").val(ui.item.referencia);
					$("#donaTotalDTDon").val(ui.item.donativoTotal);
					$("#numPagosDTDon").val(ui.item.numPagos);
					$("#importePagoDTDon").val(ui.item.importeNumPagos);
				}else if (ui.item.medioCobro==3){
					$("#tcBanDon").val(ui.item.banco);
					$("#tcNombreTarHabDon").val(ui.item.nombreTarjetahabiente);
					$("#tcRedDon").val(ui.item.red);
					$("#tcTipoDon").val(ui.item.tipoTarjeta);
					$("#tcNumTarjetaDon").val(ui.item.numTarjeta);
					$("#tcFechaMesDon").val(ui.item.mesVencimiento);
					$("#tcFechaAnioDon").val(ui.item.anioVencimiento);
					
					$("#tcTipoDonaDon").val(ui.item.tipoDonativo);
					$("#tcTipoDonaDon").val(ui.item.tipoDonativo).trigger('change');
					$("#tcNumPagosDon").val(ui.item.numPagos);
					$("#tcDonaTotalDon").val(ui.item.donativoTotal);
					$("#tcImportePagoDon").val(ui.item.importeNumPagos);
					$("#tcfechaIniAporDon").val(ui.item.mesInicioAportacion);
				}
				
				$("#correoDon").val(ui.item.email);
				$("#celularDon").val(ui.item.celular);
				$("#tel1Don").val(ui.item.telefono1);
				$("#tel2Don").val(ui.item.telefono2);
				
				$("#calleDon").val(ui.item.calle);
				$("#nExteriorDon").val(ui.item.numExt);
				$("#nInteriorDon").val(ui.item.numInt);
				$("#colDon").val(ui.item.col);
				$("#localidadDon").val(ui.item.loc);
				$("#municipioDon").val(ui.item.mun);
				$("#estadoDon").val(ui.item.edo);
				$("#cpDon").val(ui.item.cp);
				
				
					     
					
					$("#calleFDon").val(ui.item.calleFiscal);
					$("#nExteriorFDon").val(ui.item.numExtFiscal);
					$("#nInteriorFDon").val(ui.item.numIntFiscal);
					$("#colFDon").val(ui.item.colFiscal);
					$("#localidadFDon").val(ui.item.locFiscal);
					$("#municipioFDon").val(ui.item.munFiscal);
					$("#estadoFDon").val(ui.item.edoFiscal);
					$("#cpFDon").val(ui.item.cpFiscal);
					$("#rfcFDon").val(ui.item.rfc);
				$("#observacionesDon").val(ui.item.observaciones);
			     
		/*		$("#idBeneficiarioDon").val(ui.item.beneficiario);
				$("#idBeneficiarioHidden").val(ui.item.idBeneficiario);
				$("#valBeneficiarioHidden").val(ui.item.beneficiario);
			*/	
				
				
			}else if($("#tDonativo").val() == 2){
				
				$("#tDonativo").val(2).trigger('change');
				
				$("#donante").hide();
				$("#patrocinadores").show();
				$('input:radio[name="altaCambioDon"][value="0"]').prop('checked', true);
				
				$("#idPeriodoP").val(ui.item.idPeriodo);	
				
				$("#razonSocialP").val(ui.item.razonSocial);
				$("#nombresP").val(ui.item.nombre);
				$("#aPaternoP").val(ui.item.apellidoPaterno);
				$("#aMaternoP").val(ui.item.apellidoMaterno);
			
				$("#adscripcionP").val(ui.item.adscripcion);
				
				$("#idRegionP").val(ui.item.region);
				$("#idRegionHiddenP").val(ui.item.idRegion);
				$("#valRegionHiddenP").val(ui.item.region);
								
				$("#sectorP").val(ui.item.sector);
				
				$("#descripcionDP").val(ui.item.descripcionDonativo);
			
				
				$("#correoP").val(ui.item.email);
				$("#celularP").val(ui.item.celular);
				$("#tel1P").val(ui.item.telefono1);
				$("#tel2P").val(ui.item.telefono2);
				
			 	$("#calleP").val(ui.item.calle);
			 	$("#nExteriorP").val(ui.item.numExt);
				$("#nInteriorP").val(ui.item.numInt);
					$("#colP").val(ui.item.col);
					$("#localidadP").val(ui.item.loc);
					$("#municipioP").val(ui.item.mun);
					$("#estadoP").val(ui.item.edo);
					$("#cpP").val(ui.item.cp);
					     
					
					$("#calleFP").val(ui.item.calleFiscal);
					$("#nExteriorFP").val(ui.item.numExtFiscal);
					$("#nInteriorFP").val(ui.item.numIntFiscal);
					$("#colFP").val(ui.item.colFiscal);
					$("#localidadFP").val(ui.item.locFiscal);
					$("#municipioFP").val(ui.item.munFiscal);
					$("#estadoFP").val(ui.item.edoFiscal);
					$("#cpFP").val(ui.item.cpFiscal);
					$("#rfcFP").val(ui.item.rfc);
				$("#observacionesP").val(ui.item.observaciones);
			    
			}
			
			$("#divBeneAsignado").hide();
			$("#divBotonGuardar").show();
			$("#idDonativoH").val(ui.item.idDonativo);	
			
				$("#idPeriodoDonH").val(ui.item.idPeriodo);	
				
				$("#razonSocialDonH").val(ui.item.razonSocial);
				$("#nombresDonH").val(ui.item.nombre);
				$("#aPaternoDonH").val(ui.item.apellidoPaterno);
				$("#aMaternoDonH").val(ui.item.apellidoMaterno);
				
				$("#estatusDonH").val(ui.item.estatus);
				
				
				$("#motivoEstInactivoDonH").val(ui.item.motivoEstatus);
				$("#descripcionDPH").val(ui.item.descripcionDonativo);
				
				$("#tDonanteDonH").val(ui.item.tipoDonante);
				$("#adscripcionDonH").val(ui.item.adscripcion);
				
				$("#idRegionDonH").val(ui.item.region);
				$("#idRegionHiddenDonH").val(ui.item.idRegion);
				$("#valRegionHiddenDonH").val(ui.item.region);
								
				$("#sectorDonH").val(ui.item.sector);
				
				$("#idCampaniaDonH").val(ui.item.idCampania);
				$("#medioAutorizacionDonH").val(ui.item.medioAutorizacionDonativo);
				$("#observacionesMADonH").val(ui.item.observacionMedioAutorizacion);
			    
				$("#medioCobroDonH").val(ui.item.medioCobro);
			    
				//if(ui.item.medioCobro==1){
					$("#numPersonalDonH").val(ui.item.numPersonal);
					$("#depAdscripcionDonH").val(ui.item.dependenciaAdscripcion);
					$("#donaTotalDonH").val(ui.item.donativoTotal);
					$("#donaQnaDonH").val(ui.item.donativoQuincenal);
					$("#numQuincenasDonH").val(ui.item.numQuincenas);
					$("#quincenaInicioDonH").val(ui.item.quincenaInicio);
					$("#idQuincenaHiddenH").val(ui.item.idQuincenaInicio);
					$("#valQuincenaHiddenH").val(ui.item.quincenaInicio);
					
				//}else if (ui.item.medioCobro==2){
					$("#cscBanDonH").val(ui.item.cuentaBancaria);
					$("#idCuentaBanHiddenH").val(ui.item.idCuentaBancaria);
					$("#valCuentaBanHiddenH").val(ui.item.cuentaBancaria);
					$("#depTraReferenciaDonH").val(ui.item.referencia);
					$("#donaTotalDTDonH").val(ui.item.donativoTotal);
					$("#numPagosDTDonH").val(ui.item.numPagos);
					$("#importePagoDTDonH").val(ui.item.importeNumPagos);
				//}else if (ui.item.medioCobro==3){
					$("#tcBanDonH").val(ui.item.banco);
					$("#tcNombreTarHabDonH").val(ui.item.nombreTarjetahabiente);
					$("#tcRedDonH").val(ui.item.red);
					$("#tcTipoDonH").val(ui.item.tipoTarjeta);
					$("#tcNumTarjetaDonH").val(ui.item.numTarjeta);
					$("#tcFechaMesDonH").val(ui.item.mesVencimiento);
					$("#tcFechaAnioDonH").val(ui.item.anioVencimiento);
					$("#tcTipoDonaDonH").val(ui.item.tipoDonativo);
					$("#tcNumPagosDonH").val(ui.item.numPagos);
					$("#tcDonaTotalDonH").val(ui.item.donativoTotal);
					$("#tcImportePagoDonH").val(ui.item.importeNumPagos);
					$("#tcfechaIniAporDonH").val(ui.item.mesInicioAportacion);
			//	}
				
				$("#correoDonH").val(ui.item.email);
				$("#celularDonH").val(ui.item.celular);
				$("#tel1DonH").val(ui.item.telefono1);
				$("#tel2DonH").val(ui.item.telefono2);
				
			 	$("#calleDonH").val(ui.item.calle);
			 	
					$("#nExteriorDonH").val(ui.item.numExt);
					$("#nInteriorDonH").val(ui.item.numInt);
					$("#colDonH").val(ui.item.col);
					$("#localidadDonH").val(ui.item.loc);
					$("#municipioDonH").val(ui.item.mun);
					$("#estadoDonH").val(ui.item.edo);
					$("#cpDonH").val(ui.item.cp);
					
					$("#calleFDonH").val(ui.item.calleFiscal);
					$("#nExteriorFDonH").val(ui.item.numExtFiscal);
					$("#nInteriorFDonH").val(ui.item.numIntFiscal);
					$("#colFDonH").val(ui.item.colFiscal);
					$("#localidadFDonH").val(ui.item.locFiscal);
					$("#municipioFDonH").val(ui.item.munFiscal);
					$("#estadoFDonH").val(ui.item.edoFiscal);
					$("#cpFDonH").val(ui.item.cpFiscal);
					$("#rfcFDonH").val(ui.item.rfc);
				$("#observacionesDonH").val(ui.item.observaciones);
			     
		//		$("#idBeneficiarioDonH").val(ui.item.beneficiario);
		//		$("#idBeneficiarioHiddenH").val(ui.item.idBeneficiario);
		//		$("#valBeneficiarioHiddenH").val(ui.item.beneficiario);
		//	$("#"+idRegionHidden).val(ui.item.value);
			return false;
		},
		minLength: 0
		

	});
}


function checkMismoDF(id){
	if($("#"+id).val() == 1){
		$("#calleFDon").val($("#calleDon").val());
		$("#nExteriorFDon").val($("#nExteriorDon").val());
		$("#nInteriorFDon").val($("#nInteriorDon").val());
		$("#colFDon").val($("#colDon").val());
		$("#localidadFDon").val($("#localidadDon").val());
		$("#municipioFDon").val($("#municipioDon").val());
		$("#estadoFDon").val($("#estadoDon").val());
		$("#cpFDon").val($("#cpDon").val());		
	}else{
		$("#calleFDon").val('');
		$("#nExteriorFDon").val('');
		$("#nInteriorFDon").val('');
		$("#colFDon").val('');
		$("#localidadFDon").val('');
		$("#municipioFDon").val('');
		$("#estadoFDon").val('');
		$("#cpFDon").val('');	
	}
}

function checkMismoDFP(id){
	if($("#"+id).val() == 1){
		$("#calleFP").val($("#calleP").val());
		$("#nExteriorFP").val($("#nExteriorP").val());
		$("#nInteriorFP").val($("#nInteriorP").val());
		$("#colFP").val($("#colP").val());
		$("#localidadFP").val($("#localidadP").val());
		$("#municipioFP").val($("#municipioP").val());
		$("#estadoFP").val($("#estadoP").val());
		$("#cpFP").val($("#cpP").val());		
	}else{
		$("#calleFP").val('');
		$("#nExteriorFP").val('');
		$("#nInteriorFP").val('');
		$("#colFP").val('');
		$("#localidadFP").val('');
		$("#municipioFP").val('');
		$("#estadoFP").val('');
		$("#cpFP").val('');	
	}
}