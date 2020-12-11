'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleUploadForm1 = document.querySelector('#multipleUploadForm1');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);
    console.log("hola");

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadSingleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
        	formData.append("idUsuario", $("#idUsuario").val());
        	
        	 $("#registros").html("");
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/administracion/import",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                    $("#registros").append(data);
                    $("#tabal").show();

                },
                error: function (e) {

                    $("#result").text(e.responseText);
                    console.log("ERROR : ", e);
                    $("#btnSubmit").prop("disabled", false);

                }
            });
        	
       
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        } 
    }

    xhr.send(formData);
    
    
}

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
        formData.append("idPeriodo",$("#idPeriodoCF").val());
        formData.append("idDonativo",$("#idBenefactorHiddenCF").val());
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p style='color:#00993D; font-weight:bold;'>Todos los archivos subieron con éxito</p>";
            /*for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }*/
            
            /*$("#postResultDiv").show();
			if(result == "Done"){
				$("#postResultDiv").html("<p class='divRespuesta'>! Quincena modificada !<br></p>");
			}else{
				$("#postResultDiv").html("<strong>Error</strong>");
			}*/
			
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
            
            $("#multipleFileUploadSuccess").delay(6000).hide(600);
            
            $("#subirYMostrarCF").show();		
			var datos = {
					idPeriodo : $("#idPeriodoCF").val(),
					idDonativo : $("#idBenefactorHiddenCF").val()
					
				}
			$("#listarArchivos").load("/listFiles", datos,function( response, status, xhr ) {
			  if(response.includes("Sesión inactiva")){				 
					 window.location = "/login?session=false";
					    }
				  
				if(xhr.status==200 && xhr.statusText== "parsererror"){
					window.location = "/login?session=false";
				}
			}); 
			
			$("#multipleFileUploadInput").val('');
			$("#fileSize").text('0');
            
        } else {
        	alert(xhr.responseText);
            alert(xhr.status);
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}



function updateSize() {
    var nBytes = 0, 
            oFiles = document.getElementById("multipleFileUploadInput").files,
            nFiles = oFiles.length;
    for (var nFileId = 0; nFileId < nFiles; nFileId++) {
        nBytes += oFiles[nFileId].size;       
    }
    

    var sOutput = nBytes + " bytes";
    // optional code for multiples approximation
    for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
        sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
    }
    // end of optional code
    
    //document.getElementById("fileNum").innerHTML = nFiles;
    document.getElementById("fileSize").innerHTML = sOutput ;
}

function extension(){
	var fileName = '', 
    oFiles = document.getElementById("singleFileUploadInput").files,
    nFiles = oFiles.length;
	for (var nFileId = 0; nFileId < nFiles; nFileId++) {
		fileName += oFiles[nFileId].name;
	}
	
	return fileName.split('.').pop();
	
}

function compruebaExtencionExcel(){
	console.log(extension());
	if(extension() != "xlsx"){
		$("#singleFileUploadInput").val('');
		document.getElementById("fileSize").innerHTML = '0' ;
		alert("El archivo debe ser un Excel con extensión .xlsx");
		
	}
	
}

function cambiaPeriodoCF(){
	
	$("#subirYMostrarCF").hide();
	
	$("#benefactorCF").val("");
	$("#idBenefactorHiddenCF").val("");
	$("#valBenefactorHiddenCF").val("");
	if($("#idPeriodoCF").val() > 0){
	$("#benefactorCF").prop('disabled',false);
	}else{
		$("#benefactorCF").prop('disabled',true);
	}

}


function autocompleteDonanteCF() {
	
	
	
	$("#benefactorCF").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "/ingresos/autocompleteDonantesTodos",
				dataType : "json",
				data : {
					term : request.term,
					idPeriodo : $("#idPeriodoCF").val()
				},
				success : function(data) {
					$("#subirYMostrarCF").hide();

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
			$("#subirYMostrarCF").show();		
			var datos = {
					idPeriodo : $("#idPeriodoCF").val(),
					idDonativo : ui.item.value
					
				}
			$("#listarArchivos").load("/listFiles", datos,function( response, status, xhr ) {
			  if(response.includes("Sesión inactiva")){				 
					 window.location = "/login?session=false";
					    }
				  
				if(xhr.status==200 && xhr.statusText== "parsererror"){
					window.location = "/login?session=false";
				}
			}); 
			
			
			if(ui.item.value=='Sin donante'){
			$("#idBenefactorHiddenCF").val(0);
			}else{
				$("#idBenefactorHiddenCF").val(ui.item.value);
			}
			$("#valBenefactorHiddenCF").val(this.value);
		
			return false;
		},
		minLength: 0
		

	});
}

