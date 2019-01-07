function cambiaPeriodoAs(){
	
///	$("#subirYMostrarCF").hide();
	
	if($("#idPeriodoAs").val() > 0){
	$("#idDonBene").prop('disabled',false);
	}else{
		$("#idDonBene").prop('disabled',true);
	}

}


function checkIds(id){

	$("#idDonBene").val('');
	$("#idPeriodoAs").val('');
	$("#idDonBene").prop('disabled',true);
	$("#registros").hide();
	$("#idDonBene").keyup();
	if($("#"+id).val() == '1'){
		$("#donBene").text("Beneficiario");				
	}else if($("#"+id).val() == '0'){
		$("#donBene").text("Donante");
	}
}


function autocompleteDonBeneAS() {
	
	if( $('input:radio[name=altaCambio]:checked').val() ==1){
		tipo = "/egresos/autocompleteBeneficiarioNoDeporA";
	}else{
		tipo ="/ingresos/autocompleteBenefactorNoPatrocinadorA";
	}
	
	//alert($('input:radio[name=altaCambio]:checked').val()+" "+tipo);
	//alert(tipo);
	$("#idDonBene").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : tipo,
				dataType : "json",
				data : {
					term : request.term,					
					idPeriodo : $("#idPeriodoAs").val()
				},
				success : function(data) {
			//		$("#subirYMostrarCF").hide();

					response($.map(data, function(item) {
						if( $('input:radio[name=altaCambio]:checked').val() ==1){
						
						return {
							
								label: item.nombreCompletoBene,
								value : item.idBeneficiario
											
														
						};
						}else{
							return {
								
								label: item.nombreCompletoDon,
								value : item.idDonativo	
														
						};
							
						}
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
			
			if( $('input:radio[name=altaCambio]:checked').val() ==1){
			$("#etiquetaTab").text("Donante(s)");
			
			reg=1;
			if(ui.item.value=='Sin beneficiario'){
				$("#idBeneficiarioHidden").val(0);
				}else{
				$("#idBeneficiarioHidden").val(ui.item.value);
				}			
			$("#valBeneficiarioHidden").val(this.value);
			
			}else{
				$("#etiquetaTab").text("Beneficiario(s)");
				reg=0;
				
			if(ui.item.value=='Sin donante'){
				$("#idBenefactorHiddenCF").val(0);
				}else{
					$("#idBenefactorHiddenCF").val(ui.item.valueD);
				}
				$("#valBenefactorHiddenCF").val(this.valueD);
			}
			
			$("#registros").show();		
			var datos = {
					idPeriodo : $("#idPeriodoAs").val(),
					tipoAsig : reg
				}
			$("#registros").load("/administracion/actualizaRegistrosAsig", datos,function( response, status, xhr ) {
			  if(response.includes("Sesión inactiva")){				 
					 window.location = "/login?session=false";
					    }
				  
				if(xhr.status==200 && xhr.statusText== "parsererror"){
					window.location = "/login?session=false";
				}
			}); 
			
			
			
		
			return false;
		},
		minLength: 0
		

	});
}

