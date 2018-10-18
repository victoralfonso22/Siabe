$("#tDonativo").change(function() {
	
	if($("#tDonativo").val() == 1){
		
		if( $('input:radio[name=altaCambioDon]:checked').val() ==1){
		$("#donante").show();
		$("#patrocinadores").hide();
		
		}else{
			$("#lblAutoDon").html('Nombre de donante');			
			$("#donante").hide();
			$("#patrocinadores").hide();
		}
	}else{
		
		if( $('input:radio[name=altaCambioDon]:checked').val() ==1){
		$("#donante").hide();
		$("#patrocinadores").show();
		}else{
			$("#donante").hide();
			$("#patrocinadores").hide();
			$("#lblAutoDon").html('Nombre de patrocinador');
		}
	}
	
});


function checkInicioDonante(id){
	$("#idDon").val('');
	$("#tipoDonativo").show();
	
	
	$("#patrocinadores").hide();
	
	if($("#"+id).val() == '1'){
		$("#donante").show();	
		$("#lblAutoDon").html('Nombre de donante');
		$("#tDonativo").val(1);
		$("#completeCambio").hide();				
	}else if($("#"+id).val() == '0'){
		$("#donante").hide();
		if($("#tDonativo").val() == 1){
			$("#lblAutoDon").html('Nombre de donante');
		}else{
			$("#lblAutoDon").html('Nombre de patrocinador');
		}
	
		$("#completeCambio").show();
	}
}


$("#medioCobro").change(function() {
	
	if($("#medioCobro").val() == 1){
		$("#mcNomina").show();
		$("#mcDepTrans").hide();
		$("#mcTarjetaCredito").hide();
	}else if($("#medioCobro").val() == 2){
		$("#mcNomina").hide();
		$("#mcDepTrans").show();
		$("#mcTarjetaCredito").hide();
	}else if($("#medioCobro").val() == 3){
		$("#mcNomina").hide();
		$("#mcDepTrans").hide();
		$("#mcTarjetaCredito").show();
	}
	
});
