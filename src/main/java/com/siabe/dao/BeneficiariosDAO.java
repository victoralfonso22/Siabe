package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Beneficiarios;

import com.siabe.mapa.BeneficiariosMapa;
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

	

	
	public String insertaBeneficiarioBecas(int idPeriodo, String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, String idBenefactor, int idUsuario) {
		
		String sql = BeneficiariosMapa.INSERT_SQL_BECAS + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?)";

		Object[] params = new Object[] { idPeriodo, matricula.toUpperCase(), nombre, apellidoPaterno, apellidoMaterno , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 idCarrera,  periodoActual,  promedioGeneral,  edad,  genero,  lenguaIndigena,  discapacidad,  estadoCivil,  lugarNacimiento,  fechaNacimiento,
				 breveHistoria,  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps,  mismoVivFam,  calleEst,  numEEst,  numIEst,  colEst,  locEst,  munEst,  edoEst,  cpEst,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  formaPago,  banco,  cuentaDeposito, 
				 tarjetaDeposito,  claveReferenciado,  vigenciaReferenciado,  montoBeca,  finalidadApoyo,  observaciones,  idBenefactor,  idUsuario };
		
		try {
			
		this.getJdbcTemplate().update(sql, params);
			
		return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	 
	
	public String insertaBeneficiarioDeportivas(int idPeriodo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu, String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idUsuario) {
		
		String sql = BeneficiariosMapa.INSERT_SQL_DEPORTIVAS + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
																	+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
																	

		Object[] params = new Object[] { idPeriodo, nombre, apellidoPaterno, apellidoMaterno , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 escuelaDeportiva,  fechaIngEscDep,  nivelEduCursa,  turno,  tipoInstitucion,  grado,  nombreEdu, calleEdu,  numExtEdu,  numIntEdu,  colEdu,  locEdu, 
				 munEdu,  edoEdu,  cpEdu,  telEdu,  promedioGeneral,  edad,  genero,  lugarNacimiento,  fechaNacimiento,
				 breveHistoria,  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps,   nombreTutor,  parentescoTutor,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  ocupacionTutor,  hermanosInscritos,  escuelaHermanosInscritos, 
				 montoBeca,  observaciones,  idUsuario};
		
		try {
			
		this.getJdbcTemplate().update(sql, params);
			
		return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
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

	public String actualizaDatosBecas(int idPeriodo, String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, String idBenefactor, int idBeneficiario, int idUsuario) {

		String sql = BeneficiariosMapa.UPDATE_SQL + " id_periodo = ?,matricula=?, nombre=?, apellido_paterno=?,apellido_materno=?,estatus=?,motivo_estatus=?,tipo_becario=?,adscripcion=?, id_region=?, id_carrera=?, periodo_actual=?,promedio_general=?," + 
				"edad=?,genero=?,lengua_indigena=?,discapacidad=?,estado_civil=?,lugar_nacimiento=?,fecha_nacimiento=?,breve_historia=?,integrantes_familiares=?,ingresos_familiares=?,calle_viv_fam=?,numE_viv_fam=?,numI_viv_fam=?," + 
				"col_viv_fam=?,loc_viv_fam=?,mun_viv_fam=?,edo_viv_fam=?,cp_viv_fam=?,enlace_maps=?,mismo_vivienda_fam=?,calle_est=?,numE_est=?,numI_est=?,col_est=?,loc_est=?,mun_est=?,edo_est=?,cp_est=?,celular=?,tel_domicilio=?," + 
				"tipo_tel_ref=?,num_tel_ref=?,parentesco_ref=?,observaciones_ref=?,email=?,facebook=?,facebook2=?,facebook3=?,forma_pago=?,banco=?,cuenta_deposito=?,tarjeta_deposito=?,clave_referenciado=?,vigencia_referenciado=?," + 
				"monto_beca=?,finalidad_apoyo=?,observaciones=?,id_benefactor=?  where id =?;";
		
		//System.out.println(sql);
		Object[] params = new Object[] { idPeriodo, matricula.toUpperCase(), nombre, apellidoPaterno, apellidoMaterno , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 idCarrera,  periodoActual,  promedioGeneral,  edad,  genero,  lenguaIndigena,  discapacidad,  estadoCivil,  lugarNacimiento,  fechaNacimiento,
				 breveHistoria,  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps,  mismoVivFam,  calleEst,  numEEst,  numIEst,  colEst,  locEst,  munEst,  edoEst,  cpEst,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  formaPago,  banco,  cuentaDeposito, 
				 tarjetaDeposito,  claveReferenciado,  vigenciaReferenciado,  montoBeca,  finalidadApoyo,  observaciones,  idBenefactor,  idBeneficiario};
		// System.out.println(sql+ id+ password);
		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('beneficiarios',"+idBeneficiario+","+idUsuario+")";
		
		try {
			this.getJdbcTemplate().update(sql, params);
			this.getJdbcTemplate().update(sqlCambio);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	public String actualizaDatosDeportivas(int idPeriodo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu,String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idBeneficiario, int idUsuario) {

		String sql = BeneficiariosMapa.UPDATE_SQL + " id_periodo=?, nombre=?, apellido_paterno=?,apellido_materno=?,estatus=?,motivo_estatus=?,tipo_becario=?,adscripcion=?, id_region=?, escuela_deportiva=?,fecha_ing_escuela_dep=?,nivel_edu_cursa=?,"+ 
				"turno=?,tipo_institucion=?,grado=?,nombre_edu=?,calle_edu=?,numE_edu=?,numIedu=?,col_edu=?,loc_edu=?,mun_edu=?,edo_edu=?,cp_edu=?,tel_edu=?,promedio_general=?,edad=?,genero=?,lugar_nacimiento=?,fecha_nacimiento=?,breve_historia=?," + 
				"integrantes_familiares=?,ingresos_familiares=?,calle_viv_fam=?,numE_viv_fam=?,numI_viv_fam=?,col_viv_fam=?,loc_viv_fam=?,mun_viv_fam=?,edo_viv_fam=?,cp_viv_fam=?,enlace_maps=?,nombre_tutor=?,parentesco_tutor=?," + 
				"celular=?,tel_domicilio=?,tipo_tel_ref=?,num_tel_ref=?,parentesco_ref=?,observaciones_ref=?,email=?,facebook=?,facebook2=?,facebook3=?,ocupacion_tutor=?,hermanos_inscritos=?,escuela_hermano=?,monto_beca=?," + 
				"observaciones=? where id =?;";
		
		//System.out.println(sql);
		Object[] params = new Object[] { idPeriodo, nombre, apellidoPaterno, apellidoMaterno , estatus,  motivoEstatus,  tipoBecario,  adscripcion,  idRegion, 
				 escuelaDeportiva,  fechaIngEscDep,  nivelEduCursa,  turno,  tipoInstitucion,  grado,  nombreEdu ,calleEdu,  numExtEdu,  numIntEdu,  colEdu,  locEdu, 
				 munEdu,  edoEdu,  cpEdu,  telEdu,  promedioGeneral,  edad,  genero,  lugarNacimiento,  fechaNacimiento,
				 breveHistoria,  integrantesFamiliares,  ingresosFamiliares,  calleVivFam,  numEVivFam,  numIVivFam,  colVivFam,  locVivFam,  munVivFam,  edoVivFam,
				 cpVivFam,  enlaceMaps,   nombreTutor,  parentescoTutor,  celular,  telDomicilio,
				 tipoTelRef,  numTelRef,  parentescoRef,  observacionesRef,  email,  facebook,  facebook2,  facebook3,  ocupacionTutor,  hermanosInscritos,  escuelaHermanosInscritos, 
				 montoBeca,  observaciones,  idBeneficiario};
		// System.out.println(sql+ id+ password);
		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('beneficiarios',"+idBeneficiario+","+idUsuario+")";
		
		try {
			this.getJdbcTemplate().update(sql, params);
			this.getJdbcTemplate().update(sqlCambio);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
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
	
	
	public List<Beneficiarios> autocompletarBeneficiarios(String termino) {
		
		
		String	sql = BeneficiariosMapa.BASE_SQL + " WHERE (nombre_completo_bene LIKE '%"+termino+"%') OR (matricula LIKE  '%"+termino+"%'); ";
	
		
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
		
		String	sql = "select * FROM view_beneficiarios where id_tipo_beca = "+idTipoBeca+idPString+idRString;
		//System.out.println(sql);
		
		try {
			return this.getJdbcTemplate().query(sql, new BeneficiariosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}