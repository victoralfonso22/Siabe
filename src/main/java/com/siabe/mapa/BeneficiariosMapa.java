package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.siabe.modelo.Beneficiarios;
import org.springframework.jdbc.core.RowMapper;

public class BeneficiariosMapa implements RowMapper<Beneficiarios> {

	public static final String BASE_SQL //
			= "SELECT * from view_beneficiarios ";
	
	public static final String INSERT_SQL_BECAS //
	= "INSERT INTO beneficiarios (id_periodo,matricula, nombre, apellido_paterno,apellido_materno,estatus,motivo_estatus,tipo_becario,adscripcion, id_region, id_carrera, periodo_actual,promedio_general,"
			+ "edad,genero,lengua_indigena,discapacidad,estado_civil,lugar_nacimiento,fecha_nacimiento,breve_historia,integrantes_familiares,ingresos_familiares,calle_viv_fam,numE_viv_fam,numI_viv_fam,"
			+ "col_viv_fam,loc_viv_fam,mun_viv_fam,edo_viv_fam,cp_viv_fam,enlace_maps,mismo_vivienda_fam,calle_est,numE_est,numI_est,col_est,loc_est,mun_est,edo_est,cp_est,celular,tel_domicilio,"
			+ "tipo_tel_ref,num_tel_ref,parentesco_ref,observaciones_ref,email,facebook,facebook2,facebook3,forma_pago,banco,cuenta_deposito,tarjeta_deposito,clave_referenciado,vigencia_referenciado,"
			+ "monto_beca,finalidad_apoyo,observaciones,id_benefactor,id_usuario) values ";
	
	public static final String INSERT_SQL_DEPORTIVAS //
	= "INSERT INTO beneficiarios (id_periodo, nombre, apellido_paterno,apellido_materno,estatus,motivo_estatus,tipo_becario,adscripcion, id_region, escuela_deportiva,fecha_ing_escuela_dep,nivel_edu_cursa,"
			+ "turno,tipo_institucion,grado,nombre_edu,calle_edu,numE_edu,numIedu,col_edu,loc_edu,mun_edu,edo_edu,cp_edu,tel_edu,promedio_general,edad,genero,lugar_nacimiento,fecha_nacimiento,breve_historia,"
			+ "integrantes_familiares,ingresos_familiares,calle_viv_fam,numE_viv_fam,numI_viv_fam,col_viv_fam,loc_viv_fam,mun_viv_fam,edo_viv_fam,cp_viv_fam,enlace_maps,nombre_tutor,parentesco_tutor,"
			+ "celular,tel_domicilio,tipo_tel_ref,num_tel_ref,parentesco_ref,observaciones_ref,email,facebook,facebook2,facebook3,ocupacion_tutor,hermanos_inscritos,escuela_hermano,monto_beca,"
			+ "observaciones,id_usuario) values ";
	
	
	public static final String UPDATE_SQL //
	= "update beneficiarios set ";
	
	
	@Override
	public Beneficiarios mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int idBeneficiario = rs.getInt("id");
		int idTipoBeca = rs.getInt("id_tipo_beca");
		String periodo = rs.getString("periodo");
		String tipoBeca = rs.getString("tipo_beca");
		int idPeriodo = rs.getInt("id_periodo");
		String matricula = rs.getString("matricula");
		String nombre = rs.getString("nombre");		
		String apellidoPaterno = rs.getString("apellido_paterno");
		String apellidoMaterno = rs.getString("apellido_materno");
		int estatus = rs.getInt("estatus");
		String motivoEstatus = rs.getString("motivo_estatus");
		String tipoBecario = rs.getString("tipo_becario");
		String adscripcion = rs.getString("adscripcion");
		int idRegion = rs.getInt("id_region");
		String region = rs.getString("region");
		String carrera = rs.getString("carrera");
		String nivel = rs.getString("nivel");
		String modalidad = rs.getString("modalidad");
		String facultad = rs.getString("facultad");
		int idFacultad = rs.getInt("id_facultad");
		int idArea = rs.getInt("id_area");
		String area = rs.getString("area");
		int idCarrera = rs.getInt("id_carrera");
		String escuelaDeportiva = rs.getString("escuela_deportiva");
		Date fechaIngEscDep = rs.getDate("fecha_ing_escuela_dep");
		String nivelEduCursa = rs.getString("nivel_edu_cursa");
		String turno = rs.getString("turno");
		String tipoInstitucion = rs.getString("tipo_institucion");
		int grado = rs.getInt("grado");
		String nombreEdu = rs.getString("nombre_edu");
		String calleEdu = rs.getString("calle_edu");
		String numExtEdu = rs.getString("numE_edu");
		String numIntEdu = rs.getString("numIedu");
		String colEdu = rs.getString("col_edu");
		String locEdu = rs.getString("loc_edu");
		String munEdu = rs.getString("mun_edu");		
		String edoEdu = rs.getString("edo_edu");
		int cpEdu = rs.getInt("cp_edu");
		String telEdu = rs.getString("tel_edu");
		int periodoActual = rs.getInt("periodo_actual");
		double promedioGeneral = rs.getDouble("promedio_general");
		int edad = rs.getInt("edad");
		String genero = rs.getString("genero");
		String lenguaIndigena = rs.getString("lengua_indigena");
		String discapacidad = rs.getString("discapacidad");
		String estadoCivil = rs.getString("estado_civil");
		String lugarNacimiento = rs.getString("lugar_nacimiento");
		Date fechaNacimiento = rs.getDate("fecha_nacimiento");
		String breveHistoria = rs.getString("breve_historia");
		int integrantesFamiliares = rs.getInt("integrantes_familiares");
		double ingresosFamiliares = rs.getDouble("ingresos_familiares");
		String calleVivFam = rs.getString("calle_viv_fam");
		String numEVivFam = rs.getString("numE_viv_fam");
		String numIVivFam = rs.getString("numI_viv_fam");
		String colVivFam = rs.getString("col_viv_fam");
		String locVivFam = rs.getString("loc_viv_fam");
		String munVivFam = rs.getString("mun_viv_fam");
		String edoVivFam = rs.getString("edo_viv_fam");
		int cpVivFam = rs.getInt("cp_viv_fam");
		String enlaceMaps = rs.getString("enlace_maps");		
		int mismoVivFam = rs.getInt("mismo_vivienda_fam");
		
