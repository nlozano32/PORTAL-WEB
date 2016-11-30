package com.proquinal.pep.managed_bean.ycontent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex. Matcher;
import java.util.regex. Pattern; 

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;
import com.proquinal.pep.entidades.ycontent.dto.ParametricaDTO;
import com.proquinal.pep.fwk.constantes.ConstantesPEP;
import com.proquinal.pep.fwk.constantes.ConstantesPEPParametricas;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.fwk.jms.EnvioCorreo;
import com.proquinal.pep.fwk.utilidades.UtilidadesFecha;
import com.proquinal.pep.fwk.utilidades.UtilidadesVarias;
import com.proquinal.pep.fwk.vo.DocAdjuntoVO;
import com.proquinal.pep.fwk.vo.MensajeCorreoVO;
import com.proquinal.pep.negocioServicio.internet.IServicioInternet;
import com.proquinal.pep.negocioServicio.ycontent.IServicioYcontent;




public class FormulariosBean {
	
	@EJB(mappedName="ejb/ServicioYcontent")
	private IServicioYcontent servicioYcontent;
	

	@EJB(mappedName="ejb/ServicioInternet")
	private IServicioInternet servicioInternet;
	
	/**Atributos para capturar la fecha de nacimiento*/
	
	
	private Calendar fecha; /** fecha actual del servidor */	
	
	private int dia;        /** dia actual del servidor   */
	private int mes;        /** mes actual del servidor   */
	private int anio;       /** año actual del servidor   */
	private String fechaNacimientoCad;
	private Date fechaNacimiento;

	
	
	public FormulariosBean() {
		// TODO Auto-generated constructor stub	
		
		fecha    = Calendar.getInstance();
		dia      = fecha.get(Calendar.DAY_OF_MONTH);
		mes      = fecha.get(Calendar.MONTH) + 1; /** + 1 porque el rango de meses arrojado por la clase Calendar va de 0 a 12*/
		anio     = fecha.get(Calendar.YEAR);
		
		fechaNacimientoCad  = UtilidadesVarias.ceroIzquierda(dia) + "/" + UtilidadesVarias.ceroIzquierda(mes) + "/" + String.valueOf(anio);
		fechaNacimiento = UtilidadesFecha.getDateDDMMYYYY(fechaNacimientoCad);
	}
	
	/** Set y get para la fecha de nacimiento */ 
	
	public String getFechaNacimientoCad() {
		return fechaNacimientoCad;
	}
	

