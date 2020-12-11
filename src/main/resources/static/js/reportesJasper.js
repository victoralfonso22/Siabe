/***************************************************FUNCION GENARAL PARA GENERACION DE REPORTES****************************/
function generarReporte(url,idSpan,divClass){
//DO POST
	
//	$('#'+idSpan+'2').attr("aria-valuenow",50); 
//	$('#'+idSpan+'2').attr("style","width:50%");
//	$('#'+idSpan+'2').html("Creando reporte 50%");
$.ajax({
    		type : "POST",
		url : url,		
		success : function(result) {
		    if(result.includes("Sesión inactiva")){
			window.location = "/login?session=false";
		    }else{
		    	move();
		    
		   /* 	$('#'+idSpan+'2').attr("aria-valuenow",75); 
		    	//$('#'+idSpan)
		    	$('#'+idSpan+'2').attr("style","width:75%");
		    	$('#'+idSpan+'2').attr("aria-valuenow",100); 
		    	//$('#'+idSpan)
		    	$('#'+idSpan+'2').attr("style","width:100%");*/
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
			alert(jqXHR.responseText);
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
	
	carrera = 0;
	facultad = 0;
	area= 0;
	if($("#car").is(':checked')){
		carrera = 1;
	}
	if($("#fac").is(':checked')){
		facultad = 1;
	}
	if($("#are").is(':checked')){
		area = 1;
	}
	
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
	generarReporte("reporteTiempoPromedio?type=pdf&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"&carrera="+carrera+"&facultad="+facultad+"&area="+area,idSpan,divClass);
	}else if(tipo == 2){
		generarReporte("reporteTiempoPromedio?type=vnd.openxmlformats-officedocument.spreadsheetml.sheet&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"&carrera="+carrera+"&facultad="+facultad+"&area="+area,idSpan,divClass);
	}else if(tipo == 3){
		generarReporte("reporteTiempoPromedio?type=html&idPeriodo="+$("#peridoTSeleccionado").val()+"&idRegion="+idRegion+"&inputBusca="+$("#buscarTiempoPromedio").val()+"&carrera="+carrera+"&facultad="+facultad+"&area="+area,idSpan,divClass);
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
	
	conta = '';
	
	if($("#idBecaGeneral").val() == 4){
		if(getValueCheckboxPorClase('chkDe') != ''){
			conta = 'valores';
		}

	}else if($("#idBecaGeneral").val() == 3){
		if(getValueCheckboxPorClase('chk') != '' || getValueCheckboxPorClase('chkApoyo') != ''){
			conta = 'valores';
		}
	}else{
		if(getValueCheckboxPorClase('chk') != '' ){
			conta = 'valores';
		}
	}
	
	
	if(conta != ''){
	
	if($("#idPeriodoGeneral").val()== ''){
		idPer = 0;
		$("#periodoNom").prop("checked", true);
	}else{
		idPer = $("#idPeriodoGeneral").val();
		$("#periodoNom").prop("checked", false);
	}
	
	if($("#idRegionGeneral").val()== ''){
		idReg = 0;
	}else{
		idReg = $("#idRegionGeneral").val();
	}
	
	$("div."+divClass).hide(600);
	
	$('#'+idSpan).show();
	$("#idBeneficiario").prop("checked", true);
	
var contadores =  [];
	
if($("#idBecaGeneral").val() != 4){
	contadores.push(getContadorCheckboxPorClase("aca"));
	contadores.push(getContadorCheckboxPorClase("gen"));
	contadores.push(getContadorCheckboxPorClase("fam"));
	contadores.push(getContadorCheckboxPorClase("est"));
	contadores.push(getContadorCheckboxPorClase("dcon"));
	contadores.push(getContadorCheckboxPorClase("tref"));
	contadores.push(getContadorCheckboxPorClase("face"));
	contadores.push(getContadorCheckboxPorClase("ban"));
	contadores.push(getContadorCheckboxPorClase("obs"));
	if($("#idBecaGeneral").val() == 3){
		if($(".chkApoyo").is(':checked')){
			contadores.pop();
			contadores.push(getContadorCheckboxPorClase("obs")+1);
			
		}
	}
}else{
	contadores.push(getContadorCheckboxPorClase("gen1"));
	contadores.push(getContadorCheckboxPorClase("fam1"));
	contadores.push(getContadorCheckboxPorClase("inf"));
	contadores.push(getContadorCheckboxPorClase("tref1"));
	contadores.push(getContadorCheckboxPorClase("face1"));
	contadores.push(getContadorCheckboxPorClase("her"));
	contadores.push(getContadorCheckboxPorClase("edu"));
	contadores.push(getContadorCheckboxPorClase("obs1"));
}
	
	if(tipo == 1){
		
		if($("#idBecaGeneral").val() == 4){
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chkDe')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=pdf"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);

		}else if($("#idBecaGeneral").val() == 3){
		
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+","+getValueCheckboxPorClase('chkApoyo')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=pdf"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);
		
		}else{
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=pdf"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);
		}
		
		
	
	
	}else if(tipo == 2){
		
		if($("#idBecaGeneral").val() == 4){
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chkDe')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);			

		}else if($("#idBecaGeneral").val() == 3){			
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+","+getValueCheckboxPorClase('chkApoyo')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);			
		
		}else{			
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);
			
		}
		
		
		
		
		
	}else if(tipo == 3){
		if($("#idBecaGeneral").val() == 4){
		
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chkDe')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=html"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);

		}else if($("#idBecaGeneral").val() == 3){			
			
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+","+getValueCheckboxPorClase('chkApoyo')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=html"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);
		
		}else{			
			
			generarReporte("reporteBeneficiariosGeneral?valores="+getValueCheckboxPorClase('chk')+"&idTipoBeca="+$("#idBecaGeneral").val()+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=html"+"&tipoB="+$("#idBecaGeneral").text()+"&contadores="+contadores.toString(),idSpan,divClass);
			
		}
		
		
	}
	
	}else{
		alert('Debes seleccionar al menos un dato');
	}

}


function donantesGeneral(tipo,idSpan,divClass){
	
	if(getValueCheckboxPorClase('chk') != ''){
	
	if($("#idPeriodoGeneralDonante").val()== ''){
		idPer = 0;
	}else{
		idPer = $("#idPeriodoGeneralDonante").val();
	}
	
	if($("#idRegionGeneral").val()== ''){
		idReg = 0;
	}else{
		idReg = $("#idRegionGeneral").val();
	}
	
	$("div."+divClass).hide(600);
	 
	$('#'+idSpan).show();
	
	
	$("#idDonativo").prop("checked", true);
	//$("#periodoNom").prop("checked", false);
	
	var contadores =  [];
	
	contadores.push(getContadorCheckboxPorClase("gen"));
	contadores.push(getContadorCheckboxPorClase("con"));
	contadores.push(getContadorCheckboxPorClase("par"));
	contadores.push(getContadorCheckboxPorClase("fis"));
	contadores.push(getContadorCheckboxPorClase("don"));
	contadores.push(getContadorCheckboxPorClase("obs"));
	
	if(tipo == 1){
		
	generarReporte("reporteDonantesGeneral?valores="+getValueCheckboxPorClase('chk')+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=pdf"+"&contadores="+contadores.toString(),idSpan,divClass);

	}else if(tipo == 2){
	
		generarReporte("reporteDonantesGeneral?valores="+getValueCheckboxPorClase('chk')+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet"+"&contadores="+contadores.toString(),idSpan,divClass);
	
	}else if(tipo == 3){
		
		generarReporte("reporteDonantesGeneral?valores="+getValueCheckboxPorClase('chk')+"&idPeriodo="+idPer+"&idRegion="+idReg+"&type=html"+"&contadores="+contadores.toString(),idSpan,divClass);
	
	}
	
	}else{
		alert('Debes seleccionar al menos un dato');
	}

}


function cobranzaGeneral(tipo,idSpan,divClass){

	
	$("div."+divClass).hide(600);
	 
	$('#'+idSpan).show();
	

	if($("#medioCobroC").val() == 1){

	if(tipo == 1){
		
		generarReporte("reporteNominaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&idQuincena="+$("#idQuincena").val()+"&type=pdf",idSpan,divClass);

	}else if(tipo == 2){
	
		generarReporte("reporteNominaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&idQuincena="+$("#idQuincena").val()+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet",idSpan,divClass);
	
	}else if(tipo == 3){
		
		generarReporte("reporteNominaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&idQuincena="+$("#idQuincena").val()+"&type=html",idSpan,divClass);
	
	}
	}else if($("#medioCobroC").val() == 2){

	if(tipo == 1){
		
		generarReporte("reporteDepTraGeneral?idPeriodo="+$("#idPeriodoC").val()+"&type=pdf",idSpan,divClass);

	}else if(tipo == 2){
	
		generarReporte("reporteDepTraGeneral?idPeriodo="+$("#idPeriodoC").val()+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet",idSpan,divClass);
	
	}else if(tipo == 3){
		
		generarReporte("reporteDepTraGeneral?idPeriodo="+$("#idPeriodoC").val()+"&type=html",idSpan,divClass);
	
	}
	}else if($("#medioCobroC").val() == 3){

	if(tipo == 1){
		
		generarReporte("reporteTarjetaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&anio="+$("#anio").val()+"&mes="+$("#mes").val()+"&type=pdf",idSpan,divClass);

	}else if(tipo == 2){
	
		generarReporte("reporteTarjetaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&anio="+$("#anio").val()+"&mes="+$("#mes").val()+"&type=vnd.openxmlformats-officedocument.spreadsheetml.sheet",idSpan,divClass);
	
	}else if(tipo == 3){
		
		generarReporte("reporteTarjetaGeneral?idPeriodo="+$("#idPeriodoC").val()+"&anio="+$("#anio").val()+"&mes="+$("#mes").val()+"&type=html",idSpan,divClass);
	
	}
	}

	

}


var i = 0;
function move() {
  if (i == 0) {
    i = 1;
    var elem = document.getElementById("resultReport112");
    var width = 1;
    var id = setInterval(frame, 10);
    function frame() {
      if (width >= 100) {
        clearInterval(id);
        i = 0;
      } else {
        width++;
        elem.style.width = width + "%";
      }
    }
  }
}
