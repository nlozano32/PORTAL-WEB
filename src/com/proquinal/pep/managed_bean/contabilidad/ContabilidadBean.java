package com.proquinal.pep.managed_bean.contabilidad;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.proquinal.pep.base.UsuarioProquinalBean;
import com.proquinal.pep.entidades.contabilidad.ContabilidadLogCert;
import com.proquinal.pep.entidades.contabilidad.dto.ReteFuenteDTO;
import com.proquinal.pep.entidades.contabilidad.dto.ReteIcaDTO;
import com.proquinal.pep.entidades.contabilidad.dto.ReteIvaDTO;
import com.proquinal.pep.entidades.nomina.TNomConfig;
import com.proquinal.pep.entidades.ycontent.dto.ParametricaDTO;
import com.proquinal.pep.fwk.constantes.ConstantesPEP;
import com.proquinal.pep.fwk.constantes.ConstantesPEPParametricas;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.fwk.utilidades.NumerosLetras;
import com.proquinal.pep.fwk.utilidades.UtilidadesFecha;
import com.proquinal.pep.fwk.utilidades.UtilidadesVarias;
import com.proquinal.pep.negocioServicio.contabilidad.IServicioContabilidad;
import com.proquinal.pep.negocioServicio.nomina.IServicioNomina;
import com.proquinal.pep.negocioServicio.ycontent.IServicioYcontent;

/**
 * Backing Bean utilizado para definir los requerimientos de contabilidad
* @author <a href="mailto:luismartinez@btech.com.co">Luis Gabriel Martinez Lopez</a>
* @version 1.0
* @FechaCreacion 30/01/2011
*/

public class ContabilidadBean extends UsuarioProquinalBean {
	
	private static final long serialVersionUID = 1L;

	@EJB(mappedName="ejb/ServicioContabilidad")
	private IServicioContabilidad servicioContabilidad;
	
	@EJB(mappedName="ejb/ServicioYcontent")
	private IServicioYcontent servicioYcontent;
	
	@EJB(mappedName="ejb/ServicioNomina")
	private IServicioNomina servicioNomina;
	 
	// Parametros de entrada para la generacion de certificados
	/**
	 * Codigo del tercero
	 */
	private String codigoTercero;
	/**
	 * bimestre Inicial
	 */
	private String bimestreInicial;
	
	/**
	 * bimestre final
	 */
	private String bimestreFinal;
	/**
	 * distingue el tipo de certificado
	 */
	private String tipoCertificado; 
	/**
	 * año fiscal
	 */
	private String anoFiscal;
	/**
	 * Periodo	
	 */
	private String periodo;
	
	private String totalSometidoRetencion;
	
	
	private String montoTotalRetencion;
	
	private String ciudad;
	
	
	/**
	 * Este metodo obtiene el codigo del tercero
	 * @return
	 */
	public String getCodigoTercero() {
		return codigoTercero;
	}
	/**
	 * Este metodo permite establecer el codigo del tercero
	 * @param codigoTercero
	 */
	public void setCodigoTercero(String codigoTercero) {
		this.codigoTercero = codigoTercero;
	}

	/**
	 * Este metodo obtiene el bimestre inicial
	 * @return String bimestre inicial 
	 */
	public String getBimestreInicial() {
		return bimestreInicial;
	}
	/**
	 * Este metodo permite establecer el bimestre inicial
	 * @param bimestreInicial
	 */
	public void setBimestreInicial(String bimestreInicial) {
		this.bimestreInicial = bimestreInicial;
	}
	/**
	 * Este metodo obtiene el bimestre final
	 * @return String bimestre final
	 */
	public String getBimestreFinal() {
		return bimestreFinal;
	}
	public void setBimestreFinal(String bimestreFinal) {
		this.bimestreFinal = bimestreFinal;
	}
	/**
	 * Este metodo obtiene el tipo de certificado
	 * @return
	 */
	public String getTipoCertificado() {
		return tipoCertificado;
	}

