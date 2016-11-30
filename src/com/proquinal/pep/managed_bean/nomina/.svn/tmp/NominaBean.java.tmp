package com.proquinal.pep.managed_bean.nomina;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.proquinal.pep.base.UsuarioProquinalBean;
import com.proquinal.pep.entidades.nomina.NominaLogCert;
import com.proquinal.pep.entidades.nomina.NominaSolVac;
import com.proquinal.pep.entidades.nomina.TNomCargo;
import com.proquinal.pep.entidades.nomina.TNomConfig;
import com.proquinal.pep.entidades.nomina.dto.DeduccionesEmpleadoDTO;
import com.proquinal.pep.entidades.nomina.dto.DevengosEmpleadoDTO;
import com.proquinal.pep.entidades.nomina.dto.EncabezadoCertificadoLaboralDTO;
import com.proquinal.pep.entidades.nomina.dto.EncabezadoExtractoNominaDTO;
import com.proquinal.pep.entidades.nomina.dto.ListarSolicitudesVacacionesDTO;
import com.proquinal.pep.entidades.nomina.dto.LogCertificadoLaboralDTO;
import com.proquinal.pep.entidades.nomina.dto.SecuenciasEmpleadoDTO;
import com.proquinal.pep.entidades.nomina.dto.SolicitarVacacionesDTO;
import com.proquinal.pep.entidades.nomina.dto.SolicitarVacacionesInfoJefeDTO;
import com.proquinal.pep.entidades.ycontent.dto.ParametricaDTO;
import com.proquinal.pep.fwk.constantes.ConstantesPEP;
import com.proquinal.pep.fwk.constantes.ConstantesPEPParametricas;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.fwk.jms.EnvioCorreo;
import com.proquinal.pep.fwk.utilidades.UtilidadesFecha;
import com.proquinal.pep.fwk.utilidades.UtilidadesVarias;
import com.proquinal.pep.fwk.vo.MensajeCorreoVO;
import com.proquinal.pep.negocioServicio.nomina.IServicioNomina;
import com.proquinal.pep.negocioServicio.ycontent.IServicioYcontent;

public class NominaBean extends UsuarioProquinalBean {
	
	private static final long serialVersionUID = 1L;

	@EJB(mappedName="ejb/ServicioNomina")
	private IServicioNomina servicioNomina;
	
	@EJB(mappedName="ejb/ServicioYcontent")
	private IServicioYcontent servicioYcontent;
	
	private static final Logger logger = Logger.getLogger(NominaBean.class);
	
	/**
	 * variables de prueba para el usuario en sesión 
	 */
	
	String codigoEmpresaSesion = "1";
//	String usuSesion = "yair sanchez matiz".toUpperCase().trim();                String codtraSesion = "200405"; String cargotraSesion = "645";	String cedulaSesion = "80149388"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "juan carlos valencia garcia".toUpperCase().trim();	      String codtraSesion = "200630"; String cargotraSesion = "13";	String cedulaSesion = "79752007"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "angela maria acero cardenas".toUpperCase().trim();       String codtraSesion = "200634"; String cargotraSesion = "350";	String cedulaSesion = "52814235"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "hector francisco torres gutierrez".toUpperCase().trim(); String codtraSesion = "200818"; String argotraSesion = "169";	String cedulaSesion = "19369311"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "claudia valencia sepulveda".toUpperCase().trim();        String codtraSesion = "208115"; String cargotraSesion = "207";	String cedulaSesion = "66953415"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "jairo maldonado hernandez".toUpperCase().trim();         String codtraSesion = "208148"; String cargotraSesion = "181";	String cedulaSesion = "94458535"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "martha yaneth cediel suarez".toUpperCase().trim();       String codtraSesion = "810793"; String cargotraSesion = "204";	String cedulaSesion = "51627840"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
	String usuSesion = "javier alfonso pinzon aguilar".toUpperCase().trim();     String codtraSesion = "820015"; String cargotraSesion = "179";	String cedulaSesion = "19383977"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "andres rojas rodriguez".toUpperCase().trim();            String codtraSesion = "873440"; String cargotraSesion = "170";	String cedulaSesion = "79147541"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "julia isabel montes rodriguez".toUpperCase().trim();     String codtraSesion = "900113"; String cargotraSesion = "196";	String cedulaSesion = "20424218"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "ilbar alirio nova fonseca".toUpperCase().trim();;        String codtraSesion = "910054"; String cargotraSesion = "13";	String cedulaSesion = "79342744"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//  String usuSesion = "hector eduardo acosta ojeda".toUpperCase().trim();       String codtraSesion = "910079"; String cargotraSesion = "239";	String cedulaSesion = "79243532"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "john peter santamaria romero".toUpperCase().trim();      String codtraSesion = "920013"; String cargotraSesion = "14";	String cedulaSesion = "79464788"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "helver artemio olarte benitez".toUpperCase().trim();     String codtraSesion = "920108"; String cargotraSesion = "500";	String cedulaSesion = "80513116"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "angela maria sepulveda lozano".toUpperCase().trim();     String codtraSesion = "940038"; String cargotraSesion = "13";	String cedulaSesion = "39561024"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "clara ines abella castro".toUpperCase().trim();          String codtraSesion = "970003"; String cargotraSesion = "642";	String cedulaSesion = "20948713"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "Ángelá mAria cano arias".toUpperCase().trim();           String codtraSesion = "880012"; String cargotraSesion = "199";	String cedulaSesion = "41904483"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
//	String usuSesion = "nelson dario rico ramirez".toUpperCase().trim();         String codtraSesion = "980005"; String cargotraSesion = "418";	String cedulaSesion = "79398146"; String cedulaCiudadSesion = "Bogotá D.C."; String correoSesion = "econsue@hotmail.com";
	
	public void cargarParametrosSession(){
		System.out.println(" -------------- cargarParametrosSession -----------------");
		//codigoEmpresaSesion = getUsuarioAutenticado().getIdEmpresa();
		codigoEmpresaSesion = "1";
		usuSesion = getUsuarioAutenticado().getNombre().toUpperCase().trim()+ " " + getUsuarioAutenticado().getApellido().toUpperCase().trim() ;     
		codtraSesion = getUsuarioAutenticado().getCodigo(); 
		cargotraSesion = getUsuarioAutenticado().getCargo();	
		cedulaSesion = getUsuarioAutenticado().getIdentificacion(); 
		cedulaCiudadSesion = getUsuarioAutenticado().getCiudad(); 
		correoSesion = "econsue@hotmail.com"; //getUsuarioAutenticado().getEmail();
		System.out.println(usuSesion + " " + codtraSesion + " " + cedulaSesion);
	}
	/**
	 * variables para el manejo de la fecha del servidor
	 */
	
	private Calendar fecha    = Calendar.getInstance();           /** fecha actual del servidor */
	
	private int dia           = fecha.get(Calendar.DAY_OF_MONTH); /** dia actual del servidor   */
	
	private int mes           = fecha.get(Calendar.MONTH) + 1;    /** mes actual del servidor   */
	                                                              /** + 1 porque el rango de meses arrojado por la clase Calendar va de 0 a 12*/
	
	private int anio          = fecha.get(Calendar.YEAR);         /** año actual del servidor   */
	
	/**
	 * variables del requerimiento extracto de nómina
	 */
	
	private String periodoExtracto;
	
	private ArrayList <SelectItem> elemPeriodoExtracto  = null;
	
	/**
	 * variables del requerimiento certificado laboral
	 */
	
	private String horasExtrasSelect;
	
	private String comisionSelect;
	
	private String bonificacionSelect;
	
	private String dirigido;
	
	private ArrayList <SelectItem> elemHE  = null;
	
	private ArrayList <SelectItem> elemCom = null;
	
	private ArrayList <SelectItem> elemBon = null;
	
	private String[] OPCIONES              = {ConstantesPEP.NO, ConstantesPEP.SI};
	
	/**
	 * variables del requerimiento log certificado laboral
	 */
	
	String orderBy;
	
	private ArrayList <SelectItem> elemOrderBy  = null;
	
	/** String      "dd/MM/yyyy" - log certificado laboral */
	private String fechaIniCad  = UtilidadesVarias.ceroIzquierda(dia) + "/" + UtilidadesVarias.ceroIzquierda(mes) + "/" + String.valueOf(anio); 
	
	/** String      "dd/MM/yyyy" - log certificado laboral */
	private String fechaFinCad  = UtilidadesVarias.ceroIzquierda(dia) + "/" + UtilidadesVarias.ceroIzquierda(mes) + "/" + String.valueOf(anio);
	
	/** Date Format "dd/MM/yyyy" - log certificado laboral */
	private Date fechaIniDate = UtilidadesFecha.getDateDDMMYYYY(fechaIniCad);
	
	/** Date Format "dd/MM/yyyy" - log certificado laboral */
	private Date fechaFinDate = UtilidadesFecha.getDateDDMMYYYY(fechaFinCad);
	
	private List<LogCertificadoLaboralDTO> logCertificadoLaboral;
	
	/**
	 * variables del requerimiento solicitud de vacaciones
	 */
	private String perDispVac;

	private String perPendVac;
	
	private String anioVac;
	
	private String mesVac;
	
	private String diaVac;
	
	private String nombreJefe;
	
	private String correoJefe;
	
	private String cargoJefe;
	
	private String bodyMail;
	
	private int mesSelVac = 0;       /** variable para evento onchange del mes */
	
	private int diaIniVac = 1;       /** variable para cargar los días de cada mes en el select*/
	
	private int mesIniVac = mes;     /** primer mes a cargar en el select, será el mes actual*/
	
	private int mesFinVac = mes + 1; /** último mes a cargar en el select, será el mes siguiente del actual*/
	
	/** arreglo que contiene los años del select */
	private String[] vecAnioVac = {String.valueOf(anio), String.valueOf(anio + 1)};
	
	private ArrayList <SelectItem> elemAnioVac = null;

	private ArrayList <SelectItem> elemMesVac = null;
	
	private ArrayList <SelectItem> elemDiaVac = null;

	/**
	 * variables del requerimiento aprobar solicitud de vacaciones
	 */
	
	private String countSolicitudesAprobadas; 
	
	private String countSolicitudesNoAprobadas;
	
	private String textoSolicitudesAprobadas;
	
	private String textoSolicitudesNoAprobadas;
	
	private List<ListarSolicitudesVacacionesDTO> logSolicitudVacaciones;
	
	/**
	 * variables para pintar las tablas y las celdas de los pdf
	 */
	
	PdfPTable table;
	
	PdfPCell  cell;
	
	/**
	 * Contructor
	 */
	public NominaBean() {
		
		logger.info("Ingreso a la clase NominaBean");
		System.out.println("Ingreso a la clase NominaBean()");
	}
	
	/**
	 * Postcontructor
	 */
	 @PostConstruct
	public void postConstructor(){
		System.out.println("Ingreso al método: postConstructor()");
		cargarParametrosSession();
		getInfoVacaciones();           /** carge inicial de la información basica para la solicitud de vacaciones */
		listarSolicitudesVacaciones(); /** carge inicial con los registros de las solicitudes de vacaciones para un jefe*/
	}
	
