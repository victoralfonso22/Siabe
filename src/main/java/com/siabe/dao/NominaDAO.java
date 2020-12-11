package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siabe.modelo.Nomina;
import com.siabe.mapa.DonativosMapa;
import com.siabe.mapa.NominaMapa;

import java.util.List;
import java.sql.PreparedStatement;


@Repository
@Transactional
public class NominaDAO extends JdbcDaoSupport {

	@Autowired
	public NominaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Nomina regresarNominaPeriodo(int idPeriodo) {	
		String sql = NominaMapa.BASE_SQL_PERIODOS_DONANTE + " where vn.id_periodo = ?; ";
		Object[] params = new Object[] { idPeriodo };
		NominaMapa mapper = new NominaMapa();
		try {
			Nomina periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaNomina(int idDonativo, int numDescuento, double descuentoQuincenal, double saldo, int idQuincenActual, int idUsuario) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = NominaMapa.INSERT_SQL + "(?, ?, ?, ?, ?, ?)";

	
		try {
			
			
			this.getJdbcTemplate().update(con -> {
			    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
			    ps.setInt(1, idDonativo);
			    ps.setInt(2, numDescuento);
			    ps.setDouble(3, descuentoQuincenal);		
			    ps.setDouble(4, saldo);	
			    ps.setDouble(5, idQuincenActual);	
			    ps.setInt(6, idUsuario);		
			    return ps;}, keyHolder);
			
						
			System.out.println("Ud insertado :"+keyHolder.getKey().intValue());

	
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Nomina> obtenerNominas() {

		String sql = NominaMapa.BASE_SQL +" order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new NominaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Nomina> obtenerNominasPeriodo(int idPeriodo, int idQuincena) {

		String sql = NominaMapa.BASE_SQL_PERIODOS_DONANTE +" where vn.id_periodo = "+idPeriodo+" and n.id_quincena = "+idQuincena+";";
		try {
			return this.getJdbcTemplate().query(sql, new NominaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Nomina> autocompletarDonantesNomina(String termino, int idPeriodo) {
		


		String	sql = NominaMapa.BASE_SQL_AUTOCOMPLETE_DONANTE + " where ((nombre_completo_don LIKE '%"+termino+"%') OR (rfc LIKE  '%"+termino+"%') OR (razon_social LIKE  '%"+termino+"%'))  and id_periodo = "+idPeriodo+" and medio_cobro = 1 and estatus = 1 limit 20;"; 
	
	System.out.println(sql);
	
	try {
		return this.getJdbcTemplate().query(sql, new NominaMapa());

	} catch (EmptyResultDataAccessException e) {
		return null;
	}

}
	
	public int getNumDescuento(int idDonativo) {
		
		
		String	sql = "select (num_descuento) + 1 num_descuento from nomina where id_donativo = '"+idDonativo+"'; ";
	
		
		try {
			System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
	public double getSaldo(int idDonativo) {
		
		
		String	sql = "select saldo from nomina where id_donativo = '"+idDonativo+"' order by num_descuento desc limit 1; ";
	
		
		try {
		//	System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Double.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
public Nomina verificaExiste(int idDonativo,int idPeriodo,int idQuincena) {
		
		
	String sql = NominaMapa.BASE_SQL_PERIODOS_DONANTE +" where n.id_donativo = "+idDonativo+" and vn.id_periodo = "+idPeriodo+" and n.id_quincena = "+idQuincena+" ;";
	
		
	Object[] params = new Object[] {  };
	NominaMapa mapper = new NominaMapa();
	try {
		Nomina nom = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return nom;
	} catch (EmptyResultDataAccessException e) {
		return null;
	}

	}

public String actualizaNomina(double descuentoQuincenal, double saldo, int idUsuario, int idNomina) {

	
	String sql = NominaMapa.UPDATE_SQL + " donativo_quincenal = "+descuentoQuincenal+", saldo = "+saldo+", id_usuario = "+idUsuario+" where id = "+idNomina+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


public String eliminaNomina(int idNomina) {

	
	String sql = NominaMapa.DELETE_SQL + "  where id = "+idNomina+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


}
