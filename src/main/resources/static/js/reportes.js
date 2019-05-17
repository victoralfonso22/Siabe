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


$("#idBecaGeneral").change(function(){
	$('input[name=general]').prop('checked', false);
	$('input[name=checkPrincipal]').prop('checked', false);
	$("#iconosReporteGeneral").show();
	
	var datos = {
			idTipoBeca : $("#idBecaGeneral").val()
		}
	
	$("#idPeriodoGeneral").load("actualizaPeriodoRepGen", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
	
	
	
	if($("#idBecaGeneral").val() == 4){
		$("#generales").hide();
		$(".apoyo").hide();
		$("#deportivas").show();
	}else if($("#idBecaGeneral").val() == 3){
		$(".apoyo").show();
		$("#generales").show();
		$("#deportivas").hide();
	}else{
		$("#generales").show();
		$(".apoyo").hide();
		$("#deportivas").hide();
	}
});


$('document').ready(function(){
	   $("#checkTodos").change(function () {
	      $("input:checkbox").prop('checked', $(this).prop("checked"));
	  });
	});



$('document').ready(function(){
	   $("#checkTodosDeport").change(function () {
		   $('input:checkbox[name="deporti"]').prop('checked', $(this).prop("checked"));
	      //$('input:checkbox[name="deporti"]').prop('checked', true);
	  });
	});


$("#idPeriodoGeneral").change(function(){
	var datos = {
			idPeriodo : $("#idPeriodoGeneral").val()
		}
	
	$("#idRegionGeneral").load("actualizaRegionRepGen", datos,function( response, status, xhr ) {			  
		  if(response.includes("Sesión inactiva")){				 
			 window.location = "/login?session=false";
			    }
		if(xhr.status==200 && xhr.statusText== "parsererror"){
			window.location = "/login?session=false";
		}
	});
});


function visualiza(id,clase){
	if($("#"+id).is(':checked')){
		alert("check");
		$('input.'+clase).prop('checked', true);
	}else{
		$('input.'+clase).prop('checked', false);
		alert("no check");
	}
}