	/**
	 * Método que carga las variables de sesión (prueba)
	 */
	public String getDatosSesion() {
		String table = UtilidadesVarias.datosSesion(usuSesion, codtraSesion, cargotraSesion, cedulaSesion, cedulaCiudadSesion, correoSesion);
		return table;
	}
	
	/**
	 * Método que carga los 12 periodos del año (extracto de nómina)
	 */
	public Collection <SelectItem> getCargarPeriodosExtracto() {
		
		if( elemPeriodoExtracto == null ){
		
			elemPeriodoExtracto = new ArrayList <SelectItem> ();
			
			for ( int i = 1; i <= 12; i++ ) {	
				
				int anioT = anio;
				int mesT = i;
				
				if( mesT >= mes ){	
					anioT = anio - 1;
				}
				
				String value = String.valueOf(anioT) + UtilidadesVarias.ceroIzquierda(i);
				String label = UtilidadesFecha.mesIntToString(i) + " de " + String.valueOf(anioT);
				
				elemPeriodoExtracto.add( new SelectItem(value, label) );
			}
		}
		
		return elemPeriodoExtracto;
	}

	/**
	 * @getter periodoExtracto (extracto de nómina)
	 */
	public String getPeriodoExtracto() {
		return periodoExtracto;
	}
	
	/**
	 * @setter periodoExtracto (extracto de nómina)
	 */
	public void setPeriodoExtracto(String periodoExtracto) {
		this.periodoExtracto = periodoExtracto;
	}
	
	/**
	 * Método que carga las opciones Si y No de las horas extras (certificación laboral)
	 */
	public Collection <SelectItem> getCargarHorasExtras() {
		
		if( elemHE == null ){
		
			elemHE = new ArrayList <SelectItem> ();
				
			for ( int i = 0; i < OPCIONES.length; i++ ) {
				
				elemHE.add( new SelectItem(String.valueOf(i), OPCIONES[i]) );
			}
		}

		return elemHE;
	}

	/**
	 * Método que carga las opciones Si y No de la comisión (certificación laboral)
	 */
	public Collection <SelectItem> getCargarComision() {
		
		if( elemCom == null ){

			elemCom = new ArrayList <SelectItem> ();
				
			for ( int i = 0; i < OPCIONES.length; i++ ) {
					
				elemCom.add( new SelectItem(String.valueOf(i), OPCIONES[i]) );
			}
		}

		return elemCom;
	}
	
	/**
	 * Método que carga las opciones Si y No de la bonificación por gestión (certificación laboral)
	 */
	public Collection <SelectItem> getCargarBonificacion() {
		
		if( elemBon == null ){
			
			elemBon = new ArrayList <SelectItem> ();
				
			for ( int i = 0; i < OPCIONES.length; i++ ) {
				
				elemBon.add( new SelectItem(String.valueOf(i), OPCIONES[i]) );
	
			}
		}
		
		return elemBon;
	}
	
	/**
	 * @getter horasExtrasSelect (certificación laboral)
	 */
	public String getHorasExtrasSelect() {
		return horasExtrasSelect;
	}
	
	/**
	 * @setter horasExtrasSelect (certificación laboral)
	 */
	public void setHorasExtrasSelect(String horasExtrasSelect) {
		this.horasExtrasSelect = horasExtrasSelect;
	}
	
	/**
	 * @getter comisionSelect (certificación laboral)
	 */
	public String getComisionSelect() {
		return comisionSelect;
	}
	
	/**
	 * @setter comisionSelect (certificación laboral)
	 */
	public void setComisionSelect(String comisionSelect) {
		this.comisionSelect = comisionSelect;
	}
	
	/**
	 * @getter bonificacionSelect (certificación laboral)
	 */
	public String getBonificacionSelect() {
		return bonificacionSelect;
	}
	
	/**
	 * @setter bonificacionSelect (certificación laboral)
	 */
	public void setBonificacionSelect(String bonificacionSelect) {
		this.bonificacionSelect = bonificacionSelect;
	}

	/**
	 * @getter dirigido (certificación laboral)
	 */
	public String getDirigido() {
		return dirigido;
	}
		
	/**
	 * @setter dirigido (certificación laboral)
	 */
	public void setDirigido(String dirigido) {
		this.dirigido = dirigido.toUpperCase().trim();
	}
	
	/**
	 * Gets the default timezone of the host server. The timezone is needed by
	 * the convertDateTime for formatting the time dat values.
	 * 
	 * @return timezone for the current JVM (log certificación laboral)
	 */
	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	/**
	 * @getter fechaIniCad (log certificación laboral)
	 */
	public String getFechaIniCad() {
		return fechaIniCad;
	}
	
	/**
	 * @setter fechaIniCad (log certificación laboral)
	 */
	public void setFechaIniCad(String fechaIniCad) {
		this.fechaIniCad = fechaIniCad;
	}
	
	/**
	 * @getter fechaFinCad (log certificación laboral)
	 */
	public String getFechaFinCad() {
		return fechaFinCad;
	}
	
	/**
	 * @setter fechaFinCad (log certificación laboral)
	 */
	public void setFechaFinCad(String fechaFinCad) {
		this.fechaFinCad = fechaFinCad;
	}
	
	/**
	 * @getter fechaIniDate (log certificación laboral)
	 */
	public Date getFechaIniDate() {
		return fechaIniDate;
	}
	
	/**
	 * @setter fechaIniDate (log certificación laboral)
	 */
	public void setFechaIniDate(Date fechaIniDate) {
		this.fechaIniDate = fechaIniDate;
	}
	
	/**
	 * @getter fechaFinDate (log certificación laboral)
	 */
	public Date getFechaFinDate() {
		return fechaFinDate;
	}
	
	/**
	 * @setter fechaFinDate (log certificación laboral)
	 */
	public void setFechaFinDate(Date fechaFinDate) {
		this.fechaFinDate = fechaFinDate;
	}
	
	/**
	 * @setter logCertificadoLaboral (log certificación laboral)
	 */
	public void setLogCertificadoLaboral(
			List<LogCertificadoLaboralDTO> logCertificadoLaboral) {
		this.logCertificadoLaboral = logCertificadoLaboral;
	}
	
	/**
	 * @getter logCertificadoLaboral (log certificación laboral)
	 */
	public List<LogCertificadoLaboralDTO> getLogCertificadoLaboral() {
		return logCertificadoLaboral;
	}
	
	/**
	 * (log certificación laboral)
	 */
	public String getOrderBy() {
		return orderBy;
	}
	
	/**
	 * (log certificación laboral)
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	/**
	 * Método que carga los campos de ordenamiento (log certificación laboral)
	 */
	public Collection <SelectItem> getCargarCamposOrderBy() {
		
		if( elemOrderBy == null ){
		
			elemOrderBy = new ArrayList <SelectItem> ();
			
			for ( int i = 0; i < ConstantesPEP.CAMPOS_TABLA_LOG_CERT_LABEL.length; i++ ) {	
				
				String value = ConstantesPEP.CAMPOS_TABLA_LOG_CERT_VALUE[i];
				String label = ConstantesPEP.CAMPOS_TABLA_LOG_CERT_LABEL[i];
				
				elemOrderBy.add( new SelectItem(value, label) );
			}
		}
		
		return elemOrderBy;
	}
		
	/**
	 * Método que carga la información básica para realizar una solicitud de vacaciones (solicitud de vacaciones)
	 */
	public String getInfoVacaciones(){
		
		System.out.println("Ingreso al método: getInfoVacaciones()");
		
		SolicitarVacacionesDTO InfoVacaciones = new SolicitarVacacionesDTO();
		SolicitarVacacionesInfoJefeDTO InfoJefeVacaciones = new SolicitarVacacionesInfoJefeDTO();

		try {
			
			String fechaCorte = String.valueOf(anio) + UtilidadesVarias.ceroIzquierda(mes) + UtilidadesVarias.ceroIzquierda(dia);
			InfoVacaciones = servicioNomina.obtenerInfoVacaciones(fechaCorte, codtraSesion);
			
			setPerDispVac(InfoVacaciones.getDisponible());
			setPerPendVac(InfoVacaciones.getPendientes());
			
			/** periodo pendiente y periodo disponible del empleado*/
			System.out.println( "--------------------");
			System.out.println( "Periodo disponible : " + InfoVacaciones.getDisponible() );
			System.out.println( " Periodo pendiente : " + InfoVacaciones.getPendientes() );
			System.out.println( "--------------------");
			
			InfoJefeVacaciones = servicioNomina.obtenerInfoJefeVacaciones(codtraSesion);
			
			String email = UtilidadesVarias.concatenarCorreosJefe(InfoJefeVacaciones.getEmailperjefe(), InfoJefeVacaciones.getEmailcorpjefe());
			
			setNombreJefe(InfoJefeVacaciones.getNomjefe());
			setCorreoJefe(email);
			
			/** nombre y correo del jefe del empleado*/
			System.out.println( "-----------------");
			System.out.println( "Nombre del jefe : " + InfoJefeVacaciones.getNomjefe() );
			System.out.println( "Correo del jefe : " + email );
			System.out.println( "-----------------");
		}
		catch ( BusinessException be ) {
			be.printStackTrace();
		}

		return null;
	}
	
	/**
	 * @getter perDispVac (solicitud de vacaciones)
	 */
	public String getPerDispVac() {
		return perDispVac;
	}
	
	/**
	 * @setter perDispVac (solicitud de vacaciones)
	 */
	public void setPerDispVac(String perDispVac) {
		this.perDispVac = perDispVac;
	}
	
	/**
	 * @getter perPendVac (solicitud de vacaciones)
	 */
	public String getPerPendVac() {
		return perPendVac;
	}
	
	/**
	 * @setter perPendVac (solicitud de vacaciones)
	 */
	public void setPerPendVac(String perPendVac) {
		perPendVac = UtilidadesVarias.conevertirPeriodoPendienteVacaciones(perPendVac);
		this.perPendVac = perPendVac;
	}
	
	/**
	 * Método que carga el/los año(s) para dar inicio a las vacaciones (solicitud de vacaciones)
	 */
	public Collection <SelectItem> getCargarAnioVac() {
		
		/** mesSiguiente: mes que le sigue al mes actual*/
		int mesSiguiente  = UtilidadesFecha.getMesSiguiente(fecha);
		
		/** cantAnios: cantidad de años a mostar en el select,
		 *  inicialmente se asume que solo mostrará un solo año
		 */
		int cantAnios = 0;
		
		/** si se está en diciembre y el día es superior a 25
		 *  no se mostrará el mes de diciembre ni tampoco su respectivo año
		 */
		if( ( dia > ConstantesPEP.DIA_FIN_VACACIONES ) && ( mesSiguiente == 1 ) ){
			/** solo se mostará un año y este debe ser el que le sigue al
			 *  año actual, por este motivo se actualiza el primer valor del arreglo
			 */
			vecAnioVac[0] = String.valueOf( anio + 1 );
		}
		else if( mesSiguiente == 1 ){
			/** se verifica si el mes siguiente del mes actual es enero,
			 *  si es así se habilita el siguiente año,
			 *  si el mes siguiente es enero quiere decir que puede pedir las
			 *  vacaciones en diciembre del año actual o en enero del siguiente año
			 */
			cantAnios = 1;
		}
		
		if( elemAnioVac == null ) {
		
			elemAnioVac = new ArrayList <SelectItem> ();
			
			elemAnioVac.add( new SelectItem( "- Año -" ) );
				
			for ( int i = 0; i <= cantAnios; i++ ) {
					
				elemAnioVac.add( new SelectItem( vecAnioVac[i] ) );
			}
		}
			
		return elemAnioVac;
	}
	