	public void setFechaNacimientoCad(String fechaNacimientoCad) {
		this.fechaNacimientoCad = fechaNacimientoCad;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	// contactenos
	private String nombres;
	private String apellidos;
	private String empresa;
	private String cargo;
	private String telefono;
	private String direccion;
	private String celular;
	private String pais;
	private String ciudad;
	private String email;
	private String producto;
	private String motivo;
	private String comentario;
	private String mensajeC;
	private String mensajeT;
	
	
	
	private ArrayList <SelectItem> lProductos = null;
	
	/**
	 * @return the lProductos
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SelectItem> getlProductos() {
		
		lProductos = new ArrayList <SelectItem> ();
		try {
			List<String> lCategorias = servicioInternet.obtenerCategoriasSegmentos();
			for (Iterator iterator = lCategorias.iterator(); iterator.hasNext();) {
				String producto = (String) iterator.next();
				lProductos.add( new SelectItem( String.valueOf( producto.toLowerCase() ), String.valueOf( producto.toLowerCase() ) ));
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return lProductos;
	}

	/**
	 * @param lProductos the lProductos to set
	 */
	public void setlProductos(ArrayList<SelectItem> lProductos) {
		this.lProductos = lProductos;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	// trabaje con nosotros
	// informacion basica
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String identificacion;
	
	// informacion personal
	
	
	
	private String genero;
	private String ciudadResidencia;
	private String celularCandidato;
	private String aspiracionSalarial;
	
	
	// informacion formal
	private String nivelEducativo;
	private String tituloObtenido;
	private String areaInteres;
	
	

	// archivo
	private FileInfo archivoActual;
	

	public FileInfo getArchivoActual() {
		return archivoActual;
	}
	 

	
	public void uploadActionListener(ActionEvent actionEvent) {
        InputFile inputFile = (InputFile) actionEvent.getSource();
        archivoActual = inputFile.getFileInfo();
	}
	


	/**
	 * @return the nombre
	 */
	
	/**
	 * Gets the default timezone of the host server. The timezone is needed by
	 * the convertDateTime for formatting the time dat values.
	 * 
	 * @return timezone for the current JVM (log certificación laboral)
	 */
	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}
	
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	/**
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return the segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the ciudadResidencia
	 */
	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	/**
	 * @param ciudadResidencia the ciudadResidencia to set
	 */
	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	/**
	 * @return the celularCandidato
	 */
	public String getCelularCandidato() {
		return celularCandidato;
	}

	/**
	 * @param celularCandidato the celularCandidato to set
	 */
	public void setCelularCandidato(String celularCandidato) {
		this.celularCandidato = celularCandidato;
	}

	/**
	 * @return the aspiracionSalarial
	 */
	public String getAspiracionSalarial() {
		return aspiracionSalarial;
	}

	/**
	 * @param aspiracionSalarial the aspiracionSalarial to set
	 */
	public void setAspiracionSalarial(String aspiracionSalarial) {
		this.aspiracionSalarial = aspiracionSalarial;
	}

	/**
	 * @return the nivelEducativo
	 */
	public String getNivelEducativo() {
		return nivelEducativo;
	}

	/**
	 * @param nivelEducativo the nivelEducativo to set*/
	 
	public void setNivelEducativo(String nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

	/**
	 * @return the tituloObtenido
	 */
	public String getTituloObtenido() {
		return tituloObtenido;
	}

	/**
	 * @param tituloObtenido the tituloObtenido to set
	 */
	public void setTituloObtenido(String tituloObtenido) {
		this.tituloObtenido = tituloObtenido;
	}

	/**
	 * @return the areaInteres
	 */
	public String getAreaInteres() {
		return areaInteres;
	}

	/**
	 * @param areaInteres the areaInteres to set
	 */
	public void setAreaInteres(String areaInteres) {
		this.areaInteres = areaInteres;
	}
	
	
	
	/**Constantes para pintar las opciones "Motivo por el cual nos contacta - Formulario contáctenos"*/
	
	String motivoContacta [] = ConstantesPEP.MOTIVOCONTACTO;
	private ArrayList <SelectItem> listarMotivoContacto;
	
	
	public Collection <SelectItem> getListarMotivoContacto(){
		
		if( listarMotivoContacto == null ){
		
			listarMotivoContacto = new ArrayList <SelectItem> ();
			
			for(int i=0;i<6;i++){
				
				
				String value =(motivoContacta[i]);
				String label =(motivoContacta[i]);
				
				listarMotivoContacto.add( new SelectItem(value, label) );
				
				//System.out.println(listarEstudios);
				
			    }
			
		}

		return listarMotivoContacto;
	}
	


	/**Constantes para pintar las opciones del menu de género*/
	
	String generos[] = ConstantesPEP.GENERO;
	private ArrayList <SelectItem> listarGeneros;	
	

	public Collection <SelectItem> getListarGeneros(){
		
		if( listarGeneros == null ){
		
			listarGeneros = new ArrayList <SelectItem> ();
			
			for(int i=0;i<2;i++){
				
				
				String value =(generos[i]);
				String label =(generos[i]);
				
				listarGeneros.add( new SelectItem(value, label) );
				
				//System.out.println(listarEstudios);
				
			    }
			
		}

		return listarGeneros;
	}
	
	
	
	/**Constantes para pintar las opciones del menu para Nivel de estudios*/
	
	
	String estudios[] = ConstantesPEP.NIVEL_EDUCATIVO;
	private ArrayList <SelectItem> listarEstudios;	
	

	public Collection <SelectItem> getListarEstudios(){
		
		if( listarEstudios == null ){
		
			listarEstudios = new ArrayList <SelectItem> ();
			
			for(int i=0;i<7;i++){
				
				
				String value =(estudios[i]);
				String label =(estudios[i]);
				
				listarEstudios.add( new SelectItem(value, label) );
				
				//System.out.println(listarEstudios);
				
			    }
			
		}

		return listarEstudios;
	}
	
	/**Constantes para pintar las opciones del menu para Área de interés*/
	
	String areaDeInteres[] = ConstantesPEP.AREAINTERES;
	private ArrayList <SelectItem> listarAreaInteres;	
	

	public Collection <SelectItem> getListarAreaInteres(){
		
		if( listarAreaInteres == null ){
		
			listarAreaInteres = new ArrayList <SelectItem> ();
			
			for(int i=0;i<5;i++){
				
				
				String value =(areaDeInteres[i]);
				String label =(areaDeInteres[i]);
				
				listarAreaInteres.add( new SelectItem(value, label) );
				
				
				
			    }
			
		}

		return listarAreaInteres;
	}
	
	
	/** Validación de los campos para el formulario contáctenos*/
	
	public  boolean validaCamposInternetContactenos(){

		FacesContext context = FacesContext.getCurrentInstance();
		String msjc = null;         
		String msjap = null;
		String msjcar = null;
		String msjtel = null;
		String msjdir = null;
		String msjcel = null;
		String msjpai = null;
		String msjciu = null;
		String msjema = null;
		
		String cmsjc = null;
		String cmsjap = null;
		String cmsemp = null;
		String cmsjcar = null;
		String cmsjpai = null;
		String cmsjciu = null;
		
		boolean estado = true;
			
		/**Validacion de campos nulos*/
		
		if ( nombres.equals("")) {
			msjc = ConstantesPEP.ERRORNOMBRENULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjc , null));
			estado = false;
			
			
		}if ( apellidos.equals("")) {
			msjap = ConstantesPEP.ERRORAPELLIDONULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjap, null));
			estado = false;
			}
		if ( cargo.equals("")) {
			msjcar = ConstantesPEP.ERRORCARGONULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjcar, null));
			estado = false;
		}
		if ( telefono.equals("")) {
			msjtel = ConstantesPEP.ERRORTELEFONONULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjtel, null));
			estado = false;
		}
		if ( direccion.equals("")) {
			msjdir = ConstantesPEP.ERRORDIRECCIONNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjdir, null));
			estado = false;
		}
		if ( celular.equals("")) {
			msjcel = ConstantesPEP.ERRORCELULARNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjcel, null));
			estado = false;
		}
		if ( pais.equals("")) {
			msjpai = ConstantesPEP.ERRORPAISNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjpai, null));
			estado = false;
		}
		if ( ciudad.equals("")) {
			msjciu = ConstantesPEP.ERRORCIUDADNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjciu, null));
			estado = false;
		}
		if ( email.equals("")) {
			msjema = ConstantesPEP.ERROREMAILNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjema, null));
			estado = false;
		}
		
		/**Validacion de caracteres de letras*/
		
		boolean vnumeronombres = false;
			for (int i = 0; i < nombres.length();i++) { 
			 if ( Character.isDigit(nombres.charAt(i)) ){
				 
				 vnumeronombres=true;
			 	}
			 
		 	}
		 	if(vnumeronombres){
				 
				cmsjc = ConstantesPEP.ERRORNOMBRELETRA;
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjc , null));
				estado = false;
			 }
			 
			 
		 	boolean vnumeroapellidos = false;
		 	
		 	for (int j = 0; j < apellidos.length();j++) { 
				 if ( Character.isDigit(apellidos.charAt(j)) ){
					 
					 vnumeroapellidos=true;
				 	}
				 
			 	}	 
			 	if(vnumeroapellidos){
					 
					
					cmsjap = ConstantesPEP.ERRORAPELLIDOLETRA;
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjap, null));
					estado = false;
				 }
			 	
			 	boolean vnumeroempresa = false;
			 	for (int i = 0; i < empresa.length();i++) { 
					 if ( Character.isDigit(empresa.charAt(i)) ){
						 
						 vnumeroempresa=true;
					 	}
					 
				 	}	 
				 	if(vnumeroempresa){
						 
				 		cmsemp = ConstantesPEP.ERROREMPRESALETRA;
						context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsemp, null));
						estado = false;
					 }
				 	
				 	boolean vnumerocargo = false;
				 	for (int i = 0; i < cargo.length();i++) { 
						 if ( Character.isDigit(cargo.charAt(i)) ){
							 
							 vnumerocargo=true;
						 	}
						 
					 	}	 
					 	if(vnumerocargo){
							 
					 		cmsjcar = ConstantesPEP.ERRORCARGOLETRA;
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjcar, null));
							estado = false;
						 }
					 	boolean vnumeropais = false;
					 	for (int i = 0; i < pais.length();i++) { 
							 if ( Character.isDigit(pais.charAt(i)) ){
								 
								 vnumeropais=true;
							 	}
							 
						 	}	 
						 	if(vnumeropais){
								 
						 		cmsjpai = ConstantesPEP.ERRORPAISLETRA;
								context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjpai, null));
								estado = false;
							 }
						 	boolean vnumerociudad = false;
						 	for (int i = 0; i < ciudad.length();i++) { 
								 if ( Character.isDigit(ciudad.charAt(i)) ){
									 
									 vnumerociudad=true;
								 	}
								 
							 	}	 
							 	if(vnumerociudad){
									 
							 		cmsjciu  = ConstantesPEP.ERRORCIUDADLETRA;
									context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjciu, null));
									estado = false;
								 }
		
		
		return estado;		
	}
			
			
		
		
		/***/
		
		
	/** Validar campos numericos*/
	
	public boolean isNumericIntCelular(){
		
		celular = celular.trim();
		
		
		String msjnum = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			int iCelular = Integer.parseInt( celular );
			
			return true;
		}
		
		catch (NumberFormatException ex){
			ex.printStackTrace();
			
			msjnum=ConstantesPEP.ERRORCELULARNUMERICO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjnum , null));
			return false;
			
		}
		
			
	}
	public boolean isNumericIntTelefono(){
		
		
		telefono = telefono.trim();
		
		String msjnum = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			int itelefono = Integer.parseInt( telefono );
			return true;
		}
		
		catch (NumberFormatException ex){
			ex.printStackTrace();
			
			msjnum=ConstantesPEP.ERRORTELEFONONUMERICO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjnum , null));
			return false;
			
		}
		
			
	}

		public  String actionInternetContactenos(){
		logger.info("ingreso metodo FormulariosBean:actionInternetContactenos");
		boolean validaContactenos =validaCamposInternetContactenos();
		boolean validarNumericoCelular=isNumericIntCelular();
		boolean validarNumericoTelefono=isNumericIntTelefono();

		if( validaContactenos&&validarNumericoCelular&&validarNumericoTelefono ){
			
		
		try {
			
			ParametricaDTO direccionRemitenteC = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.CORREO_SOPORTE_PROQUINAL);
			String remitentec = direccionRemitenteC.getDescripcion1().trim();
			ParametricaDTO direccionEnvioC = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.CORREO_CONTACTENOS);
			String envioc = direccionEnvioC.getDescripcion1().trim();
					
				MensajeCorreoVO mcc = new MensajeCorreoVO();
				mcc.setDireccionRemitente(remitentec);
				System.out.println(remitentec);
				mcc.setDireccionesEnvio(envioc);
				System.out.println(envioc);
				mcc.setDireccionesCC("nancyesperanza@gmail.com");
				mcc.setAsunto("Nuevo contacto");
				mcc.setTextoCorreo("<table width='707' border='1' cellspacing='0' cellpadding='0' bordercolor='#999999'>" +
						  "<tr>"+
						  "<td height='41' colspan='2' align='left'><strong>Nueva información de contacto Proquinal</strong></td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td width='318'><strong>Nombres</strong></td>"+
						  "<td width='276'>"+getNombres()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Apellidos</strong></td>"+
						  "<td>"+getApellidos()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Empresa</strong></td>"+
						  "<td>"+getEmpresa()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Cargo</strong></td>"+
						  "<td>"+getCargo()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Teléfono</strong></td>"+
						  "<td>"+getTelefono()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Dirección</strong></td>"+
						  "<td>"+getDireccion()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Teléfono celular</strong></td>"+
						  "<td>"+getCelular()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Ciudad</strong></td>"+
						  "<td>"+getCiudad()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>País</strong></td>"+
						  "<td>"+getPais()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>email</strong></td>"+
						  "<td>"+getEmail()+"</td>"+
						  "</tr>"+
						  "<tr>"+
                          "<td><strong>Línea del producto en la cual está interesado</strong></td>"+
						  "<td>"+getProducto()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Motivo por el cual nos contacta</strong></td>"+
						  "<td>"+getMotivo()+"</td>"+
						  "</tr>"+
						  "<tr>"+
						  "<td><strong>Comentario</strong></td>"+
						  "<td>"+getComentario()+"</td>"+
						  "</tr>"+
						  "</table>");
				
				EnvioCorreo.envioCorreo(mcc);
				
				}
				catch (Exception e ){
				e.printStackTrace();
				}
			
			
		}
		return "success";
	}
	
	
	/** Validación de los campos para el formulario trabaje con nosotros*/
	
	public  boolean validaCamposInternet(){

		FacesContext context = FacesContext.getCurrentInstance();
		String msj = null;
		String msjpa = null;
		String msjid = null;
		String msjema = null;
		String msjfnac = null;
		String msjcelcan = null;
		String msjaspsal = null;
		
		String cmsjc = null;
		String cmsjpap = null;
		String cmssape = null;
		String cmsjciu = null;
		
		boolean estadocan=true;
		
		
		
		if ( nombre.equals("")) {
			msj = ConstantesPEP.ERRORNOMBRENULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msj, null));
			estadocan = false;
			
		}
		if ( primerApellido.equals("")) {
			msjpa = ConstantesPEP.ERRORPRIMERAPELLIDONULO ;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjpa, null));
			estadocan = false;
			
		}
		if ( identificacion.equals("")) {
			msjid = ConstantesPEP.ERRORIDNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjid, null));
			estadocan = false;
			
		}
		if ( email.equals("")) {
			msjema = ConstantesPEP.ERROREMAILNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjema, null));
			estadocan = false;
			
		}
		if ( fechaNacimiento.equals("")) {
			msjfnac = ConstantesPEP.ERRORFECHANACNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjfnac, null));
			estadocan = false;
			
		}
		if ( celularCandidato.equals("")) {
			msjcelcan = ConstantesPEP.ERRORCELCCANNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjcelcan, null));
			estadocan = false;
			
		}
		if ( aspiracionSalarial.equals("")) {
			msjaspsal = ConstantesPEP.ERRORASPSALNULO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,msjaspsal, null));
			estadocan = false;
			
		}
		
		boolean vnumeronombres = false;
		for (int i = 0; i < nombre.length();i++) { 
		 if ( Character.isDigit(nombre.charAt(i)) ){
			 
			 vnumeronombres=true;
		 	}
		 
	 	}
	 	if(vnumeronombres){
			 
			cmsjc = ConstantesPEP.ERRORNOMBRELETRA;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjc , null));
			estadocan = false;
		 }
	 	boolean vnumeroPrimerApellido = false;
		for (int i = 0; i < primerApellido.length();i++) { 
		 if ( Character.isDigit(primerApellido.charAt(i)) ){
			 
			 vnumeroPrimerApellido=true;
		 	}
		 
	 	}
	 	if(vnumeroPrimerApellido){
			 
	 		cmsjpap = ConstantesPEP.ERRORPRIMERAPELLIDOLETRA ;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjpap , null));
			estadocan = false;
		 }
	 	
	 	boolean vnumeroSegundoApellido = false;
	 	for (int i = 0; i < segundoApellido.length();i++) { 
			 if ( Character.isDigit(segundoApellido.charAt(i)) ){
				 
				 vnumeroSegundoApellido=true;
			 	}
			 
		 	}	 
		 	if(vnumeroSegundoApellido){
				 
		 		cmssape = ConstantesPEP.ERRORSEGUNDOPELLIDOLETRA;
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmssape, null));
				estadocan = false;
			 }
		 	boolean vnumeroCiudadResidencia = false;
		 	for (int i = 0; i < ciudadResidencia.length();i++) { 
				 if ( Character.isDigit(ciudadResidencia.charAt(i)) ){
					 
					 vnumeroCiudadResidencia=true;
				 	}
				 
			 	}	 
			 	if(vnumeroCiudadResidencia){
					 
			 		cmsjciu  = ConstantesPEP.ERRORCIUDADLETRA;
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cmsjciu, null));
					estadocan = false;
				 }
		 	

		return estadocan;		
		
	}
	
	/** Validación para tipo de archivo */
	
