package com.siabe.reportesDinamicos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.RegionesServicio;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionStyleExpression;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.StatusLightCondition;
import net.sf.jasperreports.engine.JRParameter;
import ar.com.fdvs.dj.domain.entities.DJColSpan;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

@Service
public class ReporteDinamicoDonantesGeneral extends BaseReporteDonantesGeneral {
	

	@Autowired
	PeriodoServicio periodoServicio;
	
	@Autowired
	RegionesServicio regionesServicio;
	
	public String cadena;
	public String periodo = "";
	public String region = "";
	
	public DynamicReport buildReport() throws Exception {
		
		
		
	//	tipoB = tipoBecaServicio.regresaTipoBeca(idTipoBeca).getNombre();
		tipoB= "";
		
		if(idPeriodo != 0) {
			periodo = periodoServicio.regresaPeriodo(idPeriodo).getNombre();
			tipoB = tipoB+" 	Periodo : "+periodo;
		}
		
		if(idRegion != 0) {
			region = regionesServicio.regresaRegionUnica(idRegion).getNombre();
			tipoB = tipoB+" 	Región : "+region;
		}else {
			tipoB = tipoB+" 	Región : TODAS";
		}
		
		
		
		  					  			
		  					  		Style detailStyle = new Style();
		  				  			detailStyle.setStretchWithOverflow(true);detailStyle.setPadding(1);
		  				  			detailStyle.setVerticalAlign(VerticalAlign.MIDDLE);detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);detailStyle.setFont(Font.VERDANA_SMALL);detailStyle.setBlankWhenNull(Boolean.TRUE);
		  				  					  			detailStyle.setTransparency(Transparency.OPAQUE);detailStyle.setBackgroundColor(new Color(230,230,230));
		  					  			
		  					 
		  	/*	ConditionStyleExpression condition ;
		  			condition.equals("$V{REPORT_COUNT}%2==1");
		  		ConditionalStyle conditionalStyle = new ConditionalStyle(condition, detailStyle);
		  		conditionalStyle.setStyle(detailStyle);
		  		
		  		conditionalStyle.setCondition(condition);*/
		  			
		  		Style headerStyle = new Style(); headerStyle.setBackgroundColor(new Color(0,81,161));headerStyle.setTransparency(Transparency.OPAQUE);headerStyle.setTextColor(Color.WHITE);
		  		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		  		headerStyle.setBorder(Border.THIN());headerStyle.setBorderColor(Color.BLACK);headerStyle.setFont(Font.VERDANA_SMALL_BOLD);
		  		
		  		Style headerStyle1 = new Style(); headerStyle1.setBackgroundColor(new Color(0,153,61));headerStyle1.setTransparency(Transparency.OPAQUE);headerStyle1.setTextColor(Color.WHITE);
		  		headerStyle1.setVerticalAlign(VerticalAlign.MIDDLE);headerStyle1.setHorizontalAlign(HorizontalAlign.CENTER);
		  		headerStyle1.setBorder(Border.THIN());headerStyle1.setBorderColor(Color.BLACK);headerStyle1.setFont(Font.VERDANA_MEDIUM_BOLD);
		  	 
		  		Style titleStyle = new Style();
		  		titleStyle.setFont(Font.VERDANA_BIG_BOLD);
		  		
		  		Style subtitleStyle = new Style();
		  		Style amountStyle = new Style(); amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		  		
		  		
		  		/**
		  		 * Creates the DynamicReportBuilder and sets the basic options for
		  		 * the report
		  		 */
		  		DynamicReportBuilder drb = new DynamicReportBuilder();
		  		drb.setTitle(tipoB)					//defines the title of the report
		  			.setSubtitle("	")
		  			
		  			 //.setDetailHeight(17)						//defines the height for each record of the report
		  			
		  			.setMargins(2, 2, 2, 2)							//define the margin space for each side (top, bottom, left and right)
		  			.setDefaultStyles(titleStyle, subtitleStyle, headerStyle, detailStyle);
					//.setColumnsPerPage(1);
			;
		

		/*
		  Note that we didn't call the build() method yet
		 */

		/*
		  Column definitions. We use a new ColumnBuilder instance for each
		  column, the ColumnBuilder.getNew() method returns a new instance
		  of the builder
		 */
		
//		beneficiariosServicio.reporteGeneral(0, 0, 0, cadena);

		String[] datos = cadena.split(",");
		
		int x = 1;
		
		List<AbstractColumn> columnas = new ArrayList<AbstractColumn>();
		