	/**
	 * Método que carga los meses para dar inicio a las vacaciones (solicitud de vacaciones)
	 */
	public Collection <SelectItem> getCargarMesVac() {
		
		if( dia > 25 ){
			mesIniVac = mesFinVac;
		}
		
		if( elemMesVac == null ) {
			
			elemMesVac = new ArrayList <SelectItem> ();
			
			elemMesVac.add( new SelectItem( "- Mes -" ) );
			
			for ( int i = mesIniVac; i <= mesFinVac; i++ ) {
				
				String value = UtilidadesVarias.ceroIzquierda(i);
				String label = UtilidadesFecha.mesIntToString(i).substring(0, 3);
				
				elemMesVac.add( new SelectItem(value, label) );
			}
		}
		return elemMesVac;
	}
	
	/**
	 * Método que carga los días para dar inicio a las vacaciones (solicitud de vacaciones)
	 */
	public Collection <SelectItem> getCargarDiaVac() {

		if ( mesSelVac == mes ){
			/** caso 1: cuando se cargen los días del mes actual, no deben cargarse los días anteriores al día de hoy
			 *  mesSelVac == 0         => indica que aún no ha ocurrido el evento onchange del select de los meses
			 *  mesSelVac == mesActVac => indica que ocurrio el evento onchange y se escogió el mes actual
			 */
			diaIniVac = dia;
		}
		else{
		     /** caso 2: cuando se cargen los días del mes siguiente se cargan todos completos*/
			diaIniVac = 1;
		}
		
		if( elemDiaVac == null ) {
			
			elemDiaVac = new ArrayList <SelectItem> ();
			
			elemDiaVac.add( new SelectItem( "- Día -" ) );
			
			for ( int i = diaIniVac; i <= ConstantesPEP.DIA_FIN_VACACIONES; i++ ) {
				
				String value = UtilidadesVarias.ceroIzquierda(i);
				
				elemDiaVac.add( new SelectItem( value ) );

			}
		}
		
		mesSelVac = 0; /** se setea la variable que maneja el evento onchange para la próxima vez que ocurra el evento */
		
		return elemDiaVac;
	}
	
	/**
	 * @getter anioVac (solicitud de vacaciones)
	 */
	public String getAnioVac() {
		return anioVac;
	}
	
	/**
	 * @setter anioVac (solicitud de vacaciones)
	 */
	public void setAnioVac(String anioVac) {
	     this.anioVac = anioVac;
	}
	
	/**
	 * @getter mesVac (solicitud de vacaciones)
	 */
	public String getMesVac() {
		return mesVac;
	}
	
	/**
	 * @setter mesVac (solicitud de vacaciones)
	 */
	public void setMesVac(String mesVac) {
		this.mesVac = mesVac;
	}
	
	/**
	 * @getter diaVac (solicitud de vacaciones)
	 */
	public String getDiaVac() {
		return diaVac;
	}
	
	/**
	 * @setter diaVac (solicitud de vacaciones)
	 */
	public void setDiaVac(String diaVac) {
	    this.diaVac = diaVac;
	}
	
	/**
	 * @getter nombreJefe (solicitud de vacaciones)
	 */
	public String getNombreJefe() {
		return nombreJefe;
	}
	
	/**
	 * @setter nombreJefe (solicitud de vacaciones)
	 */
	public void setNombreJefe(String nombreJefe) {
		this.nombreJefe = nombreJefe;
	}
	
	/**
	 * @getter correoJefe (solicitud de vacaciones)
	 */
	public String getCorreoJefe() {
		return correoJefe;
	}
	
	/**
	 * @setter correoJefe (solicitud de vacaciones)
	 */
	public void setCorreoJefe(String _correoJefe) {
		this.correoJefe =_correoJefe;
	}
	
	/**
	 * @getter cargoJefe (solicitud de vacaciones)
	 */
	public String getCargoJefe() {
		return cargoJefe;
	}
	
	/**
	 * @setter cargoJefe (solicitud de vacaciones)
	 */
	public void setCargoJefe(String cargoJefe) {
		this.cargoJefe = cargoJefe;
	}
	
	/**
	 * @return the bodyMail
	 */
	public String getBodyMail() {
		return bodyMail;
	}