public boolean validarAchivo(){
		
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		String tipoArchivo = archivoActual.getContentType();
		System.out.println(tipoArchivo);
		
		String tipoDeArchivo[] =  ConstantesPEP.EXTENSIONESPERMITIDAS;
		String mvta = null;
		boolean archivo = false;
		
		
 			 			
	 			for (int i = 0; i < tipoDeArchivo.length; i++){ 
	 				
	 			if (tipoDeArchivo[i].equals(tipoArchivo)) { 
	 			archivo = true;	
		 	     break;  	 
		 	        }
	 			}
	 		
	 			if(!archivo){
				
				
				mvta=ConstantesPEP.MENSAJEERRORTIPOARCHIVO;
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mvta , null));
				
				
	 				}else{
	 					
	 					System.out.println ("El archivo tiene la extensión permitida");
	 					return true;
	 				}
	 			return false;
	 			
 			
}
	
/** Validación para peso del archivo */

	public boolean ValidarTamanoArchivo(){
	
	FacesContext context = FacesContext.getCurrentInstance();
	
	long pesoArchivo = archivoActual.getSize();	
	System.out.println(pesoArchivo);
	String mvtp=null;
	boolean pesoA = true;
	
		if(pesoArchivo<5120000){
		
			//mvtp=ConstantesPEP.MENSAJEERRORPESOARCHIVO;
			pesoA = true;
			
		}
		if(!pesoA){
			
			mvtp=ConstantesPEP.MENSAJEERRORTIPOARCHIVO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mvtp , null));
		}else{
				
				System.out.println ("El archivo tiene el peso permitida");
				return true;
			}
		return false;
		
	}	
	