		System.out.println("Entre "+contadores.get(0)+" longitud "+contadores.size());
		
		
	
	
		
		
		for(int i=0;i< datos.length;i++) {
			

			x++;
			

		
		String[] datosIn = datos[i].split(" as ");
	
		if( type.equals("pdf"))  {
		params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
	
		}else {
			params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		
		}
		
		if( datosIn[0].trim().equals("idDonativo") )  {
			
			AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
			.setColumnProperty(datosIn[0].trim(), Integer.class.getName())			//defines the field of the data source that this column will show, also its type
			.setTitle("#")											//the title for the column
			.setWidth(25)								//the width of the column
			.build();	
		columnas.add(ia);
		//builds and return a new AbstractColumn
		}else	if( datosIn[0].trim().equals("numQuincenas") ||
				datosIn[0].trim().equals("numPagos") || datosIn[0].trim().equals("mesVencimiento") || datosIn[0].trim().equals("anioVencimiento") || datosIn[0].trim().equals("mesInicioAportacion")
						)  {
			
			AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
			.setColumnProperty(datosIn[0].trim(), Integer.class.getName())			//defines the field of the data source that this column will show, also its type
			.setTitle(datosIn[1].trim())											//the title for the column
			.setWidth(50)								//the width of the column
			.build();	
		columnas.add(ia);
		//builds and return a new AbstractColumn
		}else if(datosIn[0].trim().equals("donativoTotal") || datosIn[0].trim().equals("donativoQuincenal") || datosIn[0].trim().equals("importeNumPagos") )  {
		
	
			AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
			.setColumnProperty(datosIn[0].trim(), Double.class.getName())			//defines the field of the data source that this column will show, also its type
			.setTitle(datosIn[1].trim())	
			.setPattern("$ #,###.00")	 //the title for the column
			.setWidth(50)								//the width of the column
			.build();	
		columnas.add(ia);
			 
		//builds and return a new AbstractColumn
		}else {
			

			
			AbstractColumn	 ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
					.setColumnProperty(datosIn[0].trim(), String.class.getName())			//defines the field of the data source that this column will show, also its type
					.setTitle(datosIn[1].trim())											//the title for the column
					.setWidth(50)			
													//the width of the column
					.build();	
			columnas.add(ia);
			
				
				
		}
		
		
		}
		
		for(AbstractColumn str : columnas)
		{		
					drb.addColumn(str);
		}
		
		
		
		drb.setColspan(1, contadores.get(0), "Datos generales",headerStyle1);
		drb.setColspan(contadores.get(0)+1, contadores.get(1), "Datos de contacto",headerStyle1);

		drb.setColspan(contadores.get(0)+1+contadores.get(1), contadores.get(2), "Domicilio particular",headerStyle1);
		drb.setColspan(contadores.get(0)+1+contadores.get(1)+contadores.get(2), contadores.get(3), "Domicilio fiscal",headerStyle1);
		
		drb.setColspan(contadores.get(0)+1+contadores.get(1)+contadores.get(2)+contadores.get(3), contadores.get(4), "Información del donativo",headerStyle1);
		drb.setColspan(contadores.get(0)+1+contadores.get(1)+contadores.get(2)+contadores.get(3)+contadores.get(4), contadores.get(5), "Observaciones generales",headerStyle1);

	//drb.setOddRowBackgroundStyle(detailStyle1);
	drb.setPrintBackgroundOnOddRows(true);
	drb.setUseFullPageWidth(false);
		
		//Create more columns
	/*	AbstractColumn columnBranch = ColumnBuilder.getNew()
			.setColumnProperty("apellidoPaterno", String.class.getName())
			.setTitle("Branch").setWidth(85)
			.build();

		AbstractColumn columnaProductLine = ColumnBuilder.getNew()
			.setColumnProperty("apellidoMaterno", String.class.getName())
			.setTitle("Product Line").setWidth(85)
			.build();

		AbstractColumn columnaItem = ColumnBuilder.getNew()
			.setColumnProperty("matricula", String.class.getName())
			.setTitle("Item").setWidth(85)
			.build();

		AbstractColumn columnCode = ColumnBuilder.getNew()
			.setColumnProperty("idBeneficiario", Integer.class.getName())
			.setTitle("ID").setWidth(40)
			.build();

		AbstractColumn columnaCantidad = ColumnBuilder.getNew()
			.setColumnProperty("facultad", String.class.getName())
			.setTitle("Quantity").setWidth(80)
			.build();

		AbstractColumn columnAmount = ColumnBuilder.getNew()
			.setColumnProperty("genero", String.class.getName())
			.setTitle("Amount").setWidth(90)
			.setPattern("$ 0.00")		//defines a pattern to apply to the values swhown (uses TextFormat)
			.setStyle(amountStyle)		//special style for this column (align right)
			.build();
*/
		/*
		  We add the columns to the report (through the builder) in the order
		  we want them to appear
		 */
	/*	
		drb.addColumn(columnBranch);
		drb.addColumn(columnaProductLine);
		drb.addColumn(columnaItem);
		drb.addColumn(columnCode);
		drb.addColumn(columnaCantidad);
		drb.addColumn(columnAmount);*/

		/*
		  add some more options to the report (through the builder)
		 */
		//drb.setUseFullPageWidth(true);	//we tell the report to use the full width of the page. this rezises
										//the columns width proportionally to meet the page width.

		//This look for the resource in the classpath
		drb.setTemplateFile("reportes/donantes/donantesGeneral.jrxml",true,true,true,true);

		//Portrait (looks the resource as a file in the filesystem)
//		drb.setTemplateFile(System.getProperty("user.dir") + "/target/test-classes/templates/TemplateReportTest.jrxml");

		//Landscape  (looks the resource as a file in the filesystem)
//		drb.setTemplateFile(System.getProperty("user.dir") + "/target/test-classes/templates/TemplateReportTestPortLandscape.jrxml");

		DynamicReport dr = drb.build();	//Finally build the report!

	//	params.put("leftHeader", "DynamicJasper is the Best!!!");
//		params.put("rightHeader", "This is the right header");

		return dr;
	}

 

}