package com.siabe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siabe.modelo.TiempoPromedio;

import com.siabe.servicio.TiempoPromedioServicio;



@Controller
public class ControladorAutocompletar {
	
	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;
	

	@RequestMapping(value = "/tiempoPromedio", method = RequestMethod.GET)
	 @ResponseBody
	public  List<TiempoPromedio> getTiempoPromedio(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("term") String term) throws Exception {

		System.out.println("Escribe type " + term);
	
			
		return tiempoPromedioServicio.todosTiemposPromedioTermino(term);

	}

}
