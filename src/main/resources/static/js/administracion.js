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
					tipoAsig : reg,
					id : ui.item.value
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


function replaceAll( text, busca, reemplaza ){
  while (text.toString().indexOf(busca) != -1)
      text = text.toString().replace(busca,reemplaza);
  return text;
}


function guardaDonativo(donativo,tipo,id){
	bandera = true;
	if(donativo==""){
		$("#montoAsig"+id).val("$0.00");
		  $("#montoAsig"+id).prop('disabled',true);
		 // alert()
		  document.getElementById(id).checked = false;
		  donativo=0;
	}else{
		don = replaceAll(donativo,"$","");		
		dona = replaceAll(don,",","");
		//don = donativo.replace("$","").replace(",","");
		
		donativo = parseFloat(dona);
		
	}
	
	iden = $("#identificador").val();
	
	if (tipo == 1){
		idBeneficiario = $("#identificador").val();
		idDonante = id;		
	}else{
		idBeneficiario = id;
		idDonante = $("#identificador").val();
	}
	
	hide = replaceAll($("#montoAsigHidden"+id).val(),"$","");
	mtHide = replaceAll(hide,",","");
	
	montoHide = parseFloat(mtHide);
	
	
	if(donativo == montoHide){
		bandera = false;
	}
	
	
	if(tipo == 1){				
		
		donaAsig = replaceAll($("#spMontoAsignado").text(),"$","");		
		donaAsig = replaceAll(donaAsig,",","");
		donativoBeneficiarioAsig = (parseFloat(donativo) + parseFloat(donaAsig));
		
		donaTotal = replaceAll($("#spMontoBeca").text(),"$","");
		donaTotal = replaceAll(donaTotal,",","");
		
		//donaTotal = parseFloat(donaT).format(2,3);
		
		saldo = (parseFloat(donaTotal) - parseFloat(donativoBeneficiarioAsig));
		
	//	alert(saldo +" / "+ donaTotal +" / "+ donativoBeneficiarioAsig);
		
	}else{
		
		donaAsig = replaceAll($("#spDonativoAsignado").text(),"$","");		
		donaAsig = replaceAll(donaAsig,",","");
						    		
		donativoDonanteAsig =  (parseFloat(donativo) + parseFloat(donaAsig));
		
		donaTotal = replaceAll($("#spDonativoTotal").text(),"$","");
		donaTotal = replaceAll(donaTotal,",","");
		
		//donaTotal = parseFloat(donaT).format(2,3);
		
		saldo = (parseFloat(donaTotal) - parseFloat(donativoDonanteAsig));
		
	//	alert(saldo +" / "+ donaTotal +" / "+ donativoDonanteAsig);
	}
	
	if(saldo>=0 && bandera){
	
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
						tipoAsig : tipo,
						id : iden
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
						    //		alert(donativoBeneficiarioAsig);
						    		$("#spMontoAsignado").text("$ "+parseFloat(donativoBeneficiarioAsig).format(2,3));
						    		$("#spBSaldo").text("$ "+parseFloat(saldo).format(2,3));
						    		
						    	}else{
						    		$("#spDonativoAsignado").text("$ "+parseFloat(donativoDonanteAsig).format(2,3));		
						    		$("#spDSaldo").text("$ "+parseFloat(saldo).format(2,3));
						    		
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
	}else if(saldo<0 && bandera){
	//	$("#montoAsig"+id).val("");
		if(tipo==1){
			alert("El Donativo asignado no puede ser mayor al Donativo total");
		}else{
			alert("El Monto asignado no puede ser mayor al Monto de beca/apoyo");
		}
		
	}
	
	//
}