function cambiaPeriodoAs() {
	$("#registros").hide();
	$("#labelsB").hide();
	$("#labelsD").hide();
	$("#idDonBene").val("");
	if ($("#idPeriodoAs").val() > 0) {
		$("#idDonBene").prop('disabled', false);
	} else {
		$("#idDonBene").prop('disabled', true);
	}

}


function checkIds(id) {

	$("#idDonBene").val('');
	$("#labelsB").hide();
	$("#labelsD").hide();
	$("#idPeriodoAs").val('');
	$("#idDonBene").prop('disabled', true);
	$("#registros").hide();
	$("#idDonBene").keyup();
	$("#labels").hide();
	if ($("#" + id).val() == '1') {
		$("#donBene").text("Beneficiario");
		$("#lblTotal").text("Monto apoyo");
	} else if ($("#" + id).val() == '0') {
		$("#donBene").text("Donante");
		$("#lblTotal").text("Donativo total");

	}
}


function autocompleteDonBeneAS() {

	if ($('input:radio[name=altaCambio]:checked').val() == 1) {
		tipo = "/egresos/autocompleteBeneficiarioNoDeporAAsig";
	} else {
		tipo = "/ingresos/autocompleteBenefactorNoPatrocinadorA";
	}

	//alert($('input:radio[name=altaCambio]:checked').val()+" "+tipo);
	//alert(tipo);
	$("#idDonBene").autocomplete({
		source: function (request, response) {
			$.ajax({
				url: tipo,
				dataType: "json",
				data: {
					term: request.term,
					idPeriodo: $("#idPeriodoAs").val()
				},
				success: function (data) {
					//		$("#subirYMostrarCF").hide();

					response($.map(data, function (item) {
						if ($('input:radio[name=altaCambio]:checked').val() == 1) {

							return {

								label: item.nombreCompletoBene + " " + item.tipoBeca,
								value: item.idBeneficiario,
								montoBeca: item.montoBeca


							};
						} else {
							return {

								label: item.nombreCompletoDon,
								value: item.idDonativo,
								donativoTotal: item.donativoTotal

							};

						}
					}));

				},
				error: function (jqXHR, textStatus) {
					//alert(jqXHR.responseText);
					if (jqXHR.status == 200 && textStatus == "parsererror") {
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus: function () {

			// prevent value inserted on focus
			return false;
		},
		select: function (event, ui) {

			this.value = ui.item.label;
			$("#identificador").val(ui.item.value);

			if ($('input:radio[name=altaCambio]:checked').val() == 1) {

				$("#donBene").text("Beneficiario");
				$("#lblTotal").text("Monto apoyo");
				$("#labelsB").show();
				$("#labelsD").hide();
				$("#spMontoBeca").text(" $ " + ui.item.montoBeca.format(2, 3));
				$("#spDSaldo").text("");


				var datos = {
					idDonante: 0,
					idBeneficiario: ui.item.value
				}
				$("#spMontoAsignado").load("/administracion/regresaDonativoAsig", datos, function (response, status, xhr) {
					if (response.includes("Sesión inactiva")) {
						window.location = "/login?session=false";
					} else {
						$("#spBSaldo").text("$ " + (ui.item.montoBeca - parseFloat($("#spMontoAsignado").text())).format(2, 3));
						sp = $("#spMontoAsignado").text();
						$("#spMontoAsignado").text("$ " + parseFloat(sp).format(2, 3));
					}

					if (xhr.status == 200 && xhr.statusText == "parsererror") {
						window.location = "/login?session=false";
					}
				});

				reg = 1;
				if (ui.item.value == 'Sin beneficiario') {
					$("#idBeneficiarioHidden").val(0);
				} else {
					$("#idBeneficiarioHidden").val(ui.item.value);
				}
				$("#valBeneficiarioHidden").val(this.value);

			} else {

				$("#donBene").text("Donante");
				$("#lblTotal").text("Donativo total");
				$("#labelsD").show();
				$("#labelsB").hide();
				$("#spMontoBeca").text("");
				$("#spDonativoTotal").text(" $ " + ui.item.donativoTotal.format(2, 3));
				$("#spBSaldo").text("");

				var datos = {
					idDonante: ui.item.value,
					idBeneficiario: 0
				}
				$("#spDonativoAsignado").load("/administracion/regresaDonativoAsig", datos, function (response, status, xhr) {
					if (response.includes("Sesión inactiva")) {
						window.location = "/login?session=false";
					} else {
						$("#spDSaldo").text("$ " + (ui.item.donativoTotal - parseFloat($("#spDonativoAsignado").text())).format(2, 3));
						sp = $("#spDonativoAsignado").text();
						$("#spDonativoAsignado").text("$ " + parseFloat(sp).format(2, 3));
					}

					if (xhr.status == 200 && xhr.statusText == "parsererror") {
						window.location = "/login?session=false";
					}
				});



				reg = 0;

				if (ui.item.value == 'Sin donante') {
					$("#idBenefactorHiddenCF").val(0);
				} else {
					$("#idBenefactorHiddenCF").val(ui.item.valueD);
				}
				$("#valBenefactorHiddenCF").val(this.valueD);
			}


			$("#labels").show();

			$("#registros").show();
			var datos = {
				idPeriodo: $("#idPeriodoAs").val(),
				tipoAsig: reg,
				id: ui.item.value
			}
			$("#registros").load("/administracion/actualizaRegistrosAsig", datos, function (response, status, xhr) {
				if (response.includes("Sesión inactiva")) {
					window.location = "/login?session=false";
				} else {

					if ($('input:radio[name=altaCambio]:checked').val() == 1) {
						$("#etiquetaTab").text("Donantes");
						$("#thTipoBeca").hide();
						$("#tdTipoBeca").hide();
					} else {
						$("#etiquetaTab").text("Beneficiarios");
						$("#thTipoBeca").show();
						$("#tdTipoBeca").show();
					}

				}

				if (xhr.status == 200 && xhr.statusText == "parsererror") {
					window.location = "/login?session=false";
				}
			});




			return false;
		},
		minLength: 0


	});
}

function deshabilitaMonto(tipo, id) {


	if ($("#" + id).is(':checked')) {
		if ($("#montoAsig" + id).val() == "" || $("#montoAsig" + id).val() == "$0.00") {
			$("#montoAsig" + id).val("");
		}
		$("#montoAsig" + id).prop('disabled', false);
	} else {
		guardaDonativo(0, tipo, id);
		$("#montoAsig" + id).val("$0.00");
		$("#montoAsig" + id).prop('disabled', true);
	}

}


function replaceAll(text, busca, reemplaza) {
	while (text.toString().indexOf(busca) != -1)
		text = text.toString().replace(busca, reemplaza);
	return text;
}


function guardaDonativo(donativo, tipo, id) {
	bandera = true;
	if (donativo == "") {
		$("#montoAsig" + id).val("$0.00");
		$("#montoAsig" + id).prop('disabled', true);
		// alert()
		document.getElementById(id).checked = false;
		donativo = 0;
	} else {
		don = replaceAll(donativo, "$", "");
		dona = replaceAll(don, ",", "");
		//don = donativo.replace("$","").replace(",","");

		donativo = parseFloat(dona);

	}

	iden = $("#identificador").val();

	if (tipo == 1) {
		idBeneficiario = $("#identificador").val();
		idDonante = id;
	} else {
		idBeneficiario = id;
		idDonante = $("#identificador").val();
	}

	hide = replaceAll($("#montoAsigHidden" + id).val(), "$", "");
	mtHide = replaceAll(hide, ",", "");

	montoHide = parseFloat(mtHide);


	if (donativo == montoHide) {
		bandera = false;
	}


	if (tipo == 1) {

		donaAsig = replaceAll($("#spMontoAsignado").text(), "$", "");
		donaAsig = replaceAll(donaAsig, ",", "");
		donativoBeneficiarioAsig = (parseFloat(donativo) + parseFloat(donaAsig));

		donaTotal = replaceAll($("#spMontoBeca").text(), "$", "");
		donaTotal = replaceAll(donaTotal, ",", "");

		//donaTotal = parseFloat(donaT).format(2,3);

		saldo = (parseFloat(donaTotal) - parseFloat(donativoBeneficiarioAsig));

		//	alert(saldo +" / "+ donaTotal +" / "+ donativoBeneficiarioAsig);

	} else {

		donaAsig = replaceAll($("#spDonativoAsignado").text(), "$", "");
		donaAsig = replaceAll(donaAsig, ",", "");

		donativoDonanteAsig = (parseFloat(donativo) + parseFloat(donaAsig));

		donaTotal = replaceAll($("#spDonativoTotal").text(), "$", "");
		donaTotal = replaceAll(donaTotal, ",", "");

		//donaTotal = parseFloat(donaT).format(2,3);

		saldo = (parseFloat(donaTotal) - parseFloat(donativoDonanteAsig));

		//	alert(saldo +" / "+ donaTotal +" / "+ donativoDonanteAsig);
	}

	if (saldo >= 0 && bandera) {

		var parametrosPermisos = { "idDonante": idDonante, "idBeneficiario": idBeneficiario, "donativo": donativo };

		$.ajax({
			type: "POST",
			url: "registraDonativoAsig",
			data: parametrosPermisos,
			success: function (result) {
				//  alert(result);
				if (result.includes("Sesión inactiva")) {
					window.location = "/login?session=false";
				} else if (result == "Done") {


					var datos = {
						idPeriodo: $("#idPeriodoAs").val(),
						tipoAsig: tipo,
						id: iden
					}
					$("#registros").load("/administracion/actualizaRegistrosAsig", datos, function (response, status, xhr) {
						if (response.includes("Sesión inactiva")) {
							window.location = "/login?session=false";
						} else {

							if ($('input:radio[name=altaCambio]:checked').val() == 1) {
								$("#etiquetaTab").text("Donantes");
								$("#thTipoBeca").hide();
								$("#tdTipoBeca").hide();
							} else {
								$("#etiquetaTab").text("Beneficiarios");
								$("#thTipoBeca").show();
								$("#tdTipoBeca").show();
							}

							if (tipo == 1) {
								//		alert(donativoBeneficiarioAsig);
								$("#spMontoAsignado").text("$ " + parseFloat(donativoBeneficiarioAsig).format(2, 3));
								$("#spBSaldo").text("$ " + parseFloat(saldo).format(2, 3));

							} else {
								$("#spDonativoAsignado").text("$ " + parseFloat(donativoDonanteAsig).format(2, 3));
								$("#spDSaldo").text("$ " + parseFloat(saldo).format(2, 3));

							}

						}

						if (xhr.status == 200 && xhr.statusText == "parsererror") {
							window.location = "/login?session=false";
						}
					});

				} else if (result == "DonativoExeD") {
					alert("El donativo que trata de asignar excede el saldo disponible del donante");
				} else if (result == "DonativoExeB") {
					alert("El donativo que trata de asignar excede el saldo disponible del beneficiario");
				}

				console.log(result);
			},
			error: function (jqXHR, e) {
				alert(jqXHR.responseText);
				if (jqXHR.status != 200) {
					window.location = "/error";
				} else {
					window.location = "/login?session=false";
				}
				console.log("ERROR: ", e);
			}
		});
	} else if (saldo < 0 && bandera) {
		//	$("#montoAsig"+id).val("");
		if (tipo == 1) {
			alert("El Donativo asignado no puede ser mayor al Donativo total");
		} else {
			alert("El Monto asignado no puede ser mayor al Monto de beca/apoyo");
		}

	}

	//
}


function cambiaPeriodoRD() {



	$("#idRegionRD").prop("disabled", false);
	$("#idCampaniaRD").prop("disabled", false);
	$("#idPeriodoDestinoRD").prop("disabled", false);
	$("#registros").show();




	var datos = {
		idPeriodo: $("#idPeriodoRD").val(),
	}
	$("#idRegionRD").load("/administracion/actualizaRegionRD", datos, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {


		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});



	var datos2 = {
		idPeriodo: $("#idPeriodoRD").val(),
	}
	$("#idPeriodoDestinoRD").load("/administracion/periodosMayores", datos2, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {


		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});


	if ($("#idRegionRD").val() == "") {
		reg = 0;
	} else {
		reg = $("#idRegionRD").val();
	}

	if ($("#idCampaniaRD").val() == "") {
		campa = 0;
	} else {
		campa = $("#idCampaniaRD").val();
	}


	actualizaTablaRD($("#idPeriodoRD").val(), reg, campa);
	$("#checkTodosRD").prop("checked", false);



}


function actualizaTablaRD(idPeriodo, idRegion, idCampania) {
	$("#postResultDiv").html("<div class='loader'></div>");
	$("#registros").hide();
	var datos1 = {
		idPeriodo: idPeriodo,
		idRegion: idRegion,
		idCampania: idCampania
	}
	$("#registrosRD").load("/administracion/actualizaTablaRD", datos1, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {
			if ($("#muestraRegistro").val() == 0) {
				$("#postResultDiv").html("<span style='color:red; font-weight:bold;'>No existen registros para mostrar</span>");
				/*$("#idRegionRD").prop("disabled",true);
				$("#idCampaniaRD").prop("disabled",true);
				$("#idPeriodoDestinoRD").prop("disabled",true);*/
			} else {
				$("#postResultDiv").html("");
				$("#checkTodosRD").prop("checked", false);
				$("#registros").show();
			}

		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});
}

function cambiaRegionRD() {

	if ($("#idCampaniaRD").val() == "") {
		campa = 0;
	} else {
		campa = $("#idCampaniaRD").val();
	}

	if ($("#idRegionRD").val() != '') {

		actualizaTablaRD($("#idPeriodoRD").val(), $("#idRegionRD").val(), campa);

	} else {
		actualizaTablaRD($("#idPeriodoRD").val(), 0, campa);


	}
	//   $('input[name=checkPrincipal]').attr('checked', false);
	$("#checkTodosRD").prop("checked", false);
}

function cambiaCampaniaRD() {

	if ($("#idRegionRD").val() == "") {
		reg = 0;
	} else {
		reg = $("#idRegionRD").val();
	}

	if ($("#idCampaniaRD").val() != '') {

		actualizaTablaRD($("#idPeriodoRD").val(), reg, $("#idCampaniaRD").val());

	} else {
		actualizaTablaRD($("#idPeriodoRD").val(), reg, 0);

	}
	$("#checkTodosRD").prop("checked", false);
}

/*************************************************************Funciones para el refrendo de donantes */

function empaquetaRefrendo(cadena) {
	var arrayCadena = cadena.split(',');
	var retorno = "";
	bandera = false;
	mont = 0;
	npago = 0;
	var mensaje = "Debe llenar los campos obligatorios ";

	for (var i = 0; i < arrayCadena.length; i++) {
		retorno += arrayCadena[i] + "-" + $("#montoTotal" + arrayCadena[i]).val() + "-" + $("#medioCobro" + arrayCadena[i]).val() + "-";

		if ($("#numPagos" + arrayCadena[i]).val() == "") {
			npa = 0
		} else {
			npa = $("#numPagos" + arrayCadena[i]).val();
		}
		retorno += npa;

		if ($("#montoTotal" + arrayCadena[i]).val() == "") {
			bandera = true;
			mont = 1;
			$("#trRow" + arrayCadena[i]).css('background', '#D9534F');
			//	$("#trRow"+arrayCadena[i]).css('color','white');
		} else {
			$("#trRow" + arrayCadena[i]).css('background', 'white');
			//$("#trRow"+arrayCadena[i]).css('color','#333333');
		}

		if ($("#medioCobro" + arrayCadena[i]).val() == 1 || $("#medioCobro" + arrayCadena[i]).val() == 3) {
			if ($("#numPagos" + arrayCadena[i]).val() == "") {
				bandera = true;
				npago = 1;
				$("#trRow" + arrayCadena[i]).css('background', '#D9534F');
				//$("#trRow"+arrayCadena[i]).css('color','white');

			} else {
				$("#trRow" + arrayCadena[i]).css('background', 'white');
				//	$("#trRow"+arrayCadena[i]).css('color','#333333');
			}


			retorno += "-" + $("#inicioPagos" + arrayCadena[i]).val();
		} else {
			if ($("#inicioPagosDTE" + arrayCadena[i]).val() == "") {
				dte = 0;
			} else {
				dte = $("#inicioPagosDTE" + arrayCadena[i]).val();
			}
			retorno += "-" + dte;
		}

		if ($("#observaciones" + arrayCadena[i]).val() == "") {
			obser = " ";
		} else {
			obser = $("#observaciones" + arrayCadena[i]).val();
		}

		retorno += "-" + obser + ",";
	}

	/*if(mont ==1){
		mensaje+="* monto total";
	}
	if(npago ==1){
		mensaje+=" * No. de pagos";
	}*/
	if (bandera) {
		alert(mensaje);
		return null;

	} else {
		return retorno = retorno.substring(0, retorno.length - 1);
	}
}



function guardarRD() {


	if ($("#idPeriodoDestinoRD").val() != null) {
		if (getValueCheckboxPorClase("RD") != "") {

			if (empaquetaRefrendo(getValueCheckboxPorClase("RD")) != null) {

				cadena = empaquetaRefrendo(getValueCheckboxPorClase("RD"));

				var parametrosPermisos = { "cadena": cadena, "idPeriodo": $("#idPeriodoDestinoRD").val() };
				$("#postResultDiv1").html("<div class='loader'></div>");
				$.ajax({
					type: "POST",
					url: "refrendaDonantes",
					data: parametrosPermisos,
					success: function (result) {
						//  alert(result);
						if (result.includes("Sesión inactiva")) {
							window.location = "/login?session=false";
						} else if (result == "Done") {
							$("#postResultDiv1").html("<p class='divRespuesta'>! Donantes refrendados !<br></p>");
						} else {
							$("#postResultDiv1").html("<strong>Error</strong>");
						}

						console.log(result);

						if ($("#idRegionRD").val() == "") {
							reg = 0;
						} else {
							reg = $("#idRegionRD").val();
						}

						if ($("#idCampaniaRD").val() == "") {
							campa = 0;
						} else {
							campa = $("#idCampaniaRD").val();
						}

						actualizaTablaRD($("#idPeriodoRD").val(), reg, campa);
						$("#postResultDiv1").delay(7000).hide(700);
					},
					error: function (jqXHR, e) {
						alert(jqXHR.responseText);
						if (jqXHR.status != 200) {
							window.location = "/error";
						} else {
							window.location = "/login?session=false";
						}
						console.log("ERROR: ", e);
					}
				});





			}

		} else {
			alert("Debe seleccionar al menos un donante a refrendar");
		}

	} else {
		alert("Debe seleccionar un periodo destino para el refrendo");
	}


}


function habilitaDatos(id) {
	if ($("#" + id).is(':checked')) {
		$("#montoTotal" + id).prop('disabled', false);
		$("#medioCobro" + id).prop('disabled', false);
		$("#numPagos" + id).prop('disabled', false);
		$("#inicioPagos" + id).prop('disabled', false);
		$("#observaciones" + id).prop('disabled', false);

		$("#montoTotal" + id).prop('required', true);
		$("#numPagos" + id).prop('required', true);
		$("#inicioPagos" + id).prop('required', true);


		$("#trRow" + id).css('background', 'white');

	} else {
		$("#trRow" + id).css('background', 'white');
		$("#montoTotal" + id).prop('disabled', true);
		$("#montoTotal" + id).val('');
		$("#medioCobro" + id).prop('disabled', true);
		$("#medioCobro" + id).val(1);
		$("#numPagos" + id).prop('disabled', true);
		$("#numPagos" + id).val('');
		$("#inicioPagos" + id).prop('disabled', true);
		$("#inicioPagos" + id).show();
		$("#inicioPagosDTE" + id).hide();
		$("#inicioPagos" + id).val(1);
		$("#inicioPagosDTE" + id).prop('disabled', true);
		$("#inicioPagosDTE" + id).val(1);
		$("#observaciones" + id).prop('disabled', true);
		$("#observaciones" + id).val('');

		$("#montoTotal" + id).prop('required', false);
		$("#numPagos" + id).prop('required', false);
		$("#inicioPagos" + id).prop('required', false);
		$("#inicioPagosDTE" + id).prop('required', false);

	}
}




function valoresDatosRefrendo(id, value) {
	var res = id.split("medioCobro");
	$("#numPagos" + res[1]).prop('required', true);
	if (value == 1) {
		var nom = "inicioPagos" + res[1];
		$("#inicioPagosDTE" + res[1]).hide();

		$("#" + nom).show();
		$("#" + nom).prop('disabled', false);
		$("#inicioPagos" + res[1]).prop('required', true);
		$("#inicioPagosDTE" + res[1]).prop('required', false);
		$("#inicioPagosDTE" + res[1]).prop('disabled', true);

	} else {
		var nom = "inicioPagosDTE" + res[1];
		$("#inicioPagos" + res[1]).hide();
		$("#" + nom).show();
		$("#" + nom).prop('disabled', false);
		$("#inicioPagos" + res[1]).prop('required', false);
		$("#inicioPagos" + res[1]).prop('disabled', true);
		$("#inicioPagosDTE" + res[1]).prop('required', true);

	}

	if (value == 2) {
		$("#inicioPagosDTE" + res[1]).prop('required', false);
		$("#numPagos" + res[1]).prop('required', false);
	}
}

/********************************funciones para refrendo de beneficiarios */
function cambiaPeriodoRB() {


	$("#idTipoBecaRB").prop("disabled", false);
	$("#idRegionRB").prop("disabled", false);
	$("#idPeriodoDestinoRB").prop("disabled", false);
	$("#registros").show();




	var datos = {
		idPeriodo: $("#idPeriodoRB").val(),
	}
	$("#idRegionRB").load("/administracion/actualizaRegionRB", datos, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {


		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});



	var datos2 = {
		idPeriodo: $("#idPeriodoRB").val(),
	}
	$("#idPeriodoDestinoRB").load("/administracion/periodosMayores", datos2, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {


		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});


	if ($("#idRegionRB").val() == "") {
		reg = 0;
	} else {
		reg = $("#idRegionRB").val();
	}

	if ($("#idTipoBecaRB").val() == "") {
		campa = 0;
	} else {
		campa = $("#idTipoBecaRB").val();
	}


	actualizaTablaRB($("#idPeriodoRB").val(), reg, campa);
	$("#checkTodosRB").prop("checked", false);



}


function actualizaTablaRB(idPeriodo, idRegion, idTipoBeca) {
	$("#postResultDiv").html("<div class='loader'></div>");
	$("#registros").hide();
	var datos1 = {
		idPeriodo: idPeriodo,
		idRegion: idRegion,
		idTipoBeca: idTipoBeca
	}
	$("#registrosRB").load("/administracion/actualizaTablaRB", datos1, function (response, status, xhr) {
		if (response.includes("Sesión inactiva")) {
			window.location = "/login?session=false";
		} else {
			if ($("#muestraRegistro").val() == 0) {
				$("#postResultDiv").html("<span style='color:red; font-weight:bold;'>No existen registros para mostrar</span>");
				/*$("#idRegionRB").prop("disabled",true);
				$("#idTipoBecaRB").prop("disabled",true);
				$("#idPeriodoDestinoRB").prop("disabled",true); */
			} else {
				$("#postResultDiv").html("");
				$("#checkTodosRB").prop("checked", false);
				$("#registros").show();
			}

		}

		if (xhr.status == 200 && xhr.statusText == "parsererror") {
			window.location = "/login?session=false";
		}
	});
}

function cambiaRegionRB() {

	if ($("#idTipoBecaRB").val() == "") {
		campa = 0;
	} else {
		campa = $("#idTipoBecaRB").val();
	}

	if ($("#idRegionRB").val() != '') {

		actualizaTablaRB($("#idPeriodoRB").val(), $("#idRegionRB").val(), campa);

	} else {
		actualizaTablaRB($("#idPeriodoRB").val(), 0, campa);


	}
	//   $('input[name=checkPrincipal]').attr('checked', false);
	$("#checkTodosRB").prop("checked", false);
}

function cambiaTipoBecaRB() {

	if ($("#idRegionRB").val() == "") {
		reg = 0;
	} else {
		reg = $("#idRegionRB").val();
	}

	if ($("#idTipoBecaRB").val() != '') {

		actualizaTablaRB($("#idPeriodoRB").val(), reg, $("#idTipoBecaRB").val());

	} else {
		actualizaTablaRB($("#idPeriodoRB").val(), reg, 0);

	}
	$("#checkTodosRB").prop("checked", false);
}


function guardarRB() {


	if ($("#idPeriodoDestinoRB").val() != null) {
		if (getValueCheckboxPorClase("RB") != "") {


			console.log(getValueCheckboxPorClase("RB"));

			var parametrosPermisos = { "cadena": getValueCheckboxPorClase("RB"), "idPeriodo": $("#idPeriodoDestinoRB").val() };
			$("#postResultDiv1").html("<div class='loader'></div>");
			$.ajax({
				type: "POST",
				url: "refrendaBeneficiarios",
				data: parametrosPermisos,
				success: function (result) {
					//  alert(result);
					if (result.includes("Sesión inactiva")) {
						window.location = "/login?session=false";
					} else if (result == "Done") {
						$("#postResultDiv1").html("<p class='divRespuesta'>! Beneficiarios refrendados !<br></p>");
					} else {
						$("#postResultDiv1").html("<strong>Error</strong>");
					}

					//console.log(result);

					if ($("#idRegionRB").val() == "") {
						reg = 0;
					} else {
						reg = $("#idRegionRB").val();
					}

					if ($("#idTipoBecaRB").val() == "") {
						campa = 0;
					} else {
						campa = $("#idTipoBecaRB").val();
					}

					actualizaTablaRB($("#idPeriodoRB").val(), reg, campa);
					$("#postResultDiv1").delay(7000).hide(700);
				},
				error: function (jqXHR, e) {
					alert(jqXHR.responseText);
					if (jqXHR.status != 200) {
						window.location = "/error";
					} else {
						window.location = "/login?session=false";
					}
					console.log("ERROR: ", e);
				}
			});





		} else {
			alert("Debe seleccionar al menos un beneficiario a refrendar");
		}

	} else {
		alert("Debe seleccionar un periodo destino para el refrendo");
	}

}

/*******************************COBRANZA ****************************/

function cambiaPeriodoQuincenaCobranza() {
	bandera = false;

	if ($("#medioCobroC").val() == 1) {	

		if ($("#idQuincena").val() != null && $("#idPeriodoC").val() != null) {
			bandera = true;
			var parametrosPermisos = { "idPeriodo": $("#idPeriodoC").val(), "medioCobro": 1, "idQuincena": $("#idQuincena").val(), "anio":0, "mes" : 0 };
		

		}
	}else if($("#medioCobroC").val() == 2){
		if ( $("#idPeriodoC").val() != null) {
		bandera = true;
			var parametrosPermisos = { "idPeriodo": $("#idPeriodoC").val(), "medioCobro": 2, "idQuincena": 0 , "anio":0, "mes" : 0};
		}
	}else if($("#medioCobroC").val() == 3){
		if ( $("#idPeriodoC").val() != null) {
		bandera = true;
			var parametrosPermisos = { "idPeriodo": $("#idPeriodoC").val(), "medioCobro": 3, "idQuincena": 0 , "anio":$("#anio").val(), "mes" : $("#mes").val()};
		}
	}

	if(bandera){
		$("#registrosRB").html("<div class='loader'></div>");
	$.ajax({
		type: "POST",
		url: "tablaMedioCobro",
		data: parametrosPermisos,
		success: function (result) {
			//  alert(result);
			if (result.includes("Sesión inactiva")) {
				window.location = "/login?session=false";
			} else {
				$("#registrosRB").html(result);
			}

		},
		error: function (jqXHR, e) {
			alert(jqXHR.responseText);
			if (jqXHR.status != 200) {
				window.location = "/error";
			} else {
				window.location = "/login?session=false";
			}
			console.log("ERROR: ", e);
		}
	});
	}else{
		$("#registrosRB").html("");
	}

}

function cambiaMedioCobro(){

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
	$("#btn-guardar-cobranza").hide();
	cambiaPeriodoQuincenaCobranza();
}


function tablaNominas() {
	//var tbody = $('#registrosRB tbody'); 
	//var fila_contenido = tbody.find('tr').first().html();
	//Agregar fila nueva. 


	$('#registrosRB .button_agregar_producto').click(function () {

		var nFilas = $("#registrosRB tr").length - 1;
		var suma = parseInt($("#totalFilas").val()) + 1;
		$("#totalFilas").val(suma);

		if($("#medioCobroC").val() == 1){
		
		var nuevaFila = $("<tr  id='trRow" + $("#totalFilas").val() + "' ><td ><label style='color: #333333; font-weight: normal;' id='lab" + $("#totalFilas").val() + "'> " + nFilas + "</label><input type='hidden' id='numHidde" + $("#totalFilas").val() + "' value='" + $("#totalFilas").val() + "'/></td>"
			+ "<td   ><input type='text' onblur='blurNombre("+ $("#totalFilas").val() +")' onkeyup='autocompletarDonanteCobranza(this.id," + $("#totalFilas").val() + ");' id='auto" + $("#totalFilas").val() + "'> <input type='hidden' id='idDon" + $("#totalFilas").val() + "'/> <input type='hidden' id='nomDon" + $("#totalFilas").val() + "'/></td>"
			+ "<td   ><span id='numPer" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='donaAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='quincenaAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='numDescuento" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><input type='text' onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual" + $("#totalFilas").val() + "','saldo" + $("#totalFilas").val() + "', "+ $("#totalFilas").val() +"); \" onkeypress='return filterFloat(event,this)' id='descuentoQuincenal" + $("#totalFilas").val() + "'><input type='hidden' id='descuentoQuincenalHidden"+$("#totalFilas").val()+"'/></td>"
			+ "<td   ><span id='saldo" + $("#totalFilas").val() + "' /> <input type='hidden' id='saldoActual"+$("#totalFilas").val()+"'/> </td>"
			+ "<td   > <button type='button' class='btn btn-danger button_eliminar_producto'> Eliminar </button></td></tr>");
		//  fila_nueva.append(fila_contenido);
		$("#registrosRB tbody").append(nuevaFila);
		
	}else if($("#medioCobroC").val() == 2){

		var nuevaFila = $("<tr  id='trRow" + $("#totalFilas").val() + "' ><td ><label style='color: #333333; font-weight: normal;' id='lab" + $("#totalFilas").val() + "'> " + nFilas + "</label><input type='hidden' id='numHidde" + $("#totalFilas").val() + "' value='" + $("#totalFilas").val() + "'/></td>"
			+ "<td   ><input type='text' onblur='blurNombre("+ $("#totalFilas").val() +")' onkeyup='autocompletarDonanteCobranza(this.id," + $("#totalFilas").val() + ");' id='auto" + $("#totalFilas").val() + "'> <input type='hidden' id='idDon" + $("#totalFilas").val() + "'/> <input type='hidden' id='nomDon" + $("#totalFilas").val() + "'/></td>"			
			+ "<td   ><span id='donaAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='mesesAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='numDescuento" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><input type='text' onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual" + $("#totalFilas").val() + "','saldo" + $("#totalFilas").val() + "', "+ $("#totalFilas").val() +"); \" onkeypress='return filterFloat(event,this)' id='descuentoQuincenal" + $("#totalFilas").val() + "'><input type='hidden' id='descuentoQuincenalHidden"+$("#totalFilas").val()+"'/></td>"
			+ "<td   ><span id='saldo" + $("#totalFilas").val() + "' /> <input type='hidden' id='saldoActual"+$("#totalFilas").val()+"'/> </td>"
			+ "<td   ><input  type='date' id='fecha"+$("#totalFilas").val()+"' onchange='cambiaFecha(this.value, "+$("#totalFilas").val()+");' /> </td> " 
			+ "<td   > <button type='button' class='btn btn-danger button_eliminar_producto'> Eliminar </button></td></tr>");

		//  fila_nueva.append(fila_contenido);
		$("#registrosRB tbody").append(nuevaFila);


	}else if($("#medioCobroC").val() == 3){

		var nuevaFila = $("<tr  id='trRow" + $("#totalFilas").val() + "' ><td ><label style='color: #333333; font-weight: normal;' id='lab" + $("#totalFilas").val() + "'> " + nFilas + "</label><input type='hidden' id='numHidde" + $("#totalFilas").val() + "' value='" + $("#totalFilas").val() + "'/></td>"
			+ "<td   ><input type='text' onblur='blurNombre("+ $("#totalFilas").val() +")' onkeyup='autocompletarDonanteCobranza(this.id," + $("#totalFilas").val() + ");' id='auto" + $("#totalFilas").val() + "'> <input type='hidden' id='idDon" + $("#totalFilas").val() + "'/> <input type='hidden' id='nomDon" + $("#totalFilas").val() + "'/></td>"
			+ "<td   ><span id='donaAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='mesesAuto" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><span id='numDescuento" + $("#totalFilas").val() + "' /></td>"
			+ "<td   ><input type='text' onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual" + $("#totalFilas").val() + "','saldo" + $("#totalFilas").val() + "', "+ $("#totalFilas").val() +"); \" onkeypress='return filterFloat(event,this)' id='descuentoQuincenal" + $("#totalFilas").val() + "'><input type='hidden' id='descuentoQuincenalHidden"+$("#totalFilas").val()+"'/></td>"
			+ "<td   ><span id='saldo" + $("#totalFilas").val() + "' /> <input type='hidden' id='saldoActual"+$("#totalFilas").val()+"'/> </td>"
			+ "<td   > <button type='button' class='btn btn-danger button_eliminar_producto'> Eliminar </button></td></tr>");

		//  fila_nueva.append(fila_contenido);
		$("#registrosRB tbody").append(nuevaFila);


	}

	});
	//Eliminar fila. 
	$('#registrosRB').on('click', '.button_eliminar_producto', function () {
		id = $(this).closest('tr').prop('id');

		nm = id.replace("trRow","");


		if($("#idDon"+nm).val() > 0){
		eliminaCobranza($("#idDon"+nm).val(),$("#medioCobroC").val());
		}

		$(this).parents('tr').eq(0).remove();

		a = 1;
		if ($("#registrosRB tr").length == 2) {
			$("#btn-guardar-cobranza").hide();
		} else {
			numer = 0;
			for (i = 1; i <= parseInt($("#totalFilas").val()); i++) {
				if ($("#idDon" + i).length > 0) {
					if ($("#idDon" + i).val() != '') {
						numer += 1;
					}
				}
			}
			if (numer == 0) {
				$("#btn-guardar-cobranza").hide();
			} else {
				$("#btn-guardar-cobranza").show();
			}
		}

		console.log("Total"+parseInt($("#totalFilas").val()));
		cal = "";
		for (x = 1; x <= parseInt($("#totalFilas").val()); x++) {
				
			//	console.log($("#lab"+i).length); */
			if ($("#lab" + x).length > 0) {
				$("#lab" + x).empty();
				$("#lab" + x).append(a);
				cal+= $("#lab" + x).text()+" "+a;
				a++;
			} else {
				
			}
		}
		console.log(cal);
		


	});
}


function getNumDescuento(idDonante, id, n) {

	var parametrosPermisos = { "idDonante": idDonante, "medioCobro": $("#medioCobroC").val() };


	$.ajax({
		type: "POST",
		url: "numDescuento",
		data: parametrosPermisos,
		success: function (result) {

			if (parseInt(result) == 0) {
				numero = 1;
			} else {
				numero = parseInt(result);
			}

			$("#" + id).append(numero);

			donativoTol = $("#donaAuto" + n).text().replace('$', '');		
			donativoTol = donativoTol.replace(',', '');

			descuento = $("#descuentoQuincenal"+n).val().replace(",","");

			totalSaldo = parseFloat(donativoTol) - parseFloat(descuento);

			if(numero == 1){
				$("#saldo" + n).empty();
				$("#saldo" + n).append(fomatoDouble(totalSaldo));
				$("#saldoActual" + n).val(totalSaldo);
			}else{
				getSaldo($("#idDon" + n).val(), 'saldo' + n, 'saldoActual' + n, n);
			}
			
			
		},
		error: function (jqXHR, e) {
			alert(jqXHR.responseText);
			if (jqXHR.status != 200) {
				window.location = "/error";
			} else {
				window.location = "/login?session=false";
			}
			console.log("ERROR: ", e);
		}
	});



}


function getSaldo(idDonante, id, hiden, indice) {

	if($("#medioCobroC").val() == 1){
	var parametrosPermisos = { "idDonante": idDonante };
	url = "saldoNomina";
	}

	$.ajax({
		type: "POST",
		url: url,
		data: parametrosPermisos,
		success: function (result) {


			numero = parseFloat(result);
			if($("#medioCobroC").val() == 1 || $("#medioCobroC").val() == 2){
				numero = numero - $("#descuentoQuincenal"+indice).val();
			}

			$("#" + id).append(fomatoDouble(numero));
			$("#" + hiden).val(numero);
		},
		error: function (jqXHR, e) {
			alert(jqXHR.responseText);
			if (jqXHR.status != 200) {
				window.location = "/error";
			} else {
				window.location = "/login?session=false";
			}
			console.log("ERROR: ", e);
		}
	});

}




function verificarSaldoCobranza(donativoQuincenal, saldoTotal, idSaldo, num) {

	donativoTol = $("#donaAuto" + num).text().replace('$', '');		
	donativoTol = donativoTol.replace(',', '');
	console.log(" + "+ saldoTotal);
	console.log(" p "+ $("#"+saldoTotal).val());
	saldoTo = $("#"+saldoTotal).val();
	donativo = 0;

	if(donativoQuincenal != ""){
		donativo = donativoQuincenal;
	}

	descuentoHide = $("#descuentoQuincenalHidden"+num).val();

	console.log(parseFloat(saldoTo) +" | "+ parseFloat(donativo) + " ? "+$("#descuentoQuincenalHidden"+num).val());

	saldo = parseFloat(saldoTo) - parseFloat(donativo) + parseFloat(descuentoHide);

	if(parseFloat(saldo) < 0){
		alert("El descuento no puede ser mayor que el saldo");
		$("#descuentoQuincenal"+num).val(parseFloat(descuentoHide));
		saldo = parseFloat(saldoTo);
	}
	$("#trRow" + num).css('background', 'white');
	$("#" + idSaldo).empty();
	$("#" + idSaldo).append(fomatoDouble(saldo));

}


function autocompletarDonanteCobranza(id, n) {

	ids = "";
	for (i = 1; i <= parseInt($("#totalFilas").val()); i++) {



		if ($("#idDon" + i).length > 0) {
			ids += $("#idDon" + i).val() + ",";
		}
	}

	var res = ids.split(",");
	idss = "";
	for (x = 0; x < res.length; x++) {
		if (res[x] != '') {
			idss += res[x] + ",";
		}
	}


	idss.substring(0, idss.length - 1);


	$("#" + id).autocomplete({
		source: function (request, response) {
			$.ajax({
				url: "/ingresos/autocompleteDonanteCobranza",
				dataType: "json",
				data: {
					term: request.term,
					idPeriodo: $("#idPeriodoC").val(),
					medioCobro: $("#medioCobroC").val(),
					ids: idss
				},
				success: function (data) {

					response($.map(data, function (item) {
						//console.log(value);
						//alert(data);
						return {
							//label: item.nombre + " - " +item.,
							label: item.nombreCompletoDon,
							value: item.idDonativo,
							idDonativo: item.idDonativo,


							numPersonal: item.numPersonal,
							dependenciaAdscripcion: item.dependenciaAdscripcion,

							donativoTotal: item.donativoTotal,
							donativoQuincenal: item.donativoQuincenal,

							numQuincenas: item.numQuincenas,
							idQuincenaInicio: item.idQuincenaInicio,
							quincenaInicio: item.quincenaInicio,

							anioQuincena: item.anioQuincena,

							idCuentaBancaria: item.idCuentaBancaria,
							cuentaBancaria: item.cuentaBancaria,

							referencia: item.referencia,

							numPagos: item.numPagos,
							importeNumPagos: item.importeNumPagos,

							banco: item.banco,
							nombreTarjetahabiente: item.nombreTarjetahabiente,
							red: item.red,
							tipoTarjeta: item.tipoTarjeta,
							numTarjeta: item.numTarjeta,
							mesVencimiento: item.mesVencimiento,
							anioVencimiento: item.anioVencimiento,
							numDescuento: item.numDescuento,
							saldo: item.saldo,

							mesInicioAportacion: item.mesInicioAportacion

						};
					}));

				},
				error: function (jqXHR, textStatus) {
					alert(jqXHR.responseText);
					if (jqXHR.status == 200 && textStatus == "parsererror") {
						window.location = "/login?session=false";
					}
				}
			});
		},
		focus: function () {

			// prevent value inserted on focus
			return false;
		},
		select: function (event, ui) {

			this.value = ui.item.label;

			$("#idDon" + n).val(ui.item.idDonativo);

			if ($("#medioCobroC").val() == 1) {
				$("#numPer" + n).empty();
				$("#numPer" + n).append(ui.item.numPersonal);

				$("#donaAuto" + n).empty();
				$("#donaAuto" + n).append(fomatoDouble(ui.item.donativoTotal));

				$("#quincenaAuto" + n).empty();
				$("#quincenaAuto" + n).append(ui.item.numQuincenas);

				descuento = ui.item.donativoTotal / ui.item.numQuincenas;
				$("#descuentoQuincenal"+n).val(fomatoDoubleSoloNum(descuento));
				$("#descuentoQuincenalHidden"+n).val(fomatoDoubleSoloNum(descuento));

				$("#numDescuento" + n).empty();
				$("#saldo" + n).empty();
				getNumDescuento($("#idDon" + n).val(), 'numDescuento' + n, n);

			}else if ($("#medioCobroC").val() == 2) {
				
				$("#donaAuto" + n).empty();
				$("#donaAuto" + n).append(fomatoDouble(ui.item.donativoTotal));

				$("#mesesAuto" + n).empty();
				$("#mesesAuto" + n).append(ui.item.numPagos);


				console.log(ui.item.numPagos+" meses");
				if(ui.item.numPagos == 0){
					numPa = 1;
				}else{
					numPa = ui.item.numPagos;
				}

				descuento = ui.item.donativoTotal / numPa;



				$("#descuentoQuincenal"+n).val(fomatoDoubleSoloNum(descuento));
				$("#descuentoQuincenalHidden"+n).val(fomatoDoubleSoloNum(descuento));

				$("#numDescuento" + n).empty();
				$("#saldo" + n).empty();
				getNumDescuento($("#idDon" + n).val(), 'numDescuento' + n, n);
			}else if ($("#medioCobroC").val() == 3) {
				
				$("#donaAuto" + n).empty();
				$("#donaAuto" + n).append(fomatoDouble(ui.item.donativoTotal));

				$("#mesesAuto" + n).empty();
				$("#mesesAuto" + n).append(ui.item.numPagos);

				console.log("meses"+ui.item.numPagos);

				if(ui.item.numPagos == 0){
					numPa = 1;
				}else{
					numPa = ui.item.numPagos;
				}

				descuento = ui.item.donativoTotal / numPa;



				$("#descuentoQuincenal"+n).val(fomatoDoubleSoloNum(descuento));
				$("#descuentoQuincenalHidden"+n).val(fomatoDoubleSoloNum(descuento));

				$("#numDescuento" + n).empty();
				$("#saldo" + n).empty();
				getNumDescuento($("#idDon" + n).val(), 'numDescuento' + n, n);
			}

				$("#btn-guardar-cobranza").show();



			return false;
		},
		minLength: 0


	});

}



function blurNombre(indice){
	if($("#auto"+indice).val() == ''){
		$("#auto"+indice).val($("#nomDon"+indice).val());
	}

}


function cambiaFecha(value, num){
	if(value != "" && value != "0000-00-00"){
		$("#trRow" + num).css('background', 'white');
	}else{
		$("#trRow" + num).css('background', '#D9534F');
	}
}

function guardarCobranza() {
	
	cadena = "";
	mensaje = "";
	desc = "";
	fcha = "";
	descuentoQui = false;
	fech = false;
	for (i = 1; i <= parseInt($("#totalFilas").val()); i++) {


		if ($("#idDon" + i).length > 0) {
		if ($("#idDon" + i).val() != '') {
			cadena += $("#idDon" + i).val() + "-";

			if ($("#medioCobroC").val() == 1) {
				mensaje = "<p class='divRespuesta'>! Cobranza de nómina guardada !<br></p>";
				desc = "Debe introducir un descuento quincenal";
				
				if ($("#idQuincena").val() != null) {
					quincenaN = $("#idQuincena").val();
					} else {
					alert("Debe seleccionar una quincena");
					return false;
				}

				anio = 0;

			}else if ($("#medioCobroC").val() == 2) {
					mensaje = "<p class='divRespuesta'>! Cobranza de depósito o transferencia guardada !<br></p>";
					desc = "Debe introducir un descuento";
					fcha = "Debe introducir una fecha";
				

					quincenaN = 0;
					anio = 0;


			}else if ($("#medioCobroC").val() == 3) {
					mensaje = "<p class='divRespuesta'>! Cobranza de tarjeta de crédito o débito guardada !<br></p>";
					desc = "Debe introducir un descuento";
	
					quincenaN = 0;
					anio = $("#anio").val();


			}
					cadena += $("#numDescuento" + i).text() + "-";

					if($("#descuentoQuincenal" + i).val() != ""){
						desnt = $("#descuentoQuincenal" + i).val().replace(",","");
					cadena += desnt + "-";
					}else{
						$("#trRow" + i).css('background', '#D9534F');
						descuentoQui = true;
					
					}

					donativoTol = $("#saldo" + i).text().replace('$', '');
					donativoTol = donativoTol.replace(',', '');

					saldo = parseFloat(donativoTol);

					if ($("#medioCobroC").val() == 1 || $("#medioCobroC").val() == 3) {
					cadena += saldo + ",";
					}else if ($("#medioCobroC").val() == 2) {
						cadena += saldo + "-";

					if($("#fecha" + i).val() != ""){
						fecha = $("#fecha" + i).val().replace('-','+');
						fecha =fecha.replace('-','+')
					cadena +=  fecha + ",";
					}else{
						$("#trRow" + i).css('background', '#D9534F');
						fech = true;
					
					}

					}

		
			

		} 
	}

	}

	if(descuentoQui){
		alert(desc);
		return false;	
	}

	if(fech){
		alert(fcha);
		return false;	
	}



		cadena = cadena.substring(0,cadena.length-1);

		var parametrosPermisos = { "cadena": cadena, "idPeriodo": $("#idPeriodoC").val(), "idQuincena": quincenaN, "medioCobro": $("#medioCobroC").val(), "idUsuario": $("#idUsuario").val(), "anio" : anio };
		$("#postResultDiv1").html("<div class='loader'></div>");
		$("#postResultDiv1").show();
		$.ajax({
			type: "POST",
			url: "guardaCobranza",
			data: parametrosPermisos,
			success: function (result) {
				//  alert(result);
				if (result.includes("Sesión inactiva")) {
					window.location = "/login?session=false";
				} else if (result == "Done") {
					$("#postResultDiv1").html(mensaje);
				} else {
					$("#postResultDiv1").html("<strong>Error</strong>");
				}

				//console.log(result);

				//actualizaTablaRB($("#idPeriodoRB").val(), reg, campa);
				$("#postResultDiv1").delay(7000).hide(700);
			},
			error: function (jqXHR, e) {
				alert(jqXHR.responseText);
				if (jqXHR.status != 200) {
					window.location = "/error";
				} else {
					window.location = "/login?session=false";
				}
				console.log("ERROR: ", e);
			}
		});
}




function eliminaCobranza(id, medioCobro) {

	if(medioCobro == 1){
		var parametrosPermisos = { "id": id, "medioCobro" : 1, "idPeriodo": $("#idPeriodoC").val(), "idQuincena": $("#idQuincena").val()};
		url= "eliminaNomina";
	}else if(medioCobro == 2){
		var parametrosPermisos = { "id": id, "idPeriodo": $("#idPeriodoC").val()};
		url= "eliminaDepositoTransferencia";
	}else if(medioCobro == 3){
		var parametrosPermisos = { "id": id, "idPeriodo": $("#idPeriodoC").val()};
		url= "eliminaTarjeta";
	}
		
		$.ajax({
			type: "POST",
			url: url,
			data: parametrosPermisos,
			success: function (result) {
				//  alert(result);
				if (result.includes("Sesión inactiva")) {
					window.location = "/login?session=false";
				} 
			},
			error: function (jqXHR, e) {
				alert(jqXHR.responseText);
				if (jqXHR.status != 200) {
					window.location = "/error";
				} else {
					window.location = "/login?session=false";
				}
				console.log("ERROR: ", e);
			}
		});
}