	/**
	 * @param bodyMail the bodyMail to set
	 */
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}

	/**
	 * Método que muestra en pantalla el mes en letras del inicio de vacaciones (solicitud de vacaciones)
	 */
	public String getMesVacString() {
		int i = Integer.parseInt(mesVac);
		String mesString = UtilidadesFecha.mesIntToString(i).toLowerCase();
		return mesString;
	}
	
	/**
	 * Método del evento onchange del select de los meses de la solicitud de vacaciones
	 * metodo encargado de cambiar el select de los días de acuerdo al mes seleccionado (solicitud de vacaciones)
	 */
	public void cambiarDiasVac(ValueChangeEvent value1) throws AbortProcessingException {
		
		String selectedValue = (String) value1.getNewValue();
		
		if(!selectedValue.contains("Mes")){
			mesSelVac = Integer.parseInt(selectedValue);
		}
		else{
			mesSelVac = 13;
		}
		
		/** se setea el array para crear nuevamente el arreglo de los días de un mes */
		elemDiaVac = null;
		
		/** se invoca nuevamente el metodo de carga de días para refrescar al select */
		getCargarDiaVac();
	}
	
	/**
	 * Método que lista las solicitudes de vacaciones (aprobar solicitud de vacaciones)
	 */	
	public String listarSolicitudesVacaciones(){
		
		logger.info("Ingreso listarSolicitudesVacaciones");
		System.out.println("Ingreso al método: listarSolicitudesVacaciones()");
		
		List<ListarSolicitudesVacacionesDTO> lSolicitudes = new ArrayList<ListarSolicitudesVacacionesDTO>();
		
		/** id del cargo del jefe en sesión*/
		System.out.println( "--------------------------------");
		System.out.println( "Id del cargo del jefe en sesión: " + cargotraSesion );
		System.out.println( "--------------------------------");
		
		try {
			
			lSolicitudes = servicioNomina.obtenerSolicitudesVacaciones(cargotraSesion);
			
			List<SelectItem> estadosAprobacion = new ArrayList<SelectItem>();
			
			estadosAprobacion.add(new SelectItem(ConstantesPEP.APROBADO, "Aprobar"));
			estadosAprobacion.add(new SelectItem(ConstantesPEP.NO_APROBADO, "No aprobrar"));
			estadosAprobacion.add(new SelectItem(ConstantesPEP.PENDIENTE, "Pendiente"));
			
			for (ListarSolicitudesVacacionesDTO nominaLogCert : lSolicitudes) {
				
				/** datos listados */
				System.out.println( "--------------------------------");
				System.out.println( "        Código del empleado: " + nominaLogCert.getCodigoEmpleado() );
				System.out.println( "       Nombre del empleado : " + nominaLogCert.getNombreEmpleado());
				System.out.println( "        Fecha de solicitud : " + nominaLogCert.getFechaSolicitud());
				System.out.println( "                   Periodo : " + nominaLogCert.getPeriodo() );
				System.out.println( "           Fecha de inicio : " + nominaLogCert.getFechaInicio());
				System.out.println( "--------------------------------");

				nominaLogCert.setEstadosAprobacion(estadosAprobacion);
			}
			setLogSolicitudVacaciones(lSolicitudes);
		}
		catch ( BusinessException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @getter logSolicitudVacaciones (aprobar solicitud de vacaciones)
	 */
	public List<ListarSolicitudesVacacionesDTO> getLogSolicitudVacaciones() {
		return logSolicitudVacaciones;
	}
	
	/**
	 * @setter logSolicitudVacaciones (aprobar solicitud de vacaciones)
	 */
	public void setLogSolicitudVacaciones(
			List<ListarSolicitudesVacacionesDTO> logSolicitudVacaciones) {
		this.logSolicitudVacaciones = logSolicitudVacaciones;
	}
	
	/**
	 * @getter countSolicitudesAprobadas (aprobar solicitud de vacaciones)
	 */
	public String getCountSolicitudesAprobadas() {
		return countSolicitudesAprobadas;
	}
	
	/**
	 * @setter countSolicitudesAprobadas (aprobar solicitud de vacaciones)
	 */
	public void setCountSolicitudesAprobadas(String countSolicitudesAprobadas) {
		this.countSolicitudesAprobadas = countSolicitudesAprobadas;
	}
	
	/**
	 * @getter countSolicitudesNoAprobadas (aprobar solicitud de vacaciones)
	 */
	public String getCountSolicitudesNoAprobadas() {
		return countSolicitudesNoAprobadas;
	}
	
	/**
	 * @setter countSolicitudesNoAprobadas (aprobar solicitud de vacaciones)
	 */
	public void setCountSolicitudesNoAprobadas(String solicitudesNoAprobadas) {
		this.countSolicitudesNoAprobadas = solicitudesNoAprobadas;
	}
	
	/**
	 * @getter textoSolicitudesAprobadas (aprobar solicitud de vacaciones)
	 */
	public String getTextoSolicitudesAprobadas() {
		return textoSolicitudesAprobadas;
	}
	
	/**
	 * @setter textoSolicitudesAprobadas (aprobar solicitud de vacaciones)
	 */
	public void setTextoSolicitudesAprobadas(String textoSolicitudesAprobadas) {
		this.textoSolicitudesAprobadas = textoSolicitudesAprobadas;
	}
	
	/**
	 * @getter textoSolicitudesNoAprobadas (aprobar solicitud de vacaciones)
	 */
	public String getTextoSolicitudesNoAprobadas() {
		return textoSolicitudesNoAprobadas;
	}
	
	/**
	 * @setter textoSolicitudesNoAprobadas (aprobar solicitud de vacaciones)
	 */
	public void setTextoSolicitudesNoAprobadas(String textoSolicitudesNoAprobadas) {
		this.textoSolicitudesNoAprobadas = textoSolicitudesNoAprobadas;
	}
	
	/**
	 * regla de navegación extracto de nómina
	 */
	public String actionExtractoNomina(){
		
		String fileName = ConstantesPEP.PREFIJO_PDF_EN + UtilidadesVarias.crearSemilla(periodoExtracto, usuSesion);
		String path     = ConstantesPEP.RUTA_FISICA_SERVIDOR + fileName;
		String url      = ConstantesPEP.URL_EXTERNA + fileName;
		generacionPDFExtractoNomina(periodoExtracto, path);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect( url );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * validación de campos para el formulario certificado laboral (generaPdfCertificadoLaboral.jspx)
	 */
	public boolean validaCamposCertificado(){

		FacesContext context = FacesContext.getCurrentInstance();
		String msj = null;
		
		if ( dirigido.equals("") ) {
			msj = ConstantesPEP.ERROR_CAMPO_OBLIGATORIO;
		}

		if( msj != null ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj , null));
			return false;
		}

		return true;		
	}
	
	/**
	 * regla de navegación para el formulario certificado laboral (generaPdfCertificadoLaboral.jspx)
	 */
	public String actionCertificadoLaboral(){
		
		boolean valida = validaCamposCertificado();
		
		if( valida ){
			
			try {
				
				Calendar calendar = Calendar.getInstance();
				java.util.Date currentDate = calendar.getTime();
				java.sql.Date date = new java.sql.Date(currentDate.getTime());
				
				NominaLogCert nominaLogCert = new NominaLogCert();
				
				nominaLogCert.setCodtra( codtraSesion );
				nominaLogCert.setDirigido( dirigido );
				nominaLogCert.setEstado( ConstantesPEP.ACTIVO );
				nominaLogCert.setNomtra( usuSesion );
				nominaLogCert.setFSolCert( date);
				
				boolean b = servicioNomina.insertarSolicitudCertificadoLaboral(nominaLogCert);
				
				/** estado de la insercion de la solicitud del certificedo laboral*/
				System.out.println( "------------------------------");
				System.out.println( "Insercion en NOMINA_LOG_CERT : " + b );
				System.out.println( "------------------------------");
				
				String fileName = ConstantesPEP.PREFIJO_PDF_CL + UtilidadesVarias.crearSemilla("", usuSesion);
				String path = ConstantesPEP.RUTA_FISICA_SERVIDOR + fileName;
				String url = ConstantesPEP.URL_EXTERNA + fileName;
				generacionPDFCertificadoLaboral(path);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect( url );
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * validación de campos para el formulario log de certificado laboral (consultaLogCertificadoLaboral.jspx)
	 */
	public boolean validaCamposLog(){

		FacesContext context = FacesContext.getCurrentInstance();
		String msj = "";
		
		if (fechaIniDate == null){
			msj = msj + ConstantesPEP.ERROR_FECHA_INICIAL + "<br />";
		}
		
		if (fechaFinDate == null){
			msj = msj + ConstantesPEP.ERROR_FECHA_FINAL + "<br />";
		}
		
		if(fechaIniDate != null && fechaFinDate != null){
			if (UtilidadesFecha.restarMeses(fechaIniDate,  fechaFinDate) < 0){
				msj = msj + ConstantesPEP.ERROR_RESTA_FECHAS + "<br />";
			}
		}

		if( msj != "" ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj , null));
			return false;
		}

		return true;
	}
	
	/**
	 * regla de navegación para el formulario log de certificado laboral (consultaLogCertificadoLaboral.jspx)
	 */
	public String actionCertificadoLaboralLog(){
		
		boolean validaFormulario = validaCamposLog();
		
		if( validaFormulario ){
			
			try {

				String fechaICad = UtilidadesFecha.getStringDDMMYYYY(fechaIniDate); 
				String fechaFCad   = UtilidadesFecha.getStringDDMMYYYY(fechaFinDate);
				
				List<LogCertificadoLaboralDTO> lSolicitudes;
				lSolicitudes = servicioNomina.obtenerSolicitudesCertificados(fechaICad, fechaFCad, orderBy);
				setLogCertificadoLaboral(lSolicitudes);
				
			} catch (BusinessException e) { //BusinessException e
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return NominaAction.EXITO;
		}
		else{	
			return null;
		}
	}
	
	/**
	 * regla de navegación para el formulario log de certificado laboral (consultaLogCertificadoLaboralResgistros.jspx)
	 */
	public String actionCertificadoLaboralLogRegistros(){
		
		boolean validaFormulario = validaCamposLog();
		
		if( validaFormulario ){
			
			try {

				String fechaICad = UtilidadesFecha.getStringDDMMYYYY(fechaIniDate); 
				String fechaFCad   = UtilidadesFecha.getStringDDMMYYYY(fechaFinDate);
				
				List<LogCertificadoLaboralDTO> lSolicitudes;
				lSolicitudes = servicioNomina.obtenerSolicitudesCertificados(fechaICad, fechaFCad, orderBy);
				setLogCertificadoLaboral(lSolicitudes);
				
			} catch (BusinessException e) { //BusinessException e
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
		
	/**
	 * validación de campos para el formulario vacaciones (solicitaVacaciones.jspx)
	 */
	public boolean validaCamposVacaciones(){

		FacesContext context = FacesContext.getCurrentInstance();
		String msj = "";
		int cont = 0;
		
		if ( anioVac.contains("Año") ) {
			msj = msj + ConstantesPEP.ERROR_SELECCIONAR_ANIO + ", ";
			cont++;
		}
		if ( mesVac.contains("Mes") ) {
			msj = msj + ConstantesPEP.ERROR_SELECCIONAR_MES + ", ";
			cont++;
		}
		if ( diaVac.contains("Día") ) {
			msj = msj + ConstantesPEP.ERROR_SELECCIONAR_DIA + ", ";
			cont++;
		}
		
		if(cont==1){
			msj = msj.replace(", ", "");
			msj = ConstantesPEP.ERROR_CAMPO_OBLIGATORIO + ": " + msj + ".";
		}
		else if(cont==2){
			msj = msj.substring(0, msj.length() - 2);
			msj = msj.replace(", ", " y ");
			msj = ConstantesPEP.ERROR_CAMPOS_OBLIGATORIOS + ": " + msj + ".";
		}
		else if(cont==3){
			msj = ConstantesPEP.ERROR_CAMPOS_OBLIGATORIOS + ".";
		}
			
		/** validaciones en caso de que se habilite el siguiente año */
		if( elemAnioVac.size() == 3 && !anioVac.contains("Año") && !mesVac.contains("Mes") && !diaVac.contains("Día") ){

			int anioSel = Integer.parseInt(anioVac); /** año seleccionado por pantalla */
			int mesSel = Integer.parseInt(mesVac);   /** mes seleccionado por pantalla */
			int anioSig = anio + 1;                  /** año que le sigue al año actual */
			
			/** validación 1: en caso de que si toma el año actual no puede tomar el mes de enero*/
			if( ( anioSel == anio ) && ( mesSel == 13 ) ){
				msj = msj + ConstantesPEP.ERROR_SELECCION_FECHA + "<br />";
			}
			
			/** validación 2: si toma el año siguiente no puede tomar el mes de diciembre */
			else if( ( anioSel == anioSig ) && ( mesSel == 12 ) ){
				msj = msj + ConstantesPEP.ERROR_INICIO_VACACIONES + "<br />";
			}
		}

		if( msj != "" ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj , null));
			return false;
		}

		return true;
		
	}
	
	/**
	 * regla de navegación para el formulario vacaciones (solicitaVacaciones.jspx)
	 */
	public String actionVacaciones(){
		
		boolean valida = validaCamposVacaciones();
		
		if( valida ){
			return NominaAction.EXITO;
		}
		else{	
			return null;
		}
	}
	
	/**
	 * regla de navegación para el formulario confirmación de vacaciones (solicitaVacacionesConfirmacion.jspx)
	 */
	public String actionVacacionesSi(){
		boolean b = false;
			
		try {
		
			String fechaIniCad = diaVac + "/" + mesVac + "/" + anioVac;
			String fechaSolcad = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(anio);
			
			java.sql.Date fechaIniDate = UtilidadesFecha.dateUtilToSqlDate(fechaIniCad);
			java.sql.Date fechaSolDate = UtilidadesFecha.dateUtilToSqlDate(fechaSolcad);
			
			TNomCargo cargoObject = new TNomCargo();
			cargoObject = servicioNomina.esJefe(cargotraSesion);
			Integer idCargoJefe = cargoObject.getdCargoJefe();
			
			/** id del cargo del jefe para la inserción*/
			System.out.println( "-------------------");
			System.out.println( "Id del cargo del jefe : " + idCargoJefe );
			System.out.println( "-------------------");
			
			NominaSolVac nominaSolVac = new  NominaSolVac();
			nominaSolVac.setCodtra( codtraSesion );
			nominaSolVac.setNomtra( usuSesion );
			nominaSolVac.setCargotra( Integer.parseInt(cargotraSesion) );
			nominaSolVac.setCargojefe( idCargoJefe );
			nominaSolVac.setPeriodo( perDispVac );
			nominaSolVac.setFIniVac( fechaIniDate );
			nominaSolVac.setFSolVac( fechaSolDate );
			nominaSolVac.setEstado( ConstantesPEP.PENDIENTE );
			nominaSolVac.setCorreotra(correoSesion);
			String cedula = UtilidadesVarias.formatoDeMilCedula(cedulaSesion) + " de " + cedulaCiudadSesion;
			nominaSolVac.setCedula(cedula);
			b = servicioNomina.insertarSolicitudVacaciones(nominaSolVac);
			
			if(b){
				
				String accion = "enviarSolicitud";
				String titulo = ConstantesPEP.SOLICITUD_DE_VACACIONES;
				String fechaActual = dia + " de " + UtilidadesFecha.mesIntToString(mes) + " de " + anio;
				String fechaInicial = diaVac + " de " + UtilidadesFecha.mesIntToString(Integer.parseInt(mesVac)) + " de " + anioVac;
				String periodoDisponible = perDispVac;
				String usuario = usuSesion;
				String codigo = codtraSesion;
				
				String cuerpoMsj = UtilidadesVarias.bodyMailVacaciones(accion, titulo, fechaActual, periodoDisponible, fechaInicial, usuario, cedula, codigo, nombreJefe);
				setBodyMail(cuerpoMsj);
				
				/** correo del jefe */
				System.out.println( "-----------------");
				System.out.println( "Correo del jefe : " + correoJefe );
				System.out.println( "-----------------");
				
				correoJefe = "econsue@hotmail.com";
				
//				CorreoDto correo = new CorreoDto();
//				correo.setDestino(correoJefe);
//				correo.setEncabezado(titulo + ConstantesPEP.TEXTO_EMPRESA);
//				correo.setContenido(bodyMail);
//				//EnviarCorreo.enviarCorreo(correo);
				
				MensajeCorreoVO mc = new MensajeCorreoVO();
				mc.setDireccionRemitente("soporte.portal@proquinal.com");
				mc.setDireccionesEnvio(correoJefe);
				mc.setAsunto(titulo + ConstantesPEP.TEXTO_EMPRESA);
				mc.setTextoCorreo( bodyMail );

//				DocAdjuntoVO doc = new DocAdjuntoVO();
//				File ff = new File(path);
//				doc.setByteArray(EnvioCorreo.getBytesFromFile(ff));
//				doc.setNombre(ff.getName());
//				mc.addDocAdjunto(doc);
				System.out.println("envio correo");
				EnvioCorreo.envioCorreo(mc);
				
				/** cuerpo del correo */
				System.out.println( "-----------------");
				System.out.println( "Cuerpo del correo : " + bodyMail );
				System.out.println( "-----------------");
			}
				
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return NominaAction.EXITO;
	}
	
	/**
	 * regla de navegación para el formulario confirmación de vacaciones (solicitaVacacionesConfirmacion.jspx))
	 */
	public String actionBack(){
		
		return NominaAction.CANCELAR; 
		
	}
	
	/**
	 * regla de navegación para el formulario confirmación de vacaciones (solicitaVacacionesConfirmacion.jspx))
	 */
	public String actionVerSolicitud(){
		
		return NominaAction.EXITO; 
		
	}
	
	/**
	 * regla de navegación para aprobar o no aprobar vacaciones (apruebaVacaciones.jspx)
	 */
	public String actionAprobarVacaciones(){
		
		logger.info("Ingreso aprobarVacaciones");
		
		int aprobado = 0;
		int i = 0;
		
		int noAprobado = 0;
		int j = 0;
		
		String textoAprobado = "";
		String textoNoAprobado = "";
		
		try {
			
			ParametricaDTO correo = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.INFORMACION_DEPARTAMENTO_NOMINA );
			String correoNomina = correo.getDescripcion2().trim();
			
			String accion = "";
			String titulo = "";
			String cuerpoMsj = "";
//			CorreoDto correoDTOA = new CorreoDto();
//			CorreoDto correoDTONoA = new CorreoDto();
			
			List<ListarSolicitudesVacacionesDTO> lAprobacionVacaciones = new ArrayList<ListarSolicitudesVacacionesDTO>();
			lAprobacionVacaciones = getLogSolicitudVacaciones();
			
			for (Iterator<ListarSolicitudesVacacionesDTO> iterator = lAprobacionVacaciones.iterator(); iterator.hasNext();) {
				ListarSolicitudesVacacionesDTO solicitudVacacionesDTO = (ListarSolicitudesVacacionesDTO) iterator.next();
				
				if ( !solicitudVacacionesDTO.getEstado().equals(ConstantesPEP.PENDIENTE) ){

					/** listado de solicitud de vacaciones */
					System.out.println( "-------------------");
					System.out.println( "   Id autonumerico : " + solicitudVacacionesDTO.getId() );
					System.out.println( "Codigo del empleado: " + solicitudVacacionesDTO.getId() );
					System.out.println( "Nombre del empleado: " + solicitudVacacionesDTO.getNombreEmpleado() );
					System.out.println( "Fecha de solicitud : " + solicitudVacacionesDTO.getFechaSolicitud() );
					System.out.println( "           Periodo : " + solicitudVacacionesDTO.getPeriodo() );
					System.out.println( "   Fecha de inicio : " + solicitudVacacionesDTO.getFechaInicio() );
					System.out.println( "            Estado : " + solicitudVacacionesDTO.getEstado() );
					System.out.println( "-------------------");

					String usuario = solicitudVacacionesDTO.getNombreEmpleado().toUpperCase();
					String correoUsuario = solicitudVacacionesDTO.getCorreotra();
					String periodoDisponible = solicitudVacacionesDTO.getPeriodo();
					String fechaSolicitud = solicitudVacacionesDTO.getFechaSolicitud();
					String cedula = solicitudVacacionesDTO.getCedula();
					String codigo = solicitudVacacionesDTO.getCodigoEmpleado();
					Date fechaI = solicitudVacacionesDTO.getFechaInicio();
					String fechaInicial = UtilidadesFecha.getStringYYYYMMDD(fechaI);
					fechaInicial = fechaInicial.replace("/", "-") + " ";
					fechaInicial = UtilidadesFecha.getDateTexto(fechaInicial);
					String nombreJefe = usuSesion;
					
					// realiza la actualizacion de los registros
					String fechaCad = UtilidadesFecha.getStringDDMMYYYY(solicitudVacacionesDTO.getFechaInicio());
					boolean b = servicioNomina.aprobarVacaciones(solicitudVacacionesDTO.getEstado(), solicitudVacacionesDTO.getId(), fechaCad );
					
					if ( b ) {
						
						if ( solicitudVacacionesDTO.getEstado().equals(ConstantesPEP.APROBADO) ){
							
							aprobado++;
							textoAprobado = textoAprobado + (++i) + ". " + usuario + "<br />";
							
							accion = "aprobarSolicitud";
							titulo = ConstantesPEP.SOLICITUD_DE_VACACIONES_APROB;
							cuerpoMsj = UtilidadesVarias.bodyMailVacaciones(accion, titulo, fechaSolicitud, periodoDisponible, fechaInicial, usuario, cedula, codigo, nombreJefe);
							setBodyMail(cuerpoMsj);
							
							/** datos del correo de aprobación */
							System.out.println( "------------------------------------");
							System.out.println( "               Correo del empleado : " + correoUsuario );
							System.out.println( "                  Correo de nómina : " + correoNomina );
							System.out.println( "Cuerpo del mail solicitud aprobada : " + bodyMail );
							System.out.println( "------------------------------------");

							/** enviar correo electrónico a nomina con copia al empleado de solicitud fue aprobada */
							try {
//								correoDTOA.setDestino(correoNomina);
//								correoDTOA.setDestinoCC(correoUsuario);
//								correoDTOA.setEncabezado(titulo + ConstantesPEP.TEXTO_EMPRESA);
//								correoDTOA.setContenido(bodyMail);
								//EnviarCorreo.enviarCorreo(correoDTOA);
								
								MensajeCorreoVO mc = new MensajeCorreoVO();
								mc.setDireccionRemitente(correoNomina);
								mc.setDireccionesEnvio(correoUsuario);
								mc.setAsunto(titulo + ConstantesPEP.TEXTO_EMPRESA);
								mc.setTextoCorreo( bodyMail );

								EnvioCorreo.envioCorreo(mc);
							}
							catch (Exception e ){
								e.printStackTrace();
							}

						}
						else if ( solicitudVacacionesDTO.getEstado().equals(ConstantesPEP.NO_APROBADO) ){
							noAprobado++;
							textoNoAprobado = textoNoAprobado + (++j) + ". " + usuario + "<br />";
							
							accion = "noAprobarSolicitud";
							titulo = ConstantesPEP.SOLICITUD_DE_VACACIONES_NO_APROB;
							cuerpoMsj = UtilidadesVarias.bodyMailVacaciones(accion, titulo, fechaSolicitud, periodoDisponible, fechaInicial, usuario, cedula, codigo, nombreJefe);
							setBodyMail(cuerpoMsj);
							
							/** datos del correo de no aprobación */
							System.out.println( "---------------------------------------");
							System.out.println( "                  Correo del empleado : " + correoUsuario );
							System.out.println( "Cuerpo del mail solicitud no aprobada : " + bodyMail );
							System.out.println( "---------------------------------------");

							/** enviar correo electrónico al empleado de solicitud no aprobada */
							try {
//								correoDTONoA.setDestino(correoUsuario);
//								correoDTONoA.setEncabezado(titulo + ConstantesPEP.TEXTO_EMPRESA);
//								correoDTONoA.setContenido(bodyMail);
								//EnviarCorreo.enviarCorreo(correoDTONoA);
								
								MensajeCorreoVO mc = new MensajeCorreoVO();
								mc.setDireccionRemitente(correoNomina);
								mc.setDireccionesEnvio(correoUsuario);
								mc.setAsunto(titulo + ConstantesPEP.TEXTO_EMPRESA);
								mc.setTextoCorreo( bodyMail );

								EnvioCorreo.envioCorreo(mc);
							}
							catch (Exception e ){
								e.printStackTrace();
							}

						}
					}
				}
			}
		}
		catch ( Exception be ) {
			be.printStackTrace();
		}
		
		textoAprobado = "<table><tr><td align='left'>" + textoAprobado + "</td></tr></table>";
		textoNoAprobado = "<table><tr><td align='left'>" + textoNoAprobado + "</td></tr></table>";
		
		setCountSolicitudesAprobadas(String.valueOf(aprobado));
		setCountSolicitudesNoAprobadas(String.valueOf(noAprobado));
		
		setTextoSolicitudesAprobadas(textoAprobado);
		setTextoSolicitudesNoAprobadas(textoNoAprobado);
		
		listarSolicitudesVacaciones(); /** carge inicial con los registros de las solicitudes de vacaciones para un jefe*/
		
		return NominaAction.EXITO;
	}
	
	/**
	 * Método que genera el pdf del extracto de nómina
	 */	
	public String generacionPDFExtractoNomina(String periodo, String rutaFisica){
		
		// fuente para el titulo COMPROBANTE DE PAGO NOMINA
		Font fontTituloPpal = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontTituloPpal.setSize(15);

		// fuente para los titulos EMPRESA, NIT, NOMBRE, CODIGO, IDENTIFICACION, FECHA DE PAGO, PERIODO DE PAGO, DEPENDENCIA, SUELDO MES, DEVENGADOS y DEDUCCIONES
		Font fontTituloDatosEmp = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontTituloDatosEmp.setSize(10);

		// fuente para los registros del empleado arrojados por la base de datos
		Font fontRegistrosBD = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
		fontRegistrosBD.setSize(12);

		// fuente para los titulos DESCRIPCION, No. HORAS y VALOR
		Font fontTituloDetalle = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontTituloDetalle.setSize(8);
		fontTituloDetalle.setColor(BaseColor.WHITE);

		// fuente para la descripción del detalle de cada devengos y cada deducción
		Font fontDetalleDesc = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
		fontDetalleDesc.setSize(7);

		// fuente para los titulos TOTAL DEVENGADO, TOTAL DEDUCCIONES y NETO A PAGAR
		Font fontTituloPie = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontTituloPie.setSize(10);
		fontTituloPie.setColor(BaseColor.WHITE);

		//step 1: creation of a document-object
		Document document = new Document(PageSize.A4, ConstantesPEP.LEFT_EN, ConstantesPEP.RIGHT_EN, ConstantesPEP.TOP_EN, ConstantesPEP.BOTTON_EN);

		try {
			
			// NIT y nombre de la empresa
			TNomConfig tConfig = servicioNomina.obtenerInfoEmpresa(codigoEmpresaSesion);
			String empresa = tConfig.getDDescripcion();
			String nit     = tConfig.getDNit();
			
			// secuencia del empleado
			SecuenciasEmpleadoDTO seDTO = servicioNomina.obtenerSecuenciasEmpleado(periodoExtracto, codtraSesion);
			String secuencia         = seDTO.getSecuencia().toString();
			String codigoNomina      = seDTO.getCodigoEmpleado();
			String descSecuencia     = seDTO.getDescripcion();
			String periodo_corriente = seDTO.getPeriodoCorriente();
			String tipoLiquidacion   = seDTO.getTipoLiquidacion();
			
			// encabezado y pie del comprobante de nómina
			EncabezadoExtractoNominaDTO encabezado = servicioNomina.encabezadoPieExtractoNomina( secuencia, codigoNomina );
			String nombreEmpleado  = encabezado.getNombresEmpleado().toUpperCase();
			String cedula          = encabezado.getCedula();
			String totalDevengado  = UtilidadesVarias.formatoDeMil(encabezado.getDevengos());
			String totalDeducido   = UtilidadesVarias.formatoDeMil(encabezado.getDeducciones());
			String totalNeto       = UtilidadesVarias.formatoDeMil(encabezado.getNeto());
			String dependencia     = encabezado.getDependencia();
			String fechaPago       = encabezado.getFechaPago();
			String sueldo          = UtilidadesVarias.formatoDeMil(encabezado.getNeto());
			
			// NIT y nombre de la empresa
			System.out.println( "----------------------");
			System.out.println( "Nombre de la empresa : " + empresa );
			System.out.println( "                 NIT : " + nit );
			System.out.println( "----------------------");
			
			// secuencia del empleado
			System.out.println( "-----------------------------");
			System.out.println( "                  Secuencia : " + secuencia );
			System.out.println( "            Código empleado : " + codigoNomina );
			System.out.println( "Descripción de la secuencia : " + descSecuencia );
			System.out.println( "                    Periodo : " + periodo_corriente );
			System.out.println( "        Tipo de liquidación : " + tipoLiquidacion );
			System.out.println( "----------------------------");
			
			// encabezado y pie del comprobante de nómina
			System.out.println( "-------------------------");
			System.out.println( "        Código empleado : " + encabezado.getCodigoEmpleado() );
			System.out.println( "        Nombre empleado : " + nombreEmpleado );
			System.out.println( "        Cédula empleado : " + cedula );
			System.out.println( "                Periodo : " + encabezado.getPeriodo() );
			System.out.println( "   Valor total devengos : " + totalDevengado );
			System.out.println( "Valor total deducciones : " + totalDeducido );
			System.out.println( "     Valor neto a pagar : " + totalNeto );
			System.out.println( "            Dependencia : " + dependencia );
			System.out.println( "          Fecha de pago : " + fechaPago );
			System.out.println( "             Sueldo mes : " + sueldo );
			System.out.println( "-------------------------");
			
			// conceptos por los cuales se le devenga al empleado
			List<DevengosEmpleadoDTO> lDevengos = null;
			lDevengos = servicioNomina.devengosEmpleado(secuencia, codigoNomina);
			
			int i = 1;
			String[] descDev  = new String[lDevengos.size() + 1];
			String[] valorDev = new String[lDevengos.size() + 1];
			float n_horas[]   = new float[lDevengos.size() + 1];
			
			for (Iterator<?> iterator = lDevengos.iterator(); iterator.hasNext();) {
				DevengosEmpleadoDTO devengosEmpleadoDTO = (DevengosEmpleadoDTO) iterator.next();
				
				System.out.println( "--------------------------");
				System.out.println( "         Código empleado : " + devengosEmpleadoDTO.getCodigoEmpleado() );
				System.out.println( "                Concepto : " + devengosEmpleadoDTO.getCodigoConcepto() );
				System.out.println( "Descripción del concepto : " + devengosEmpleadoDTO.getDescripcionConcepto() );
				System.out.println( "         Número de horas : " + devengosEmpleadoDTO.getnHoras() );
				System.out.println( "         Valor devengado : " + devengosEmpleadoDTO.getValor() );
				System.out.println( "--------------------------");
				
				descDev[i] = devengosEmpleadoDTO.getDescripcionConcepto();
				valorDev[i] = devengosEmpleadoDTO.getValor();
				n_horas[i++] = Float.parseFloat(devengosEmpleadoDTO.getnHoras());
			}
			
			// conceptos por los cuales se le realizan deducciones al empleado
			List<DeduccionesEmpleadoDTO> lDeducciones = null;
			lDeducciones = servicioNomina.deduccionesEmpleado(secuencia, codigoNomina);
			
			i = 1;
			String[] descDed  = new String[lDeducciones.size() + 1];
			String[] valorDed = new String[lDeducciones.size() + 1];
			
			for (Iterator<?> iterator = lDeducciones.iterator(); iterator.hasNext();) {
				DeduccionesEmpleadoDTO deduccionesEmpleadoDTO = (DeduccionesEmpleadoDTO) iterator.next();
				
				System.out.println( "--------------------------");
				System.out.println( "         Código empleado : " + deduccionesEmpleadoDTO.getCodigoEmpleado() );
				System.out.println( "                Concepto : " + deduccionesEmpleadoDTO.getCodigoConcepto());
				System.out.println( "Descripción del concepto : " + deduccionesEmpleadoDTO.getDescripcionConcepto() );
				System.out.println( "         Valor devengado : " + deduccionesEmpleadoDTO.getValor() );
				System.out.println( "--------------------------");
				
				descDed[i] = deduccionesEmpleadoDTO.getDescripcionConcepto();
				valorDed[i++] = deduccionesEmpleadoDTO.getValor();
			} 

			// el tamaño del vector lo da el numrow del resultado del query
			int tamDev = valorDev.length;
			int tamDed = valorDed.length;

			// se haya cual de los dos vectores tiene más elementos para poder pintarlos en el PDF
			int mayor = tamDev;
			if (tamDed > mayor) mayor = tamDed;

			// se pasa cada valor devengado a formato de mil
			for ( i = 1; i < tamDev; i++ ) {
				valorDev[i] = UtilidadesVarias.formatoDeMil(valorDev[i]);
			}
			
			// se pasa cada valor deducido a formato de mil
			for ( i = 1; i < tamDed; i++ ) {
				valorDed[i] = UtilidadesVarias.formatoDeMil(valorDed[i]);
			}

			// DevDescVal: matriz que contiene la descripciónes y los arrojadas por la base de datos para los devengos
			// DedDescVal: matriz que contiene la descripciónes y los arrojadas por la base de datos para las deducciones
			// la fila 1 de cada matriz contiene la descripcion
			// la fila 2 de cada matriz contiene los valores

			// matriz para los devengos
			String[][] DevDescVal = new String[3][tamDev];
			for ( i = 1; i < descDev.length; i++ ) {
				
				if( descDev[i].length() > ConstantesPEP.CARACTERES_MAX_DEVENGOS_EN )
					descDev[i] = descDev[i].substring(0, ConstantesPEP.CARACTERES_MAX_DEVENGOS_EN);
				
				DevDescVal[1][i] = descDev[i];
				DevDescVal[2][i] = valorDev[i];	
			}

			// matriz para las deducciones
			String[][] DedDescVal = new String[3][tamDed];
			for ( i = 1; i < descDed.length; i++ ) {
				
				if( descDed[i].length() > ConstantesPEP.CARACTERES_MAX_DEDUCCIONES_EN )
					descDed[i] = descDed[i].substring(0, ConstantesPEP.CARACTERES_MAX_DEDUCCIONES_EN);
				
				DedDescVal[1][i] = descDed[i];
				DedDescVal[2][i] = valorDed[i];	
			}
			
			PdfWriter.getInstance(document, new FileOutputStream(rutaFisica));
		    // step 3: we open the document
		    document.open();
		        
		    // TABLA 1. se crea la tabla que contiene el titulo principal
		    table = new PdfPTable(1); // 1 ==> indica que solo tendra una columna la tabla
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT);
		    
		    // fila que contiene el titulo COMPROBANTE DE PAGO NOMINA
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_PRINCIPAL_EN, fontTituloPpal)); // se añade el texto a la celda y se le da formato al texto
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER); // centar el texto horizontalmente dentro de la celda
		    cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // centar el texto verticalmente dentro de la celda
		    cell.setBorderColor(BaseColor.WHITE); // color del borde de la celda (BLANCO)
		    table.addCell(cell); //agrega la celda a la tabla
		    
		    document.add(table); //pinta la tabla en el PDF
		    
		    document.add(new Paragraph("\n")); // salto de linea
		    
		    // TABLA 2. se crea la tabla que contiene nombre y nit de la empresa y el nombre del empleado
			table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_01_EN); // se crea la tabla con los anchos especificos en sus 2 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo EMPRESA y NIT
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_EMPRESA_EN, fontTituloDatosEmp));
		    cell.setBorderWidthBottom(0);
		    cell.setPaddingLeft(5f); // para que no quede el texto pegado a la tabla lo separamos 5 px;
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NIT_EN, fontTituloDatosEmp));
		    cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0); // se eliminan los bordes necesarios
		    cell.setPaddingLeft(5f);
		    table.addCell(cell);
		    
		    // fila que contiene la EMPRESA y NIT arrojados por la base de datos 
		    cell = new PdfPCell(new Paragraph(empresa, fontRegistrosBD));
		    cell.setPaddingLeft(45f);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    cell = new PdfPCell(new Paragraph(nit, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0);cell.setBorderWidthBottom(0);
		    cell.setPaddingRight(30f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo NOMBRE
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NOMBRE_EN, fontTituloDatosEmp));
		    cell.setColspan(2);
		    cell.setPaddingLeft(5f);
		    cell.setFixedHeight(20f);
		    cell.setBorderWidthBottom(0);
		    table.addCell(cell);
		    
		    // fila que contiene el NOMBRE arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(nombreEmpleado, fontRegistrosBD));
		    cell.setColspan(2);
		    cell.setPaddingLeft(45f);
		    cell.setFixedHeight(20f);
		    cell.setBorderWidthTop(0);
		    table.addCell(cell);

		    document.add(table);
		    
		    // TABLA 3. se crea la tabla que contiene el código, la cédula, la fecha de pago y el periodo de pago del empleado
			table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_02_EN); // se crea la tabla con los anchos especificos en sus 4 columnas
			table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo CODIGO
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_CODIGO_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthBottom(0); cell.setBorderWidthTop(0);
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo IDENTIFICACION
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_IDENTIFICACION_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0); cell.setBorderWidthTop(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo FECHA DE PAGO
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_FECHA_PAGO_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0); cell.setBorderWidthTop(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo PERIODO DE PAGO
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_PERIODO_PAGO_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0); cell.setBorderWidthTop(0);
		    table.addCell(cell);
		    
		    
		    // fila que contiene el CODIGO arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(codigoNomina, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    cell.setFixedHeight(20f);
		    cell.setPaddingLeft(45f);
		    table.addCell(cell);
		    
		    // fila que contiene la IDENTIFICACION arrojada por labase de datos
		    cell = new PdfPCell(new Paragraph(cedula, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    cell.setPaddingRight(23f);
		    
		    table.addCell(cell);
		    
		    // fila que contiene la FECHA DE PAGO arrojada por la base de datos
		    cell = new PdfPCell(new Paragraph(fechaPago, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    table.addCell(cell);
		    
		    // fila que contiene la PERIODO DE PAGO arrojada por la base de datos
		    cell = new PdfPCell(new Paragraph(periodo, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0);
		    cell.setPaddingRight(23f);
		    table.addCell(cell);
		    
		    document.add(table);
		    
		    // TABLA 4. se crea la tabla que contiene la dependencia y el sueldo del empleado
		    table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_03_EN); // se crea la tabla con los anchos especificos en sus 2 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo DEPENDENCIA
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DEPENDENCIA_EN, fontTituloDatosEmp));
		    cell.setBorderWidthBottom(0);
		    cell.setPaddingLeft(5f); // para que no quede el texto pegado a la tabla lo separamos 5 px;
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo SUELDO MES
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_SUELDO_MES_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthLeft(0); cell.setBorderWidthBottom(0); // se eliminan los bordes necesarios
		    table.addCell(cell);
		    
		    // fila que contiene la DEPENDENCIA arrojada por la base de datos
		    cell = new PdfPCell(new Paragraph(dependencia , fontRegistrosBD));
		    cell.setPaddingLeft(45f);
		    cell.setBorderWidthTop(0); 
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    // fila que contiene el SUELDO MES arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(sueldo, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setBorderWidthTop(0); cell.setBorderWidthLeft(0);
		    cell.setPaddingLeft(18f);
		    table.addCell(cell);
		    
		    document.add(table);

		    // TABLA 5. se crea la tabla que contiene el titulo de DEVENGADOS y DEDUCCIONES
			table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_04_EN); // se crea la tabla con los anchos especificos en sus 2 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo DEVENGADOS
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DEVENGADOS_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthTop(0);cell.setBorderWidthBottom(0); cell.setBorderWidthRight(0);
		    cell.setFixedHeight(15f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo DEDUCCIONES
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DEDUCCIONES_EN, fontTituloDatosEmp));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);// se eliminan los bordes necesarios
		    table.addCell(cell);
		    
		    document.add(table);
		    
		    // TABLA 6. se crea la tabla que contiene el detalle de los devengados y deducciones
		 	table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_05_EN); // se crea la tabla con los anchos especificos en sus 5 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo DESCRIPCION
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DESCRIPCION_EN, fontTituloDetalle));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderWidthBottom(0);
		    cell.setFixedHeight(12f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo No. HORAS
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_HORAS_EN, fontTituloDetalle));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderColor(BaseColor.WHITE);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0); cell.setBorderWidthRight(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo VALOR
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_VALOR_EN, fontTituloDetalle));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderColor(BaseColor.WHITE);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0); cell.setBorderWidthRight(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo DESCRIPCION
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DESCRIPCION_EN, fontTituloDetalle));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderColor(BaseColor.WHITE);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo VALOR
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_VALOR_EN, fontTituloDetalle));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthLeft(0);
		    table.addCell(cell);
		    
		    // fila vacia antes de iniciar a pintar los detalles columna DESCRIPCION
			cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
			cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    cell.setFixedHeight(12f);
		    table.addCell(cell);
		    
		    // fila vacia antes de iniciar a pintar los detalles columna No. HORAS
		    cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell);
		    
		    // fila vacia antes de iniciar a pintar los detalles columna VALOR
		    cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell);
		    
		    // fila vacia antes de iniciar a pintar los detalles columna DESCRIPCION
		    cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		    table.addCell(cell);
		    
		    // fila vacia antes de iniciar a pintar los detalles columna VALOR
		    cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthLeft(0);
		    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		    table.addCell(cell);

		    String descripcion = "", valor = "", horas = "";
		    for( i = 1; i < mayor; i++ ) {
		    	
		    	// fila que contiene las DESCRIPCIONES arrojadas por la base de datos para devengos
		    	if (i < tamDev) descripcion = DevDescVal[1][i];
		    	cell = new PdfPCell(new Paragraph(descripcion, fontDetalleDesc));
		    	cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        cell.setFixedHeight(15f);
		        cell.setPaddingLeft(5f);
		        table.addCell(cell);
		        descripcion = "";
		        
		        // fila que contiene los No. HORAS arrojadas por la base de datos para devengos
		        if (i < tamDev) horas = "" + n_horas[i] + "0";
		        cell = new PdfPCell(new Paragraph(horas, fontDetalleDesc));
		        cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		        horas = "";
		        
		        // fila que contiene los VALORES arrojados por la base de datos para devengos
		        if (i < tamDev) valor = DevDescVal[2][i];
		        cell = new PdfPCell(new Paragraph(valor, fontDetalleDesc));
		        cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		        valor = "";
		        
		        // fila que contiene las DESCRIPCIONES arrojadas por la base de datos para deducciones
		        if (i < tamDed) descripcion = DedDescVal[1][i];
		        cell = new PdfPCell(new Paragraph(descripcion, fontDetalleDesc));
		        cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        cell.setPaddingLeft(5f);
		        table.addCell(cell);
		        descripcion = "";
		        
		        // fila que contiene los VALORES arrojados por la base de datos para deducciones
		        if (i < tamDed) valor = DedDescVal[2][i];
		        cell = new PdfPCell(new Paragraph(valor, fontDetalleDesc));
		        cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthLeft(0);
		        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        table.addCell(cell);
		        valor = "";
		    }
		        
		    // se pintan las lineas vacias para completar la hoja del comprobante de nómina
		    // se le resta 1 a mayor porque no se tiene en cuenta la posición cero del vector
		    if( ( mayor - 1 ) < ConstantesPEP.FILAS_MAX_EN ){
		    	for( i = mayor; i <= ConstantesPEP.FILAS_MAX_EN; i++ ) {
		        	
		    		//fila vacia
		    		cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		        	cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		            cell.setFixedHeight(15f);
		            table.addCell(cell);
		            
		            //fila vacia
		            cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		            cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            table.addCell(cell);
		            
		            //fila vacia
		            cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		            cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthRight(0);
		            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            table.addCell(cell);
		            
		            //fila vacia
		            cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		            cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);
		            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		            cell.setPaddingLeft(5f);
		            table.addCell(cell);
		            
		            //fila vacia
		            cell = new PdfPCell(new Paragraph("", fontDetalleDesc));
		            cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0);cell.setBorderWidthLeft(0);
		            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            table.addCell(cell);
		    	}
		    }

			document.add(table);
			
		    // TABLA 7. se crea la tabla que contiene los totales	
		 	table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_06_EN); // se crea la tabla con los anchos especificos en sus 5 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_EN);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo TOTAL DEVENGADO
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_TOTAL_DEVENGADO_EN, fontTituloPie));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderWidthBottom(0);
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo TOTAL DEDUCCIONES
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_TOTAL_DEDUCCIONES_EN, fontTituloPie));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderColor(BaseColor.WHITE);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0); cell.setBorderWidthRight(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo NETO A PAGAR
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_TOTAL_NETO_EN, fontTituloPie));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBackgroundColor(BaseColor.BLACK);
		    cell.setBorderColor(BaseColor.WHITE);
		    cell.setBorderWidthTop(0); cell.setBorderWidthBottom(0); cell.setBorderWidthRight(0);
		    table.addCell(cell);
		    
		    // fila que contiene el TOTAL DEVENGADO arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(totalDevengado, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthRight(0);cell.setBorderWidthTop(0);
		    cell.setFixedHeight(20f);
		    table.addCell(cell);
		    
		    // fila que contiene el TOTAL DEDUCCIONES arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(totalDeducido, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthRight(0);cell.setBorderWidthTop(0);
		    table.addCell(cell);
		    
		    // fila que contiene el NETO A PAGAR arrojado por la base de datos
		    cell = new PdfPCell(new Paragraph(totalNeto, fontRegistrosBD));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cell.setBorderWidthTop(0);
		    table.addCell(cell);
		    
		    document.add(table);
		}
		catch(DocumentException de) {
		    System.err.println(de.getMessage());
		}
		catch(Exception e) {
		    System.err.println(e.getMessage());
		}

		// step 5: we close the document
		document.close();
		
		return null;
	}
	
	public String generacionPDFCertificadoLaboral(String rutaFisica){
		
		int noPrint = 0; // variable que contará cuantos valores no se imprimieron el el PDF
		
		boolean horasExtras  = false;
		boolean comision     = false;
		boolean bonificacion = false;
		
		if(horasExtrasSelect.equals("1"))  horasExtras  = true;
		if(comisionSelect.equals("1"))     comision     = true;
		if(bonificacionSelect.equals("1")) bonificacion = true;
		
		// fuente para el titulo NIT
		Font fontNIT = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontNIT.setSize(8);

		// fuente para el cuerpo de la certificación
		Font fontBody = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.NORMAL);
		fontBody.setSize(12);

		// fuente para la firma digital
		Font fontFirma = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.BOLD);
		fontFirma.setSize(12);

		// fuente para los datos de la empresa
		Font fontDatosEmpresa = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontDatosEmpresa.setSize(10);

		// fuente para el link: http://www.proquinal.com
		Font fontLink = new Font(Font.FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
		fontLink.setSize(10);
		fontLink.setColor(BaseColor.BLUE);

		// fuente para la leyenda al final de la página
		Font fontPie = new Font(Font.FontFamily.COURIER, Font.DEFAULTSIZE, Font.BOLD);
		fontPie.setSize(9);
		fontPie.setColor(25,25,25);

		//step 1: creation of a document-object     
		Document document = new Document(PageSize.A4, ConstantesPEP.LEFT_CL, ConstantesPEP.RIGHT_CL, ConstantesPEP.TOP_CL, ConstantesPEP.BOTTON_CL); //tamaño de la página,left, right, top, botton

		try {
			
			// información básica de la empresa
			TNomConfig tConfig = servicioNomina.obtenerInfoEmpresa(codigoEmpresaSesion);
			String nit       = tConfig.getDNit();
			String direccion = tConfig.getDDireccion();
			String telefono  = ConstantesPEP.TITULO_TELEFONO + tConfig.getDTelefono();
			String fax       = ConstantesPEP.TITULO_FAX + tConfig.getDFax() + ConstantesPEP.CIUDAD_EMPRESA;
			String sitioWeb  = "http://www.proquinal.com";
			
			// encabezado del certificado laboral
			EncabezadoCertificadoLaboralDTO encabezado = servicioNomina.encabezadoCertificadoLaboral(codtraSesion);
			String nombreEmpleado = encabezado.getNomtra().toUpperCase();
			String cedula         = encabezado.getCedula();
			String fechaIngreso   = encabezado.getFechaIngreso();
			String cargo          = encabezado.getCargo();
			String contrato       = encabezado.getContrato();
			String sexo           = encabezado.getSexo().toString().toUpperCase();
			String area           = encabezado.getArea();
			String salario        = UtilidadesVarias.formatoDeMil(encabezado.getSalario());
			String fechaActual    = encabezado.getFechaActual();
			
			// promedio de horas extras
			String promedioHE = "";
			String promedioC  = "";
			String promedioBG = "";
			
			if(horasExtras)  promedioHE = UtilidadesVarias.formatoDeMil(servicioNomina.promedioHorasExtrasCertificadoLaboral(codtraSesion));
			if(comision)     promedioC  = UtilidadesVarias.formatoDeMil(servicioNomina.promedioComisionesCertificadoLaboral(codtraSesion));
			if(bonificacion) promedioBG = UtilidadesVarias.formatoDeMil(servicioNomina.promedioBonificacionCertificadoLaboral(codtraSesion));
			
			// logo proquinal
			ParametricaDTO logoPqn = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.IMAGEN_LOGO_PROQUINAL );
			String sLogoPqn = logoPqn.getPathImagen();
			String sLogoPqnDefault = logoPqn.getDescripcion3();
			String logoPqnRuta;
			
			File iLogoPqn = new File( sLogoPqn );
			if( iLogoPqn.exists() ){
				logoPqnRuta = sLogoPqn;
			}
			else{
				logoPqnRuta = sLogoPqnDefault;
			}
			
			// nombre y firma digital del(a) jefe de nómina
			ParametricaDTO infoNomina = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.INFORMACION_DEPARTAMENTO_NOMINA );
			String jefeNomina = infoNomina.getDescripcion1().trim().toUpperCase();
			String firmaDigital = infoNomina.getPathImagen();
			String firmaDigitalDefault = infoNomina.getDescripcion3();
			String firmaDigitalRuta;
			
			File iFirmaDigital = new File( firmaDigital );
			if( iFirmaDigital.exists() ){
				firmaDigitalRuta = firmaDigital;
			}
			else{
				firmaDigitalRuta = firmaDigitalDefault;
			}
			
			// leyenda al final del certificado
			ParametricaDTO leyenda = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.LEYENDA_CERTIFICADO_LABORAL );
			String sLeyenda = leyenda.getDescripcion1();
			
			// información básica de la empresa
			System.out.println( "-------------------");
			System.out.println( "NIT de la empresa : " + nit );
			System.out.println( "        Dirección : " + direccion );
			System.out.println( "         Telefono : " + telefono );
			System.out.println( "              Fax : " + fax );
			System.out.println( "        Sitio WEB : " + sitioWeb );
			System.out.println( "-------------------");
			
			// encabezado del certificado laboral
			System.out.println( "-----------------------");
			System.out.println( "Nombre del trabajador : " + nombreEmpleado );
			System.out.println( "               Cedula : " + cedula );
			System.out.println( "     Fecha de ingreso : " + fechaIngreso );
			System.out.println( "                Cargo : " + cargo );
			System.out.println( "     Tipo de contrato : " + contrato );
			System.out.println( "                 Sexo : " + sexo );
			System.out.println( "                 Area : " + area );
			System.out.println( "              Salario : " + salario );
			System.out.println( "         Fecha actual : " + fechaActual );
			System.out.println( "-----------------------");
			
			// promedio de horas extras
			if(horasExtras){
				System.out.println( "--------------------------");
				System.out.println( "promedio de horas extras : " + promedioHE );
				System.out.println( "--------------------------");
			}
			
			// promedio comisiones
			if(comision){
				System.out.println( "------------------------");
				System.out.println( "promedio de comisiones : " + promedioC );
				System.out.println( "------------------------");
			}
			
			// promedio bonificación por gestión
			if(bonificacion){
				System.out.println( "--------------------------------------");
				System.out.println( "promedio de bonificación por gestión : " + promedioBG );
				System.out.println( "--------------------------------------");
			}
			
			// ruta del logo proquinal
			System.out.println( "-------------------------");
			System.out.println( "Ruta del logo proquinal : " + logoPqnRuta );
			System.out.println( "-------------------------");
			
			// ruta de la firma digital
			System.out.println( "--------------------------");
			System.out.println( "Ruta de la firma digital : " + firmaDigitalRuta );
			System.out.println( "--------------------------");
			
			// leyenda
			System.out.println( "---------");
			System.out.println( "Leyenda : " + sLeyenda );
			System.out.println( "---------");
			
			// jefe de nomina
			System.out.println( "----------------");
			System.out.println( "Jefe de Nómina :" + jefeNomina );
			System.out.println( "----------------");
			
			PdfWriter.getInstance(document, new FileOutputStream(rutaFisica));
		    
			// step 3: we open the document
		    document.open();
		        
		    // se agrega el logo de la empresa
		    Image logo = Image.getInstance(logoPqnRuta);
		    logo.setAlignment(Image.LEFT);
		    logo.scaleAbsolute(90, 85);
		    document.add(logo);
		    
		    // TABLA 1. se crea la tabla que contiene el NIT de la empresa
		 	table = new PdfPTable(1);
			table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
		    
			// fila que contiene el NIT
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NIT_CL + " " + nit, fontNIT));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setBorderColor(BaseColor.WHITE);
			cell.setPaddingLeft(8f); // para que no quede el texto pegado a la tabla lo separamos 8 px;
			table.addCell(cell);

			document.add(table); //pinta la tabla en el PDF
			
			document.add(new Paragraph("\n")); // salto de linea
		 	
			// TABLA 2. se crea la tabla que contiene el titulo C E R T I F I C A C I O N
			table = new PdfPTable(1);
			table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			//fila que contiene el titulo C E R T I F I C A C I O N
			cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_PRINCIPAL_CL, fontBody));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
			
		    document.add(table); //pinta la tabla en el PDF
		    
		    document.add(new Paragraph("\n")); // salto de linea
			
			// TABLA 3. se crea la tabla que contiene el texto de constancia de la certificación
			table = new PdfPTable(1);
			table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			//fila que contiene el texto de la constancia
			String textoConstancia_cL = UtilidadesVarias.generoCertificadoLaboral(sexo);
			cell = new PdfPCell(new Paragraph(textoConstancia_cL, fontBody));
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setBorderColor(BaseColor.WHITE);
			table.addCell(cell);
		    
		    document.add(table); //pinta la tabla en el PDF
		    
		    document.add(new Paragraph("\n")); // salto de linea
		    
		    // TABLA 4. se crea la tabla que contiene los datos del empleado
			table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_01_CL); // se crea la tabla con los anchos especificos en sus 3 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo Nombre del trabajador
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_NOMBTRE_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene la Nombre del trabajador arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(nombreEmpleado, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo Número de identificación
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_IDENTIFICACION_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el Número de identificación arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(cedula, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo Fecha de ingreso
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_FECHA_INGRESO_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene la Fecha de ingreso arrojada por la base de datos 
		    cell = new PdfPCell(new Paragraph(fechaIngreso, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo Cargo que desempeña
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_CARGO_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el Cargo que desempeña arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(cargo, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo Tipo de contrato 
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_TIPO_CONTRATO_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el Tipo de contrato arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(contrato, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el titulo Departamento 
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_DEPARTAMENTO_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene los dos puntos
		    cell = new PdfPCell(new Paragraph(":", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el Departamento arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(area, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);

		    document.add(table); //pinta la tabla en el PDF
		    
		    document.add(new Paragraph("\n")); // salto de linea
		    
		    // TABLA 5. se crea la tabla que contiene salario, horas extras, comisiones, bonificaciones y hacia quien va dirigido
			table = new PdfPTable(ConstantesPEP.WIDTH_TABLA_02_CL); // se crea la tabla con los anchos especificos en sus 3 columnas
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el titulo Devengando un salario básico mensual de
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_SALARIO_BASICO_CL, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el signo $
		    cell = new PdfPCell(new Paragraph("$", fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el salario básico mensual arrojado por la base de datos 
		    cell = new PdfPCell(new Paragraph(salario, fontBody));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    if( horasExtras ){
		    	// fila que contiene el titulo Valor promedio horas extras
		    	cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_VALOR_PROM_HE_CL, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el signo $
		        cell = new PdfPCell(new Paragraph("$", fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el Valor promedio horas extras arrojado por la base de datos 
		        cell = new PdfPCell(new Paragraph(promedioHE, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		    }
		    
		    if( comision ){
		    	// fila que contiene el titulo Valor promedio de comisiones de
		    	cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_VALOR_PROM_COM_CL, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el signo $
		        cell = new PdfPCell(new Paragraph("$", fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el Valor promedio comisiones arrojado por la base de datos 
		        cell = new PdfPCell(new Paragraph(promedioC, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		    }
		    
		    if( bonificacion ){
		    	// fila que contiene el titulo Valor promedio bonificación por gestion de
		    	cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_VALOR_PROM_BG_CL, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el signo $
		        cell = new PdfPCell(new Paragraph("$", fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		        
		        // fila que contiene el Valor promedio comisiones arrojado por la base de datos 
		        cell = new PdfPCell(new Paragraph(promedioBG, fontBody));
		        cell.setBorderWidth(0);
		        table.addCell(cell);
		    }
		    
		    // fila vacia
			cell = new PdfPCell(new Paragraph(" ", fontBody));
			cell.setColspan(3);
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que indica a quien va dirigido y en que fecha se expidio
		    String tituloLeyendaCert_cL = ConstantesPEP.TITULO_01_LEYENDA_CERT_CL + fechaActual + ConstantesPEP.TITULO_02_LEYENDA_CERT_CL;
		    cell = new PdfPCell(new Paragraph(tituloLeyendaCert_cL + dirigido + ".", fontBody));
			cell.setColspan(3);
		    cell.setBorderWidth(0);
		    cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		    table.addCell(cell);
		    
		    // se cuentan cuantos valores no se escibieron
		    if( !horasExtras )  noPrint++;
		    if( !comision )     noPrint++;
		    if( !bonificacion ) noPrint++;
		    
		    for ( int i = 1; i <= noPrint; i++ ) {
		    	// fila que contiene los espacios en blanco de los valores que 
		    	// no fueron pedidos por el empleado para la generación del certificado
		    	cell = new PdfPCell(new Paragraph(" ", fontBody));
		    	cell.setColspan(3);
		    	cell.setBorderWidth(0);
		        table.addCell(cell);
		    }
		    
		    document.add(table); //pinta la tabla en el PDF
		    
		    //document.add(new Paragraph("\n")); // salto de linea
		    
		    // se agrega la imagen de la firma digital
		    Image firma = Image.getInstance(firmaDigitalRuta);
		    firma.setAlignment(Image.LEFT);
		    firma.scaleAbsolute(240, 120);
		    document.add(firma);
		    
		    // TABLA 6. se crea la tabla que contiene el nombre del jefe de nómina y el cargo
			table = new PdfPTable(1);
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_FIRMA_CL);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene el nombre del jefe de nómina
		    cell = new PdfPCell(new Paragraph(jefeNomina, fontFirma));
		    cell.setBorderWidthLeft(0); cell.setBorderWidthRight(0); cell.setBorderWidthBottom(0);
		    table.addCell(cell);
		    
		    // fila que contiene el cargo del jefe de nómina
		    cell = new PdfPCell(new Paragraph(ConstantesPEP.TITULO_JEFE_DE_NOMINA_CL, fontFirma));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    document.add(table); //pinta la tabla en el PDF

		    document.add(new Paragraph("\n\n")); // salto de linea
		    
		    // TABLA 7. se crea la tabla que contiene la información de la empresa y la leyenda como pie de pagina
			table = new PdfPTable(1);
		    table.setWidthPercentage(ConstantesPEP.ANCHO_TABLA_CL);
		    table.setHorizontalAlignment(Element.ALIGN_LEFT); // alinear tabla a la derecha horizontalmente
		    
		    // fila que contiene la dirección de la empresa
		    cell = new PdfPCell(new Paragraph(direccion, fontDatosEmpresa));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene la dirección de la empresa
		    cell = new PdfPCell(new Paragraph(telefono, fontDatosEmpresa));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene la dirección de la empresa
		    cell = new PdfPCell(new Paragraph(fax, fontDatosEmpresa));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene la dirección de la empresa
		    cell = new PdfPCell(new Paragraph(sitioWeb, fontLink));
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila vacia
			cell = new PdfPCell(new Paragraph(""));
			cell.setFixedHeight(36f);
		    cell.setBorderWidth(0);
		    table.addCell(cell);
		    
		    // fila que contiene el nombre del jefe de nómina
		    cell = new PdfPCell(new Paragraph(sLeyenda, fontPie));
		    cell.setBorderWidth(0);
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    table.addCell(cell);
		    
		    document.add(table); //pinta la tabla en el PDF
		}
		catch(DocumentException de) {
		    System.err.println(de.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// step 5: we close the document
		document.close();
		
		return null;
	}	
}