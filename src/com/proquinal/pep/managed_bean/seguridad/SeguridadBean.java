package com.proquinal.pep.managed_bean.seguridad;

import java.util.List;

import javax.ejb.EJB;

import org.apache.log4j.Logger;

import com.proquinal.Utilidades.EjbLocator;
import com.proquinal.pep.base.ProquinalBackingBean;
import com.proquinal.pep.entidades.seguridad.Usuario;
import com.proquinal.pep.entidades.seguridad.dto.UsuarioVO;
import com.proquinal.pep.fwk.exception.BusinessException;
import com.proquinal.pep.managed_bean.nomina.NominaAction;
import com.proquinal.pep.negocioServicio.seguridad.IServicioSeguridad;

/**
 * Backing Bean utilizado para definir los requerimientos del modulo de seguridad
* @author <a href="mailto:luismartinez@btech.com.co">Luis Gabriel Martinez Lopez</a>
* @version 1.0
* @FechaCreacion 30/01/2011
*/
public class SeguridadBean extends ProquinalBackingBean {

	private static final long serialVersionUID = 1L;

	@EJB(mappedName="ejb/ServicioSeguridad")
	private IServicioSeguridad servicioSeguridad; 
	
	private String usuario;
	
	private String contrasena;
	
	private String contrasenaNueva;
	
	private String identificacion;
	
	private String correo;

	public SeguridadBean(){
		System.out.println("***********SeguridadBean");
	}
	
	public String getUsuario() {
		usuario = getUsuarioAutenticado().getIdentificacion();
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	public String getContrasenaNueva() {
		return contrasenaNueva;
	}

	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String obtenerInfoProfesor(String usuario, String contrasena) {
		Usuario u = null;
		try {
			u = servicioSeguridad.autenticarUsuario(usuario, contrasena);
			System.out.println( u.getIdentificacion());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u.toString();
	}
	
	public boolean autenticarUsuario(String usuario, String contrasena) {
		Usuario u = null;
		try {
			u = servicioSeguridad.autenticarUsuario(usuario, contrasena);
			System.out.println( u.getIdentificacion());
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
		
		if ( u != null ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getInfoEmpleado() {
		String identificacion = usuario;
		System.out.println("getInfoEmpleado: Identificacion:" + identificacion);
		UsuarioVO uVO = servicioSeguridad.obtenerInfoEmpleado(usuario, identificacion);
		System.out.println( uVO.getNombre() + " " + uVO.getApellido() );
		return null;
	}
	
	public String getInfoComercializadora() {
		String identificacion = usuario;
		System.out.println("getInfoComercializadora: Identificacion:" + identificacion);
		UsuarioVO uVO = servicioSeguridad.obtenerInfoTercero(usuario, identificacion);
		System.out.println( uVO.getNombre() );
		return null;
	}
	
	public String cambiarContrasena(){
		logger.info("ingreso cambiarContrasena");
		System.out.println("cambiarContrasena: Identificacion:" );
		boolean b;
		try {
			b = servicioSeguridad.cambiarContrasena(usuario, contrasena, contrasenaNueva);
			if ( b ) {
				System.out.println(" Cambio fue exitoso");
			}
			else {
				System.out.println(" Resultado fallido");
				return NominaAction.LOGIN;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String conocerUsuarioContrasena(){
		System.out.println("conocerUsuarioContrasena: " );
		Usuario u = servicioSeguridad.conocerUsuarioContrasenaEmpleado(identificacion, correo);
		if ( u != null ) {
			System.out.println(" Usuario " + u.getUsuario() + " " + u.getContrasena());
		}
		else {
			System.out.println(" Usuario no existe");
		}
		return null;
	}
	
	public String actualizarContrasena(){
		System.out.println("actualizarContrasena: " );
		boolean b = servicioSeguridad.resetearContrasena(usuario, contrasena);
		if ( b ) {
			System.out.println(" Cambio exitoso " + usuario + " " + contrasena);
		}
		else {
			System.out.println(" Cambio fallido");
		}
		return null;
	}
	
	public String conocerUsuarioContrasenaTercero(){
		System.out.println("conocerUsuarioContrasenaTercero: " );
		Usuario u = servicioSeguridad.conocerInfoUsuarioTercero(identificacion, correo);
		if ( u != null ) {
			System.out.println(" Usuario " + u.getUsuario() + " " + u.getContrasena());
		}
		else {
			System.out.println(" Usuario no existe");
		}
		return null;
	}
	
	public String validarUsuario() {
		logger.info("ingreso metodo validarUsuario");
		System.out.println("Before autenticacion: User:" + usuario + " Contrasena: " + contrasena);
		
		System.out.println("metodo prueba " + obtenerInfoProfesor(usuario, contrasena));
		//seguridad = (ISeguridad)EjbLocator.locate("ejb/Seguridad","com.proquinal.pep.negocioServicio.seguridad.ISeguridad");
//		System.out.println("User:" + seguridad.obtenerNombre());
//		
//		boolean b = seguridad.autenticarUsuario(usuario, contrasena);
//		System.out.println("Resultado autenticacion " + b);
//		if ( b ){
//			return "success";
//		}
		
		return "failure";
	}
	
	public String rolesUsuarios(){
		logger.info("ingreso metodo rolesUsuarios");
		List<String> s;
		try {
			s = servicioSeguridad.obtenerRolesUsuario(usuario);
			for (Object object : s) {
				System.out.println( object.toString());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return null;
	}
	
	public String autenticacion(){
		System.out.println("********************** metodo de autenticacion **********************");
		try {
			boolean b = false;
			System.out.println("antes invocacion locator ");
			IServicioSeguridad seguridad = (IServicioSeguridad) EjbLocator.locate("ejb/ServicioSeguridad", "com.proquinal.pep.negocioServicio.seguridad.IServicioSeguridad");
			System.out.println("despues invocacion locator ");
			b = seguridad.autenticacion(usuario, contrasena);
			if ( b ) {
				System.out.println("correcto " + b );
			}
			else{
				System.out.println("Incorrecto " + b );
			}
			System.out.println( b );
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	private static final Logger logger = Logger.getLogger(SeguridadBean.class);
}
