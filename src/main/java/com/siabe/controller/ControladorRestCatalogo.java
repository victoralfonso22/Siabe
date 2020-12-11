package com.siabe.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siabe.modelo.Usuario;
import com.siabe.modelo.Regiones;
import com.siabe.modelo.RelacionRegion;
import com.siabe.modelo.TiempoPromedio;
import com.siabe.modelo.Areas;
import com.siabe.modelo.Campana;
import com.siabe.modelo.CuentasBancarias;
import com.siabe.modelo.Facultades;
import com.siabe.modelo.Periodo;
import com.siabe.modelo.Quincenas;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.TipoBecaServicio;
import com.siabe.servicio.TipoDonativoServicio;
import com.siabe.servicio.CuentasBancariasServicio;
import com.siabe.servicio.RegionesServicio;
import com.siabe.servicio.RelacionRegionServicio;
import com.siabe.servicio.CampanaServicio;
import com.siabe.servicio.TiempoPromedioServicio;
import com.siabe.servicio.QuincenasServicio;

@RestController
public class ControladorRestCatalogo {

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private PeriodoServicio periodoServicio;	
	
	@Autowired
	private TipoBecaServicio tipoBecaServicio;

	@Autowired
	private RegionesServicio regionesServicio;

	@Autowired
	private RelacionRegionServicio relacionRegionServicio;

	@Autowired
	private CampanaServicio campanaServicio;

	@Autowired
	private TipoDonativoServicio tipoDonativoServicio;

	@Autowired
	private CuentasBancariasServicio cuentasBancariasServicio;
	
	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;
	
	@Autowired
	private QuincenasServicio quincenasServicio;