public boolean isNumericIntCelularTrabajeNosotros(){
		
	celularCandidato = celularCandidato.trim();
		
		
		String msjnum = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			int iCelularcan = Integer.parseInt( celularCandidato);
			
			return true;
		}
		
		catch (NumberFormatException ex){
			ex.printStackTrace();
			
			msjnum=ConstantesPEP.ERRORCELULARNUMERICO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjnum , null));
			return false;
			
		}
		
			
	}
	public boolean isNumericIntAspiracionSalarial(){
		
		
		aspiracionSalarial = aspiracionSalarial.trim();
		
		String msjnum = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			
			int itelefono = Integer.parseInt( aspiracionSalarial );
			return true;
		}
		
		catch (NumberFormatException ex){
			ex.printStackTrace();
			
			msjnum=ConstantesPEP.ERRORASPIRACIONNUMERICO;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msjnum , null));
			return false;
			
		}
		
			
	}
	
	
	public  String actionInternet(){
		
		logger.info("ingreso metodo FormulariosBean:actionInternet");	
		
		
		boolean valida = validaCamposInternet();
		boolean validaArchivo= validarAchivo();
		boolean validaCelular= isNumericIntCelularTrabajeNosotros();
		boolean validaAspiracionSalarial= isNumericIntAspiracionSalarial();
		boolean pesoArchivo = ValidarTamanoArchivo();

		if( valida &&  validaArchivo && validaCelular && validaAspiracionSalarial && pesoArchivo){
			
			
		try {
			String sFechaNacimiento = UtilidadesFecha.getDateYYYYMMDD( getFechaNacimiento());

			
				ParametricaDTO direccionRemitente = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.CORREO_SOPORTE_PROQUINAL);
				String remitente = direccionRemitente.getDescripcion1().trim();
				ParametricaDTO direccionEnvio = servicioYcontent.obtenerInfoParametrica( ConstantesPEPParametricas.CORREO_TRABAJE_CON_NOSOTROS);
				String envio = direccionEnvio.getDescripcion1().trim();

				
				MensajeCorreoVO mc = new MensajeCorreoVO();
				mc.setDireccionRemitente(remitente);
				//System.out.println(remitente);
				mc.setDireccionesEnvio(envio);
				//System.out.println(envio);
				mc.setDireccionesCC("nancyesperanza@gmail.com");
				mc.setAsunto("Nueva hoja de vida de Aspirante");
				mc.setTextoCorreo("<table width='2024' border='1' cellspacing='0' cellpadding='0' bordercolor='#000000'>" +
									"<tr style='text-align:center;'>"+
									"<td colspan='5'><strong>INFORMACIÓN BÁSICA</strong></td>"+
									"<td colspan='5'><strong>INFORMACIÓN PERSONAL</strong></td>"+
									"<td colspan='2'><strong>EDUCACIÓN FORMAL</strong></td>"+
									"<td><strong>ÁREA DE INTERÉS</strong></td>"+
									"</tr>"+
									"<tr style='text-align:center'>"+
									"<td><strong>Primer apellido</strong></td>"+
									"<td><strong>Segundo apellido</strong></td>"+
									"<td><strong>Nombres</strong></td>"+
									"<td><strong>Identificación</strong></td>"+
									"<td><strong>E-Mail</strong></td>"+
									"<td><strong>Fecha de nacimiento</strong></td>"+
									"<td><strong>Género</strong></td>"+
									"<td><strong>Ciudad de residencia</strong></td>"+
									"<td><strong>Teléfono celular</strong></td>"+
									"<td><strong>Aspiración salarial</strong></td>"+
									"<td><strong>Nivel educativo</strong></td>"+
									"<td><strong>Título obtenido</strong></td>"+
									"<td><strong>Área de interés</strong></td>"+
									"</tr>"+
									"<tr>"+
									"<td>"+getPrimerApellido()+"</td>"+
									"<td>"+getSegundoApellido()+"</td>"+
									"<td>"+getNombre()+"</td>"+
									"<td>"+getIdentificacion()+"</td>"+
									"<td>"+getEmail()+"</td>"+
									//"<td>"+getFechaNacimiento()+"</td>"+
									"<td>"+sFechaNacimiento+"</td>"+
									"<td>"+getGenero()+"</td>"+
									"<td>"+getCiudadResidencia()+"</td>"+
									"<td>"+getCelularCandidato()+"</td>"+
									"<td>"+getAspiracionSalarial()+"</td>"+
									"<td>"+getNivelEducativo()+"</td>"+
									"<td>"+getTituloObtenido()+"</td>"+
									"<td>"+getAreaInteres()+"</td>"+
									"</tr>"+
									"</table>");

			
				String path = archivoActual.getPhysicalPath();
				DocAdjuntoVO doc = new DocAdjuntoVO();
				File ff = new File(path);
				doc.setByteArray(EnvioCorreo.getBytesFromFile(ff));
				doc.setNombre(ff.getName());
				mc.addDocAdjunto(doc);

				EnvioCorreo.envioCorreo(mc);

				}
				catch (Exception e ){
				e.printStackTrace();
				}
	
			
		}
			
	return "success";
	
	}
	
	private static final Logger logger = Logger.getLogger(FormulariosBean.class);
	
}


