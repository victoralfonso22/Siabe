/***************************************************FUNCION GENARAL PARA GENERACION DE REPORTES****************************/
function generarReporte(url,idSpan,divClass){
//DO POST
	
	$('#'+idSpan+'2').attr("aria-valuenow",50); 
	$('#'+idSpan+'2').attr("style","width:50%");
//	$('#'+idSpan+'2').html("Creando reporte 50%");
$.ajax({
    		type : "POST",
		url : url,		
		success : function(result) {
		    if(result.includes("Sesión inactiva")){
			window.location = "/login?session=false";
		    }else{
		    
		    	$('#'+idSpan+'2').attr("aria-valuenow",75); 
		    	//$('#'+idSpan)
		    	$('#'+idSpan+'2').attr("style","width:75%");
		    	$('#'+idSpan+'2').attr("aria-valuenow",100); 
		    	//$('#'+idSpan)
		    	$('#'+idSpan+'2').attr("style","width:100%");
		    	setTimeout(function(){
		    		window.open(url,"_blank");
		    	//	var w = window.open();
		    	//	$(w.document.body).html(result);
		    		
		    		
		    	}, 1000);
		    	

		    	//$('img.image').css('opacity', '1');
		    	
		//	$("#"+idSpan).html("		Reporte creado");
			$("#"+idSpan).delay(4000).hide(600);
			$("div."+divClass).delay(4000).show(600);
		    }
		},
		error : function(jqXHR,e) {
		    	alert(e);
			if (jqXHR.status != 200) {
			window.location = "error";
			}else{
				window.location = "/login?session=false";
			}

			console.log("ERROR: ", e);				
		}
	});

}

/*
function generarReporte(url,idSpan){
	//DO POST
	var x = null;
	$('#'+idSpan+'2').attr("aria-valuenow",50); 
	$('#'+idSpan+'2').attr("style","width:50%");
	 try {
		 
		 $('#'+idSpan+'2').attr("aria-valuenow",75);
	    	$('#'+idSpan+'2').attr("style","width:75%");
	    	$('#'+idSpan+'2').attr("aria-valuenow",100);
	    	$('#'+idSpan+'2').attr("style","width:100%");
	    	setTimeout(function(){
		    	//	window.open(url,"_blank");
	    		x = window.open(url,"_blank");
		    		}, 2000);
	    	$('img.image').css('opacity', '1');
	    	$("#"+idSpan).delay(6000).hide(600);
	      
	     // $('.aReporte').click(function(e) { });
	    } catch (e) {
	      alert(e);
	      window.location = "error";
	    } finally {
	    	alert(x);
	      /*if (!x) {
	        alert("errrrrrrrrr!");
	      }
	    }

	}*/


/*************************************************TIEMPO PROMEDIO******************************************************/

function reporteTiempoPromedio(tipo,idSpan,divClass){
	$("div."+divClass).hide(600);
	$("#divLogosReportes").show();
	$("#divLogosReportes").css('position','inherit !important');
	if($("#idRegionTiempo").val() != null){
		idRegion= $("#idRegionTiempo").val();
	}else{
		idRegion=0;
	}
	
	$('#'+idSpan).show();
	$('#'+idSpan).attr("aria-valuenow",20); 
	//$('#'+idSpan).attr("style","width:10%");
	$('#'+idSpan+'2').attr("style","width:20%");

	
	if(tipo == 1){
	generarReporte("reporteTiempoPromedio?type=pdf&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"",idSpan,divClass);
	}else if(tipo == 2){
		generarReporte("reporteTiempoPromedio?type=vnd.openxmlformats-officedocument.spreadsheetml.sheet&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"",idSpan,divClass);
	}else if(tipo == 3){
		generarReporte("reporteTiempoPromedio?type=html&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"",idSpan,divClass);
	}
	
}


/*************************************************BENEFICIARIOS******************************************************/

function beneficiariosTodos(tipo,idSpan,divClass){	
	$("div."+divClass).hide(600);
	
	
	
	$('#'+idSpan).show();
	$('#'+idSpan).attr("aria-valuenow",20); 
	//$('#'+idSpan).attr("style","width:10%");
	$('#'+idSpan+'2').attr("style","width:20%");

	
	if(tipo == 1){
	generarReporte("reporteBeneficiariosTodos?valores="+getValuePorClase('hid')+"&type=pdf",idSpan,divClass);
	}else if(tipo == 2){
		generarReporte("reporteBeneficiariosTodos?valores="+getValuePorClase('hid')+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet",idSpan,divClass);
	}else if(tipo == 3){
		generarReporte("reporteBeneficiariosTodos?valores="+getValuePorClase('hid')+"&type=html",idSpan,divClass);
	}

}

function beneficiarioNombre(tipo,idSpan,divClass){	
	$("div."+divClass).hide(600);
	if($("#idBeneNomHidden").val() == ''){
		alert('Debes escribir y seleccionar beneficiario');
	}else{	
	
	
	$('#'+idSpan).show();
	$('#'+idSpan+'2').attr("aria-valuenow",25); 
	//$('#'+idSpan).css("width","10%");
	$('#'+idSpan+'2').attr("style","width:25%");
//	$('#'+idSpan+'2').html("Creando reporte 10%");
	
	if(tipo == 1){
	generarReporte("reporteBeneficiarioNombre?type=pdf&idBeneficiario="+$("#idBeneNomHidden").val(),idSpan,divClass);
	}else if(tipo == 2){
		generarReporte("reporteBeneficiarioNombre?type=vnd.openxmlformats-officedocument.spreadsheetml.sheet&idBeneficiario="+$("#idBeneNomHidden").val(),idSpan,divClass);
	}else if(tipo == 3){
		generarReporte("reporteBeneficiarioNombre?type=html&idBeneficiario="+$("#idBeneNomHidden").val(),idSpan,divClass);
	}
	}
}

function beneficiariosGeneral(tipo,idSpan,divClass){
	
	if(getValueCheckboxPorClase('chk') != ''){
	
	if($("#idPeriodoGeneral").val()== null){
		idPer = 0;
	}else{
		idPer = $("#idPeriodoGeneral").val();
	}
	
	if($("#idRegionGeneral").val()== null){
		idReg = 0;
	}else{
		idReg = $("#idRegionGeneral").val();
	}
	
	$("div."+divClass).hide(600);
	
	$('#'+idSpan).show();
	$('#'+idSpan).attr("aria-valuenow",20); 
	//$('#'+idSpan).attr("style","width:10%");
	$('#'+idSpan+'2').attr("style","width:20%");

	
	if(tipo == 1){
	generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=pdf",idSpan,divClass);
	}else if(tipo == 2){
		generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet",idSpan,divClass);
	}else if(tipo == 3){
		generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=html",idSpan,divClass);
	}
	
	}else{
		alert('Debes seleccionar al menos un dato');
	}

}