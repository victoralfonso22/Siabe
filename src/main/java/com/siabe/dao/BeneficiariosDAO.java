package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;
import com.siabe.mapa.BeneficiariosMapa;
import com.siabe.mapa.DonativosMapa;
import com.siabe.mapa.UsuarioMapa;

import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class BeneficiariosDAO extends JdbcDaoSupport {

	@Autowired
	public BeneficiariosDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public Beneficiarios beneficiario(int idBeneficiario) {	
		String sql = BeneficiariosMapa.BASE_SQL + " where id = ? ; ";
		Object[] params = new Object[] { idBeneficiario };
		BeneficiariosMapa mapper = new BeneficiariosMapa();
		try {
			Beneficiarios periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Beneficiarios> beneficiariosPeriodo(int idPeriodo) {	
		String sql = BeneficiariosMapa.BASE_SQL + " where id_periodo = ? ; ";
		Object[] params = new Object[] { idPeriodo };
		BeneficiariosMapa mapper = new BeneficiariosMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Beneficiarios> beneficiariosActivosPeriodo(int idPeriodo) {	
		String sql = BeneficiariosMapa.BASE_SQL + " where id_periodo = ? and estatus = 1; ";
		Object[] params = new Object[] { idPeriodo };
		BeneficiariosMapa mapper = new BeneficiariosMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	

	
	public String insertaBeneficiarioBecas(int idPeriodo, int idTipoBeca,String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, String idBenefactor, int idUsuario) {
		
		String sql = BeneficiariosMapa.INSERT_SQL_BECAS + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?)";

		Object[] params = new Object[] { idPeriodo, idTipoBeca, matricula.toUpperCase().trim(), nombre.trim(), apellidoPaterno.trim(), apellidoMaterno.trim() , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 idCarrera,  periodoActual,  promedioGeneral,  edad,  genero,  lenguaIndigena,  discapacidad,  estadoCivil,  lugarNacimiento.trim(),  fechaNacimiento,
				 breveHistoria.trim(),  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps.trim(),  mismoVivFam,  calleEst.trim(),  numEEst,  numIEst,  colEst.trim(),  locEst.trim(),  munEst.trim(),  edoEst.trim(),  cpEst,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  formaPago,  banco,  cuentaDeposito, 
				 tarjetaDeposito,  claveReferenciado,  vigenciaReferenciado,  montoBeca,  finalidadApoyo.trim(),  observaciones.trim(),   idUsuario };
		
		try {
			
			if(getCountIdBeneficiario(matricula.toUpperCase(),idPeriodo,idTipoBeca) > 0) {
				return "Duplicado";
			}else {
			
		this.getJdbcTemplate().update(sql, params);
		
		if(idBenefactor != "") {
			
			if(idBenefactor == "Sin donante") {
				
			}
			String sqlInsertaRelacion = DonativosMapa.INSERT_SQL_TRDONBEN + " ("+idBenefactor+","+getIdBeneficiario(matricula.toUpperCase(),idPeriodo)+")";
			this.getJdbcTemplate().update(sqlInsertaRelacion);
		}
		
			
			
		return "Done";
		}
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}catch(IncorrectResultSizeDataAccessException ex) {
			return "MasFilas";
		}
		catch(DataIntegrityViolationException exx) {
			return "Duplicado";
		}
	}
	
	 
	
	public String insertaBeneficiarioDeportivas(int idPeriodo, int idTipoBeca, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu, String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idUsuario) {
		
		String sql = BeneficiariosMapa.INSERT_SQL_DEPORTIVAS + " (?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
																	

		Object[] params = new Object[] { idPeriodo, idTipoBeca, nombre.trim(), apellidoPaterno.trim(), apellidoMaterno.trim() , estatus,  motivoEstatus.trim(),  tipoBecario,  adscripcion,  idRegion, 
				 escuelaDeportiva,  fechaIngEscDep,  nivelEduCursa,  turno,  tipoInstitucion,  grado,  nombreEdu.trim(), calleEdu.trim(),  numExtEdu.trim(),  numIntEdu.trim(),  colEdu.trim(),  locEdu.trim(), 
				 munEdu.trim(),  edoEdu.trim(),  cpEdu,  telEdu.trim(),  promedioGeneral,  edad,  genero,  lugarNacimiento.trim(),  fechaNacimiento,
				 breveHistoria,  integrantesFamiliares,  ingresosFamiliares,  calleVivFam.trim(),  numEVivFam,  numIVivFam,  colVivFam,  locVivFam.trim(),  munVivFam.trim(),  edoVivFam.trim(),
				 cpVivFam,  enlaceMaps.trim(),   nombreTutor.trim(),  parentescoTutor.trim(),  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  ocupacionTutor.trim(),  hermanosInscritos,  escuelaHermanosInscritos.trim(), 
				 montoBeca,  observaciones.trim(),  idUsuario};
		
		try {
			
		this.getJdbcTemplate().update(sql, params);
			
		return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}catch(IncorrectResultSizeDataAccessException ex) {
			return "MasFilas";
		}catch(DataIntegrityViolationException exx) {
			return "Duplicado";
		}
	}

	public List<Beneficiarios> obtenerBeneficiarios() {

		String sql = BeneficiariosMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public String actualizaDatosBecas(String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, int idBeneficiario, int idUsuario) {

		String sql = BeneficiariosMapa.UPDATE_SQL + " matricula=?, nombre=?, apellido_paterno=?,apellido_materno=?,estatus=?,motivo_estatus=?,tipo_becario=?,adscripcion=?, id_region=?, id_carrera=?, periodo_actual=?,promedio_general=?," + 
				"edad=?,genero=?,lengua_indigena=?,discapacidad=?,estado_civil=?,lugar_nacimiento=?,fecha_nacimiento=?,breve_historia=?,integrantes_familiares=?,ingresos_familiares=?,calle_viv_fam=?,numE_viv_fam=?,numI_viv_fam=?," + 
				"col_viv_fam=?,loc_viv_fam=?,mun_viv_fam=?,edo_viv_fam=?,cp_viv_fam=?,enlace_maps=?,mismo_vivienda_fam=?,calle_est=?,numE_est=?,numI_est=?,col_est=?,loc_est=?,mun_est=?,edo_est=?,cp_est=?,celular=?,tel_domicilio=?," + 
				"tipo_tel_ref=?,num_tel_ref=?,parentesco_ref=?,observaciones_ref=?,email=?,facebook=?,facebook2=?,facebook3=?,forma_pago=?,banco=?,cuenta_deposito=?,tarjeta_deposito=?,clave_referenciado=?,vigencia_referenciado=?," + 
				"monto_beca=?,finalidad_apoyo=?,observaciones=?  where id =?;";
		

		Object[] params = new Object[] { matricula.toUpperCase().trim(), nombre.trim(), apellidoPaterno.trim(), apellidoMaterno.trim() , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 idCarrera,  periodoActual,  promedioGeneral,  edad,  genero,  lenguaIndigena,  discapacidad,  estadoCivil,  lugarNacimiento.trim(),  fechaNacimiento,
				 breveHistoria.trim(),  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps.trim(),  mismoVivFam,  calleEst.trim(),  numEEst,  numIEst,  colEst.trim(),  locEst.trim(),  munEst.trim(),  edoEst.trim(),  cpEst,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  formaPago,  banco,  cuentaDeposito, 
				 tarjetaDeposito,  claveReferenciado,  vigenciaReferenciado,  montoBeca,  finalidadApoyo.trim(),  observaciones.trim(),  idBeneficiario};

		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('beneficiarios',"+idBeneficiario+","+idUsuario+")";
		
		try {
			this.getJdbcTemplate().update(sql, params);
			this.getJdbcTemplate().update(sqlCambio);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}catch(IncorrectResultSizeDataAccessException ex) {
			return "MasFilas";
		}catch(DataIntegrityViolationException exx) {
			return "Duplicado";
		}

	}
	
	public String actualizaDatosDeportivas(String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu,String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idBeneficiario, int idUsuario) {

		String sql = BeneficiariosMapa.UPDATE_SQL + " nombre=?, apellido_paterno=?,apellido_materno=?,estatus=?,motivo_estatus=?,tipo_becario=?,adscripcion=?, id_region=?, escuela_deportiva=?,fecha_ing_escuela_dep=?,nivel_edu_cursa=?,"+ 
				"turno=?,tipo_institucion=?,grado=?,nombre_edu=?,calle_edu=?,numE_edu=?,numIedu=?,col_edu=?,loc_edu=?,mun_edu=?,edo_edu=?,cp_edu=?,tel_edu=?,promedio_general=?,edad=?,genero=?,lugar_nacimiento=?,fecha_nacimiento=?,breve_historia=?," + 
				"integrantes_familiares=?,ingresos_familiares=?,calle_viv_fam=?,numE_viv_fam=?,numI_viv_fam=?,col_viv_fam=?,loc_viv_fam=?,mun_viv_fam=?,edo_viv_fam=?,cp_viv_fam=?,enlace_maps=?,nombre_tutor=?,parentesco_tutor=?," + 
				"celular=?,tel_domicilio=?,tipo_tel_ref=?,num_tel_ref=?,parentesco_ref=?,observaciones_ref=?,email=?,facebook=?,facebook2=?,facebook3=?,ocupacion_tutor=?,hermanos_inscritos=?,escuela_hermano=?,monto_beca=?," + 
				"observaciones=? where id =?;";
		

		Object[] params = new Object[] { nombre.trim(), apellidoPaterno.trim(), apellidoMaterno.trim() , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 escuelaDeportiva,  fechaIngEscDep,  nivelEduCursa,  turno,  tipoInstitucion,  grado,  nombreEdu.trim() ,calleEdu.trim(),  numExtEdu,  numIntEdu,  colEdu.trim(),  locEdu.trim(), 
				 munEdu.trim(),  edoEdu.trim(),  cpEdu,  telEdu,  promedioGeneral,  edad,  genero,  lugarNacimiento.trim(),  fechaNacimiento,
				 breveHistoria.trim(),  integrantesFamiliares,  ingresosFamiliares,  calleVivFam.trim(),  numEVivFam,  numIVivFam.trim(),  colVivFam.trim(),  locVivFam.trim(),  munVivFam.trim(),  edoVivFam.trim(),
				 cpVivFam,  enlaceMaps,   nombreTutor,  parentescoTutor,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  ocupacionTutor,  hermanosInscritos,  escuelaHermanosInscritos, 
				 montoBeca,  observaciones.trim(),  idBeneficiario};

		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('beneficiarios',"+idBeneficiario+","+idUsuario+")";
		
		try {
			this.getJdbcTemplate().update(sql, params);
			this.getJdbcTemplate().update(sqlCambio);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}catch(IncorrectResultSizeDataAccessException ex) {
			return "MasFilas";
		}catch(DataIntegrityViolationException exx) {
			return "Duplicado";
		}

	}
	
	public List<Beneficiarios> autocompletarBeneficiariosPeriodo(int idPeriodo, String termino) {
		
	
		String	sql = BeneficiariosMapa.BASE_SQL + " where r.nombre like '%"+termino+"%' and r.id_periodo = "+idPeriodo+" and r.estatus= 1; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Beneficiarios> autocompletarBeneficiarios(String termino, int idPeriodo, int idTipoBeca) {
		
		String	sql = "";
		
		if(idPeriodo == 0 && idTipoBeca == 0) {
		sql = BeneficiariosMapa.BASE_SQL + " vn\r\n" + 
				"join (select vn2.id, b.nombre_completo_bene, b.matricula, b.id_periodo, min(vn2.id_tipo_beca) id_tipo_beca from\r\n" + 
				"(SELECT nombre_completo_bene, matricula, max(id_periodo) id_periodo  from view_beneficiarios WHERE (nombre_completo_bene LIKE '%"+termino+"%') OR (matricula LIKE '%"+termino+"%')  group by nombre_completo_bene limit 25)b\r\n" + 
				"join view_beneficiarios vn2 on vn2.id_periodo = b.id_periodo and vn2.nombre_completo_bene = b.nombre_completo_bene group by b.nombre_completo_bene )a\r\n" + 
				"on a.nombre_completo_bene = vn.nombre_completo_bene  and a.id_periodo = vn.id_periodo and a.id_tipo_beca = vn.id_tipo_beca";
				
		}else if(idPeriodo != 0 && idTipoBeca == 0){
			sql= BeneficiariosMapa.BASE_SQL +" where nombre_completo_bene like '%"+termino+"%' and id_periodo = "+idPeriodo+" ;";
		}else {
			sql= BeneficiariosMapa.BASE_SQL +" where nombre_completo_bene = '"+termino+"' and id_periodo = "+idPeriodo+" and id_tipo_beca = "+idTipoBeca+";";
		}

		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Beneficiarios> autocompletarBeneficiarios(String termino, int idPeriodo) {
		
		
		String	sql = BeneficiariosMapa.BASE_SQL + " WHERE (nombre_completo_bene LIKE '%"+termino+"%') OR (matricula LIKE  '%"+termino+"%') and id_periodo = "+idPeriodo+" order by nombre_completo_bene ; ";
		
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
public List<Beneficiarios> autocompletarBeneficiariosNoDepor(String termino, int idPeriodo) {
		
		
		String	sql = BeneficiariosMapa.BASE_SQL + " WHERE id_periodo = "+idPeriodo+" and id_tipo_beca in(1,2,3) and (nombre_completo_bene LIKE '%"+termino+"%' OR matricula LIKE  '%"+termino+"%') order by nombre_completo_bene ; ";

		
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

public List<Beneficiarios> autocompletarBeneficiariosNoDeporAsig(String termino, int idPeriodo) {
	
	
	String	sql = BeneficiariosMapa.BASE_SQL + " WHERE id_periodo = "+idPeriodo+" and id_tipo_beca in(1,2,3) and (nombre_completo_bene LIKE '%"+termino+"%' OR matricula LIKE  '%"+termino+"%') order by nombre_completo_bene ; ";
	

	
	try {
		return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

	} catch (EmptyResultDataAccessException e) {
		return null;
	}

}
	
	
public List<Beneficiarios> reporteGeneral(int idTipoBeca,int idPeriodo, int idRegion) {
		String idPString = "";
		String idRString = "";
	
		if(idPeriodo!=0) {
			idPString = " and id_periodo = "+idPeriodo;
		}
		
		if(idRegion!=0) {
			idRString = " and id_region = "+idRegion;
		}
		
		String	sql = "select * FROM view_beneficiarios where id_tipo_beca = "+idTipoBeca+idPString+idRString+" ORDER BY nombre_completo_bene,id_periodo,id_region;";
	
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

public int getIdBeneficiario(String matricula, int idPeriodo) {
	
	
	String	sql = BeneficiariosMapa.BASE_SQL_ID + " WHERE matricula = '"+matricula+"' and id_periodo = "+idPeriodo+"; ";

	
	try {
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);

	} catch (EmptyResultDataAccessException e) {
		return 0;
	}

}

public int getCountIdBeneficiario(String matricula, int idPeriodo, int idTipoBeca) {
	
	
	String	sql = BeneficiariosMapa.BASE_SQL_ID_COUNT + " WHERE matricula = '"+matricula+"' and id_periodo = "+idPeriodo+" AND id_tipo_beca = "+idTipoBeca+"; ";

	
	try {
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);

	} catch (EmptyResultDataAccessException e) {
		return 0;
	}

}

public List<Beneficiarios> refrendo(int idPeriodo, int idRegion, int idTipoBeca) {

	String idRString = "", idCString = "";


	if(idRegion!=0) {
		idRString = " and id_region = "+idRegion;
	}
	
	if(idTipoBeca!=0) {
		idCString = " and id_tipo_beca = "+idTipoBeca;
	}
	
	String	sql = "select * FROM view_beneficiarios where id_periodo = "+idPeriodo+idRString+idCString+" and refrendado = 0 ORDER BY region desc, id_tipo_beca asc , nombre ASC";
	System.out.println(sql);
	
	try {
		return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

	} catch (EmptyResultDataAccessException e) {
		return null;
	}

}

public String refrendaBeneficiario(int idBeneficiario,int idPeriodo) {
	
	String sql = " call refrendarBeneficiarios("+idBeneficiario+", "+idPeriodo+");";

	try {
		
	this.getJdbcTemplate().update(sql);
		
	return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}catch(IncorrectResultSizeDataAccessException ex) {
		return "MasFilas";
	}catch(DataIntegrityViolationException exx) {
		System.out.println(exx.getMessage());
		return "Duplicado";
	}
}

}
