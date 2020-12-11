function buscarBeneficiarioNombre(){
	$("#iconosReporteBenNom").hide();
	$("#idBeneNom").val('');
	$("#idBeneNomHidden").val('');
	$("#buscarBeneficiarioModal").show();
}

function buscarBenDonNombre(){
	$("#iconosReporteBenNom").hide();
	$("#idBeneNom").val('');
	$("#idBeneNomHidden").val('');
	$("#buscarBenDonModal").show();
}


function autocompletarBeneficiarioNom() {

	$("#idBeneNom").autocomplete({
		source : function(request, response) {
			$.ajax({
				type : "POST",
				url : "/egresos/autocompleteBeneficiarioMod",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : 0,
					idTipoBeca : 0
				//	idPeriodo : $("#idPeriodo").val()
				},
				success : function(data) {

					response($.map(data, function(item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label : item.nombre +" "+item.apellidoPaterno+" "+item.apellidoMaterno,
							value : item.idBeneficiario
						 
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
			$("#idBeneNomHidden").val(ui.item.value);	
			$("#iconosReporteBenNom").show();
			return false;
			
		},
		minLength: 0
		

	});
}



function beneficiarioGeneral(){
	
	$("#generales").hide();
	$("#iconosReporteGeneral").hide();
	$("#idBeneNom").val('');
	$("#idBeneNomHidden").val('');
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	
	$("#idBecaGeneral").val('');
	$("#idPeriodoGeneral").val('');
	$("#idRegionGeneral").val('');
	$("#beneficiarioGeneralModal").show();
}


function donanteGeneral(){
	
	$("#generales").hide();
	$("#iconosReporteGeneral").hide();
	$("#idBeneNom").val('');
	$("#idBeneNomHidden").val('');
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	
	$("#idPeriodoGeneralDonante").val('');
	$("#idRegionGeneral").val('');
	$("#donanteGeneralModal").show();
}

function cobranza(){
	
	$("#generales").hide();
	$("#iconosReporteGeneral").hide();
	$("#idBeneNom").val('');
	$("#idBeneNomHidden").val('');
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	
	$("#idPeriodoGeneralDonante").val('');
	$("#idRegionGeneral").val('');
	$("#cobranzaModal").show();
}

function cambiaMedioCobro(){

	$("#iconosReporteCobranza").hide();

	if($("#medioCobroC").val() == 1){
		$("#idQuincena").val(null);
		$("#rowQuincena").show();
		$("#rowTarjeta").hide();
	}else if($("#medioCobroC").val() == 2){
		$("#rowQuincena").hide();
		$("#rowTarjeta").hide();
	}else if($("#medioCobroC").val() == 3){
		$("#rowQuincena").hide();
		$("#rowTarjeta").show();
	}

	verificarCombinacionCobranza();	
}

function verificarCombinacionCobranza(){
	
	if($("#medioCobroC").val() == 1 && $("#idQuincena").val() != null && $("#idPeriodoC").val() != null){
		$("#iconosReporteCobranza").show();

	}else if( $("#idPeriodoC").val() != null && ($("#medioCobroC").val() == 2 || $("#medioCobroC").val() == 3)){
		$("#iconosReporteCobranza").show();
	}
}

$("#idBecaGeneral").change(function(){
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	$("#iconosReporteGeneral").show();
	
	var datos = {
			idTipoBeca : $("#idBecaGeneral").val()
		}
	
	
	if($("#idBecaGeneral").val() != ''){
	
	
		
	$("#idPeriodoGeneral").load("actualizaPeriodoRepGen", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
	
	
	$("#chequea").show();
	
	if($("#idBecaGeneral").val() == 4){
		$(".apoyo").hide();
		$("#deportivas").show();
		$("#generales").hide();
	}else if($("#idBecaGeneral").val() == 3){
		$(".apoyo").show();
		$("#deportivas").hide();
		$("#generales").show();
	}else{
		$(".apoyo").hide();
		$("#deportivas").hide();
		$("#generales").show();
	}
	
	}else{

		$("#idPeriodoGeneral").val('');
		$("#idRegionGeneral").val('');
		$("#generales").hide();
		$(".apoyo").hide();
		$("#deportivas").hide();
		$("#iconosReporteGeneral").hide();
	}
	
});


$("#idPeriodoGeneral").change(function(){
	var datos = {
			idPeriodo : $("#idPeriodoGeneral").val()
		}
	
	
	if($("#idPeriodoGeneral").val() != ''){
		
	
	
	$("#idRegionGeneral").load("actualizaRegionRepGen", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
	
	}
});


$("#idPeriodoGeneralDonante").change(function(){
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	$("#iconosReporteGeneral").show();
	var datos = {
			idPeriodo : $("#idPeriodoGeneralDonante").val()
		}
	
	
	if($("#idPeriodoGeneralDonante").val() != ''){
		
		$("#generales").show();
		

	
	$("#idRegionGeneral").load("actualizaRegionRepGen", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
	
	}else{

		$("#idPeriodoGeneralDonante").val('');
		$("#idRegionGeneral").val('');
		$("#generales").hide();
		$(".apoyo").hide();
		$(".deportivas").hide();
		$("#iconosReporteGeneral").hide();
	}
});

