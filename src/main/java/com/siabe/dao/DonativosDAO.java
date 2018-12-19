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


import java.util.List;


@Repository
@Transactional
public class DonativosDAO extends JdbcDaoSupport {

	@Autowired
	public DonativosDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	public Donativos donativo(int idDonativo) {	
		String sql = DonativosMapa.BASE_SQL + " where id = ? ; ";
		Object[] params = new Object[] { idDonativo };
		DonativosMapa mapper = new DonativosMapa();
		try {
			Donativos periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Donativos> donativosPeriodo(int idPeriodo) {	
		String sql = DonativosMapa.BASE_SQL + " where id_periodo = ? ; ";
		Object[] params = new Object[] { idPeriodo };
		DonativosMapa mapper = new DonativosMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Donativos> donativosActivosPeriodo(int idPeriodo) {	
		String sql = DonativosMapa.BASE_SQL + " where id_periodo = ? and estatus = 1; ";
		Object[] params = new Object[] { idPeriodo };
		DonativosMapa mapper = new DonativosMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

			
	
	public String insertaDonante(int idPeriodo, String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String adscripcion,  String tipoDonante, int idRegion, 
			int sector, int idCampania, int medioAutorizacionDonativo, String observacionesMedioAutorizacion, int medioCobro, String numPersonal, String dependenciaAdscripcion, double donativoTotal, double donativoQuincenal,
			int numQuincenas, int idQuincenaInicio, int idCuentasBancarias, String referencia, int numPagos, double importeNumPagos, String banco, String nombreTarjetahabiente, String red, String tipoTarjeta,
			String numTarjeta,int mesVencimiento, int anioVencimiento, String tipoDonativo, int mesInicioAportacion, String email, String celular, String telefono1, String telefono2,String calle,
			String numE,String numI, String col, String loc, String mun, String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, 
			String colFiscal,String locFiscal, String munFiscal, String edoFiscal, String cpFiscal, String rfc, String observaciones, String idBeneficiarioAsignado, int idUsuario) {
		
		String sql = DonativosMapa.INSERT_SQL_DONANTES + " (1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															+ "?, ?, ?, ?, ?, ?, ?, ?)";

		Object[] params = new Object[] { idPeriodo,  razonSocial.trim(),  nombre.trim(),  apellidoPaterno.trim(),  apellidoMaterno.trim() , estatus,  motivoEstatus,  adscripcion,   tipoDonante,  idRegion, 
				 sector,  idCampania,  medioAutorizacionDonativo,  observacionesMedioAutorizacion,  medioCobro,  numPersonal,  dependenciaAdscripcion,  donativoTotal,  donativoQuincenal,
				 numQuincenas,  idQuincenaInicio,  idCuentasBancarias,  referencia,  numPagos,  importeNumPagos,  banco,  nombreTarjetahabiente,  red,  tipoTarjeta, 
				 numTarjeta, mesVencimiento,  anioVencimiento,  tipoDonativo,  mesInicioAportacion,  email,  celular,  telefono1,  telefono2, calle,
				 numE,  numI,  col,  loc,  mun,  edo,  cp,  calleFiscal,  numEFiscal,  numIFiscal,  
				 colFiscal,  locFiscal,  munFiscal,  edoFiscal,  cpFiscal, rfc.toUpperCase(),  observaciones, idUsuario };
		
		try {
			
			
		this.getJdbcTemplate().update(sql, params);
		if(idBeneficiarioAsignado != "") {
			
			String sqlInsertaRelacion = DonativosMapa.INSERT_SQL_TRDONBEN + " ("+getIdDonativos(rfc.toUpperCase())+","+idBeneficiarioAsignado+")";
			this.getJdbcTemplate().update(sqlInsertaRelacion);
		}
		
			
		return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}catch(IncorrectResultSizeDataAccessException ex) {
			return "MasFilas";
		}catch(DataIntegrityViolationException exx) {
			return "Duplicado";
		}
	}
	
	
	public String insertaPatrocinador(int idPeriodo, String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno, String adscripcion, int idRegion, int sector, 
			 String descripcionDonativo, String email, String celular, String telefono1, String telefono2, String calle,String numE, String numI, String col, String loc, String mun, 
			 String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, String locFiscal, String munFiscal, String edoFiscal, String cpFiscal,
			 String rfc, String observaciones, int idUsuario) {
		
		String sql = DonativosMapa.INSERT_SQL_PATROCINADORES + " (2, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															   + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
															   + "?, ?, ?)";
		

		Object[] params = new Object[] { idPeriodo,  razonSocial.trim(),  nombre.trim(),  apellidoPaterno.trim(),  apellidoMaterno.trim(),  adscripcion,  idRegion,  sector, descripcionDonativo,  
				email,  celular,  telefono1,  telefono2, calle, numE,  numI,  col,  loc,  mun, edo, 
				cp,  calleFiscal,  numEFiscal,  numIFiscal,  colFiscal,  locFiscal,  munFiscal,  edoFiscal,  cpFiscal, rfc.toUpperCase(), 
				observaciones,  idUsuario};
		
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

	public List<Donativos> obtenerDonativos() {

		String sql = DonativosMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new DonativosMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public String actualizaDatosDonantes(int idPeriodo, String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String adscripcion,  String tipoDonante, int idRegion, 
			int sector, int idCampania, int medioAutorizacionDonativo, String observacionesMedioAutorizacion, int medioCobro, String numPersonal, String dependenciaAdscripcion, double donativoTotal, double donativoQuincenal,
			int numQuincenas, int idQuincenaInicio, int idCuentasBancarias, String referencia, int numPagos, double importeNumPagos, String banco, String nombreTarjetahabiente, String red, String tipoTarjeta,
			String numTarjeta,int mesVencimiento, int anioVencimiento, String tipoDonativo, int mesInicioAportacion, String email, String celular, String telefono1, String telefono2,String calle,String numE, 
			String numI, String col, String loc, String mun, String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, 
			String locFiscal, String munFiscal, String edoFiscal, String cpFiscal, String rfc, String observaciones, int idUsuario, int idDonativo) {

		String sql = DonativosMapa.UPDATE_SQL + " id_periodo = ?,razon_social = ?, nombres = ?, apellido_paterno = ?,apellido_materno = ?,estatus = ?,motivo_estatus = ?," + 
				"			adscripcion = ?,tipo_donante = ?, id_region = ?, sector = ?,	id_campania = ?, medio_autorizacion_donativo = ?,observaciones_medio_autorizacion = ?," + 
				"			medio_cobro = ?,num_personal = ?,dependencia_adscripcion = ?,donativo_total = ?,donativo_quincenal = ?,num_quincenas = ?,id_quincena_inicio = ?,id_cuentas_bancarias = ?,referencia = ?," + 
				"			num_pagos = ?,importe_num_pagos = ?,banco = ?,nombre_tarjetahabiente = ?,red = ?,tipo_tarjeta = ?,num_tarjeta = ?,mes_vencimiento = ?,anio_vencimiento = ?,tipo_donativo = ?," + 
				"			mes_inicio_aportacion = ?,email = ?,celular = ?, telefono1 = ?,telefono2 = ?,calle = ?,num_exterior = ?,num_interior = ?,colonia = ?, localidad = ?, municipio = ?, estado = ?, cp = ?," + 
				"			calle_fiscal = ?,num_exterior_fiscal = ?,num_interior_fiscal = ?,colonia_fiscal = ?, localidad_fiscal = ?, municipio_fiscal = ?, estado_fiscal = ?, cp_fiscal = ?, rfc = ?," + 
				"			observaciones = ? where id =?;";
		
		//System.out.println(sql);
		Object[] params = new Object[] { idPeriodo,  razonSocial.trim(),  nombre.trim(),  apellidoPaterno.trim(),  apellidoMaterno.trim() , estatus,  motivoEstatus,  adscripcion,   tipoDonante,  idRegion, 
				 sector,  idCampania,  medioAutorizacionDonativo,  observacionesMedioAutorizacion,  medioCobro,  numPersonal,  dependenciaAdscripcion,  donativoTotal,  donativoQuincenal,
				 numQuincenas,  idQuincenaInicio,  idCuentasBancarias,  referencia,  numPagos,  importeNumPagos,  banco,  nombreTarjetahabiente,  red,  tipoTarjeta,  numTarjeta,
				 mesVencimiento,  anioVencimiento,  tipoDonativo,  mesInicioAportacion,  email,  celular,  telefono1,  telefono2,
				 calle,numE,  numI,  col,  loc,  mun,  edo,  cp,  calleFiscal,  numEFiscal,  numIFiscal,  colFiscal,  locFiscal,  munFiscal,  edoFiscal,  cpFiscal,
				 rfc,  observaciones,  idDonativo};
		// System.out.println(sql+ id+ password);
		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('donativos',"+idDonativo+","+idUsuario+")";
		
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
	
	public String actualizaDatosPatrocinadores(int idPeriodo, String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno, String adscripcion, int idRegion, int sector, 
			 String descripcionDonativo, String email, String celular, String telefono1, String telefono2,String calle,String numE, String numI, String col, String loc, String mun, 
			 String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, String locFiscal, String munFiscal, String edoFiscal, String cpFiscal,
			 String rfc, String observaciones, int idUsuario, int idDonativo) {

		String sql = DonativosMapa.UPDATE_SQL + " id_periodo = ?,razon_social = ?, nombres = ?, apellido_paterno = ?,apellido_materno = ?," + 
				"	adscripcion = ?, id_region = ?, sector = ?, descripcion_donativo = ?, email = ?,celular = ?, telefono1 = ?,telefono2 = ?,calle = ?,num_exterior = ?,num_interior = ?,colonia = ?, localidad = ?, municipio = ?, estado = ?, cp = ?," + 
				"	calle_fiscal = ?,num_exterior_fiscal = ?,num_interior_fiscal = ?,colonia_fiscal = ?, localidad_fiscal = ?, municipio_fiscal = ?, estado_fiscal = ?, cp_fiscal = ?, rfc=?," + 
				"	observaciones = ? where id =?;";
		
		//System.out.println(sql);
		Object[] params = new Object[] { idPeriodo,  razonSocial.trim(),  nombre.trim(),  apellidoPaterno.trim(),  apellidoMaterno.trim(),  adscripcion,  idRegion,  sector, 
				  descripcionDonativo,  email,  celular,  telefono1,  telefono2, calle, numE,  numI,  col,  loc,  mun, 
				  edo,  cp,  calleFiscal,  numEFiscal,  numIFiscal,  colFiscal,  locFiscal,  munFiscal,  edoFiscal,  cpFiscal,
				  rfc,  observaciones,  idDonativo};
		// System.out.println(sql+ id+ password);
		
		
		String sqlCambio = UsuarioMapa.INSERTA_CAMBIO_SQL + " ('donativos',"+idDonativo+","+idUsuario+")";
		
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
	
	public List<Donativos> autocompletarDonativosTipoDonativo(int tipoDonativo, String termino) {
		
	
		String	sql = DonativosMapa.BASE_SQL + " where nombre_completo_don like '%"+termino+"%' and donativo_tipo = "+tipoDonativo+" ; ";
		
		//System.out.println(sql);
		
		try {
			return this.getJdbcTemplate().query(sql, new DonativosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public int getIdDonativos(String rfc) {
		
		
		String	sql = DonativosMapa.BASE_SQL_ID + " WHERE rfc = '"+rfc+"'; ";
	
		
		try {
			return this.getJdbcTemplate().queryForObject(sql, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
	
public List<Donativos> autocompletarBenefactorNoPatrocinador(String termino, int idPeriodo) {
		
		
		String	sql = DonativosMapa.BASE_SQL + " WHERE id_periodo = "+idPeriodo+" and donativo_tipo = 1 and (nombre_completo_don LIKE '%"+termino+"%' OR rfc LIKE  '%"+termino+"%') order by nombre_completo_don limit 10; ";
		
//		System.out.println(sql);
		
		try {
			return this.getJdbcTemplate().query(sql, new DonativosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Donativos> autocompletarDonativosTodos(String termino, int idPeriodo) {
		
		
			String	sql = DonativosMapa.BASE_SQL + " WHERE id_periodo = "+idPeriodo+" and (nombre_completo_don LIKE '%"+termino+"%' OR rfc LIKE  '%"+termino+"%') order by nombre_completo_don limit 10; ";
		
//		System.out.println(sql);
		
		try {
			return this.getJdbcTemplate().query(sql, new DonativosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	/*
public List<Donativos> reporteGeneral(int idTipoBeca,int idPeriodo, int idRegion) {
		String idPString = "";
		String idRString = "";
	
		if(idPeriodo!=0) {
			idPString = " and id_periodo = "+idPeriodo;
		}
		
		if(idRegion!=0) {
			idRString = " and id_region = "+idRegion;
		}
		
		String	sql = "select * FROM view_donativos where id_tipo_beca = "+idTipoBeca+idPString+idRString;
		//System.out.println(sql);
		
		try {
			return this.getJdbcTemplate().query(sql, new DonativosMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}*/

}
