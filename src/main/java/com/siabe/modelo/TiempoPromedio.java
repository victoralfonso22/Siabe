package com.siabe.modelo;

public class TiempoPromedio {
	
	private int idCarrera;
    private String carrera;
    private String nivel;
    private String modalidad;
    private int plan;
    private int periodoPromedio;
    private int idFacultad;
    private String facultad;
    private int idArea;
    private String area;
    private int idRegion;
    private String region;
   // private int idPeriodo;
  //  private String periodo;
    private int estatus;
    private String estatusDefinicion;
    
    
    public TiempoPromedio() {
    	
    }
 
    public TiempoPromedio(int idCarrera, String carrera, String nivel, String modalidad, int plan, int periodoPromedio, int idFacultad, 
    		String facultad, int idArea, String area, int idRegion, String region,  int estatus, String estatusDefinicion) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
        this.nivel = nivel;
        this.modalidad = modalidad;
        this.plan = plan;
        this.periodoPromedio = periodoPromedio;
        this.idFacultad = idFacultad;
        this.facultad = facultad;
        this.idArea = idArea;
        this.area = area;
        this.idRegion = idRegion;
        this.region = region;
     //   this.idPeriodo = idPeriodo;
     //   this.periodo = periodo;
        this.estatus  = estatus;
        this.estatusDefinicion = estatusDefinicion;
         }

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getPeriodoPromedio() {
		return periodoPromedio;
	}

	public void setPeriodoPromedio(int periodoPromedio) {
		this.periodoPromedio = periodoPromedio;
	}

	public int getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(int idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	/*public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}*/

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getEstatusDefinicion() {
		return estatusDefinicion;
	}

	public void setEstatusDefinicion(String estatusDefinicion) {
		this.estatusDefinicion = estatusDefinicion;
	}
 
}