	/**
	 * Este metodo permite establecer el tipo de certificado
	 * @param tipoCertificado
	 */
	public void setTipoCertificado(String tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	/**
	 * Este metodo obtiene el año fiscal
	 * @return
	 */
	public String getAnoFiscal() {
		return anoFiscal;
	}

	/**
	 * Este metodo permite establecer el año fiscal
	 * @param anoFiscal
	 */
	public void setAnoFiscal(String anoFiscal) {
		this.anoFiscal = anoFiscal;
	}
	
	/**
	 * Este metodo obtiene el periodo
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * Este metodo permite establecer el periodo
	 * @param periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Este metodo obtiene el monto total sometido a retencion por concepto de retefuente, ica o iva
	 * @return
	 */
	public String getTotalSometidoRetencion() {
		return totalSometidoRetencion;
	}
	/**
	 * Este metodo permite establecer el monto total sometido a retencion por concepto de retefuente, ica o iva
	 * @param totalSometidoRetencion
	 */
	public void setTotalSometidoRetencion(String totalSometidoRetencion) {
		this.totalSometidoRetencion = totalSometidoRetencion;
	}

	/**
	 * Este metodo obtiene el montal total por retencion
	 * @return montal total por retencion
	 */
	public String getMontoTotalRetencion() {
		return montoTotalRetencion;
	}
	/**
	 * Este metodo permite establecer el montal total por retencion
	 * @param montoTotalRetencion
	 */
	public void setMontoTotalRetencion(String montoTotalRetencion) {
		this.montoTotalRetencion = montoTotalRetencion;
	}

	/**
	 * Este metodo obtiene el nombre de la ciudad donde se practico la retencion
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * Este metodo permite establecer la ciudad donde se practico la retencion
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
	 * Este metodo obtiene los bimestres de un año fiscal
	 * @return the bimestres
	 */
	public ArrayList<SelectItem> getBimestres() {
		
		bimestres = new ArrayList <SelectItem> ();
		
		bimestres.add( new SelectItem("1", "Enero - Febrero" ) );
		bimestres.add( new SelectItem("2", "Marzo - Abril" ) );
		bimestres.add( new SelectItem("3", "Mayo - Junio" ) );
		bimestres.add( new SelectItem("4", "Julio - Agosto" ) );
		bimestres.add( new SelectItem("5", "Septiembre - Octubre" ) );
		bimestres.add( new SelectItem("6", "Noviembre - Diciembre" ) );

		return bimestres;
	}
	
	
	/**
	 * Este metodo obtiene el listado del año fiscal anterior y el año en curso
	 * @return the lAnoFiscal
	 */
	public ArrayList<SelectItem> getlAnoFiscal() {
		lAnoFiscal = new ArrayList <SelectItem> ();
		int periodoFiscal = 0;
		GregorianCalendar gc = new GregorianCalendar();
		int year = gc.get( Calendar.YEAR );
		int dia = gc.get( Calendar.DAY_OF_MONTH );
		int mes = gc.get( Calendar.MONTH );
		
		if ( ( mes == 1 ) && ( dia < 15 ) ) {
			periodoFiscal = year - 2;
		}
		else {
			periodoFiscal = year--;
		}
		
		lAnoFiscal.add( new SelectItem( String.valueOf( periodoFiscal ), String.valueOf( periodoFiscal ) ) );
		lAnoFiscal.add( new SelectItem( "2009", "2009" ) );
		return lAnoFiscal;
	}
	
	/**
	 * Este metodo obtiene el listado del año fiscal anterior para certificado retefuente
	 * @return the lAnoFiscalReteFuente
	 */
	public ArrayList<SelectItem> getlAnoFiscalReteFuente() {
		logger.info("ingreso metodo ContabilidadBean:getlAnoFiscalReteFuente");
		lAnoFiscalReteFuente = new ArrayList <SelectItem> ();
		int periodoFiscal = 0;
		GregorianCalendar gc = new GregorianCalendar();
		int year = gc.get( Calendar.YEAR );
		int dia = gc.get( Calendar.DAY_OF_MONTH );
		int mes = gc.get( Calendar.MONTH );
		if ( ( mes == 1 ) && ( dia < 16 ) ) {
			periodoFiscal = year - 2;
		}
		else {
			periodoFiscal = --year;
		}
		lAnoFiscalReteFuente.add( new SelectItem( String.valueOf( periodoFiscal ), String.valueOf( periodoFiscal ) ) );
		lAnoFiscalReteFuente.add( new SelectItem( "2009", "2009" ) );
		
		return lAnoFiscalReteFuente;
	}
	/**
	 * @param lAnoFiscalReteFuente the lAnoFiscalReteFuente to set
	 */
	public void setlAnoFiscalReteFuente(ArrayList<SelectItem> lAnoFiscalReteFuente) {
		this.lAnoFiscalReteFuente = lAnoFiscalReteFuente;
	}
	/**
	 * Este metodo permite establecer el año fiscal anterior y el año en curso
	 * @param lAnoFiscal the lAnoFiscal to set
	 */
	public void setlAnoFiscal(ArrayList<SelectItem> lAnoFiscal) {
		this.lAnoFiscal = lAnoFiscal;
	}
	/**
	 * @param bimestres the bimestres to set
	 */
	public void setBimestres(ArrayList<SelectItem> bimestres) {
		this.bimestres = bimestres;
	}
	/**
	 * Este metodo funciona como testing para porbar el servicio certificadoReteFuente
	 * @return
	 */
	public String certificadoReteFuente(){
		logger.info("ingreso metodo ContabilidadBean:certificadoRetefuente");
		List<ReteFuenteDTO> lReteFuente = new ArrayList<ReteFuenteDTO>();
		int valorRetenido = 0;
		int valorSometidoRetencion = 0;
		try {
			lReteFuente = servicioContabilidad.certificadoReteFuente(codigoTercero, periodo);
			for (Iterator<ReteFuenteDTO> iterator = lReteFuente.iterator(); iterator.hasNext();) {
				ReteFuenteDTO reteFuenteDTO = (ReteFuenteDTO) iterator.next();
				System.out.println( reteFuenteDTO.getPeriodo() + " " +
									reteFuenteDTO.getConcepto()+ " " +
									reteFuenteDTO.getTasaAplicada() + " " +
									reteFuenteDTO.getValorBase() + " " +
									reteFuenteDTO.getValorRetenido()
				);
				valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteFuenteDTO.getValorBase() );
				valorRetenido = valorRetenido + Integer.parseInt( reteFuenteDTO.getValorRetenido() );
			}
			setTotalSometidoRetencion( String.valueOf( valorSometidoRetencion ) );
			setMontoTotalRetencion(String.valueOf( valorRetenido ) );
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Este metodo funciona como testing para porbar el servicio certificadoReteIca
	 * @return
	 */
	public String certificadoReteIca(){
		logger.info("ingreso metodo ContabilidadBean:certificadoReteIca");
		List<ReteIcaDTO> lReteIca;
		int valorRetenido = 0;
		int valorSometidoRetencion = 0;
		try {
			lReteIca = servicioContabilidad.certificadoReteIca(codigoTercero, anoFiscal, bimestreInicial, bimestreFinal);
			for (Iterator<ReteIcaDTO> iterator = lReteIca.iterator(); iterator.hasNext();) {
				ReteIcaDTO reteIcaDTO = (ReteIcaDTO) iterator.next();
				System.out.println( reteIcaDTO.getBimestre() + " " +
									reteIcaDTO.getConcepto()+ " " +
									reteIcaDTO.getMontoTotal() + " " +
									reteIcaDTO.getValorBase() + " " +
									reteIcaDTO.getTasa() + " " +
									reteIcaDTO.getValorRetenido()
				);
				valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteIcaDTO.getMontoTotal() );
				valorRetenido = valorRetenido + Integer.parseInt( reteIcaDTO.getValorRetenido() );
			}
			setTotalSometidoRetencion( String.valueOf( valorSometidoRetencion ) );
			setMontoTotalRetencion(String.valueOf( valorRetenido ) );
			System.out.println( getTotalSometidoRetencion() );
			System.out.println( getMontoTotalRetencion() );
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * certificadoReteIva
	 * @return
	 */
	public String certificadoReteIva(){
		logger.info("ingreso metodo ContabilidadBean:certificadoReteIva");
		List<ReteIvaDTO> lReteIva;
		int valorRetenido = 0;
		int valorSometidoRetencion = 0;
		try {
			lReteIva = servicioContabilidad.certificadoReteIva(codigoTercero, anoFiscal, bimestreInicial, bimestreFinal);
			for (Iterator<ReteIvaDTO> iterator = lReteIva.iterator(); iterator.hasNext();) {
				ReteIvaDTO reteIvaDTO = (ReteIvaDTO) iterator.next();
				System.out.println( reteIvaDTO.getBimestre() + " " +
									reteIvaDTO.getConcepto()+ " " +
									reteIvaDTO.getMontoTotal()+ " " +
									reteIvaDTO.getIvaGenerado() + " " +
									reteIvaDTO.getTasa() + " " +
									reteIvaDTO.getValorRetenido()
				);
				valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteIvaDTO.getMontoTotal() );
				valorRetenido = valorRetenido + Integer.parseInt( reteIvaDTO.getValorRetenido() );
			}
			setTotalSometidoRetencion(String.valueOf( valorSometidoRetencion ) );
			setMontoTotalRetencion(String.valueOf( valorRetenido ) );
			System.out.println( getTotalSometidoRetencion() );
			System.out.println( getMontoTotalRetencion() );
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Este metodo genera el certificado de ReteFuente en formato PDF
	 * @param path ruta que indica el espacio fisico del equipo donde quedara almacenado el documento
	 * @return booleano true indica si el documento fue generado correctamente de lo contrario false
	 */
	public boolean generarPDFRetefuente(String path){
		logger.info("ingreso metodo ContabilidadBean:certificadoRetefuente");
		FacesContext context = FacesContext.getCurrentInstance();
		boolean bEstado = false;
		List<ReteFuenteDTO> lReteFuente = new ArrayList<ReteFuenteDTO>();
		
		try {
			codigoTercero			= getUsuarioAutenticado().getCodigo();
			System.out.println("codigo ********** " + getUsuarioAutenticado().getCodigo());
			String nit				= getUsuarioAutenticado().getIdentificacion();
			System.out.println("Identificacion ********** " + getUsuarioAutenticado().getIdentificacion());
			String proveedor 		= getUsuarioAutenticado().getNombre();

			// NIT y nombre de la empresa
			TNomConfig tConfig = servicioNomina.obtenerInfoEmpresa( ConstantesPEP.ID_PROQUINAL );
			//String nombreEmpresa = tConfig.getDDescripcion();
			String nitEmpresa     = tConfig.getDNit();
			
			lReteFuente = servicioContabilidad.certificadoReteFuente(codigoTercero, anoFiscal);
			List<ContabilidadLogCert> logCertificado = new ArrayList<ContabilidadLogCert>();
			logCertificado = servicioContabilidad.obtenerRegistroCertificadoRetefuente( codigoTercero, anoFiscal );
			
			if ( lReteFuente.size() > 0 ){
				//String fechaEmision = ConstantesPEP.CIUDAD_EMISION_CERTIFICADO + " " + UtilidadesFecha.formatDateInSpanish( new Date() );
				int valorRetenido = 0;
				int valorSometidoRetencion = 0;
				Document documento = new Document(PageSize.A4, ConstantesPEP.MARGEN_IZQUIERDA_RETENCIONES, ConstantesPEP.MARGEN_DERECHA_RETENCIONES, ConstantesPEP.MARGEN_SUPERIOR_RETENCIONES, ConstantesPEP.MARGEN_INFERIOR_RETENCIONES); //tamaño de la página,left, right, top, botton;
				
				// fuente para los titulos
				Font fontTitulos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontTitulos.setSize(9);
				
				// fuente para los conceptos
				Font fontConceptos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontConceptos.setSize(8);
				
				// fuente para la leyendas
				Font fontLeyendas = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontLeyendas.setSize(9);
				
				// fuente para las leyendas con negrilla
				Font fontLeyendasNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontLeyendasNegrita.setSize(9);
				
				// fuente para la leyenda Departamento
				Font fontMayusculaNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontMayusculaNegrita.setSize(8);
				
				// fuente para el titulo NIT
				Font fontNIT = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontNIT.setSize(8);
				
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
				FileOutputStream ficheroPdf = new FileOutputStream(path);
		
				// Se asocia el documento al OutputStream y se indica que el espaciado entre
				// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento

				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				// Se abre el documento.
				documento.open();
				
				// realiza la insercion de la imagen transparente indicando que es una copia
				if ( logCertificado.size() > 0 ) {
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				// se agrega el logo de la empresa
				String logoPqnRuta = servicioYcontent.obtenerLogoProquinal();
			    Image logo = Image.getInstance(logoPqnRuta);
			    logo.setAlignment(Image.LEFT);
			    logo.scaleAbsolute(90, 85);
			    documento.add(logo);
			    
			    // TABLA 1. se crea la tabla que contiene el NIT de la empresa
			    PdfPTable table;
			    PdfPCell cell = null;
			 	table = new PdfPTable(1);
				table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);
			    
				// fila que contiene el NIT
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NIT_CL + " " + nitEmpresa, fontNIT));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingLeft(8f); // para que no quede el texto pegado a la tabla lo separamos 8 px;
				table.addCell(cell);

				documento.add(table); //pinta la tabla en el PDF
			    
				PdfPCell space;
		        space = new PdfPCell();
		        space.setBorder(Rectangle.NO_BORDER);
		        space.setColspan(2);
		        space.setFixedHeight( ConstantesPEP.ESPACIO_LEYENDAS );
				
		     // TABLA 2. se crea la tabla que contiene el encabezado inicial certificado retefuente
				PdfPTable tablaEncabezado = new PdfPTable(1);
				tablaEncabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Paragraph());
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( space);
				tablaEncabezado.addCell( cell);
				
				cell = new PdfPCell(new Paragraph(ConstantesPEP.LEYENDA_NORMAS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CERTIFICAMOS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				tablaEncabezado.addCell(space);
				
				ParametricaDTO leyendaEncabezado = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_ENCABEZADO_RETEFUENTE );
				String sLeyendaEncabezado = leyendaEncabezado.getDescripcion1().replaceAll("#periodoFiscal", anoFiscal );
				String sLeyendaEncabezadoNit = sLeyendaEncabezado.replaceAll("#nit", nit );
				String sLeyendaEncabezadoProveedor = sLeyendaEncabezadoNit.replaceAll("#proveedor", proveedor );
				cell = new PdfPCell(new Paragraph(sLeyendaEncabezadoProveedor , fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaEncabezado.addCell(cell);
				tablaEncabezado.addCell(space);
				
				documento.add(tablaEncabezado);
				
				// tabla que contiene los titulos de los conceptos
				PdfPTable tablaTitulos = new PdfPTable(5); // se crea la tabla con los anchos especificos en sus 2 columnas
				//tablaTitulos.setWidthPercentage(anchoTabla);
				tablaTitulos.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
			    
			    // fila que contiene el concepto periodo
			    cell = new PdfPCell(new Paragraph( ConstantesPEP.CONCEPTOS_PERIODO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_CONCEPTO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el concepto tasa aplicada 
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_TASA_APLICADA, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_VALOR_BASE, fontTitulos));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el el concepto valor retenido
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_VALOR_RETENIDO, fontTitulos));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);

			    documento.add(tablaTitulos);
			   
			    // tabla que contiene los valores de los conceptos traidos de la BD
			    PdfPTable tabla = new PdfPTable(5);
				tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

				for (Iterator<ReteFuenteDTO> iterator = lReteFuente.iterator(); iterator.hasNext();) {
					ReteFuenteDTO reteFuenteDTO = (ReteFuenteDTO) iterator.next();
					// Agregar los diferentes conceptos
					ciudad = reteFuenteDTO.getCiudad();
					cell = new PdfPCell(new Paragraph( reteFuenteDTO.getPeriodo(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER); 
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( reteFuenteDTO.getConcepto(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( reteFuenteDTO.getTasaAplicada() + "%" , fontConceptos));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteFuenteDTO.getValorBase() ), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteFuenteDTO.getValorRetenido() + "\n" ) , fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					
					valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteFuenteDTO.getValorBase() );
					valorRetenido = valorRetenido + Integer.parseInt( reteFuenteDTO.getValorRetenido() );
				}	
				setTotalSometidoRetencion( String.valueOf( valorSometidoRetencion ) );
				setMontoTotalRetencion(String.valueOf( valorRetenido ) );
				String valorRetenidoLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorRetenido ).toUpperCase() ;
				String valorSometidoRetencionLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorSometidoRetencion ).toUpperCase() ;
				String vRetenido = UtilidadesVarias.formatoDeMil( String.valueOf( valorRetenido ) );
				String vSometidoRetencion = UtilidadesVarias.formatoDeMil( String.valueOf( valorSometidoRetencion ) );
				tabla.addCell(space);
				tabla.addCell(space);
				documento.add(tabla);
				
				// tabla que contiene las leyendas finales del documento
				PdfPTable tablaFooter = new PdfPTable(1);
				tablaFooter.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Paragraph());
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell( cell);
				tablaFooter.addCell(space);
				
				// leyenda que contiene el pago sometido a retencion
				ParametricaDTO leyenda01 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEFUENTE_01 );
				String sLeyenda01 = leyenda01.getDescripcion1().replaceAll("#valorSometidoRetencionLetras", valorSometidoRetencionLetras);
				String leyendaRenta01 = sLeyenda01.replaceAll("#valorSometidoRetencion", vSometidoRetencion);
				cell = new PdfPCell(new Paragraph(leyendaRenta01, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);

				// leyenda que contiene los valores por los cuales se le efectuo retencion
				ParametricaDTO leyenda02 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEFUENTE_02 );
				String sLeyenda02 = leyenda02.getDescripcion1().replaceAll("#valorLetrasRetenido", valorRetenidoLetras);
				String leyendaRenta02 = sLeyenda02.replaceAll("#valorRetenido", vRetenido);
				cell = new PdfPCell(new Paragraph(leyendaRenta02, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda ciudad de retencion
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CIUDAD_RETENCION + " " + ciudad, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda articulo del certificado retefuente
				ParametricaDTO leyendaArticulo = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEFUENTE_ARTICULO );
				cell = new PdfPCell(new Paragraph(leyendaArticulo.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda donde se indica la direccion y telefono del departamento que emite el certificado
				ParametricaDTO leyendaDepartamento = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_DEPARTAMENTO );
				cell = new PdfPCell(new Paragraph(leyendaDepartamento.getDescripcion1(), fontMayusculaNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				// leyenda sobre la firma
				ParametricaDTO leyendaFirma = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_FIRMA_RETENCIONES );
				cell = new PdfPCell(new Paragraph(leyendaFirma.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				if ( logCertificado.size() > 0 ) {
					// realiza la insercion de la imagen transparente indicando que es una copia
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				
				// leyenda fecha de emision certificado
				ParametricaDTO leyendaFechaEmision = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.FECHA_EMISION_RETEFUENTE );
				cell = new PdfPCell(new Paragraph( leyendaFechaEmision.getDescripcion1(), fontLeyendas));
				//cell = new PdfPCell(new Paragraph( fechaEmision, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				documento.add(tablaFooter);

				documento.close();
				bEstado = true;
			}
			else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.NO_EXISTE_CERTIFICADO_RETEFUENTE, null ) );
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bEstado;
	}
	/**
	 * Este metodo genera el certificado de ReteIca en formato PDF
	 * @param path ruta que indica el espacio fisico del equipo donde quedara almacenado el documento
	 * @return booleano true indica si el documento fue generado correctamente de lo contrario false
	 */
	public boolean generarPDFReteIca( String path ){
		logger.info("ingreso metodo ContabilidadBean:generarPDFReteIca");
		FacesContext context = FacesContext.getCurrentInstance();
		List<ReteIcaDTO> lReteIca = new ArrayList<ReteIcaDTO>();
		boolean bEstado = false;		
		try {
			//codigoTercero 		= "9001955819";
			//String nit 			= "89939399393-2";
			//String proveedor 	= "VANIPLAST S.A";
			
			codigoTercero			= getUsuarioAutenticado().getCodigo();
			System.out.println("codigo ********** " + getUsuarioAutenticado().getCodigo());
			String nit				= getUsuarioAutenticado().getIdentificacion();
			System.out.println("Identificacion ********** " + getUsuarioAutenticado().getIdentificacion());
			String proveedor 		= getUsuarioAutenticado().getNombre();
			
			// NIT y nombre de la empresa
			TNomConfig tConfig = servicioNomina.obtenerInfoEmpresa( ConstantesPEP.ID_PROQUINAL );
			//String nombreEmpresa = tConfig.getDDescripcion();
			String nitEmpresa     = tConfig.getDNit();
			
			lReteIca = servicioContabilidad.certificadoReteIca(codigoTercero, anoFiscal, bimestreInicial, bimestreFinal);
			boolean bCopia = servicioContabilidad.validarDuplicidadCertificados(codigoTercero, anoFiscal, ConstantesPEP.CERTIFICADO_RETEICA, bimestreInicial, bimestreFinal);
			
			if ( lReteIca.size() > 0 ) {
				int valorRetenido = 0;
				int valorSometidoRetencion = 0;
				//String fechaEmision = ConstantesPEP.CIUDAD_EMISION_CERTIFICADO + " " + UtilidadesFecha.formatDateInSpanish( new Date() );
				String fechaEmision = ConstantesPEP.CIUDAD_EMISION_CERTIFICADO + " " + UtilidadesVarias.obtenerFechaEmisionBimestre(bimestreFinal);  
				Document documento = new Document(PageSize.A4, ConstantesPEP.MARGEN_IZQUIERDA_RETENCIONES, ConstantesPEP.MARGEN_DERECHA_RETENCIONES, ConstantesPEP.MARGEN_SUPERIOR_RETENCIONES, ConstantesPEP.MARGEN_INFERIOR_RETENCIONES); //tamaño de la página,left, right, top, botton;
				
				// fuente para los titulos
				Font fontTitulos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontTitulos.setSize(9);
				
				// fuente para los conceptos
				Font fontConceptos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontConceptos.setSize(8);
				
				// fuente para la leyendas
				Font fontLeyendas = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontLeyendas.setSize(9);
				
				// fuente para las leyendas con negrilla
				Font fontLeyendasNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontLeyendasNegrita.setSize(9);
				
				// fuente para la leyenda Departamento
				Font fontMayusculaNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontMayusculaNegrita.setSize(8);
				
				// fuente para el titulo NIT
				Font fontNIT = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontNIT.setSize(8);
				
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
				FileOutputStream ficheroPdf = new FileOutputStream( path );
		
				// Se asocia el documento al OutputStream y se indica que el espaciado entre
				// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento

				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				// Se abre el documento.
				documento.open();
				// realiza la insercion de la imagen transparente indicando que es una copia
				if ( bCopia ) {
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				// se agrega el logo de la empresa
				String logoPqnRuta = servicioYcontent.obtenerLogoProquinal();
			    Image logo = Image.getInstance(logoPqnRuta);
			    logo.setAlignment(Image.LEFT);
			    logo.scaleAbsolute(90, 85);
			    documento.add(logo);
			    
			    // TABLA 1. se crea la tabla que contiene el NIT de la empresa
			    PdfPTable table;
			    PdfPCell cell = null;
			 	table = new PdfPTable(1);
				table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);

				// fila que contiene el NIT
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NIT_CL + " " + nitEmpresa, fontNIT));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingLeft(8f); // para que no quede el texto pegado a la tabla lo separamos 8 px;
				table.addCell(cell);
				
				documento.add(table); 
			    
				PdfPCell space;
		        space = new PdfPCell();
		        space.setBorder(Rectangle.NO_BORDER);
		        space.setColspan(2);
		        space.setFixedHeight( ConstantesPEP.ESPACIO_LEYENDAS );
				
				PdfPTable tablaEncabezado = new PdfPTable(1);
				tablaEncabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Paragraph());
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( space);
				tablaEncabezado.addCell( cell);
				
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_NORMAS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CERTIFICAMOS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				tablaEncabezado.addCell(space);
				
				String bimestres = UtilidadesVarias.obtenerRangoBimestres(bimestreInicial, bimestreFinal);
				ParametricaDTO leyendaEncabezado = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_ENCABEZADO_RETEICA );
				String sLeyendaEncabezadoBimestres = leyendaEncabezado.getDescripcion1().replaceAll("#bimestres", bimestres );
				String sLeyendaEncabezado = sLeyendaEncabezadoBimestres.replaceAll("#periodoFiscal", anoFiscal );
				String sLeyendaEncabezadoNit = sLeyendaEncabezado.replaceAll("#nit", nit );
				String sLeyendaEncabezadoProveedor = sLeyendaEncabezadoNit.replaceAll("#proveedor", proveedor );
				cell = new PdfPCell(new Paragraph(sLeyendaEncabezadoProveedor , fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaEncabezado.addCell(cell);
				tablaEncabezado.addCell(space);
				
				documento.add(tablaEncabezado);
				
				// tabla que contiene los titulos de los conceptos
				PdfPTable tablaTitulos = new PdfPTable(6); // se crea la tabla con los anchos especificos en sus 2 columnas
				//tablaTitulos.setWidthPercentage(anchoTabla);
				tablaTitulos.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
			    
			    // fila que contiene el titulo EMPRESA y NIT
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_BIMESTRE, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_CONCEPTO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene la EMPRESA y NIT arrojados por la base de datos 
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_MONTO_TOTAL, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_VALOR_BASE, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el titulo NOMBRE
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_TASA, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el titulo NOMBRE
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_VALOR_RETENIDO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);

			    documento.add(tablaTitulos);
			    
			    // tabla que contiene los valores de los conceptos traidos de la BD
			    PdfPTable tabla = new PdfPTable(6);
				tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

				for (Iterator<ReteIcaDTO> iterator = lReteIca.iterator(); iterator.hasNext();) {
					ReteIcaDTO reteIcaDTO = (ReteIcaDTO) iterator.next();
					// Agregar los diferentes conceptos
					ciudad = reteIcaDTO.getCiudad();
					cell = new PdfPCell(new Paragraph( reteIcaDTO.getBimestre(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER); 
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( reteIcaDTO.getConcepto(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( UtilidadesVarias.formatoDeMil( reteIcaDTO.getMontoTotal()), fontConceptos));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteIcaDTO.getValorBase() ), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  reteIcaDTO.getTasa()  + "%" , fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteIcaDTO.getValorRetenido() ) , fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );

					valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteIcaDTO.getValorBase() );
					valorRetenido = valorRetenido + Integer.parseInt(  reteIcaDTO.getValorRetenido() );
				}	
				setTotalSometidoRetencion( String.valueOf( valorSometidoRetencion ) );
				setMontoTotalRetencion(String.valueOf( valorRetenido ) );
				String valorRetenidoLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorRetenido ).toUpperCase() ;
				String valorSometidoRetencionLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorSometidoRetencion ).toUpperCase() ;
				String vRetenido = UtilidadesVarias.formatoDeMil( String.valueOf( valorRetenido ) );
				String vSometidoRetencion = UtilidadesVarias.formatoDeMil( String.valueOf( valorSometidoRetencion ) );
				tabla.addCell(space);
				tabla.addCell(space);	
				documento.add(tabla);
					
				// tabla que contiene las leyendas finales del documento
				PdfPTable tablaFooter = new PdfPTable(1);
				tablaFooter.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Paragraph());
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell( cell);
				tablaFooter.addCell(space);
				
				// leyenda que contiene el pago sometido a retencion
				ParametricaDTO leyenda01 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEICA_01 );
				String sLeyenda01 = leyenda01.getDescripcion1().replaceAll("#valorSometidoRetencionLetras", valorSometidoRetencionLetras);
				String leyendaRenta01 = sLeyenda01.replaceAll("#valorSometidoRetencion", vSometidoRetencion);
				cell = new PdfPCell(new Paragraph(leyendaRenta01, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);

				// leyenda que contiene los valores por los cuales se le efectuo retencion
				ParametricaDTO leyenda02 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEICA_02 );
				String sLeyenda02 = leyenda02.getDescripcion1().replaceAll("#valorLetrasRetenido", valorRetenidoLetras );
				String leyendaRenta02 = sLeyenda02.replaceAll("#valorRetenido", vRetenido);
				cell = new PdfPCell(new Paragraph(leyendaRenta02, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				// leyenda ciudad de retencion
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CIUDAD_RETENCION +  " " + ciudad, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				// leyenda articulo del certificado reteIca
				ParametricaDTO leyendaArticulo = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEICA_ARTICULO );
				cell = new PdfPCell(new Paragraph(leyendaArticulo.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);

				// leyenda donde se indica la direccion y telefono del departamento que emite el certificado
				ParametricaDTO leyendaDepartamento = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_DEPARTAMENTO );
				cell = new PdfPCell(new Paragraph(leyendaDepartamento.getDescripcion1(), fontMayusculaNegrita ));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda sobre la firma
				ParametricaDTO leyendaFirma = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_FIRMA_RETENCIONES );
				cell = new PdfPCell(new Paragraph(leyendaFirma.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				if ( bCopia ) {
					// realiza la insercion de la imagen transparente indicando que es una copia
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				// leyenda fecha de emision certificado
				cell = new PdfPCell(new Paragraph( fechaEmision, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				documento.add(tablaFooter);

				documento.close();
				bEstado = true;
			}
			else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.NO_EXISTE_CERTIFICADO_RETEICA, null ) );
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bEstado;
	}
	
	/**
	 * Este metodo genera el certificado de ReteIva en formato PDF
	 * @param path ruta que indica el espacio fisico del equipo donde quedara almacenado el documento
	 * @return booleano true indica si el documento fue generado correctamente de lo contrario false
	 */
	public boolean generarPDFReteIva( String path ){
		logger.info("ingreso metodo ContabilidadBean:generarPDFReteIva");
		FacesContext context = FacesContext.getCurrentInstance();
		List<ReteIvaDTO> lReteIva = new ArrayList<ReteIvaDTO>();
		boolean bEstado = false;
		
		try {
			//codigoTercero 		= "9000276190";
			//String nit 			= "89939399393-2";
			//String proveedor 	= "VANIPLAST S.A";
			
			codigoTercero			= getUsuarioAutenticado().getCodigo();
			System.out.println("codigo ********** " + getUsuarioAutenticado().getCodigo());
			//String nit 			= "86620862";
			String nit				= getUsuarioAutenticado().getIdentificacion();
			System.out.println("Identificacion ********** " + getUsuarioAutenticado().getIdentificacion());
			//String proveedor 		= "DIAZ RAMOS ANTONIO RAFAEL";
			String proveedor 		= getUsuarioAutenticado().getNombre();
			System.out.println("Nombre ********** " + getUsuarioAutenticado().getNombre());
			
			// NIT y nombre de la empresa
			TNomConfig tConfig = servicioNomina.obtenerInfoEmpresa( ConstantesPEP.ID_PROQUINAL );
			//String nombreEmpresa = tConfig.getDDescripcion();
			String nitEmpresa     = tConfig.getDNit();
			
			lReteIva = servicioContabilidad.certificadoReteIva(codigoTercero, anoFiscal, bimestreInicial, bimestreFinal);
			boolean bCopia = servicioContabilidad.validarDuplicidadCertificados(codigoTercero, anoFiscal, ConstantesPEP.CERTIFICADO_RETEIVA, bimestreInicial, bimestreFinal);
			
			if ( lReteIva.size() > 0 ) {
				int valorRetenido = 0;
				int valorSometidoRetencion = 0;
				//String fechaEmision = ConstantesPEP.CIUDAD_EMISION_CERTIFICADO + " " + UtilidadesFecha.formatDateInSpanish( new Date() );
				String fechaEmision = ConstantesPEP.CIUDAD_EMISION_CERTIFICADO + " " + UtilidadesVarias.obtenerFechaEmisionBimestre(bimestreFinal); 
				Document documento = new Document(PageSize.A4, ConstantesPEP.MARGEN_IZQUIERDA_RETENCIONES, ConstantesPEP.MARGEN_DERECHA_RETENCIONES, ConstantesPEP.MARGEN_SUPERIOR_RETENCIONES, ConstantesPEP.MARGEN_INFERIOR_RETENCIONES); //tamaño de la página,left, right, top, botton;
				
				// fuente para los titulos
				Font fontTitulos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontTitulos.setSize(9);
				
				// fuente para los conceptos
				Font fontConceptos = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontConceptos.setSize(8);
				
				// fuente para la leyendas
				Font fontLeyendas = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL);
				fontLeyendas.setSize(9);
				
				// fuente para las leyendas con negrilla
				Font fontLeyendasNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontLeyendasNegrita.setSize(9);
				
				// fuente para la leyenda Departamento
				Font fontMayusculaNegrita = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontMayusculaNegrita.setSize(8);
				
				// fuente para el titulo NIT
				Font fontNIT = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
				fontNIT.setSize(8);
				
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
				FileOutputStream ficheroPdf = new FileOutputStream( path );
		
				// Se asocia el documento al OutputStream y se indica que el espaciado entre
				// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento

				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				// Se abre el documento.
				documento.open();
				// realiza la insercion de la imagen transparente indicando que es una copia
				//if ( logCertificado.size() > 0 ) {
				if ( bCopia ) {
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				// se agrega el logo de la empresa
				String logoPqnRuta = servicioYcontent.obtenerLogoProquinal();
			    Image logo = Image.getInstance(logoPqnRuta);
			    logo.setAlignment(Image.LEFT);
			    logo.scaleAbsolute(90, 85);
			    documento.add(logo);
			    
			    // TABLA 1. se crea la tabla que contiene el NIT de la empresa
			    PdfPTable table;
			    PdfPCell cell = null;
			 	table = new PdfPTable(1);
				table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
				table.setHorizontalAlignment(Element.ALIGN_LEFT);

				// fila que contiene el NIT
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NIT_CL + " " + nitEmpresa, fontNIT));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setBorderColor(BaseColor.WHITE);
				cell.setPaddingLeft(8f); // para que no quede el texto pegado a la tabla lo separamos 8 px;
				table.addCell(cell);
				
				documento.add(table); 
			    
				PdfPCell space;
		        space = new PdfPCell();
		        space.setBorder(Rectangle.NO_BORDER);
		        space.setColspan(2);
		        space.setFixedHeight( ConstantesPEP.ESPACIO_LEYENDAS );
				
				PdfPTable tablaEncabezado = new PdfPTable(1);
				tablaEncabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_NORMAS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CERTIFICAMOS, fontLeyendasNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaEncabezado.addCell( cell);
				tablaEncabezado.addCell(space);
				
				String bimestres = UtilidadesVarias.obtenerRangoBimestres(bimestreInicial, bimestreFinal);
				ParametricaDTO leyendaEncabezado = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_ENCABEZADO_RETEIVA );
				String sLeyendaEncabezadoBimestres = leyendaEncabezado.getDescripcion1().replaceAll("#bimestres", bimestres );
				String sLeyendaEncabezado = sLeyendaEncabezadoBimestres.replaceAll("#periodoFiscal", anoFiscal );
				String sLeyendaEncabezadoNit = sLeyendaEncabezado.replaceAll("#nit", nit );
				String sLeyendaEncabezadoProveedor = sLeyendaEncabezadoNit.replaceAll("#proveedor", proveedor );
				cell = new PdfPCell(new Paragraph(sLeyendaEncabezadoProveedor , fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaEncabezado.addCell(cell);
				tablaEncabezado.addCell(space);
				
				documento.add(tablaEncabezado);
				
				// tabla que contiene los titulos de los conceptos
				PdfPTable tablaTitulos = new PdfPTable(6); // se crea la tabla con los anchos especificos en sus 2 columnas
				//tablaTitulos.setWidthPercentage(anchoTabla);
				tablaTitulos.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
			    
			    // fila que contiene el titulo EMPRESA y NIT
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_BIMESTRE, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_CONCEPTO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene la EMPRESA y NIT arrojados por la base de datos 
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_MONTO_SIN_IVA, fontTitulos));
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);
			    
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_IVA_GENERADO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el titulo NOMBRE
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_TASA, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    tablaTitulos.addCell(cell);
			    
			    // fila que contiene el titulo NOMBRE
			    cell = new PdfPCell(new Paragraph(ConstantesPEP.CONCEPTOS_VALOR_RETENIDO, fontTitulos));
			    cell.setBorder(Rectangle.NO_BORDER); 
			    tablaTitulos.addCell(cell);

			    documento.add(tablaTitulos);
			    
			 // tabla que contiene los valores de conceptos traidos de la BD
			    PdfPTable tabla = new PdfPTable(6);
				tabla.setHorizontalAlignment(Element.ALIGN_LEFT);

				for (Iterator<ReteIvaDTO> iterator = lReteIva.iterator(); iterator.hasNext();) {
					ReteIvaDTO reteIvaDTO = (ReteIvaDTO) iterator.next();
					// Agregar los diferentes conceptos
					ciudad = reteIvaDTO.getCiudad();
					cell = new PdfPCell(new Paragraph( reteIvaDTO.getBimestre(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER); 
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( reteIvaDTO.getConcepto(), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( UtilidadesVarias.formatoDeMil( reteIvaDTO.getMontoTotal() ) , fontConceptos));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteIvaDTO.getIvaGenerado() ), fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph( reteIvaDTO.getTasa() + "%" , fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					tabla.addCell( cell );
					cell = new PdfPCell(new Paragraph(  UtilidadesVarias.formatoDeMil( reteIvaDTO.getValorRetenido() ) , fontConceptos ));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tabla.addCell( cell );

					valorSometidoRetencion = valorSometidoRetencion + Integer.parseInt( reteIvaDTO.getIvaGenerado() );
					valorRetenido = valorRetenido + Integer.parseInt( reteIvaDTO.getValorRetenido() );
				}	
				setTotalSometidoRetencion( String.valueOf( valorSometidoRetencion ) );
				setMontoTotalRetencion(String.valueOf( valorRetenido ) );
				String valorRetenidoLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorRetenido ).toUpperCase() ;
				String valorSometidoRetencionLetras = NumerosLetras.convertirLetrasCantidades( ( long ) valorSometidoRetencion ).toUpperCase() ;
				String vRetenido = UtilidadesVarias.formatoDeMil( String.valueOf( valorRetenido ) );
				String vSometidoRetencion = UtilidadesVarias.formatoDeMil( String.valueOf( valorSometidoRetencion ) );
				
				tabla.addCell(space);
				tabla.addCell(space);
				documento.add(tabla);
					
				// tabla que contiene las leyendas finales del documento
				PdfPTable tablaFooter = new PdfPTable(1);
				tablaFooter.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell = new PdfPCell(new Paragraph());
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell( cell);
				tablaFooter.addCell(space);
				
				// leyenda que contiene el pago sometido a retencion
				ParametricaDTO leyenda01 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEIVA_01 );
				String sLeyenda01 = leyenda01.getDescripcion1().replaceAll("#valorLetrasRetenido", valorSometidoRetencionLetras);
				String leyendaRenta01 = sLeyenda01.replaceAll("#valorRetenido", vSometidoRetencion);
				cell = new PdfPCell(new Paragraph(leyendaRenta01, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);

				// leyenda que contiene los valores por los cuales se le efectuo retencion
				ParametricaDTO leyenda02 = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEIVA_02 );
				String sLeyenda02 = leyenda02.getDescripcion1().replaceAll("#valorSometidoRetencionLetras", valorRetenidoLetras);
				String leyendaRenta02 = sLeyenda02.replaceAll("#valorSometidoRetencion", vRetenido);
				cell = new PdfPCell(new Paragraph(leyendaRenta02, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER); 
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				// leyenda ciudad de retencion
				cell = new PdfPCell(new Paragraph( ConstantesPEP.LEYENDA_CIUDAD_RETENCION + " " + ciudad, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda articulo del certificado reteIva					
				ParametricaDTO leyendaArticulo = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_RETEIVA_ARTICULO );
				cell = new PdfPCell(new Paragraph(leyendaArticulo.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				// leyenda donde se indica la direccion y telefono del departamento que emite el certificado
				ParametricaDTO leyendaDepartamento = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_DEPARTAMENTO );
				cell = new PdfPCell(new Paragraph(leyendaDepartamento.getDescripcion1(), fontMayusculaNegrita));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				// leyenda sobre la firma
				ParametricaDTO leyendaFirma = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_FIRMA_RETENCIONES );
				cell = new PdfPCell(new Paragraph(leyendaFirma.getDescripcion1(), fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
				
				if ( bCopia ) {
					// realiza la insercion de la imagen transparente indicando que es una copia al final de la pagina
					ParametricaDTO rutaImagenCopia = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_COPIA_DOCUMENTOS);
					Image img = Image.getInstance( rutaImagenCopia.getPathImagen( ));
				    img.setTransparency(new int[]{ 0xF0, 0xFF });
				    img.setAbsolutePosition(-40, 180);
				    documento.add(img);
				}
				
				// leyenda fecha de emision certificado
				cell = new PdfPCell(new Paragraph( fechaEmision, fontLeyendas));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorderWidthTop(0); 
				cell.setBorderWidthLeft(0);
				cell.setBorder(Rectangle.NO_BORDER);
				tablaFooter.addCell(cell);
				tablaFooter.addCell(space);
					
				documento.add(tablaFooter);

				documento.close();
				bEstado = true;
			}
			else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.NO_EXISTE_CERTIFICADO_RETEIVA, null ) );
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bEstado;
	}
	
	/**
	 * Este metodo genera la ruta fisica donde sera almacenado el certificado ReteFuente e invoca el metodo que genera el certificado
	 * @return String regla de navegacion
	 */
	public String actionCertificadoReteFuente(){
		logger.info("ingreso metodo ContabilidadBean:actionCertificadoReteFuente");
		// obtener variables por sesion de la autenticacion del usuario 
		//codigoTercero 		= "202912852";
		codigoTercero 			= getUsuarioAutenticado().getCodigo();
		String nombreUsuario	= getUsuarioAutenticado().getNombre().toUpperCase().trim();
		try {

			String fileName = ConstantesPEP.PREFIJO_PDF_RETEFUENTE + UtilidadesVarias.crearSemilla(anoFiscal, nombreUsuario);
			String path     = ConstantesPEP.RUTA_FISICA_SERVIDOR + fileName;
			String url      = ConstantesPEP.URL_EXTERNA + fileName;
			boolean b = generarPDFRetefuente( path );
			String fechaActual = UtilidadesFecha.getDateDDMMYYYYWithLess( new Date() );
		
			if ( b ) {
				java.sql.Date fechaSol = UtilidadesFecha.dateUtilToSqlDate( fechaActual );
				ContabilidadLogCert contabilidadLogCert = new ContabilidadLogCert();
				contabilidadLogCert.setCodigoTercero( codigoTercero );
				contabilidadLogCert.setTipoCertificado( ConstantesPEP.CERTIFICADO_RETEFUENTE );
				contabilidadLogCert.setPeriodoFiscal( anoFiscal );
				contabilidadLogCert.setFechaSolicitud( fechaSol );
				contabilidadLogCert.setEstado( ConstantesPEP.ACTIVO );
				
				servicioContabilidad.insertarSolicitudCertificadosRetenciones(contabilidadLogCert);
			
				FacesContext.getCurrentInstance().getExternalContext().redirect( url );
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Este metodo genera la ruta fisica donde sera almacenado el certificado ReteIca e invoca el metodo que genera el certificado
	 * @return String regla de navegacion
	 */
	public String actionCertificadoReteIca(){
		logger.info("ingreso metodo ContabilidadBean:actionCertificadoReteIca");
		FacesContext context = FacesContext.getCurrentInstance();
		boolean bBimestres 	= validarRangosBimestres(bimestreInicial, bimestreFinal);
		codigoTercero 			= getUsuarioAutenticado().getCodigo();
		String nombreUsuario	= getUsuarioAutenticado().getNombre().toUpperCase().trim();
		if ( bBimestres )
		{
			boolean bValidarFecha = validarFechaEmisionCertificado( bimestreFinal );
			if ( bValidarFecha ) 
			{
				String fileName = ConstantesPEP.PREFIJO_PDF_RETEICA + UtilidadesVarias.crearSemilla(anoFiscal, nombreUsuario);
				String path     = ConstantesPEP.RUTA_FISICA_SERVIDOR + fileName;
				String url      = ConstantesPEP.URL_EXTERNA + fileName;
				
				String fechaActual = UtilidadesFecha.getDateDDMMYYYYWithLess( new Date() );
				// valida que el pdf se halla generado correctamente dentro del servidor
				boolean b = generarPDFReteIca( path );
				try {
					if ( b ) 
					{
						java.sql.Date fechaSol = UtilidadesFecha.dateUtilToSqlDate( fechaActual );
						ContabilidadLogCert contabilidadLogCert = new ContabilidadLogCert();
						contabilidadLogCert.setCodigoTercero( codigoTercero );
						contabilidadLogCert.setTipoCertificado( ConstantesPEP.CERTIFICADO_RETEICA );
						contabilidadLogCert.setPeriodoFiscal( anoFiscal );
						contabilidadLogCert.setFechaSolicitud( fechaSol );
						contabilidadLogCert.setBimestreInicial( Integer.parseInt( bimestreInicial ) );
						contabilidadLogCert.setBimestreFinal( Integer.parseInt( bimestreFinal ) );
						contabilidadLogCert.setEstado( ConstantesPEP.ACTIVO );
						
						servicioContabilidad.insertarSolicitudCertificadosRetenciones(contabilidadLogCert);
						FacesContext.getCurrentInstance().getExternalContext().redirect( url );
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.ERROR_FECHA_EMISION_CERTIFICADO, null ) );
			}
		}
		else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.ERROR_VALIDACION_BIMESTRES, null ) );
		}
		return null;
	}
	/**
	 * Este metodo genera la ruta fisica donde sera almacenado el certificado ReteIva e invoca el metodo que genera el certificado
	 * @return String regla de navegacion
	 */
	public String actionCertificadoReteIva(){
		logger.info("ingreso metodo ContabilidadBean:actionCertificadoReteIva");
		FacesContext context = FacesContext.getCurrentInstance();
		boolean bBimestres = validarRangosBimestres(bimestreInicial, bimestreFinal);
		codigoTercero 		= getUsuarioAutenticado().getCodigo();
		String nombreUsuario	= getUsuarioAutenticado().getNombre().toUpperCase().trim();
		if ( bBimestres )
		{
			boolean bValidarFecha = validarFechaEmisionCertificado( bimestreFinal );
			if ( bValidarFecha ) 
			{
				String fileName = ConstantesPEP.PREFIJO_PDF_RETEIVA + UtilidadesVarias.crearSemilla(anoFiscal, nombreUsuario);
				String path     = ConstantesPEP.RUTA_FISICA_SERVIDOR + fileName;
				String url      = ConstantesPEP.URL_EXTERNA + fileName;
	
				String fechaActual = UtilidadesFecha.getDateDDMMYYYYWithLess( new Date() );
				boolean b = generarPDFReteIva( path );
				
				try {
					if ( b ) {
						java.sql.Date fechaSol = UtilidadesFecha.dateUtilToSqlDate( fechaActual );
						ContabilidadLogCert contabilidadLogCert = new ContabilidadLogCert();
						contabilidadLogCert.setCodigoTercero( codigoTercero );
						contabilidadLogCert.setTipoCertificado( ConstantesPEP.CERTIFICADO_RETEIVA );
						contabilidadLogCert.setPeriodoFiscal( anoFiscal );
						contabilidadLogCert.setFechaSolicitud( fechaSol );
						contabilidadLogCert.setBimestreInicial( Integer.parseInt( bimestreInicial ) );
						contabilidadLogCert.setBimestreFinal( Integer.parseInt( bimestreFinal ) );
						contabilidadLogCert.setEstado( ConstantesPEP.ACTIVO );
						
						servicioContabilidad.insertarSolicitudCertificadosRetenciones(contabilidadLogCert);
						FacesContext.getCurrentInstance().getExternalContext().redirect( url );
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.ERROR_FECHA_EMISION_CERTIFICADO, null ) );
			}	
		}
		else {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, ContabilidadAction.ERROR_VALIDACION_BIMESTRES, null ) );
		}
				return null;
	}
	
	/**
	 * Este metodo valida que los rangos de bimestres establecidos esten acordes a la logica
	 * @param bimestreInicial
	 * @param bimestreFinal
	 * @return booleano que indica si el bimestre final e inicial estan dentro de los rangos establecidos
	 */
	public boolean validarRangosBimestres(String bimestreInicial, String bimestreFinal){
		logger.info("ingreso metodo ContabilidadBean:validarRangosBimestres");
		int bInicial = Integer.parseInt( bimestreInicial );
		int bFinal = Integer.parseInt( bimestreFinal );
		if ( bInicial > bFinal ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Este metodo validad si la fecha actual es un parametro de ingreso valido para la generacion del certificado ReteIca o ReteIva
	 * @param bimestreFinal
	 * @return booleano que indica si la fecha es aceptable o no
	 */
	public boolean validarFechaEmisionCertificado(String bimestreFinal){
		logger.info("ingreso metodo ContabilidadBean:validarRangosBimestres");
		Date d = new Date();
		System.out.println("fecha actual " + d);
		Date dFechaEmision = UtilidadesVarias.obtenerFechaEmisionCertificado( bimestreFinal );
		System.out.println("fecha emision " + dFechaEmision);
		if ( d.after( dFechaEmision) ) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		try {
		PdfReader reader = new PdfReader("C:/certificado.pdf");
	      int n = reader.getNumberOfPages();

	      // Create a stamper that will copy the document to a new file
	      PdfStamper stamp = new PdfStamper(reader, 
	        new FileOutputStream("C:/myPDF.pdf"));
	      int i = 1;
	      PdfContentByte under;
	      Image img = Image.getInstance("C:/copia.jpg");
	      img.setTransparency(new int[]{ 0xF0, 0xFF });
	      img.setAbsolutePosition(-40, 180);
	      
	      while (i < n) 
	      {
	        // Watermark under the existing page
	        under = stamp.getUnderContent(i);
	        under.addImage(img);
	    
	        i++;
	      }
	    
	      stamp.close();

	    }
	    catch (Exception de) 
	    {}


	  }

	private ArrayList <SelectItem> bimestres = null;
	private ArrayList <SelectItem> lAnoFiscal = null;
	private ArrayList <SelectItem> lAnoFiscalReteFuente = null;
	private static final Logger logger = Logger.getLogger(ContabilidadBean.class);
	
}
