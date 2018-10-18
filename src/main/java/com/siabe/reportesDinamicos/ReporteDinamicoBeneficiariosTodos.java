package com.siabe.reportesDinamicos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;


import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;

import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

@Service
public class ReporteDinamicoBeneficiariosTodos extends BaseReporteBeneficiariosGeneral {
	
	
	
	public String cadena;
	
	public DynamicReport buildReport() throws Exception {
		
		

		Style detailStyle = new Style();
		  			detailStyle.setBorder(Border.THIN());detailStyle.setBorderColor(Color.BLACK);detailStyle.setStretchWithOverflow(true);detailStyle.setPadding(5);
		  			detailStyle.setVerticalAlign(VerticalAlign.MIDDLE);detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		  //			detailStyle.setTransparency(Transparency.OPAQUE);
		  		Style headerStyle = new Style(); headerStyle.setBackgroundColor(new Color(230,230,230));headerStyle.setTransparency(Transparency.OPAQUE);headerStyle.setTextColor(new Color(51, 122, 183));
		  		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		  		headerStyle.setBorder(Border.THIN());headerStyle.setBorderColor(Color.BLACK);
		  
		  		Style titleStyle = new Style();
		  		Style subtitleStyle = new Style();
		  		Style amountStyle = new Style(); amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		  
		  		/**
		  		 * Creates the DynamicReportBuilder and sets the basic options for
		  		 * the report
		  		 */
		  		DynamicReportBuilder drb = new DynamicReportBuilder();
		  		drb.setTitle("November 2006 sales report")					//defines the title of the report
		  			.setSubtitle("The items in this report correspond "
		  					+"to the main products: DVDs, Books, Foods and Magazines")
		  			 //.setDetailHeight(17)						//defines the height for each record of the report
		  			
		  			.setMargins(5, 5, 5, 5)							//define the margin space for each side (top, bottom, left and right)
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
		
		List<AbstractColumn> columnas = new ArrayList<AbstractColumn>();
		
		for(int i=0;i< datos.length;i++) {
			
		
		
		String[] datosIn = datos[i].split(" as ");
		
	//	System.out.println(datosIn[0].trim()+" "+datosIn[0].trim().getClass().getTypeName());
		if(datosIn[0].trim().equals("fechaIngEscDep") || datosIn[0].trim().equals("fechaNacimiento")) {
			System.out.println("entre");
			AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
					.setColumnProperty(datosIn[0].trim(), Date.class.getName())			//defines the field of the data source that this column will show, also its type
					.setTitle(datosIn[1].trim())											//the title for the column
					.setWidth(85)									//the width of the column
					.setPattern("dd-MM-yyyy")
					.build();	
			columnas.add(ia);
		}else if(datosIn[0].trim().equals("estatus") || datosIn[0].trim().equals("grado") || datosIn[0].trim().equals("cpEdu") || datosIn[0].trim().equals("periodoActual") ||
				datosIn[0].trim().equals("edad") || datosIn[0].trim().equals("integrantesFamiliares") || datosIn[0].trim().equals("cpVivFam") || datosIn[0].trim().equals("mismoVivFam") ||
				datosIn[0].trim().equals("hermanosInscritos") || datosIn[0].trim().equals("formaPago") || datosIn[0].trim().equals("idBenefactor") || datosIn[0].trim().equals("periodoRebasa") ||
				datosIn[0].trim().equals("periodoPromedio"))  {
		
		AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
			.setColumnProperty(datosIn[0].trim(), Integer.class.getName())			//defines the field of the data source that this column will show, also its type
			.setTitle(datosIn[1].trim())											//the title for the column
			.setWidth(85)								//the width of the column
			.build();	
		columnas.add(ia);
		//builds and return a new AbstractColumn
		}else if(datosIn[0].trim().equals("promedioGeneral") || datosIn[0].trim().equals("ingresosFamiliares") || datosIn[0].trim().equals("montoBeca") )  {
		
			 if(datosIn[0].trim().equals("promedioGeneral")){
				 AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
							.setColumnProperty(datosIn[0].trim(), Double.class.getName())			//defines the field of the data source that this column will show, also its type
							.setTitle(datosIn[1].trim())
							.setWidth(85)								//the width of the column
							.build();	
						columnas.add(ia);
			 }else {
		AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
			.setColumnProperty(datosIn[0].trim(), Double.class.getName())			//defines the field of the data source that this column will show, also its type
			.setTitle(datosIn[1].trim())	
			.setPattern("$ #,###.00")	 //the title for the column
			.setWidth(85)								//the width of the column
			.build();	
		columnas.add(ia);
			 }
		//builds and return a new AbstractColumn
		}else {
			
			if(datosIn[0].trim().equals("breveHistoria")) {
				AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
						.setColumnProperty(datosIn[0].trim(), String.class.getName())			//defines the field of the data source that this column will show, also its type
						.setTitle(datosIn[1].trim())											//the title for the column
						.setWidth(150)		
														//the width of the column
						.build();
				columnas.add(ia);
			}else {
			AbstractColumn ia = ColumnBuilder.getNew()		//creates a new instance of a ColumnBuilder
					.setColumnProperty(datosIn[0].trim(), String.class.getName())			//defines the field of the data source that this column will show, also its type
					.setTitle(datosIn[1].trim())											//the title for the column
					.setWidth(85)		
													//the width of the column
					.build();	
			columnas.add(ia);
			}
				
				
		}
		
		
		}
		
		for(AbstractColumn str : columnas)
		{
			drb.addColumn(str);
		}
		
		
		
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
		drb.setTemplateFile("reportes/beneficiarios/beneficiariosTodos.jrxml",true,true,true,true);

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