		String calleEst = rs.getString("calle_est");
		String numEEst = rs.getString("numE_est");
		String numIEst = rs.getString("numI_est");
		String colEst = rs.getString("col_est");
		String locEst = rs.getString("loc_est");
		String munEst = rs.getString("mun_est");
		String edoEst = rs.getString("edo_est");
		String cpEst = rs.getString("cp_est");
		
		String nombreTutor = rs.getString("nombre_tutor");
		String parentescoTutor = rs.getString("parentesco_tutor");
		String celular = rs.getString("celular");
		String telDomicilio = rs.getString("tel_domicilio");
		String tipoTelRef = rs.getString("tipo_tel_ref");
		String numTelRef = rs.getString("num_tel_ref");
		String parentescoRef = rs.getString("parentesco_ref");
		String observacionesRef = rs.getString("observaciones_ref");
		String email = rs.getString("email");
		String facebook = rs.getString("facebook");
		String facebook2 = rs.getString("facebook2");
		String facebook3 = rs.getString("facebook3");
		
		String ocupacionTutor = rs.getString("ocupacion_tutor");
		int hermanosInscritos = rs.getInt("hermanos_inscritos");
		String escuelaHermanosInscritos = rs.getString("escuela_hermano");
		
		int formaPago = rs.getInt("forma_pago");
		
		String banco = rs.getString("banco");
		String cuentaDeposito = rs.getString("cuenta_deposito");
		String tarjetaDeposito = rs.getString("tarjeta_deposito");
		String claveReferenciado = rs.getString("clave_referenciado");
		String vigenciaReferenciado = rs.getString("vigencia_referenciado");
		
		double montoBeca = rs.getDouble("monto_beca");
		
		String finalidadApoyo = rs.getString("finalidad_apoyo");
		String observaciones = rs.getString("observaciones");
		
		int idBenefactor = rs.getInt("id_benefactor");
		
		String estatusBene= rs.getString("estatus_bene");
			     String tipoBene= rs.getString("tipo_bene");
			     String adscripcionBene= rs.getString("adscripcion_bene");
			     String escuelaDeportivaBene= rs.getString("escuela_deportiva_bene");
			     String nivelEducativoBene= rs.getString("nivel_educativo_que_cursa_bene");
			     String turnoBene= rs.getString("turno_bene");
			     String tipoInstitucionBene= rs.getString("tipo_institucion_bene");
			     String edoCivilBene= rs.getString("estado_civil_bene");
			     String tipoTelRefBene= rs.getString("tipo_telefono_bene");
			     String hermanosInscritosBene= rs.getString("hermanos_inscritos_en_escuelas_deportivas_bene");
			     String formaPagoBene= rs.getString("forma_de_pago_bene");
			     String nombreCompletoBene= rs.getString("nombre_completo_bene");
			     String generoBene= rs.getString("genero_bene");
			     int periodoPromedio= rs.getInt("periodo_promedio");
			     int periodoRebasa= rs.getInt("periodo_rebasa");
		
		return new Beneficiarios(idBeneficiario, idTipoBeca, periodo, tipoBeca,idPeriodo, matricula, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, 
				region, carrera, nivel, modalidad, facultad, idFacultad, idArea,area,
				idCarrera, escuelaDeportiva, fechaIngEscDep, nivelEduCursa, turno, tipoInstitucion, grado,nombreEdu, calleEdu, numExtEdu, numIntEdu, colEdu, locEdu, munEdu, edoEdu,cpEdu, telEdu,
				periodoActual, promedioGeneral, edad, genero, lenguaIndigena, discapacidad, estadoCivil, lugarNacimiento, fechaNacimiento, breveHistoria, integrantesFamiliares, 
				ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps,mismoVivFam, calleEst, numEEst, numIEst, colEst, locEst, 
				munEst, edoEst, cpEst, nombreTutor, parentescoTutor, celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, 
				ocupacionTutor, hermanosInscritos, escuelaHermanosInscritos, formaPago, banco, cuentaDeposito, tarjetaDeposito, claveReferenciado, vigenciaReferenciado, montoBeca, 
				finalidadApoyo, observaciones, idBenefactor, estatusBene,  tipoBene,  adscripcionBene,  escuelaDeportivaBene,  nivelEducativoBene,  turnoBene,  tipoInstitucionBene,
				 edoCivilBene,  tipoTelRefBene,  hermanosInscritosBene, formaPagoBene, nombreCompletoBene,  periodoPromedio, periodoRebasa, generoBene);
	}

}
