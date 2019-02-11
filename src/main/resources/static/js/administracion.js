function cambiaPeriodoAs(){
	
	$("#registros").hide();
	$("#labelsB").hide();
	$("#labelsD").hide();
	$("#idDonBene").val("");
	if($("#idPeriodoAs").val() > 0){
	$("#idDonBene").prop('disabled',false);
	}else{
		$("#idDonBene").prop('disabled',true);
	}

}


function checkIds(id){

	$("#idDonBene").val('');
	$("#labelsB").hide();
	$("#labelsD").hide();
	$("#idPeriodoAs").val('');
	$("#idDonBene").prop('disabled',true);
	$("#registros").hide();
	$("#idDonBene").keyup();
	$("#labels").hide();
	if($("#"+id).val() == '1'){
		$("#donBene").text("Beneficiario");		
		$("#lblTotal").text("Monto apoyo");
	}else if($("#"+id).val() == '0'){
		$("#donBene").text("Donante");
		$("#lblTotal").text("Donativo total");
	
	}
}


function autocompleteDonBeneAS() {
	
	if( $('input:radio[name=altaCambio]:checked').val() ==1){
		tipo = "/egresos/autocompleteBeneficiarioNoDeporAAsig";
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
							
								label: item.nombreCompletoBene+" "+item.tipoBeca ,
								value : item.idBeneficiario,
								montoBeca : item.montoBeca
											
														
						};
						}else{
							return {
								
								label: item.nombreCompletoDon,
								value : item.idDonativo,
								donativoTotal : item.donativoTotal
														
						};
							
						}
					}));

				},
				error : function(jqXHR,textStatus) {
					//alert(jqXHR.responseText);
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
			$("#identificador").val(ui.item.value);
			
			if( $('input:radio[name=altaCambio]:checked').val() ==1){
			
				$("#donBene").text("Beneficiario");		
				$("#lblTotal").text("Monto apoyo");
				$("#labelsB").show();
				$("#labelsD").hide();
				$("#spMontoBeca").text(" $ "+ui.item.montoBeca.format(2,3));
				$("#spDSaldo").text("");
				
				
				var datos = {
						idDonante : 0,
						idBeneficiario : ui.item.value
					}
				$("#spMontoAsignado").load("/administracion/regresaDonativoAsig", datos,function( response, status, xhr ) {
				  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	$("#spBSaldo").text("$ "+(ui.item.montoBeca - parseFloat($("#spMontoAsignado").text())).format(2,3));
						    	sp = $("#spMontoAsignado").text();
						    	$("#spMontoAsignado").text("$ "+parseFloat(sp).format(2,3));					    	
						    }
					  
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				}); 
			
			reg=1;
			if(ui.item.value=='Sin beneficiario'){
				$("#idBeneficiarioHidden").val(0);
				}else{
				$("#idBeneficiarioHidden").val(ui.item.value);
				}			
			$("#valBeneficiarioHidden").val(this.value);
			
			}else{
				
				$("#donBene").text("Donante");
				$("#lblTotal").text("Donativo total");
				$("#labelsD").show();
				$("#labelsB").hide();
				$("#spMontoBeca").text("");
				$("#spDonativoTotal").text(" $ "+ui.item.donativoTotal.format(2,3));
				$("#spBSaldo").text("");
				
				var datos = {
						idDonante : ui.item.value,
						idBeneficiario : 0
					}
				$("#spDonativoAsignado").load("/administracion/regresaDonativoAsig", datos,function( response, status, xhr ) {
				  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	$("#spDSaldo").text("$ "+(ui.item.donativoTotal - parseFloat($("#spDonativoAsignado").text())).format(2,3));
						    	sp = $("#spDonativoAsignado").text();
						    	$("#spDonativoAsignado").text("$ "+parseFloat(sp).format(2,3));
						    }
					  
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				}); 
				

				
				reg=0;
				
			if(ui.item.value=='Sin donante'){
				$("#idBenefactorHiddenCF").val(0);
				}else{
					$("#idBenefactorHiddenCF").val(ui.item.valueD);
				}
				$("#valBenefactorHiddenCF").val(this.valueD);
			}
			
			
			$("#labels").show();
			
			$("#registros").show();		
			var datos = {
					idPeriodo : $("#idPeriodoAs").val(),
					tipoAsig : reg
				}
			$("#registros").load("/administracion/actualizaRegistrosAsig", datos,function( response, status, xhr ) {
			  if(response.includes("Sesión inactiva")){				 
					 window.location = "/login?session=false";
					    }else{
					    	
					    	if( $('input:radio[name=altaCambio]:checked').val() ==1){
								$("#etiquetaTab").text("Donantes");
								$("#thTipoBeca").hide();
								$("#tdTipoBeca").hide();
							}else{
								$("#etiquetaTab").text("Beneficiarios");
								$("#thTipoBeca").show();
								$("#tdTipoBeca").show();
							}
					    	
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

function deshabilitaMonto(tipo,id){
	
	
	if($("#"+id).is(':checked')){
		if($("#montoAsig"+id).val() == "" || $("#montoAsig"+id).val() == "$0.00"){
		$("#montoAsig"+id).val("");
		}
		$("#montoAsig"+id).prop('disabled',false);		
	  } else {
		  guardaDonativo(0,tipo,id);
		  $("#montoAsig"+id).val("$0.00");
		  $("#montoAsig"+id).prop('disabled',true);
	  }
	
}

function guardaDonativo(donativo,tipo,id){
	if(donativo==""){
		$("#montoAsig"+id).val("$0.00");
		  $("#montoAsig"+id).prop('disabled',true);
		 // alert()
		  document.getElementById(id).checked = false;
		  donativo=0;
	}else{
		don = donativo.replace("$","").replace(",","");
		
		donativo = parseFloat(don).format(2,3);
		
	}
	
	if (tipo == 1){
		idBeneficiario = $("#identificador").val();
		idDonante = id;
	}else{
		idBeneficiario = id;
		idDonante = $("#identificador").val();
	}
	
	
	if(tipo == 1){						    		
		donaAsig = $("#spDonativoAsignado").val().replace("$","").replace(",","");						    		
		donativoDonanteAsig = donativo + parseFloat(donaAsig).format(2,3);
		
		donaTotal = $("#spDonativoTotal").val().replace("$","").replace(",","");	
		
		saldo = donaTotal - donativoDonanteAsig;
		
	}else{
		donaAsig = $("#spMontoAsignado").val().replace("$","").replace(",","");						    		
		donativoBeneficiarioAsig = donativo + parseFloat(donaAsig).format(2,3);
		
		donaTotal = $("#spMontoBeca").val().replace("$","").replace(",","");	
		
		saldo = donaTotal - donativoBeneficiarioAsig;
		
	}
	
	if(saldo>=0){
	
var parametrosPermisos = {"idDonante": idDonante, "idBeneficiario": idBeneficiario, "donativo" : donativo};  
	
	$.ajax({
	    	type : "POST",
			url :"registraDonativoAsig",
			data : parametrosPermisos,
			success : function(result) {	
			  //  alert(result);
			    if(result.includes("Sesión inactiva")){
				window.location = "/login?session=false";
			    }else if(result == "Done"){
			    
			    
			    var datos = {
						idPeriodo : $("#idPeriodoAs").val(),
						tipoAsig : tipo
					}
				$("#registros").load("/administracion/actualizaRegistrosAsig", datos,function( response, status, xhr ) {
				  if(response.includes("Sesión inactiva")){				 
						 window.location = "/login?session=false";
						    }else{
						    	
						    	if( $('input:radio[name=altaCambio]:checked').val() ==1){
									$("#etiquetaTab").text("Donantes");
									$("#thTipoBeca").hide();
									$("#tdTipoBeca").hide();
								}else{
									$("#etiquetaTab").text("Beneficiarios");
									$("#thTipoBeca").show();
									$("#tdTipoBeca").show();
								}
						    	
						    	if(tipo == 1){						    		
						   
						    		$("#spDonativoAsignado").val("$ "+parseFloat(donativoDonante).format(2,3));
						    	}else{
						    							    		
						    		$("#spMontoAsignado").val("$ "+parseFloat(donativoBeneficiario).format(2,3));
						    	}	
						    	
						    }
					  
					if(xhr.status==200 && xhr.statusText== "parsererror"){
						window.location = "/login?session=false";
					}
				}); 
			    
			}else if(result == "DonativoExeD"){
				alert("El donativo que trata de asignar excede el saldo disponible del donante");
			}else if(result == "DonativoExeB"){
				alert("El donativo que trata de asignar excede el saldo disponible del beneficiario");
			}
				
				console.log(result);
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
		if(tipo==1){
			alert("El Donativo asignado no puede ser mayor al Donativo total");
		}else{
			alert("El Monto asignado no puede ser mayor al Monto de beca/apoyo");
		}
		
	}
	
	//
}