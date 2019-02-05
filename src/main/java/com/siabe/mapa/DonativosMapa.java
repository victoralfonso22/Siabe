package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Donativos;
import org.springframework.jdbc.core.RowMapper;

public class DonativosMapa implements RowMapper<Donativos> {

	public static final String BASE_SQL //
			= "SELECT * from view_donativos ";
	
	public static final String INSERT_SQL_DONANTES //
	= "INSERT INTO donativos (donativo_tipo,id_periodo,razon_social, nombres, apellido_paterno,apellido_materno,estatus,motivo_estatus,adscripcion,tipo_donante, id_region,"
			+ "sector,	id_campania, medio_autorizacion_donativo,observaciones_medio_autorizacion,medio_cobro,num_personal,dependencia_adscripcion,donativo_total,donativo_quincenal,"
			+ "num_quincenas,id_quincena_inicio,anio_quincena,id_cuentas_bancarias,referencia,num_pagos,importe_num_pagos,banco,nombre_tarjetahabiente,red,tipo_tarjeta,"
			+ "num_tarjeta,mes_vencimiento,anio_vencimiento,tipo_donativo,mes_inicio_aportacion,email,celular, telefono1,telefono2,calle,"
			+ "num_exterior,num_interior,colonia, localidad, municipio, estado, cp,calle_fiscal,num_exterior_fiscal,num_interior_fiscal,"
			+ "colonia_fiscal, localidad_fiscal, municipio_fiscal, estado_fiscal, cp_fiscal, rfc,observaciones,id_usuario) values ";
	
	public static final String INSERT_SQL_PATROCINADORES //
	= "INSERT INTO donativos (donativo_tipo,id_periodo,razon_social, nombres, apellido_paterno,apellido_materno," 
	+ "adscripcion, id_region, sector, descripcion_donativo, email,celular, telefono1,telefono2,calle,num_exterior,num_interior,colonia, localidad, municipio, estado, cp," + 
			"calle_fiscal,num_exterior_fiscal,num_interior_fiscal,colonia_fiscal, localidad_fiscal, municipio_fiscal, estado_fiscal, cp_fiscal, rfc," + 
			"observaciones,id_usuario) values ";
	
	public static final String BASE_SQL_ID //
	= "SELECT id from view_donativos ";

	
	public static final String UPDATE_SQL //
	= "update donativos set ";
	
	public static final String INSERT_SQL_TRDONBEN //
	= "insert into trDonantesBeneficiarios (id_donante,id_beneficiario) values ";
	
	public static final String UPDATE_SQL_TRDONBEN //
	= "update trDonantesBeneficiarios set ";
	
	public static final String INSERT_SQL_TRDONBENASIG //
	= "insert into trDonantesBeneficiarios (id_donante,id_beneficiario,donativo) values ";
	
	public static final String UPDATE_SQL_TRDONBENASIG //
	= "insert into trDonantesBeneficiarios (donativo) values ";
	
	public static final String SUMDONATIVO_SQL_TRDONBENASIG //
	= "select ifnull(sum(donativo),0) from trDonantesBeneficiarios ";
	