	@ModelAttribute
	public void addAttributes(Model model, Principal principal) {
		if (principal != null) {
			Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());

		}
	}
	
	
	@PostMapping(value = "/catalogos/ajaxPeriodo")
	public String postAjaxPeriodo(@RequestParam String nombre, @RequestParam String fecha_inicio,
			@RequestParam String fecha_final) {
		System.out.println("nombre " + nombre);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String response = "";

		try {
			Date dateI = formatter.parse(fecha_inicio);
			Date dateF = formatter.parse(fecha_final);
			response = periodoServicio.insertPeriodo(nombre, dateI, dateF);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxPeriodoModificar")
	public String postAjaxPeriodoModificar(@RequestParam String nombre, @RequestParam String fecha_inicio,
			@RequestParam String fecha_final, @RequestParam int estatus,
			@RequestParam int idPeriodo) {
		System.out.println("nombre " + nombre);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String response = "";

		try {
			Date dateI = formatter.parse(fecha_inicio);
			Date dateF = formatter.parse(fecha_final);
			response = periodoServicio.periodoActualizaDatos(idPeriodo, nombre, dateI, dateF, estatus);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return response;

	}
	
	@PostMapping(value = "/catalogos/periodosActivoDeportiva")
	public List<Periodo> periodosActivoDeportiva(@RequestParam int idTipoBeca) {

		return periodoServicio.periodoActivoDeportiva(idTipoBeca);

	}
	
	

	@PostMapping(value = "/catalogos/ajaxtBeca")
	public String postAjaxtBeca(@RequestParam String nombre) {

		String response = tipoBecaServicio.insertTipoBeca(nombre);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxtBecaModificar")
	public String postAjaxtBecaModificiar(@RequestParam String nombre, @RequestParam int idTipoBeca) {

		String response = tipoBecaServicio.actualizarTipoBeca(idTipoBeca, nombre);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxRegionesPeriodo")
	public String postAjaxtRegionesPeriodo(@RequestParam int idPeriodo) {
		String response = "";
		String estatus = "Activo";
		String claseTD = "";
		int a = 1;
		if (idPeriodo != 0) {

			response = "<table class=\"table tabla\" th:fragment=\"tablaRegionesPeriodo\" id=\"tablaRegionesPeriodo\"  >\r\n"
					+ "				<tr >" + "<tr>\r\n" + "					<th>#</th>\r\n"
					+ "					<th>Nombre</th>\r\n" + "					<th>Abreviatura</th>\r\n"
				//	+ "					<th>Periodo</th>	\r\n" + "					
					+"<th>Estatus</th>\r\n"
					+ "				</tr>";

			List<Regiones> regionesPeriodo = new ArrayList<Regiones>(
					regionesServicio.regresaRegionesPeriodo(idPeriodo));

			for (int i = 0; i < regionesPeriodo.size(); i++) {
				if (regionesPeriodo.get(i).getEstatus() != 1) {
					estatus = "Inactivo";
					claseTD = "inactivo";
				}else {
					estatus = "Activo";
					claseTD = "";
				}

				response += "<tr class=\"tdSencillo\" title='Click para editar estatus de región' style='cursor: pointer;' onclick=\"modificarRegionPeriodoModal("
						+ regionesPeriodo.get(i).getIdRegion() + ",'" + regionesPeriodo.get(i).getNombre() + "'," + idPeriodo + ","
						+ regionesPeriodo.get(i).getEstatus() + ");\">" + "<td>" + a + "</td>" + "<td>"
						+ regionesPeriodo.get(i).getNombre() + "</td>" + "<td>"
						+ regionesPeriodo.get(i).getAbreviatura() + "</td>"
						//+"<td>"	+ regionesPeriodo.get(i).getPeriodo() + "</td>"  
						+"<td class=" + claseTD + ">" + estatus
						+ "</td>" + "</tr>";
				a = a + 1;

			}
			response += "<tr style='cursor:pointer;' onclick='nuevaRegionPeriodoModal(" + idPeriodo + ");'>"
					+ "					<td colspan=6><img  alt=\"Agregar\" src=\"/imagenes/mas.png\"> Agregar región a periodo</td>"
					+ "				</tr>";

			List<Regiones> regionesPeriodoDependencia = new ArrayList<Regiones>(
					regionesServicio.regresaRegionesPeriodoActivas(idPeriodo));
			response += "</table>" + "<br/><br/>\r\n" + "				<table class=\"table tabla\"  >\r\n"
					+ "					<tr>\r\n" + "						<th>Dependencia de región</th>\r\n"
					+ "					</tr></table>\r\n";

			response += "<select class=\"form-control selectText\" required onchange=\"asignaDependencia(this.value);\" id=\"regionPadre\"> "
					+ "<option value='' disabled selected>Selecciona una región padre</option>  ";
			for (int b = 0; b < regionesPeriodoDependencia.size(); b++) {
				response += "<option value=\"" + regionesPeriodoDependencia.get(b).getIdRegion() + "\">"
						+ regionesPeriodoDependencia.get(b).getNombre() + "</option>";
			}
			response += "</select>" + "<div id='tablaDependencia'></div>";

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxRegion")
	public String postAjaxRegion(@RequestParam String nombre, @RequestParam String abreviatura) {

		String response = regionesServicio.insertRegion(nombre, abreviatura);

		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxRegionPeriodoN")
	public String postAjaxRegion(@RequestParam int idRegion, @RequestParam int idPeriodo) {

		String response = regionesServicio.insertRegionPeriodoN(idRegion, idPeriodo);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxRegionModificar")
	public String postAjaxRegionModificar(@RequestParam String nombre, @RequestParam String abreviatura,
			@RequestParam int idRegion) {
		System.out.println("nombre " + nombre);

		String response = regionesServicio.regionesActualizaDatos(idRegion, nombre, abreviatura);

		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxRegionPeriodoModificar")
	public String ajaxRegionPeriodoModificar(@RequestParam int estatus, @RequestParam int idPeriodo,
			@RequestParam int idRegion) {
		
		String response = regionesServicio.regionesActualizaDatosTR(estatus, idPeriodo, idRegion);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxRegionesDependiente")
	public String postAjaxtRegionesDependientes(@RequestParam int idPeriodo, @RequestParam int idRegion) {

		String estilo = "tdSencillo";

		String response = "<br/><br/>\r\n"
				+ "				<table class=\"table tabla\" th:fragment=\"tablaRegionesDependencia\" id=\"tablaRegionesDependencia\">\r\n";
		response += "<tr><td class='etiquetaSpan'>Regiones dependientes :<br/></td></tr>";

		List<Regiones> regionesPeriodoDependenciaNoId = new ArrayList<Regiones>(
				regionesServicio.regresaRegionesPeriodoActivasNoId(idPeriodo, idRegion));

		for (int b = 0; b < regionesPeriodoDependenciaNoId.size(); b++) {
			RelacionRegion relacionHijo = relacionRegionServicio.verificarRelacionRegion(idRegion,
					regionesPeriodoDependenciaNoId.get(b).getIdRegion(), idPeriodo);
			if (relacionHijo != null) {
				if (relacionHijo.getEstatus() == 1) {
					estilo = "tdSencilloPermiso";
				} else {
					estilo = "tdSencillo";
				}
			} else {
				estilo = "tdSencillo";
			}
			response += "<tr class=\"" + estilo + "\" onclick=\"cambiaRelacionRegion("
					+ regionesPeriodoDependenciaNoId.get(b).getIdRegion() + ")\"><td id=\""
					+ regionesPeriodoDependenciaNoId.get(b).getIdRegion() + "\">"
					+ regionesPeriodoDependenciaNoId.get(b).getNombre() + "</td></tr>";

		}

		response += "</table>";

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxRegionesDependienteInsert")
	public String postAjaxtRegionesDependientesInserta(@RequestParam int idRegionPadre,
			@RequestParam int idRegionHijo, @RequestParam int idPeriodo) {
		RelacionRegion relacionHijo = relacionRegionServicio.verificarRelacionRegion(idRegionPadre, idRegionHijo, idPeriodo);
		if (relacionHijo != null) {
			if (relacionHijo.getEstatus() == 1) {
				return relacionRegionServicio.actualizarRelacionRegion(relacionHijo.getIdRelacion(), 0);
			} else {
				return relacionRegionServicio.actualizarRelacionRegion(relacionHijo.getIdRelacion(), 1);
			}
		} else {
			return relacionRegionServicio.insertRelacionRegion(idRegionPadre, idRegionHijo, idPeriodo);
		}

	}

	@PostMapping(value = "/catalogos/ajaxCampanaPeriodo")
	public String postAjaxtCampanaPeriodo(Model model, @RequestParam int idPeriodo) {
		String response = "";

		if (idPeriodo != 0) {

			List<Regiones> regionesPeriodo = new ArrayList<Regiones>(
					regionesServicio.regresaRegionesPeriodo(idPeriodo));
			response += "<select class=\"form-control selectText\" required onchange=\"verCampanas(this.value);\" id=\"idRegion\"> "
					+ "<option value='' disabled selected>Selecciona una región</option>  ";
			for (int b = 0; b < regionesPeriodo.size(); b++) {
				response += "<option value=\"" + regionesPeriodo.get(b).getIdRegion() + "\">"
						+ regionesPeriodo.get(b).getNombre() + "</option>";
			}
			response += "</select>" + "<br/><br/>" + "<div id='tablaCampanas'></div>";

		} else {
			response = "Nada";
		}

		model.addAttribute("regionesCampana", regionesServicio.regresaRegionesPeriodo(idPeriodo));

		return response;
	}

	@PostMapping(value = "/catalogos/ajaxCampanaRegion")
	public String postAjaxtCampanaRegion(@RequestParam int idRegion) {
		String response = "";

		String claseTD = "";
		List<Campana> campanas = new ArrayList<Campana>(campanaServicio.todosCampanaRegion(idRegion));

		response += "<table class=\"table tabla\" >" + "<tr>" + "<th>#</th>" + "<th>Nombre</th>" + "<th>Region</th>"
				+ "<th>Estatus</th>" + "</tr>";
		;
		int a = 1;

		for (int i = 0; i < campanas.size(); i++) {
			String estatus = "Activo";
			if (campanas.get(i).getEstatus() != 1) {
				estatus = "Inactivo";
				claseTD = "inactivo";
			} else {
				claseTD = "";
			}
			response += "<tr class=\"tdSencillo\" title='Click para editar' onclick=\"modificarCampanaModal('"
					+ campanas.get(i).getNombre() + "'," + campanas.get(i).getIdRegion() + ","
					+ campanas.get(i).getEstatus() + "," + campanas.get(i).getIdCampana() + ");\">" + "<td>" + a
					+ "</td>" + "<td>" + campanas.get(i).getNombre() + "</td>" + "<td id=>"
					+ campanas.get(i).getRegion() + "</td>" + "<td class=\"" + claseTD + "\">" + estatus + "</td>"
					+ "</tr>";
			a++;
		}
		response += "<tr style='cursor:pointer;' onclick=\"nuevaCampanaModal();\">"
				+ "<td colspan=4><img  alt='Agregar' src=\"/imagenes/mas.png\"> Agregar campaña</td></tr></table>";

		return response;
	}

	@PostMapping(value = "/catalogos/ajaxCampana")
	public String postAjaxtCampanaRegion(@RequestParam String nombre, @RequestParam int idRegion) {

		return campanaServicio.insertCampana(nombre, idRegion);
	}

	@PostMapping(value = "/catalogos/ajaxCampanaModificar")
	public String postAjaxCampanaModificar(@RequestParam String nombre, @RequestParam int idRegion,
			@RequestParam int estatus, @RequestParam int idCampana) {

		String response = campanaServicio.actualizarCampana(idCampana, nombre, estatus, idRegion);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxCampanaRegionM")
	public String postAjaxtCampanaRegionM(@RequestParam int idPeriodo, @RequestParam int idRegion) {
		String response = "";

		if (idPeriodo != 0) {

			List<Regiones> regionesPeriodo = new ArrayList<Regiones>(
					regionesServicio.regresaRegionesPeriodo(idPeriodo));
			response += "<select class=\"form-control selectText\" required  id=\"idRegionM\"> ";
			String select = "selected";
			for (int b = 0; b < regionesPeriodo.size(); b++) {
				if (regionesPeriodo.get(b).getIdRegion() == idRegion) {
					select = "selected";
				} else {
					select = "";
				}

				response += "<option " + select + " value=\"" + regionesPeriodo.get(b).getIdRegion() + "\">"
						+ regionesPeriodo.get(b).getNombre() + "</option>";
			}
			response += "</select>" + "<br/><br/>" + "<div id='tablaCampanas'></div>";

		} else {
			response = "Nada";
		}

		return response;
	}

	@PostMapping(value = "/catalogos/ajaxtDonativo")
	public String postAjaxtDonativo(@RequestParam String nombre) {

		String response = tipoDonativoServicio.insertTipoDonativo(nombre);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxtDonativoModificar")
	public String postAjaxtDonativoModificiar(@RequestParam String nombre, @RequestParam int idTipoDonativo) {

		String response = tipoDonativoServicio.actualizarTipoDonativo(idTipoDonativo, nombre);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxmCobro")
	public String postAjaxmCobro(@RequestParam String nombre, @RequestParam String numCuenta,
			@RequestParam String sucursal, @RequestParam String clabe) {

		String response = cuentasBancariasServicio.insertCuentaBancaria(nombre, numCuenta, sucursal, clabe);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxmCobroModificar")
	public String postAjaxmCobroModificiar(@RequestParam String nombre, @RequestParam String numCuenta,
			@RequestParam String sucursal, @RequestParam String clabe, @RequestParam int idCuentaBancaria) {

		String response = cuentasBancariasServicio.actualizarCuentasBancarias(idCuentaBancaria, nombre, numCuenta,
				sucursal, clabe);

		return response;

	}

	@PostMapping(value = "/catalogos/ajaxTiempoSelect")
	public String postAjaxtTiempoPeriodoT() {
		String response = "";
	

			List<Regiones> regionesPeriodo = new ArrayList<Regiones>(
					regionesServicio.todosRegionesPrincipales());

			response +=	""
					+ "<select class=\"form-control selectText\" required onchange=\"verTiempoPromerdio();\" id=\"idRegionTiempo\"> "
					+ "<option value='0' selected>Selecciona una región</option>  ";
			for (int b = 0; b < regionesPeriodo.size(); b++) {
				response += "<option value=\"" + regionesPeriodo.get(b).getIdRegion() + "\">"
						+ regionesPeriodo.get(b).getNombre() + "</option>";
			}
			response += "</select><br/><br/>"
					+ ""
					+ ""
					+ "<div id=\"selction-ajax\"></div>";
			
			

	

		return response;

	}
	
	
	@PostMapping(value = "/catalogos/ajaxTiempoPeriodoTablaP")
	public String postAjaxTiempoPeriodoTabla() {
		String response = "";
				
			/**************************************************************************************************************************/
			
			
			List<TiempoPromedio> tiempoPromedioP = new ArrayList<TiempoPromedio>(
					tiempoPromedioServicio.todosTiemposPromedioPeriodo());

			response += "<br/><br/>"
					+ "<table class=\"table tabla\"  id=\"tableTiempoPeriodo\">"
					+ "<tr><th>#</th>"
					+ "<th>Carrera</th>"
					+ "<th>Nivel</th>"
					+ "<th>Modalidad</th>"
					+ "<th>Plan</th>"
					+ "<th>Periodo promedio</th>"
					+ "<th>Facultad</th>"
					+ "<th>Area</th>"
					+ "<th>Región</th>"
					+ "<th>Estatus</th></tr>";
			int a = 1;
			for (int b = 0; b < tiempoPromedioP.size(); b++) {
				response += "<tr class=\"tdSencillo\" id="+tiempoPromedioP.get(b).getIdCarrera()+" title='Click para editar' onclick='modificarTiempoPromedio(\""+tiempoPromedioP.get(b).getCarrera()+"\",\""+tiempoPromedioP.get(b).getNivel()+"\",\""
						+ tiempoPromedioP.get(b).getModalidad()+"\","+tiempoPromedioP.get(b).getPlan()+","+tiempoPromedioP.get(b).getPeriodoPromedio()+","+tiempoPromedioP.get(b).getIdFacultad()+","+tiempoPromedioP.get(b).getIdArea()+","
								+ tiempoPromedioP.get(b).getIdRegion()+","+tiempoPromedioP.get(b).getIdCarrera()+","+tiempoPromedioP.get(b).getEstatus()+")'>"
						+ "<td>"+a+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getCarrera()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getNivel()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getModalidad()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getPlan()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getPeriodoPromedio()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getFacultad()+"</td>"
					    + "<td>"+tiempoPromedioP.get(b).getArea()+"</td>"
					    + "<td>"+tiempoPromedioP.get(b).getRegion()+"</td>"
						+ "<td>"+tiempoPromedioP.get(b).getEstatusDefinicion()+"</td>"
								+ "</tr>";
				a++;
			}
			response += "<tr style='cursor:pointer;' onclick=\"nuevoTiempoPeriodoModal();\">"
					+ "<td colspan=10><img  alt='Agregar' src=\"/imagenes/mas.png\"> Agregar tiempo promedio</td></tr></table>";
			
			
			/**************************************************************************************************************************/
			

	

		return response;

	}
	
	
	
	@PostMapping(value = "/catalogos/ajaxTiempoRegionSelect")
	public String postAjaxtTiempoRegionT(@RequestParam int idRegion, @RequestParam String buscarInput, @RequestParam int carrera, @RequestParam int facultad, @RequestParam int area) {
		String response = "";


			List<TiempoPromedio> tiempoPromedioR = new ArrayList<TiempoPromedio>(
					tiempoPromedioServicio.todosTiemposPromedioRegionInput( idRegion, buscarInput, carrera, facultad, area));

			response += "<br/><br/>"
					+ "<table class=\"table tabla\"  id=\"tableTiempoPeriodo\">"
					+ "<tr><th>#</th>"
					+ "<th>Carrera</th>"
					+ "<th>Nivel</th>"
					+ "<th>Modalidad</th>"
					+ "<th>Plan</th>"
					+ "<th>Periodo promedio</th>"
					+ "<th>Facultad</th>"
					+ "<th>Area</th>"
					+ "<th>Región</th>"
					+ "<th>Estatus</th></tr>";
			int a = 1;
			for (int b = 0; b < tiempoPromedioR.size(); b++) {
				response += "<tr class=\"tdSencillo\" id="+tiempoPromedioR.get(b).getIdCarrera()+" title='Click para editar' onclick='modificarTiempoPromedio(\""+tiempoPromedioR.get(b).getCarrera()+"\",\""+tiempoPromedioR.get(b).getNivel()+"\",\""
						+ tiempoPromedioR.get(b).getModalidad()+"\","+tiempoPromedioR.get(b).getPlan()+","+tiempoPromedioR.get(b).getPeriodoPromedio()+","+tiempoPromedioR.get(b).getIdFacultad()+","+tiempoPromedioR.get(b).getIdArea()+","
								+ tiempoPromedioR.get(b).getIdRegion()+","+tiempoPromedioR.get(b).getIdCarrera()+","+tiempoPromedioR.get(b).getEstatus()+")'>"
						+ "<td>"+a+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getCarrera()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getNivel()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getModalidad()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getPlan()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getPeriodoPromedio()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getFacultad()+"</td>"
					    + "<td>"+tiempoPromedioR.get(b).getArea()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getRegion()+"</td>"
						+ "<td>"+tiempoPromedioR.get(b).getEstatusDefinicion()+"</td></tr>";
				a++;
			}
			response += "<tr style='cursor:pointer;' onclick=\"nuevoTiempoPeriodoModal();\">"
					+ "<td colspan=10><img  alt='Agregar' src=\"/imagenes/mas.png\"> Agregar tiempo promedio</td></tr></table>";

	
		
		
		//System.out.println(response);

		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxAreasRegiones")
	public String postAjaxAreasRegiones(@RequestParam int idRegion) {
		String response = "";
		if (idRegion != 0) {

			List<Areas> areasRegion = new ArrayList<Areas>(
					tiempoPromedioServicio.todosAreasRegion(idRegion));
			String seleccionado = "";			

			
			response +=	""
					+ "<select class=\"form-control selectText\" onchange=\"obtenerFacultadesArea(this.value);\" required  id='idArea'> "
					+ "<option value='' "+seleccionado+">Selecciona area...</option>  ";
			for (int b = 0; b < areasRegion.size(); b++) {


				
				response += "<option "+seleccionado+" value=\"" + areasRegion.get(b).getIdArea()+ "\">"
						+ areasRegion.get(b).getNombre() + "</option>";
				
				
			}
			response += "</select>";
			
			

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	
	@PostMapping(value = "/catalogos/ajaxTiempoPeriodoArea")
	public String postAjaxtTiempoPeriodoArea(@RequestParam int idArea) {
		String response = "";
		if (idArea != 0) {

			List<Facultades> facultades = new ArrayList<Facultades>(
					tiempoPromedioServicio.todosFacultades(idArea,0));

			response +=	"<select class=\"form-control selectText\" required  id=\"idFac\"> "
					+ "<option value='' disabled selected>Selecciona una facultad</option>  ";
			for (int b = 0; b < facultades.size(); b++) {
				response += "<option value=\"" + facultades.get(b).getIdFacultad() + "\">"
						+ facultades.get(b).getNombre() + "</option>";
			}
			response += "</select>";

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxTiempoPeriodoAreaM")
	public String postAjaxtTiempoPeriodoAreaM(@RequestParam int idArea, @RequestParam int idFacultad) {
		String response = "";
		String seleccionado = "";
		
		if (idArea != 0) {

			List<Facultades> facultades = new ArrayList<Facultades>(
					tiempoPromedioServicio.todosFacultades(idArea,0));

			response +=	"<select class=\"form-control selectText\" required  id=\"idFacM\"> "
					+ "<option value='' disabled >Selecciona una facultad</option>  ";
			for (int b = 0; b < facultades.size(); b++) {
				if(facultades.get(b).getIdFacultad() == idFacultad) {
					seleccionado = "selected";
				}else {
					seleccionado = "";
				}
				
				response += "<option value=\"" + facultades.get(b).getIdFacultad() + "\" "+seleccionado+">"+ facultades.get(b).getNombre() + "</option>";
			}
			response += "</select>";

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	
	
	@PostMapping(value = "/catalogos/ajaxtPromedio")
	public String postAjaxtPromedio(@RequestParam String carrera, @RequestParam String nivel, @RequestParam String modalidad,  @RequestParam int plan,  @RequestParam int pPromedio, @RequestParam int idRegion, @RequestParam int idFac , @RequestParam int idArea ) {		

		String response = tiempoPromedioServicio.insertCarrera(idFac,idArea, idRegion, carrera, nivel, modalidad, plan, pPromedio);
		
		return response;

	}
	
	
	@PostMapping(value = "/catalogos/ajaxtPromedioModificar")
	public String postAjaxtPromedioModificar( @RequestParam int idFacultad , @RequestParam int idRegion , @RequestParam String carrera, @RequestParam String nivel, @RequestParam String modalidad,  @RequestParam int plan,  @RequestParam int pPromedio, @RequestParam int estatus,@RequestParam int idArea, @RequestParam int idCarrera) {		

		String response = tiempoPromedioServicio.actualizarCarrera(idFacultad, idArea, idRegion, carrera, nivel, modalidad, plan, pPromedio, estatus, idCarrera);
		
		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxAreaTPro")
	public String postajaxAreaTPro(@RequestParam String tipo, @RequestParam String nombreArea, @RequestParam int idAreaModifica) {		
		
		String response = "";
		if(tipo.equals("N")) {
		response = tiempoPromedioServicio.insertArea(nombreArea);
		}else if (tipo.equals("M")) {
			response = tiempoPromedioServicio.actualizarArea(idAreaModifica, nombreArea);
		}
		
		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxFacTPro")
	public String postajaxFacTPro(@RequestParam String tipo, @RequestParam String nombreFac, @RequestParam int idFacModifica) {		
		
		String response = "";
		if(tipo.equals("N")) {
		response = tiempoPromedioServicio.insertFacultad(nombreFac);
		}else if (tipo.equals("M")) {
			response = tiempoPromedioServicio.actualizarFacultad(idFacModifica, nombreFac);
		}
		
		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxQuincenas")
	public String postAjaxtPromedio(@RequestParam int numQuincena, @RequestParam String nombre, @RequestParam int anio) {		

		String response = quincenasServicio.insertQuincena(numQuincena, nombre, anio);
		
		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxQuincenasModificar")
	public String postAjaxQuincenasModificar(@RequestParam int numQuincena, @RequestParam String nombre, @RequestParam int anio, @RequestParam int idQuincena) {		

		String response = quincenasServicio.actualizarQuincenas(idQuincena, numQuincena, nombre, anio);
		
		return response;

	}
	
	@RequestMapping(value = "/catalogos/autocompleteRegion", method = RequestMethod.GET)
	
	public  @ResponseBody Map<String,Regiones> postAjaxAutocompleteRegion(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Regiones> region = regionesServicio.autocompletarRegiones(idPeriodo, term); 
		
		Map<String,Regiones> response = new HashMap<String,Regiones>();
		
		for(int b = 0; b < region.size() ;b++) {
			response.put(region.get(b).getNombre(), region.get(b));
		}
		
		return response;

	}
	
@RequestMapping(value = "/catalogos/autocompleteArea", method = RequestMethod.GET)
	
	public  @ResponseBody Map<String,Areas> postAjaxAutocompleteArea(@RequestParam String term, @RequestParam int idRegion) {		

		List<Areas> area = tiempoPromedioServicio.autocompletarAreas(idRegion, term); 
		
		Map<String,Areas> response = new TreeMap<String,Areas>();
		
		for(int b = 0; b < area.size() ;b++) {
			response.put(area.get(b).getNombre(), area.get(b));
		}
		
		return response;

	}

@RequestMapping(value = "/catalogos/autocompleteFacultad", method = RequestMethod.GET)

public  @ResponseBody Map<String,Facultades> postAjaxAutocompleteFacultad(@RequestParam String term, @RequestParam int idRegion, @RequestParam int idArea) {		

	List<Facultades> fac = tiempoPromedioServicio.autocompleteFacultad(idRegion, idArea, term); 
	
	Map<String,Facultades> response = new TreeMap<String,Facultades>();
	
	for(int b = 0; b < fac.size() ;b++) {
		response.put(fac.get(b).getNombre(), fac.get(b));
	}
	
	return response;

}

@RequestMapping(value = "/catalogos/autocompleteNivel", method = RequestMethod.GET)

public  @ResponseBody Map<String,TiempoPromedio> postAjaxAutocompleteNivel(@RequestParam String term, @RequestParam int idRegion, @RequestParam int idArea, @RequestParam int idFac) {		

	List<TiempoPromedio> nivel = tiempoPromedioServicio.autocompleteNivel(idRegion, idArea, idFac, term); 
	
	Map<String,TiempoPromedio> response = new TreeMap<String,TiempoPromedio>();
	
	for(int b = 0; b < nivel.size() ;b++) {
		response.put(nivel.get(b).getNivel(), nivel.get(b));
	}
	
	return response;

}

@RequestMapping(value = "/catalogos/autocompleteModalidad", method = RequestMethod.GET)

public  @ResponseBody Map<String,TiempoPromedio> postAjaxAutocompleteModalidad(@RequestParam String term, @RequestParam int idRegion, @RequestParam int idArea, @RequestParam int idFac, @RequestParam String nivel) {		

	List<TiempoPromedio> modalidad = tiempoPromedioServicio.autocompleteModalidad(idRegion, idArea, idFac,nivel, term); 
	
	Map<String,TiempoPromedio> response = new TreeMap<String,TiempoPromedio>();
	
	for(int b = 0; b < modalidad.size() ;b++) {
		response.put(modalidad.get(b).getModalidad(), modalidad.get(b));
	}
	
	return response;

}

@RequestMapping(value = "/catalogos/autocompleteCarrera", method = RequestMethod.GET)

public  @ResponseBody Map<String,TiempoPromedio> postAjaxAutocompleteCarrera(@RequestParam String term, @RequestParam int idRegion, @RequestParam int idArea, @RequestParam int idFac, @RequestParam String nivel, @RequestParam String modalidad) {		

	List<TiempoPromedio> carrera = tiempoPromedioServicio.autocompleteCarrera(idRegion, idArea, idFac,nivel, modalidad,term); 
	
	Map<String,TiempoPromedio> response = new TreeMap<String,TiempoPromedio>();
	
	for(int b = 0; b < carrera.size() ;b++) {
		response.put(carrera.get(b).getCarrera(), carrera.get(b));
	}
	
	return response;

}

@RequestMapping(value = "/catalogos/autocompleteQuincena", method = RequestMethod.GET)

public  @ResponseBody Map<String,Quincenas> postAjaxAutocompleteQuincena(@RequestParam String term) {		

	List<Quincenas> quincena = quincenasServicio.autocompleteQuincenas(term); 
	
	Map<String,Quincenas> response = new LinkedHashMap<String,Quincenas>();
	
	for(int b = 0; b < quincena.size() ;b++) {
		response.put(quincena.get(b).getNombre(), quincena.get(b));
	}
	
	return response;

}

@RequestMapping(value = "/catalogos/autocompleteCuentaBancaria", method = RequestMethod.GET)

public  @ResponseBody Map<String,CuentasBancarias> postAjaxAutocompleteCuentaBancaria(@RequestParam String term) {		

	List<CuentasBancarias> quincena = cuentasBancariasServicio.autocompleteCuentasBancarias(term); 
	
	Map<String,CuentasBancarias> response = new LinkedHashMap<String,CuentasBancarias>();
	
	for(int b = 0; b < quincena.size() ;b++) {
		response.put(quincena.get(b).getNombre(), quincena.get(b));
	}
	
	return response;

}

}