	@Override
	public Donativos mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int idDonativo = rs.getInt("id");
		int idDonativoTipo = rs.getInt("donativo_tipo");
		int idPeriodo = rs.getInt("id_periodo");
		String periodo = rs.getString("periodo");
		String tipoBeca = rs.getString("tipo_beca");
		int idTipoBeca = rs.getInt("id_tipo_beca");
		String razonSocial = rs.getString("razon_social");
		String nombre = rs.getString("nombres");
		String apellidoPaterno = rs.getString("apellido_paterno");
		String apellidoMaterno = rs.getString("apellido_materno");
		int estatus = rs.getInt("estatus");
		String estatusDon = rs.getString("estatus_don");
		String motivoEstatus = rs.getString("motivo_estatus");
		int adscripcion = rs.getInt("adscripcion");
		int tipoDonante = rs.getInt("tipo_donante");
		String tipoDon = rs.getString("tipo_don");
		int idRegion = rs.getInt("id_region");
		String region = rs.getString("region");
		int sector = rs.getInt("sector");
		String sectoDon = rs.getString("sector_don");
		String descripcionDonativo = rs.getString("descripcion_donativo");
		int idCampania = rs.getInt("id_campania");
		String campania = rs.getString("campania");
		int medioAutorizacionDonativo = rs.getInt("medio_autorizacion_donativo");
		String medioAutorizacionDon = rs.getString("medio_autorizacion_don");
		String observacionMedioAutorizacion = rs.getString("observaciones_medio_autorizacion");
		int medioCobro = rs.getInt("medio_cobro");
		String medioCobroDon = rs.getString("medio_cobro_don");
		String numPersonal = rs.getString("num_personal");
		String dependenciaAdscripcion = rs.getString("dependencia_adscripcion");
		double donativoTotal = rs.getDouble("donativo_total");
		double donativoQuincenal = rs.getDouble("donativo_quincenal");
		int numQuincenas = rs.getInt("num_quincenas");
		int idQuincenaInicio = rs.getInt("id_quincena_inicio");
		String quincenaInicio = rs.getString("quincena_inicio");
		String anioQuincena = rs.getString("anio_quincena");
		int idCuentaBancaria = rs.getInt("id_cuentas_bancarias");
		String cuentaBancaria = rs.getString("cuenta_bancaria");
		String referencia = rs.getString("referencia");
		int numPagos = rs.getInt("num_pagos");
		double importeNumPagos = rs.getDouble("importe_num_pagos");
		String banco = rs.getString("banco");
		String nombreTarjetahabiente = rs.getString("nombre_tarjetahabiente");
		String red = rs.getString("red");
		String tipoTarjeta = rs.getString("tipo_tarjeta");
		String numTarjeta = rs.getString("num_tarjeta");
		int mesVencimiento = rs.getInt("mes_vencimiento");
		int anioVencimiento = rs.getInt("anio_vencimiento");
		String tipoDonativo = rs.getString("tipo_donativo");
		int mesInicioAportacion = rs.getInt("mes_inicio_aportacion");
		String email = rs.getString("email");
		String celular = rs.getString("celular");
		String telefono1 = rs.getString("telefono1");
		String telefono2 = rs.getString("telefono2");
		String calle = rs.getString("calle");
		String numE = rs.getString("num_exterior");
		String numI = rs.getString("num_interior");
		String col = rs.getString("colonia");
		String loc = rs.getString("localidad");
		String mun = rs.getString("municipio");
		String edo = rs.getString("estado");
		String cp = rs.getString("cp");
		String calleFiscal = rs.getString("calle_fiscal");
		String numEFiscal = rs.getString("num_exterior_fiscal");
		String numIFiscal = rs.getString("num_interior_fiscal");
		String colFiscal = rs.getString("colonia_fiscal");
		String locFiscal = rs.getString("localidad_fiscal");
		String munFiscal = rs.getString("municipio_fiscal");
		String edoFiscal = rs.getString("estado_fiscal");
		String cpFiscal = rs.getString("cp_fiscal");
		String rfc = rs.getString("rfc");
		String observaciones = rs.getString("observaciones");
		int idBeneficiario = rs.getInt("id_beneficiario_asignado");
		String beneficiario = rs.getString("nombre_completo_bene");
		String nombreCompletoDon = rs.getString("nombre_completo_don");
		
		return new Donativos( idDonativo, idDonativoTipo, idPeriodo,  periodo,  tipoBeca,  idTipoBeca,  razonSocial,
				 nombre,  apellidoPaterno,  apellidoMaterno,  estatus,  estatusDon,
				 motivoEstatus,  adscripcion,  tipoDonante,  tipoDon,  idRegion,  region,
				 sector,  sectoDon,  descripcionDonativo,  idCampania,  campania,
				 medioAutorizacionDonativo,  medioAutorizacionDon,  observacionMedioAutorizacion,
				 medioCobro,  medioCobroDon,  numPersonal,  dependenciaAdscripcion,  donativoTotal,
				 donativoQuincenal,  numQuincenas,  idQuincenaInicio,  quincenaInicio, anioQuincena,
				 idCuentaBancaria,  cuentaBancaria,  referencia,  numPagos,  importeNumPagos,
				 banco,  nombreTarjetahabiente,  red,  tipoTarjeta,  numTarjeta,
				 mesVencimiento,  anioVencimiento, tipoDonativo, mesInicioAportacion,  email,  celular,
				 telefono1,  telefono2,  calle,  numE,  numI,  col,  loc,
				 mun,  edo,  cp,  calleFiscal,  numEFiscal,  numIFiscal,  colFiscal,
				 locFiscal,  munFiscal,  edoFiscal,  cpFiscal,  rfc,  observaciones,
				 idBeneficiario,  beneficiario,nombreCompletoDon);
	}

}
